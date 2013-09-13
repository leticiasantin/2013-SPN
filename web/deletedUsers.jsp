<%-- 
    Document   : deletedUsers
    Created on : Sep 4, 2013, 6:05:38 PM
    Author     : leticia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script  src="js/jquery.js"></script>
        <script type="text/javascript">
            $(document).ready(function (){
                
            });
            
             function delUser(userId){
            $.ajax({
                        type: "POST",
                        url: "Controller",
                        data: {
                            c: "doUserSCRUD",
                            m: "delete",
                            userId: userId
                        }
                    }).success(function(data) {
                        alert('Usuário excluído com sucesso');
                        location.reload();
                    }).error(function() {
                        alert('Falha ao salvar');
                    }); 
        
        alert("ok" + userId);
            }
            
        </script>    
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
         <jsp:useBean id="user" class="br.uel.entity.User"/>
         <table border="1">
             <tr>
                 <td>Id</td>
                 <td>Nome</td>
                 <td>Login</td>
                 <td></td>
             </tr>
         <c:forEach var="user" items="${delUsers}">
             <tr>
                 <td>${user.userId}</td>
                 <td>${user.name}</td>
                 <td>${user.login}</td>
                 <td><img id="delete" src="icons/delete.png" onclick="javascript: delUser(${user.userId});"/></td>                 
             </tr>
              
         </c:forEach>
         </table>
    </body>
</html>
