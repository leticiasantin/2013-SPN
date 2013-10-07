<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id='comServ' class="br.uel.entity.CompletedService"/>
<table>
<c:forEach var="comServ" items="${asProviderList}">
    <tr>
        <td>${comServ.service.serviceId}</td>
        <td>${comServ.service.description}</td>
        <td>${comServ.service.clientRequestDat}</td>
        <td>${comServ.service.providerResponseDat}</td>
        <td>${comServ.service.providerName}</td>
    </tr>
    <tr id="more${comServ.service.serviceId}">
        <td>
            Preço Cobrado: ${comServ.serviceEvaluation.cPrice} <br/>
            Prazo combinado: ${comServ.serviceEvaluation.cRespectForDeadlines} <br/>
            Qualidade de Serviço: ${comServ.serviceEvaluation.cQualityOfService} <br/>
            Qualidade de Atendimento: ${comServ.serviceEvaluation.cQualityOfCare} <br/>
            Comentário: <br/> ${comServ.serviceEvaluation.cComment} <br>
            
        </td>
        <td>
            Pagamento Adequado: ${comServ.serviceEvaluation.pAppropriatePayment} <br/>
            Fornecimento de Materiais: ${comServ.serviceEvaluation.pMaterialsSupply} <br/>
            Facilidade de Comunicação: ${comServ.serviceEvaluation.pComunicationWithClient} <br/>
            Comentário: <br/> ${comServ.serviceEvaluation.pComment} <br>
        </td>
        
    </tr>
</c:forEach>
</table>
<hr>  <hr>  


<c:forEach var="comServ" items="${asClientList}">
    1
</c:forEach>
