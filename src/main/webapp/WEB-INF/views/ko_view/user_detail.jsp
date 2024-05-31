<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" href="resources/ko_images/favicon.png">
<link rel="stylesheet" type="text/css"
	href="resources/ko_css/userBoard.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function user_list() {
		location.href = "user_list.do";
	}
	
	function board_list(u_idx) {
		location.href = "board_list.do?u_idx="+u_idx;
	}
	
	function report_list(u_idx) {
		location.href = "report_list.do?u_idx="+u_idx;
	}
	
	function path_list(u_idx) {
		location.href = "path_list.do?u_idx="+u_idx;
	}
	
	function comment_list(u_idx) {
		location.href = "comment_list.do?u_idx="+u_idx;
	}
	
</script>
	
</head>
<body>

	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

	<section style="margin: 0 auto; width: 1300px; min-height: 700px;">
		
		<div style="margin: 30px auto; text-align: center; ">
			<input type="button" class="user_btn" value="전체유저목록" onclick=user_list() />
		</div>
		
		<div class="user_search">
			<h2>
				<span style="font-weight: bold;">${uvo.u_name}(${uvo.u_id})</span>
				님의 작성글 
			</h2>
		</div>
		
		<div style="margin: 30px auto; text-align: center; ">
			<input type="button" class="user_btn" value="자유게시판" onclick="board_list(${u_idx})" />
			<input type="button" class="user_btn" value="신고게시판" onclick="report_list(${u_idx})" />
			<input type="button" class="user_btn" value="추천경로게시판" onclick="path_list(${u_idx})" />
			<input type="button" class="user_btn" value="작성댓글" onclick="comment_list(${u_idx})" />
		</div>
			
	</section>

	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>