<script src="js/jquery.js"></script>
<script src="js/header.js"></script>
<link type="text/css" href="css/header.css" rel="stylesheet" />
<jsp:useBean id="user" class="br.uel.entity.User" scope="session"/>
<form id="formHeader" name="formHeader" method="post" action="Controller">
    <table id="menu">
        <tr>
            <td id="welcome">Home </td>
            <td id="profile">  Meu Perfil</td>
            <td id="pendencies">Pendencias</td>
            <td id="services">Serviços</td>
            <td id="category">Minhas Categorias</td>
            <td id="logout">Logout</td> 
            <td>${user.userId}</td>
        </tr> 
    </table>
    <input type="hidden" id="uid" name="uid" value="${user.userId}"/>
    <input type="hidden" id="c" name="c"/>
    <input type="hidden" id="m" name="m"/>
</form>
