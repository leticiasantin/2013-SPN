<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="category" class="br.uel.entity.Category"/>
<form action="Controller" method="GET">
    <caption><h2>Avaliação do Prestador Codigo do serviço ${param.serviceId} </h2></caption>
    <table>
        <tr>
            <td>Categoria do Serviço</td>
            <td>
                <select name="category" id="select">
                    <option id="catId" name="catId" value="0"></option>    
                    <c:forEach var="category" items="${userCategories}">
                        <option id="cat" name="cat" value="${category.catId}">${category.name}</option>    
                    </c:forEach>
                </select>

            </td>
        </tr>
        <tr>
            <td>Pagamento Adequado</td>
            <td><select name="pAppropriatePayment">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
            </td>
        </tr>
        <tr>

            <td>Fornecimento de Materiais</td>
            <td><select name="pMaterialsSupply">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select></td>
        </tr>
        <tr>
            <td>Facilidade de comunicação com o Cliente</td>
            <td><select name="pComunicationWithClient">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select></td>
        </tr>
        <tr>
            <td colspan="2">Deixe seu comentário sobre o serviço e o cliente</td>
        </tr>
        <tr>
            <td colspan="2"><textarea name="pComment" maxlenght="1024" rows="10" cols="50" ></textarea></td>
        </tr>
    </table>
    <a href="javascript:openPopup('imageUpload.jsp?type=s&id=${param.serviceId}&userType=p');">Inserir Foto</a>
    <input type="hidden" name="serviceId" value="${param.serviceId}">
    <input type="hidden" name="c" value="doServiceEvaluation">
    <input type="hidden" name="m" value="providerEvaluation">
    <input type="submit" value="sub"/>
</form>