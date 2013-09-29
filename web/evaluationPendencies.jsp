<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="service" class="br.uel.entity.Service" scope="request"/>
<table border="1">
    <caption> Avaliações como Cliente </caption>
    <tr>
        <th id="number">Número </th>
        <th id="serviceId">Número do serviço</th>
        <th id="cName">Nome do Cliente</th>
        <th id="startDate">Data inicial</th>
        <th id="finishDate">Data final</th>
        <th id="evaluation">Avaliação</th>
    </tr>
    <c:forEach var="service" varStatus="status" items="${asClient}">
        <tr>
          <td headers="number"></td>
          <td headers="serviceId">${service.serviceId}</td>
          <td headers="cName">${service.providerName}</td>
          <td headers="startDate">${service.startDate}</td>
          <td headers="finishDate">${service.finishDate}</td>
        <td headers="evaluation"></td>  
        </tr>
        ${service.serviceId}
    </c:forEach>
</table>
    
    
    Prestador
    <c:forEach var="service" varStatus="status" items="${asProvider}">
        ${status}
    </c:forEach>
