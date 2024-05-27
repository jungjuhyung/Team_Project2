<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/cho_css/category.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
<meta charset="UTF-8">
<title>테마별 장소 추천</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script type="text/javascript">
	let areaCode ="";
	let areaName ="";
	$(document).ready(function() {
		// 지역 누르면 실행
	    $(".swipeAreaList").click(function() {
	       areaCode = $(this).attr('data-areacode')
	       areaName = $(this).find('p').text();
	       searchAreaPlace(areaCode,areaName)
	    });   
	    $(".SearchTitleView").click(function() {
	      	location.href = "searchResult"
	    });   
	    searchAreaPlace("1","서울")
	    
	    
    });
	function initializeSwiper1() {
		let mySwiper = new Swiper(".mySwiper", {
	    	slidesPerView: 9, // 한 번에 표시할 슬라이드 수
	    	spaceBetween: 20, // 슬라이드 간의 간격  
	        loop: true, // 슬라이드 루프(무한 회전) 활성화
	        navigation: {
	            nextEl: ".swiper-button-next",
	            prevEl: ".swiper-button-prev"
	        },
	        watchOverflow: true, // 슬라이드가 화면을 넘어갈 때의 처리 설정
	    });
	}
	function initializeSwiper2() {
		 let mySwiper = new Swiper(".mySwiper2", {
		    	slidesPerView: 5, // 한 번에 표시할 슬라이드 수
		    	spaceBetween: 20, // 슬라이드 간의 간격  
		        loop: true, // 슬라이드 루프(무한 회전) 활성화
		        navigation: {
		            nextEl: ".swiper-button-next",
		            prevEl: ".swiper-button-prev"
		        },
		        watchOverflow: true, // 슬라이드가 화면을 넘어갈 때의 처리 설정
		});
	}

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
					$(tag).html('<img src="resources/ko_images/heart_on3.png">');
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
					 $(tag).html('<img src="resources/ko_images/heart_off2.png">');
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
				areaCode: areaCode
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
				for (let i = 0; i < data.randomList.length; i++) {
          	        let place = data.randomList[i];
          	        addPlace2("99",place);
          	    }
				initializeSwiper1();
				initializeSwiper2();
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
		    heartIcon = '<span class="heart-state wish-added" data-place_contentid="' + place.contentid + '">' + '<img src="resources/ko_images/heart_on3.png" >' + '</span>';
		} else {
		    heartIcon = '<span class="heart-state" data-place_contentid="' + place.contentid + '">' + '<img src="resources/ko_images/heart_off2.png" >' + '</span>';
		}
			
   	    let placeHTML = '<div class="place-box swiper-slide" >' +
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
   	    if(contenttypeid ==="99"){
   	    	$('#randomList').append(placeHTML);
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
	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
	<%@ include file="/WEB-INF/views/cho_views/sideBar.jsp"%>
	<section>
		<div class="swipe-Wrapper ">
			<div class = "swiper mySwiper">
				<div class="swipe-arealist swiper-wrapper">
					<div id="area1" class="swipeAreaList swiper-slide" data-areacode="1"><img src="resources/cho_images/Seoul.svg" ><p>서울</p></div>
					<div id="area2" class="swipeAreaList swiper-slide" data-areacode="2"><img src="resources/cho_images/Incheon.svg" ><p>인천</p></div>
					<div id="area3" class="swipeAreaList swiper-slide" data-areacode="3"><img src="resources/cho_images/Daejeon.svg" ><p>대전</p></div>
					<div id="area4" class="swipeAreaList swiper-slide" data-areacode="4"><img src="resources/cho_images/Daegu.svg" ><p>대구</p></div>
					<div id="area5" class="swipeAreaList swiper-slide" data-areacode="5"><img src="resources/cho_images/Gwangju.svg" ><p>광주</p></div>
					<div id="area6" class="swipeAreaList swiper-slide" data-areacode="6"><img src="resources/cho_images/busan.svg" ><p>부산</p></div>
					<div id="area7" class="swipeAreaList swiper-slide" data-areacode="7"><img src="resources/cho_images/Ulsan.svg" ><p>울산</p></div>
					<div id="area8" class="swipeAreaList swiper-slide" data-areacode="8"><img src="resources/cho_images/Sejong.svg" ><p>세종</p></div>
					<div id="area31" class="swipeAreaList swiper-slide" data-areacode="31"><img src="resources/cho_images/Gyeonggi.svg" ><p>경기</p></div>
					<div id="area32" class="swipeAreaList swiper-slide" data-areacode="32"><img src="resources/cho_images/Gangwon.svg" ><p>강원</p></div>
					<div id="area33" class="swipeAreaList swiper-slide" data-areacode="33"><img src="resources/cho_images/Chungcheongbuk.svg" ><p>충북</p></div>
					<div id="area34" class="swipeAreaList swiper-slide" data-areacode="34"><img src="resources/cho_images/Chungcheongnam.svg" ><p>충남</p></div>
					<div id="area35" class="swipeAreaList swiper-slide" data-areacode="35"><img src="resources/cho_images/Gyeongsangbuk.svg" ><p>경북</p></div>
					<div id="area36" class="swipeAreaList swiper-slide" data-areacode="36"><img src="resources/cho_images/Gyeongsangnam.svg" ><p>경남</p></div>
					<div id="area37" class="swipeAreaList swiper-slide" data-areacode="37"><img src="resources/cho_images/Jeollabuk.svg" ><p>전북</p></div>
					<div id="area38" class="swipeAreaList swiper-slide" data-areacode="38"><img src="resources/cho_images/Jeollanam.svg" ><p>전남</p></div>
					<div id="area39" class="swipeAreaList swiper-slide" data-areacode="39"><img src="resources/cho_images/jeju.svg" ><p>제주</p></div>
				</div>
				
				<div class="swiper-button-prev"></div>
   				<div class="swiper-button-next"></div>
			</div>
			<div class="swipe-Main">
				<div class="areaName"></div>
				<div id= "randomList" class="place-Wrapper swipe-MainContent-Wrapper">
					<!-- <div  class="swipe-MainContent"></div> -->
				</div>
				
			</div>
		 </div>
		 
		 <div id="thema-titleWrap" class="section"><div id="thema-title">우리 지역 핫플레이스</div> <div class = "SearchTitleView">상세 검색 하기</div></div>
		 <div id = "hot-place-back">
			 <div id = "hot-place-wrapper">
			 	<div id = "thema-wrapper" >
					 	<div class= "thema-box mySwiper2">
					 		<h4 id="tourist" class="thema-subtitle" > <span class="areaName"></span> 추천 관광지</h4>
					 		<div id="touristPlace" class="place-Wrapper swiper-wrapper"> </div>
							<div class="swiper-button-prev"></div>
			   				<div class="swiper-button-next"></div>
					 	</div>
					 	<div class= "thema-box mySwiper2">
					 		<h4 id="party" class="thema-subtitle"><span class="areaName"></span> 추천 축제</h4>
						 		<div id="partyPlace"  class="place-Wrapper swiper-wrapper"></div>
								<div class="swiper-button-prev"></div>
				   				<div class="swiper-button-next"></div>
					 	</div>
					 	<div class= "thema-box mySwiper2" >
					 		<h4 id="restaurant" class="thema-subtitle"><span class="areaName"></span> 추천 맛집</h4>
					 		<div id="restaurantPlace"  class="place-Wrapper swiper-wrapper"></div>
								<div class="swiper-button-prev"></div>
				   				<div class="swiper-button-next"></div>
					 	</div>
				 </div>
			 </div>
		 </div>
			
	</section>
	<input type="hidden" id="userLogin" value="${userLogin}">

</body>
</html>