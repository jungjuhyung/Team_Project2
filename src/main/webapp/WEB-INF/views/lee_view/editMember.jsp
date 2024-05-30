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

	$(document).ready(function () {
		$("#u_nickname").keyup(function () {
			let u_nickname = $("#u_nickname").val();
			let u_nickname2 = $("#u_nickname2").val();
			
			if (u_nickname != u_nickname2) {
				$.ajax({
					url : "getNickChk.do",
					// data : "u_nickname=" + $("#u_nickname").val(),
					data : {u_nickname:u_nickname},
					method : "post",
					dataType : "text",
					success : function(data) {
						//console.log(data);
						
						if(data == '1' && u_nickname){
							$("#btn_1").removeAttr("disabled");
							$("#nick_chk").text("사용가능").css('color' , 'green');
						}else if(data == '0' && /\s/.test(u_nickname)){
							$("#nick_chk").text("사용불가").css('color' , 'red');
							$("#btn_1").attr("disabled", "disabled");
						}
						
					},
					error : function() {
						alert("읽기 실패");
					}
				});
			}else {
				$("#nick_chk").text("");
			}
			
		});
		
			 
		
	});
	function chk_password(f) {
        $.ajax({
            url : "chkPassword.do",
            data : {u_pwd: $("#u_pwd").val(),
            		u_idx: $("#u_idx").val()},
            method : "post",
            dataType : "text",
            success : function(data) {
                if(data == '1'){
                    f.action = "new_pass.do";
                    f.submit();
                } else if(data == '0'){
                    alert("비밀번호가 일치하지 않습니다.");
                    $("#u_pwd").val("");
                    $("#u_pwd").focus();
                }
            },
            error : function() {
                alert("읽기 실패");
            }
        });
    }
	
	
	function edit_success(f) {
		f.action = "my_edit_ok.do";
		f.submit();
	}

</script>
</head>
<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
<div class="edit-container" style="width: 1300px; height:900px; margin: 0 auto;">
	<div class="edit_sub">
        <form class="edit-form" method="post">
            <h2>회원정보</h2>
            <ul class="list-form"> 
            <li class="input-group">
            	<span>아이디</span><br>
               	<input type="hidden" id="u_idx" name="u_idx" value="${mvo.u_idx}" readonly>
               	<input type="text" id="u_id" name="u_id" value="${mvo.u_id}" readonly>
            </li>
            <li class="input-group">
            	<span>이름</span><br>
               	<input type="text" id="u_name" name="u_name" value="${mvo.u_name}" readonly>
               	<input type="hidden" name="n_status" value="0">
               	<input type="hidden" name="k_status" value="0">
            </li>
            <li class="input-group">
            	<span>닉네임</span><br>
       			<input type="text" id="u_nickname" name="u_nickname" value="${mvo.u_nickname}">
               	<input type="hidden" id="u_nickname2" name="u_nickname2" value="${mvo.u_nickname}">
       			<br><span id="nick_chk" style="font-size: 10px;"></span>
               	<span id="nickCheckResult" style="font-size: 10px; color: red;"></span><br>
       		</li>
            <li class="input-group">
            	<span>이메일</span><br>
               	<input type="email" id="u_email" name="u_email" pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*" 
               	value="${mvo.u_email}" readonly>
            </li>
            <li class="input-group">
            	<span>성별</span><br>
               	<select id="u_gender" name="u_gender"  disabled>
                    <option value="">성별을 선택하세요</option>
                    <option value="male" ${mvo.u_gender == 'male' ? 'selected' : '' }>남성</option>
                    <option value="female" ${mvo.u_gender == 'female' ? 'selected' : '' }>여성</option>
               	</select>
            </li>
            
            <li class="input-group">
        		<span>생년월일</span><br>    		
               	<input type="date" id="u_birth" name="u_birth" value="${mvo.u_birth}" readonly>
	            </li>
            <li class="input-group">
            	<span>자기소개</span><br>
               	<input type="text" id="u_self" name="u_self"   value="${mvo.u_self}">
            </li>
           	<li class="input-group">
          		<span>비밀번호</span><br>
            	<input type="password" id="u_pwd" name="u_pwd" maxlength="8" >
            	<br><span id="pwd_chk" style="font-size: 10px;"></span>
               	<span id="pwdCheckResult" style="font-size: 10px; color: red;"></span>
            	
            </li>
            </ul>
            <div class="btn">
	            <ul class="list-form2">
	            	<li>
	            		<input type="button" id="newpwd" value="비밀번호 변경" onclick="chk_password(this.form)" >
	            		<input type="button" id="btn_1" value="수정하기" onclick="edit_success(this.form)" disabled>
	            		<input type="reset" id="btn_2" value=" 취소">
	            	</li>
	            </ul>
            </div>
        </form>
        </div>
    </div>
</body>
</html>