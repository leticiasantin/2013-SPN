<%-- 
    Document   : profile
    Created on : Aug 28, 2013, 11:06:04 PM
    Author     : leticia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/profile.css"/>
<script src="js/default.js"></script>
<table>
    <tr>
        <td>  
            <div id="img">
                <img src="imagens/profiles/profile.jpg" width="200" height="200"/><br/>
                <!--<a href="javascript:openPopup('imageUpload.jsp?type=p&id=${profile.profileId}');">Alterar Foto</a>-->
            </div>  
            <h2>
                ${profile.user.name}
            </h2>
        </td>
    </tr>
    <tr>
        <td>    
            ${profile.description}
        </td>
    </tr> 
    <!-- 
    Categorias
    
    <tr>
         <td><hr> Minhas Categorias: </td>
    <c:forEach var="category" items="${category.list}">
    <td><img src="${category}"></td>
    </c:forEach>
</tr> -->
   
</table>

