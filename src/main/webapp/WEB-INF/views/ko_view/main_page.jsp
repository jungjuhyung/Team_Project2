<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Five Guys Travel Guide</title>
<link rel="icon" href="/resources/ko_images/favicon.png">
<link rel="stylesheet" type="text/css"
	href="resources/ko_css/main_page.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
	function detail_go(path_post_idx) {
		location.href = "pathReviewDetail?path_post_idx=" + path_post_idx;
	}
	
	function area_wish(areacode) {
		console.log(areacode);
		$.ajax({
			url : "main_ajax_area.do",
			data : "r_areacode=" + areacode,
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$("#area_wrapper").empty();
				let content = "";
				$.each(data, function(index, value) {
					
					content += '<div class="path_box" onclick="detail_go(' 
								+ value.path_post_idx + ')">' 
								+ '<div class="path_image">' 
								+ '<img alt="" src="/resources/rc_main_img/' 
								+ value.firstimage + '"></div>';
								
					if (value.path_post_title.length >= 12) {
						content += '<div class="path_text">' 
									+ value.path_post_title.substring(0,12) 
									+ '...</div></div>';
					}else {
						content += '<div class="path_text">' 
									+ value.path_post_title 
									+ '</div></div>';
					}
				})
				$("#area_wrapper").append(content);
			},
			error : function() {
				alert("실패");
			}
		});
	}
	
	function tema_wish(contenttypeid) {
		console.log(contenttypeid);
		$.ajax({
			url : "main_ajax_tema.do",
			data : "r_contenttypeid=" + contenttypeid,
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$("#tema_wrapper").empty();
				let content = "";
				$.each(data, function(index, value) {
					content += '<div class="path_box" onclick="detail_go(' 
								+ value.path_post_idx + ')">' 
								+ '<div class="path_image">' 
								+ '<img alt="" src="/resources/rc_main_img/' 
								+ value.firstimage + '"></div>';
						if (value.path_post_title.length >= 12) {
							content += '<div class="path_text">' 
										+ value.path_post_title.substring(0,12) 
										+ '...</div></div>';
						}else {
							content += '<div class="path_text">' 
										+ value.path_post_title 
										+ '</div></div>';
						}
				})
				$("#tema_wrapper").append(content);
			},
			error : function() {
				alert("실패");
			}
		});
	}
</script>

<script>
	
	//	팝업 쿠키 체크
	$(function() {
		popupcookie = document.cookie;
		if (popupcookie.indexOf("popup=done") < 0) {
			document.getElementById('popup').style.display = "block";
		}else {
			document.getElementById('popup').style.display = "none";
		}
	})
	
	//	쿠키 설정 함수
	function setCookie(name, value, expiredays) {
		let today = new Date();
		today.setDate(today.getDate() + expiredays);
		document.cookie = name + "=" + escape(value) + "; path=/; expires=" + today;
	}
	
	//	닫기
	function close_popup() {
		document.getElementById('popup').style.display = "none";
	}
	
	//	하루동안보지않기 => expiredays 을 1 로 설정
	function close_today() {
		setCookie("popup", "done", 1);	
		document.getElementById('popup').style.display = "none";
	}
	
</script>

</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

	<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>

	<section style="margin: 0 auto; width: 1300px; min-height: 1900px;">
		
		<!-- 팝업창 -->
	    <div id="popup">
	        <div class="popup_img">
	        	<img alt="" src="/resources/popup_img/${popvo.f_name}">
	        </div>         
	        <div class="popup_btn">
	        	<button type="button" onclick="close_today()">오늘하루보지않기</button>
	        	<button type="button" onclick="close_popup()">닫기</button>
	        </div>
	    </div> 
    
		<div class="img_wrapper">
			<div class="img_slider">
				<div class="slide_box">
					<c:forEach var="k" items="${text_list}">
						<div class="change">
							<p>${k.key}</p>
							<span>${k.value}</span>
						</div>
					</c:forEach>
				</div>
				<c:forEach var="k" begin="1" end="10">
					<div class="slider fade">
						<img class="slide_show" alt=""
							src="/resources/ko_images/main_image/${k}.jpg">
					</div>
				</c:forEach>
			</div>
			<div class="img_slider_bg">
				<c:forEach var="k" begin="1" end="10">
					<div class="slider_bg fade_bg">
						<img class="slide_show_bg" alt=""
							src="/resources/ko_images/main_image/${k}.jpg">
					</div>
				</c:forEach>
			</div>
		</div>
		
		<c:choose>
			<c:when test="${empty memberUser}"></c:when>
			<c:otherwise>
				<%@ include file="/WEB-INF/views/jung_view/recommend_gpt.jsp"%>
			</c:otherwise>
		</c:choose>
		
		
		<div class="main_text">
			<h2>지역별 추천경로 BEST</h2>
		</div>
		<div class="path_button">
			<c:forEach var="k" items="${area}">
				<div>
					<button type="button" onclick="area_wish(${k.key})">${k.value}</button>
				</div>
			</c:forEach>
		</div>
		<div id="area_wrapper">
			<c:forEach var="k" items="${area_list}">
				<div class="path_box" onclick="detail_go(${k.path_post_idx})">
					<div class="path_image">
						<c:choose>
							<c:when test="${k.img_status==0}">
								<img src="resources/rc_main_img/${k.firstimage}">								
							</c:when>
							<c:otherwise>
								<img src="${k.firstimage}">
							</c:otherwise>
						</c:choose>
					</div>
					<c:choose>
						<c:when test="${fn:length(k.path_post_title) >= 12}">
							<div class="path_text">${fn:substring(k.path_post_title, 0, 12)}...</div>
						</c:when>
						<c:otherwise>
							<div class="path_text">${k.path_post_title}</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>

		<div class="main_text">
			<h2>테마별 추천경로 BEST</h2>
		</div>
		<div class="path_button">
			<c:forEach var="k" items="${tema}">
				<div>
					<button type="button" onclick="tema_wish(${k.key})">${k.value}</button>
				</div>
			</c:forEach>
		</div>
		<div id="tema_wrapper">
			<c:forEach var="k" items="${tema_list}">
				<div class="path_box" onclick="detail_go(${k.path_post_idx})">
					<div class="path_image">
						<c:choose>
							<c:when test="${k.img_status==0}">
								<img src="resources/rc_main_img/${k.firstimage}">								
							</c:when>
							<c:otherwise>
								<img src="${k.firstimage}">
							</c:otherwise>
						</c:choose>
					</div>
					<c:choose>
						<c:when test="${fn:length(k.path_post_title) >= 12}">
							<div class="path_text">${fn:substring(k.path_post_title, 0, 12)}...</div>
						</c:when>
						<c:otherwise>
							<div class="path_text">${k.path_post_title}</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>
		
		<div style="width: 100%; height: 70px;"></div>
		
	</section>

	<script type="text/javascript">
		let slideIndex = 0;
		let slideIndexBg = 0;
		let textIndex = 0;
	    showSlides();
	    function showSlides() {
	        let i;
	        let slides = document.getElementsByClassName("slider");
	        let slidesBg = document.getElementsByClassName("slider_bg");
	        let change = document.getElementsByClassName("change");
	        for (i = 0; i < slides.length; i++) {
	            slides[i].style.display = "none";
	            slidesBg[i].style.display = "none";
	            change[i].style.display = "none";
	        }
	        slideIndex++;
	        slideIndexBg++;
	        textIndex++;
	        if (slideIndex > slides.length) {
	            slideIndex = 1
	            slideIndexBg = 1
	            textIndex = 1
	        }
	        slides[slideIndex - 1].style.display = "block";
	        slidesBg[slideIndexBg - 1].style.display = "block";
	        change[textIndex - 1].style.display = "block";
	        setTimeout(showSlides, 3000); // 3초마다 이미지 변경
	    }
	</script>
	

	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>