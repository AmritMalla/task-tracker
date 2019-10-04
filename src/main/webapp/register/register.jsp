<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TT</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../shared/header.jsp"></jsp:include>

<div class="container">

    <h2>User Register Form</h2>
    <div class="col-md-6 col-md-offset-3">
        <div class="alert alert-success center" role="alert">
            <p>${NOTIFICATION}</p>
        </div>

        <form action="<%=request.getContextPath()%>/register" method="post">

            <div class="form-group">
                <label for="fname">First Name:</label>
                <input type="text" class="form-control" id="fname"
                       placeholder="First Name" name="firstName" required>
            </div>

            <div class="form-group">
                <label for="lname">Last Name:</label>
                <input type="text" class="form-control" id="lname"
                       placeholder="last Name" name="lastName" required>
            </div>

            <div class="form-group">
                <label for="username">User Name:</label>
                <input type="text" class="form-control" id="username"
                       placeholder="User Name" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password"
                       placeholder="Password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </div>

</div>


<jsp:include page="../shared/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
        integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
</body>
</html>
