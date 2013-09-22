<style type="text/css">
    table,td,th {
        border: red 2px solid;
    }
</style>
<script type="text/javascript">
    function acceptSolicitation(sId) { 
                           
        
    }
    
    function rejectSolicitation(sId){
        decisao = confirm("Deseja recusar o serviço? Esta ação é irreversível");
        if (decisao) {
            var desc;
            do {
                desc = prompt("Insira a motivo da recusa");
            } while (desc === null || desc === "");
            $.ajax({
                        type: "POST",
                        url: "Controller",
                        data: {
                            c: "doSolicitationAction",
                            m: "reject",
                            serviceId: sId,
                            reason: desc
                        }
                    }).success(function(data) {
                        alert('Solicitação cancelada com sucess. Atualize a página');
                    }).error(function() {
                        alert('Falha ao cancelar');
                    });
        }
    
    }
</script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="service" class="br.uel.entity.Service" scope="request"/>

<c:choose>
    <c:when test="${not empty providerPendencies}">
        <table>
            <caption><h2>Pendências como prestador </h2></caption>
            <tr>
                <th id="cName">Nome do Cliente</th>
                <th id="cLocal">Endereço</th>
                <th id="cDescription">Descrição do Serviço</th>
                <th id="cRequest">Dia da solicitação</th>
                <th id="pResponse">Você poderá realizar o serviço?</th>
            </tr>
            <fn
            <c:forEach var="service" items="${providerPendencies}">
                <tr>
                    <td headers="cName">${service.clientName}</td>
                    <td headers="cLoca">${service.clientAddress}</td>
                    <td headers="cDescription">${service.description}</td>
                    <td headers="cRequest"> ${service.clientRequestDat} </td>
                    <td headers="pResponse">
                        <img id="delete" src="icons/delete.png" onclick="javascript: rejectSolicitation(${service.serviceId})"/>
                        <a href="javascript:openPopup('solicitationAcceptedForm.jsp?serviceId=${service.serviceId}')"> <img src="icons/check.png"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        Você Não possui pendências como Prestador
    </c:otherwise>
  
</c:choose>



 ${fn:length(providerPendencies)}
