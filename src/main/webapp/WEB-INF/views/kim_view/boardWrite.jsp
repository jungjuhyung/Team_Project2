<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자유게시판</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/boardWrite.css">
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<!-- include summernote css/js-->
<link rel="stylesheet" href="resources/jung_summernote/summernote-lite.css">
<script type="text/javascript">
	let pwdchk = "${pwdchk}";
	console.log("pwdchk 값:", pwdchk);
	if(pwdchk == 'fail'){
		alert("비밀번호틀림");
	}
</script>
</head>

<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<script src="resources/jung_summernote/summernote-lite.js"></script>
<script src="resources/jung_summernote/summernote-ko-KR.js"></script>
<form method="post" action="boardWriteOK">
		<div class="boardcontainer">
			<div class="insert">

				<table class="boardtable">
					<caption class="boardcaption">
						<h2>자유게시판</h2>
					</caption>
					<tr class="boardtr">
						<td class="boardmenu">닉네임</td>
						<td class="userin">${membervo.u_nickname}</td>
					</tr>
					<tr class="boardtr">
						<td class="boardmenu">비밀번호</td>
						<td class="userin"><input type="password" id="board_pw" name="board_pw" />
					</tr>
					<tr class="boardtr">
						<td class="boardmenu">제목</td>
						<td class="userin"><input type="text" id="board_title" name="board_title">
						</td>
					</tr>
					<tr class="boardtr">
						<td class="boardmenu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content"></textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="boardcreate">
				<input class="but4" type="submit" value="등록하기">
				<input class="but4" type="button" value="취소하기" onclick="location.href='boardList'"> 

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
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>