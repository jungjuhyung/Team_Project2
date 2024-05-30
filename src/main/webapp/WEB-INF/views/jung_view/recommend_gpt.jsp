<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/jung_css/recommend_gpt.css">
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
	            	let div = $('<div class="item" onclick="gpt_detail(' + item.contentid + ', ' + item.contenttypeid + ')"></div>');
	                let div_img = $('<div class="path_image"></div>');
	                let img = $('<img>').attr('src', item.firstimage);
	                let title = '';
	                if (item.title.length >= 12) {
						title = '<div class="path_text">' + item.title.substring(0,12) + '...</div>';
					}else {
						title = '<div class="path_text">' + item.title + '</div>';
					}
	                
	                div_img.append(img);
	                div.append(div_img);
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
	                let div = $('<div class="item" onclick="gpt_detail(' + item.contentid + ', ' + item.contenttypeid + ')"></div>');
	                let div_img = $('<div class="path_image"></div>');
	                let img = $('<img>').attr('src', item.firstimage);
	                let title = '';
	                if (item.title.length >= 12) {
						title = '<div class="path_text">' + item.title.substring(0,12) + '...</div>';
					}else {
						title = '<div class="path_text">' + item.title + '</div>';
					}
	                
	                div_img.append(img);
	                div.append(div_img);
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

<script type="text/javascript">
	
	function gpt_detail(contentid, contenttypeid) {
		location.href = "ko_detail.do?contentid=" + contentid + "&contenttypeid=" + contenttypeid;
	}
	
</script>

</head>
<body>
	<div class="main_text">
		<h2>AI 맞춤 추천 지역
			<button class="gpt_btn" onclick="re_recommend()">다시 추천받기</button>
		</h2>
	</div>
	<div id="gpt_res"></div>
</body>
</html>