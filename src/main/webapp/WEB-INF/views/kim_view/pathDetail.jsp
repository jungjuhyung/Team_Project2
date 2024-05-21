<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/pathDetail.css">
<!-- include libraries(jQuery, bootstrap) -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<script src="resources/jung_summernote/summernote-lite.js"></script>
<script src="resources/jung_summernote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="resources/jung_summernote/summernote-lite.css">
<script type="text/javascript">
function rcommentInsert(f) {
	console.log="${kpostvo.path_post_idx}";
	f.action="rcommentInsert";
	f.submit();
}
function rcommentDelete(f) {
	console.log="${kpostvo.path_post_idx}";
	f.action="rcommentDelete";
	f.submit();
}
function ilikethis() {
    var dataToSend = {
        path_post_idx: "${kpostvo.path_post_idx}",
        u_idx: "${kpostvo.u_idx}",
        firstimage: "${kpostvo.firstimage}",
        r_contenttypeid: "${kpostvo.r_contenttypeid}",
        path_post_title: "${kpostvo.path_post_title}"
    };

    $.ajax({
        url: 'ilikethis', 
        type: 'POST', 
        data: dataToSend,
        success: function(response) {
            alert("좋아요를 눌렀습니다.");
            var likeButton = $("#likeButton");
            likeButton.text("취소(찜)");
            likeButton.attr("onclick", "ihatethis()");
        },
        error: function() {
            alert("실패.");
        }
    });
}

function ihatethis() {
    var dataToSend = {
        path_post_idx: "${kpostvo.path_post_idx}",
        u_idx: "${kpostvo.u_idx}",
        firstimage: "${kpostvo.firstimage}",
        r_contenttypeid: "${kpostvo.r_contenttypeid}",
        path_post_title: "${kpostvo.path_post_title}",
        path_idx: "${kpostvo.path_idx}"
    };

    $.ajax({
        url: 'ihatethis', 
        type: 'POST', 
        data: dataToSend,
        success: function(response) {
            alert("좋아요를 취소했습니다.");
            var likeButton = $("#likeButton");
            likeButton.text("좋아요(찜)");
            likeButton.attr("onclick", "ilikethis()");
        },
        error: function() {
            alert("실패.");
        }
    });
}

function openModal(src) {
	console.log("Modal opened with source:", src); // 디버깅 로그 추가
    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("img01");
    modal.style.display = "block";
    modalImg.src = src;
}

function closeModal() {
	console.log("Modal closed"); // 디버깅 로그 추가
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
	<div id="world">
		<div id="infoUser">
			<div id="reviewTitle">${kpostvo.path_post_title}</div>
			<div id="reviewUserDate">
				<div id="reviewUser">글쓴이 : ${kpostvo.u_id}</div>
				<div id="reviewDate">작성날짜 : ${kpostvo.regdate.substring(0,10)}</div>
			</div>
		</div>
		<div id="map" style="width: 100%; height: 500px;"></div>
		
			
	 <div class="d_img">
    <div class="image-slider">
        <div class="slider-wrapper">
            <c:forEach var="marker" items="${tourtestvoimg}">
                <c:forEach var="img" items="${marker.imgList}">
                    <c:choose>
                        <c:when test="${img.img_status == 0}">
                            <img class="div_img" src="resources/rc_main_img/${img.image_name}" onclick="openModal(this.src)">
                        </c:when>
                        <c:otherwise>
                            <img class="div_img" src="${img.image_name}"  onclick="openModal(this.src)">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:forEach>
        </div>
        <button class="prev-btn" onclick="moveSlider(-1)">&#10094;</button>
        <button class="next-btn" onclick="moveSlider(1)">&#10095;</button>
    </div>
</div>

    
    
    
    
		<!-- Modal Structure -->
    <div id="myModal" class="modal">
        <span class="close" onclick="closeModal()">&times;</span>
        <img class="modal-content" id="img01">
    </div>

    <script src="scripts.js"></script>
		
		<div class="empty-area"></div>
		<div id="summer">
			<textarea rows="10" cols="60" id="summernote" name="content">${kpostvo.path_post_content}</textarea>
		</div>
		<div>
		<c:choose>
			<c:when test="${membervo.u_idx == kpostvo.u_idx}">	
				<button type="button">수정</button>
				<button type="button">삭제</button>
			</c:when>
			<c:otherwise>
				<span></span>
			</c:otherwise>
		</c:choose>
		</div>
		<div class="empty-area"></div>
		<c:if test="${membervo != null}">
		    <div id="likeButtonContainer">
		        <c:choose>
		            <c:when test="${kpostvo.u_heart == '1' }">
		                <button type="button" id="likeButton" onclick="ihatethis()"
		                style="background-color: red;">취소(찜)</button>
		            </c:when>
		            <c:otherwise>
		                <button type="button" id="likeButton" onclick="ilikethis()"
		                style="background-color: blue;">좋아요(찜)</button>
		            </c:otherwise>
		        </c:choose>
		    </div>
		</c:if>
	<div class="empty-area"></div>
		<%-- 댓글 출력 --%>
	<div class="recomment">
		<c:forEach var="k" items="${comment_list}">
			<div>
				<form method="post">
					<div class="renick">${k.u_nickname}</div>
					<div class="recontent">
						<textarea rows="3" cols="40" name="content" readonly>${k.content}</textarea>
					</div>
						<div class="rebutton">${k.regdate.substring(0,19)}
						<c:choose>
							<c:when test="${membervo.u_idx == k.u_idx}">							
								<input class="rewrite" type="button" value="삭제" onclick="rcommentDelete(this.form)">
							</c:when>
							<c:otherwise>
								<span></span>
							</c:otherwise>
						</c:choose>
						</div>
						<input type="hidden" name = "comment_idx" value="${k.comment_idx}" >
						<input type="hidden" name = "path_post_idx" value="${k.path_post_idx}" >
				</form>
			</div>
		</c:forEach>
	</div>	
		<%-- 댓글 입력 --%>
	<c:if test="${membervo != null}">
	<div class="recomment">
		<form method="post">
			<fieldset>
			<div class="renick">
				<span>${membervo.u_nickname}</span> 
			</div>
			<div class="recontent">
				<textarea rows="3" cols="40" name="content"></textarea>
			</div>
			<div class="rebutton">
				<input class="rewrite" type="button" value="저장" onclick="rcommentInsert(this.form)">
			</div>
		
				<!-- 댓글 저장시 어떤 원글의 댓글인지 저장해야 한다. -->
				<input type="hidden" name = "path_post_idx" value="${kpostvo.path_post_idx}" >
			</fieldset>
		</form>
	</div>
	</c:if>
		<div id="empty-area"></div>
	</div>
	<script>
	console.log(${mapyList.size()})
		// 메인화면 페이지 로드 함수
		$(document).ready(function() {
			$('#summernote').summernote({
				 lang: "ko-KR",
					height: 300,
					minHeight: null,
					maxHeight: null,
			});
			$('#summernote').summernote('disable');
		});
	</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3f599c1d6971197a01e6600cd397224a&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var mapOption = {
    center: new kakao.maps.LatLng(${mapy1}, ${mapx1}), // 지도의 중심 좌표
    level: 8 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var positions = [];
var linePath = [];
var mapyList = <%= request.getAttribute("mapyList") %>;
var mapxList = <%= request.getAttribute("mapxList") %>;
var marktitle = JSON.parse('<%= request.getAttribute("marktitle") %>');

// mapxList 출력
mapxList.forEach(function(value) {
});

for (let i = 0; i < mapyList.length; i++) {
    let mapy = mapyList[i];
    let mapx = mapxList[i];
    let title = marktitle[i];
    let latlng = new kakao.maps.LatLng(mapy, mapx); // 올바른 kakao.maps.LatLng 객체 생성
    let distanceOverlay;
    /* let content = '<div class="dotOverlay distanceInfo">총거리 <span class="number">' + distance + '</span>m</div>'; // 커스텀오버레이에 추가될 내용입니다 */
        
 // 좌표를 콘솔에 출력
    
    positions.push({
        title: '',
        latlng: latlng // 생성한 kakao.maps.LatLng 객체를 저장
    });

    linePath.push(latlng);

    // 마커 생성
    var marker = new kakao.maps.Marker({
        map: map,
        position: latlng // 올바른 LatLng 객체를 position으로 설정
    });

    // 인포윈도우 생성
    var infowindow = new kakao.maps.InfoWindow({
        content: (i + 1)+'.' + title +' '  ,
        removable: true
    });
	
    
    
    
    // 클로저를 사용하여 현재 인덱스를 이용하여 이벤트 핸들러를 정의합니다.
    (function(marker, infowindow) {
        kakao.maps.event.addListener(marker, 'click', function() {
            infowindow.open(map, marker);
        });
    })(marker, infowindow);
}


// 지도에 표시할 선을 생성합니다
var polyline = new kakao.maps.Polyline({
    path: linePath,
    strokeWeight: 2,
    strokeColor: '#FFAE00',
    strokeOpacity: 0.7,
    strokeStyle: 'solid'

});

// 지도에 선을 표시합니다 
polyline.setMap(map);  

var totalDistance = polyline.getLength(); // getLength() 메서드로 폴리라인의 길이를 계산합니다.


// HTML Content를 만들어 리턴하는 함수입니다
function getTimeHTML(distance) {
	// 총거리 소수점제거
	distance = Math.round(distance); 
    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
    var walkTime = distance / 67 | 0;
    var walkHour = '', walkMin = '';

    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
    if (walkTime > 60) {
        walkHour = '<span class="number">' + Math.floor(walkTime / 60) + '</span>시간 ';
    }
    walkMin = '<span class="number">' + walkTime % 60 + '</span>분';

    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
    var bicycleTime = distance / 267 | 0;
    var bicycleHour = '', bicycleMin = '';

    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
    if (bicycleTime > 60) {
        bicycleHour = '<span class="number">' + Math.floor(bicycleTime / 60) + '</span>시간 ';
    }
    bicycleMin = '<span class="number">' + bicycleTime % 60 + '</span>분';

    // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
    var content = '<ul class="dotOverlay distanceInfo">';
    content += '    <li>';
    content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
    content += '    </li>';
    content += '    <li>';
    content += '        <span class="label">도보</span>' + walkHour + walkMin;
    content += '    </li>';
    content += '    <li>';
    content += '        <span class="label">자전거</span>' + bicycleHour + bicycleMin;
    content += '    </li>';
    content += '</ul>';

    return content;
}
 
//거리 값을 HTML 컨텐츠 생성 함수에 전달하여 거리 정보 표시
 var distanceContent = getTimeHTML(totalDistance);
 console.log(distanceContent); // 콘솔에 거리 정보 출력

 //HTML Content를 표시할 위치 지정 (여기서는 첫 번째 마커 위치로 설정)
 var position = linePath[0]; // 거리를 표시할 위치를 첫 번째 마커로 설정

 // 커스텀 오버레이 생성
 var distanceOverlay = new kakao.maps.CustomOverlay({
     position: position,
     content: distanceContent,
     xAnchor: 0.5,
     yAnchor: 1.5
 });

 // 지도에 커스텀 오버레이 표시
 distanceOverlay.setMap(map);

</script>
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>




















