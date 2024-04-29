<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/lee_css/loginForm.css">
<script type="text/javascript">
	function sign_in_go(f) {
		
		
		
		f.action = "login_go_ok.do"
		f.submit();
	}
	
	function sign_up_go(f) {
		f.action = "agree_go.do";
		f.submit();
	}
	
</script>
</head>
<body>
	<div class="login-container" style="width: 1300px; margin: 0 auto;">
		<div class="login">
			<form method="post" id="login_sub">
			<h2 class="login-title">로그인</h2>
				<div class="login-form">
					<input type="text" class="input" name="u_id" placeholder="아이디" required><br> 
					<input type="password" class="input" id="u_pwd" name="u_pwd" placeholder="비밀번호" required>
					<input type="submit" id="btn-signin"  value="Sign In" onclick="sign_in_go(this.form)">
					<input type="submit" id="btn-signup" value="Sign Up" onclick="sign_up_go(this.form)">
				</div>
				<a id="a1" href="#">비밀번호를 잊어버리셨나요?</a><br>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=acef18f41c74c70cc25d2050d26d4e94&redirect_uri=http://localhost:8090/kakaologin.do&response_type=code">
					<img src="resources/lee_images/kakao_login.png">
				</a>
				<a id="a3">
					<img src="resources/lee_images/btnG_logIn.png">
				</a>
			</form>
		</div>
	</div>
</body>
</html> 