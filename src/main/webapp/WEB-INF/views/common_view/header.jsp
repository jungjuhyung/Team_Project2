<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/common_css/header.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<script type="text/javascript">
	function main_go() {
		location.href = "/";
	}
</script>
</head>
<body>
	
	<section class="header" style="height: 180px;">
		<nav class="ko_nav">
			<div class="ko_logo" onclick="main_go()">
				<img alt="" src="resources/ko_images/logo.png">
			</div>
			<ul class="ko_menu">
				<li class="main">
					<a href="themaCategory">여행검색</a>
				</li>
				<li class="main"> 
					<a href="pathCategory">추천경로</a>
				</li>
				<li class="main">
					<a href="boardList">커뮤니티</a>
				</li>
			</ul>
			<ul class="ko_util">
				<c:choose>
					<c:when test="${adminUser != null }">
						<li class="btn"><a href="logout">로그아웃</a></li>
						<li class="btn"><a href="#">관리페이지</a></li>
					</c:when>
					<c:when test="${empty memberUser}">						
						<li class="btn"><a href="login_go.do">로그인</a></li>
						<li class="btn"><a href="agree_go.do">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="btn"><a href="logout">로그아웃</a></li>
						<li class="btn"><a href="mypage">마이페이지</a></li>
					</c:otherwise>
				</c:choose>
				<li class="btn"><a href="getReportgo">고객센터</a></li>
			</ul>
		</nav>
	</section>	

</body>
</html>