<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="resources/lee_css/email_chk.css">
<link rel="icon" href="/resources/ko_images/favicon.png">
</head>
<body>
 <%@ include file="/WEB-INF/views/common_view/header.jsp"%>
<div class="pw-container" style="width: 1300px; height:900px; margin: 0 auto;">
	<div class="pw_sub_1">
		<div class="sendpw_form">
		<h2>임시 비밀번호 넣어주세요</h2>
			<c:if test="${not empty message }">
				<div>${message}</div>
			</c:if>
			<form action="email_pass_ok.do" method="post">
				<input type="text" name="authNumber" min="6" max="6"> 
				<input type="submit" value="확인">
			</form>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>