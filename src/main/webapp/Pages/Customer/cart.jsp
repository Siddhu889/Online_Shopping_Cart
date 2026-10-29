
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Cart_item" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Pages/Customer/cart.css">
</head>
<body>

    <header class="header">
        <h1>Online Shopping Cart</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
            <a href="${pageContext.request.contextPath}/cart?action=VIEW">Cart</a>
        </nav>
    </header>

    <main>
        <h1 class="cart-title">My Shopping Cart</h1>

        <div class="cart-container">

            <div class="cart-items">
            
                <%
                    List<Cart_item> cartItems =
                        (List<Cart_item>) request.getAttribute("cartItems");

                    if (cartItems != null && !cartItems.isEmpty()) {
                        for (Cart_item item : cartItems) {
                %>

                  
<div class="cart-item">

    <img
        src="${pageContext.request.contextPath}/<%= item.getProductImage() %>"
        alt="<%= item.getProductName() %>">

    <div class="cart-product-info">

        <h2><%= item.getProductName() %></h2>

        <p>Price: ₹<%= String.format("%.2f", item.getUnit_price()) %></p>

        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="action" value="UPDATE">
            <input type="hidden" name="cartItemId" value="<%= item.getId() %>">

            <label>Quantity:</label>
            <input type="number"
                   class="quantity-input"
                   name="quantity"
                   value="<%= item.getQuantity() %>"
                   min="1"
                   required>

            <button type="submit">Update</button>
        </form>

        <br>

        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="action" value="REMOVE">
            <input type="hidden" name="cartItemId" value="<%= item.getId() %>">

            <button type="submit" class="remove-button">Remove</button>
        </form>

    </div>

    <div class="cart-product-total">
        ₹<%= String.format("%.2f", item.getSubtotal()) %>
    </div>

</div>
                  

                <%
                        }
                    } else {
                %>

                    <p>Your cart is currently empty.</p>

                <%
                    }
                %>

            </div>

            <div class="cart-summary">
                <h2>Cart Summary</h2>

                <p>
                    Total Items:
                    <span class="total-items">${totalItems != null ? totalItems : 0}</span>
                </p>

                <p>
                    Total Amount: ₹
                    <span class="total-amount"><%= String.format("%.2f",
                        request.getAttribute("totalAmount") == null
                            ? 0.0
                            : ((Number) request.getAttribute("totalAmount")).doubleValue()) %></span>
                </p>

                <a href="${pageContext.request.contextPath}/index.jsp"
                   class="continue-button">
                    Continue Shopping
                </a>

                
                <a href="${pageContext.request.contextPath}/checkout"
                class="checkout-button">
                Proceed to Checkout
                </a>
                
            </div>

        </div>
    </main>

    <footer class="footer">
        <p>&copy; 2026 Online Shopping Cart.</p>
    </footer>

   <%--  <script src="${pageContext.request.contextPath}/Pages/Customer/cart.js"></script>  --%>

</body>
</html>
