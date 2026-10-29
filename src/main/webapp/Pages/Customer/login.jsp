<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/Pages/Customer/login.css">
</head>
<body>
    <div class="login">
        <h2>User Login</h2>
         <form action="${pageContext.request.contextPath}/login" method="post">
         <input type="hidden" name="loginType" value="customer">

            <label for="email">Email:</label>
            <input type="text"
                   name="email"
                   id="email"
                   value="${requestScope.email}">
            <br><br>

            <label for="phoneno">Phoneno:</label>
            <input type="number"
                   name="phoneno"
                   id="phoneno"
                   value="${requestScope.phoneno}">
            <br><br>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password"><br><br>

            <button type= "submit">Login</button>
            <br><br>

            <p>
                New User?
            <a href="${pageContext.request.contextPath}/Pages/Customer/register.jsp">Register</a>
            </p>
            <br><br>
        </form>
    </div>
    <% String errorMessage = (String) request.getAttribute("errorMessage");  
    
    if (errorMessage != null) {
    %>
    <script>
        alert("<%= errorMessage %>");
    </script>
    <%
    }
    %>
    <script src="${pageContext.request.contextPath}/Pages/Customer/login.js"></script>
</body>
</html>