<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/boardDelete.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		let pwdchk = "${pwdchk}";
		console.log("pwdchk 값:", pwdchk);
		if(pwdchk == 'fail'){
			alert("비밀번호틀림");
			return;
		}
	});
</script>
<script type="text/javascript">
	function boardDeleteOK(f) {
		f.action = "boardDeleteOK";
		f.submit();
	}
</script>
</head>
<body>

	<form method="post">
	<section id="wrap">
		<div id="deletecon">
			<h1>비밀번호를 <br>
			입력해주세요</h1>
			<input type="password" name="board_cpw" id="board_cpw">
		</div>
		<div id="btnn">
			<input type="hidden" name="board_idx" value="${board_idx}">
			<input type="hidden" name="cPage" value="${cPage}">
			<input type="hidden" name="board_cpw" value="${board_cpw}">
			<input type="button" value="삭제" onclick=" boardDeleteOK(this.form)">
			<input type="button" value="취소" onclick="location.href='boardList'">
		</div>
	</section>
	</form>
	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>