<%-- 
    Document   : categories
    Created on : Sep 4, 2013, 8:38:05 PM
    Author     : leticia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<jsp:useBean id="category" class="br.uel.entity.Category"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script  src="js/jquery.js"></script>
         <script type="text/javascript" >
             function delCategory(catId){
                   $.ajax({
                        type: "GET",
                        url: "Controller",
                        data: {
                            c: "doCategoryConnect",
                            m: "del",
                            catId: catId
                        }
                    }).success(function(data) {
                        alert('Cadastro excluído com sucesso');
                        location.reload();
                    }).error(function() {
                        alert('Falha ao salvar');
                    });
             }
             
             function addCategory(catId){
             $.ajax({
                        type: "GET",
                        url: "Controller",
                        data: {
                            c: "doCategoryConnect",
                            m: "add",
                            catId: catId
                        }
                    }).success(function(data) {
                        alert('Cadastro excluído com sucesso');
                        location.reload();
                    }).error(function() {
                        alert('Falha ao salvar');
                    });
                 
             }
         </script>
    </head>
    <body>
        <c:import url="header.jsp" />  
        <table>
            <tr>
                <td colspan="2">Categorias Disponiveis </td>
            </tr>
        <c:forEach var="category" items="${availableCategories}">
           <tr>
                <td>
                    ${category.name}
                </td>
                <td>
                    <img id="edit" src="icons/add.png" onclick="javascript: addCategory(${category.catId});"/>
                </td>
            </tr>
                    
        </c:forEach>
        </table>
        <table border="1">
            <thead colspan="2">
                 Minhas Categorias:
            </thead>
        <c:forEach var="category" items="${userCategories}">
            <tr>
                <td>
                    ${category.name}
                </td>
                <td>
                    <img id="delete" src="icons/delete.png" onclick="javascript: delCategory(${category.catId});"/>
                </td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
