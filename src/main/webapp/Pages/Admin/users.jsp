<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Pages/Admin/users.css">
</head>
<body>
    <header class="header">
        <div class="logo">
            <h2>Online Shopping Cart</h2>
        </div>
        <div class="admin-info">
            <span>Welcome, Admin</span>
             <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </header>

    <div class="admin-container">
        <aside class="sidebar">
            <h2>Admin Panel</h2>
            <nav class="nav">
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/product?action=list">Products</a>
    <a href="${pageContext.request.contextPath}/Pages/Admin/category.jsp">Categories</a>
    <a href="${pageContext.request.contextPath}/product?action=add">Add Products</a>
    <a href="${pageContext.request.contextPath}/product?action=list">Edit Product</a>
    <a href="${pageContext.request.contextPath}/user?action=list">Users</a>
    <a href="${pageContext.request.contextPath}/order">Orders</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
        </aside>

        <main class="main-content">

            <div class="page-header">
                <h1>User Management</h1>
                <p>View and manage registered users.</p>
            </div>

            <div class="search-section">
            <form action="<%= request.getContextPath() %>/user" method="get"> 
            
            <input type="hidden" name="action" value="search">
                <!-- <label for="search-user">Search User</label> -->

                <input type="text"
                id="search-user"
                name="search"
                placeholder="Search user by name or email">

                <button id="search-button">Search</button>
                
                </form>
            </div>

            <div class="users-section">
                <h2>Registered Users</h2>

                <table class="users-table">
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Registration</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                
                <tbody>
         
                    <%
                    List<User> users = (List<User>) request.getAttribute("users");

                    if (users != null && !users.isEmpty()) {
                        for (User user : users) {
                    %>
                        <tr>
                           <tr>
                             <td><%= user.getId() %></td>
                             <td><%= user.getName() %></td>
                             <td><%= user.getEmail() %></td>
                             <td><%= user.getPhoneNumber() %></td>
                             <td><%= user.getRegistrationDate() == null ? "" : user.getRegistrationDate().toLocalDate() %></td>
                             <td><%= user.getStatus() %></td>
                            <td>
                                <form action="<%= request.getContextPath() %>/user"
                                      method="post"
                                      style="display:inline;">
                                    <input type="hidden" name="action" value="status">
                                    <input type="hidden" name="id" value="<%= user.getId() %>">
                                    <button type="submit" class="status-button">Change Status</button>
                                </form>

                                <form action="<%= request.getContextPath() %>/user"
                                      method="post"
                                      style="display:inline;"
                                      onsubmit="return confirmDelete();">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= user.getId() %>">
                                    <button type="submit" class="delete-button">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <%
                      }
                        } else {
                    %>
                        <tr>
                            <td colspan="7">No users found.</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
                </table>
            </div>

        </main>

    </div>

    <script src="${pageContext.request.contextPath}/Pages/Admin/users.js"></script>
</body>
</html>