<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Product" %>

<%
    Product product = (Product) request.getAttribute("product");

    if (product == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        return;
    }

    String categoryName = (String) request.getAttribute("categoryName");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Pages/Customer/electronics-details.css">
</head>
<body>

<div class="navbar">
    <div class="logo">
        Online Shopping
    </div>

    <div class="nav">
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/cart?action=VIEW">Cart</a>
        <a href="${pageContext.request.contextPath}/Pages/login-option.html">Login</a>
    </div>
</div>

<div class="container">
    <div class="product-image">
        <img src="<%= request.getContextPath() %>/<%= product.getImage() %>"
             class="product-img" alt="<%= product.getName() %>">
    </div>

    <div class="product-info">
        <h1 class="product-name"><%= product.getName() %></h1>
        <p class="price">₹<%= product.getPrice() %></p>
        <p class="description"><%= product.getDetails() == null ? "" : product.getDetails() %></p>

        <h2>Key Features</h2>
        <p class="features"><%= product.getFeatures() == null ? "No features available." : product.getFeatures() %></p>

        <p>
            <strong>Availability:</strong>
            <span class="available"><%= product.getAvailability() == null ? "Unknown" : product.getAvailability() %></span>
        </p>

        <p class="category">Category: <%= categoryName == null ? "Unknown" : categoryName %></p>
 
<form class="add-to-cart-form"
      action="<%= request.getContextPath() %>/cart"
      method="post">

    <input type="hidden" name="action" value="ADD">

    <input type="hidden"
           name="productId"
           value="<%= product.getId() %>">

    <label for="quantity">Quantity:</label>

    <input type="number"
           id="quantity"
           name="quantity"
           value="1"
           min="1"
           max="<%= product.getQuantity() %>"
           <%= product.getQuantity() <= 0 ? "disabled" : "" %>
           required>

    <br><br>

    <button class="cart-button" type="submit">
                  Add to Cart
             </button>
</form>
           
        <a href="<%= request.getContextPath() %>/product?action=category&id=<%= product.getCategory_id() %>"
           class="back-link">Back to Products</a>
    </div>
</div>

<div class="footer">
    <p>&copy; 2026 Online Shopping Cart. All rights reserved.</p>
</div>

<script src="<%= request.getContextPath() %>/Pages/Customer/electronics-details.js"></script>

</body>
</html>