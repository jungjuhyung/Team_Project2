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
	
$(document).ready(function(){
    // 비밀번호와 비밀번호 확인란의 값을 비교하여 일치 여부를 확인하는 함수
    function checkPasswordMatch() {
        var password = $('#u_pwd').val(); // 비밀번호 입력란의 값
        var confirmPassword = $('#u_pwd_chk').val(); // 비밀번호 확인란의 값
        var passwordMatch = (password === confirmPassword); // 비밀번호 일치 여부
        
        // 비밀번호 일치 여부에 따라 메시지를 표시합니다.
        if (passwordMatch) {
            $('.successPwChk').text('비밀번호가 일치합니다.').css('color', 'green');
            $('#pwDoubleChk').val('matched'); // 비밀번호 일치 여부를 hidden 필드에 저장
        } else {
            $('.successPwChk').text('비밀번호가 일치하지 않습니다.').css('color', 'red');
            $('#pwDoubleChk').val('not matched'); // 비밀번호 일치 여부를 hidden 필드에 저장
        }
    }

    // 비밀번호 입력란 또는 확인란에서 입력 값이 변경될 때마다 비밀번호 일치 여부를 확인합니다.
    $('#u_pwd, #u_pwd_chk').keyup(checkPasswordMatch);

    // 회원가입 버튼 클릭 시 비밀번호 일치 여부를 확인하고, 일치하지 않으면 가입을 막습니다.
    $('#signup-form').submit(function(e){
        e.preventDefault(); // 폼 제출 방지
        var passwordMatch = $('#pwDoubleChk').val(); // hidden 필드에 저장된 비밀번호 일치 여부
        
        if (passwordMatch !== 'matched') {
            alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
            return; // 비밀번호가 일치하지 않으면 가입을 막고 함수 종료
        }
        
        // 비밀번호가 일치하면 여기에 회원가입 로직을 추가할 수 있습니다.
        
        // 예를 들어, 서버로 회원가입 데이터를 전송하거나 추가적인 유효성 검사를 수행할 수 있습니다.
        
    });
});
    function join_success(f) {
		f.action="join_success_go.do";
		f.submit();
    }
		
	
</script>

</head>
<body>
<div class="container" style="width: 1300px; margin: 0 auto;">
        <form id="signup-form" method="post">
            <h2>회원가입</h2>
            <ul class="list-form"> 
            <li class="input-group">
                <label for="u_id">아이디
                	<input type="text" id="u_id" name="u_id" required>
                </label>
                
            </li>
            <li class="input-group">
                <label for="userpass">비밀번호
                	<input type="password" id="u_pwd" name="u_pwd" required maxlength="8" autocomplete="off"><br>
                	<span class="point">* 비밀번호는 총 8자까지 입력 가능</span>
                </label>
                
            </li>
            <li class="input-group">
                <label for="userpasschk">비밀번호 확인
                	<input type="password" id="u_pwd_chk" name="u_pwd_chk" placeholder="동일하게 입력해주세요." required autocomplete="off"><br>
                	<span class="point successPwChk"></span>
                	<input type="hidden" id="pwDoubleChk">
                </label>
                
            </li>  
            <li class="input-group">
                <label for="u_name">이름
                	<input type="text" id="u_name" name="u_name" required>
                </label>
                
            </li>
            <li class="input-group">
                <label for="dob">생년월일
                	<input type="date" id="u_birth" name="u_birth" required>
                </label>
                
            </li>
            <li class="input-group">
                <label for="u_email">이메일
                	<input type="email" id="u_email" name="u_email" required>
                </label>
            </li>
            <li class="input-group">
                <label for="u_gender">성별
                	<select id="u_gender" name="u_gender" required>
	                    <option value="">성별을 선택하세요</option>
	                    <option value="male">남성</option>
	                    <option value="female">여성</option>
	                    <option value="other">기타</option>
                	</select>
                </label>
                
            </li>
            <li class="input-group">
                <label for="u_nickname">닉네임
                	<input type="text" id="u_nickname" name="u_nickname" required>
                </label>
            </li>
            <li class="input-group">
                <label for="u_self">자기소개
                	<input type="text" id="u_self" name="u_self" required>
                </label>
            </li>
            
            </ul>
            <div class="btn">
            <ul class="list-form2">
            	<li>
            		<input type="button" value="가입하기" onclick="join_success(this.form)">
            		<input type="reset" value=" 취소">
            	</li>
            	
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