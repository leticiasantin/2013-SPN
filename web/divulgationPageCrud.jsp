<script  src="js/toMD5.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#create").click(function() {
            $("#create").hide();
            $.ajax({
                type: "POST",
                url: "Controller",
                data: {
                    c: "doDivulgationPageCrud",
                    m: "create",
                    pId: "${user.userId}"
                }
            }).success(function(data) {
                location.reload();
                $("#isntAProvider").hide();
            }).error(function() {
                alert('Falha ao salvar');
            });
        });


    });

</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dPage" class="br.uel.entity.DivulgationPage" scope="request"/>  

<c:choose>

    <c:when test="${not empty dPage}">

        <form id="profileForm"  name="profileForm" action="Controller"  >
            <input type="hidden" id="pageId" name="pageId" value="${dPage.profileId}" />
            <input type="hidden" id="pId" name="pId" value="${dPage.providerId}" />
            <table>
                <tr>
                    <td>  
                        <textarea id="pDescription" name="pDescription" rows="10" cols="40" maxlength="1024" >${dPage.description}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        Fotos de Divulgação
                    </td>
                    <td>
                         <a href="javascript:openPopup('imageUpload.jsp?type=page&id=${dPage.profileId}');">Inserir Foto</a>
                    </td>
                </tr>
            </table>

            <div id="images">
                <c:forEach var="picture" items="${dPage.pictures}" >
                    <img src="${picture}" />
                </c:forEach>
            </div>

            <input type="checkbox" id="delPage" name="delPage" value="t"/>Cancelar Minha Página<br>
            <input type="submit" id="submit" name="submit" value="Atualizar" />
            <input type="hidden" id="c" name="c" value="doDivulgationPageCrud" />
            <input type="hidden" id="m" name="m" value="save" /> 
        </form>

    </c:when>
    <c:otherwise>
        <nav id="isntAProvider">
            <h1>Você não está cadastrado como Prestador</h1>
            <input type="button" id="create" value="Criar Minha Conta" />
        </nav>
    </c:otherwise>
</c:choose>
