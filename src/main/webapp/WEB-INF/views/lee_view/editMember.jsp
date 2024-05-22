<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/lee_css/editMember.css">
<link rel="icon" href="/resources/ko_images/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function edit_success(f) {
		f.action = "my_edit_ok.do";
		f.submit();
	}
</script>
</head>
<body>
<div class="edit-container" style="width: 1300px; height:900px; margin: 0 auto;">
	<div class="edit_sub">
        <form class="edit-form" method="post">
            <h2>회원정보</h2>
            <ul class="list-form"> 
            <li class="input-group">
               	<input type="text" id="u_id" name="u_id" value="${mvo.u_id}" readonly>
            </li>
            <li class="input-group">
               	<input type="text" id="u_name" name="u_name" value="${mvo.u_name}" readonly>
               	<input type="hidden" name="n_status" value="0">
               	<input type="hidden" name="k_status" value="0">
            </li>
            <li class="input-group">
       			<input type="text" id="u_nickname" name="u_nickname" value="${mvo.u_nickname}" >
       		</li>
            <li class="input-group">
               	<input type="email" id="u_email" name="u_email" pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*" 
               	value="${mvo.u_email}" readonly>
            </li>
            <li class="input-group">
                	<select id="u_gender" name="u_gender"  disabled>
	                    <option value="">성별을 선택하세요</option>
	                    <option value="male" ${mvo.u_gender == 'male' ? 'selected' : '' }>남성</option>
	                    <option value="female" ${mvo.u_gender == 'female' ? 'selected' : '' }>여성</option>
                	</select>
            </li>
            
            <li class="input-group">
		               	<input type="date" id="u_birth" name="u_birth" value="${mvo.u_birth}" readonly>
		            </li>
            <li class="input-group">
               	<input type="text" id="u_self" name="u_self" value="${mvo.u_self }"  >
            </li>
            
            </ul>
            <div class="btn">
	            <ul class="list-form2">
	            	<li>
	            		<input type="button" id="btn_1" value="수정하기" onclick="edit_success(this.form)">
	            		<input type="reset" id="btn_2" value=" 취소">
	            	</li>
	            </ul>
            </div>
        </form>
        </div>
    </div>
</body>
</html>