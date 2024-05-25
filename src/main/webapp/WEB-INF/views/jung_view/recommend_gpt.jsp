<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<script type="text/javascript">
function per_gpt() {
	$.ajax({
		// Open API에서 제공하는 GPT 사용을 위한 url 주소
	    url: 'perbot',
	    type: 'POST',
	    dataType: "json"
	    success: function(data) {
	    	console.log("성공")
	    },
	    error: function(xhr, status, error) {
	        console.error('Error');
	    }
	});
}
</script>
</head>
<body>
	
</body>
</html>