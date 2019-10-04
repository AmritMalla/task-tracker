<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>TT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>

</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: darkslateblue">
        <div><a href="#" class="navbar-brand">Task Tracker</a></div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Todos</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Tasks</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new"
               class="btn btn-primary">Add taks</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Date</th>
                <th>Work Hour</th>
                <th>How productive you were</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="task" items="${listTask}">

                <tr>
                    <td><c:out value="${task.title}"/></td>
                    <td><c:out value="${task.description}"/></td>
                    <td><c:out value="${task.taskDate}"/></td>
                    <td><c:out value="${task.taskHour}"/></td>
                    <td><c:out value="${task.productivity}"/></td>

                    <td>
                        <a href="edit?id=<c:out value='${task.id}' />" class="btn btn-primary">Edit</a>
                        <a href="delete?id=<c:out value='${todo.id}' />" class="btn btn-danger">Delete</a>
                        <a href ="update?id=<c:out value='${task.id}'/>" class="btn btn-success">Update</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>

<jsp:include page="../shared/footer.jsp"></jsp:include>
</body>
</html>