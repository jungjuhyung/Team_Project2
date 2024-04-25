<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 시군구 코드 가져오는 ajax
		function getList() {
			$("#sigunguCode").empty();
			let areaCode = $("#areaCodes").val();

			$.ajax({
				url : "sigunguCodeList",
				method : "get",
				dataType : "json",
				data : { areaCode : areaCode },
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
		// 지역 바뀔 때마다 시군구 가져오는 ajax 실행
		$("#areaCodes").change(function() { getList();});
	
		
		$("#areaSearchButton").click(function() {
            let areaCode = $("#areaCodes").val();
            let sigunguCode = $("#sigunguCode").val();
            let contentType = $("#contentTypes").val();

            // AJAX 요청 실행
            $.ajax({
                url: "areaSearchTourList",
                method: "post",
                dataType: "json",
                data: {
                    areaCode: areaCode,
                    sigunguCode: sigunguCode,
                    contentType: contentType
                },
                success: function(data) {
                	{
                	 $('#place_wrapper').empty();
                	    // 검색된 배열의 JSON 요소들을 반복하면서 처리
                	    for (let i = 0; i < data.length; i++) {
                	        let place = data[i];
                	        addPlace(place);
                	    }
                	}

                	// 각 장소 정보를 HTML로 변환하여 추가하는 함수
                	function addPlace(place) {
                	    let placeHTML = '<div class="place-box">' +
                	                        '<div class="image-box">' +
                	                            '<img alt="' + place.title + '" src="' + place.firstimage + '">' +
                	                        '</div>' +
                	                        '<div class="text-box">' +
                	                            '<h4>' + place.title + '</h4>' +
                	                        '</div>' +
                	                    '</div>';
                	    $('#place_wrapper').append(placeHTML);
                	}
                },
                error: function() {
                }
            });
        });
		
		getList();
	})
</script>
</head>
<body>
	<section style="width: 1300px; margin: 0 auto;">
	<form id="areaSearchForm" method="post" style="width: 400px; margin: 0 auto;">
		<select id="areaCodes">
			<option value="1" selected>서울</option>
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
		</select> <select id="sigunguCode">
		</select> <select id="contentTypes">
			<option value="12">관광지</option>
			<option value="15">행사/공연/축제</option>
			<option value="39">음식점</option>
		</select>
		<input type="button" value="검색" id = "areaSearchButton">
	</form>
	<hr>
	<div id = "place_wrapper">
		
	</div>
</section>

</body>
</html>