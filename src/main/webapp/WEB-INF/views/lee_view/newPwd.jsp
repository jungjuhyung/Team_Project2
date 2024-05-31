<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="resources/lee_css/newPwd.css">
<link rel="icon" href="resources/ko_images/favicon.png">
<script type="text/javascript">
	function pwd_up(f) {
		if(f.u_pwd.value != f.pwdchk.value){
			alert("비밀번호가 일치하지 않습니다.");
			f.focus(f.u_pwd.value);
			return;
		}
		
		f.action= "new_pass_ok.do";
		f.submit();
	}
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
<div class="pw-container" style="width: 1300px; height:900px; margin: 0 auto;">
	<div class="pw_sub_1">
		<div class="sendpw_form">
		<h2>비밀번호 변경</h2>
			<c:if test="${not empty message }">
				<div>${message}</div>
			</c:if>
			<form method="post">
				<input type="password"  name="u_pwd" placeholder="새 비밀번호"/> 
				<input type="password"  name="pwdchk" placeholder="새 비밀번호 확인"/> 
				<input type="hidden" name="u_idx" value="${mvo.u_idx}">
				<input type="button" value="확인" onclick="pwd_up(this.form)">
			</form>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>