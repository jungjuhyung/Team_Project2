<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자유게시판</title>
<link rel="stylesheet" href="resources/common_css/reset.css">
<link rel="stylesheet" href="resources/kim_css/boardWrite.css">
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    <!-- include summernote css/js-->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script type="text/javascript">
function getReportgo(f) {
	f.action="getReportgo";
	f.submit();
}
</script>
</head>

<body>
<form method="post" action="reportWriteOK">
		<div class="container">
			<div class="insert">

				<table>
					<caption>
						<h2>신고게시판</h2>
					</caption>
					<tr>
						<td class="menu">아이디</td>
						<td class="userin"></td>
					</tr>
					<tr>
						<td class="menu">비밀번호</td>
						<td class="userin"><input type="password" id="report_pw" name="report_pw" />
					</tr>
					<tr>
						<td class="menu">불량유저ID</td>
						<td class="userin"><input type="text" id="reported_id" name="reported_id">
						</td>
					</tr>
					<tr>
						<td class="menu">제목</td>
						<td class="userin"><input type="text" id="report_title" name="report_title">
						</td>
					</tr>
					<tr>
						<td class="menu">내용</td>
						<td><textarea rows="10" cols="60" id="summernote" name="content"></textarea>
						</td>
					</tr>
				</table>

			</div>

			<div class="create">
				<input class="but4" type="submit" id="submit_btn" value="등록하기">
				<input class="but4" type="button" value="취소하기" onclick="getReportgo(this.form)"> 

			</div>
		</div>
	</form>
<script>
    // 메인화면 페이지 로드 함수
    $(document).ready(function () {
        $('#summernote').summernote({
            placeholder: '내용을 작성하세요',
            height: 400,
            maxHeight: 400
        });
    });
    
    /* 불량유저 아이디체크할껀데 잠시 보류
    $("#reported_id").keyup(function() {
		$.ajax({
			url : "AjaxIdChk",
			data : "reported_id="+$("#reported_id").val(),        // 갈때 url 갈때 따라감
			method : "post",                       //type : "post", 메서드와 동일
			dataType : "text",                    // 올때
			success : function(data){
				if (data == '1') {
					// 사용가능
					$("#submit_btn").removeAttr("disabled");
					$("span").text("사용가능");
				}else if (data == '0') {
					// 사용불가
					$("#submit_btn").attr("disabled","disabled");
					$("span").text("사용가능");
					
				}
			},
			error : function() {
				alert("읽기 실패")
			}
		});
	});
    */
    
</script>
</body>
</html>