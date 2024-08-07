<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<link rel="icon" href="/resources/ko_images/favicon.png">
<link rel="stylesheet" type="text/css"
	href="resources/ko_css/place_detail.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<!-- ajax -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

	$(document).on("click", ".heart-state", function(e) {
		let contentid = $(this).val();
		let userLogin = document.getElementById("userLogin").value;
		if (userLogin !== null && userLogin === "ok") {
			if ($(this).hasClass("wish-added")) {
				placeWishRemove(this, contentid);
			} else {
				placeWishadd(this, contentid);
			}
		} else {
			alert("찜하기를 하시려면 로그인을 해주세요.")
		}
	});

	// 장소 찜추가
	function placeWishadd(tag, contentid) {
		$.ajax({
			url : "detailPlaceWishAdd",
			type : "post",
			data : {
				contentid : contentid
			},
			dataType : "text",
			success : function(data) {
				$('.heart-count').text("좋아요  " + data);
				$(tag).addClass("wish-added");
				$(tag).text("찜해제하기");
				$('.heart_icon').empty();
				let content = '<img alt="" src="/resources/ko_images/heart_on3.png" width="40px;">';
				$('.heart_icon').append(content);
				$('.heart-state').css('background', 'pink');
				alert("좋아요를 눌렀습니다.")
			},
			error : function() {
				alert("실패");
			}
		});
	}

	// 장소 찜제거
	function placeWishRemove(tag, contentid) {
		$.ajax({
			url : "detailPlaceWishRemove",
			type : "post",
			data : {
				contentid : contentid,
			},
			dataType : "text",
			success : function(data) {
				$('.heart-count').text("좋아요  " + data);
				$(tag).text("찜추가하기");
				$(tag).removeClass("wish-added");
				$('.heart_icon').empty();
				let content = '<img alt="" src="/resources/ko_images/heart_off2.png" width="40px;">';
				$('.heart_icon').append(content);
				$('.heart-state').css('background', 'black');
				alert("좋아요를 취소하셨습니다.")
			},
			error : function() {
				alert("실패");
			}
		});
	}
	
</script>

<!-- 버튼 -->
<script type="text/javascript">
	
	function path_detail(path_post_idx) {
		location.href = "pathReviewDetail?path_post_idx=" + path_post_idx; 
	}
	
</script>

</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
	
	<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>

	<section class="detail_sec" style="width: 1300px; margin: 0 auto;">
		<div class="ko_detail">

			<div class="detail_main">
				<div class="main_image">
					<img alt="${itemVO.title}" src="${itemVO.firstimage}" width="100%"
						style="vertical-align: middle;">
				</div>

				<div class="main_content">
					<div class="title">
						<h1>${itemVO.title}</h1>
						<h2>${itemVO.homepage}</h2>
					</div>

					<c:if test="${itemVO.contenttypeid == 12}">
						<table class="content_table">
							<c:if test="${!empty itemVO.addr1}">
								<tr>
									<th>주소</th>
									<td>${itemVO.addr1}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.infocenter}">
								<tr>
									<th>번호</th>
									<td>${itemVO.infocenter}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.parking}">
								<tr>
									<th>주차</th>
									<td>${itemVO.parking}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.restdate}">
								<tr>
									<th>휴무</th>
									<td>${itemVO.restdate}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.usetime}">
								<tr>
									<th>시간</th>
									<td>${itemVO.usetime}</td>
								</tr>
							</c:if>
						</table>
					</c:if>
					<c:if test="${itemVO.contenttypeid == 15}">
						<table class="content_table">
							<c:if test="${!empty itemVO.eventplace}">
								<tr>
									<th>위치</th>
									<td>${itemVO.eventplace}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.eventstartdate}">
								<tr>
									<th>기간</th>
									<td>${itemVO.eventstartdate}~${itemVO.eventenddate}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.playtime}">
								<tr>
									<th>시간</th>
									<td>${itemVO.playtime}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.sponsor1}">
								<tr>
									<th>후원</th>
									<td>${itemVO.sponsor1}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.sponsor1tel}">
								<tr>
									<th>전화</th>
									<td>${itemVO.sponsor1tel}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.usetimefestival}">
								<tr>
									<th>비용</th>
									<td>${itemVO.usetimefestival}</td>
								</tr>
							</c:if>
						</table>
					</c:if>
					<c:if test="${itemVO.contenttypeid == 39}">
						<table class="content_table">
							<c:if test="${!empty itemVO.firstmenu}">
								<tr>
									<th>주메뉴</th>
									<td>${itemVO.firstmenu}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.treatmenu}">
								<tr>
									<th>전체메뉴</th>
									<td>${itemVO.treatmenu}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.addr1}">
								<tr>
									<th>주소</th>
									<td>${itemVO.addr1}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.infocenterfood}">
								<tr>
									<th>번호</th>
									<td>${itemVO.infocenterfood}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.opentimefood}">
								<tr>
									<th>시간</th>
									<td>${itemVO.opentimefood}</td>
								</tr>
							</c:if>
							<c:if test="${!empty itemVO.restdatefood}">
								<tr>
									<th>휴무</th>
									<td>${itemVO.restdatefood}</td>
								</tr>
							</c:if>
						</table>
					</c:if>
					<div class="wish_btn">
						<c:choose>
							<c:when test="${itemVO.uheart == 1}">
								<i class="heart_icon"><img alt="" src="/resources/ko_images/heart_on3.png" width="40px;"></i>
								<button type="button" class="heart-state wish-added"
									value="${itemVO.contentid}">
									찜해제하기
								</button>
							</c:when>
							<c:otherwise>
								<i class="heart_icon"><img alt="" src="/resources/ko_images/heart_off2.png" width="40px;"></i>
								<button type="button" class="heart-state"
									value="${itemVO.contentid}">
									찜추가하기
									</button>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="like_count">
						<img alt="" src="/resources/ko_images/like.png" width="50px">
						<div class="heart-count">좋아요  ${itemVO.heart}</div>
					</div>
					<input type="hidden" id="userLogin" value="${userLogin}">
				</div>
			</div>
			<hr>

			<div class="detail_content">
				<h2>상세설명</h2>
				<p>${itemVO.overview}</p>
			</div>

			<hr>

			<div class="detail_map">
				<h2>지도</h2>
				<!-- 지도를 표시할 div 입니다 -->
				<div id="map" style="width: 100%; height: 350px;"></div>
				<input type="hidden" id="mapx" value="${itemVO.mapx}"> 
				<input type="hidden" id="mapy" value="${itemVO.mapy}"> 
				<input type="hidden" id="title" value="${itemVO.title}">
			</div>

			<hr>

			<div class="detail_path">
				<h2>해당 장소 포함된 추천경로</h2>
					<c:choose>
						<c:when test="${empty path_list}">
							<h3 class="path_empty">처음으로 해당 장소를 포함해서 경로를 작성해보세요!</h3>
						</c:when>
						<c:otherwise>
							<div id="path_wrapper">
								<c:forEach var="k" items="${path_list}">
									<div class="path_box" onclick="path_detail(${k.path_post_idx})">
										<div class="path_image">
											<img alt="" src="/resources/rc_main_img/${k.firstimage}">
										</div>
										<div class="path_text">${k.path_post_title}</div>
									</div>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
			</div>

		</div>
	</section>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d514432bb9f7ee5d4a28d62d4ccba6b8">
	</script>
	<script>
		let mapx = document.getElementById('mapx').value;
		let mapy = document.getElementById('mapy').value;
		let title = document.getElementById('title').value;
		let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(mapy, mapx), // 지도의 중심좌표
			level : 6 // 지도의 확대 레벨
		};
		let map = new kakao.maps.Map(mapContainer, mapOption);
		// 마커 표시 위치 
		let markerPosition = new kakao.maps.LatLng(mapy, mapx);
		// 마커 생성
		let marker = new kakao.maps.Marker({
			position : markerPosition
		});
		// 마커가 지도 위에 표시되도록 설정
		marker.setMap(map);
		let iwContent = '<div style="padding:5px;">'
						+ title
						+ '<br><a href="https://map.kakao.com/link/to/' 
						+ title + ',' + mapy + ',' + mapx 
						+ '" style="color:blue" target="_blank">길찾기</a></div>', 
				iwPosition = new kakao.maps.LatLng(mapy, mapx);
		// 인포윈도우 생성
		let infowindow = new kakao.maps.InfoWindow({
			position : iwPosition,
			content : iwContent
		});
		// 마커 위에 인포윈도우 표시
		infowindow.open(map, marker);
	</script>
	
	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>