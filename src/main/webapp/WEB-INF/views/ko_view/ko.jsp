<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.main_img{text-align: center;}
	.container{display: flex;}
	.wish li{list-style-type: none; padding-right: 20px;}
	.wish img{width: 200px; height: 200px;}
	.wish a{text-decoration: none;}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	
	<h1>메인페이지</h1>
	<hr>
	<section style="margin: 0 auto; width: 1300px;">
		<div class="main_img">
			<img alt="" src="http://tong.visitkorea.or.kr/cms/resource/55/1842055_image2_1.jpg">
		</div>
		<hr>
		<h2>지역별 장소 5개</h2>
		<div class="wish">
			<ul class="container">
				<c:forEach var="k" items="${area_list}">
					<li class="item">
						<a href="ko_detail.do?contentid=${k.contentid}">
							<img alt="" src="${k.firstimage}">
							<br>${k.title}
						</a>
					</li>	
				</c:forEach>
			</ul>
		</div>
		<hr>
		<h2>테마별 장소 5개</h2>
		<div class="wish">
			<ul class="container">
				<c:forEach var="k" items="${tema_list}">
					<li class="">
						<a href="ko_detail.do?contentid=${k.contentid}&contenttypeid=${k.contenttypeid}">
							<img alt="" src="${k.firstimage}">
							<br>${k.title}
						</a>
					</li>	
				</c:forEach>
			</ul>
		</div>
	</section>
	
	
</body>
</html>