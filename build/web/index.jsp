<%-- 
    Document   : index
    Created on : 25/07/2013, 14:55:36
    Author     : Vanessa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <form method="post" name="formLogin" action="ServeletController">
            Login <input type="text" size="20" name="login"><br />
            Senha <input type="password" size="20" name="password"><br />
            <input type="submit" value="Ok">
            <input type="hidden" value="doLogin" id="action" name="action" />
        </form>
       
        <a href="userCrud.jsp">Cadastrar usuário</a>
    </body>
</html>
