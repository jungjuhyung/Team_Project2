<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="resources/jung_summernote/summernote-lite.js"></script>
<script src="resources/jung_summernote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="resources/jung_summernote/summernote-lite.css">
<script type="text/javascript">
function getReportgo(f) {
	f.action="getReportgo";
	f.submit();
}
</script>
<script type="text/javascript">
$(document).ready(function(){
    let badid = "${badid}";
    console.log("badid 값:", badid);
    if(badid === "fail"){
        alert("신고하는 아이디가 없습니다.");
        return;
    }
});

</script>
</head>

<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
<form method="post" action="reportWriteOK">
		<div class="reportcontainer">
			<div class="insert">

				<table id="reporttable">
					<caption>
						<h2>신고게시판</h2>
					</caption>
					<tr class="reporttr">
						<td class="reportmenu">아이디</td>
						<td class="userin">${membervo.u_id}</td>
					</tr>
					<tr class="reporttr">
						<td class="reportmenu">비밀번호</td>
						<td class="userin"><input type="password" id="report_pw" name="report_pw" />
					</tr>
					<tr class="reporttr">
						<td class="reportmenu">불량유저ID</td>
						<td class="userin"><input type="text" id="reported_id" name="reported_id">
						</td>
					</tr>
					<tr class="reporttr">
						<td class="reportmenu">제목</td>
						<td class="userin"><input type="text" id="report_title" name="report_title">
						</td>
					</tr>
					<tr class="reporttr">
						<td class="reportmenu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content"></textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="reportcreate">
				<input class="but4" type="submit" id="submit_btn" value="등록하기">
				<input class="but4" type="button" value="취소하기" onclick="getReportgo(this.form)"> 

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