<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/jung_css/recommend_write.css">
</head>
<body>
	<div>
		<div id="map"></div>
	</div>
	<div id="wish_box">
		<h2 id="wish_title">위시리스트</h2>
		<c:forEach var="k" items="${marker_list}">
			<div class="wishs">
				<input class="chk_box" type="checkbox" name="chk" value="0">
				<div><img src="${k.firstimage}"></div>
				<p>${k.place_title}</p>
				<input type="hidden" name="place_title" value="${k.place_title}">
				<input type="hidden" name="map_x" value="${k.map_x}">
				<input type="hidden" name="map_y" value="${k.map_y}">
			</div>
		</c:forEach>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c186c802b1e519c6f748b4481b8a4b53"></script>
<script>
//여기서부터 map 생성
let mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(35.207766, 128.569655), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 생성된 마커들 모음
let markers = [];

// 마커 이미지의 이미지 주소입니다.
function marker(position) {
	let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		//map.setCenter(position.latlng);
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
	    markers.push(marker)
}
function marker_del(position) {
	console.log(position)
	for (let i = 0; i < markers.length; i++) {
		console.log(markers[i].getPosition().getLat())
		console.log(position.latlng.getLat())
		console.log(markers[i].getPosition().getLng())
		console.log(position.latlng.getLng())
		console.log(markers[i].getPosition().getLat() == position.latlng.getLat() && markers[i].getPosition().getLng() == position.latlng.getLng())
		if (markers[i].getPosition().getLat() == position.latlng.getLat() && markers[i].getPosition().getLng() == position.latlng.getLng()) {
			markers[i].setMap(null);
		}
	}
}
// 변화시 확인
$(".chk_box").change(function() {
		let x = $(this).next().next().next().next().val()
		let y = $(this).next().next().next().next().next().val()
        let position = 
            {
        		title: $(this).next().next().next().val(), 
                latlng: new kakao.maps.LatLng(y, x)
            }
	if ($(this).prop("checked")) {
        marker(position)
    } else {
        marker_del(position)
    }
})
</script>
</body>
</html>