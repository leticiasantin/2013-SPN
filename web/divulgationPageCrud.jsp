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
<jsp:useBean id="profile" class="br.uel.entity.DivulgationPage" scope="session"/>  

<form id="profileForm"  name="profileForm" action="Controller" hidden="hidden" >
    <input type="hidden" id="pageId" name="pageId" value="${profile.profileId}" />
    <input type="hidden" id="pId" name="pId" value="${profile.providerId}" />
    <jsp:include page = "forms/profile.jsp" /> 
    <input type="checkbox" id="delPage" name="delPage" value="t"/>Cancelar Minha Página<br>
    <input type="submit" id="submit" name="submit" value="Atualizar" />
    <input type="hidden" id="c" name="c" value="doDivulgationPageCrud" />
    <input type="hidden" id="m" name="m" value="save" /> 
</form>
<c:choose>
    <c:when test="${not empty param.owner}">
        
    </c:when>
    <c:when test="${profile.profileId != 0}">
        <script type="text/javascript">
            $("#profileForm").show();
        </script>
    </c:when>
    <c:otherwise>
        <nav id="isntAProvider">
            <h1>Você não está cadastrado como Prestador</h1>
            <input type="button" id="create" value="Criar Minha Conta" />
        </nav>
    </c:otherwise>
</c:choose>
