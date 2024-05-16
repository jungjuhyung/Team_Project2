<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/lee_css/pw_email_form.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link rel="icon" href="/resources/ko_images/favicon.png">
<script type="text/javascript">
	function backup(f) {
		
		f.action="backup.do";
		f.submit();
	}
	
	
</script>
</head>
<body>
 <%@ include file="/WEB-INF/views/common_view/header.jsp"%>
<div class="find-container" style="width: 1300px; height:900px; margin: 0 auto;">
<div class="pw_sub">
	<div class="pw_form">
		<form action="email_send_ok.do" method="post">
		<h2>비밀번호 찾기</h2>
			<input type="email" name="email"
				pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*"
				title="이메일 양식" placeholder="이메일" > 
			<input type="submit"  value="전송">
			<input type="button" onclick="backup(this.form)" value="돌아가기" >
		</form>
		
	</div>
	</div>	
	</div>
	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>








