<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function login_go() {
		location.href="login_go.do";
	}

	function boardList() {
		location.href="boardList";
	}
	
</script>
</head>
<body>
<div id="ko" style="width: 500px; margin: 60px auto 30px; border: 1px solid red;">
	<form action="">
		<fieldset>
		<legend>고예찬</legend>
			<input type="hidden">
			<input type="text">
			<input type="submit">
		</fieldset>
	</form>
</div>
<div id="kim" style="width: 500px; margin: 30px auto 30px; border: 1px solid yellow;">
	<form action="">
		<fieldset>
		<legend>김철환</legend>
			<input type="hidden">
			<input type="text">
			<input type="button" onclick="boardList()">
		</fieldset>
	</form>
</div>
<div id="lee" style="width: 500px; margin: 30px auto 30px; border: 1px solid blue;">
	<form action="">
		<fieldset>
		<legend>이학준</legend>
			<input type="hidden">
			<input type="text">
			<button type="button" onclick="login_go()">고~!</button> 
		</fieldset>
	</form>
</div>
<div id="jung" style="width: 500px; margin: 30px auto 30px; border: 1px solid green;">
	<form action="">
		<fieldset>
		<legend>정주형</legend>
			<input type="hidden">
			<input type="text">
			<input type="submit">
		</fieldset>
	</form>
	<form action="recommend_write_go" method="post">
		<fieldset>
		<legend>jung, kakaomap</legend>
			<input type="hidden" name="title" value="카카오">
			<input type="hidden" name="mapx" value="33.450705">
			<input type="hidden" name="mapy" value="126.570677">
			
			<input type="hidden" name="title" value="생태연못">
			<input type="hidden" name="mapx" value="33.450936">
			<input type="hidden" name="mapy" value="126.569477">
			
			<input type="hidden" name="title" value="텃밭">
			<input type="hidden" name="mapx" value="33.450879">
			<input type="hidden" name="mapy" value="126.569940">
			
			<input type="hidden" name="title" value="근린공원">
			<input type="hidden" name="mapx" value="33.451393">
			<input type="hidden" name="mapy" value="126.570738">
			<input type="hidden" name="title">
			<input type="submit" value="작성하기">
		</fieldset>
	</form>
</div>
<div id="cho" style="width: 500px; margin: 30px auto 30px; border: 1px solid black;">
	<form action="areaCategory">
		<fieldset>
		<legend>조민기</legend>
			<input type="submit">
		</fieldset>
	</form>
</div>
</body>
</html>