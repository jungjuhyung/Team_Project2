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
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<!-- include summernote css/js-->
<link rel="stylesheet" href="resources/jung_summernote/summernote-lite.css">
<script type="text/javascript">
function rcommentInsert(f) {
	f.action="rcommentInsert";
	f.submit();
}
function rcommentDelete(f) {
	f.action="rcommentDelete";
	f.submit();
}
function reportWrite(f) {
	f.action="reportWrite";
	f.submit();
}

function pathDelete(f) {
	f.action="pathDelete";
	f.submit();
}
function ilikethis() {
    var dataToSend = {
        path_post_idx: "${kpostvo.path_post_idx}",
        u_idx: "${kpostvo.u_idx}",
        firstimage: "${kpostvo.firstimage}",
        r_contenttypeid: "${kpostvo.r_contenttypeid}",
        path_post_title: "${kpostvo.path_post_title}"
        path_post_title: "${kpostvo.img_status}"
    };

    $.ajax({
        url: 'ilikethis', 
        type: 'POST', 
        data: dataToSend,
        success: function(response) {
            alert("좋아요를 눌렀습니다.");
            var likeButton = $("#likeButton");
            likeButton.html("&#x1F44E; 취소"); // 👎 취소
            likeButton.attr("onclick", "ihatethis()");
            likeButton.css("background-color", "red"); // 버튼 색상 변경
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
            likeButton.html("&#x1F44D; 좋아요"); // 👍 좋아요
            likeButton.attr("onclick", "ilikethis()");
            likeButton.css("background-color", "blue"); // 버튼 색상 변경
        },
        error: function() {
            alert("실패.");
        }
    });
}

function openModal(src) {
	console.log("Modal opened with source:", src); // 디버깅 로그 추가
    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("rbimg01");
    modal.style.display = "block";
    modalImg.src = src;
}

function closeModal() {
	console.log("Modal closed"); // 디버깅 로그 추가
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

function formatDate(date) {
    let year = date.getFullYear();
    let month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더함
    let day = date.getDate().toString().padStart(2, '0');
    return year + month + day;
}

function addDays(date, days) {
    let result = new Date(date);
    result.setDate(date.getDate() + days);
    return result;
}

function translateSky(value) {
    if (value === '1') return '맑음';
    else if (value === '3') return '구름 많음';
    else if (value === '4') return '흐림';
    else return '알 수 없음';
}

function weather(btn) {
	console.log(btn)
	let title = $(btn).text();
	console.log(title)
	let ajax_x = $(btn).find('input[type="hidden"][name="ajax_x"]').val();
	let ajax_y = $(btn).find('input[type="hidden"][name="ajax_y"]').val();
	$.ajax({
	    url: 'weather?ajax_x='+ajax_x+"&ajax_y="+ajax_y,
	    type: 'POST',
		dataType : "json",
	    success: function(data) {
	    	$("#weather_box").empty();
	    	let item = data.response.body.items.item;
	    	let f_now = new Date();
	    	let now = formatDate(f_now)
			let tomorrow = formatDate(addDays(f_now, 1))
			let toTomorrow = formatDate(addDays(f_now, 2))
			
			let todayWeather = [];
		    let tomorrowWeather = [];
		    let toTomorrowWeather = [];
			/* 오늘날씨 */
	    	if (item[0].baseTime <= 800) {
	    		let now01 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "0900" && (k.category === "POP" || k.category === "SKY");
	    		})
	    		let now02 = item.filter(function(k) {
	    		    return  k.fcstDate === now && k.fcstTime === "1400" && (k.category === "POP" || k.category === "SKY");
	    		})
	    		let now03 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "1900" && (k.category === "POP" || k.category === "SKY");
	    		})
	    		
	    		todayWeather.push(now01)
	    		todayWeather.push(now02)
	    		todayWeather.push(now03)
			}else if (item[0].baseTime > 800 & item[0].baseTime <= 1400) {
	    		let now01 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "0900" && (k.category === "POP" || k.category === "SKY");
	    		})
				let now02 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "1400" && (k.category === "POP" || k.category === "SKY");
	    		})
	    		let now03 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "1900" && (k.category === "POP" || k.category === "SKY");
	    		})
	    		todayWeather.push(now01)
	    		todayWeather.push(now02)
	    		todayWeather.push(now03)
			}else if (item[0].baseTime > 1400) {
	    		let now01 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "0900" && (k.category === "POP" || k.category === "SKY");
	    		})
				let now02 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "1400" && (k.category === "POP" || k.category === "SKY");
	    		})
	    		let now03 = item.filter(function(k) {
	    		    return k.fcstDate === now && k.fcstTime === "1900" && (k.category === "POP" || k.category === "SKY");
	    		})
	    		todayWeather.push(now01)
	    		todayWeather.push(now02)
	    		todayWeather.push(now03)
			}
	    	/* 내일 날씨 */
	    	let tomorrow01 = item.filter(function(k) {
    		    return k.fcstDate === tomorrow && k.fcstTime === "0900" && (k.category === "POP" || k.category === "SKY");
    		})
	    	let tomorrow02 = item.filter(function(k) {
    		    return k.fcstDate === tomorrow && k.fcstTime === "1400" && (k.category === "POP" || k.category === "SKY");
    		})
	    	let tomorrow03 = item.filter(function(k) {
    		    return k.fcstDate === tomorrow && k.fcstTime === "1900" && (k.category === "POP" || k.category === "SKY");
    		})
		    tomorrowWeather.push(tomorrow01)
	    	tomorrowWeather.push(tomorrow02)
	    	tomorrowWeather.push(tomorrow03)
    		/* 모레 날씨 */
	    	let toTomorrow01 = item.filter(function(k) {
    		    return k.fcstDate === toTomorrow && k.fcstTime === "0900" && (k.category === "POP" || k.category === "SKY");
    		})
	    	let toTomorrow02 = item.filter(function(k) {
    		    return k.fcstDate === toTomorrow && k.fcstTime === "1400" && (k.category === "POP" || k.category === "SKY");
    		})
	    	let toTomorrow03 = item.filter(function(k) {
    		    return k.fcstDate === toTomorrow && k.fcstTime === "1900" && (k.category === "POP" || k.category === "SKY");
    		})
    		toTomorrowWeather.push(toTomorrow01)
	    	toTomorrowWeather.push(toTomorrow02)
	    	toTomorrowWeather.push(toTomorrow03)
	    	
			
			let div = "<table class='table_style'>";
			div += "<tr><th colspan='4' class='table_title'>"+title+"</th></tr>";
			div += "<tr><th class='table_th'>날짜</th><th class='table_th'>오전</th><th class='table_th'>오후</th><th class='table_th'>저녁</th></tr>";
			div += "<tr><td>당일</td>";
			todayWeather.forEach(function(k) {
				if (k.length === 0) {
					div += "<td>지난 시간</td>";
				}else {
					div += "<td>강수확률 : "+k[1].fcstValue+"%, 하늘 : "+translateSky(k[0].fcstValue)+"</td>";
				}
			})
			div += "</tr>";
			div += "<tr><td>내일</td>";
			tomorrowWeather.forEach(function(k) {
				if (k.length === 0) {
					div += "<td>지난 시간</td>";
				}else {
					div += "<td>강수확률 : "+k[1].fcstValue+"%, 하늘 : "+translateSky(k[0].fcstValue)+"</td>";
				}
			})
			div += "</tr>";
			div += "<tr><td>모레</td>";
			toTomorrowWeather.forEach(function(k) {
				if (k.length === 0) {
					div += "<td>지난 시간</td>";
				}else {
					div += "<td>강수확률 : "+k[1].fcstValue+"%, 하늘 : "+translateSky(k[0].fcstValue)+"</td>";
				}
			})
			div += "</tr></table>";
	    	$("#weather_box").append(div)
	    },
	    error: function(xhr, status, error) {
	        alert("실패")
	    }
	});
}

</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
	<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
	<script src="resources/jung_summernote/summernote-lite.js"></script>
	<script src="resources/jung_summernote/summernote-ko-KR.js"></script>
	<form method="post">
	<div id="world">
		<div id="infoUser">
			<div id="reviewTitle">${kpostvo.path_post_title}</div>
			<div id="reviewUserDate">
				<div id="reviewUser">작성자 : ${kpostvo.u_nickname}(${kpostvo.u_lev})</div>
				<div id="reviewDate">작성날짜 : ${kpostvo.regdate.substring(2,10)}</div>
			</div>
		</div>
		<div id="map" style="width: 100%; height: 500px;"></div>
		<div class="empty-area"></div>
			
	<div class="d_img">
	    <div class="image-slider">
	        <div class="slider-wrapper">
	            <c:forEach var="marker" items="${tourtestvoimg}">
	                <c:forEach var="img" items="${marker.imgList}">
	                    <div class="img-container">
	                        <c:choose>
	                            <c:when test="${img.img_status == 0}">
	                                <img class="div_img" src="resources/rc_main_img/${img.image_name}" onclick="openModal(this.src)">
	                            </c:when>
	                            <c:otherwise>
	                                <img class="div_img" src="${img.image_name}" onclick="openModal(this.src)">
	                            </c:otherwise>
	                        </c:choose>
	                        <div class="img-title" onclick="weather(this)">${img.title}
	                        	<input type="hidden" name="ajax_x" value="${img.mapx}">
	                        	<input type="hidden" name="ajax_y" value="${img.mapy}">
	                        </div>
	                    </div>
	                </c:forEach>
	            </c:forEach>
	        </div>
	        <button class="prev-btn" onclick="moveSlider(-1)">&#10094;</button>
	        <button class="next-btn" onclick="moveSlider(1)">&#10095;</button>
	    </div>
	</div>

    
    
    
    
		<!-- Modal Structure -->
    <div id="myModal" class="modal">
        <span class="rboxclose" onclick="closeModal()">&times;</span>
        <img class="modal-content" id="rbimg01">
    </div>
	<div id="weather_box"></div>
    <script src="scripts.js"></script>
		<div class="empty-area"></div>
		<div id="summer">
			<textarea rows="10" cols="60" id="summernote" name="content">${kpostvo.path_post_content}</textarea>
		</div>
		<div class="empty-area"></div>
		<div>
			<c:if test="${adminUser != null || membervo.u_idx == kpostvo.u_idx}">
				<input type="hidden" name="path_post_idx" value="${kpostvo.path_post_idx}">
				<input type="button" class="reportbtn" value="삭제" onclick="pathDelete(this.form)"/>
			</c:if>
			<c:if test="${membervo.u_idx != kpostvo.u_idx && membervo != null}">
				<input type="hidden" name="reported_id" value="${kpostvo.u_id}">
				<input class="reportbtn" type="button" value="신고" onclick="reportWrite(this.form)">
			</c:if>
		</div>
		<div class="empty-area"></div>
		<c:if test="${membervo != null}">
		    <div id="likeButtonContainer">
		        <c:choose>
		            <c:when test="${kpostvo.u_heart == '1' }">
		                <button type="button" id="hateButton" onclick="ihatethis()">&#x1F44E; 취소</button>
		            </c:when>
		            <c:otherwise>
		                <button type="button" id="likeButton" onclick="ilikethis()">&#x1F44D; 좋아요</button>
		            </c:otherwise>
		        </c:choose>
		    </div>
		</c:if>
	</div>
	<div class="empty-area"></div>
	</form>
	
	
	
	
	<c:if test="${membervo == null}">
		<div class="empty-area"></div>
	</c:if>	
	</div>
	
		<%-- 댓글 입력 --%>
	<c:if test="${membervo != null}">
	<div class="recomment">
		<form method="post">
			<fieldset>
			<div class="renick">
				<span>${membervo.u_nickname}(${membervo.u_lev})</span> 
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
		
		<%-- 댓글 출력 --%>
		<c:choose>
			<c:when test="${empty comment_list}">
				<div></div>
			</c:when>
			<c:otherwise>
			<div class="recomment2">
		<c:forEach var="k" items="${comment_list}">
			<div>
				<form method="post">
					<div class="renick">${k.u_nickname}(${k.u_lev})</div>
					<div class="recontent">
						<textarea rows="3" cols="40" name="content" readonly>${k.content}</textarea>
					</div>
					<div class="rebutton">${k.regdate.substring(2,19)}
						<c:choose>
							<c:when test="${membervo.u_idx == k.u_idx || adminUser != null}">							
								<input class="rewrite" type="button" value="삭제" onclick="rcommentDelete(this.form)">
							</c:when>
							<c:otherwise>
								<span></span>
							</c:otherwise>
						</c:choose>
						<c:if test="${membervo != null && membervo.u_idx != k.u_idx}">
							<input type="hidden" name="reported_id" value="${k.u_id}">
							<input class="rewrite" type="button" value="신고" onclick="reportWrite(this.form)">
						</c:if>
					</div>
					<input type="hidden" name = "comment_idx" value="${k.comment_idx}" >
					<input type="hidden" name = "path_post_idx" value="${k.path_post_idx}" >
				</form>
			</div>
		</c:forEach>
	</div>	
			</c:otherwise>
		</c:choose>
	
	
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
    	content: '<div class="custom-infowindow">' + (i + 1) + '. ' + title + '</div>',
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




















