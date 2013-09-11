<%-- 
    Document   : index
    Created on : 25/07/2013, 14:55:36
    Author     : Vanessa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/jquery.js"></script>
<script src="js/toMD5.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
     $("#submit").click(function() {
        $("#password").val(CryptoJS.MD5($("#password").val()));
        $("#formLogin").submit();
    });
    });
</script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SPN - SERVICE PROVIDER NETWORK</title>
         <script src="js/default.js"></script>
    </head>
   
    <body>
        <form method="post" id="formLogin" name="formLogin" action="Controller">
            Login <input type="text" size="20" name="login"><br />
            Senha <input type="password" size="20" id="password" name="password"><br />
            <input type="submit" id="submit" value="Ok">
            <input type="hidden" value="doLog" id="c" name="c" />
            <input type="hidden" value="login" id="m" name="m" />
        </form>
        <a href="userCrud.jsp">Cadastrar usuário</a>       
       </body>
</html>
