<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/cho_css/category.css">
<meta charset="UTF-8">
<title>테마별 장소 추천</title>
</head>
<body>
	<section class="section" style="background-color: rgba(0,0,0,0.3)">
		<div class="swipe-Wrapper">
			<div class="swipe-arealist">
				<div id="area1" class="swipeAreaList" data-areacode="1"><img src="" ><span>서울</span></div>
				<div id="area2" class="swipeAreaList" data-areacode="2"><img src="" ><span>인천</span></div>
				<div id="area3" class="swipeAreaList" data-areacode="3"><img src="" ><span>대전</span></div>
				<div id="area4" class="swipeAreaList" data-areacode="4"><img src="" ><span>대구</span></div>
				<div id="area5" class="swipeAreaList" data-areacode="5"><img src="" ><span>광주</span></div>
				<div id="area6" class="swipeAreaList" data-areacode="6"><img src="" ><span>부산</span></div>
				<div id="area7" class="swipeAreaList" data-areacode="7"><img src="" ><span>울산</span></div>
				<div id="area8" class="swipeAreaList" data-areacode="8"><img src="" ><span>세종</span></div>
				<div id="area31" class="swipeAreaList" data-areacode="31"><img src="" ><span>경기</span></div>
				<div id="area32" class="swipeAreaList" data-areacode="32"><img src="" ><span>강원</span></div>
				<div id="area33" class="swipeAreaList" data-areacode="33"><img src="" ><span>충북</span></div>
				<div id="area34" class="swipeAreaList" data-areacode="34"><img src="" ><span>충남</span></div>
				<div id="area35" class="swipeAreaList" data-areacode="35"><img src="" ><span>경북</span></div>
				<div id="area36" class="swipeAreaList" data-areacode="36"><img src="" ><span>경남</span></div>
				<div id="area37" class="swipeAreaList" data-areacode="37"><img src="" ><span>전북</span></div>
				<div id="area38" class="swipeAreaList" data-areacode="38"><img src="" ><span>전남</span></div>
				<div id="area39" class="swipeAreaList" data-areacode="39"><img src="" ><span>제주</span></div>
			</div>
			
			<div class="swipe-Main">
				<div id="areaName">지역</div>
				<div class="swipe-MainContent-Wrapper">
					<div class="swipe-MainContent"></div>
					<div class="swipe-MainContent"></div>
					<div class="swipe-MainContent"></div>
				</div>
				
			</div>
			
		 </div>
		 
		 <div id = "hot-place-wrapper">
		 	<div id="thema-title">우리 지역 핫플레이스</div>
		 	<div class= "thema-box">
		 		<div class="tourist" >{지역} 추천 관광지</div>
		 		<div class="tourist-place"></div>
		 	</div>
		 	<div class= "thema-box">
		 		<div class="party">{지역} 추천 축제</div>
		 		<div class=""></div>
		 	</div>
		 	
		 	<div class= "thema-box">
		 		<div class="restaurant">{지역} 추천 맛집</div>
		 		<div class=""></div>
		 	</div>
		 </div>
		 
		 
	</section>
</body>
</html>