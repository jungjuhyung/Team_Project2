<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
function boardWrite() {
	location.href="boardWrite";
}
</script>
</head>
<body>
	<div id="board_form" align="center">
		<table summary="게시판 목록">
			<thead>
				<tr class="board_title">
					<th class="no">번호</th>
					<th class="u_nickname">글쓴이</th>
					<th class="title">제목</th>
					<th class="reg">날짜</th>
					<th class="hit">조회수</th>
				</tr>
			</thead>
			<tbody class="content_css">
				<c:choose>
					<c:when test="${empty boardlist }">
						<tr><td colspan="4"><h3>게시물이 존재하지 않습니다</h3> </td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${boardlist}" varStatus="vs">
							<tr>
								<td>${paging.totalRecord - ((paging.nowPage-1)*paging.numPerPage + vs.index )}</td>
								<td>${k.u_nickname}</td>
								<td>
								   <a href="boardDetail?board_idx=${k.board_idx}&cPage=${paging.nowPage}">${k.board_title}</a>
								</td>
								<td>${k.regdate.substring(0,10)}</td>
								<td >${k.hit }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<!-- 페이지기법 -->
			<tfoot>
				<tr>
					<td colspan="4">
						<ol class="paging">
						    <!-- 이전 -->
						    <c:choose>
						    	<c:when test="${paging.beginBlock <= paging.pagePerBlock }">
						    		<li class="disable">이전으로</li>
						    	</c:when>
						    	<c:otherwise>
						    		<li><a href="boardList?cPage=${paging.beginBlock - paging.pagePerBlock }">이전으로</a></li>
						    	</c:otherwise>
						    </c:choose>
						    <!-- 블록안에 들어간 페이지번호들 -->
						    <c:forEach begin="${paging.beginBlock }" end="${paging.endBlock }" step="1" var="k">
						    	<%-- 현재 페이지이면 링크 X , 나머지 페이지는 링크 O --%>
						    	<c:if test="${k == paging.nowPage }">
						    		<li class="now">${k }</li>
						    	</c:if>
						    	<c:if test="${k != paging.nowPage }">
						    		<li><a href="boardList?cPage=${k}">${k}</a></li>
						    	</c:if>
						    </c:forEach>
							<!-- 다음 -->
							<c:choose>
						    	<c:when test="${paging.endBlock >= paging.totalPage }">
						    		<li class="disable">다음으로</li>
						    	</c:when>
						    	<c:otherwise>
						    		<li><a href="boardList?cPage=${paging.beginBlock + paging.pagePerBlock }">다음으로</a></li>
						    	</c:otherwise>
						    </c:choose>
						</ol>
					</td>
				<%-- <c:choose>
					<c:when test="${ssuvo.user_type != null}">	 --%>
						<td>
							<input type="button" value="글쓰기" onclick="boardWrite()" />
						</td>
					<%-- </c:when>
				</c:choose> --%>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>