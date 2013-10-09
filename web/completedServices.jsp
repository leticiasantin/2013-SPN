<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="comServ" class="br.uel.entity.CompletedService"/>
<jsp:useBean id="picture" class="br.uel.entity.Picture" />
<script type="text/javascript">
    function showDetails(serviceId) {
        id = "#more" + serviceId;
        $(id).show();
    }
</script>
<table border="1">
    <caption> Serviços Finalizados </caption>
    <tr>
        <th id="serviceId">Número do serviço</th>
        <th id="description">Descrição</th>
        <th id="clientRequestDat">Data de requisição</th>
        <th id="providerResponseDat">Data de resposta</th>
        <th id="providerName">Nome do Prestador</th>
        <th id="showMore"></th>
    </tr>
    <c:forEach var="comServ" items="${asProviderList}">
        <tr>
            <td>${comServ.service.serviceId}</td>
            <td>${comServ.service.description}</td>
            <td>${comServ.service.clientRequestDat}</td>
            <td>${comServ.service.providerResponseDat}</td>
            <td>${comServ.service.providerName}</td>
            <td onclick="javascript:showDetails(${comServ.service.serviceId});">+Detalhes</td>
        </tr>
        <tr id="more${comServ.service.serviceId}" hidden="hidden">
            <td colspan="3">
                <table>
                    <tr>
                        <td><b>Preço Cobrado: </b></td>
                        <td>${comServ.serviceEvaluation.cPrice} </td>
                    </tr>
                    <tr>
                        <td><b>Prazo combinado:</b> </td>
                        <td>${comServ.serviceEvaluation.cRespectForDeadlines}</td>
                    </tr>
                    <tr>
                        <td><b>Qualidade de Serviço:</b></td>
                        <td> ${comServ.serviceEvaluation.cQualityOfService} </td>
                    </tr>
                    <tr>
                        <td><b>Qualidade de Atendimento:</b>  </td>
                        <td>${comServ.serviceEvaluation.cQualityOfCare}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><b>Comentário:</b> </td>
    
                    </tr>
                    <tr>
                        <td colspan="2"> ${comServ.serviceEvaluation.cComment} <br/> </td>
                    </tr>

                </table>



                
                <b>Fotos</b>
                <c:forEach var="picture" varStatus="index" items="${comServ.serviceEvaluation.cPictures}">
                    <a href="imagens/services/c/${picture.pictureId}.jpg" target="_blank"> <img width="100" height="100" src="imagens/services/c/${picture.pictureId}.jpg"></a>

                    <c:if test="${index.count == 3}">
                        <br/>
                    </c:if>
                </c:forEach>

            </td>
            <td colspan="3">
                <table>
                    <tr>
                        <td><b> Pagamento Adequado:</b></td>
                        <td>${comServ.serviceEvaluation.pAppropriatePayment}</td>
                    </tr>
                    <tr>
                        <td><b> Fornecimento de Materiais:</b></td>
                        <td>${comServ.serviceEvaluation.pMaterialsSupply}</td>
                    </tr>
                    <tr>
                        <td> <b> Facilidade de Comunicação:</b></td>
                        <td>${comServ.serviceEvaluation.pComunicationWithClient} </td>
                    </tr>
                    <tr>
                        <td colspan="2"><b>Comentário:</b></td>
                    </tr>
                    <tr>
                        <td colspan="2">${comServ.serviceEvaluation.pComment}</td>
                    </tr>
                </table>
                
                 <c:forEach var="picture" varStatus="index" items="${comServ.serviceEvaluation.pPictures}">
                    <a href="imagens/services/p/${picture.pictureId}.jpg" target="_blank"><img width="100" height="100" src="imagens/services/p/${picture.pictureId}.jpg"></a>
                        <c:if test="${index.count == 3}">
                        <br/>
                    </c:if>
                </c:forEach>
            </td>

        </tr>
    </c:forEach>
</table>
<hr>  <hr>  


<c:forEach var="comServ" items="${asClientList}">
    1
</c:forEach>
