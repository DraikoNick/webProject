<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
  <head>
    <title>Main page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/page.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
  </head>
  <body>
  <header>
    <ul id="menu">
      <li id="btn"><a href="<c:url value='/index.jsp'/>">User: ${user.name}</a></li>
      <li id="btn"><a href="<c:url value='/main?list_type=today'/>">Today ${today}</a></li>
      <li id="btn"><a href="<c:url value='/main?list_type=tomorrow'/>">Tomorrow ${tomorrow}</a></li>
      <li id="btn"><a href="<c:url value='/main?list_type=someday'/>">Someday</a></li>
      <li id="btn"><a href="<c:url value='/main?list_type=fixed'/>">Fixed</a></li>
      <li id="btn"><a href="<c:url value='/main?list_type=recbyn'/>">Recycle Bin</a></li>
      <li id="btnR"><a href="<c:url value='/logout'/>">Logout</a></li>
    </ul>
  </header>

    <table id="tasks">
      <thead>
        <th></th><th>Task</th><th>Date</th><th></th>
      </thead>
      <tbody>
        <form id="form_tasks" name="form_tasks" action="" method="POST" onsubmit="return false">
            <p id="error" style="color: red">${error}</p>
            <input type="hidden" name="pressed_button" />
        <c:forEach var="task" items="${tasks}">
          <tr>
            <td><input type="checkbox" id="check${task.id}" name="idTask" value="${task.id}"/></td>
            <td>${task.name}</td>
            <td>${task.date}</td>
            <td><a href="<c:url value='/download?file=${task.fileName}'/>">${task.fileName}</a> </td>
            <c:if test="${param.list_type != 'fixed'}">
              <td><input type="submit" name="action" value="Done"   onclick="doAction(${task.id}, this.value)  "/></td>
            </c:if>
            <td><input type="submit" name="action" value="Change" onclick="doAction(${task.id}, this.value+0)"/></td>
            <c:if test="${param.list_type != 'recbyn'}">
              <td><input type="submit" name="action" value="Delete" onclick="doAction(${task.id}, this.value)  "/></td>
            </c:if>

          </tr>
        </c:forEach>
        <c:if test="${fn:length(tasks)>0}">
          <tr>
            <c:if test="${param.list_type != 'fixed'}">
              <td><input type="button" name="action"   value="Done"   onclick="doActions(this.value)  "/></td>
            </c:if>
            <!--<td><input type="button" name="action"   value="Change" onclick="doActions(this.value+0)"/></td>-->
            <c:if test="${param.list_type != 'recbyn'}">
              <td><input type="button" name="action"   value="Delete" onclick="doActions(this.value)  "/></td>
            </c:if>
          </tr>
        </c:if>
        </form>
        <form id="form_add" name="form_add" action="<c:url value='/action.jsp?add=yes'/>" method="POST" onsubmit="return false">
          <input type="hidden" name="pressed_button" />
          <tr>
            <td><input type="button" name="action" value="Add" onclick="addTask(this.value)"/></td>
          </tr>
        </form>
      </tbody>

  <footer>
    <a href="mailto:imekov.nikita@gmail.com">Imekov 'DraikoN' Nikita did it.</a>
  </footer>
  </body>
</html>
