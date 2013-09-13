<%-- 
    Document   : myProfile
    Created on : Aug 28, 2013, 11:16:24 PM
    Author     : leticia
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
        <c:import url="header.jsp" />  
        <jsp:useBean id="profile" class="br.uel.entity.Profile" scope="request"/>
        <jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
        <c:choose>
            <c:when test="${profile.profileId != 0}">
                <c:import url="forms/profile.jsp?profile=${user.userId}" /> 
                <c:forEach var="category" items="${profile.categories}">
                    <label>categoria </label>
                    <label id="${category.catId}"></label> ${category.name}<br/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h1>Você não está cadastrado como Prestador</h1>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>
