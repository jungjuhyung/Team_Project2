<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/reportList.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
		        nowPage: ${paging.nowPage},
		        endBlock: ${paging.endBlock},
		        beginBlock: ${paging.beginBlock},
		        totalPage: ${paging.totalPage}
		    };
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
<script type="text/javascript">
	$(document).ready(function() {
		
		
		
		function getList() {
			$.ajax({
				url : "getReportList",
				method : "post",        //type : "post", 메서드와 동일
				dataType : "xml",
				success : function(data){
					let tbody ="";
					console.log(data);
					$(data).find("report").each(function() {
						tbody += "<tr>";
						tbody += "<td>" + "<input type= 'hidden' name = 'report_idx' value='"+$(this).find("report_idx").text()+"'>"
						tbody += "<td>" + $(this).find("report_idx").text() + "</td>";
						tbody += "<td>" + $(this).find("u_id").text() + "</td>";
						tbody += "<td><a href='#' class='report-title-link'>" + $(this).find("report_title").text() + "</td>";
						tbody += "<td>" + $(this).find("regdate").text() + "</td>";
						 let reportState = $(this).find("report_state").text();
						    if (reportState === "0") {
						        tbody += "<td style='color: red;'>답변대기</td>";
						    } else{
						        tbody += "<td style='color: blue;'>답변완료</td>";
						    } 
						tbody += "</tr>";
					});
					$("#tbody").append(tbody);
		/* 			
					$(".report-title-link").click(function(event) {
	                    event.preventDefault();
	                    let clickedTitle = $(this).text();
	                    let reportIdx = $(this).data("report_idx").text();
	                    window.location.href = "reportDetail?report_idx=" + encodeURIComponent(reportIdx);
					}); */
					$("body").on("click", ".report-title-link", function(event) {
					    event.preventDefault();

					    // 클릭한 보고서의 행(tr 요소)을 찾기
					    let $row = $(this).closest("tr");

					    // 클릭한 보고서의 report_idx 값을 가져옴
					    let reportIdx = $row.find("input[name='report_idx']").val();
					    
					    // reportDetail 페이지로 이동하면서 report_idx 값을 쿼리 문자열로 전달
					    window.location.href = "reportDetail?report_idx=" + encodeURIComponent(reportIdx);
					});
				},
				error : function(){
					alert("실패 역")
				}
			});	
		}
		
		getList();

	});
</script>
<script type="text/javascript">
function reportWrite() {
	location.href="reportWrite";
}
</script>
</head>
<body>
<div id="bigbox">
    <h2>신고게시판</h2>
    <div>
        <table id="list">
            <thead>
                <tr>
                    <th>번호</th><th>닉네임</th><th>제목</th><th>작성일자</th><th>답변여부</th>
                </tr>
            </thead>
            <tbody id="tbody">
            	
            </tbody>
        </table>
    </div>
    <div class="board-list-paging"></div>
    <div id="bwbtn"><input type="button" value="글쓰기" onclick="reportWrite()" /></div>
</div>
</body>
</html>