<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/boardDetail.css">
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<!-- include summernote css/js-->
<link rel="stylesheet" href="resources/jung_summernote/summernote-lite.css">
    
<script type="text/javascript">
function boardUpdate(f) {
	f.action="boardUpdate";
	f.submit();
}
function boardDelete(f) {
	f.action="boardDelete";
	f.submit();
}
function reportWrite(f) {
	f.action="reportWrite";
	f.submit();
}
function commentInsert(f) {
	f.action="commentInsert";
	f.submit();
}
function commentDelete(f) {
	f.action="commentDelete";
	f.submit();
}
</script>

</head>

<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
<!-- include summernote css/js-->
<script src="resources/jung_summernote/summernote-lite.js"></script>
<script src="resources/jung_summernote/summernote-ko-KR.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<form method="post">
		<div class="boardcontainer">
			<div class="insert">

				<table id="boardtable">
					<caption class="boardcaption">
						<h2>자유게시판</h2>
					</caption>
					<tr class="boardtr">
						<td class="boardmenu">닉네임</td>
						<td class="userin">${boardvo.u_nickname}(${boardvo.u_lev})</td>
					</tr>
					<tr class="boardtr">
						<td class="boardmenu">제목</td>
						<td class="userin">${boardvo.board_title }
						</td>
					</tr>
					<tr class="boardtr">
						<td class="boardmenu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content" readonly>${boardvo.content }</textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="boardcreate">
				<input type="hidden" name="board_idx" value="${boardvo.board_idx}">
				<input type="hidden" name="cPage" value="${cPage}">
				<input class="but4" type="button" value="목록" onclick="location.href='boardList'"/>
				<c:if test="${membervo.u_idx == boardvo.u_idx}">
				    <input class="but4" type="button" value="수정" onclick="boardUpdate(this.form)"/>
				    <input class="but4" type="button" value="삭제" onclick="boardDelete(this.form)"/>
				</c:if>
				<c:if test="${membervo.u_idx != boardvo.u_idx && membervo != null}">
					<input type="hidden" name="reported_id" value="${boardvo.u_id}">
					<input class="but4" type="button" value="신고" onclick="reportWrite(this.form)">
				</c:if>
				<c:if test="${adminUser != null}">
				    <input class="but4" type="button" value="삭제" onclick="boardDelete(this.form)"/>
				</c:if>
			</div>
		</div>
	</form>
	
	
	<%-- 댓글 출력 --%>
	<div class="recomment">
		<c:forEach var="k" items="${comment_list}">
			<div>
				<form method="post">
					<div class="renick">${k.u_nickname}(${k.u_lev})</div>
					<div class="recontent">
						<textarea rows="3" cols="40" name="content" readonly>${k.content}</textarea>
					</div>
						<div class="rebutton">${k.regdate.substring(0,10)}
						<c:choose>
							<c:when test="${membervo.u_idx == k.u_idx || adminUser != null}">							
								<input class="rewrite" type="button" value="삭제" onclick="commentDelete(this.form)">
							</c:when>
							<c:otherwise>
								<span></span>
							</c:otherwise>
						</c:choose>
						<c:if test="${membervo != null && membervo.u_idx != k.u_idx}">
							<input type="hidden" name="reported_id" value="${k.u_id}">
							<input class="rewrite" type="button" value="신고" onclick="reportWrite(this.form)">
						</c:if>
						</div>
						<input type="hidden" name = "comment_idx" value="${k.comment_idx}" >
						<input type="hidden" name = "board_idx" value="${k.board_idx}" >
				</form>
			</div>
		</c:forEach>
	</div>	
		<%-- 댓글 입력 --%>
	<c:if test="${membervo != null}">
	<div class="recomment">
		<form method="post">
			<fieldset>
			<div class="renick">
				<span>${membervo.u_nickname}(${membervo.u_lev})</span> 
			</div>
			<div class="recontent">
				<textarea rows="3" cols="40" name="content"></textarea>
			</div>
			<div class="rebutton">
				<input class="rewrite" type="button" value="저장" onclick="commentInsert(this.form)">
				<input type="hidden" name = "board_idx" value="${boardvo.board_idx}" >
			</div>
		
				<!-- 댓글 저장시 어떤 원글의 댓글인지 저장해야 한다. -->
			</fieldset>
		</form>
	</div>
	</c:if>
	<div id="empty-area">
	</div>
	
<script>

	let $j = jQuery.noConflict();
	
    // 메인화면 페이지 로드 함수
    $j(document).ready(function () {
    	$j('#summernote').summernote({
            placeholder: '내용을 작성하세요',
            height: 400,
            maxHeight: 400
        });
    	$j('#summernote').summernote('disable');
    });
</script>
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>