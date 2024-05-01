<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/ko_css/header.css">
</head>
<body>

	<nav class="navbar">
		<div class="nav_logo">
			<a href="/">로고<br>Five Guys<br>Travel Guide</a>
		</div>
		<ul class="nav_menu">
			<li><a href="">테마</a></li>
			<li><a href="">지역</a></li>
			<li><a href="">추천경로</a></li>
			<li><a href="">커뮤니티</a></li>
		</ul>
		<ul class="nav_icons">
			<c:choose>
				<c:when test="">
					<li><a href="">마이페이지</a></li>
					<li><a href="">로그아웃</a></li>
					<li><a href="">고객센터</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="">로그인</a></li>
					<li><a href="">회원가입</a></li>
					<li><a href="">고객센터</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>

</body>
</html>