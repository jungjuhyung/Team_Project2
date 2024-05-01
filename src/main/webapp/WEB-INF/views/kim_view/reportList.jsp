<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		function getList() {
			$.ajax({
				url : "getReportList",
				method : "post",        //type : "post", 메서드와 동일
				dataType : "xml",
				success : function(data){
					let tbody ="";
					$(data).find("report").each(function() {
						tbody += "<tr>";
						
						tbody += "<td>" + $(this).find("report_idx").text() + "</td>";
						tbody += "<td>" + $(this).find("u_id").text() + "</td>";
						tbody += "<td>" + $(this).find("board_title").text() + "</td>";
						tbody += "<td>" + $(this).find("regdate").text() + "</td>";
						tbody += "<td>" + $(this).find("report_state").text() + "</td>";
						tbody += "</tr>";
					});
					$("#tbody").append(tbody);
					
				},
				error : function(){
					alert("실패")
				}
			});	
		}
	
</script>
</head>
<body>
    <h2>신고게시판</h2>
    <div>
        <table id="list">
            <thead>
                <tr>
                    <th>번호</th><th>닉네임</th><th>제목</th><th>작성일자</th><th>답변여부</th>
                </tr>
            </thead>
            <tbody id="tbody">
            	
            </tbody>
        </table>
    </div>
</body>
</html>