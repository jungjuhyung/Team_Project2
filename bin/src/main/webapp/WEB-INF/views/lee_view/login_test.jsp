<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#res").empty();
		$.ajax({
			url : "kakaoUser2.do",
			method : "post",
			dataType : "text",
			success : function(data) {
				let users = data.split("/");
				$("#res").append(users[1]+"("+user[2] + ")" + "님 환영합니다.")
			},
			error : function() {
				alert("읽기실패");
			}
		}); 
	});
</script>
</head>
<body>
	<h2>kakao 로그인 결과</h2>
	<div id=res>
		<span></span>
		
	</div>
		<a href="https://kauth.kakao.com/oauth/logout?client_id=acef18f41c74c70cc25d2050d26d4e94&logout_redirect_uri=http://localhost:8090/kakaologout.do">
    	로그아웃
    </a>
</body>
</html>