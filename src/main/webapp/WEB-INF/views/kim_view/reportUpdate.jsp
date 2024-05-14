<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자유게시판</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/reportWrite.css">
    <!-- include libraries(jQuery, bootstrap) -->
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
    <!-- include summernote css/js-->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    
<script type="text/javascript">
$(document).ready(function(){
	let pwdchk = "${pwdchk}";
	console.log("pwdchk 값:", pwdchk);
	if(pwdchk == 'fail'){
		alert("비밀번호틀림");
		return;
	}
});

</script>
<script type="text/javascript">
function updateok(f) {
	f.action="reportUpdateOK";
	f.submit();
}
function getReportgo(f) {
	f.action="getReportgo";
	f.submit();
}
</script>
</head>
<body>

<form method="post" >
		<div class="container">
			<div class="insert">

				<table>
					<caption>
						<h2>자유게시판</h2>
					</caption>
					<tr>
						<td class="menu">아이디</td>
						<td class="userin">${reportvo.u_id}</td>
					</tr>
					<tr>
						<td class="menu">비밀번호</td>
						<td class="userin"><input type="password" id="report_cpw" name="report_cpw" />
					</tr>
					<tr>
						<td class="menu">제목</td>
						<td class="userin"><input type="text" id="report_title" name="report_title" value="${reportvo.report_title }">
						
						</td>
					</tr>
					<tr>
						<td class="menu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content">${reportvo.content }</textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="create">
				<input type="hidden" name="report_idx" value="${report_idx}">
				<input type="hidden" name="report_cpw" value="${report_cpw}">
				<input type="hidden" name="cPage" value="${cPage}">
				<input class="but4" type="button" value="수정하기" onclick="updateok(this.form)">
				<input class="but5" type="button" value="취소하기" onclick="getReportgo(this.form)"> 

			</div>
		</div>
	</form>
<script>
    // 메인화면 페이지 로드 함수
    $(document).ready(function () {
        $('#summernote').summernote({
            placeholder: '내용을 작성하세요',
            height: 400,
            maxHeight: 400
        });
       
    });
</script>
</body>
</html>