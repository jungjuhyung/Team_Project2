<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/lee_css/id_email_form.css">
<link rel="icon" href="/resources/ko_images/favicon.png">
<script type="text/javascript">
	function idfindForm(f) {
		var name = document.getElementById("u_name").value;
		var email = document.getElementById("u_email").value;
		var emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]+(\.[a-zA-Z]+)?$/;

		if (name.trim() === "") {
			alert("이름을 입력하세요.");
			return false;
		}
		if (email.trim() === "") {
			alert("이메일을 입력하세요.");
			return false;
		} else if (!emailPattern.test(email)) {
			alert("이메일 형식이 아닙니다.");
			return false;
		}
		f.action = "idFind_go.do";
		f.submit();

	}
	function backup(f) {
		f.action = "backup.do";
		f.submit();
	}
	
	
</script>

</head>
<body>
 <%@ include file="/WEB-INF/views/common_view/header.jsp"%> 
<div class="find-container" style="width: 1300px; height:900px; margin: 0 auto;">
	<div class="id_sub">
	<div class="id_form">
			<form method="post">
				 <h2>아이디 찾기</h2>
					<input type="text" id="u_name" name="u_name" placeholder="이름"><br>
					<input type="email" id="u_email" name="u_email"
							pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*"
							title="이메일 양식" placeholder="이메일">
					<input type="button" onclick= "idfindForm(this.form)" value="아이디 찾기">
					<input type="button" onclick="backup(this.form)" value="돌아가기"> 
			</form>
			</div>
			</div> 
</div>
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>