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
</script>
<style type="text/css">
.dot {overflow:hidden;float:left;width:12px;height:12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');}    
.dotOverlay {position:relative;bottom:10px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;font-size:12px;padding:5px;background:#fff;}
.dotOverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}    
.number {font-weight:bold;color:#ee6152;}
.dotOverlay:after {content:'';position:absolute;margin-left:-6px;left:50%;bottom:-8px;width:11px;height:8px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png')}
.distanceInfo {position:relative;top:5px;left:5px;list-style:none;margin:0;}
.distanceInfo .label {display:inline-block;width:50px;}
.distanceInfo:after {content:none;}
</style>
</head>
<body>
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
			<c:choose>
				<c:when test="${empty imglist}">
				<div class="in_div">이미지가 없음</div>
				</c:when>
				<c:otherwise>
				<c:forEach var="k" items="${imglist}" varStatus="vs">
					<div class="in_div">${k.img}</div>
				</c:forEach>
				</c:otherwise>
			</c:choose>		
		</div>
		<div class="d_img">
			<c:choose>
				<c:when test="${empty imglist}">
				<div class="in_div">이미지가 없음</div>
				</c:when>
				<c:otherwise>
				<c:forEach var="k" items="${imglist}" varStatus="vs">
					<div class="in_div">${k.img}</div>
				</c:forEach>
				</c:otherwise>
			</c:choose>		
		</div>
		<div class="d_img">
			<c:choose>
				<c:when test="${empty imglist}">
				<div class="in_div">이미지가 없음</div>
				</c:when>
				<c:otherwise>
				<c:forEach var="k" items="${imglist}" varStatus="vs">
					<div class="in_div">${k.img}</div>
				</c:forEach>
				</c:otherwise>
			</c:choose>		
		</div>
		
		<div id="summer">
			<textarea rows="10" cols="60" id="summernote" name="content">${kpostvo.path_post_content}</textarea>
		</div>
		<div>
			<button type="button">수정</button>
			<button type="button">삭제</button>
		</div>
		<button type="button">추천</button>
		<button type="button">비추천</button>
		<button type="button">스크랩</button>
		
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
	<c:if test="${membervo.u_grade != null}">
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
    console.log(value);
});

for (let i = 0; i < mapyList.length; i++) {
    let mapy = mapyList[i];
    let mapx = mapxList[i];
    let title = marktitle[i];
    let latlng = new kakao.maps.LatLng(mapy, mapx); // 올바른 kakao.maps.LatLng 객체 생성

 // 좌표를 콘솔에 출력
    console.log("좌표 추가:", latlng);
    
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

//마커 간의 거리를 저장할 변수 초기화
var totalDistance = 0;

// 마커 간의 거리를 계산하기 위해 반복문 사용
for (let i = 0; i < linePath.length - 1; i++) {
    // 현재 좌표와 다음 좌표 간의 거리 계산하여 더하기
    let distance = kakao.maps.geometry.distance(linePath[i], linePath[i + 1]);
    totalDistance += distance;
}

// 거리 값을 HTML 컨텐츠 생성 함수에 전달하여 거리 정보 표시
var distanceContent = getTimeHTML(totalDistance);
console.log(distanceContent); // 콘솔에 거리 정보 출력

// HTML Content를 만들어 리턴하는 함수입니다
function getTimeHTML(distance) {
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

</script>
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>




















