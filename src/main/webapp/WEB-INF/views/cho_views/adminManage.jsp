<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery -->
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
			let admin_note = $("#admin_note").val();
			$.ajax({
				url : "adminCreate",
				method : "post",
				data :{
					admin_id:admin_id,
					admin_pwd:admin_pwd,
					admin_grade:admin_grade,
					admin_note:admin_note
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
	
	/* 모달 여는 버튼 누름 */
	 $(document).on('click', '.admindtailbtn', function() {
		let admin_idx = $(this).data('admin_idx')
		$.ajax({
			url : "adminDetail",
			method : "post",
			data :{
				admin_idx:admin_idx,
			},
			dataType : "json",
			success : function(data) {
				$('#modal_admin_id').text(data.admin_id);
                $('#modal_admin_grade').val(data.admin_grade)
                $('#modal_admin_state').val(data.admin_state);
                $('#modal_admin_note').val(data.admin_note); 
                $('#modal_admin_regdate').text(data.regdate.substring(0, 10));
                $('#modal_admin_updatetime').text(data.updatetime.substring(0, 10));
			},
			error: function() {
				alert("전송 실패")
			}
		})
	});
	
	// 모달창 여닫기
	$(document).on('click', '.admindtailbtn', function(){
        $('.modal_box').fadeIn(100);
    });
	
	$(document).on('click', '#closeModal', function(){
        $('.modal_box').fadeOut();
    });
	
	$(document).on('click', '#DBupDate', function(){
        $('.modal_box2').fadeIn(100);
        updateDB();
    });
	
	
	/* 모달에서 수정 버튼 누름 */
	 $(document).on('click', '#updateAdmin', function() {
		let admin_id = $('#modal_admin_id').text();
		let admin_grade = $('#modal_admin_grade').val()
        let admin_state = $('#modal_admin_state').val();
        let admin_note = $('#modal_admin_note').val();          
		$.ajax({
			url : "adminUpdate",
			method : "post",
			data :{
				admin_id:admin_id,
				admin_grade:admin_grade,
				admin_state:admin_state,
				admin_note:admin_note,
			},
			dataType : "json",
			success : function(data) {
				$('.modal_box').fadeOut();
		        $('.modal_bg').fadeOut();
		        getList();
			},
			error: function() {
				alert("전송 실패")
			}
		})
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
				    let deleteButton = admin.admin_grade !== '9' ? "<input type='button' class='adminMangeBtn' onclick='adminDel(" + admin.admin_idx + ")' value='삭제하기'>" : "";
				    let updatetButton = admin.admin_grade !== '9' ? "<button type='button' class='adminMangeBtn admindtailbtn'  data-admin_idx="+admin.admin_idx+">상세보기</button>" : "";
					let state = admin.admin_state == '1' ? "활성" : "비활성";
					
					let row = "<tr>" +
		                         "<td>" + admin.admin_idx + "</td>" +
		                         "<td>" + admin.admin_id + "</td>" +
		                         "<td>" + grade + "</td>" +
		                         "<td>" + state + "</td>" +
		                         "<td>" + updatetButton + "</td>" +
		                         "<td>" + deleteButton + "</td>" +
		                         "<td>" + admin.regdate.substring(0, 10) + "</td>" +
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
	
	/* DB 장소 업데이트 */
	function updateDB() {
		$.ajax({
			url : "updateTest",
			type : "post",
			success : function(data) {
				console.log("AJAX 요청 성공:", data);
				$('.modal_box2').fadeOut();
			},
			error : function() {
				console.log("AJAX 요청 실패");
				$('.modal_box2').fadeOut();
			}
		});
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
	width: 1200px;
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
	vertical-align: middle;
}
#adminJoinForm {
    margin: 20px auto;
    width: 100%;
    max-width: 1300px;
    border-collapse: collapse; /* 테이블 경계선을 하나로 합침 */
}

#adminJoinForm th,
#adminJoinForm td {
    border: 1px solid black;
    text-align: center;
    width: 50%; /* 각 셀의 너비를 50%로 설정하여 두 열이 균등하게 나눠지도록 함 */
    padding: 10px; /* 여백을 늘려 가독성을 높임 */
    vertical-align: middle;
}

.adminH1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
}

.adminArticle {
    padding: 20px;
    border: 1px solid #ccc;
    background-color: #f9f9f9;
    border-radius: 5px;
    max-width: 1300px;
    margin: 0 auto;
}

.adminMangeBtn {
    padding: 10px 20px;
    margin: 5px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.adminMangeBtn:disabled {
    background-color: #ccc;
    cursor: not-allowed;
}

textarea {
    resize: none;
    width: 100%;
    box-sizing: border-box; /* 패딩과 경계선을 포함하여 너비를 설정 */
}

input[type="text"],
input[type="password"],
select {
    width: calc(100% - 22px); /* 패딩을 고려한 너비 조정 */
    padding: 10px;
    box-sizing: border-box;
}

.span {
    color: red;
}

.modalwrap{
	margin: 0 auto;
	width: 100%;
	height: 100%;
}

.modalcontent{
	width: 80%;
	height: 150px;
	margin: 20px auto;
}

.modalcontent button{
	border: none;
	width: 100px;
	height: 30px;
	margin-top: 20px;
	float: right;
	cursor: pointer;
}

/* 모달창 */
.modal_box{
	width: 500px;
	height: 800px;
	margin: 0 auto;
	padding: 20px;
	text-align: center;
	background-color: azure;
	display: flex;
	flex-flow: column;
	position: absolute;
	top:50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 999;
	display: none;
}
.modal_box2{
	width: 500px;
	height: 400px;
	margin: 0 auto;
	padding: 20px;
	text-align: center;
	background-color: azure;
	display: flex;
	flex-flow: column;
	position: absolute;
	top:50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 999;
	display: none;
}

.modal_box button{
	width: 100px;
	height: 30px;
	border: none;
	margin-top: 10px;
	align-self: center;
	cursor: pointer;
}

.modal_box td, .modal_box table{
	border: 1px solid black;
	text-align: center;
	padding: 3px;
	margin: 0 auto;
	border-collapse: collapse;
	vertical-align: middle;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
	
	<section class = "section">
		
	
		<article>
			<div class = "adminH1">DB 기능</div>
			<input type="button" class= "adminMangeBtn" id="DBupDate" value = "DB 동기화">
		</article>
	
	
		<div class="adminH1">관리자 목록</div>
		<article id="adminList" class = "adminArticle">
			<table id ="adminTable">
				<thead>
					<tr>
						<th width="100px">관리자 번호</th> 
						<th width="150px">관리자ID</th> 
						<th width="200px">관리자 등급</th> 
						<th width="100px">상태</th> 
						<th width="100px">상세보기</th> 
						<th width="100px">삭제하기</th> 
						<th width="100px">생성일자</th> 
					</tr>
				</thead>
				
				<tbody id = "adminInfo">	
				</tbody>
			</table>
		</article>
		<br>
		<!-- 관리자 추가  -->
		<article id="adminCreate" class = "adminArticle">
			<div class="adminH1">관리자 생성</div>
			<div>
				<table id= "adminJoinForm" >
					<tbody>
						<tr>
							<td> 아이디 </td>
							<td>
								<input type="text" size="14" name="admin_id" id="admin_id">
							</td>
						</tr>
						<tr>
							<td> 비밀번호 </td>
							<td><input type="password" size="8" name="admin_pwd" id="admin_pwd"></td>
						</tr>
						<tr>
							<td> 관리자 등급 <br> (일반 관리자 = 1, 고급 관리자 = 2)</td>
							<td>
								<select name="admin_grade" id= "admin_grade">
									<option value="1">일반 관리자</option>
									<option value="2">고급 관리자</option>
								</select> 
							</td>
						</tr>
						<tr>
							<td> 비고 </td>
							<td><textarea name="admin_note" id ="admin_note" rows="10" cols="50"></textarea> </td>
						</tr>
					</tbody>
						<tr>
							<td>
								<input type="button" class="adminMangeBtn" value="유효성검사" onclick="ValChk()"><br>
							</td>
							<td>
								<span class ="span">유효성 검사를 해주세요.</span>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<button type="button" class="adminMangeBtn" id="join_btn" disabled>생성하기</button>
							</td>
						</tr>
				</table>
			</div>
		</article>
	</section>
	
	
		<!-- 회원 정보 모달 -->
		<div class="modalwrap">
			<div class="modal_box">
				<div>관리자 상세 정보</div>
					<table style="width: 400px; margin: 0 auto;">
						<tbody>
							<tr>
								<td>관리자 id</td>
								<td id ="modal_admin_id"></td>
							</tr>
							<tr>
								<td>관리자 등급</td>
								<td >
								<select id= "modal_admin_grade">
										<option value="1">일반 관리자</option>
										<option value="2">고급 관리자</option>
								</select> </td>
							</tr>
							<tr>
								<td>상태</td>
								<td><select id="modal_admin_state">
										<option value="0">비활성</option>
										<option value="1">활성</option>
										</select> 
								</td>
							</tr>
							<tr>
								<td>비고</td>
								<td><textarea rows="10" cols="30" style="resize: none;" id= "modal_admin_note"></textarea> </td>
							</tr>
							<tr>
								<td>수정일자</td>
								<td id="modal_admin_updatetime"></td>
							</tr>
							<tr>
								<td>생성일자</td>
								<td id="modal_admin_regdate"></td>
							</tr>
						</tbody>						
						
					</table>
				<button id="closeModal">닫기</button>			
				<button id="updateAdmin">수정하기</button>			
			</div>
		</div>
	
		<!-- DB 갱신 -->
		<div class="modalwrap">
			<div class="modal_box2">
			<div>
				<p>DB 업데이트 중...</p>
				<p>예상 소요시간 10분...</p>
				<p>창이 닫힐때까지 기다려주세요.</p>
			 </div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common_view/footer.jsp" %>
</body>
</html>