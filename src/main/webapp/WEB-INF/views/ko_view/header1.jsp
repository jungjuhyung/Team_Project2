<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/ko_css/header.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<script type="text/javascript">
	function main_go() {
		location.href = "main_page.do?areacode=1&contenttypeid=12";
	}
</script>
</head>
<body>
		
		<nav class="ko_nav">
			<div class="ko_logo" onclick="main_go()">
				<img alt="" src="resources/ko_images/logo.png">
			</div>
			<ul class="ko_menu">
				<li class="main">
					<a href="areaCategory">장소검색</a>
				</li>
				<li class="main"> 
					<a href="">추천경로</a>
				</li>
				<li class="main">
					<a href="">커뮤니티</a>
				</li>
			</ul>
			<ul class="ko_util">
				<li class="btn"><a href="">로그인</a></li>
				<li class="btn"><a href="">회원가입</a></li>
				<li class="btn"><a href="">고객센터</a></li>
			</ul>
		</nav>

</body>
</html>