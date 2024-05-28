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
		
		 // alert 파라미터 확인 및 경고창 띄우기
        const failed = new URLSearchParams(window.location.search);
        const alertmsg = failed.get('alert');
        if (alertmsg === 'fail') {
            alert('권한이 없습니다.');
        }
		
		
		getList(1);
		console.log("${adminUser}")
		function getList(page) {
			$("#tbody").empty()
			$.ajax({
				url : "getReportList",
				method : "post",        //type : "post", 메서드와 동일
				dataType : "xml",
				data: {
					page: page // 페이지 번호 전달
				},
				success : function(data){
					let tbody ="";
					console.log(data);
					processXML(data);
					
					$(data).find("report").each(function() {
						tbody += "<tr>";
						tbody += "<td><input type='hidden' name='report_idx' value='" + $(this).find("report_idx").text() + "'>" + $(this).find("report_idx").text() + "</td>";
						tbody += "<td>" + $(this).find("u_id").text() + "</td>";
						tbody += "<td><a href='#' class='report-title-link'>" + $(this).find("report_title").text() + "</td>";
						tbody += "<td>" + $(this).find("regdate").text() + "</td>";
						 let reportState = $(this).find("report_state").text();
						    if (reportState === "0") {
						        tbody += "<td style='color: blue;'>확인중</td>";
						    } else{
						        tbody += "<td style='color: red;'>확인완료</td>";
						    } 
						tbody += "</tr>";
					});
					$("#tbody").append(tbody);
					if("${adminUser}" === "") {
					
					}else{
						
					}
					
				},
				error : function(){
					alert("실패 역")
				}
			});	
		}
		$("body").on("click", ".report-title-link", function(event) {
		    event.preventDefault();

		    // 클릭한 보고서의 행(tr 요소)을 찾기
		    let $row = $(this).closest("tr");

		    // 클릭한 보고서의 report_idx 값을 가져옴
		    let reportIdx = $row.find("input[name='report_idx']").val();
		    
		    
		    
		    // reportDetail 페이지로 이동하면서 report_idx 값을 쿼리 문자열로 전달
		    window.location.href = "reportDetail?report_idx=" + encodeURIComponent(reportIdx);
		});
					
		function processXML(xml) {
		    // XML에서 페이징 정보 추출
		    let paging = {
		        nowPage: $(xml).find('paging > nowPage').text(),
		        endBlock: $(xml).find('paging > endBlock').text(),
		        beginBlock: $(xml).find('paging > beginBlock').text(),
		        totalPage: $(xml).find('paging > totalPage').text()
		    };

		    // 페이징 처리
		    updatePagination(paging);
		}
		
		
		// 페이징 처리 함수
		function updatePagination(paging) {
	    let content = '';
	    content += '<input type="hidden" class="nowPage" data-page="' + paging.nowPage + '">';
	    content += '<ol class="pagination" id="pagination">';
	    if (paging.beginBlock > 1) {
	        content += '<li class="page-item" data-page="' + 1 + '"> 1 </li>';
	    }
	    if (paging.beginBlock > 1) {
	        content += '<li class="page-item" data-page="' + (paging.beginBlock - 1) + '"> ... </li>';
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
	 	// 빈 공간을 표시하는 부분 추가
	    if (paging.nowPage === 1) {
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	    }
	 	if( paging.nowPage === 2){
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	 	}
	 	if( paging.nowPage === 3){
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	 	}
	 	
	    // 페이지 번호를 표시하는 부분 수정
	    for (let i = startPage; i <= endPage; i++) {

		    if (i === paging.nowPage) {
		        content += '<li class="nowPageInput">'
		                    + '<input type="text" class="pageMove" value="' + i + '">'
		                  + '</li>';
		    } else {
		        content += '<li class="page-item" data-page="' + i + '">' + i + '</li>';
		    }
		}
		if(paging.nowPage < (paging.totalPage-2)){
		    if (paging.endBlock < paging.totalPage) {
		        content += '<li class="page-item" data-page="' + (paging.endBlock + 1) + '"> ... </li>';
		    }
		    if (paging.endBlock < paging.totalPage) {
		        content += '<li class="page-item totalPage" data-page="' + paging.totalPage + '"> '+ paging.totalPage +' </li>';
		    }
	    }
	    if (paging.nowPage ===  paging.totalPage) {
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	        content += '<li class="page-item blank"> </li>';
	    }
	 	if( paging.nowPage ===  (paging.totalPage-1)){
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	 	}
	 	if( paging.nowPage ===  (paging.totalPage-2)){
	        content += '<li class="page-item blank">  </li>';
	        content += '<li class="page-item blank">  </li>';
	 	}
	  
	    content += '</ol>';

	    $(".board-list-paging").html(content);
	    
		}
		
		
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
		
		$(document).on("click", ".pagination li.page-item", function(e) {
		    e.preventDefault();
		    let page = parseInt($(this).attr('data-page'));
		    console.log(page)
		    getList(page);
		});



	
		
	});

	// 현재 페이지 구하는 함수
	function getCurrentPage() {
	    return parseInt($(".nowPage").attr("data-page"));
	}

</script>
<script type="text/javascript">
function reportWrite() {
	location.href="reportWrite";
}
console.log(${adminUser});
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
<div id="bigbox">
    <div id="list_maint">신고게시판</div>
    <div>
        <table id="listtable">
            <thead>
                <tr class="list_title" style="width: 100%;">
                    <th>번호</th><th>아이디</th><th>제목</th><th>작성일자</th><th>확인여부</th>
                </tr>
            </thead>
            <tbody id="tbody">
            	
            </tbody>
        </table>
    </div>
    <div class="board-list-paging"></div>
    
    <div id="bwbtn">
    	<input type="hidden" name="cPage" value="${paging.nowPage}+${paging.endBlock}+ ${paging.beginBlock}+${paging.totalPage}">
    	<c:if test="${memberUser != null}">
    	<button onclick="reportWrite()" class="reportwbtn">글쓰기</button>
    	</c:if>
    </div>
    
</div>
	<div id="empty-area" ></div>
<%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</html>