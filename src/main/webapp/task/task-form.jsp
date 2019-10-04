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
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> Task List </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">list</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${task != null}">
            <form action="<%=request.getContextPath()%>/update" method="post">
                </c:if>
                <c:if test="${task == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${task != null}">
                                Edit Task
                            </c:if>
                            <c:if test="${task == null}">
                                Add New Task
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${task != null}">
                        <input type="hidden" name="id" value="<c:out value='${task.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Task Title</label>
                        <input type="text"
                               class="form-control"
                               placeholder="Title..."
                               name="title" required="required" minlength="3">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Task Decription</label>
                        <input type="text"
                               placeholder="Description..."
                               class="form-control"
                               name="description" minlength="5">
                    </fieldset>

                        <fieldset class="form-group">
                            <label>Task Duration</label>
                            <input type="number"
                                   value="<c:out value='${task.taskHour}' />"
                                   placeholder="Enter the task duration in an hours"
                                   class="form-control"
                                   name="taskHour" min="0" max="24">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Productivity</label>
                            <input type="number"
                                   value="<c:out value='${task.producticity}' />"
                                   placeholder="Rate your productivity in between 0 to 100"
                                   class="form-control"
                                   name="productivity" min="0" max="100">
                        </fieldset>


                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

<jsp:include page="../shared/footer.jsp"></jsp:include>
</body>
</html>