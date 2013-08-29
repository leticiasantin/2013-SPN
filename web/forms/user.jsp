<table>
    <tr>
        <td> <input type="hidden" id="userId" name="userId" value="${user.userId}" />          </td>
    </tr>
    <tr>
        <td> Nome </td> 
        <td> <input type="text" maxlength="50" name="name" id ="name" value="${user.name}">  </td>
    </tr>   

    <tr>
        <td> Data Nasc </td>
        <td> <input type="text" id="dtOfBirth" name="dtOfBirth" value="${user.dtOfBirth}"/>       </td>
    </tr>
     <tr>
        <td>Login</td>
        <td><input type="text" maxlength="15" name="login" id="login" value="${user.login}">  
            <span hidden>Login já existente </span> </td>
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
</table> 
    
    
    <!--<style type="text/css">
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #555555;
}
h1  
{
	font-size: 13px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #2e62b1;
	font-weight: bold;
}
h2  
{
	font-size: 10px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}
</style> -->