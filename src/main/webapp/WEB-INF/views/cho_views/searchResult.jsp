<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/cho_css/category.css">
<script src="resources/cho_js/search_Result.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
	<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
	<section class="section" >
		<div class="areaSearchForm">
			<select id="searchType" class = "searchSelect">
				<option value="1">장소</option>
				<option value="2">경로</option>
			</select>
			<select id="areaCodes" class = "searchSelect">
				<option value="999">지역</option>
				<option value="999">전체</option>
				<option value="1">서울</option>
				<option value="6">부산</option>
				<option value="4">대구</option>
				<option value="2">인천</option>
				<option value="5">광주</option>
				<option value="3">대전</option>
				<option value="7">울산</option>
				<option value="8">세종</option>
				<option value="31">경기</option>
				<option value="32">강원</option>
				<option value="33">충북</option>
				<option value="34">충남</option>
				<option value="35">경북</option>
				<option value="36">경남</option>
				<option value="37">전북</option>
				<option value="38">전남</option>
				<option value="39">제주</option>
			</select> 
			<select id="sigunguCode" class = "searchSelect">
			</select> 
			<select id="contentTypes" class = "searchSelect">
				<option value="999">테마</option>
				<option value="999">전체</option>
				<option value="12">관광지</option>
				<option value="15">행사/공연/축제</option>
				<option value="39">음식점</option>
			</select>
			
			<input type="text" class ="searchTitle" name = "title">
			<input type="button" value="검색" class = "SearchButton">
		</div>
		<hr>
		<div id = "result">
			<div id = "resultOption">
				<div id ="resultCount"></div>
				<!-- 보기 옵션 -->
				<div>
					<select id = "orderOption">
						<option value = "like">좋아요 순</option>
						<option value = "t_asc">제목(ㄱ->ㅎ)</option>
						<option value = "t_desc">제목(ㅎ->ㄱ)</option>
					</select>
					<select id = "viewLimit">
						<option value = "10">10개 보기</option>
						<option value = "20" selected>20개 보기</option>
						<option value = "30">30개 보기</option>
						<option value = "50">50개 보기</option>
						<option value = "100">100개 보기</option>
					</select>
				</div>
			</div>
			<!-- 장소 표시 -->
			<div id = "place_wrapper"></div>
			<!-- 페이징 표시 -->
			<div class="board-list-paging"></div>
			<!-- 페이지 이동 -->
			<div class="pageMoveForm">
				<!-- <input type="text" class ="pageMove"> -->
				<input type="button" value="이동" class = "pageMoveButton">
			</div>
			
		</div>
	</section>
	<input type="hidden" id="userLogin" value="${userLogin}">
</body>
</html>