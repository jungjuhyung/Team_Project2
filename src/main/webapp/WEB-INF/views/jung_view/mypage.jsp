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
<script type="text/javascript">
	function recommend_write(){
		location.href = "recommend_write_go";
	}
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
<section id="mypage">
	<article id="mypage_article">
		<div class="my_profile_box">
			<div class="profile_image">
				<img class="pro_image" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA4MTlfMTgy%2FMDAxNjkyMzk0OTY2NTQx.kcqRj3Tf9RD5663NiKYV95dPN9YlyRfKPs0Re8S12Xcg.WbcFWteQCwRqC61R4PiAVZzD3XOfBtyDM5UvVwANwpgg.PNG.jjungaang%2Fpfp%25A3%25DFultraviolet%25A3%25DFuzubaong.png&type=ff332_332">
			</div>
			<div class="profile_box_left">
				<p class="profile_name">이름 : ${memberUser.u_name}</p>
				<p class="profile_nickname">닉네임 : ${memberUser.u_nickname}</p>
				<p class="profile_email">이메일 : ${memberUser.u_email}</p>
				<button class="update_button" type="button">회원 정보 수정</button>
			</div>
			<!-- 세로 밑줄 -->
			<div class="profile_box_right">
			</div>
		</div>
	</article>
	<article>
		<p>찜한 여행지</p>
		<c:choose>
			<c:when test="${empty wish_place}">
				<p>찜한 여행지가 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${wish_place}">
					<div>
						<div>
							<img src="">
						</div>
						<p></p>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</article>
	<article>
		<p>찜한 추천 경로</p>
		<c:choose>
			<c:when test="${empty wish_path}">
				<p>찜한 추천 경로가 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${wish_path}">
					<div>
						<div>
							<img src="">
						</div>
						<p>서울 맛집 투어</p>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</article>
	<article>
		<p>내가 만든 여행 코스</p>
		<c:choose>
			<c:when test="${empty my_recommend}">
				<p>내가 만든 여행 코스가 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${my_recommend}">
					<div>
						<div>
							<img src="">
						</div>
						<p>내가 좋아하는 음식 투어</p>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<button type="button" onclick="recommend_write()">추천 경로 작성하기</button>
	</article>
	<article>
		<p>내 게시물</p>
		<%-- <div>
			<p>자유 게시판</p>
			<div class="free">
				<span>작성자(닉네임)</span>
				<span>제목</span>
				<span>댓글수</span>
				<span>작성일자</span>
			</div>
			<c:choose>
				<c:when test="${empty }">
					<p>작성한 자유 게시글이 없습니다.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="" items="">
						<div>
							<span>라면먹는중</span>
							<span>test title</span>
							<span>6</span>
							<span>2024-05-12</span>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div> --%>
		<%-- <div>
			<p>신고 게시판</p>
			<div class="re">
				<span>작성자(닉네임)</span>
				<span>제목</span>
				<span>작성일자</span>
				<span>답변 여부</span>
			</div>
			<c:choose>
				<c:when test="${empty }">
					<p>작성한 신고 게시글이 없습니다.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="" items="">
						<div>
							<span>라면먹는중</span>
							<span>test title</span>
							<span>6</span>
							<span>2024-05-12</span>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div> --%>
	</article>
</section>
</body>
</html>