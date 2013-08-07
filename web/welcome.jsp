<%-- 
    Document   : welcome
    Created on : 29/07/2013, 09:48:31
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
        <jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>

        <c:choose>
            <c:when test="${not empty user.uid}">
                <h1>Welcome ${user.name}!</h1>
            </c:when>
            <c:otherwise>
                <h1>Login error!</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>
