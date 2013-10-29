<%-- 
    Document   : categoriesPage
    Created on : Oct 28, 2013, 11:43:43 PM
    Author     : leticia
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

    function openChild(cId) {
        childId = "#child" + cId;
        var objects = "";
        <c:forEach var="category" items="${catList}">
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
            dl += "<a href='Controller?c=doLoadCategoryPage&catId="+catId+"'>"+catName+" </a>";
            dl += "</dt><dd id='child" + catId + "'></dd></dl>";
            return dl;
        }
        
       
</script>
<table>
    <dl>
        <c:forEach var="category" items="${catList}">
            <c:if test="${category.parentId == 0}">
                <dt id="${category.catId}">
                <img id="more${category.catId}" src="icons/add.png" onclick="javascript: openChild(${category.catId});"/>
                <img hidden="hidden" id="less${category.catId}" src="icons/minus.png" onclick="javascript: closeChild(${category.catId});"/>
                <a href="Controller?c=doLoadCategoryPage&catId=${category.catId}" >${category.name} </a>
                </dt>
                <dd id="child${category.catId}"></dd>
            </c:if>
        </c:forEach>
    </dl>
</table>
