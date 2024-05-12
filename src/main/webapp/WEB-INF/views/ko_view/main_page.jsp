<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Five Guys Travel Guide</title>
<link rel="icon" href="/resources/ko_images/favicon.png">
<link rel="stylesheet" type="text/css" href="resources/ko_css/main_page.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
	function detail_go(contentid, contenttypeid) {
		location.href = "ko_detail.do?contentid=" + contentid 
						+ "&contenttypeid=" + contenttypeid;
	}
	
	function area_wish(areacode) {
		console.log(areacode);
		$.ajax({
			url : "ko_ajax_area.do",
			data : "areacode=" + areacode,
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$("#area_wrapper").empty();
				let content = "";
				$.each(data, function(index, value) {
					content += '<div class="path_box" onclick="detail_go(' 
								+ value.contentid + ',' + value.contenttypeid + ')">' 
								+ '<div class="path_image">' 
								+ '<img alt="" src="' + value.firstimage + '"></div>'
								+ '<div class="path_text">' + value.title + '</div></div>';
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
			url : "ko_ajax_tema.do",
			data : "contenttypeid=" + contenttypeid,
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data);
				$("#tema_wrapper").empty();
				let content = "";
				$.each(data, function(index, value) {
					content += '<div class="path_box" onclick="detail_go(' 
								+ value.contentid + ',' + value.contenttypeid + ')">' 
								+ '<div class="path_image">' 
								+ '<img alt="" src="' + value.firstimage + '"></div>'
								+ '<div class="path_text">' + value.title + '</div></div>';
				})
				
				
				$("#tema_wrapper").append(content);
			},
			error : function() {
				alert("실패");
			}
		});
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

	<section style="margin: 0 auto; width: 1300px; min-height: 1750px;">
		<div class="img_wrapper">
			<div class="img_slider2">
				<c:forEach var="k" begin="1" end="10">
					<div class="slider2 fade2">
						<img class="slide_show2" alt=""
							src="/resources/ko_images/main_image/${k}.jpg">
					</div>
				</c:forEach>
			</div>
			<div class="img_slider">
				<div class="slide_box">
						<div class="change">
							<p>남산</p>
							<span>서울타워와 벚꽃</span>					
						</div>
						<div class="change">
							<p>고창</p>
							<span>해바라기 밭</span>					
						</div>
						<div class="change">
							<p>북한산</p>
							<span>가을단풍 풍경</span>					
						</div>
						<div class="change">
							<p>안동</p>
							<span>하회마을 설경</span>					
						</div>
						<div class="change">
							<p>평창</p>
							<span>대관령 양떼목장</span>					
						</div>
						<div class="change">
							<p>광화문</p>
							<span>경복궁의 정문</span>					
						</div>
						<div class="change">
							<p>아산</p>
							<span>노란 은행나무길</span>					
						</div>
						<div class="change">
							<p>광양</p>
							<span>섬진강 매화 축제</span>					
						</div>
						<div class="change">
							<p>한강</p>
							<span>동작대교 야경</span>					
						</div>
						<div class="change">
							<p>서울</p>
							<span>아름다운 풍경</span>					
						</div>
				</div>
				<c:forEach var="k" begin="1" end="10">
					<div class="slider fade">
						<img class="slide_show" alt=""
							src="/resources/ko_images/main_image/${k}.jpg">
					</div>
				</c:forEach>
			</div>
		</div>
		<hr>
		<h2>지역별 추천경로(좋아요 높은거)</h2>
		<h4>=>현재는 tourapi 테이블 데이터</h4>
		<div class="path_button">
			<c:forEach var="k" items="${area}">
				<div>
					<button type="button" onclick="area_wish(${k.key})">${k.value}</button>
				</div>
			</c:forEach>
		</div>
		<div id="area_wrapper">
			<c:forEach var="k" items="${area_list}">
				<div class="path_box"
					onclick="detail_go(${k.contentid}, ${k.contenttypeid})">
					<div class="path_image">
						<img alt="" src="${k.firstimage}">
					</div>
					<div class="path_text">${k.title}</div>
				</div>
			</c:forEach>
		</div>
		<hr>
		<h2>테마별 추천경로(좋아요 높은거)</h2>
		<h4>=>현재는 tourapi 테이블 데이터</h4>
		<div class="path_button">
			<c:forEach var="k" items="${tema}">
				<div>
					<button type="button" onclick="tema_wish(${k.key})">${k.value}</button>
				</div>
			</c:forEach>
		</div>
		<div id="tema_wrapper">
			<c:forEach var="k" items="${tema_list}">
				<div class="path_box"
					onclick="detail_go(${k.contentid}, ${k.contenttypeid})">
					<div class="path_image">
						<img alt="" src="${k.firstimage}">
					</div>
					<div class="path_text">${k.title}</div>
				</div>
			</c:forEach>
		</div>
	</section>

	<script type="text/javascript">
		let slideIndex = 0;
		let slideIndex2 = 0;
		let textIndex = 0;
	    showSlides();
	    showSlides2();
	    showText();
	
	    function showSlides() {
	        let i;
	        let slides = document.getElementsByClassName("slider");
	       
	        for (i = 0; i < slides.length; i++) {
	            slides[i].style.display = "none";
	        }
	        slideIndex++;
	        if (slideIndex > slides.length) {
	            slideIndex = 1
	        }
	        slides[slideIndex - 1].style.display = "block";
	    
	        setTimeout(showSlides, 3000); // 3초마다 이미지가 체인지됩니다
	    }
	
	    function showSlides2() {
	        let i;
	        let slides2 = document.getElementsByClassName("slider2");
	       
	        for (i = 0; i < slides2.length; i++) {
	            slides2[i].style.display = "none";
	        }
	        slideIndex2++;
	        if (slideIndex2 > slides2.length) {
	            slideIndex2 = 1
	        }
	        slides2[slideIndex2 - 1].style.display = "block";
	    
	        setTimeout(showSlides2, 3000); // 3초마다 이미지가 체인지됩니다
	    }
	    
	    function showText() {
	        let i;
	        let change = document.getElementsByClassName("change");
	       
	        for (i = 0; i < change.length; i++) {
	        	change[i].style.display = "none";
	        }
	        textIndex++;
	        if (textIndex > change.length) {
	        	textIndex = 1
	        }
	        change[textIndex - 1].style.display = "block";
	    
	        setTimeout(showText, 3000); // 3초마다 이미지가 체인지됩니다
	    }
	    
	</script>
	
	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>