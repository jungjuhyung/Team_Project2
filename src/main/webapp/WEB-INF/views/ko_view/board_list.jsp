<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" href="/resources/ko_images/favicon.png">
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
	
	function board_delete(board_idx, u_idx) {
		location.href = "board_delete.do?board_idx=" + board_idx + "&u_idx=" + u_idx;
	}
	
	function board_detail(board_idx) {
		location.href = "board_detail.do?board_idx=" + board_idx;  
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
			<input type="button" class="user_btn2" value="자유게시판" onclick="board_list(${u_idx})" />
			<input type="button" class="user_btn" value="신고게시판" onclick="report_list(${u_idx})" />
			<input type="button" class="user_btn" value="추천경로게시판" onclick="path_list(${u_idx})" />
			<input type="button" class="user_btn" value="작성댓글" onclick="comment_list(${u_idx})" />
		</div>
					
		<div class="user_table">
			<table>
				<thead>
					<tr>
						<th>제목</th>
						<th>작성날짜</th>
						<th>삭제하기</th>
						<th>본문이동</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty board_list}">
							<tr>
								<td colspan="4">작성글이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="k" items="${board_list}" >
								<tr>
									<td>
										<c:choose>
											<c:when test="${fn:length(k.board_title) >= 12}">
												<div class="path_text">${fn:substring(k.board_title, 0, 12)}...</div>
											</c:when>
											<c:otherwise>
												<div class="path_text">${k.board_title}</div>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${k.regdate.substring(0,10)}</td>
									<td>
										<c:choose>
											<c:when test="${k.active == '1'}">
												삭제
											</c:when>
											<c:otherwise>
												<input type="button" class="user_btn" value="삭제하기" 
															onclick="board_delete(${k.board_idx}, ${u_idx})">
											</c:otherwise>
										</c:choose>
									</td>
									<td><input type="button" class="user_btn" value="상세보기" 
												onclick="board_detail(${k.board_idx})"></td>
								<tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<div class="user_page">
			<ol class="paging">
				<!-- 이전 버튼 -->
				<c:choose>
					<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
						<li class="disable">이전</li>
					</c:when>
					<c:otherwise>
						<li><a
							href="board_list.do?cPage=${paging.beginBlock - paging.pagePerBlock}&u_idx=${u_idx}">
								이전</a></li>
					</c:otherwise>
				</c:choose>
				<!-- 페이지번호들 -->
				<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}"
					step="1" var="k">
					<c:choose>
						<c:when test="${k == paging.nowPage}">
							<li class="now">${k}</li>
						</c:when>
						<c:otherwise>
							<li><a href="board_list.do?cPage=${k}&u_idx=${u_idx}">${k}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 이후 버튼 -->
				<c:choose>
					<c:when test="${paging.endBlock >= paging.totalPage}">
						<li class="disable">다음</li>
					</c:when>
					<c:otherwise>
						<li><a
							href="board_list.do?cPage=${paging.beginBlock + paging.pagePerBlock}&u_idx=${u_idx}">
								다음</a></li>
					</c:otherwise>
				</c:choose>
			</ol>
		</div>

	</section>

	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>