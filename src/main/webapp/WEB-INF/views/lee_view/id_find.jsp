<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/lee_css/id_find.css">

</head>
<body>
<%@ include file="/WEB-INF/views/common_view/header.jsp"%> 
<div class="id-container" style="width: 1300px; height:900px; margin: 0 auto;">
	<div class="id_find_st">
		<form method="post">
	        <h2>아이디 입니다.</h2>
	        <c:choose>
	        	<c:when test="${empty list })">
	        		<ul>
	        			<li>비었습니다.</li>
	        		</ul>
	        	</c:when>
	        	<c:otherwise>
	        		<c:forEach var="k" items="${list}">
	        			<ul class="k2">
	        				<li style="list-style-type: none;">${k.u_id}</li>
	        			</ul>
	        		</c:forEach>
	        	</c:otherwise>
	        </c:choose>
	         	<!-- <input type="button" value="비밀번호 찾기" onclick="pw_send.do()"> -->
	    </form>
	          <form action="backup.do" method="post">
	         	<input type="submit" id="back_to" value="돌아가기" >
	         </form> 
	         <form action="email_send.do" method="post">
	         	<input type="submit" id="pw_to" value="비밀번호 찾기">
	         </form>
     </div>    
</div>         
     <%@ include file="/WEB-INF/views/common_view/footer.jsp"%>
</body>
</body>
</html>