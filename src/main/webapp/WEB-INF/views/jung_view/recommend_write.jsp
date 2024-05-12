<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/jung_css/recommend_write.css">
<!-- 서머노트를 위해 추가해야할 부분 -->
<script src="resources/jung_summernote/summernote-lite.js"></script>
<script src="resources/jung_summernote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="resources/jung_summernote/summernote-lite.css">
<style type="text/css">
	/* summernote toolbar 수정 */
    .note-btn-group{width: auto;}
    .note-toolbar{width: auto;}
</style>
</head>
<body>
	<div>
		<div id="map"></div>
	</div>
	<input type="button" value="경로 초기화" onclick="path_del()">
	<div id="wish_box">
		<h2 id="wish_title">위시리스트</h2>
		<c:choose>
			<c:when test="${empty marker_list}">
				찜한 장소가 없습니다.
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${marker_list}">
					<div class="wishs">
						<input class="chk_box" type="checkbox" name="chk" value="0">
						<div><img src="${k.firstimage}"></div>
						<p>${k.place_title}</p>
						<input type="hidden" name="place_title" value="${k.place_title}">
						<input type="hidden" name="mapx" value="${k.mapx}">
						<input type="hidden" name="mapy" value="${k.mapy}">
						<input type="hidden" name="contentid" value="${k.contentid}">
						<input type="hidden" name="areacode" value="${k.areacode}">
						<input type="hidden" name="sigungucode" value="${k.sigungucode}">
						<input type="hidden" name="contenttypeid" value="${k.contenttypeid}">
					</div>
				</c:forEach>			
			</c:otherwise>
		</c:choose>
	</div>
	<form action="recommend_write_ok" method="post" enctype="multipart/form-data">
		<p>경로 유형</p>
			<input type="radio" name="r_contenttypeid" value="12">관광지
			<input type="radio" name="r_contenttypeid" value="15">문화시설
			<input type="radio" name="r_contenttypeid" value="39">음식
			<input type="radio" name="r_contenttypeid" value="99">종합
		<p>경로 지역</p>
		<select name="r_areacode">
			<option value="1">서울</option>
			<option value="2">인천</option>
			<option value="3">대전</option>
			<option value="4">대구</option>
			<option value="5">광주</option>
			<option value="6">부산</option>
			<option value="7">울산</option>
			<option value="8">세종시</option>
			<option value="31">경기도</option>
			<option value="32">강원도</option>
			<option value="33">충청북도</option>
			<option value="34">충청남도</option>
			<option value="35">경상북도</option>
			<option value="36">경상남도</option>
			<option value="37">전라북도</option>
			<option value="38">전라남도</option>
			<option value="39">제주도</option>
		</select>
		<p>
			<label>메인 이미지 : </label>
			<input type="file" name="f_main">
		</p>
		<div id="upload_box">
		</div>
		<div>
			<label>본글 제목 : </label>
			<input type="text" name="path_post_title">
		</div>
		<div class="container">
	  		<textarea class="summernote" name="path_post_content"></textarea>    
		</div>
		<input type="submit" value="작성하기">
	</form>

<!-- 섬머노트 스크립트 -->
<script>
$('.summernote').summernote({
    lang: "ko-KR",
	height: 300,
	minHeight: null,
	maxHeight: null,
    focus: true,
    placeholder: "최대 3000자까지 쓸 수 있습니다."
});
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c186c802b1e519c6f748b4481b8a4b53"></script>
<script>
// 기본 map 생성
let mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(35.207766, 128.569655), // 지도의 중심좌표
        level: 6 // 지도의 확대 레벨
    };

let map = new kakao.maps.Map(mapContainer, mapOption);

// 지도에 생성된 마커들 모음
let markers = [];
// 지도에 생성된 infowindow 모음
let infos= [];

// 선긋기 이벤트용 변수들
let drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
let moveLine; // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
let clickLine; // 마우스로 클릭한 좌표로 그려질 선 객체입니다
let distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
let dots = {}; // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.
let index = 0; // 지도에서 마커 선택시 순서 표시 변수
let upload_idx = 0; // 마커 표시에 따른 upload name변수 이름 지정

// 마커 생성 함수
function marker(position,contentid,areacode,sigungucode,contenttypeid) {
	// 마커 이미지
	let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
	    
		// 마커 이미지의 이미지 크기 입니다.
	    let imageSize = new kakao.maps.Size(24, 35);
	    // 마커 이미지를 생성합니다.
	    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	    
	    // 마커를 생성합니다.
	    let marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: position.latlng, // 마커를 표시할 위치
	        title : position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다.
	        image : markerImage // 마커 이미지
	    });
	    
	    kakao.maps.event.addListener(marker, 'click', function() {
		    index += 1;
	    	let infowindow = new kakao.maps.InfoWindow({
		        content: "<div class='info_title'>"+position.title+"</div>"+"<div class='info_num'>"+index+"</div>", // 인포윈도우에 표시할 내용
		        position: position.latlng
		    });
	    	console.log(infowindow.getPosition())
	    	infos.push(infowindow)
	    	infowindow.open(map, marker);
    		line_draw(marker)
    		div_create(marker,contentid,areacode,sigungucode,contenttypeid)
		});
	    markers.push(marker)
	    map.setCenter(position.latlng);
}
// 마커 사진 업로드 div생성 function
function div_create(marker,contentid,areacode,sigungucode,contenttypeid) {
    var divElement = $('<div></div>', {
        class: 'markers'
    });
    let span = $('<span></span>',{
    	class : "marker_title"
    }).text(marker.getTitle())
    let inputFile = $('<input/>', {
        type: 'file',
        name: 'marker'+ upload_idx,
        multiple: "multiple",
        onchange: "checkFileCount(this)"
    });
    
    let inputTitle = $('<input/>', {
        type: 'hidden',
        name: 'title',
        value : marker.getTitle()
    });
    let inputX = $('<input/>', {
        type: 'hidden',
        name: 'mapy',
        value : marker.getPosition().getLat()
    });
    let inputY = $('<input/>', {
        type: 'hidden',
        name: 'mapx',
        value : marker.getPosition().getLng()
    });
    let inputContentid = $('<input/>', {
        type: 'hidden',
        name: 'contentid',
        value : contentid
    });
    let inputAreacode = $('<input/>', {
        type: 'hidden',
        name: 'areacode',
        value : areacode
    });
    let inputSigungucode = $('<input/>', {
        type: 'hidden',
        name: 'sigungucode',
        value : sigungucode
    });
    let inputContenttypeid = $('<input/>', {
        type: 'hidden',
        name: 'contenttypeid',
        value : contenttypeid
    });

	divElement.append(span)
	divElement.append(inputFile)
	divElement.append(inputTitle)
	divElement.append(inputX)
	divElement.append(inputY)
	divElement.append(inputContentid)
	divElement.append(inputAreacode)
	divElement.append(inputSigungucode)
	divElement.append(inputContenttypeid)
	upload_idx += 1;
	$("#upload_box").append(divElement)
}

// 마커별 사진 업로드 갯수 제한
function checkFileCount(input) {
    const maxCount = 5; // 최대 파일 수 설정
    if (input.files.length > maxCount) {
        alert(`장소별 사진은 최대 ${maxCount}개까지 등록 가능합니다.`);
        input.value = ''; // 입력된 파일 선택을 초기화
    }
}

// 마커 제거 함수(타이틀 기반)
function marker_del(position) {
	for (let i = 0; i < markers.length; i++) {
		if (markers[i].getTitle() == position.title) {
			markers[i].setMap(null);
		}
	}
	for (let i = 0; i < infos.length; i++) {
		console.log("info", infos[i].getPosition())
		console.log("position", position.latlng)
		console.log(infos[i].getPosition().equals(position.latlng))
		if (infos[i].getPosition().equals(position.latlng)) {
			infos[i].close();
		}
	}
}
// 체크박스 상태가 변하면 타이틀, 위도, 경도를 가지고 상태에 따른 함수 실행
$(".chk_box").change(function() {
		let y = $(this).next().next().next().next().val()
		let x = $(this).next().next().next().next().next().val()
		let contentid = $(this).next().next().next().next().next().next().val()
		let areacode = $(this).next().next().next().next().next().next().next().val()
		let sigungucode = $(this).next().next().next().next().next().next().next().next().val()
		let contenttypeid = $(this).next().next().next().next().next().next().next().next().next().val()
        let position = 
            {
        		title: $(this).next().next().next().val(), 
                latlng: new kakao.maps.LatLng(x, y)
            }
	if ($(this).prop("checked")) {
        marker(position,contentid,areacode,sigungucode,contenttypeid)
    } else {
        marker_del(position)
    }
})

// --------------------------- 여기서부터 선긋기 함수 실행

function line_draw(marker) {
    // 마우스로 클릭한 위치입니다
   	var clickPosition = marker.getPosition();
    // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
     if (!drawingFlag) {

        // 상태를 true로, 선이 그리고있는 상태로 변경합니다
        drawingFlag = true;
        
        // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
        deleteClickLine();
        
        // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
        deleteDistnce();

        // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
        deleteCircleDot();
    
        // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
        clickLine = new kakao.maps.Polyline({
            map: map, // 선을 표시할 지도입니다 
            path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
            strokeWeight: 3, // 선의 두께입니다 
            strokeColor: '#db4040', // 선의 색깔입니다
            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다
        });
        
        // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
        moveLine = new kakao.maps.Polyline({
            strokeWeight: 3, // 선의 두께입니다 
            strokeColor: '#db4040', // 선의 색깔입니다
            strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다    
        });
    
        // 클릭한 지점에 대한 정보를 지도에 표시합니다
        displayCircleDot(clickPosition, 0)
    } else { // 선이 그려지고 있는 상태이면

        // 그려지고 있는 선의 좌표 배열을 얻어옵니다
        var path = clickLine.getPath();

        // 좌표 배열에 클릭한 위치를 추가합니다
        path.push(clickPosition);
        
        // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
        clickLine.setPath(path);

        var distance = Math.round(clickLine.getLength());
        displayCircleDot(clickPosition, distance);
    }
    console.log(moveLine)
}

// 지도에 마우스무브 이벤트를 등록합니다
// 선을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 선의 위치를 동적으로 보여주도록 합니다             

// 지도에 마우스 오른쪽 클릭 이벤트를 등록합니다
// 선을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면 선 그리기를 종료합니다
kakao.maps.event.addListener(map, 'rightclick', function (mouseEvent) {

    // 지도 오른쪽 클릭 이벤트가 발생했는데 선을 그리고있는 상태이면
    if (drawingFlag) {
        
        // 마우스무브로 그려진 선은 지도에서 제거합니다
        moveLine.setMap(null);
        moveLine = null;  
        
        // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
        var path = clickLine.getPath();
    
        // 선을 구성하는 좌표의 개수가 2개 이상이면
        if (path.length > 1) {

            // 마지막 클릭 지점에 대한 거리 정보 커스텀 오버레이를 지웁니다
            if (dots[dots.length-1].distance) {
                dots[dots.length-1].distance.setMap(null);
                dots[dots.length-1].distance = null;    
            }

            var distance = Math.round(clickLine.getLength()), // 선의 총 거리를 계산합니다
                content = getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다
                
            // 그려진 선의 거리정보를 지도에 표시합니다
            showDistance(content, path[path.length-1]);  
             
        } else {

            // 선을 구성하는 좌표의 개수가 1개 이하이면 
            // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
            deleteClickLine();
            deleteCircleDot(); 
            deleteDistnce();

        }
        // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
        drawingFlag = false;          
    }  
});    

function infofunction(map, marker, infowindow) {
	infowindow.open(map, marker);
}

// 경로 초기화
function path_del() {
	deleteClickLine()
	deleteDistnce()
	deleteCircleDot()
	for (let i = 0; i < infos.length; i++) {
		infos[i].close()
	}
	index = 0;
	drawingFlag = false;
	upload_idx = 0;
	$("#upload_box").empty()	
}

// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
function deleteClickLine() {
    if (clickLine) {
        clickLine.setMap(null);    
        clickLine = null;        
    }
}

// 그려지고 있는 선의 총거리 정보와 
// 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
function deleteDistnce () {
    if (distanceOverlay) {
        distanceOverlay.setMap(null);
        distanceOverlay = null;
    }
}

// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 지도에서 모두 제거하는 함수입니다
function deleteCircleDot() {
    var i;
    for ( i = 0; i < dots.length; i++ ){
        if (dots[i].circle) { 
            dots[i].circle.setMap(null);
        }

        if (dots[i].distance) {
            dots[i].distance.setMap(null);
        }
    }
    dots = [];
}

//선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여 
//클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
function displayCircleDot(position, distance) {

 // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
 	var circleOverlay = new kakao.maps.CustomOverlay({
	     content: '<span class="dot"></span>',
	     position: position,
	     zIndex: 1
 	});

 // 지도에 표시합니다
 	circleOverlay.setMap(map);

 	if (distance > 0) {
     // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
    	 var distanceOverlay = new kakao.maps.CustomOverlay({
	         content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
	         position: position,
	         yAnchor: 1,
	         zIndex: 2
     });
     // 지도에 표시합니다
     distanceOverlay.setMap(map);
 }

 // 배열에 추가합니다
 dots.push({circle:circleOverlay, distance: distanceOverlay});
}

//마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
//마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
function showDistance(content, position) {
 
 if (distanceOverlay) { // 커스텀오버레이가 생성된 상태이면
     
     // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
     distanceOverlay.setPosition(position);
     distanceOverlay.setContent(content);
     
 } else { // 커스텀 오버레이가 생성되지 않은 상태이면
     
     // 커스텀 오버레이를 생성하고 지도에 표시합니다
     distanceOverlay = new kakao.maps.CustomOverlay({
         map: map, // 커스텀오버레이를 표시할 지도입니다
         content: content,  // 커스텀오버레이에 표시할 내용입니다
         position: position, // 커스텀오버레이를 표시할 위치입니다.
         xAnchor: 0,
         yAnchor: 0,
         zIndex: 10  
     });      
 }
}

// 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여 
// 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
// HTML Content를 만들어 리턴하는 함수입니다
function getTimeHTML(distance) {

    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
    var walkkTime = distance / 67 | 0;
    var walkHour = '', walkMin = '';

    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
    if (walkkTime > 60) {
        walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
    }
    walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'

    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
    var bycicleTime = distance / 227 | 0;
    var bycicleHour = '', bycicleMin = '';

    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
    if (bycicleTime > 60) {
        bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
    }
    bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'

    // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
    var content = '<ul class="dotOverlay distanceInfo">';
    content += '    <li>';
    content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
    content += '    </li>';
    content += '    <li>';
    content += '        <span class="label">도보</span>' + walkHour + walkMin;
    content += '    </li>';
    content += '    <li>';
    content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
    content += '    </li>';
    content += '</ul>'

    return content;
}
</script>
</body>
</html>