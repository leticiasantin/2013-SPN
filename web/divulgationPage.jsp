<%request.removeAttribute("uid");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dPage" class="br.uel.entity.DivulgationPage" scope="request"/>
<jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
<jsp:useBean id="category" class="br.uel.entity.Category"/>
<jsp:useBean id="picture" class="br.uel.entity.Picture"/>
<c:choose>
    <c:when test="${dPage.profileId != '0'}">
        <c:if test="${not empty owner}">
            <a href="Controller?c=doDivulgationPageCrud&m=edit&dPageId=${dPage.providerId}">
                Editar Página </a>
            </c:if>

        <table id="pageTable">
            <c:if test="${dPage.user != null}">
            <caption><h2>Dados do Usuário</h2></caption>
            <tr>
                <td>Nome:</td>
                <td>${dPage.user.name}</td>
            </tr>
            <tr>
                <td>Endereço:</td>
                <td>
                    ${dPage.user.street},${dPage.user.number} ${dPage.user.complement}<br/>
                        ${dPage.user.neighborhood}, ${dPage.user.city}-${dPage.user.state}            
                    
                </td>
            </tr>
            </c:if>
            <tr>
                <td>Descrição do <br/> Prestador</td>
                <td><i>${dPage.description}</i></td>
            </tr>  
        </table>

        <h2>Categorias do Prestador</h2>

        <c:forEach var="category" items="${dPage.categories}" varStatus="index">
            <a href="Controller?c=doSearchProvider&m=searchByCategory&catId=${category.catId}" >${category.name}&nbsp;&nbsp;</a>
            <c:if test="${index.count == 5}">
                <br/>
            </c:if>

        </c:forEach>
                <hr><hr>
        <c:forEach var="picture" items="${dPage.pictures}" varStatus="index">
            <img width="200" height="200" src="imagens/pages/${picture.pageId}-${picture.pictureId}.jpg">
            <c:if test="${index.count == 5}">
                <br/>
            </c:if>
            
        </c:forEach>


    </c:when>
    <c:otherwise>
        Não tem Página
        <a href="Controller?c=doDivulgationPageCrud&m=create&pId=${user.userId}" >Criar Página</a>
    </c:otherwise>
</c:choose>

