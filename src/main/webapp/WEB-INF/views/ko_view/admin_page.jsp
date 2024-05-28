<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" href="/resources/ko_images/favicon.png">
<link rel="stylesheet" type="text/css"
	href="resources/ko_css/userBoard.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
	
</head>
<body>

	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

	<section style="margin: 0 auto; width: 1300px; min-height: 700px;">
		
		<div class="user_search">
			<h2>관리자 페이지</h2>
		</div>
		
		<div style="margin: 30px auto; text-align: center; ">
			<input type="button" class="user_btn3" value="유저관리" onclick="location.href='user_list.do'" />
			<input type="button" class="user_btn3" value="팝업관리" onclick="location.href='popup_img.do'" />
			<c:if test="${admin_grade == '2'}">
				<input type="button" class="user_btn3" value="채팅상담" onclick="location.href='adminQnAChat'" />
			</c:if>
			<c:if test="${admin_grade == '9'}">
				<input type="button" class="user_btn3" value="총관리자" onclick="location.href='adminManage'" />
			</c:if>
		</div>
			
	</section>

	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>