<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/ko_css/ko.css">
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

	<h1>메인페이지</h1>
	<hr>
	<section style="margin: 0 auto; width: 1300px;">
		<div class="img_wrapper">
			<div class="img_slider">
				<c:forEach var="k" begin="1" end="11">
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
	    showSlides();
	
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
	</script>

</body>
</html>