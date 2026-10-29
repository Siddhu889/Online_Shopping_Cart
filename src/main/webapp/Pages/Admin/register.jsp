<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/Pages/Admin/register.css">
</head>
<body>
    <div class="register">
        <h2>Register</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
        <input type="hidden" name="registerType" value="admin">

            <label for="username">Admin name:</label>
            <input type="text" name="username" id="username" value="${requestScope.username}"> <br><br>

            <label for="email">Email:</label>
            <input type="text" name="email" id="email" value="${requestScope.email}"><br><br>

            <label for="phoneno">Phoneno:</label>
            <input type="text" name="phoneno" id="phoneno" value="${requestScope.phoneno}"><br><br>

            <label for="password">Create a new Password:</label>
            <input type="password" name="password" id="password"><br><br>

            <label for="confirmPassword">Confirm the Password:</label>
            <input type="password" name="confirmPassword" id="confirmPassword"><br><br>

            <button type="submit">Register</button>

            <p>
                already register? <br>
                <a href="login.jsp">Login</a>
            </p>
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
   <script src="${pageContext.request.contextPath}/Pages/Admin/register.js"></script>
</body>
</html>