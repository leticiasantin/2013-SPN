<%-- 
    Document   : mainView
    Created on : Sep 12, 2013, 8:34:54 PM
    Author     : leticia

--%>

<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SPN</title>
        <script src="js/jquery.js"></script>
        <script  src="js/default.js"></script> 
    </head>
    <link type="text/css" href="css/templateView.css" rel="stylesheet" />
    <body>      
        
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
        <jsp:useBean id="view" class="br.uel.entity.TemplateView" scope="request"/>
        <div class="header">
         <c:if test="${not empty view.header}">
                <c:import url ="guestHeader.jsp"/>
            </c:if> 
            <c:if test="${empty view.header}">
                <c:import url ="guestHeader.jsp"/>
            </c:if>
        </div>
        <div class="content-page">
            <div id="menu"> 
                <c:if test="${not empty view.menu}">
                    <c:import url="${view.menu}.jsp" /> 
                </c:if>
            </div>
            <div id="content">
                <c:if test="${not empty view.message}">
                    <div id="message">
                        ${view.message}
                        <script>
                            setTimeout("$('#message').hide()", 2000);
                        </script>
                    </div>
                </c:if>
                <c:if test="${not empty view.content}">
                    <c:import url="${view.content}.jsp" />
                </c:if>
                 <div id="extra">
                <c:if test="${not empty view.extra}">
                    <c:import url="${view.extra}.jsp"/>
                </c:if>
            </div>
            </div>
           
           
        </div>
         <div id="footer">
                <c:if test="${not empty view.footer}">
                    <c:import url="${view.footer}.jsp" />
                </c:if>
            </div>  
        <script type="text/javascript">
            top.document.title = '<c:out value="${view.title}" default="SPN"/>';
        </script>      
    </body>
</html>
