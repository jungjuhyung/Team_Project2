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
/* 
	$(function() {
		$("#detail").empty();
		let contentId = "${contentid}";
		let contenttypeId = "${contenttypeid}";
		console.log(contentId);
		console.log(contenttypeId);
		$.ajax({
			url : "ko_detailCommon1.do",
			data : {
				contentId : contentId,
				contenttypeId : contenttypeId
			},
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data);
				let common = '<h2>공통 정보 조회</h2>';
				common += '<ul class="detail_item">';
				$.each(data.response.body.items.item, function(index, value) {
					common += '<li>addr1 : ' + value.addr1 + '</li>'
							+ '<li>mapx : ' + value.mapx + '</li>'
							+ '<li>mapy : ' + value.mapy + '</li>'
							+ '<li>overview : ' + value.overview + '</li>'
							+ '<li>title : ' + value.title + '</li>';
				})
				common += '</ul>';
				$("#detail").append(common);
			},
			error : function() {
				alert("실패");
			}
		});

		$.ajax({
			url : "ko_detailIntro1.do",
			data : {
				contentId : contentId,
				contenttypeId : contenttypeId
			},
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data);
				let intro = '<h2>소개 정보 조회</h2>';
				intro += '<ul class="detail_item">';
				$.each(data.response.body.items.item, function(index, value) {
					if (value.contenttypeid == '12') {
						intro += '<li>expguide : ' + value.expguide + '</li>'
								+ '<li>infocenter : ' + value.infocenter
								+ '</li>' + '<li>parking : ' + value.parking
								+ '</li>' + '<li>restdate : ' + value.restdate
								+ '</li>' + '<li>usetime : ' + value.usetime
								+ '</li>';
					} else if (value.contenttypeid == '15') {
						intro += '<li>eventplace : ' + value.eventplace
								+ '</li>' + '<li>eventstartdate : '
								+ value.eventstartdate + '</li>'
								+ '<li>eventenddate : ' + value.eventenddate
								+ '</li>' + '<li>playtime : ' + value.playtime
								+ '</li>' + '<li>sponsor1 : ' + value.sponsor1
								+ '</li>'
						'<li>sponsor1tel : ' + value.sponsor1tel + '</li>'
						'<li>usetimefestival : ' + value.usetimefestival
								+ '</li>';
					} else {
						intro += '<li>firstmenu : ' + value.firstmenu + '</li>'
								+ '<li>treatmenu : ' + value.treatmenu
								+ '</li>' + '<li>infocenterfood : '
								+ value.infocenterfood + '</li>'
								+ '<li>opentimefood : ' + value.opentimefood
								+ '</li>' + '<li>restdatefood : '
								+ value.restdatefood + '</li>';
					}
				})
				intro += '</ul>';
				$("#detail").append(intro);
			},
			error : function() {
				alert("실패");
			}
		});
	});
 */	
</script>
</head>
<body>
	<h1>상세페이지</h1>
		<div>
			<ul>
				<h2>공통</h2>
				<li>addr1 : ${itemVO.addr1}</li>
				<li>homepage : ${itemVO.homepage}</li>
				<li>mapx : ${itemVO.mapx}</li>
				<li>mapy : ${itemVO.mapy}</li>
				<li>title : ${itemVO.title}</li>
				<li>firstimage : ${itemVO.firstimage}</li>
				<li>overview : ${itemVO.overview}</li>
				<h2>소개</h2>
				<c:if test="${itemVO.contenttypeid == 12}">
					<li>expguide : ${itemVO.expguide}</li>
					<li>infocenter : ${itemVO.infocenter}</li>
					<li>parking : ${itemVO.parking}</li>
					<li>restdate : ${itemVO.restdate}</li>
					<li>usetime : ${itemVO.usetime}</li>
				</c:if>
				<c:if test="${itemVO.contenttypeid == 15}">
					<li>eventplace : ${itemVO.eventplace}</li>
					<li>eventstartdate : ${itemVO.eventstartdate}</li>
					<li>eventenddate : ${itemVO.eventenddate}</li>
					<li>playtime : ${itemVO.playtime}</li>
					<li>sponsor1 : ${itemVO.sponsor1}</li>
					<li>sponsor1tel : ${itemVO.sponsor1tel}</li>
					<li>usetimefestival : ${itemVO.usetimefestival}</li>
				</c:if>
				<c:if test="${itemVO.contenttypeid == 39}">
					<li>firstmenu : ${itemVO.firstmenu}</li>
					<li>treatmenu : ${itemVO.treatmenu}</li>
					<li>infocenterfood : ${itemVO.infocenterfood}</li>
					<li>opentimefood : ${itemVO.opentimefood}</li>
					<li>restdatefood : ${itemVO.restdatefood}</li>
				</c:if>
			</ul>
		</div>
	<section style="margin: 0 auto; width: 1300px;">
	</section>

</body>
</html>