<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<div id="map" style="width:100%;height:350px;"></div>
	<div>
	</div>
		<h2>위시리스트</h2>
		<div>
			<input type="checkbox" name="chk" value="0">
			<div>이미지 칸1</div>
			<p>타이틀 칸1</p>
			<input type="hidden" name="mapx" value="33.450936">
			<input type="hidden" name="mapy" value="126.569477">
		</div>
		<div>
			<input type="checkbox" name="chk" value="0">
			<div>이미지 칸2</div>
			<p>타이틀 칸2</p>
			<input type="hidden" name="mapx" value="33.450705">
			<input type="hidden" name="mapy" value="126.570677">
		</div>
		<div>
			<input type="checkbox" name="chk" value="0">
			<div>이미지 칸3</div>
			<p>타이틀 칸3</p>
			<input type="hidden" name="mapx" value="33.450879">
			<input type="hidden" name="mapy" value="126.569940">
		</div>
		<div>
			<input type="checkbox" name="chk" value="0">
			<div>이미지 칸</div>
			<p>타이틀 칸</p>
			<input type="hidden" name="mapx" value="33.451393">
			<input type="hidden" name="mapy" value="126.570738">
		</div>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 사용하세요"></script>
<script>

// 여기서부터 map 생성
let mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
// map 생성 끝

// 마커를 표시할 위치와 title 객체 배열입니다
let positions = [
    {
        title: '카카오', 
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    {
        title: '생태연못', 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },
    {
        title: '텃밭', 
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
    },
    {
        title: '근린공원',
        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
    }
];

// 마커 이미지의 이미지 주소입니다.
function () {
	let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

	for (let i = 0; i < positions.length; i ++) {
	    // 마커 이미지의 이미지 크기 입니다.
	    let imageSize = new kakao.maps.Size(24, 35);
	    
	    // 마커 이미지를 생성합니다.
	    let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	    
	    // 마커를 생성합니다.
	    let marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng, // 마커를 표시할 위치
	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다.
	        image : markerImage // 마커 이미지
	    });
	}
}
</script>
</body>
</html>