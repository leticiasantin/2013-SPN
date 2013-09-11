<%-- 
    Document   : myProfile
    Created on : Aug 28, 2013, 11:16:24 PM
    Author     : leticia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
        <jsp:useBean id="profile" class="br.uel.entity.Profile" scope="request"/>
        <jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
        <c:import url="header.jsp" />  
        <c:choose>
            <c:when test="${profile.profileId != 0}">
                <c:import url="forms/profile.jsp?profile=${user.userId}" /> 
                <c:forEach var="category" items="${profile.categories}">
                    <label>categoria </label>
                    <label id="${category.catId}"></label> ${category.name}<br/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h1>Você não está cadastrado como Prestador</h1>
            </c:otherwise>
        </c:choose>
        <table>
            <tr>
                <td> <br/><br/>Dados Pessoais <hr></td>

            </tr>
            <tr>
                <td>Nome: ${user.name} </td>
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

        <c:set scope="session" var="profile" value="${profile}"/>
        <a href="profileCrud.jsp">Crie/Edit Seu Perfil </a>
    </body>
</html>
