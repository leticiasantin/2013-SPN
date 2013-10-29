<%-- 
    Document   : usersList
    Created on : Oct 9, 2013, 10:31:01 AM
    Author     : leticia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="br.uel.entity.User"/>
     <h1>Hello World!</h1>
     <table border="1">
         <caption> Relação de Usuarios </caption>
          <tr>
                <th >Login </th>
                <th >NOme</th>
                <th >Cidade-UF</th>
                <th >Status</th>
                <th >Perfil</th>
              
            </tr>
     <c:forEach var="user" items="${users}">
         <tr>
             <td>${user.login}</td>
             <td>${user.name}</td>
             <td>${user.city}-${user.state}</td>
             <td>${user.status}</td>
         </tr>
         
     </c:forEach>
     </table>   
        

