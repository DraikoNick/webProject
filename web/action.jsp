<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Add task</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/page.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
<header>
    <ul id="menu">
        <li id="btn"><a href="<c:url value='/index.jsp'/>">User: ${user.name}</a></li>
        <li id="btn"><a href="<c:url value='/main?list_type=today'/>">Tasks</a></li>
        <li id="btnR"><a href="<c:url value='/logout'/>">Logout</a></li>
    </ul>
</header>

<table id="tasks">

    <form id="form_change" name="form_change" action="" method="POST" enctype="multipart/form-data">
        <p id="error" style="color: red">${error}</p>
        <input type="hidden" name="pressed_button" />
        <table>
            <thead>
                <tr>
                    <td>Task</td>
                    <td>Date</td>
                    <c:if test="${not empty param.change}">
                        <td>Status</td>
                    </c:if>
                    <td>File</td>
                </tr>
            </thead>
            <tbody>
            <tr>
                <c:if test="${not empty param.add}">
                    <td><input type="text" id="taskName" name="taskName" value=""/></td>
                    <td>
                        <input type="date" id="taskDate" name="taskDate" value="<%= (new java.sql.Date(new Date().getTime()))%>"
                               min="<%= (new java.sql.Date(new Date().getTime()))%>">
                    </td>
                    <td>
                        Choose file for upload: <input type="file" name="fileName"/>
                    </td>
                </c:if>
                <c:if test="${not empty param.change}">
                    <c:forEach var="task" items="${taskstochange}">
                        <tr>
                        <input type="hidden" id="taskid${task.id}" name="taskid" value="${task.id}"/>
                        <td><input type="text" id="taskName${task.id}" name="taskName${task.id}" value="${task.name}"/></td>
                        <td><input type="date" id="taskDate${task.id}" name="taskDate${task.id}" value="${task.date}"
                                   min="<%= (new java.sql.Date(new Date().getTime()))%>"> </td>
                        <td>
                            <select id = "statusList${task.id}" name="taskStatus${task.id}">
                                <option value="${task.status}" selected="selected">${task.status}</option>
                                <option value = "TODO">TODO</option>
                                <option value = "FIXED">FIXED</option>
                                <option value = "DELETED">DELETED</option>
                            </select>
                        </td>
                        <td><a href="<c:url value='/download?file=${task.fileName}'/>">${task.fileName}</a></td>
                        <td>
                                Change File: <input type="file" name="fileName"/>
                        </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tr>
            <tr>
                    <c:if test="${not empty param.change}">
                        <td>
                            <input type="button" id="change" name="savebtn" value="Change" onclick="confirmChanges(this.value)"/>
                        </td>
                    </c:if>
                    <c:if test="${not empty param.add}">
                        <td>
                            <input type="button" id="create" name="savebtn" onclick="confirmAdding(this.value)" value="Add"/>
                        </td>
                    </c:if>
            </tr>
            </tbody>
        </table>
    </form>

    <footer>
        <a href="mailto:imekov.nikita@gmail.com">Imekov 'DraikoN' Nikita did it.</a>
    </footer>
</body>
</html>
