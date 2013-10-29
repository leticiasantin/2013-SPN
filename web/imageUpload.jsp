<%-- 
    Document   : testeUpload
    Created on : Aug 31, 2013, 1:43:31 PM
    Author     : leticia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Upload de Foto</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${param.type eq 'p' or param.type eq 's' or param.type eq 'page'}">
                <form action="doImageUpload" enctype="multipart/form-data" method="post">
                    <label for="file">Arquivo:</label> 
                    <input type="hidden" id="type" name="type" value="${param.type}" />
                    <input type="hidden" name="id" value="${param.id}"/>
                    <input type="hidden" name="userType" value="${param.userType}"/>
                    <input type="file" name="file" id="file"><br/>
                    <input type="submit" value="Enviar"/>
                </form>
            </c:when>           
            <c:otherwise>
                não deu certo
            </c:otherwise>
         
        </c:choose>

    </body>
</html> 
