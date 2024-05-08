<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
function idfindForm(f) {
		var name = document.getElementById("u_name").value;
		var email = document.getElementById("u_email").value;
		var emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]+(\.[a-zA-Z]+)?$/;
		
		if(name.trim() === ""){
			alert("이름을 입력하세요.");
			return false;
		}
		if(email.trim() === ""){
			alert("이메일을 입력하세요.");
			return false;
		}else if(!emailPattern.test(email)){
			alert("이메일 형식이 아닙니다.");
			return false;
		}
		f.action="idFind_go.do";
		f.submit();
	
}
	
	
	

</script>
</head>
<body>
	<h2>비밀번호 받을 이메일을 넣어주세요</h2>
	<form action="email_send_ok.do" method="post">
        <input type="email" name="email"
            pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*" title="이메일 양식">
        <input type="submit" value="전송">
    </form>
    
    <h2>아이디 찾을 이메일을 넣어주세요</h2>
    <span id="userInfo"></span>
    <form method="post">
    <label>이름
    	<input type="text" id="u_name" name="u_name"><br>
    </label>
    <label>이메일
    	<input type="email" id="u_email" name="u_email"
    		pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*" title="이메일 양식">
    </label>
    
    	<input type="button" onclick="idfindForm(this.form)" value="전송">
    </form>
</body>
</html>








