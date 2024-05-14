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
					$(tag).text("♥");
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
				areaCode: areaCode
				},
			dataType : "json",
			success : function(data) {
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
		    heartIcon = '<span class="heart-state wish-added" data-place_contentid="' + place.contentid + '">' + '♥' + '</span>';
		} else {
		    heartIcon = '<span class="heart-state" data-place_contentid="' + place.contentid + '">' + '♡' + '</span>';
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
	
	document.addEventListener('DOMContentLoaded', function () {
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
	});
	document.addEventListener('DOMContentLoaded', function () {
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
	});

	
</script>

</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp"%>
	<section>
		<div class="swipe-Wrapper ">
			<div class = "swiper mySwiper">
				<div class="swipe-arealist swiper-wrapper">
					<div id="area1" class="swipeAreaList swiper-slide" data-areacode="1"><img src="https://i.namu.wiki/i/jARUtzne9YECXOWhedDJykQHu6lgKKAz-fOHrkN8HJN_jVyPtofDLTUAHQvEA7P7YsCT8QlTePhjlP1mZis9WOPkCPKmseD4AOqWt6tlu-pBA7EkXJcWSqK_Vd-zi7gl_TMqD6qGOvXux5evLYojTQ.svg" ><span>   서울</span></div>
					<div id="area2" class="swipeAreaList swiper-slide" data-areacode="2"><img src="https://i.namu.wiki/i/SoDp4zf4o92jz_4HiQ3MeENd99xXTVPwzppyG5y5h35zUypql9bsAZsJHeMKi5mPB4QKaBVYTDsu2UoHCOHW-3dPqkoxDScIXZDgyMRXf0MNBp0iu-hlAYottFMK7VG82bjXXD_nmhEN75tLtUVClw.svg" ><span>   인천</span></div>
					<div id="area3" class="swipeAreaList swiper-slide" data-areacode="3"><img src="https://i.namu.wiki/i/lLE4uCXjSWEaJ1Vq2lPmt4qgHxnxC0BeQdXwcuUJADLcBLY06UfgP32NfsJ3BPVMoatighwmulxAZLQS8LafbwMRlLvhc7mMDdMrZu3sGGuMBCK9LC11kDYV4QHSmcidBZbHmoi427ZyN_8nvOscsg.svg" ><span>   대전</span></div>
					<div id="area4" class="swipeAreaList swiper-slide" data-areacode="4"><img src="https://i.namu.wiki/i/b6c2iEZvl_dDDGKBUgzaIDeMtx5Mmn7Zgv6mVhvFIG3ItzSqitXaS8iWLjUDoAz5oTF_xC0y5ORcKrLJl3WgqWF1rMspsl4_dYK-601UNcVX4uzp6zdvZ4xYSgRHelGtu1US_bA96V-I7kFcHsh5uQ.svg" ><span>   대구</span></div>
					<div id="area5" class="swipeAreaList swiper-slide" data-areacode="5"><img src="https://i.namu.wiki/i/cTZ_5YWiump0ElkRrc6TANaaaQOBhU6elurOrDfahzGNTxJZ4eRpCcLl9pK9rTgI7hx4WyDc5K6NbLinU3IrSWKY2rHtOkSxAQt8uJ6bqYwd6Ep_O8uRMoL5-RqynU-inc5hQ9rrykkhR89Q1zNpSg.svg" ><span>   광주</span></div>
					<div id="area6" class="swipeAreaList swiper-slide" data-areacode="6"><img src="https://i.namu.wiki/i/2SroXafXU-mPcl65oCulV06tJ941AFEdRi47bgbUiyPVneYinmBPLem0e33XgDfJ3LHyKiMNnRkccbvpiAIDzXEDWapyEifpGjG1dt4Ynp5siEQOo5aGn3NvkJdd4Jln82mfxYsgTQvXBqkG9ZcDxQ.svg" ><span>   부산</span></div>
					<div id="area7" class="swipeAreaList swiper-slide" data-areacode="7"><img src="https://i.namu.wiki/i/sLd6VmoMG9MmZXCNngCa20Al5pZ9EHxrrvubidq8rTWhKBjUnANpgex6x5JWpQsucc-SDPii_fIUFTpEhi-Z7fdq0g1PjUKJV6GS17h3lxlsz-i_bRWwhHJgEnfTImcqA6-z-aZgZrJoAUQ2uIG-Pw.svg" ><span>   울산</span></div>
					<div id="area8" class="swipeAreaList swiper-slide" data-areacode="8"><img src="https://i.namu.wiki/i/wIy4DDuuPVku02uxZCUEeWGNTro1Ez2FboffWNPGjFgm88_AG_hvKIWi--CuHeVgW4uBCvfrxJlby1jnb_EunWWJJtfPskiphABWeuQ6K2yV4G-cIKlrM8DhmSVBPzFTY8TvIOey6WhvtCxIneXHDw.svg" ><span>   세종</span></div>
					<div id="area31" class="swipeAreaList swiper-slide" data-areacode="31"><img src="https://i.namu.wiki/i/maPlRWUUrlaBU7Gltn-AM_36TQNI1Wg-SAYeH3rHtUvSaMn3L_7xCEAyRWDhLF9MXcl_e83q45LydZSrRR73BbwPQeOUwqkPw26_K2hVpG3CjbTluMR4u6y4G7ot6NmdMWHcDOBnTiwwppB_2hdK2Q.svg" ><span>   경기</span></div>
					<div id="area32" class="swipeAreaList swiper-slide" data-areacode="32"><img src="https://i.namu.wiki/i/UTqPYLcJXcIlgfuRTQUuW3HegUPW7W8c9ndx-084NPJIuzbFU3zp5HEdzz4VsWBrV1loN65uLo2EB8BImu3LiuOfZwnSEr4a3OIIWLNlm-66KJu3KK_au3TU8c-TzalQssRqJtjueT2iE4tqJu-Fuw.svg" ><span>   강원</span></div>
					<div id="area33" class="swipeAreaList swiper-slide" data-areacode="33"><img src="https://i.namu.wiki/i/z3iAx04HLAzjZHZscNwk8HDHetHaCNVETP1p17SnwTiJfbqamV38BtU5dWVYkNlnEbLUCN6o32pwUAG5DLEU4A.svg" ><span>   충북</span></div>
					<div id="area34" class="swipeAreaList swiper-slide" data-areacode="34"><img src="https://i.namu.wiki/i/qfy_Pb5pLFZpF-Tlztud-1hxB5UGpy-UZ17crIY9_ZmYM8VKSlbA4r0svoQzTWn0fayvzhzJnFu9FWcAuoy0oYW1vA_IPkpjPlcbrvBNq1PFelF2E10aWsTOE4aV5uSFwPrqXRwFbqZANzSt3ztXQg.svg" ><span>   충남</span></div>
					<div id="area35" class="swipeAreaList swiper-slide" data-areacode="35"><img src="https://i.namu.wiki/i/0vAy9uI8ZUSgWZbn4sbHqsRqYDc3v-YWyTWm0MiY0rLY5IRgROP8ap6axfTm08SVWg9FJ1e7jjjXsZzOo2kweIx1Bg1cf1GpvHjIgEv_6BO0NATXHL1k0cv_cJun7_Wxuo6c7nYffbIUbWGXE84C_Q.svg" ><span>   경북</span></div>
					<div id="area36" class="swipeAreaList swiper-slide" data-areacode="36"><img src="https://i.namu.wiki/i/xcWiuA-Yd3lbiU2LQy_TVGyqTppRfxkP10lgAlGOnfa04x4qDNwzaxoXtvfyADfs60vP6x9z6XiFuqAd5JeGOPmtQ3ap2EUe_iqoRyTTUmr3lOP2NoevmU30lqZeE92HKiAv5m4hMqCPpevvDbowYA.svg" ><span>   경남</span></div>
					<div id="area37" class="swipeAreaList swiper-slide" data-areacode="37"><img src="https://i.namu.wiki/i/kEsSdu5QRw9sbD4wPLjTdbHLbYOuwi4Jbl-LuPLc4ZFrqE2ldBvc3wy8SCCmqWtte3dei7MZpTnu0cE6qrHEWB_zdL6jEZt5fM3NLXiGv5FY9kyh5yxGdGLP6T-o04BCVsIMYDtz4z-vMyVrLalhZQ.svg" ><span>   전북</span></div>
					<div id="area38" class="swipeAreaList swiper-slide" data-areacode="38"><img src="https://i.namu.wiki/i/HkFBzr8gGv3VoP7FWzdwFwXrSsv7svoL2RJcxEBHlOONJhpSYEIbxGSs6oQkRgI7VvokmZ93bc4rRT7U_7ry_ZG_rFon3-swjSS_ZeZzm2H2ZY8ye08bz5-zZtz2HuViU2fOXjhjg9TkwmkEOKF4yw.svg" ><span>   전남</span></div>
					<div id="area39" class="swipeAreaList swiper-slide" data-areacode="39"><img src="https://i.namu.wiki/i/UxR-PNtgz8QRi4-YI4TrLpco0uHn0el5B7XVU7MQjRmzrg8-6b1suPoBF3fKqkpDKYxv8V2VkpqqlemErZHzDy_DwkjINeP1gdIrIe9FeRznPLakDm87XEW1GnOdR1qQ-fdXEMxtn0AUbfriexATqg.svg" ><span>   제주</span></div>
				</div>
				
				<div class="swiper-button-prev"></div>
   				<div class="swiper-button-next"></div>
			</div>
			<div class="swipe-Main">
				<div class="areaName"></div>
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