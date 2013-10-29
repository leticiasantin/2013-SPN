<%-- 
    Document   : welcome
    Created on : 29/07/2013, 09:48:31
    Author     : dskaster
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty user.userId}">
    <h1>Bem-vindo ${user.name}!</h1>
    <table>
        <tr>
            <td>  
                <div id="img">
                    <img src="imagens/profiles/${user.userId}.jpg" width="200" height="200"/><br/>
                    <a href="javascript:openPopup('imageUpload.jsp?type=p&id=${user.userId}');">Alterar Foto</a>
                </div>  
            </td>
        </tr>
        <tr>
            <td> <br/><br/>Dados Pessoais <hr></td>

        </tr>
        <tr>
            <td>Nome: ${user.name} </td>
        </tr>
        <tr>
            <td>Login: ${user.login} </td>
        </tr>
        <tr>
            <td>Data de Nascimento: ${user.dtOfBirth} </td>
        </tr>
        <tr>
            <td>Estado: ${user.state}</td>
        </tr>
        <tr>
            <td>Cidade: ${user.city}</td>
        </tr>
        <tr>
            <td>CEP: ${user.zipCode} </td>
        </tr>
        <tr>
            <td>Endereço: ${user.street} nº: ${user.number} </td>
        </tr>
        <c:if test ="${user.complement} is not null" >
            <tr>
                <td>
                    Complemento: ${user.complement} 
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Bairro: ${user.neighborhood} </td>
        </tr>
    </table>
</c:if>
