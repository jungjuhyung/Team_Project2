<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="/resources/ko_images/favicon.png">

<link rel="stylesheet" type="text/css" href="resources/ko_css/popup.css">

<script type="text/javascript">
	function save_go(f) {
		
		if (f.elements[0].value == "") {
			alert("제목을 입력해주세요!");
			f.elements[0].focus();
			return;
		}else if (f.elements[1].value == "") {
			alert("사진을 등록해주세요!");
			f.elements[1].focus();
			return;
		}
		
		f.action = "popup_img_insert.do";
		f.submit();
	}
	
	function popup_update(popup_idx) {
		document.cookie = 'popup=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
		location.href = "popup_img_change.do?popup_idx=" + popup_idx;
	}
	
	function popup_delete(popup_idx) {
		location.href = "popup_img_delete.do?popup_idx=" + popup_idx;
	}
	
</script>

<style type="text/css">
</style>

</head>
<body>

	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>

		<div style="margin: 0px auto; text-align: center; ">
			<input type="button" class="user_btn" value="관리자 페이지" onclick="location.href='adminpage'" />
		</div>
	<div class="popup" style="margin: 0 auto; width: 1300px; min-height: 700px;">
		
		
		<div class="popup_form">
		
			<h2>팝업이미지 추가하기</h2>

			<form method="post" enctype="multipart/form-data">
				<table>
					<tr><th>제목</th><td><input class="pop_title" type="text" name="subject" /></td></tr>
					<tr><th>사진</th><td><input type="file" name="file" /></td></tr>
					<tr><td colspan="2" style="text-align: center;">
						<input class="pop_btn" type="button" value="추가하기" onclick="save_go(this.form)"> 
						<input class="pop_btn" type="reset" value="취소"></td></tr>
				</table>
			</form>
		</div>

		<div class="popup_table">
			<table>
				<thead>
					<tr>
						<th>팝업이미지</th>
						<th>설명</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty popup_list}">
							<tr>
								<td colspan="2"><h3>등록된 이미지 목록이 없습니다.</h3></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="k" items="${popup_list}">
								<tr>
									<td><img src="resources/popup_img/${k.f_name}"
										height="150px;"></td>
									<td>
									
										<div id="content_table">
											<table>
												<tr><th>작성자</th><td>${k.admin_id}</td></tr>
												<tr>
													<th>제목</th>
													<td>
														<c:choose>
															<c:when test="${fn:length(k.subject) >= 22}">
																<div>${fn:substring(k.subject, 0, 21)}...</div>
															</c:when>
															<c:otherwise>
																<div>${k.subject}</div>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
												<tr><th>등록날짜</th><td>${k.regdate.substring(0,10)}</td></tr>
												<tr><th>선택</th><td><c:choose>
											<c:when test="${k.popup_state == '1'}">
	 										<span style="color: red; font-weight: bold;">현재이미지</span>
										</c:when>
											<c:otherwise>
												<button class="pop_btn" onclick="popup_update(${k.popup_idx})">바꾸기</button>
												<button class="pop_btn" onclick="popup_delete(${k.popup_idx})">삭제</button>
											</c:otherwise>
										</c:choose></td></tr>
											</table>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

			<div class="popup_page">
				<ol class="paging">
					<!-- 이전 버튼 -->
					<c:choose>
						<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
							<li class="disable">이전</li>
						</c:when>
						<c:otherwise>
							<li><a
								href="popup_img.do?cPage=${paging.beginBlock - paging.pagePerBlock}">
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
								<li><a href="popup_img.do?cPage=${k}">${k}</a></li>
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
								href="popup_img.do?cPage=${paging.beginBlock + paging.pagePerBlock}">
									다음</a></li>
						</c:otherwise>
					</c:choose>
				</ol>
			</div>
		</div>

	</div>

	<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>

</body>
</html>