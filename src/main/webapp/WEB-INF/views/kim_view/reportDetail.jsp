<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고게시판</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/reportWrite.css">
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    <!-- include summernote css/js-->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    
<script type="text/javascript">
function getReportgo(f) {
	f.action="getReportgo";
	f.submit();
}
function reportUpdate(f) {
	f.action="reportUpdate";
	f.submit();
}
function reportDelete(f) {
	f.action="reportDelete";
	f.submit();
}
function commentInsert(f) {
	console.log="${reportvo.report_idx}";
	f.action="commentInsert";
	f.submit();
}
function commentDelete(f) {
	console.log="${reportvo.report_idx}";
	f.action="commentDelete";
	f.submit();
}
</script>

</head>

<body>
<form method="post">
		<div class="container">
			<div class="insert">

				<table id="reporttable">
					<caption>
						<h2>신고게시판</h2>
					</caption>
					<tr>
						<td class="menu">아이디</td>
						<td class="userin">${reportvo.u_id}</td>
					</tr>
					<tr>
						<td class="menu">제목</td>
						<td class="userin">${reportvo.report_title }
						</td>
					</tr>
					<tr>
						<td class="menu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content" readonly>${reportvo.content }</textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="create">
				<input type="hidden" name="report_idx" value="${reportvo.report_idx}">
				<input type="hidden" name="cPage" value="${cPage}">
				<input class="but4" type="button" value="목록" onclick="getReportgo(this.form)"/>
				<!-- 답변이 달리면 수정 삭제 불가 -->
				<input class="but4" type="button" value="수정" onclick="reportUpdate(this.form)"/>
				<input class="but4" type="button" value="삭제" onclick="reportDelete(this.form)"/>
			</div>
		</div>
	</form>
	
	
	<%-- 댓글 출력 --%>
	<div class="recomment">
		<c:forEach var="k" items="${comment_list}">
			<div >
				<form method="post">
				<div class="renick">
					닉네임 
				</div>
			<div class="recontent">
					<textarea rows="3" cols="40" name="content" readonly>${k.content}</textarea>
				</div>
			<div class="rebutton">
					${k.regdate.substring(0,10)}
					<input class="rewrite" type="button" value="삭제" onclick="commentDelete(this.form)">
				</div>
					<input type="hidden" name = "comment_idx" value="${k.comment_idx}" >
					<input type="hidden" name = "report_idx" value="${k.report_idx}" >
					
				</form>
			</div>
		</c:forEach>
	</div>	
		<!-- 댓글 입력 관리자만 쓸수 있게 바꿈-->
	<div class="recomment">
		<form method="post">
			<fieldset>
			<div class="renick">
				<span>관리자 : ${reportvo.u_id}</span> 
			</div>
			<div class="recontent">
				<textarea rows="3" cols="40" name="content"></textarea>
			</div>
			<div class="rebutton">
				<input class="rewrite" type="button" value="저장" onclick="commentInsert(this.form)">
			</div>
				<!-- 댓글 저장시 어떤 원글의 댓글인지 저장해야 한다. -->
				<input type="hidden" name = "report_idx" value="${reportvo.report_idx}" >
			</fieldset>
		</form>
	</div>
	<div id="empty-area">
	</div>
	 
<script>
    // 메인화면 페이지 로드 함수
    $(document).ready(function () {
        $('#summernote').summernote({
            placeholder: '내용을 작성하세요',
            height: 400,
            maxHeight: 400
        });
        $('#summernote').summernote('disable');
    });
</script>
</body>
</html>