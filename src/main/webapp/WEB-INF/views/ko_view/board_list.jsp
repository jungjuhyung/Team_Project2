<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" href="/resources/ko_images/favicon.png">
<link rel="stylesheet" type="text/css"
	href="resources/ko_css/userList.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function board_list(u_idx) {
		location.href = "board_list.do?u_idx="+u_idx;
	}
	
	function report_list(u_idx) {
		location.href = "report_list.do?u_idx="+u_idx;
	}
	
</script>

</head>
<body>

	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

	<section style="margin: 0 auto; width: 1300px; min-height: 700px;">
		
		<div style="margin: 30px auto; text-align: center; ">
			<input type="button" class="user_btn" value="자유게시판" onclick="board_list(${u_idx})" />
			<input type="button" class="user_btn" value="신고게시판" onclick="report_list(${u_idx})" />
			<input type="button" class="user_btn" value="추천경로게시판" onclick="user_path(${u_idx})" />
			<input type="button" class="user_btn" value="작성댓글" onclick="user_comment(${u_idx})" />
		</div>
		
		<div class="user_search">
			<h2>
				<span style="font-weight: bold;">${uvo.u_name}</span> 
				님의 자유게시판 작성글
			</h2>
		</div>
					
		<div class="user_table">
			<table>
				<thead>
					<tr>
						<th>제목</th>
						<th>날짜</th>
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
							<c:forEach var="k" items="${board_list}" varStatus="vs">
								<tr>
									<td>${k.board_title}</td>
									<td>${k.regdate.substring(0,10)}</td>
									<td><input type="button" class="user_btn" value="삭제"></td>
									<td><input type="button" class="user_btn" value="상세보기"></td>
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