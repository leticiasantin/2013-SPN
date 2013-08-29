<%-- 
    Document   : usercrud
    Created on : 01/08/2013, 14:30:45
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>Cadastro de Usuários</title>
        <script  src="js/jquery/js/jquery.js"></script>
        <script  src="js/jquery/js/jquery-ui.js"></script>        
        <link type="text/css" href="js/jquery/css/south-street/jquery-ui.css" rel="stylesheet" />
        <script src="js/toMD5.js"></script>
        <script  src="js/default.js"></script>
        <script  src="js/user/form.js"></script>
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
        <jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
        <p> <h1>Cadastro de Usuários </h1> </p>
    <form name="formUser" method="post" action="Controller">
        <jsp:include page = "forms/user.jsp" />  
        <input type="submit" id="submit" value="Ok" />
        <input type="reset" id="reset" value="Limpar"/>
        <input type="hidden" value="doUserSCRUD" id="c" name="c" />
        <input type="hidden" value="save" id="m" name="m" />
    </form>
</body>
</html>
