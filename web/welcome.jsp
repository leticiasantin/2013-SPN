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
        <title>SPN</title>
        <script>
            function goBack()
            {
                window.history.back()
            }
        </script>
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
        <jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
        <c:choose>
            <c:when test="${not empty user.userId}">
                <c:choose>
                    <c:when test="${user.login == 'admin' }">
                        <c:redirect url="adminWelcome.jsp" />
                    </c:when>
                    <c:otherwise>
                         <c:import url="header.jsp" />
                         <c:if test="${user.status == false}">
                            Que bom que voltou
                         </c:if>
                        <h1>Bem-vindo ${user.name}!</h1>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h1>Login error!</h1>
                <input type="button" value="Back" onclick="goBack()">
            </c:otherwise>
        </c:choose>
    </body>
</html>