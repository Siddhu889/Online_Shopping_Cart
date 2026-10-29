
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Order" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Pages/Admin/dashboard.css">
</head>

<body>

<%
    String contextPath = request.getContextPath();

    // These attributes will be supplied by the dashboard Servlet.
    Integer totalProducts = (Integer) request.getAttribute("totalProducts");
    Integer totalUsers = (Integer) request.getAttribute("totalUsers");
    Integer totalOrders = (Integer) request.getAttribute("totalOrders");
    Double totalRevenue = (Double) request.getAttribute("totalRevenue");

    List<Order> recentOrders =
        (List<Order>) request.getAttribute("recentOrders");

    List<Product> lowStockProducts =
        (List<Product>) request.getAttribute("lowStockProducts");
%>

<header class="header">
    <div class="logo">
        <h2>Online Shopping Cart</h2>
    </div>

    <div class="admin-info">
        <span>Welcome, Admin</span>

        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</header>

<div class="dashboard-container">

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

    <main class="main">

        <h1>Admin Dashboard</h1>
        <p>Manage your online shopping store</p>

        <!-- Summary Cards -->
        <div class="summary-cards">

            <div class="total-products">
                <h3>Total Products</h3>
                <p><%= totalProducts != null ? totalProducts : 0 %></p>
            </div>

            <div class="total-users">
                <h3>Total Users</h3>
                <p><%= totalUsers != null ? totalUsers : 0 %></p>
            </div>

            <div class="total-orders">
                <h3>Total Orders</h3>
                <p><%= totalOrders != null ? totalOrders : 0 %></p>
            </div>

            <div class="total-revenue">
                <h3>Total Revenue</h3>
                <p>₹<%= totalRevenue != null ? totalRevenue : 0.0 %></p>
            </div>

        </div>

        <!-- Recent Orders -->
        <div class="recent-orders">

            <h2>Recent Orders</h2>

            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Status</th>
                    </tr>
                </thead>

                <tbody>
                <%
                    if (recentOrders != null && !recentOrders.isEmpty()) {
                        for (Order order : recentOrders) {
                %>
                    <tr>
                        <!-- Replace these getter names if your Order model differs. -->
                        <td><%= order.getId() %></td>
                        <td><%= order.getCustomerName() %></td>
                        <td><%= order.getOrder_date() %></td>
                        <td>₹<%= order.getTotal_amount() %></td>
                        <td><%= order.getStatus() %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No recent orders available.</td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>

        </div>

        <!-- Low Stock Products -->
        <div class="low-stock-products">

            <h2>Low Stock Products</h2>

            <table>
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Category</th>
                        <th>Stock</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                <%
                    if (lowStockProducts != null
                            && !lowStockProducts.isEmpty()) {

                        for (Product product : lowStockProducts) {
                %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td><%= product.getName() %></td>
                        <td><%= product.getCategory_id() %></td>
                        <td><%= product.getQuantity() %></td>
                        <td>
                            <a href="<%= contextPath %>/Pages/Admin/edit-products.jsp">
                                Edit
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No low stock products available.</td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>

        </div>

        <!-- Quick Actions -->
        <div class="quick-action">

            <h2>Quick Actions</h2>

            <div class="action-links">

                <a href="<%= contextPath %>/Pages/Admin/add-product.jsp">
                    Add Product
                </a>

                <a href="<%= contextPath %>/Pages/Admin/products.jsp">
                    View Products
                </a>

                <a href="<%= contextPath %>/Pages/Admin/users.jsp">
                    View Users
                </a>

                <a href="<%= contextPath %>/Pages/Admin/orders.jsp">
                    View Orders
                </a>

            </div>
        </div>

    </main>
</div>

<div class="footer">
    <p>@ 2026 Online Shopping Cart.</p>
</div>

</body>
</html>
