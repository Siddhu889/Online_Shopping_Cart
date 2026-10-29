  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders Management</title>
   <link rel="stylesheet"
      href="${pageContext.request.contextPath}/Pages/Admin/orders.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">

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
            <h3>Admin Panel</h3>
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
            <section class="page-header">
                <h1>Orders Management</h1>
                <p>View and manage customer orders.</p>
            </section>

            <section class="order-controls">
                <div class="search-box">
                    <label for="search-order">Search Order</label>
                    <input type="text" id="search-order" placeholder="Search by Order ID or Customer Name">
                    <button id="search-button">Search</button>
                </div>

                <div class="filter-box">
                    <label for="order-status-filter">Order Status</label>
                    <select id="order-status-filter">
                        <option value="all">All</option>
                        <option value="pending">Pending</option>
                        <option value="confirmed">Confirmed</option>
                        <option value="shipped">Shipped</option>
                        <option value="delivered">Delivered</option>
                        <option value="cancelled">Cancelled</option>
                    </select>
                </div>
            </section>

            <section class="orders-section">
                <h2>All Orders</h2>

                <div class="table-container">
                    <table class="orders-table">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Customer</th>
                                <th>Order Date</th>
                                <th>Total Amount</th>
                                <th>Payment Status</th>
                                <th>Order Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                       

<tbody id="orders-table-body">

<%
    java.util.List<model.Order> orders =
        (java.util.List<model.Order>) request.getAttribute("orders");

    String errorMessage =
        (String) request.getAttribute("errorMessage");

    if (errorMessage != null) {
%>
        <tr>
            <td colspan="7">
                <%= errorMessage %>
            </td>
        </tr>
<%
    } else if (orders == null || orders.isEmpty()) {
%>
        <tr>
            <td colspan="7">No orders found.</td>
        </tr>
<%
    } else {
        for (model.Order order : orders) {
%>
            <tr>
                <td><%= order.getId() %></td>

                <td><%= order.getCustomerName() %></td>

                <td><%= order.getOrder_date() %></td>

                <td>₹<%= String.format("%.2f",
                    order.getTotal_amount()) %></td>

                <td><%= order.getPayment() %></td>

                <td><%= order.getStatus() %></td>

                
<td>
    <form method="post"
          action="${pageContext.request.contextPath}/order">

        <input type="hidden"
               name="action"
               value="updateStatus">

        <input type="hidden"
               name="orderId"
               value="<%= order.getId() %>">

        <select name="status" required>
            <option value="pending">Pending</option>
            <option value="confirmed">Confirmed</option>
            <option value="shipped">Shipped</option>
            <option value="delivered">Delivered</option>
            <option value="cancelled">Cancelled</option>
        </select>

        <button type="submit">Update Status</button>
    </form>
</td>
                
            </tr>
<%
        }
    }
%>

</tbody>

                       
                    </table>
                </div>
            </section>
        </main>
    </div>
<script src="${pageContext.request.contextPath}/Pages/Admin/orders.js"></script>
</body>
</html>
    