<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Web page</title>
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
      <c:if test="${empty user.name}">
        <li id="btn"><a href="<c:url value='index.jsp?login=yes'/>">Login</a></li>
        <li id="btn"><a href="<c:url value='index.jsp?register=yes'/>">Registration</a></li>
      </c:if>
      <c:if test="${not empty user.name}">
        <li id="btnR"><a href="<c:url value='/logout'/>">Logout</a></li>
      </c:if>
    </ul>
  </header>
  <p>Some information about project...</p>
  <c:if test="${empty user.name}">
    <table id="loginTable" width="100%" align="center">
      <tr>
          <td><p>You can login or register</p></td>
          <c:if test="${not empty param.login}">
            <td>
              <form action="<c:url value='/login'/>" name="loginform" style="float: right" method="post" onsubmit="return false">
                <p>Username:</p> <input type="text" name="usernameLogin">
                <p>Password:</p> <input type="password" name="passwordLogin">
                <p><input type="submit" name="login" value="Log In" onclick="return checkLoginFields()"></p>
                <p id="error" style="color: red">${error}</p>
              </form>
            </td>
          </c:if>
          <c:if test="${not empty param.register}">
            <td>
              <form action="<c:url value='/registration'/>" name="regform" style="float: right" method="post" onsubmit="return false">
                <p>Username:</p> <input type="text" name="usernameRegistration">
                <p>Password:</p> <input type="password" name="passwordRegistration">
                <p>EMail:</p> <input type="text" name="email">
                <p><input type="submit" name="reg" value="Register" onclick="return checkRegistrationFields()"></p>
                <p id="error" style="color: red">${error}</p>
              </form>
            </td>
          </c:if>
      </tr>
    </table>
  </c:if>
  <footer>
    <a href="mailto:imekov.nikita@gmail.com">Imekov 'DraikoN' Nikita did it.</a>
  </footer>
  </body>
</html>
