
<script src="js/jquery.js"></script>
<script src="js/userMenu.js"></script>
<script src="js/default.js"></script>
<link type="text/css" href="css/menu.css" rel="stylesheet" />
<form id="formHeader" name="formHeader" method="post" action="Controller">
    <ul>
        <li id="welcome">Home </li>

        <li id="editProfile">Editar Perfil</li>

        <li id="divulgationPage">  Página de Divulgação</li>

        <li id="solicitation">Solicitações Pendentes</li>

        <li id="evaluation">Avaliações Pendentes</li>
     
        <li id="completed">Serviços Concluídos</li>

        <li id="category">Minhas Categorias</li>

        <li id="logout">Logout</li> 
    </ul>
    <ul>
        <li>${user.userId}</li>
    </ul>
    <input type="hidden" id="uid" name="uid" value="${user.userId}"/>
    <input type="hidden" id="c" name="c"/>
    <input type="hidden" id="m" name="m"/>
</form>
