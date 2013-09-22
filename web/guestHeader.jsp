<%-- 
    Document   : guestHeader
    Created on : Sep 18, 2013, 11:23:01 PM
    Author     : leticia
--%>
<script type="text/javascript">
    $(document).ready(function() {
        $("#searchProviders").click(function(){
        $("#c").val('doSearchProvider');
        $("#m").val('show');
        $("#formHeader").submit();
        });
    });
</script>

<form id="formHeader" name="formHeader" method="POST" action="Controller">
<table style=" border: black 2px;">
    <tr>
        <td id="searchProviders">Buscar Prestadores</td>
        <td>Guest Head2</td>
        <td>Guest Head3</td>
    </tr>
</table>
