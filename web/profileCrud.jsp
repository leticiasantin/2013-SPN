<script  src="js/toMD5.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        setValues();
        $("#bProv").click(function() {
            if ($("#profileId").val() === "0") {
                $.ajax({
                    type: "POST",
                    url: "Controller",
                    data: {
                        c: "doProfileCrud",
                        m: "create",
                        pId: "${user.userId}"
                    }
                }).success(function(data) {
                    alert('Favor Cadastre seus dados como Prestador de serviços');
                    location.reload();
                }).error(function() {
                    alert('Falha ao salvar');
                });
            }
            else {
                $.ajax({
                    type: "POST",
                    url: "Controller",
                    data: {
                        c: "doProfileCrud",
                        m: "delete",
                        pId: "${user.userId}"
                    }
                }).success(function(data) {
                    $("#profileId").val("0");
                    alert('Cadastro excluído com sucesso');
                    location.reload();
                }).error(function() {
                    alert('Falha ao salvar');
                });
            }
        });
    });

    
</script>

<jsp:useBean id="profile" class="br.uel.entity.Profile" scope="session"/>  
    <form id="profileForm"  name="profileForm" action="Controller">
        <table>
            <tr>
                <td> 
                    <input type="button" id="bProv" name="bProv" value="Quero ser Prestador" /> 
                </td>
            </tr>
            <jsp:include page = "forms/user.jsp" />  
            <tr>
                <td>  
                    <div id="img">
                        <img src="imagens/profiles/${user.user_id}.jpg" width="200" height="200"/><br/>
                        <a href="javascript:openPopup('imageUpload.jsp?type=p&id=${profile.profileId}');">Alterar Foto</a>
                    </div>  
                </td>
            </tr>
        </table> 
        <input type="checkbox" id="delUser" name="delUser" value="t"> Desativar minha conta<br>
        <input type="submit" id="submit" name="submit" value="Atualizar" />
        <input type="hidden" id="c" name="c" value="doProfileCrud" />
        <input type="hidden" id="m" name="m" value="updateAll" />
    </form>