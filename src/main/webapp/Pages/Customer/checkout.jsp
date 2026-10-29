<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Cart_item" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Checkout</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Pages/Customer/checkout.css">
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

    <div class="checkout-container">

        <!-- CUSTOMER INFORMATION AND DELIVERY ADDRESS -->
        <form id="customer-information"
              action="${pageContext.request.contextPath}/checkout"
              method="post">

            <!-- Customer Information -->
            <div class="checkout-info">

                <h1>Checkout</h1>

                <label for="name">Name:</label>
                <input type="text"
                       id="name"
                       name="name"
                       required>

                <label for="email">Email:</label>
                <input type="email"
                       id="email"
                       name="email"
                       required>

                <label for="mobile-number">Mobile Number:</label>
                <input type="tel"
                       id="mobile-number"
                       name="mobile-number"
                       maxlength="10"
                       pattern="[6-9][0-9]{9}"
                       required>

            </div>

            <!-- Delivery Address -->
            <div class="delivery-address">

                <h2>Delivery Address</h2>

                <label for="city">City:</label>
                <input type="text"
                       id="city"
                       name="city"
                       required>

                <label for="state">State:</label>
                <input type="text"
                       id="state"
                       name="state"
                       required>

                <label for="pincode">Pincode:</label>
                <input type="text"
                       id="pincode"
                       name="pincode"
                       maxlength="6"
                       pattern="[1-9][0-9]{5}"
                       required>

                <label for="address">Address:</label>
                <textarea id="address"
                          name="address"
                          minlength="5"
                          maxlength="255"
                          required></textarea>

            </div>

        </form>

        <!-- ORDER SUMMARY -->
        <div class="order-summary">

            <h2>Order Summary</h2>

            <div id="cart-products">

                <%
                    List<Cart_item> cartItems =
                        (List<Cart_item>) request.getAttribute("cartItems");

                    if (cartItems != null && !cartItems.isEmpty()) {

                        for (Cart_item item : cartItems) {
                %>

                    <div class="checkout-product">

                        <div class="checkout-product-image">

                            <img
                                src="${pageContext.request.contextPath}/<%= item.getProductImage() %>"
                                alt="<%= item.getProductName() %>">

                        </div>

                        <div class="checkout-product-info">

                            <h3><%= item.getProductName() %></h3>

                            <p>
                                Price: ₹<%= String.format("%.2f",
                                    item.getUnit_price()) %>
                            </p>

                            <p>
                                Quantity: <%= item.getQuantity() %>
                            </p>

                            <p>
                                Product Total: ₹<%= String.format("%.2f",
                                    item.getSubtotal()) %>
                            </p>

                        </div>

                    </div>

                <%
                        }

                    } else {
                %>

                    <p>No products in your cart.</p>

                <%
                    }
                %>

            </div>

            <!-- PRICE SUMMARY -->
            <div class="subtotal">

                Subtotal: ₹<%= String.format("%.2f",
                    request.getAttribute("subtotal") == null
                        ? 0.0
                        : ((Number) request.getAttribute("subtotal"))
                            .doubleValue()) %>

            </div>

            <div class="delivery-charge">

                Delivery Charge: ₹<%= String.format("%.2f",
                    request.getAttribute("deliveryCharge") == null
                        ? 100.0
                        : ((Number) request.getAttribute("deliveryCharge"))
                            .doubleValue()) %>

            </div>

            <div class="total-amount">

                Total Amount: ₹<%= String.format("%.2f",
                    request.getAttribute("totalAmount") == null
                        ? 0.0
                        : ((Number) request.getAttribute("totalAmount"))
                            .doubleValue()) %>

            </div>

        </div>
        
        <!-- Continue to Payment Button -->
            <div class="button">

                <button type="submit" id="confirm-button">
                    Continue to Payment
                </button>

            </div>
        

    </div>

    <!-- FOOTER -->
    <div class="footer">

        <p>@ 2026 Online Shopping Cart. All rights reserved.</p>

    </div>

    <!-- CHECKOUT JAVASCRIPT -->
    <script>
        const checkoutServletUrl =
            "${pageContext.request.contextPath}/checkout";
    </script>

    <script src="${pageContext.request.contextPath}/Pages/Customer/checkout.js"></script>

</body>
</html>
