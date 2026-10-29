
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fashion Products</title>

    <link rel="stylesheet"
        href="<%= request.getContextPath() %>/Pages/Customer/fashion.css">
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

    <div class="header">
        <div class="header-content">
            <h1 class="website-title">Online Shopping Cart</h1>
            <p class="website-subtitle">Discover Fashion Products</p>
        </div>
    </div>

    <div class="search-section">
        <form class="search-form" onsubmit="return false;">
            <input type="text"
                class="search-input"
                placeholder="Search fashion products...">
        </form>
    </div>

    <div class="products">

        <%
            List<Product> products =
                (List<Product>) request.getAttribute("products");

            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
        %>

        <div class="products-card"
            data-id="<%= product.getId() %>">

            <div class="product-image">
                <img
                    src="<%= request.getContextPath() %>/<%= product.getImage() %>"
                    class="product-img"
                    alt="<%= product.getName() %>">
            </div>

            <div class="product-info">

                <h2 class="product-name">
                    <%= product.getName() %>
                </h2>

                <p class="price">₹<%= product.getPrice() %></p>

                <p class="description">
                    <%= product.getDescription() == null
                        ? "" : product.getDescription() %>
                </p>

                <p class="availability">
                    <strong>Availability:</strong>

                    <span class="<%= "In Stock".equalsIgnoreCase(
                        product.getAvailability())
                        ? "instock" : "outofstock" %>">
                        <%= product.getAvailability() == null
                            ? "Out of Stock" : product.getAvailability() %>
                    </span>
                </p>

                <a class="details-button"
                    href="<%= request.getContextPath() %>/product?action=details&id=<%= product.getId() %>">
                    View Details
                </a>
                
        
<form class="add-to-cart-form"
      action="<%= request.getContextPath() %>/cart"
      method="post">

    <input type="hidden" name="action" value="ADD">

    <input type="hidden"
           name="productId"
           value="<%= product.getId() %>">

    <input type="hidden" name="quantity" value="1">

    <button type="submit" class="cart-button">
        Add to Cart
    </button>
</form>
        
</div>
        </div>

        <%
                }
            } else {
        %>

            <p>No fashion products available.</p>

        <%
            }
        %>

    </div>

    <div class="footer">
        <p class="footer-text">
            &copy; 2026 Online Shopping Cart
        </p>
    </div>

    <script src="<%= request.getContextPath() %>/Pages/Customer/fashion.js"></script>

</body>
</html>
