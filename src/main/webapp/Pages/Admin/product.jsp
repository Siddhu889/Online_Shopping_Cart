
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Pages/Admin/product.css">
</head>

<body>

<header class="header">
    <div class="logo">
        <h2>Online Shopping Cart</h2>
    </div>

    <div class="admin-info">
        <span>Welcome, Admin</span>
        <a href="${pageContext.request.contextPath}/index.html">Logout</a>
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

    <main class="main">

        <div class="product-header">
            <h1>Product Management</h1>

            <a href="${pageContext.request.contextPath}/Pages/Admin/add-products.html">
                Add Product
            </a>
        </div>

        <div class="product-table-section">

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Category ID</th>
                        <th>Stock</th>
                        <th>Availability</th>
                        <th>Features</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody id="product-table-body">

                <%
                    List<Product> products =
                        (List<Product>) request.getAttribute("products");

                    if (products != null && !products.isEmpty()) {
                        for (Product product : products) {
                %>

                    <tr>
                        <td><%= product.getId() %></td>

                       
                <td>
                 <img
                       src="<%= request.getContextPath() + "/" +
                       (product.getImage() != null &&
                       product.getImage().startsWith("/")
                       ? product.getImage().substring(1)
                       : product.getImage()) %>"
                       alt="<%= product.getName() %>"
                       class="product-image">
                </td>
                       

                        <td><%= product.getName() %></td>

                        <td>₹<%= product.getPrice() %></td>

                        <td><%= product.getCategory_id() %></td>

                        <td><%= product.getQuantity() %></td>

                        <td>
                            <span class="status">
                                <%= product.getAvailability() %>
                            </span>
                        </td>

                        <td>
                            <%= product.getFeatures() == null
                                || product.getFeatures().trim().isEmpty()
                                ? "N/A"
                                : product.getFeatures() %>
                        </td>

                        <td>
                            <div class="action-buttons">

                                <a
                                    href="<%= request.getContextPath() %>/product?action=edit&id=<%= product.getId() %>"
                                    class="edit-btn">
                                    Edit
                                </a>

                                <form
                                    action="<%= request.getContextPath() %>/product"
                                    method="post"
                                    onsubmit="return confirm('Are you sure you want to delete this product?');">

                                    <input type="hidden"
                                           name="action"
                                           value="delete">

                                    <input type="hidden"
                                           name="id"
                                           value="<%= product.getId() %>">

                                    <button type="submit" class="delete-btn">
                                        Delete
                                    </button>
                                </form>

                            </div>
                        </td>
                    </tr>

                <%
                        }
                    } else {
                %>

                    <tr>
                        <td colspan="9">No products found.</td>
                    </tr>

                <%
                    }
                %>

                </tbody>
            </table>

        </div>
    </main>
</div>

</body>
</html>
