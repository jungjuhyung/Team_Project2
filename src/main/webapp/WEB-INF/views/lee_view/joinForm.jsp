<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/lee_css/joinForm.css">
<script type="text/javascript">
	function join_success(f) {
		if(u_pwd.value == u_pwd_chk.value){
			alert("비밀번호가 일치합니다.");
		}else if(u_pwd.value != u_pwd_chk.value){
			alert("비밀번호가 일치하지 않습니다.");
			f.u_pwd_chk.focus();
			return ;
		}
		f.action="join_success_go";
		f.submit();
	}
	
</script>
</head>
<body>
<div class="container" style="width: 1300px; margin: 0 auto;">
        <form id="signup-form" action="#" method="post">
            <h2>회원가입</h2>
            <div class="input-group">
                <label for="u_id">아이디</label>
                <input type="text" id="u_id" name="u_id" required>
            </div>
            <div class="input-group">
                <label for="u_pwd">비밀번호</label>
                <input type="password" id="u_pwd" name="u_pwd" required>
            </div>
            <div class="input-group">
                <label for="u_pwd_chk">비밀번호 확인</label>
                <input type="password" id="u_pwd_chk" name="u_pwd_chk" required>
            </div>
            <div class="input-group">
                <label for="dob">생년월일</label>
                <input type="date" id="dob" name="dob" required>
            </div>
            <div class="input-group">
                <label for="u_email">이메일</label>
                <input type="email" id="u_email" name="u_email" required>
            </div>
            <div class="input-group">
                <label for="u_email_num">이메일 인증번호</label>
                <input type="text" id="u_email_num" name="u_email_num">
                <button type="button" id="send_verification">인증번호 받기</button>
            </div>
            <div class="input-group">
                <label for="u_gender">성별</label>
                <select id="gender" name="gender" required>
                    <option value="">성별을 선택하세요</option>
                    <option value="male">남성</option>
                    <option value="female">여성</option>
                    <option value="other">기타</option>
                </select>
            </div>
            <div class="input-group">
                <label for="u_nickname">닉네임</label>
                <input type="text" id="u_nickname" name="u_nickname" required>
            </div>
            <button type="submit" onclick="join_success(this.form)">가입하기</button>
        </form>
    </div>
<!-- <div class="join-container" style="width: 1300px; margin: 0 auto;">
	<div class="join">
		<form method="post" >
			<div class="join-form">
				<h2>회원가입</h2>
				<hr>
					<div class="join-form2">
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
					</div>
					<div>
						<input type="button" value="회원가입" onclick="join_success(this.form)">
						<input type="reset" value="취소">
					</div>
			</div>
		</form>
	</div>
</div> -->

</body>
</html>