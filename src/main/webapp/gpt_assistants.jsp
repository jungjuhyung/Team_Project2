<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
let toollist = [{type: "file_search"}],
// GPT에 답변을 요구하는 ajax 함수
function gpt() {
	$.ajax({
		// Open API에서 제공하는 GPT 사용을 위한 url 주소
	    url: 'assitest',
	    type: 'POST',
	 	// ajax는 데이터를 보낼 때 String만 보내기 때문에 Json객체를 String으로 변환해서 보내야합니다.
	    data: JSON.stringify({
	    	name: "project2_assi",
	    	tools: toollist,
	        model: "gpt-4-turbo"
	    }),
	    success: function(data) {
	    	console.log("성공")
	    },
	    error: function(xhr, status, error) {
	        console.error('Error');
	    }
	});
}

$(document).ready(function() {
	//전송 버튼 클릭 이벤트 처리
	$("#user-input button").on('click', function(e) {
	    gpt();
	});
	

	
})
</script>
</head>
<body>
<div id="user-input">
	<button>전송</button>
</div>
</body>
</html>