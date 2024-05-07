<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/cho_css/category.css">
<meta charset="UTF-8">
<title>지역별 여행경로 추천</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	let areaCode ="";
	let areaName ="";
	$(document).ready(function() {
		// 지역 누르면 실행
	    $(".swipeAreaList").click(function() {
	       areaCode = $(this).attr('data-areacode')
	       areaName = $(this).find('span').text();
	       searchAreaPlace(areaCode,areaName)
	    });   
	    $(".SearchTitleView").click(function() {
	      	location.href = "searchResult"
	    });   
	    searchAreaPlace("1","서울")
	});
	
	//  찜 버튼 누르기
	$(document).on("click", ".heart-state", function(e) {
		let contentid = $(this).data("place_contentid");
		let userLogin = document.getElementById("userLogin").value;
		if (userLogin !== null && userLogin === "ok") {
			if ($(this).hasClass("wish-added")) {
				placeWishRemove(this, contentid);
			}else {
				placeWishadd(this, contentid);
			}
			
		}else{
			alert("찜하기를 하시려면 로그인을 해주세요.")
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
					$(tag).text("❤️");
					alert("좋아요를 눌렀습니다.")
					if(areaCode === "" || areaName ===""){
						areaCode = "1";
						areaName = "서울";
					}
					searchAreaPlace(areaCode,areaName)
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
					alert("좋아요를 취소하셨습니다.")
					if(areaCode === "" || areaName ===""){
						areaCode = "1";
						areaName = "서울";
					}
					searchAreaPlace(areaCode,areaName)
				},
				error : function() {
					alert("실패");
				}
			});
		
	}
	function searchAreaPlace(areaCode,areaName) {
		$('.areaName').empty();
		$('.areaName').text(areaName);
		$.ajax({
			url : "searchAreaPlace",
			type : "post",
			data : {
				areaCode: areaCode,
				areaName : areaName
				},
			dataType : "json",
			success : function(data) {
				console.log(data)
				$('.place-Wrapper').empty();
				for (let i = 0; i < data.touristList.length; i++) {
          	        let place = data.touristList[i];
          	        addPlace2(place.contenttypeid,place);
          	    	}
				for (let i = 0; i < data.partyList.length; i++) {
          	        let place = data.partyList[i];
          	        addPlace2(place.contenttypeid,place);
          	    	}
				for (let i = 0; i < data.restaurantList.length; i++) {
          	        let place = data.restaurantList[i];
          	        addPlace2(place.contenttypeid,place);
          	    	}
			},
			error : function() {
				alert("실패");
			}
		});
	}
	
	function addPlace2(contenttypeid,place) {
   		let originalTitle  = place.title;
   		let truncatedTitle = originalTitle.length > 12 ? originalTitle.substring(0, 12) + '..' : originalTitle;
		let heartIcon = '';
		if(place.uheart === "1") {
		    heartIcon = '<span class="heart-state wish-added" data-place_contentid="' + place.contentid + '">' + '❤️' + '</span>';
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
	   	                        heartIcon + '<span class="heart-count">'  + place.heart + '</span>'+ 
	   	                    '</div>' +
   	                    '</div>';
   	    if(contenttypeid ==="12"){
   	    	$('#touristPlace').append(placeHTML);
   	    }
   	    if(contenttypeid ==="15"){
   	    	$('#partyPlace').append(placeHTML);
   	    }
   	    if(contenttypeid ==="39"){
   	    	$('#restaurantPlace').append(placeHTML);
   	    }
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
	<%@ include file="../header.jsp"%>
	<section>
		<div class="swipe-Wrapper">
			<div class="swipe-arealist">
				<div id="area1" class="swipeAreaList" data-areacode="1"><img src="" ><span>서울</span></div>
				<div id="area2" class="swipeAreaList" data-areacode="2"><img src="" ><span>인천</span></div>
				<div id="area3" class="swipeAreaList" data-areacode="3"><img src="" ><span>대전</span></div>
				<div id="area4" class="swipeAreaList" data-areacode="4"><img src="" ><span>대구</span></div>
				<div id="area5" class="swipeAreaList" data-areacode="5"><img src="" ><span>광주</span></div>
				<div id="area6" class="swipeAreaList" data-areacode="6"><img src="" ><span>부산</span></div>
				<div id="area7" class="swipeAreaList" data-areacode="7"><img src="" ><span>울산</span></div>
				<div id="area8" class="swipeAreaList" data-areacode="8"><img src="" ><span>세종</span></div>
				<div id="area31" class="swipeAreaList" data-areacode="31"><img src="" ><span>경기</span></div>
				<div id="area32" class="swipeAreaList" data-areacode="32"><img src="" ><span>강원</span></div>
				<div id="area33" class="swipeAreaList" data-areacode="33"><img src="" ><span>충북</span></div>
				<div id="area34" class="swipeAreaList" data-areacode="34"><img src="" ><span>충남</span></div>
				<div id="area35" class="swipeAreaList" data-areacode="35"><img src="" ><span>경북</span></div>
				<div id="area36" class="swipeAreaList" data-areacode="36"><img src="" ><span>경남</span></div>
				<div id="area37" class="swipeAreaList" data-areacode="37"><img src="" ><span>전북</span></div>
				<div id="area38" class="swipeAreaList" data-areacode="38"><img src="" ><span>전남</span></div>
				<div id="area39" class="swipeAreaList" data-areacode="39"><img src="" ><span>제주</span></div>
			</div>
			
			<div class="swipe-Main">
				<div class="areaName">{지역}</div>
				<div class="swipe-MainContent-Wrapper">
					<div class="swipe-MainContent"></div>
					<div class="swipe-MainContent"></div>
					<div class="swipe-MainContent"></div>
				</div>
				
			</div>
		 </div>
		 
		 <div id="thema-titleWrap" class="section"><div id="thema-title">우리 지역 핫플레이스</div> <div class = "SearchTitleView">상세 검색 하기</div></div>
		 <div id = "hot-place-back">
			 <div id = "hot-place-wrapper">
			 	<div id = thema-wrapper>
				 	<div class= "thema-box">
				 		<h4 id="tourist" class="thema-subtitle" > <span class="areaName">{지역}</span> 추천 맛집 경로</h4>
				 		<div id="touristPlace" class="place-Wrapper"> 
				 		</div>
				 	</div>
				 	<div class= "thema-box">
				 		<h4 id="party" class="thema-subtitle"><span class="areaName">{지역}</span> 추천 축제</h4>
				 		<div id="partyPlace"  class="place-Wrapper "></div>
				 	</div>
				 	<div class= "thema-box" >
				 		<h4 id="restaurant" class="thema-subtitle"><span class="areaName">{지역}</span> 추천 맛집</h4>
				 		<div id="restaurantPlace"  class="place-Wrapper"></div>
				 	</div>
				 </div>
			 </div>
		 </div>
			
	</section>
	<input type="hidden" id="userLogin" value="${userLogin}">
</body>
</html>