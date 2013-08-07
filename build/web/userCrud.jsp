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
        <script  src="js/jquery-2.0.3.js"></script>
        <script>
            $(document).ready(function() {
                $('reset').click(function() {
                    $('password').val("");
                    $('login').val("");
                    $('name').val("");

                });
            });
        </script> 

    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
        <jsp:useBean id="user" class="br.uel.entity.User" scope="request"/>
        <p> <h1>Cadastro de Usuários </h1> </p>
    <form name="formUser" method="post" action="ServeletController">
        <input type="hidden" id="uid" name="uid" value="${user.uid}" />  
        Nome <input type="text" size="50" name="name" value="${user.name}"> <br />
        Login <input type="text" size="15" name="login" value="${user.login}"> <br />
        Senha <input type="password" size="8" name="password"> <br />
        <input type="submit" value="Ok"/>
        <input type="button" id="reset" value="Limpar"/>
        
       
        
        <input type="hidden" value="doUserSCRUD" id="c" name="c" />
        <input type="hidden" value="s" id="m" name="m" />
        user name = ${user.name}
    </form>
</body>
</html>
