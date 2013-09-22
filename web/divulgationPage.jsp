    <%request.removeAttribute("uid"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dPage" class="br.uel.entity.DivulgationPage" scope="request"/>
<table id="pageTable">
    <tr>
        <td>Nome do Prestador:</td>
        <td>dPage.providerName</td>
    </tr>
    <tr border="1">
        <td>Descrição do Prestador</td>
        <td><i>${dPage.description}</i></td>
    </tr>    
</table>
<c:choose>
    <c:when test="${dPage.profileId != 0}">
        <script type ="text/javascript">
            $("#pageTable").show();
         </script>
    </c:when>
    <c:otherwise>s
    </c:otherwise>
</c:choose>
    

