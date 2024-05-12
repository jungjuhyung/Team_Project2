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
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
	<div id="world">
		<div id="reviewTitle">${kpostvo.path_post_title}</div>
		<div id="map" style="width: 100%; height: 500px;"></div>
	<div id="review_img1">
		<c:choose>
			<c:when test="${empty imglist}">
			</c:when>
			<c:otherwise>
			<c:forEach var="k" items="${imglist}" varStatus="vs">
				<div>${k.imglist}</div>
			</c:forEach>
			</c:otherwise>
		</c:choose>		
	</div>
		<div class="d_img"> 
			<div class="in_div"></div> 
			<div class="in_div"></div> 
			<div class="in_div"></div> 
		</div>
		<div class="d_img"> 
			<div class="in_div"></div> 
			<div class="in_div"></div> 
			<div class="in_div"></div> 
		</div>
		<div class="d_img"> 
			<div class="in_div"></div> 
			<div class="in_div"></div> 
			<div class="in_div"></div> 
		</div>
		<div id="summer">
			<textarea rows="10" cols="60" id="summernote" name="content">${kpostvo.path_post_content}</textarea>
		</div>
	<button type="button">추천</button>
	<button type="button">비추천</button>
	<button type="button">스크랩</button>
	
	<div style="padding: 10px; width: 580px; margin: 0 auto">
		<form method="post">
			<fieldset>
				<span>닉네임 : </span> 
				<span>내용 : <textarea rows="3" cols="40" name="content"></textarea>
				<input style="float: right;" type="button" value="저장" onclick="commentInsert(this.form)">
				</span>
				<!-- 댓글 저장시 어떤 원글의 댓글인지 저장해야 한다. -->
				<input type="hidden" name = "board_idx" value="" >
			</fieldset>
		</form>
	</div>
	
	<%-- 댓글 출력 --%>
	<div style="display: table; margin: 0 auto;">
		<c:forEach var="k" items="${comment_list}">
			<div style="border: 1px solid #cc00cc; width: 580px; margin: 5px; padding: 5px;" >
				<form method="post">
					<p>이름 : ${k.u_nickname}</p>
					<p>내용 : <pre>${k.content}</pre></p>
					<p>날짜 : ${k.regdate.substring(0,10)}
					<!-- 실제은 로그인 성공 && 글쓴 사람만 삭제할 수 있어야 한다. -->
					<input style="float: right;" type="button" value="댓글삭제" onclick="commentDelete(this.form)">
					<input type="hidden" name = "comment_idx" value="${k.comment_idx}" >
					<input type="hidden" name = "board_idx" value="${k.board_idx}" >
					</p>
					
				</form>
			</div>
		</c:forEach>
	</div>	
	<div id="empty-area">
	</div>
	</div>
	<script>
	console.log(${mapyList.size()})
		// 메인화면 페이지 로드 함수
		$(document).ready(function() {
			$('#summernote').summernote({
				placeholder : '내용을 작성하세요',
				height : 400,
				maxHeight : 400
			});
			$('#summernote').summernote('disable');
		});
	</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3f599c1d6971197a01e6600cd397224a"></script>
<script>
var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var mapOption = {
    center: new kakao.maps.LatLng(${mapy1}, ${mapx1}), // 지도의 중심 좌표
    level: 6 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var positions = [];
var linePath = [];
var mapyList = <%= request.getAttribute("mapyList") %>;
var mapxList = <%= request.getAttribute("mapxList") %>;

for (let i = 0; i < mapyList.length; i++) {
    let mapy = mapyList[i];
    let mapx = mapxList[i];
    let position = new kakao.maps.LatLng(mapy, mapx);
    
    positions.push({
        title: '',
        latlng: position
    });

    linePath.push(position);

    // 마커 생성
    var marker = new kakao.maps.Marker({
        map: map,
        position: position
    });

    // 인포윈도우 생성
    var infowindow = new kakao.maps.InfoWindow({
        content: '마커 ' + (i + 1) + ' 위치',
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






//HTML Content를 만들어 리턴하는 함수입니다
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




















