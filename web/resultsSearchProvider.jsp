<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<jsp:useBean id="provider" class="br.uel.entity.ProviderSought"/>
    <c:forEach var="provider" items="${providersSought}" varStatus="status">
        <p> <a href="Controller?c=doLoadDivulgationPage&uid=${provider.id}" target="_blanck">
            ${provider.userName} <br/>
            ${provider.city} - ${provider.state}
        </a> </p>
    </c:forEach>