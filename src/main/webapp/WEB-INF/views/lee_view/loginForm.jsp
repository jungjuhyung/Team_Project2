<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function sign_up_go(f) {
		f.action="agree_go.do";
		f.submit();
	}
</script>
</head>
<body>
	<div class="login-container">
		<form method="post">
			<h2>로그인</h2>
			<label>ID
				<input type="text" name="u_id" required>
			</label><br>
			<label>PW
				<input type="password" id="u_pwd" name="u_pwd" required>
			</label>
			<div class="text_chk">
				<div class="ip_chk">
					<a>
						아이디 / 비밀번호 찾기
					</a>
				</div>
			</div>
			<input type="submit" value="login" onclick="">
			<input type="submit" value="sign-up" name="signup" onclick="sign_up_go(this.form)">
			<a
				href="https://kauth.kakao.com/oauth/authorize?client_id=0d71c1a838198546c59f71aef1208e6e&redirect_uri=http://localhost:8090/kakaologin.do&response_type=code">
				<img src="resources/lee_images/kakao_login.png">
			</a>
		</form>
	</div>
</body>
</html>