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
<link rel="stylesheet" type="text/css" href="resources/ko_css/userList.css">

</head>
<body>

	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

	<section style="margin: 0 auto; width: 1300px;">


		<div class="user_table">
			<h2>유저 관리 게시판</h2>
			<table>
				<thead>
					<tr>
						<th>이름</th>
						<th>상태</th>
						<th>남은 정지일수</th>
						<th>정지일수 선택</th>
						<th>정지버튼</th>
						<th>작성글 이동</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="k" items="${user_list}">
						<tr>
							<td>
								${k.u_name}(${k.u_id})
							</td>
							<td><c:choose>
									<c:when test="${k.u_active == '1'}">정지</c:when>
									<c:otherwise>원활</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${empty k.u_stopdate}">
										없음
									</c:when>
									<c:otherwise>
										<%-- 참고사이트 https://blog.naver.com/javaking75/220033235370 --%>
										<%-- 참고사이트 https://cofs.tistory.com/261 --%>
										<!-- 현재날짜 -->
										<jsp:useBean id="now" class="java.util.Date" />
										<fmt:parseNumber value="${now.time / (1000*60*60*24)}"
											integerOnly="true" var="nowDate"></fmt:parseNumber>
										<!-- 정지만료날짜 -->
										<fmt:parseDate value="${k.u_stopdate}" pattern="yyyy-MM-dd"
											var="stop"></fmt:parseDate>
										<fmt:parseNumber value="${stop.time / (1000*60*60*24)}"
											integerOnly="true" var="stopDate"></fmt:parseNumber>
										${stopDate - nowDate}일
									</c:otherwise>
								</c:choose></td>
							<td>
								<input type="radio" name="stop_radio" value="30">30일
								<input type="radio" name="stop_radio" value="90">90일
								<input type="radio" name="stop_radio" value="9999">영구정지
							</td>
							<td>
								<c:choose>
									<c:when test="${k.u_active == '1'}">
										<input type="button" class="user_btn2" value="해제하기" onclick="start_go(this.form)">
									</c:when>
									<c:otherwise>
										<input type="button" class="user_btn" value="정지하기" onclick="stop_go(this.form)">
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<input type="button" class="user_btn" value="작성글로 이동"
											onclick="board_go(this.form)">
								<input type="hidden" name="u_idx" value="${k.u_idx}" />					
							</td>
						</tr>
					</c:forEach>

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
							href="user_list.do?cPage=${paging.beginBlock - paging.pagePerBlock}">
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
							<li><a href="user_list.do?cPage=${k}">${k}</a></li>
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
							href="user_list.do?cPage=${paging.beginBlock + paging.pagePerBlock}">
								다음</a></li>
					</c:otherwise>
				</c:choose>
			</ol>
		</div>

	</section>

	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>