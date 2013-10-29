<%-- 
    Document   : categories
    Created on : Sep 4, 2013, 8:38:05 PM
    Author     : leticia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<jsp:useBean id="category" class="br.uel.entity.Category"/>

<script  src="js/jquery.js"></script>
<script type="text/javascript" >
    function delCategory(catId) {
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

    function addCategory(catId) {
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

    function openChild(cId) {
        childId = "#child" + cId;
        var objects = "";
        <c:forEach var="category" items="${availableCategories}">
        if (${category.parentId} === cId) {
            objects = objects.concat("<dl border='1'>");
            objects = objects.concat(getDl(${category.catId},${category.parentId}, "${category.name}"));
            objects = objects.concat("</dl>");
        }
        </c:forEach>
                $(childId).append(objects);
                more = "#more" + cId;
                $(more).hide();
                less = "#less" + cId;
                $(less).show();
            }

            function closeChild(cId) {
                childId = "#child" + cId;
                $(childId).text("");
                less = "#less" + cId;
                $(less).hide();
                more = "#more" + cId;
                $(more).show();
            }

            function getDl(catId, parentId, catName) {
                dl = "<dt id=" + catId + ">";
                dl += "<img id='more" + catId + "' src='icons/add.png' onclick='javascript: openChild(" + catId + ");'/>";
                dl += "<img hidden='hidden' id='less" + catId + "' src='icons/minus.png' onclick='javascript: closeChild(" + catId + ");'/>  ";
                dl += catName;
                dl += "  <img id='check' src='icons/check.png' onclick='javascript: addCategory(" + catId + ")'/>";
                dl += "</dt><dd id='child" + catId + "'></dd></dl>";
                return dl;
            }

</script>

<div id="leftDiv" style="float: left; width: 40%;"> 

    <dl<c:forEach var="category" items="${availableCategories}">
            <c:if test="${category.parentId == 0}">
                <dt id="${category.catId}">
                <img id="more${category.catId}" src="icons/add.png" onclick="javascript: openChild(${category.catId});"/>
                <img hidden="hidden" id="less${category.catId}" src="icons/minus.png" onclick="javascript: closeChild(${category.catId});"/>
                ${category.name} 
                <img id="check" src="icons/check.png" onclick="javascript: addCategory(${category.catId});"/>
                </dt>
                <dd id="child${category.catId}"></dd>
            </c:if>
        </c:forEach>
    </dl>


</div> 
<div id="rightDiv" style="float: left; width: 40%; ">
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
</div>
