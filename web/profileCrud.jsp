<%-- 
    Document   : profileCrud
    Created on : Aug 31, 2013, 1:01:48 AM
    Author     : leticia
--%>

<%@page import="java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script  src="js/jquery.js"></script>
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

             /*   $("#submit").click(function() {
                    alert("teste");
                });*/

            });

            function setValues() {
                if ($("#profileId").val() === "0") {
                    $("#bProv").val("Quero ser Prestador");
                    $("#tNId").hide();
                    $("#dNId").hide();
                    $("#img").hide();
                }
                else {
                    $("#bProv").val("Cancelar minha inscrição com Prestador");
                    $("#tNId").show();
                    $("#dNId").show();
                    $("#img").show();

                }

            }
        </script>
    </head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

    <jsp:useBean id="profile" class="br.uel.entity.Profile" scope="session"/>
    <c:import url="header.jsp" />  
    <body>
        <form id="profileForm"  name="profileForm" action="Controller">
            <jsp:include page = "errorMsg.jsp" />
            <table>
                <tr>
                    <td><input type="hidden" id="profileId" name="profileId" value="${profile.profileId}" /> </td>
                </tr>
                <tr id="dNId" hidden="hidden">
                    <td>Descrição:</td>
                    <td><textarea id="description" name="description" cols="40" rows="20" style="resize: none;">${profile.description}</textarea></td>
                </tr>   
                <tr>
                    <td>
                        <div id="img" hidden="hidden">
                            <img src="imagens/profiles/${profile.profileId}.jpg" width="200" height="200"/><br/>
                            <a href="javascript:openPopup('imageUpload.jsp?type=p&id=${profile.profileId}');">Alterar Foto</a>   
                        </div>
                    </td>
                </tr>
                <tr>
                    <td> 
                        <input type="button" id="bProv" name="bProv" value="Quero ser Prestador" /> 
                    </td>
                </tr>

                <jsp:include page = "forms/user.jsp" />  

            </table>
            <input type="checkbox" id="delUser" name="delUser" value="t"> Desativar minha conta<br>
            <input type="submit" id="submit" name="submit" value="Atualizar" />
            <input type="hidden" id="c" name="c" value="doProfileCrud" />
            <input type="hidden" id="m" name="m" value="updateAll" />
        </form>
    </body>
</html>
