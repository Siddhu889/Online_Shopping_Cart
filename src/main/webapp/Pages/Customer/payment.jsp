
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment</title>

    <link rel="stylesheet"
        href="${pageContext.request.contextPath}/Pages/Customer/payment.css">
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

    <div class="payment-container">

        <div class="payment-details">
            <h1>Payment Details</h1>

            <form id="payment-form"
                  action="${pageContext.request.contextPath}/payment"
                  method="post">

                <div class="payment-method-selection">
                    <h3>Select Payment Method</h3>

                    <label for="debit-card">
                        <input type="radio" id="debit-card"
                               name="payment-method" value="debit-card">
                        Debit Card
                    </label>

                    <label for="credit-card">
                        <input type="radio" id="credit-card"
                               name="payment-method" value="credit-card">
                        Credit Card
                    </label>

                    <label for="upi">
                        <input type="radio" id="upi"
                               name="payment-method" value="upi">
                        GPay / UPI
                    </label>

                    <label for="cod">
                        <input type="radio" id="cod"
                               name="payment-method" value="cod">
                        Cash on Delivery
                    </label>
                </div>

                <div id="payment-method-details"></div>

                <div class="payment-confirmation">
                    <label for="payment-confirm">
                        <input type="checkbox" id="payment-confirm"
                               name="payment-confirm" value="yes">
                        I confirm my payment details are correct.
                    </label>
                </div>

                <div class="button">
                    <button type="submit" id="confirm-button">
                        Confirm Payment
                    </button>
                </div>

            </form>
        </div>
    </div>

    <div class="footer">
        <p>@ 2026 Online Shopping Cart. All Rights Reserved.</p>
    </div>
    
    
<%
    String paymentError =
        (String) session.getAttribute("paymentError");

    session.removeAttribute("paymentError");
%>

<% if (paymentError != null) { %>
<script>
    alert("<%= paymentError
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\r", "")
        .replace("\n", "") %>");
</script>
<% } %>
    

    <script src="${pageContext.request.contextPath}/Pages/Customer/payment.js"></script>
</body>
</html>
