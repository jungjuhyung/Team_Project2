<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>받은 비밀번호 넣어주세요</h2>
	<form action="email_pass_ok.do" method="post">
		<input type="text" name="authNumber" min="6" max="6"> 
		<input type="submit" value="확인">
	</form>
</body>
</html>