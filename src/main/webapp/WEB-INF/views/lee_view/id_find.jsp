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
<div class="id-container">
	<div class="id_find_st">
		<form method="post">
	        <h2>아이디</h2>
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
	    </form>
	         <form action="backup.do" method="post">
	         	<input type="submit" value="비밀번호 찾기">
	         	<input type="submit" value="돌아가기" onclick="">
	         </form>
     </div>    
</div>         
</body>
</body>
</html>