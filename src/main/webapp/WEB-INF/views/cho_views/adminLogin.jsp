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
<link rel="icon" href="/resources/ko_images/favicon.png">
<script type="text/javascript">
	// 로그인 시
	function sign_in_go(f) {
		if(f.admin_id.value == ""){
			alert("아이디를 입력해주세요.");
			f.admin_id.focus();
			return;
		}
		if(f.admin_pwd.value == ""){
			alert("비밀번호를 입력해주세요.");
			f.admin_pwd.focus();
			return;
		}
		
		f.action = "adminLoginOK";
		f.submit();
	}
	
</script>
</head>

<body>
<!-- 수정요청 -->
		<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
	<div class="login-container" style="width: 1300px; height:900px; margin: 0 auto;" >
		<div class="login">
			<form method="post">
				<div class="login-form">
					<h2>로그인</h2>
					<input type="text" class="input" id="admin_id" name="admin_id" placeholder="아이디" required><br> 
					<input type="password" class="input" id="admin_pwd" name="admin_pwd" placeholder="비밀번호" required>
					<input type="button" id="btn-signin" value = "Sign in" onclick="sign_in_go(this.form)">
				</div>
			</form>
		</div>
		<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
		</div>	
</body>
</html> 