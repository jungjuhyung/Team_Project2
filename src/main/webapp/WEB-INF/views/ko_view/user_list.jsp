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
	href="resources/ko_css/userList.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

	$(function() {
		$("#search").focus();
	})
	
	function reset_go(u_idx, ustop_idx) {
		location.href = "stop_reset.do?u_idx="+u_idx+"&ustop_idx="+ustop_idx;		
	}
	
	function stop_go(u_idx, vs) {
		let stop_days = $('input[name=stop_days'+vs+']:checked').val();
		let stop_note = $('input[name=stop_note'+vs+']').val();
		if (stop_note == '') {
			alert('정지사유를 입력해주세요');
			$('input[name=stop_note'+vs+']').focus();
		}else {
			location.href = "stop_update.do?u_idx="+u_idx
							+"&stop_days="+stop_days
							+"&stop_note="+stop_note;
		}
	}

	function board_go(u_idx) {
		location.href = "user_board.do?u_idx="+u_idx;
	}
	
	function search_go() {
		let search = $('input[name=search]').val();
		if (search == '') {
			alert("검색어를 입력해주세요");
		}else {
			location.href = "user_search.do?search=" + search;
		}
	}
	
	function show_total() {
		location.href = "user_list.do";
	}

</script>

</head>
<body>

	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

	<section style="margin: 0 auto; width: 1300px; min-height: 700px;">
		
		<div style="margin: 30px auto; text-align: center; ">
			<input type="button" class="user_btn" value="관리자 페이지" onclick="location.href='adminpage'" />
		</div>
		
		<div class="user_search">
			<h2>유저 관리 게시판</h2>
			<ul>
				<li>이름(아이디) : 
					<input type="text" id="search" name="search" 
							onkeypress="if(event.keyCode==13){search_go();}" /> 
					<input type="button" class="user_btn" value="검색" onclick="search_go()">
					<input type="button" class="user_btn" value="전체보기" onclick="show_total()">
				</li>
			</ul>
		</div>

		<div class="user_table">
			<table>
				<thead>
					<tr>
						<th>이름</th>
						<th>상태</th>
						<th>남은 정지일수</th>
						<th>정지일수 선택</th>
						<th>정지사유</th>
						<th>정지버튼</th>
						<th>작성글 이동</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty user_list}">
							<tr>
								<td colspan="7">검색에 맞는 유저가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="k" items="${user_list}" varStatus="vs">
								<tr>
									<td>${k.u_name}(${k.u_id})</td>
									<td><c:choose>
											<c:when test="${k.u_state == '1'}">
												<b><span style="color: red;">정지</span></b>
											</c:when>
											<c:otherwise>원활</c:otherwise>
										</c:choose></td>
									<td>
										<c:choose>
											<c:when test="${empty k.u_stopdate}">
												없음
											</c:when>
											<c:otherwise>
												<%-- 참고사이트 https://blog.naver.com/javaking75/220033235370 --%>
												<%-- 참고사이트 https://cofs.tistory.com/261 --%>
												
												
												<!-- 현재날짜 -->
												<jsp:useBean id="now" class="java.util.Date" />
												<fmt:parseNumber value="${now.time / (1000*60*60*24)}"
																	integerOnly="true" var="nowDate" />
												<!-- 정지만료날짜 -->
												<fmt:parseDate value="${k.u_stopdate}" pattern="yyyy-MM-dd" var="stop" />
												<fmt:parseNumber value="${stop.time / (1000*60*60*24)}"
																	integerOnly="true" var="stopDate" />
												<span style="color: red;">${stopDate - nowDate}일</span>
												
												
												
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${k.u_state == '1'}">
												정지관리자 : ${k.admin_id}
											</c:when>
											<c:otherwise>
												<input type="radio" name="stop_days${vs.count}" value="30" checked>30일
												<input type="radio" name="stop_days${vs.count}" value="90">90일
												<input type="radio" name="stop_days${vs.count}" value="9999">영구정지
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${k.u_state == '1'}">
												${k.stop_note}
											</c:when>
											<c:otherwise>
												<input type="text" name="stop_note${vs.count}" />
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${k.u_state == '1'}">
											
											
												<input type="button" class="user_btn2" value="해제하기"
													onclick="reset_go(${k.u_idx}, ${k.ustop_idx})">
													
													
											</c:when>
											
											<c:otherwise>
											
											
												<input type="button" class="user_btn" value="정지하기"
													onclick="stop_go(${k.u_idx}, ${vs.count})">
													
													
													
											</c:otherwise>
										</c:choose>
									</td>
									<td><input type="button" class="user_btn" value="작성글로 이동"
										onclick="board_go(${k.u_idx})"></td>
								</tr>
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
						<li>
						<a href="user_list.do?cPage=${paging.beginBlock - paging.pagePerBlock}">
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