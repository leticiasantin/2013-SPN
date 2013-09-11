<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<jsp:useBean id="category" class="br.uel.entity.Category"/>

<c:import url="adminHeader.jsp" />  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
        <script  src="js/jquery.js"></script>
        <script  type="text/javascript">
            $(document).ready(function() {
                $("#submit").click(function() {
                    $.ajax({
                        type: "POST",
                        url: "Controller",
                        data: {
                            c: "doCategoryCrud",
                            m: "save",
                            parentId: $("#parentSelect").val(),
                            name: $("#catName").val(),
                            catId: $("#catId").val()
                        }
                    }).success(function(data) {
                        alert('Categoria salva com sucesso');
                        location.reload();
                        $("#catName").val("");
                        $("#catId").val("");
                        $("#parentSelect").val(0);
                    }).error(function() {
                        alert('Falha ao salvar');
                    });
                });
                
                $("#reset").click(function() {
                    $("#catName").val('');
                    $("#parentSelect").val(0);
                    $("#catId").val(0);
                });
            });
            function cDelete(cId) {
                $.ajax({
                    type: "POST",
                    url: "Controller",
                    data: {
                        c: "doCategoryCrud",
                        m: "delete",
                        catId: cId
                    }
                }).success(function(data) {
                    alert('Cadastro excluído com sucesso');
                    location.reload();
                }).error(function() {
                    alert('Falha ao salvar');
                });
            }
            ;
            function cEdit(cId, pId, name) {
                $("#catName").val(name);
                $("#parentSelect").val(pId);
                $("#catId").val(cId);
            }
            ;

            function openChild(cId) {
             
            }

        </script>

    </head>
    <body>
        <form id="categoryForm" name="categoryForm">
            <h2 id="title">  Adicionar Nova Categoria </h2>

            <br/>
            Nome da Nova Categoria

            <input type="hidden" id="catId" name="catId" value="0"/>
            <input type="text" id="catName" name="catName" maxlenght="50" />
            <br/><br/>
            Sub Categoria de:

            <select name="parentSelect" id="parentSelect">
                <option id="parentId" name="parentId" value="0"></option>    
                <c:forEach var="category" items="${catList}">
                    <option id="parentId" name="parentId" value="${category.catId}">${category.name}</option>    
                </c:forEach>
            </select>
            <br/><br/>
            <input type="button" id="submit" name="submit" value="Adicionar" /> 

            <input type="button" id="reset" name="reset" value="Cancelar" />
            <br/><br/>
            <hr><hr>
            <br/>
            <h2 id="title">   Editar/Excluir Categoria</h2>
            <table border="1">
                <c:forEach var="category" items="${catList}">
               
                        <tr>
                            <td id="${category.catId}" onclick="javascript: openChild(${category.catId});">
                                ${category.catId} - ${category.parentId} - ${category.name}    
                            </td>
                            <td>
                                <img id="edit" src="icons/edit.png" onclick="javascript: cEdit(${category.catId},${category.parentId}, '${category.name}');"/>
                                <img id="delete" src="icons/delete.png" onclick="javascript: cDelete(${category.catId});"/>
                                <img id="more" src="icons/add.png" onclick="javascript: openChild(${category.catId},'1');"/>
                            </td>
                           
                        </tr>
                 
                </c:forEach>
                        
            </table>

        </form>
        <c:out value="${fn:length(catList)}" />  

    </body>
</html>
