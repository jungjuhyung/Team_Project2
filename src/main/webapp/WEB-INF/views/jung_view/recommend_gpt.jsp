<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function per_gpt() {
	    $.ajax({
	        // Open API에서 제공하는 GPT 사용을 위한 url 주소
	        url: 'perbot',
	        type: 'POST',
	        dataType: 'json',
	        success: function(data) {
	        	console.log(data);
	            let container = $('#gpt_res');
	            data.forEach(function(item) {
	                let div = $('<div class="item"></div>');
	                let img = $('<img>').attr('src', item.firstimage);
	                let areaCode = $('<p></p>').text('Area Code: ' + item.areacode);
	                let contentTypeId = $('<p></p>').text('Content Type ID: ' + item.contenttypeid);
	                let title = $('<p></p>').text('Title: ' + item.title);
	                
	                div.append(img);
	                div.append(areaCode);
	                div.append(contentTypeId);
	                div.append(title);
	                
	                container.append(div);
				})
	        },
	        error: function(data) {
	        	console.log(data);
		 		let container = $('#gpt_res');
        		let error = $('<p></p>').text('요청이 많아 잠시후 다시 시도해주세요.');
        		container.append(error);
	        }
	    });
	}
	function re_recommend() {
	    $.ajax({
	        // Open API에서 제공하는 GPT 사용을 위한 url 주소
	        url: 're_recommend',
	        type: 'POST',
	        dataType: 'json',
	        success: function(data) {
	        	console.log(data);
	            let container = $('#gpt_res');
	            container.empty()
	            data.forEach(function(item) {
	                let div = $('<div class="item"></div>');
	                let img = $('<img>').attr('src', item.firstimage);
	                let areaCode = $('<p></p>').text('Area Code: ' + item.areacode);
	                let contentTypeId = $('<p></p>').text('Content Type ID: ' + item.contenttypeid);
	                let title = $('<p></p>').text('Title: ' + item.title);
	                
	                div.append(img);
	                div.append(areaCode);
	                div.append(contentTypeId);
	                div.append(title);
	                
	                container.append(div);
				})
	        },
	        error: function(data) {
	        	console.log(data);
		 		let container = $('#gpt_res');
		        container.empty()
        		let error = $('<p></p>').text('요청이 많아 잠시후 다시 시도해주세요.');
        		container.append(error);
	        }
	    });
	}
	per_gpt()
</script>
<style>
#gpt_res {
    display: flex;
    flex-wrap: wrap;
}
.item {
    margin: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    width: 200px;
    box-sizing: border-box;
}
.item img {
    max-width: 100%;
    height: auto;
}
</style>
</head>
<body>
	<div id="gpt_res"></div>
	<button onclick="re_recommend()">다시 추천받기</button>
</body>
</html>