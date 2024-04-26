<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#detail").empty();
		let contentId = "${contentid}";
		let contenttypeId = "${contenttypeid}";
		console.log(contentId);
		$.ajax({
			url : "ko_ajax_detail.do",
			data : {contentId : contentId , contenttypeId : contenttypeId},
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data);
			},
			error : function() {
				alert("실패");
			}
		});
	});
</script>
</head>
<body>
	
	<h1>상세페이지</h1>
	<section style="margin: 0 auto; width: 1300px;">
		<div id="detail"></div>
	</section>
	
	
</body>
</html>