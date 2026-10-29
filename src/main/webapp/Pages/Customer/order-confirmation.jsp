
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.Map" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.time.LocalDate,java.time.format.DateTimeFormatter" %>

<%!
    // Escape text before displaying database values in HTML.
    private String escapeHtml(Object value) {
        if (value == null) return "";

        return String.valueOf(value)
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
    }
%>

<%
    Map<String, Object> order =
        (Map<String, Object>) request.getAttribute("order");

    List<Map<String, Object>> orderItems =
        (List<Map<String, Object>>) request.getAttribute("orderItems");

    LocalDate deliveryDate =
        (LocalDate) request.getAttribute("deliveryDate");

    // The page must be opened through OrderConfirmationServlet.
    if (order == null || orderItems == null || deliveryDate == null) {
        response.sendRedirect(
            request.getContextPath() + "/order-confirmation");
        return;
    }

    String paymentMethod =
        (String) request.getAttribute("paymentMethod");

    String formattedPaymentMethod = "Not specified";

    if ("debit-card".equals(paymentMethod)) {
        formattedPaymentMethod = "Debit Card";
    } else if ("credit-card".equals(paymentMethod)) {
        formattedPaymentMethod = "Credit Card";
    } else if ("upi".equals(paymentMethod)) {
        formattedPaymentMethod = "UPI";
    } else if ("cod".equals(paymentMethod)) {
        formattedPaymentMethod = "Cash on Delivery";
    }

    String formattedDeliveryDate = deliveryDate.format(
        DateTimeFormatter.ofPattern("dd MMMM yyyy"));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Order Confirmation</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Pages/Customer/order-confirmation.css">
</head>

<body>

<div class="order-confirm-container">

    <div class="success-section">
        <div class="success-icon">&#10003;</div>
        <h1>Order Confirmed!</h1>
        <p>Your order has been confirmed. Thank you for shopping with us!</p>
    </div>

    <div class="order-information">
        <h2>Order Details</h2>

        <p><strong>Order ID:</strong>
            <%= order.get("orderId") %></p>

        <p><strong>Order Date:</strong>
            <%= escapeHtml(order.get("orderDate")) %></p>

        <p><strong>Order Status:</strong>
            <%= escapeHtml(order.get("orderStatus")) %></p>

        <p><strong>Payment Method:</strong>
            <%= escapeHtml(formattedPaymentMethod) %></p>

        <p><strong>Payment Status:</strong>
            <%= escapeHtml(order.get("paymentStatus")) %></p>
    </div>

    <div class="order-summary">
        <h2>Order Summary</h2>

        <%
            for (Map<String, Object> item : orderItems) {
                String image = (String) item.get("productImage");
                String imageUrl = "";

                if (image != null && !image.trim().isEmpty()) {
                    imageUrl = request.getContextPath() + "/"
                        + image.replace("\\", "/").replaceFirst("^/+", "");
                }

                BigDecimal price = (BigDecimal) item.get("unitPrice");
                BigDecimal subtotal = (BigDecimal) item.get("subtotal");
        %>

        <div class="order-product">

            <div class="order-product-image"
                 style="width:110px !important; height:110px !important;
                 min-width:110px !important; max-width:110px !important;
                 flex:0 0 110px !important; overflow:hidden !important;">

    <% if (!imageUrl.isEmpty()) { %>
        <img src="<%= escapeHtml(imageUrl) %>"
             alt="<%= escapeHtml(item.get("productName")) %>"
             style="display:block !important;
                    width:110px !important; height:110px !important;
                    max-width:110px !important; max-height:110px !important;
                    object-fit:contain !important;">
    <% } %>

</div>

            <div class="order-product-info">
                <h3><%= escapeHtml(item.get("productName")) %></h3>

                <p>Price: ₹<%= price.toPlainString() %></p>

                <p>Quantity: <%= item.get("quantity") %></p>

                <p>Subtotal: ₹<%= subtotal.toPlainString() %></p>
            </div>

        </div>

        <% } %>

        <div class="total-amount">
            <h3>Total Amount: ₹<%=
                ((BigDecimal) order.get("totalAmount")).toPlainString()
            %></h3>
        </div>
    </div>

    <div class="delivery-information">
        <h2>Delivery Information</h2>

        <p><strong>Expected Delivery:</strong>
            <%= escapeHtml(formattedDeliveryDate) %></p>

        <p><strong>Recipient:</strong>
            <%= escapeHtml(order.get("fullName")) %></p>

        <p><strong>Mobile:</strong>
            <%= escapeHtml(order.get("mobile")) %></p>

        <p><strong>Address:</strong>
            <%= escapeHtml(order.get("address")) %>,
            <%= escapeHtml(order.get("city")) %>,
            <%= escapeHtml(order.get("state")) %> -
            <%= escapeHtml(order.get("pincode")) %></p>
    </div>

    <a class="action" id="continue-shopping"
       href="${pageContext.request.contextPath}/index.jsp">
        Continue Shopping
    </a>

</div>

<div class="footer">
    <p>&copy; 2026 Online Shopping Cart. All rights reserved.</p>
</div>

<script src="${pageContext.request.contextPath}/Pages/Customer/order-confirmation.js"></script>

</body>
</html>
