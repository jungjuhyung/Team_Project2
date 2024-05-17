<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="/resources/ko_images/favicon.png">

<link rel="stylesheet" type="text/css" href="resources/ko_css/popup.css">

<script type="text/javascript">
	function save_go(f) {
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

	<div class="popup">

		<div class="popup_form">
			<form method="post" enctype="multipart/form-data">
				<ul>
					<li>제목 : <input type="text" name="subject" size="20">
					</li>
					<li>사진 : <input type="file" name="file" size="20" required>
					</li>
					<li><input type="button" value="추가하기"
						onclick="save_go(this.form)"> <input type="reset"
						value="취소"></li>
				</ul>
			</form>
		</div>

		<table class="popup_table">
			<thead>
				<tr>
					<th>팝업이미지</th>
					<th>작성자</th>
					<th>제목</th>
					<th>등록날짜</th>
					<th>선택</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty popup_list}">
						<tr>
							<td colspan="5"><h3>등록된 이미지 목록이 없습니다.</h3></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${popup_list}">
							<tr>
								<td><img src="resources/popup_img/${k.f_name}"
									width="150px;"></td>
								<td>${k.u_id}</td>
								<td>${k.subject}</td>
								<td>${k.regdate.substring(0,10)}</td>
								<td><c:choose>
										<c:when test="${k.popup_state == '1'}">
	 										현재이미지
										</c:when>
										<c:otherwise>
											<button onclick="popup_update(${k.popup_idx})">바꾸기</button>
											<button onclick="popup_delete(${k.popup_idx})">삭제</button>
										</c:otherwise>
									</c:choose></td>
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

</body>
</html>