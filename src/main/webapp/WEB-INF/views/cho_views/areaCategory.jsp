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
		
	});

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
	function search(page) {
        let areaCode = $("#areaCodes").val();
        let sigunguCode = $("#sigunguCode").val();
        let contentType = $("#contentTypes").val();
        let title = $(".searchTitle").val();
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
                title: title
            },
            success: function(data) {
            	$('#place_wrapper').empty();
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
	
	// 페이징 처리 함수
	function updatePagination(paging) {
	    var content = '';
	    content += '<ol class="pagination" id="pagination">';
	    if (paging.beginBlock > 1) {
	        content += '<li class="page-item" data-page="' + (paging.beginBlock - 1) + '">이전</li>';
	    }

	    for (var i = paging.beginBlock; i <= paging.endBlock; i++) {
	    	if(i === paging.nowPage){
	        content += '<li class="page-item nowPage" data-page="' + i + '">'+ i + '</li>';
	    	}else{
	        content += '<li class="page-item" data-page="' + i + '">'+ i + '</li>';
	    	}
	    }

	    if (paging.endBlock < paging.totalPage) {
	        content += '<li class="page-item" data-page="' + (paging.endBlock + 1) + '">다음</li>';
	    }

	    content += '</ol>';

	    $(".board-list-paging").html(content);
	}
	
   	// 각 장소 정보를 HTML로 변환하여 추가하는 함수
   	function addPlace(place) {
   	    let placeHTML = '<div class="place-box" onclick="goProductDetail(' + place.contentid + ', ' + place.contenttypeid + ')">' +
   	                        '<div class="image-box">' +
   	                            '<img alt="' + place.title + '" src="' + place.firstimage + '">' +
   	                        '</div>' +
   	                        '<div class="text-box">' +
   	                             place.title +
   	                        '</div>' +
   	                    '</div>';
   	    $('#place_wrapper').append(placeHTML);
   	}
   	function goProductDetail(contentid, contenttypeid){
        Location.href = "";
    }
</script>
</head>
<body>
	<section style="width: 1300px; margin: 0 auto;">
		<form class="areaSearchForm" method="post">
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
		</form>
		<hr>
		<div id = "place_wrapper">
		</div>
		
		<div class="board-list-paging"></div>
	</section>

</body>
</html>