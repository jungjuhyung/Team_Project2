<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#res").empty();
		$.ajax({
			url : "kakaoUser.do",
			method : "post",
			dataType : "text",
			success : function (data) {
				let users = data.split("/");
				$("#res").append(users[1]+"("+user[2] + ")" + "님 환영합니다.")
			},
			error : function(){
				alert("읽기실패");
			}
		});
	});
</script>
</head>
<body>
	<h2>kakao 로그인 결과</h2>
	<div id=res>
		<a href="https://kauth.kakao.com/oauth/logout?client_id=0d71c1a838198546c59f71aef1208e6e&logout_redirect_uri=http://localhost:8090/kakaologout.do">
    	로그아웃
    </a>
	</div>
</body>
</html>