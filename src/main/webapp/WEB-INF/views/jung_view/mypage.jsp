<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 슬라이드 이벤트 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<link href="resources/jung_css/mypage.css" rel="stylesheet">
<script type="text/javascript">
	function recommend_write(){
		location.href = "recommend_write_go";
	}
</script>

</head>
<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
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
				<button class="update_button" type="button" onclick="location.href='my_edit.do?u_idx=${memberUser.u_idx}'">회원 정보 수정</button>
			</div>
			<!-- 세로 밑줄 -->
			<div class="profile_box_right">
			</div>
		</div>
	</article>
	<article class="with_list_box swiper mySwiper">
		<div class="with_list_box_top">
			<p>찜한 여행지</p>
		</div>
		<c:choose>
			<c:when test="${empty wish_place}">
				<div class="empty">
					<p>찜한 여행지가 없습니다.</p>		
				</div>
			</c:when>
			<c:otherwise>
				<div class="with_list_box_bottom swiper-wrapper">
					<c:forEach var="k" items="${wish_place}">
						<div class="with_box swiper-slide">
							<a href="ko_detail.do?contentid=${k.contentid}&contenttypeid=${k.contenttypeid}">
								<img src="${k.firstimage}" class="with_img">
							</a>
							<p class="with_title">
								<c:choose>
									<c:when test="${k.contenttypeid == 12}">
										관광지
									</c:when>
									<c:when test="${k.contenttypeid == 15}">
										문화시설
									</c:when>
									<c:when test="${k.contenttypeid == 39}">
										음식점
									</c:when>
								</c:choose>
							</p>
							<p class="with_sub_title">
								<c:choose>
									<c:when test="${k.areacode == 1}">
										서울
									</c:when>
									<c:when test="${k.areacode == 2}">
										인천
									</c:when>
									<c:when test="${k.areacode == 3}">
										대전
									</c:when>
									<c:when test="${k.areacode == 4}">
										대구
									</c:when>
									<c:when test="${k.areacode == 5}">
										광주
									</c:when>
									<c:when test="${k.areacode == 6}">
										부산
									</c:when>
									<c:when test="${k.areacode == 7}">
										울산
									</c:when>
									<c:when test="${k.areacode == 8}">
										세종특별자치시
									</c:when>
									<c:when test="${k.areacode == 31}">
										경기도
									</c:when>
									<c:when test="${k.areacode == 32}">
										강원특별자치도
									</c:when>
									<c:when test="${k.areacode == 33}">
										충청북도
									</c:when>
									<c:when test="${k.areacode == 34}">
										충청남도
									</c:when>
									<c:when test="${k.areacode == 35}">
										경상북도
									</c:when>
									<c:when test="${k.areacode == 36}">
										경상남도
									</c:when>
									<c:when test="${k.areacode == 37}">
										전북특별자치도
									</c:when>
									<c:when test="${k.areacode == 38}">
										전라남도
									</c:when>
									<c:when test="${k.areacode == 39}">
										제주도
									</c:when>
								</c:choose>
							</p>
							<p class="with_sub_title">
								${k.place_title}
							</p>
						</div>
					</c:forEach>
				</div>
				<div class="swiper-button-prev"></div>
  				<div class="swiper-button-next"></div>	
			</c:otherwise>
		</c:choose>
	</article>
	<article class="with_list_box swiper mySwiper">
		<div class="with_list_box_top">
			<p>찜한 추천 경로</p>
		</div>
		<c:choose>
			<c:when test="${empty wish_path}">
				<div class="empty">
					<p>찜한 추천 경로가 없습니다.</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="with_list_box_bottom swiper-wrapper">
					<c:forEach var="k" items="${wish_path}">
						<div class="with_box swiper-slide">
							<c:choose>
								<c:when test="${k.img_status==0}">
									<a href="pathReviewDetail?path_post_idx=${k.path_post_idx}">
										<img src="resources/rc_main_img/${k.firstimage}" class="with_img">								
									</a>
								</c:when>
								<c:otherwise>
									<a href="pathReviewDetail?path_post_idx=${k.path_post_idx}">
										<img src="${k.firstimage}" class="with_img">
									</a>							
								</c:otherwise>
							</c:choose>
							<p class="with_title">
								<c:choose>
									<c:when test="${k.r_contenttypeid == 12}">
										관광지
									</c:when>
									<c:when test="${k.r_contenttypeid == 15}">
										문화시설
									</c:when>
									<c:when test="${k.r_contenttypeid == 39}">
										음식점
									</c:when>
									<c:when test="${k.r_contenttypeid == 99}">
										종합
									</c:when>
								</c:choose>
							</p>
							<p class="with_sub_title">
								${k.path_post_title}
							</p>
						</div>
					</c:forEach>
				</div>
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>
			</c:otherwise>
		</c:choose>
	</article>
	<article class="with_list_box swiper mySwiper">
		<div class="with_list_box_top">
			<p>내가 작성한 경로</p>
		</div>
		<c:choose>
			<c:when test="${empty wish_place}">
				<div class="empty">
					<p>내가 작성한 경로가 없습니다.</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="with_list_box_bottom swiper-wrapper">
					<c:forEach var="k" items="${my_recommend}">
						<div class="with_box swiper-slide">
							<c:choose>
								<c:when test="${k.img_status==0}">
									<a href="pathReviewDetail?path_post_idx=${k.path_post_idx}">
										<img src="resources/rc_main_img/${k.firstimage}" class="with_img">								
									</a>
								</c:when>
								<c:otherwise>
									<a href="pathReviewDetail?path_post_idx=${k.path_post_idx}">
										<img src="${k.firstimage}" class="with_img">
									</a>							
								</c:otherwise>
							</c:choose>
							<p class="with_title">
								<c:choose>
									<c:when test="${k.r_contenttypeid == 12}">
										관광지
									</c:when>
									<c:when test="${k.r_contenttypeid == 15}">
										문화시설
									</c:when>
									<c:when test="${k.r_contenttypeid == 39}">
										음식점
									</c:when>
									<c:when test="${k.r_contenttypeid == 99}">
										종합
									</c:when>
								</c:choose>
							</p>
							<p class="with_sub_title">
								<c:choose>
									<c:when test="${k.r_areacode == 1}">
										서울
									</c:when>
									<c:when test="${k.r_areacode == 2}">
										인천
									</c:when>
									<c:when test="${k.r_areacode == 3}">
										대전
									</c:when>
									<c:when test="${k.r_areacode == 4}">
										대구
									</c:when>
									<c:when test="${k.r_areacode == 5}">
										광주
									</c:when>
									<c:when test="${k.r_areacode == 6}">
										부산
									</c:when>
									<c:when test="${k.r_areacode == 7}">
										울산
									</c:when>
									<c:when test="${k.r_areacode == 8}">
										세종특별자치시
									</c:when>
									<c:when test="${k.r_areacode == 31}">
										경기도
									</c:when>
									<c:when test="${k.r_areacode == 32}">
										강원특별자치도
									</c:when>
									<c:when test="${k.r_areacode == 33}">
										충청북도
									</c:when>
									<c:when test="${k.r_areacode == 34}">
										충청남도
									</c:when>
									<c:when test="${k.r_areacode == 35}">
										경상북도
									</c:when>
									<c:when test="${k.r_areacode == 36}">
										경상남도
									</c:when>
									<c:when test="${k.r_areacode == 37}">
										전북특별자치도
									</c:when>
									<c:when test="${k.r_areacode == 38}">
										전라남도
									</c:when>
									<c:when test="${k.r_areacode == 39}">
										제주도
									</c:when>
								</c:choose>
							</p>
							<p class="with_sub_title">
								${k.path_post_title}
							</p>
						</div>
					</c:forEach>
				</div>
				<div class="swiper-button-prev"></div>
 				<div class="swiper-button-next"></div>
			</c:otherwise>
		</c:choose>
		<button type="button" onclick="recommend_write()">추천 경로 작성하기</button>
	</article>
	<article class="comu_main">
		<p class="comu_header">내 자유 게시판 글</p>
		<div class="comu_menu">
			<p>작성자(닉네임)</p>
			<p>제목</p>
			<p>조회수</p>
			<p>작성일자</p>
		</div>
		<c:choose>
			<c:when test="${empty my_board}">
				<p class="write_empty">작성한 자유 게시글이 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${my_board}">
					<div class="write_main">
						<p>${k.u_nickname}</p>
						<p>
							<a href="boardDetail?board_idx=${k.board_idx}">${k.board_title}</a>
						</p>
						<p>${k.hit}</p>
						<p>${k.regdate.substring(0,10)}</p>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</article>
	<article class="comu_main">
		<p class="comu_header">내 신고내역</p>
		<div class="comu_menu">
			<p>작성자(닉네임)</p>
			<p>제목</p>
			<p>작성일자</p>
			<p>답변 여부</p>
		</div>
		<c:choose>
			<c:when test="${empty my_report}">
				<p class="write_empty">문의한 신고 내역이 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${my_report}">
					<div class="write_main">
						<p>${k.u_id}</p>
						<p>
							<a href="reportDetail?report_idx=${k.report_idx}">${k.report_title}</a>
						</p>
						<p>${k.regdate.substring(0,10)}</p>
						<p>
							<c:choose>
								<c:when test="${k.report_state == 0}">
									신고 처리중
								</c:when>
								<c:when test="${k.report_state == 1}">
									처리 완료
								</c:when>
							</c:choose>
						</p>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</article>
</section>
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function () {
	    let mySwiper = new Swiper(".mySwiper", {
	    	slidesPerView: 4, // 한 번에 표시할 슬라이드 수
	    	spaceBetween: 10, // 슬라이드 간의 간격  
	        loop: true, // 슬라이드 루프(무한 회전) 활성화
	        navigation: {
	            nextEl: ".swiper-button-next",
	            prevEl: ".swiper-button-prev"
	        },
	        watchOverflow: true // 슬라이드가 화면을 넘어갈 때의 처리 설정
	    });
	});
</script>
</body>
</html>