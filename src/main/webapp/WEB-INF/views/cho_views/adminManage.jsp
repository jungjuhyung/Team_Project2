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
<link rel="stylesheet" type="text/css" href="resources/cho_css/category.css">
<script type="text/javascript">
	$(document).ready(function() {
		getList();
		
		// 검색 엔터 처리
		document.querySelector('#adminSearchText').addEventListener('keypress', function(e) {
		    if (e.key === 'Enter') {
		        e.preventDefault(); // 기본 제출 동작 방지
		        document.querySelector('#adminSearchButton').click(); // SearchButton 클릭
		    }
		});
		
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
					$('.modal_box3').fadeOut();
					if(data == "0"){
						alert("가입실패")
					}else if (data == "1") {
						getList();
					}
				},
				error: function() {
					alert("전송 실패")
					$('.modal_box3').fadeOut();
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
	
	// 관리자 수정 여닫기
	$(document).on('click', '.admindtailbtn', function(){
        $('.modal_box').fadeIn(100);
        $('.modal_box3').fadeOut();
    });
	$(document).on('click', '#closeModal', function(){
        $('.modal_box').fadeOut();
    });
	
	// DB 갱신 열기
	$(document).on('click', '#DBupDate', function(){
        $('.modal_box2').fadeIn(100);
        $('.modal_box').fadeOut();
        $('.modal_box3').fadeOut();
        updateDB();
    });
	// 관리자 생성 열기
	$(document).on('click', '#CreateAdmin', function(){
        $('.modal_box3').fadeIn(100);
        $('.modal_box2').fadeOut();
        $('.modal_box').fadeOut();
    });
	$(document).on('click', '#closeModal2', function(){
        $('.modal_box3').fadeOut();
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
	function getList(page) {
		let text = $("#adminSearchText").val()
		$.ajax({
			url: "getAdminList",
			method : "post",
			data:{
				page:page,
				text:text
				},
			dataType : "json",
			success : function(data) {
				$("#adminInfo").empty();
				let tableBody = document.getElementById("adminInfo");
				for (let i = 0; i < data.adminList.length; i++) {
					let admin = data.adminList[i];
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
					
					// 페이징 처리
					updatePagination(data.paging);
				}
			},
			
			error: function() {
				alert("읽기 실패")
			}
		});
	}
	
	// 페이지 번호 클릭 이벤트 처리
	$(document).on("click", ".pagination li.page-item", function(e) {
	    e.preventDefault();
	    let page = parseInt($(this).attr('data-page'));
	    getList(page); // 해당 페이지 검색 실행
	});
	
	// 관리자 검색
	$(document).on("click", "#adminSearchButton", function(e) {
	    getList();
	});
	
	
	// 현재 페이지 구하는 함수
	function getCurrentPage() {
	    return parseInt($(".nowPage").attr("data-page"));
	}
	
	function getTotalRecord(paging){
		
		let totalRecordHtml = '검색 결과('+paging.totalRecord+')'
		
        $('#resultCount').html(totalRecordHtml);
	}
	
	// 페이징 처리 함수
	function updatePagination(paging) {
    let content = '';
    content += '<input type="hidden" class="nowPage" data-page="' + paging.nowPage + '">';
    content += '<ol class="pagination" id="pagination">';
    if (paging.beginBlock > 1) {
        content += '<li class="page-item" data-page="' + 1 + '"> 1 </li>';
    }
    if (paging.beginBlock > 1) {
        content += '<li class="page-item" data-page="' + (paging.beginBlock - 1) + '"> ... </li>';
    }

    // 페이지 번호를 표시할 개수
    const pageNumberDisplay = paging.pagePerBlock;

    // 현재 페이지 번호를 기준으로 앞뒤로 표시할 페이지 개수 계산
    let startPage = Math.max(paging.nowPage - Math.floor(pageNumberDisplay / 2), 1);
    let endPage = Math.min(startPage + pageNumberDisplay - 1, paging.totalPage);

    // 보정된 시작 페이지 번호 계산
    startPage = Math.max(endPage - pageNumberDisplay + 1, 1);

    // 중앙에 오도록 현재 페이지 번호를 위치시키기 위한 변수 설정
    let centerIndex = Math.floor(pageNumberDisplay / 2);

 	
    // 페이지 번호를 표시하는 부분 수정
    for (let i = startPage; i <= endPage; i++) {
    	content += '<li class="page-item" data-page="' + i + '">' + i + '</li>';
	}
    
	if(paging.nowPage < (paging.totalPage-2)){
	    if (paging.endBlock < paging.totalPage) {
	        content += '<li class="page-item" data-page="' + (paging.endBlock + 1) + '"> ... </li>';
	    }
	    if (paging.endBlock < paging.totalPage) {
	        content += '<li class="page-item totalPage" data-page="' + paging.totalPage + '"> '+ paging.totalPage +' </li>';
	    }
    }
  
    content += '</ol>';

    $(".board-list-paging").html(content);
	}
	/* 관리자 삭제하기 */
	function adminDel(admin_idx) {
		$.ajax({
			url: "adminDel",
			method : "post",
			data: {admin_idx: admin_idx}, 
			dataType : "json",
			success : function(data) {
				getList(getCurrentPage());
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
    width: 100%; /* 패딩을 고려한 너비 조정 */
    padding: 10px;
    box-sizing: border-box;
}
#adminSearchText{
	width: 30%;
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
	margin: 0 auto;
	padding: 20px;
	text-align: center;
	background-color: azure;
    border: 2px solid #CCCCCC; /* 연한 회색 테두리 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 3%;
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
	margin: 0 auto;
	padding: 20px;
	text-align: center;
	background-color: azure;
    border: 2px solid #CCCCCC; /* 연한 회색 테두리 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 3%;
	display: flex;
	flex-flow: column;
	position: absolute;
	top:50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 999;
	display: none;
}

.modal_box3{
	width: 500px;
	margin: 0 auto;
	padding: 20px;
	text-align: center;
	background-color: azure;/* 배경색 흰색 */
    border: 2px solid #CCCCCC; /* 연한 회색 테두리 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 3%;
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

.user_btn {
	border: none;
	padding: 5px;
	border-radius: 5px;
	background-color: pink;
	color: black;
	font-weight: bold;
}

.user_btn:hover{
	background-color: black;
	color: pink;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common_view/header.jsp" %>
	
	<section class = "section" style="min-height: 700px;">
		
		<div style="margin: 30px auto; text-align: center; ">
			<input type="button" class="user_btn" value="관리자 페이지" onclick="location.href='adminpage'" />
		</div>
	
		<article>
			<div class = "adminH1">DB 기능</div>
			<input type="button" class= "adminMangeBtn" id="DBupDate" value = "DB 동기화">
			<input type="button" class= "adminMangeBtn"  id="CreateAdmin" value = "관리자 생성">
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
			<!-- 페이징 표시 -->
			<div class="board-list-paging"></div>
			<!-- 페이지 이동 -->
			<div class="pageMoveForm">
				<input type="text" id="adminSearchText" class ="SearchText" placeholder="아이디 또는 비고를 검색하세요.">
				<input type="button" value="검색" id="adminSearchButton" class = "adminMangeBtn" >
			</div>
		</article>
		<br>
		
		
		<!-- 관리자 추가  -->
		<div class = "modalwrap">
			<div class="modal_box3">
					<div class="adminH1">관리자 생성</div>
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
										<button id="closeModal2" class = "adminMangeBtn">닫기</button>			
										<button type="button" class="adminMangeBtn" id="join_btn" disabled>생성하기</button>
									</td>
								</tr>
						</table>
			</div>
		</div>
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