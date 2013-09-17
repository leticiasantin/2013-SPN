<%-- 
    Document   : profile
    Created on : Aug 28, 2013, 11:06:04 PM
    Author     : leticia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/profile.css"/>
<script src="js/default.js"></script>
Edit = ${param.edit};
<table>
    <tr>
        <td>  
            <textarea id="pDescription" name="pDescription" rows="10" cols="40" maxlength="1024" >
                ${profile.description}
            </textarea>
        </td>
    </tr>
    <tr>
        <td>
            Fotos de Divulgação
        </td>
    </tr>
</table>

<div id="images">
    <c:forEach var="picture" items="${profile.pictures}" >
        <img src="${picture}" />
    </c:forEach>
</div>

