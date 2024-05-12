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
<link href="resources/jung_css/mypage.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section id="mypage">
	<article id="mypage_article">
		<div class="my_profile_box">
			<div class="profile_image">
				<img class="pro_image" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA4MTlfMTgy%2FMDAxNjkyMzk0OTY2NTQx.kcqRj3Tf9RD5663NiKYV95dPN9YlyRfKPs0Re8S12Xcg.WbcFWteQCwRqC61R4PiAVZzD3XOfBtyDM5UvVwANwpgg.PNG.jjungaang%2Fpfp%25A3%25DFultraviolet%25A3%25DFuzubaong.png&type=ff332_332">
			</div>
			<div class="profile_box_left">
				<p class="profile_name">이름 : 정주형</p>
				<p class="profile_nickname">닉네임 : 라면먹는중</p>
				<p class="profile_email">이메일 : wngud2582@naver.com</p>
				<button class="update_button" type="button">회원 정보 수정</button>
			</div>
			<div class="profile_box_right">
			</div>
		</div>
	</article>
	<article>
		<p>찜한 여행지</p>
		<div>
			<div>
				<img src="">
			</div>
			<p></p>
		</div>
	</article>
	<article>
		<p>찜한 추천 경로</p>
		<div>
			<div>
				<img src="">
			</div>
			<p>경복궁</p>
		</div>
	</article>
	<article>
		<p>내가 만든 여행 코스</p>
		<div>
			<div>
				<img src="">
			</div>
			<p>내가 좋아하는 음식 투어</p>
		</div>
	</article>
	<article>
		<p>내 게시물</p>
		<div>
			<p>자유 게시판</p>
			<div class="free">
				<span>작성자(닉네임)</span>
				<span>제목</span>
				<span>댓글수</span>
				<span>작성일자</span>
			</div>
			<div>
				<span>라면먹는중</span>
				<span>test title</span>
				<span>6</span>
				<span>2024-05-12</span>
			</div>
		</div>
		<div>
			<p>QnA 게시판</p>
			<div class="qna">
				<span>작성자(닉네임)</span>
				<span>제목</span>
				<span>작성일자</span>
				<span>답변 여부</span>
			</div>
			<div>
				<span>라면먹는중</span>
				<span>test title</span>
				<span>2024-05-12</span>
				<span>답변 완료</span>
			</div>
		</div>
	</article>
</section>
</body>
</html>