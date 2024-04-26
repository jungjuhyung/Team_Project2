<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자유게시판</title>
<link rel="stylesheet" href="resources/kim_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/boardWrite.css">
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    <!-- include summernote css/js-->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
    
<script type="text/javascript">
function boardUpdate(f) {
	f.action="boardUpdate";
	f.submit();
}
function boardDelete(f) {
	f.action="boardDelete";
	f.submit();
}
function commentInsert(f) {
	console.log="${boardvo.board_idx}";
	f.action="commentInsert";
	f.submit();
}
function commentDelete(f) {
	console.log="${boardvo.board_idx}";
	f.action="commentDelete";
	f.submit();
}
</script>
</head>

<body>
<form method="post">
		<div class="container">
			<div class="insert">

				<table>
					<caption>
						<h2>자유게시판</h2>
					</caption>
					<tr>
						<td class="menu">닉네임</td>
						<td class="userin">${boardvo.u_nickname}</td>
					</tr>
					<tr>
						<td class="menu">제목</td>
						<td class="userin">${boardvo.board_title }
						</td>
					</tr>
					<tr>
						<td class="menu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content" readonly>${boardvo.content }</textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="create">
				<input type="hidden" name="board_idx" value="${boardvo.board_idx}">
				<input type="hidden" name="cPage" value="${cPage}">
				<input class="but4" type="button" value="목록" onclick="location.href='boardList'"/>
				<input class="but4" type="button" value="수정" onclick="boardUpdate(this.form)"/>
				<input class="but4" type="button" value="삭제" onclick="boardDelete(this.form)"/>
			</div>
		</div>
	</form>
		<%-- 댓글 입력 --%>
	<div style="padding: 10px; width: 580px; margin: 0 auto">
		<form method="post">
			<fieldset>
				<span>닉네임 : ${boardvo.u_nickname}</span> 
				<span>내용 : <textarea rows="3" cols="40" name="content"></textarea>
				<input style="float: right;" type="button" value="저장" onclick="commentInsert(this.form)">
				</span>
				<!-- 댓글 저장시 어떤 원글의 댓글인지 저장해야 한다. -->
				<input type="hidden" name = "board_idx" value="${boardvo.board_idx}" >
			</fieldset>
		</form>
	</div>
	
	<%-- 댓글 출력 --%>
	<div style="display: table; margin: 0 auto;">
		<c:forEach var="k" items="${comment_list}">
			<div style="border: 1px solid #cc00cc; width: 580px; margin: 5px; padding: 5px;" >
				<form method="post">
					<p>이름 : ${k.u_nickname}</p>
					<p>내용 : <pre>${k.content}</pre></p>
					<p>날짜 : ${k.regdate.substring(0,10)}
					<!-- 실제은 로그인 성공 && 글쓴 사람만 삭제할 수 있어야 한다. -->
					<input style="float: right;" type="button" value="댓글삭제" onclick="commentDelete(this.form)">
					<input type="hidden" name = "comment_idx" value="${k.comment_idx}" >
					<input type="hidden" name = "board_idx" value="${k.board_idx}" >
					</p>
					
				</form>
			</div>
		</c:forEach>
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