<%-- 
    Document   : errorMsg
    Created on : Aug 30, 2013, 1:23:13 PM
    Author     : leticia
--%>
<style type="text/css">
    #msgError {
        width: 500px;
        height: 50px;
        resize: none;
        background-color: #FF4500;
        text-align: center;
        
    }
</style>
<script src="js/jquery.js" />
<script type="text/javascript">
    function showError (msg){
        $("#msgError").val(msg);
        $("#msgError").show('slow');
    }
</script>
<input type="hidden" id="idError" value="${param.error}" />
<textarea id="msgError" hidden="true" readonly="readonly" onload="javascript:checkError();"></textarea>

