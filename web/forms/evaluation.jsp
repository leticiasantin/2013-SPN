<%-- 
    Document   : evaluation
    Created on : Sep 28, 2013, 1:45:51 PM
    Author     : leticia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${not empty request.serviceId}">
        <c:choose>
            <c:when test="${not empty param.providerId}">
                Provider
            </c:when>
            <c:when test="${not empty param.clientId}">
                Client
            </c:when>
            <c:otherwise>
                sem parametro
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        Sem service Id
    </c:otherwise>
</c:choose>


