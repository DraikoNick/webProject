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
            <c:forEach items="${sectionsMap}" var="entry">
                <li id="btn"><a href="<c:url value='/main?list_type=${entry.key}'/>">${entry.value}</a></li>
            </c:forEach>
            <li id="btnR"><a href="<c:url value='/logout'/>">Logout</a></li>
        </ul>
      </header>

    <table id="tasks">

      <thead>
        <th></th><th>Task</th><th>Date</th><th>File</th><th colspan="3">Actions</th>
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
                      <c:forEach items="${taskButtons}" var="entry">
                          <td><input type="submit" name="action" value="${entry.key}" onclick="doAction(${task.id}, '${entry.value}')"/></td>
                      </c:forEach>
                  </tr>
              </c:forEach>

              <c:if test="${fn:length(tasks)>0}">
                  <tr><td colspan="2">
                      <c:forEach items="${sectionButtons}" var="entry">
                          <input type="button" name="action"   value="${entry.key}" onclick="doActions('${entry.value}')"/>
                      </c:forEach>
                  </td></tr>
              </c:if>
          </form>

          <c:if test="${param.list_type != 'FIXED' && param.list_type != 'RECBYN'}">
              <form id="form_add" name="form_add" action="<c:url value='/action.jsp?add=yes'/>" method="POST" onsubmit="return false">
                  <input type="hidden" name="pressed_button" />
                  <tr>
                      <td><input type="button" name="action" value="Add" onclick="addTask(this.value)"/></td>
                  </tr>
              </form>
          </c:if>

      </tbody>

  <footer>
    <a href="mailto:imekov.nikita@gmail.com">Imekov 'DraikoN' Nikita did it.</a>
  </footer>
  </body>
</html>
