<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
        <h2>아이디입니다.</h2>
        <c:choose>
        	<c:when test="${empty list })">
        		<ul>
        			<li>비었습니다.</li>
        		</ul>
        	</c:when>
        	<c:otherwise>
        		<c:forEach var="k" items="${list}">
        			<ul class="k2">
        				<li>${k.u_id}</li>
        			</ul>
        		</c:forEach>
        	</c:otherwise>
        </c:choose>
        
         <form action="backup.do" method="post">
         	<input type="submit" value="돌아가기">
         </form>
         
</body>
</body>
</html>