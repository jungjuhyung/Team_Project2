<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<!-- include libraries(jQuery, bootstrap) -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<style type="text/css">
#world{
	text-align: center;
	width: 1200px;
	margin: auto;
	margin-top : 160px;
}
.d_img{
	width: 100%;
	height: 200px;
	border: 1px solid black;
	margin-top: 20px;	

}
.in_div{
	width : 33.3333%;
	height : 100%;
	border: 1px solid black;
	float: left;
	
}
#summer{
	margin-top: 30px;
}
#empty-area{
	height: 300px;
}
#reviewTitle {
	height: 100px;
	border: 1px solid black;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div id="world">
		<div id="reviewTitle">${kpostvo.path_post_title}</div>
		<div id="map" style="width: 100%; height: 500px;"></div>

		
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
	<script>
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


var mapy1 = ${mapy1};  // 위도
var mapx1 = ${mapx1}; // 경도
var mapy2 = ${mapy2};  // 위도
var mapx2 = ${mapx2}; // 경도
var mapy3 = ${mapy3};  // 위도
var mapx3 = ${mapx3}; // 경도


var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
mapOption = { 
    center: new kakao.maps.LatLng(mapy1, mapx1), // 지도의 중심좌표
    level: 4, // 지도의 확대 레벨
    draggable: false, // 지도를 생성할때 지도 이동을 막으려면 draggable: false 옵션을 추가하세요
    scrollwheel: false // 지도를 생성할때 지도 확대/축소를 막으려면 scrollwheel: false 옵션을 추가하세요    
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var positions = [];
<%-- 
var positions = [];
var linePath = [];

<%
for (int i = 0; i < tourtestvoList.size(); i++) {
    TourtestVO tourtestVO = tourtestvoList.get(i);
%>
    var mapy<%= (i + 1) %> = ${tourtestVO.getMapy()};
    var mapx<%= (i + 1) %> = ${tourtestVO.getMapx()};

    positions.push({
        title: '',
        latlng: new kakao.maps.LatLng(mapy<%= (i + 1) %>, mapx<%= (i + 1) %>)
    });

    linePath.push(new kakao.maps.LatLng(mapy<%= (i + 1) %>, mapx<%= (i + 1) %>));
<%
}
%>

<%@ page import="java.util.List" %>
<%@ page import="com.ict.traver.kim.dao.TourtestVO" %>
--%>
//마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
{
    title: '', 
    latlng: new kakao.maps.LatLng(mapy1, mapx1)
},
{
    title: '', 
    latlng: new kakao.maps.LatLng(mapy2, mapx2)
},
{
    title: '', 
    latlng: new kakao.maps.LatLng(mapy3, mapx3)
},
{
    title: '',
    latlng: new kakao.maps.LatLng()
}
];

//선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
var linePath = [
    new kakao.maps.LatLng(mapy1, mapx1),
    new kakao.maps.LatLng(mapy2, mapx2),
    new kakao.maps.LatLng(mapy3, mapx3)
];

// 지도에 표시할 선을 생성합니다
var polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 2, // 선의 두께 입니다
    strokeColor: '#FFAE00', // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid' // 선의 스타일입니다
});

// 지도에 선을 표시합니다 
polyline.setMap(map);  

//마커 이미지의 이미지 주소입니다
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

for (var i = 0; i < positions.length; i ++) {

// 마커 이미지의 이미지 크기 입니다
var imageSize = new kakao.maps.Size(24, 35); 

// 마커 이미지를 생성합니다    
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    map: map, // 마커를 표시할 지도
    position: positions[i].latlng, // 마커를 표시할 위치
    title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
    image : markerImage // 마커 이미지 
});
}
</script>

</body>
</html>