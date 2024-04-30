<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/boardList.css">
<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
function boardWrite() {
	location.href="boardWrite";
}
</script>
<script type="text/javascript">
$(document).ready(function() {

	// 페이지 이동 엔터 처리
	document.querySelector(('.board-list-paging')).addEventListener('keypress', function(e) {
		 if (e.target.classList.contains('pageMove')) {
	        if (e.key === 'Enter') {
	            e.preventDefault(); // 기본 제출 동작 방지
	            document.querySelector('.pageMoveButton').click(); // SearchButton 클릭
	        }
		 }
	});
	
	document.querySelector(('.board-list-paging')).addEventListener("input", function(e) {
		 if (e.target.classList.contains('pageMove')) {
		        // 입력된 값에서 숫자가 아닌 문자를 제거
		        let inputValue = e.target.value.replace(/\D/g, '');
		        // input 요소의 값을 업데이트
		        e.target.value = inputValue;
		    }
	});
	
	let paging = {
			nowPage : ${paging.nowPage},
			endBlock : ${paging.endBlock},
			beginBlock : ${paging.beginBlock},
			totalPage : ${paging.totalPage}
	}
	updatePagination(paging);
	
});



// 페이지 번호 클릭 이벤트 처리
$(document).on("click", ".pagination li.page-item", function(e) {
    e.preventDefault();
    let page = parseInt($(this).attr('data-page'));
    location.href="boardList?cPage="+page; // 해당 페이지 검색 실행
});

// 페이지 검색 이동
$(document).on("click", ".pageMoveButton", function(e) {
    e.preventDefault();
    let cPage = $(".pageMove").val()
    if (cPage.trim() === "") {
    page = getCurrentPage(); // 현재 페이지 가져오기
	}
    search(cPage); // 해당 페이지 검색 실행
});

//뷰 옵션 이벤트 처리
$(document).on("change", "#viewLimit", function(e) {
    e.preventDefault();
    let cPage = getCurrentPage(); // 현재 페이지 번호 가져오기
    search(cPage); // search 함수 호출
});


// 현재 페이지 구하는 함수
function getCurrentPage() {
    return parseInt($(".nowPage").attr("data-page"));
}



function getTotalRecord(paging){
	
	let totalRecordHtml = '검색 결과('+paging.totalRecord+')'
	
    $('#resultCount').html(totalRecordHtml);
}

// 페이징 처리 함수
function updatePagination(paging) {
let content = '';
content += '<input type="hidden" class="nowPage" data-page="' + paging.nowPage + '">';
content += '<ol class="pagination" id="pagination">';
if (paging.beginBlock > 1) {
    content += '<li class="page-item" data-page="' + 1 + '"> << </li>';
}
if (paging.beginBlock > 1) {
    content += '<li class="page-item" data-page="' + (paging.nowPage - 1) + '"> < </li>';
}

// 페이지 번호를 표시할 개수
const pageNumberDisplay = 5;

// 현재 페이지 번호를 기준으로 앞뒤로 표시할 페이지 개수 계산
let startPage = Math.max(paging.nowPage - Math.floor(pageNumberDisplay / 2), 1);
let endPage = Math.min(startPage + pageNumberDisplay - 1, paging.totalPage);

// 보정된 시작 페이지 번호 계산
startPage = Math.max(endPage - pageNumberDisplay + 1, 1);

// 중앙에 오도록 현재 페이지 번호를 위치시키기 위한 변수 설정
let centerIndex = Math.floor(pageNumberDisplay / 2);

// 페이지 번호를 표시하는 부분 수정
for (let i = startPage; i <= endPage; i++) {
    if (i === paging.nowPage) {
        let inputClass = (i === startPage + centerIndex) ? 'center-page' : ''; // 중앙에 위치할 경우 클래스 추가
        content += '<li class = "nowPageInput">'
                    + '<input type="text" class="pageMove ' + inputClass + '" value="' + i + '">'
                  + '</li>';
    } else {
        content += '<li class="page-item" data-page="' + i + '">' + i + '</li>';
    }
}

if (paging.endBlock < paging.totalPage) {
    content += '<li class="page-item" data-page="' + (paging.nowPage + 1) + '"> > </li>';
}
if (paging.endBlock < paging.totalPage) {
    content += '<li class="page-item" data-page="' + paging.totalPage + '"> >> </li>';
}

content += '</ol>';

$(".board-list-paging").html(content);
}


	
</script>
</head>
<body>
	<div id="board_form" align="center">
		<table summary="게시판 목록" style="width: 1000px;">
			<thead>
				<tr class="board_title" style="width: 100%;">
					<th class="no">번호</th>
					<th class="u_nickname">닉네임</th>
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
									<c:choose>
								    	<c:when test="${k.active == 1 }">
								    		<span style="color: lightgray">삭제된 게시물</span>
								    	</c:when>
								    	<c:otherwise>
								   <a href="boardDetail?board_idx=${k.board_idx}&cPage=${paging.nowPage}">${k.board_title}</a>
								    </c:otherwise>
								    </c:choose> 
								</td>
								<td>${k.regdate.substring(0,10)}</td>
								<td >${k.hit}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<!-- 페이지기법 -->
		</table>
		<div class="board-list-paging"></div>
		<div><input type="button" value="글쓰기" onclick="boardWrite()" /></div>
	</div>
</body>
</html>



















