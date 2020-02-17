<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/page.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
<header>
    <ul id="menu">
        <li id="btn"><a href="index.jsp">User:
            <c:if test="${empty user.name}">
                Guest
            </c:if>
            ${user.name}
        </a></li>
        <c:if test="${not empty user.name}">
            <li id="btn"><a href="<c:url value='/main?list_type=today'/>">Tasks</a></li>
        </c:if>
        <c:if test="${not empty user.name}">
            <li id="btnR"><a href="<c:url value='/logout'/>">Logout</a></li>
        </c:if>
    </ul>
</header>
<h2>Error occurred while processing the request</h2>
<!--<p>Type: <%=exception%></p>-->
<p><%=message%></p>
<footer>
    <a href="mailto:imekov.nikita@gmail.com">Imekov 'DraikoN' Nikita did it.</a>
</footer>
</body>
</html>
