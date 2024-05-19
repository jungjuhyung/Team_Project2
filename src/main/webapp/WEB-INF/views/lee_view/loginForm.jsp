<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/ko_css/main_page.css"> 
<link rel="stylesheet" type="text/css" href="resources/lee_css/loginForm.css">
<link rel="icon" href="/resources/ko_images/favicon.png">
<script type="text/javascript">
	// 회원가입 폼으로 이동
	function sign_up_go(f) {
		f.action = "agree_go.do";
		f.submit();
	}
	// 로그인 시
	function sign_in_go(f) {
		if(f.u_id.value == ""){
			alert("아이디를 입력해주세요.");
			f.u_id.focus();
			return;
		}
		if(f.u_pwd.value == ""){
			alert("비밀번호를 입력해주세요.");
			f.u_pwd.focus();
			return;
		}
		
		
		f.action = "login_go_ok.do";
		f.submit();
	}
	
	

	
</script>
</head>

<body>
<!-- 수정요청 -->
 <%@ include file="/WEB-INF/views/common_view/header.jsp"%> 
	<div class="login-container" style="width: 1300px; height:900px; margin: 0 auto;" >
	<c:if test="${not empty errorMessage}">
        <script>
            alert("${errorMessage}");
        </script>
    </c:if>
		<div class="login">
			<form method="post">
				<div class="login-form">
					<h2>로그인</h2>
					<input type="text" class="input" id="u_id" name="u_id" placeholder="아이디" required><br> 
					<input type="password" class="input" id="u_pwd" name="u_pwd" placeholder="비밀번호" required>
					<input type="submit" id="btn-signin"  value="Sign In" onclick="sign_in_go(this.form)">
					<input type="submit" id="btn-signup" value="Sign Up" onclick="sign_up_go(this.form)"><br>
				
					<a id="a2" href="id_email_send.do">아이디 찾기 / </a>
					<a id="a1" href="email_send.do">비밀번호 찾기</a>	<br>
					<br>
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=acef18f41c74c70cc25d2050d26d4e94&redirect_uri=http://localhost:8090/kakaologin.do&response_type=code">
					<img  src="resources/lee_images/kakao_login.png"><br>
					</a>
					<a id="a3" href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=TRKMsvddX9k547J_e_XD&state=STATE_STRING&redirect_uri=http://localhost:8090/naverlogin.do">
					<img   src="resources/lee_images/btnG_logIn.png">
				</a>
				</div>
			</form>
		</div>
	
		</div>	
		<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html> 