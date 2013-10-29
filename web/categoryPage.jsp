<%-- 
    Document   : category
    Created on : Oct 22, 2013, 8:32:56 PM
    Author     : leticia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="provider" class="br.uel.entity.ProviderSought"/>
<jsp:useBean id="category" class="br.uel.entity.Category" scope="request"/>
<h2>Categoria: ${category.name}</h2>
<h3> Prestadores nessa Categoria: <b>${nProviders}</b></h3>
<table border="1">
    <caption>Top-5</caption>
    <tr>
        <th id="criteria">Critério </th>
        <th id="first">1</th>
        <th id="second">2</th>
        <th id="third">3</th>
        <th id="fourth">4</th>
        <th id="fifth">5</th>
    </tr>
    <tr>
        <td id="price">Preço</td>
        <c:forEach var="provider" items="${topsByPrice}">
            <td>
                <a href="Controller?c=doLoadDivulgationPage&uid=${provider.id}">
                ${provider.name} </a>
            </td>

        </c:forEach>
    </tr>
    <tr>
        <td id="deadlines">Prazo</td>
        <c:forEach var="provider" items="${topsByDeadlines}">
            <td>
                 <a href="Controller?c=doLoadDivulgationPage&uid=${provider.id}">
                ${provider.name} </a>
            </td>

        </c:forEach>
    </tr>
    <tr>
        <td id="qos">Qualidade de Serviço</td>
        <c:forEach var="provider" items="${topsByQoS}">
            <td>
                 <a href="Controller?c=doLoadDivulgationPage&uid=${provider.id}">
                ${provider.name} </a>
            </td>

        </c:forEach>
    </tr>
    <tr>
        <td id="qoc">Qualidade de Atendimento</td>
        <c:forEach var="provider" items="${topsByQoC}">
            <td>
                 <a href="Controller?c=doLoadDivulgationPage&uid=${provider.id}">
                ${provider.name} </a>
            </td>

        </c:forEach>
    </tr>
    <tr>
        <td id="lastDays">Contratados nos últimos 7 dias</td>
        <c:forEach var="provider" items="${topsLastDays}">
            <td>
                 <a href="Controller?c=doLoadDivulgationPage&uid=${provider.id}">
                ${provider.name} </a>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <td id="linearComb">Combinação Linear*</td>       
        <c:forEach var="provider" items="${topsByLinComb}">
            <td>
                  <a href="Controller?c=doLoadDivulgationPage&uid=${provider.id}">
                ${provider.name} </a>
            </td>
        </c:forEach>
    </tr>
</table>
<form name="weightsForm" action="Controller">
    <table>
        <caption>* Atribua pesos para os criterios</caption>
        <tr>
            <td>
                Preço
            </td>
            <td>
                <input type="text" id="w1" name="w1" size="3" maxlenght="2" value="${param.w1}" />
            </td>
        </tr>
        <tr>
            <td>
                Prazo
            </td>
            <td>
                <input type="text" id="w2" name="w2" size="3" maxlenght="2" value="${param.w2}"/>

            </td>
        </tr>
        <tr>
            <td>
                Qualidade de Serviço
            </td>
            <td>
                <input type="text" id="w3" name="w3" size="3" maxlenght="2" value="${param.w3}"/>
            </td>
        </tr>
        <tr>
            <td>
                Qualidade de Tratamento com o cliente
            </td>
            <td>
                <input type="text" id="w4" name="w4" size="3" maxlenght="2" value="${param.w4}"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="submit" />
                <input type="reset" value="reset" />
            </td>
        </tr>
    </table>
    <input type="hidden" name="c" value="doLoadCategoryPage"/>
    <input type="hidden" name="catId" value="${category.catId}"/>
</form>
