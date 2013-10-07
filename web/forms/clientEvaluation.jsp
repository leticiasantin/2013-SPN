<%-- 
    Document   : formClientEvaluation
    Created on : Sep 26, 2013, 3:28:48 PM
    Author     : leticia
--%>
<script  src="js/jquery.js"></script>
<script  src="js/jquery/js/jquery-ui.js"></script>        
<link type="text/css" href="js/jquery/css/south-street/jquery-ui.css" rel="stylesheet" />

<script type="text/javascript">
    $(document).ready(function() {
        $("#startDate").datepicker({dateFormat: 'yy-mm-dd', maxDate: 'today',
            yearRange: '-0:+10', changeMonth: true});
        $("#finishDate").datepicker({dateFormat: 'yy-mm-dd', maxDate: 'today',
            yearRange: '-0:+10', changeMonth: true});
    });
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="Controller" method="GET">
    <caption><h2>Avaliação do Cliente Codigo do serviço ${param.serviceId}</h2></caption>
    <table>
        <tr>
            <td>Data Real de Inicio do Serviço</td>
            <td><input type="text" id="startDate" name="cRealStartDate"/></td>

        </tr>
        <tr>
            <td>Data Real de Fim do Serviço</td>
            <td><input type="text" id="finishDate" name="cRealFinishDate"/></td> 
        </tr>
        <tr> 
            <td>Preço Cobrado</td>
            <td><select name="cPrice">
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

            <td>Respeito aos prazos</td>
            <td><select name="cRespectForDeadlines">
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
            <td>Qualidade de serviços</td>
            <td><select name="cQualityOfService">
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
            <td>Qualidade do atendimento</td>
            <td><select name="cQualityOfCare">
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
            <td colspan="2">Deixe seu comentário sobre o serviço e o prestador</td>
        </tr>
        <tr>
            <td colspan="2"><textarea name="cComment" maxlenght="1024" rows="10" cols="50" ></textarea></td>
        </tr>
    </table>
      <a href="javascript:openPopup('imageUpload.jsp?type=s&id=${param.serviceId}&userType=c');">Inserir Foto</a>
    <input type="hidden" name="serviceId" value="${param.serviceId}">
    <input type="hidden" name="c" value="doServiceEvaluation">
    <input type="hidden" name="m" value="clientEvaluation">
    <input type="submit" value="sub"/>
</form>