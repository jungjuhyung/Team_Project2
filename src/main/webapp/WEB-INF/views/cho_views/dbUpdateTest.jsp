<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	function updateDB() {
		$.ajax({
			url : "updateTest",
			type : "post",
			dataType : "json",
			success : function(data) {
				alert("업데이트 성공")
			},
			error : function() {
				alert("실패");
			}
		});
	}
</script>
</head>
<body>
	<input type="button" onclick="updateDB()" value = "DB 동기화">
	
</body>
</html>