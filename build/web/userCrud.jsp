<script  src="js/jquery.js"></script>
<script  src="js/jquery/js/jquery-ui.js"></script>        
<link type="text/css" href="js/jquery/css/south-street/jquery-ui.css" rel="stylesheet" />
<script src="js/toMD5.js"></script>

<jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
<p><h1>Cadastro de Usuários </h1> </p>
<form name="formUser" method="post" action="Controller">
    <jsp:include page = "forms/user.jsp?edit=true" />  
    <input type="submit" id="submit" value="Ok" />
    <input type="reset" id="reset" value="Limpar"/>
    <input type="hidden" value="doUserSCRUD" id="c" name="c" />
    <input type="hidden" value="save" id="m" name="m" />
</form>