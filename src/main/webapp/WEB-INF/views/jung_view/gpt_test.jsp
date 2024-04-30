<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#btn").on("click", function() {
		$.ajax({
			url :  "gpttest" ,      // 서버주소
			method : "post",          // 전달방식
			dataType : "json",         // 가져오는 결과 타입
			// data  :                // 서버에 보낼때 같이 가는 데이터 (파라미터)
		    // async : true 또는 false  // 비동기(기본, 생략, true), 동기=false
		    success: function(data) {
				console.log(data)
		    },
		    error : function() {
				alert("읽기실패");
			}
		});
	});
})
</script>
</head>
<body>
	<button id="btn">json01</button>
</body>
</html>