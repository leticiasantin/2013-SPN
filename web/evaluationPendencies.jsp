<script type="text/javascript">
    $(document).ready(function() {
        $("#clientRef").click(function() {
            $("#asClientTable").show();
            $("#asProviderTable").hide();
        });
        $("#providerRef").click(function() {
            $("#asClientTable").hide();
            $("#asProviderTable").show();
        });
    });
</script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="service" class="br.uel.entity.Service" scope="request"/>
<ul>
    <li id="clientRef">Mostrar  como Cliente</li>
    <li id="providerRef">Mostrar como Prestador</li>
</ul>
<nav id="asClientTable"  hidden="hidden"> 
    <c:if test="${not empty asClient}">
        <table border="1" >
            <caption> Avaliações como Cliente </caption>
            <tr>
                <th id="number">Número </th>
                <th id="serviceId">Número do serviço</th>
                <th id="pName">Nome do Prestador</th>
                <th id="startDate">Data inicial</th>
                <th id="finishDate">Data final</th>
                <th id="evaluation">Avaliação</th>
            </tr>
            <c:forEach var="service" varStatus="status" items="${asClient}">
                <tr>
                    <td headers="number">${status.count}</td>
                    <td headers="serviceId">${service.serviceId}</td>
                    <td headers="pName">${service.providerName}</td>
                    <td headers="startDate">${service.startDate}</td>
                    <td headers="finishDate">${service.finishDate}</td>
                    <td headers="evaluation"><a href="Controller?c=doServiceEvaluation&m=form&type=s&serviceId=${service.serviceId}&userType=c"> <img src="icons/evaluate.png"/></a></td>   
                </tr>
            </c:forEach>
        </table>
    </c:if>
     <c:if test="${empty asClient}">
        <h2>Você não possui pendencias como Cliente</h2>
    </c:if>

</nav>


<nav id="asProviderTable"  hidden="hidden"> 
    <c:if test="${not empty asProvider}">
        <table border="1" >
            <caption> Avaliações como Prestador </caption>
            <tr>
                <th id="number">Num. </th>
                <th id="serviceId">Cod serviço</th>
                <th id="cName">Nome do Cliente</th>
                <th id="cAddress">Endereço do Cliente</th>
                <th id="description">Descrição do Serviço</th>
                <th id="startDate">Data inicial</th>
                <th id="finishDate">Data final</th>
                <th id="evaluation">Avaliação</th>
            </tr>
            <c:forEach var="service" varStatus="status" items="${asProvider}">
                <tr>
                    <td headers="number">${status.count}</td>
                    <td headers="serviceId">${service.serviceId}</td>
                    <td headers="cName">${service.clientName}</td>
                    <td headers="cAddress">${service.clientAddress}</td>
                    <td headers="description">${service.description}</td>
                    <td headers="startDate">${service.startDate}</td>
                    <td headers="finishDate">${service.finishDate}</td>
                    <td headers="evaluation"><a href="Controller?c=doServiceEvaluation&m=form&type=s&serviceId=${service.serviceId}&userType=p"><img src="icons/evaluate.png"/></a></td>  
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty asProvider}">
        <h2>Você não possui pendencias com Prestador </h2>
    </c:if>
</nav>


