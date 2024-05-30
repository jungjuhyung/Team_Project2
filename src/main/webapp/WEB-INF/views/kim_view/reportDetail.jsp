<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고게시판</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/reportDetail.css">
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">

    <!-- include summernote css/js-->
<link rel="stylesheet" href="resources/jung_summernote/summernote-lite.css">
    
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
function reportConfirm(f) {
	console.log="${reportvo.report_idx}";
	f.action="reportConfirm";
	f.submit();
}
</script>

</head>

<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<script src="resources/jung_summernote/summernote-lite.js"></script>
<script src="resources/jung_summernote/summernote-ko-KR.js"></script>
<form method="post">
		<div class="reportcontainer">
			<div class="insert">

				<table id="reporttable">
					<caption>
						<h2>신고게시판</h2>
					</caption>
					<tr class="reporttr">
						<td class="reportmenu">아이디</td>
						<td class="userin">${reportvo.u_id}(${reportvo.u_lev})</td>
					</tr>
					<tr class="reporttr">
						<td class="reportmenu">불량유저ID</td>
						<td class="userin">${reportvo.reported_id}</td>
					</tr>
					<tr class="reporttr">
						<td class="reportmenu">제목</td>
						<td class="userin">${reportvo.report_title }
						</td>
					</tr>
					<tr class="reporttr">
						<td class="reportmenu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content" readonly>${reportvo.content }</textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="reportcreate">
				<input type="hidden" name="report_idx" value="${reportvo.report_idx}">
				<input type="hidden" name="cPage" value="${cPage}">
				<input class="but4" type="button" value="목록" onclick="getReportgo(this.form)"/>
				<c:if test="${membervo.u_id == reportvo.u_id}">
				<input class="but4" type="button" value="수정" onclick="reportUpdate(this.form)"/>
				</c:if>
				<c:if test="${membervo.u_id == reportvo.u_id || adminUser != null}">
				<input class="but4" type="button" value="삭제" onclick="reportDelete(this.form)"/>
				</c:if>
				<!-- 관리자 확인버튼 -->
				<c:if test="${adminUser != null && reportvo.report_state != 1 }">
				<input type="hidden" name="admin_id" value="${adminUser.admin_id}">
				<input type="hidden" name="reported_id" value="${reportvo.reported_id}">
				<input class="but4" type="button" value="확인" onclick="reportConfirm(this.form)"/>
				</c:if>
			</div>
		</div>
	</form>
	
	
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
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>