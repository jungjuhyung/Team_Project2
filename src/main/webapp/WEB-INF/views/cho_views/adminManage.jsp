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
<link href="resources/jung_css/mypage.css" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
		getList();
		
		/* 가입 버튼 누름 */
		$("#join_btn").click(function() {
			let admin_id = $("#admin_id").val();
			let admin_pwd = $("#admin_pwd").val();
			let admin_grade = $("#admin_grade").val();
			$.ajax({
				url : "AdminCreate",
				method : "post",
				data :{
					admin_id:admin_id,
					admin_pwd:admin_pwd,
					admin_grade:admin_grade
				},
				dataType : "json",
				success : function(data) {
					if(data == "0"){
						alert("가입실패")
					}else if (data == "1") {
						getList();
					}
				},
				error: function() {
					alert("전송 실패")
				}
			})
		});
		
		$("#admin_id").keyup(function() {
			$("#join_btn").attr("disabled", "disabled");
            $(".span").text("유효성 검사를 해주세요.");
		});
		$("#admin_pwd").keyup(function() {
			$("#join_btn").attr("disabled", "disabled");
            $(".span").text("유효성 검사를 해주세요.");
		});
		$("#admin_grade").keyup(function() {
			$("#join_btn").attr("disabled", "disabled");
            $(".span").text("유효성 검사를 해주세요.");
		});
	});
	/* 관리자 목록 불러오기 */
	function getList() {
		$.ajax({
			url: "getAdminList",
			method : "post",
			dataType : "json",
			success : function(data) {
				$("#adminInfo").empty();
				console.log(data);
				let tableBody = document.getElementById("adminInfo");
				
				for (let i = 0; i < data.length; i++) {
					let admin = data[i];
					let grade = "일반 관리자";
					
					if(admin.admin_grade == 2){
						grade = "고급 관리자"
					}
					if(admin.admin_grade == 9){
						grade = "총괄 관리자"
					}
					
				    // 관리자 등급이 총괄 관리자가 아닌 경우에만 삭제 버튼을 추가
				    let deleteButton = admin.admin_grade !== 9 ? "<input type='button' onclick='adminDel(" + admin.admin_idx + ")' value='삭제'>" : "";
				    
					
					let row = "<tr>" +
		                         "<td>" + admin.admin_idx + "</td>" +
		                         "<td>" + admin.admin_id + "</td>" +
		                         "<td>" + grade + "</td>" +
		                         "<td>" + admin.regdate + "</td>" +
		                         "<td>" + deleteButton + "</td>" +
		                      "</tr>";
					$("#adminInfo").append(row);
				}
			},
			
			error: function() {
				alert("읽기 실패")
			}
		});
	}
	/* 관리자 삭제하기 */
	function adminDel(admin_idx) {
		$.ajax({
			url: "adminDel",
			method : "post",
			data: {admin_idx: admin_idx}, 
			dataType : "json",
			success : function(data) {
				getList();
			},
			error: function() {
				alert("읽기 실패")
			}
		});
	}
	
	// 아이디 중복 체크
	function ValChk() {
		let admin_id = $("#admin_id").val();
    	let admin_pwd =$("#admin_pwd").val();
    	let admin_grade =$("#admin_grade").val();
		// 입력값이 비어 있는지 확인
	    if (admin_id.trim() === "") {
	    	 $(".span").text("아이디를 입력하세요.");
	        return false; // 폼 제출을 막음
	    }
	    if (admin_pwd.trim() === "") {
	    	 $(".span").text("비밀번호를 입력하세요.");
	        return false; // 폼 제출을 막음
	    }
		
	    if (admin_grade.trim() === "") {
	    	 $(".span").text("관리자 등급을 입력하세요.");
	        return false; // 폼 제출을 막음
	    }

		
		$.ajax({
			url : "adminIDChk",
			method : "post",
			data : {admin_id:admin_id},
			dataType : "text",
			success : function(data) {
				if(data == "0"){
					if (validateForm()) {
	                    // 사용 가능
	                    $("#join_btn").removeAttr("disabled");
	                    $(".span").text("생성 가능");
	                } else {
	                    // 관리자 등급이 유효하지 않음
	                    $("#join_btn").attr("disabled", "disabled");
	                    $(".span").text("관리자 등급을 확인해주세요.");
	                }
				}else if(data == "1"){
					$("#join_btn").attr("disabled","disabled")
					$(".span").text("이미 있는 아이디입니다.")
				}
			},
			error: function() {
				alert("전송 실패")
			}
		})
	}
	
	// 관리자 등급 유효성 검사
	function validateForm() {
    	let admin_grade =$("#admin_grade").val();

	    // admin_pwd가 숫자 0 또는 1이 아니면 유효하지 않음
	    return admin_grade === "1" || admin_grade === "2";
	}
	
	

	
	
</script>


<style type="text/css">
* {
  box-sizing: border-box;
}
.section{
	margin: 0 auto; 
	width: 1300px;
}
#adminTable{
	margin: 20 auto; 
	width: 1300px;
	border: 1px solid black;
	margin: 0 auto;
	border-collapse: collapse;
}
#adminTable th, #adminTable td{
	border: 1px solid black;
	text-align: center;
	padding: 3px;
	margin: 0 auto;
	border-collapse: collapse;
}
#adminJoinForm{
	margin: 20 auto; 
	width: 1300px;
	margin: 0 auto;
}
#adminJoinForm th, #adminJoinForm td{
	border: 1px solid black;
	text-align: center;
	width : 400px;
	padding: 3px;
	margin: 0 auto;
	border-collapse: collapse;
}
.adminH1{
	font-size: 2rem;
	font-weight: bold;
	text-align: center;
	margin: 0 auto;
}
.adminArticle{
	margin: 10px;
}


</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
	<section class = "section">
		<div class="adminH1">관리자 목록</div>
		<article id="adminList" class = "adminArticle">
			<table id ="adminTable">
				<thead>
					<tr>
						<th>관리자 번호</th> 
						<th>관리자ID</th> 
						<th>관리자 등급</th> 
						<th>생성일자</th> 
						<th>삭제하기</th> 
					</tr>
				</thead>
				
				<tbody id = "adminInfo">	
				</tbody>
			</table>
		</article>
		<!-- 관리자 추가  -->
		<article id="adminCreate" class = "adminArticle">
			<div class="adminH1">관리자 생성</div>
			<div>
				<form id = "adminJoinForm" method="post">
					<table>
						<thead>
							<tr>
								<th>아이디</th>
								<th>패스워드</th>
								<th>등급</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="text" size="14" name="admin_id" id="admin_id">
								</td>
								<td><input type="password" size="8" name="admin_pwd" id="admin_pwd"></td>
								<td>
									<input type="text" size="1" name="admin_grade" id ="admin_grade"><br>
									(일반 관리자 = 1, 고급 관리자 = 2)
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<input type="button" value="유효성검사" onclick="ValChk()"><br>
								</td>
								<td>
									<span class ="span">유효성 검사를 해주세요.</span>
								</td>
								<td>
									<button type="button" id="join_btn" disabled>생성하기</button>
								</td>
							</tr>
							
						
						</tfoot>
					</table>
				</form>
			</div>
		</article>
	</section>
	<%@ include file="/WEB-INF/views/common_view/footer.jsp" %>
</body>
</html>