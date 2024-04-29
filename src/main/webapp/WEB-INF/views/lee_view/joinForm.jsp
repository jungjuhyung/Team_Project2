<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/lee_css/joinForm.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function join_success(f) {
		
		/* function email_send(f) {
			f.action="email_send_go.do";
			f.submit();
		}
		
		function email_send_go(f) {
			f.action="email_send_ok.do"; 
			f.submit();
		} */
		
		f.action="join_success_go.do";
		f.submit();
	}
	
</script>
</head>
<body>
<div class="container" style="width: 1300px; margin: 0 auto;">
        <form id="signup-form" method="post">
            <h2>회원가입</h2>
            <ul>
            <li class="input-group">
                <label for="u_id">아이디</label>
                <input type="text" id="u_id" name="u_id" required>
            </li>
            <li class="input-group">
                <label for="u_pwd">비밀번호</label>
                <input type="password" id="u_pwd" name="u_pwd" required>
            </li>
            <!-- <li class="input-group">
                <label for="u_pwd_chk">비밀번호 확인</label>
                <input type="password" id="u_pwd_chk" name="u_pwd_chk" required>
            </li>  -->
            <li class="input-group">
                <label for="u_name">이름</label>
                <input type="text" id="u_name" name="u_name" required>
            </li>
            <li class="input-group">
                <label for="dob">생년월일</label>
                <input type="date" id="u_birth" name="u_birth" required>
            </li>
            <li class="input-group">
                <label for="u_email">이메일</label>
                <input type="email" id="u_email" name="u_email" required>
                <!-- <input type="submit" id="send_verification" onclick="email_send(this.form)" value="인증번호 받기"> -->
            </li>
             <!-- <li class="input-group">
                <label for="u_email_num">이메일 인증번호</label>
                <input type="text" id="u_email_num" name="u_email_num">
                <input type="submit" id="email" onclick="email_send_go(this.form)" value="인증번호 확인">
                
            </li>  -->
            <li class="input-group">
                <label for="u_gender">성별</label>
                <select id="u_gender" name="u_gender" required>
                    <option value="">성별을 선택하세요</option>
                    <option value="male">남성</option>
                    <option value="female">여성</option>
                    <option value="other">기타</option>
                </select>
            </li>
            <li class="input-group">
                <label for="u_nickname">닉네임</label>
                <input type="text" id="u_nickname" name="u_nickname" required>
            </li>
            <li class="input-group">
                <label for="u_self">자기소개</label>
                <input type="text" id="u_self" name="u_self" required>
            </li>
            
            </ul>
            <div class="btn">
            <ul>
            	<li><input type="button" value="가입하기" onclick="join_success(this.form)"></li>
            	<li><input type="reset" value=" 취소"></li>
            </ul>
            </div>
        </form>
    </div>
<!-- <li class="join-container" style="width: 1300px; margin: 0 auto;">
	<li class="join">
		<form method="post" >
			<li class="join-form">
				<h2>회원가입</h2>
				<hr>
					<li class="join-form2">
						<label>아이디</label>
							<input type="text" name="u_id" placeholder="아이디" required><br>
	                    <label>비밀번호</label>						
							<input type="password" name="u_pwd" placeholder="비밀번호" required><br>
						<label>이름</label>	
							<input type="text" name="u_name" placeholder="이름" required><br>
						<label>생년월일</label>	
						
							<input type="text" name="u_birth" placeholder="생년월일" required><br>
						
						<label>이메일</label>	
							<input type="email" name="u_email" placeholder="이메일" required><br>
						<label>아이디</label>	
							<input type="text" name="u_id" placeholder="이메일 인증번호" required><br>
						<label>남</label>	
						<select></select>
							<input type="radio" name="u_gender"  required><br>
							<input type="radio" name="u_gender"  required><br>
						<label>닉네임</label>	
							<input type="text" name="u_nickname" placeholder="닉네임" required>
					</li>
					<li>
						<input type="button" value="회원가입" onclick="join_success(this.form)">
						<input type="reset" value="취소">
					</li>
			</li>
		</form>
	</li>
</li> -->

</body>
</html>