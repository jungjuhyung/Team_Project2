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
		<div class="with_list_box">
			<div class="with_list_box_top">
				<p>찜한 여행지</p>
			</div>
			<c:choose>
				<c:when test="${empty wish_place}">
					<p>찜한 여행지가 없습니다.</p>		
				</c:when>
				<c:otherwise>
					<div class="with_list_box_bottom">
					<c:forEach var="k" items="${wish_place}">
							<div class="with_box">
								<img src="data:image/jpeg;base64,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" class="wish_img">
								<p class="with_title">여행지</p>
								<p class="with_sub_title">흐어어</p>
							</div>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
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