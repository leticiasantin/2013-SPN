<script  src="js/jquery.js"></script>
<script  src="js/default.js"></script>
<script  src="js/jquery/js/jquery-ui.js"></script>        
<link type="text/css" href="js/jquery/css/south-street/jquery-ui.css" rel="stylesheet" />
<script  src="js/user/form.js"></script>
<input type="hidden" id="edit" value="${param.edit}"/>
<table>
    <tr>
        <td> <input type="hidden" id="userId" name="userId" value="${user.userId}" />          </td>
    </tr>
    <tr>
        <td> Nome </td> 
        <td> <input type="text" maexlength="50" name="name" id ="name" value="${user.name}">  </td>
    </tr>   
    <tr>
        <td> Data Nasc </td>
        <td> <input type="text" id="dtOfBirth" name="dtOfBirth" value="${user.dtOfBirth}"/>       </td>
    </tr>
    <tr id="trLogin">
        <td>Login</td>
        <td><input type="text" maxlength="15" name="login" id="login" value="${user.login}">  
        </td>
    </tr>
    <tr>
        <td>Senha</td>
        <td> <input type="password" maxlength="8" name="password" id="password" value="${user.password}"></td>
    </tr>
    <tr>
        <td>UF: </td>
        <td><select id="state" name="state" value="${user.state}">
                <option selected="selected"></option>
                <option  value="PR">PR</option>
                <option  value="SC">SC</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>Cidade: </td>
        <td><select id="city" name="city" value="${user.city}">
                <option> </option>
            </select>
        </td>
    </tr>
    <tr>
        <td>CEP: </td>
        <td><input name="zipcode" type="text" id="zipcode" maxlength="8" value="${user.zipCode}" > </td>
    </tr>
    <tr>
        <td>Logradouro: </td>
        <td><input id="street" name="street" type="text" value="${user.street}"></td>
    </tr>
    <tr>
        <td>Numero: </td>
        <td><input id="number" name="number" type="text" value="${user.number}"></td>
    </tr>
    <tr>
        <td>Complemento: </td>
        <td><input id="complement" name="complement" type="text" value="${user.complement}"></td>
    </tr>
    <tr>
        <td>Bairro: </td>
        <td><input id="neighborhood" name="neighborhood" type="text" value="${user.neighborhood}"></td>
    </tr>
    <tr hidden="hidden" id="accountDisabled">
        <td>
            <input type="checkbox" name="disable"  name="accountDisabled" value="true" />Desativar Minha Conta
        </td>
    </tr>
    
</table> 

