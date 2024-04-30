<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/cho_css/tourListCategory.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    getList();
	    search(1); // 초기 검색은 첫 페이지로 시작

	    // 지역 바뀔 때마다 시군구 가져오는 ajax 실행
	    $("#areaCodes").change(function() {
	        getList(); 
	    });   
	   
	    // 서치 버튼 누르면 실행
	    $(".SearchButton").click(function() {
	        search(1); // 검색 실행하면서 첫 페이지로 이동
	    });    
	
		// 타이틀 검색 엔터 처리
		document.querySelector('.areaSearchForm').addEventListener('keypress', function(e) {
		    if (e.key === 'Enter') {
		        e.preventDefault(); // 기본 제출 동작 방지
		        document.querySelector('.SearchButton').click(); // SearchButton 클릭
		    }
		});
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
		

	});
	
	//  뷰 옵션 이벤트 처리
	$(document).on("click", ".heart-state", function(e) {
		let contentid = $(this).data("place_contentid");
		if ($(this).hasClass("wish-added")) {
			placeWishRemove(this, contentid);
		}else {
			placeWishadd(this, contentid);
		}
	});
	

	// 장소 찜하기
	function placeWishadd(tag, contentid) {
		
			$.ajax({
				url : "placeWishAdd",
				type : "post",
				data : {contentid: contentid},
				dataType : "text",
				success : function(data) {
					$(tag).addClass("wish-added");
					$(tag).text("♥");
					search(getCurrentPage());
				},
				error : function() {
					alert("실패");
				}
			});
		
	}
	
	// 장소 찜제거
	function placeWishRemove(tag, contentid) {
	
			$.ajax({
				url : "placeWishRemove",
				type : "post",
				data : {
					contentid: contentid,
					},
				dataType : "text",
				success : function(data) {
					$(tag).text("♡");
					$(tag).removeClass("wish-added");
					search(getCurrentPage());
				},
				error : function() {
					alert("실패");
				}
			});
		
	}


	// 시군구 가져오기
	function getList() {
		$("#sigunguCode").empty();
		let basic_option = $("<option>").val('999').text('전체');
		$("#sigunguCode").append(basic_option);
		$.ajax({
			url : "sigunguCodeList",
			method : "get",
			dataType : "json",
			data : { areaCode : $("#areaCodes").val() },
			success : function(data) {
				$.each(data.response.body.items.item, function(index, item) {
					let option = $("<option>").val(item.code).text(item.name);
					$("#sigunguCode").append(option);
				});
			},
			error : function() {
				alert("읽기 실패")
			}
		});
	}
	// 페이지 번호 클릭 이벤트 처리
	$(document).on("click", ".pagination li.page-item", function(e) {
	    e.preventDefault();
	    let page = parseInt($(this).attr('data-page'));
	    search(page); // 해당 페이지 검색 실행
	});
	
	// 페이지 검색 이동
	$(document).on("click", ".pageMoveButton", function(e) {
	    e.preventDefault();
	    let page = $(".pageMove").val()
	    if (page.trim() === "") {
        page = getCurrentPage(); // 현재 페이지 가져오기
    	}
	    search(page); // 해당 페이지 검색 실행
	});
	
	//  뷰 옵션 이벤트 처리
	$(document).on("change", "#viewLimit", function(e) {
	    e.preventDefault();
	    let page = getCurrentPage(); // 현재 페이지 번호 가져오기
	    search(page); // search 함수 호출
	});
	
	
	
	// 현재 페이지 구하는 함수
	function getCurrentPage() {
	    return parseInt($(".nowPage").attr("data-page"));
	}
	
	function search(page) {
		
        let areaCode = $("#areaCodes").val();
        let sigunguCode = $("#sigunguCode").val();
        let contentType = $("#contentTypes").val();
        let title = $(".searchTitle").val();
        let limit = $('#viewLimit').val();
        // AJAX 요청 실행
        $.ajax({
            url: "areaSearchTourList",
            method: "post",
            dataType: "json",
            data: {
                areaCode: areaCode,
                sigunguCode: sigunguCode,
                contentType: contentType,
                page: page,
                title: title,
                limit: limit
            },
            success: function(data) {
            	$('#place_wrapper').empty();
            	
            	// 총 갯수 표시
            	getTotalRecord(data.paging)
            	
            	// 검색된 배열의 JSON 요소들을 반복하면서 처리
           	    for (let i = 0; i < data.choTourList.length; i++) {
           	        let place = data.choTourList[i];
           	        addPlace(place);
           	    }
            	// 페이징 처리
                updatePagination(data.paging);
            },
            error: function() {
            }
        });
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
        content += '<li class="page-item" data-page="' + 1 + '"> 1 </li>';
    }
    if (paging.beginBlock > 1) {
        content += '<li class="page-item" data-page="' + (paging.nowPage - 1) + '"> ... </li>';
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
	        content += '<li class="page-item" data-page="' + (paging.nowPage + 1) + '"> ... </li>';
	    }
	    if (paging.endBlock < paging.totalPage) {
	        content += '<li class="page-item" data-page="' + paging.totalPage + '"> '+ paging.totalPage +' </li>';
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
	
   	// 각 장소 정보를 HTML로 변환하여 추가하는 함수
   	function addPlace(place) {
   		let originalTitle  = place.title;
   		let truncatedTitle = originalTitle.length > 12 ? originalTitle.substring(0, 12) + '..' : originalTitle;
		let heartIcon = '';
		if(place.uheart === "1") {
		    heartIcon = '<span class="heart-state wish-added" data-place_contentid="' + place.contentid + '">' + '♥' + '</span>';
		} else {
		    heartIcon = '<span class="heart-state" data-place_contentid="' + place.contentid + '">' + '♡' + '</span>';
		}
			
   	    let placeHTML = '<div class="place-box" >' +
   	                        '<div class="image-box" onclick="goProductDetail(' + place.contentid + ', ' + place.contenttypeid + ')">' +
   	                            '<img alt="' + place.title + '" src="' + place.firstimage + '">' +
   	                        '</div>' +
   	                        '<div class="text-box" onmouseover="showFullTitle(this, \''+place.title+'\')" onmouseout="showTruncatedTitle(this, \''+truncatedTitle+'\')" onclick="goProductDetail(' + place.contentid + ', ' + place.contenttypeid + ')">' +
   	                     			truncatedTitle + 
   	                        '</div>' +
   	                        '<div class="wish-box">' +
	   	                        heartIcon + place.heart + 
	   	                    '</div>' +
   	                    '</div>';
   	    $('#place_wrapper').append(placeHTML);
   
   	}
	// 줄인 제목 전부 보기
   	function showFullTitle(element, originalTitle) {
   	    element.textContent = originalTitle;
   	}

   	// 기본 제목으로 되돌리기
   	function showTruncatedTitle(element,truncatedTitle) {
   	    element.textContent = truncatedTitle;
   	}
   	
   	function goProductDetail(contentid, contenttypeid){
        location.href = "ko_detail.do?contentid=" + contentid + "&contenttypeid=" + contenttypeid;
    }
   	
</script>
</head>
<body>
	<section style="width: 1300px; margin: 0 auto;">
	
		<div class="areaSearchForm">
			<select id="areaCodes" class = "searchSelect">
				<option value="999">전체</option>
				<option value="1">서울</option>
				<option value="6">부산</option>
				<option value="4">대구</option>
				<option value="2">인천</option>
				<option value="5">광주</option>
				<option value="3">대전</option>
				<option value="7">울산</option>
				<option value="8">세종</option>
				<option value="31">경기</option>
				<option value="32">강원</option>
				<option value="33">충북</option>
				<option value="34">충남</option>
				<option value="35">경북</option>
				<option value="36">경남</option>
				<option value="37">전북</option>
				<option value="38">전남</option>
				<option value="39">제주</option>
			</select> 
			<select id="sigunguCode" class = "searchSelect">
			</select> 
			<select id="contentTypes" class = "searchSelect">
				<option value="999">전체</option>
				<option value="12">관광지</option>
				<option value="15">행사/공연/축제</option>
				<option value="39">음식점</option>
			</select>
			<input type="text" class ="searchTitle" name = "title">
			<input type="button" value="검색" class = "SearchButton">
		</div>
		<hr>
		<div id = "result">
			<div id = "resultOption">
				<div id ="resultCount"></div>
				<!-- 보기 옵션 -->
				<select id = "viewLimit">
					<option value = "10">10개 보기</option>
					<option value = "20" selected>20개 보기</option>
					<option value = "30">30개 보기</option>
					<option value = "50">50개 보기</option>
					<option value = "100">100개 보기</option>
				</select>
			</div>
			<!-- 장소 표시 -->
			<div id = "place_wrapper"></div>
			<!-- 페이징 표시 -->
			<div class="board-list-paging"></div>
			<!-- 페이지 이동 -->
			<div class="pageMoveForm">
				<!-- <input type="text" class ="pageMove"> -->
				<input type="button" value="이동" class = "pageMoveButton">
			</div>
			
		</div>
	</section>

</body>
</html>