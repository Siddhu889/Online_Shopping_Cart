
package controller;

import java.io.IOException;
import java.util.List;

import dao.CartDAO;
import dao.CartDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Cart;
import model.Cart_item;
import model.Shipping_address;

import service.CartService;
import service.CheckoutService;
import service.PaymentService;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;


@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private CartService cartService;
    private CheckoutService checkoutService;
    private PaymentService paymentService;

    @Override
    public void init() {

        CartDAO cartDAO = new CartDAOImpl();

        cartService = new CartService(cartDAO);
        checkoutService = new CheckoutService();
        paymentService = new PaymentService();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("========== PAYMENT POST STARTED ==========");
    	System.out.println("Request URI: " + request.getRequestURI());
    	System.out.println("Payment method: "
    	        + request.getParameter("payment-method"));
    	System.out.println("Confirmation: "
    	        + request.getParameter("payment-confirm"));

        HttpSession session = request.getSession(false);

        // 1. Verify customer login.
        if (session == null
                || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))
                || !"customer".equals(session.getAttribute("loginType"))
                || !(session.getAttribute("customerId") instanceof Integer)) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/Pages/Customer/login.jsp");
            return;
        }

        // 2. Read the selected payment method.
        String paymentMethod =
                request.getParameter("payment-method");

        String confirmation =
                request.getParameter("payment-confirm");

        // 3. Validate the payment method.
        if (!"debit-card".equals(paymentMethod)
                && !"credit-card".equals(paymentMethod)
                && !"upi".equals(paymentMethod)
                && !"cod".equals(paymentMethod)) {

            redirectWithError(request, response,
                    "Please select a valid payment method.");
            return;
        }

        // 4. Validate the confirmation checkbox.
        if (!"yes".equals(confirmation)) {

            redirectWithError(request, response,
                    "Please confirm your payment details.");
            return;
        }

        // 5. Validate debit/credit card details through PaymentService.
        if ("debit-card".equals(paymentMethod)
                || "credit-card".equals(paymentMethod)) {

            String cardNumber =
                    request.getParameter("card-number");

            String expiryDate =
                    request.getParameter("expiry-date");

            String cvv =
                    request.getParameter("cvv");



if (cardNumber == null || !cardNumber.matches("[0-9]{16}")) {
    redirectWithError(request, response,
        "Please enter a valid 16-digit card number.");
    return;
}

if (expiryDate == null ||
        !expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
    redirectWithError(request, response,
        "Please enter a valid expiry date in MM/YY format.");
    return;
}

// Check whether the card has expired.
try {
    String[] parts = expiryDate.split("/");

    int month = Integer.parseInt(parts[0]);
    int year = 2000 + Integer.parseInt(parts[1]);

    YearMonth enteredExpiry = YearMonth.of(year, month);
    YearMonth currentMonth = YearMonth.now();

    if (enteredExpiry.isBefore(currentMonth)) {
        redirectWithError(request, response,
            "Your card has expired. Please enter a valid expiry date.");
        return;
    }

} catch (DateTimeParseException | NumberFormatException e) {
    redirectWithError(request, response,
        "Please enter a valid expiry date in MM/YY format.");
    return;
}

if (cvv == null || !cvv.matches("[0-9]{3}")) {
    redirectWithError(request, response,
        "Please enter a valid 3-digit CVV.");
    return;
}


            
        }

        // 6. Validate UPI through PaymentService.
        if ("upi".equals(paymentMethod)) {

            String upiId = request.getParameter("upi-id");

            if (!paymentService.validateUpiId(upiId)) {

                redirectWithError(request, response,
                        "Please enter a valid UPI ID.");
                return;
            }
        }

        try {

            int customerId =
                    (Integer) session.getAttribute("customerId");

            // 7. Retrieve the customer's cart.
            Cart cart =
                    cartService.getOrCreateCart(customerId);

            if (cart == null) {
                redirectWithError(request, response,
                        "Unable to load your cart. Please try again.");
                return;
            }

            List<Cart_item> cartItems =
                    cartService.getCartItems(cart.getId());

            if (cartItems == null || cartItems.isEmpty()) {
                redirectWithError(request, response,
                        "Your cart is empty. Please add products first.");
                return;
            }

            // 8. Calculate the total on the server.
            double subtotal =
                    cartService.getCartTotal(cart.getId());

            double totalAmount = subtotal + 100.00;

            if (!Double.isFinite(totalAmount) || totalAmount <= 0) {
                redirectWithError(request, response,
                        "Unable to calculate your order total.");
                return;
            }

            // 9. Retrieve checkout information from the session.
            String name =
                    (String) session.getAttribute("checkoutName");

            String mobile =
                    (String) session.getAttribute("checkoutMobile");

            String city =
                    (String) session.getAttribute("checkoutCity");

            String state =
                    (String) session.getAttribute("checkoutState");

            String pincode =
                    (String) session.getAttribute("checkoutPincode");

            String addressLine =
                    (String) session.getAttribute("checkoutAddress");

            if (isBlank(name)
                    || isBlank(mobile)
                    || isBlank(city)
                    || isBlank(state)
                    || isBlank(pincode)
                    || isBlank(addressLine)) {

                response.sendRedirect(
                        request.getContextPath() + "/checkout");
                return;
            }

            // 10. Create the shipping address model.
            Shipping_address address = new Shipping_address();

            address.setCustomerid(customerId);
            address.setFull_name(name);
            address.setMobile_number(mobile);
            address.setCity(city);
            address.setState(state);
            address.setPincode(pincode);
            address.setAddress_line(addressLine);
            address.setAddress_type("Home");

            // 11. Place the order using the existing service.
            int orderId = checkoutService.placeOrder(
                    address, cartItems, totalAmount, paymentMethod);

            if (orderId <= 0) {
                redirectWithError(request, response,
                        "Unable to create your order. Please try again.");
                return;
            }

            // 12. Store the order result in the session.
            session.setAttribute("orderId", orderId);
            session.setAttribute("paymentMethod", paymentMethod);

            session.setAttribute(
                    "paymentStatus",
                    paymentService.getPaymentStatus(paymentMethod));

            // 13. Remove checkout information after order creation.
            session.removeAttribute("checkoutName");
            session.removeAttribute("checkoutEmail");
            session.removeAttribute("checkoutMobile");
            session.removeAttribute("checkoutCity");
            session.removeAttribute("checkoutState");
            session.removeAttribute("checkoutPincode");
            session.removeAttribute("checkoutAddress");

            // 14. Redirect to order confirmation.
            response.sendRedirect(
                    request.getContextPath()
                    + "/order-confirmation");

        } catch (Exception e) {

            throw new ServletException(
                    "Unable to process the demonstration order.", e);
        }
    }

    // Display the payment page again with a user-friendly error.
    private void redirectWithError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message) throws IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.setAttribute("paymentError", message);
        }

        response.sendRedirect(
                request.getContextPath()
                + "/payment");
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))
                || !"customer".equals(session.getAttribute("loginType"))
                || !(session.getAttribute("customerId") instanceof Integer)) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/Pages/Customer/login.jsp");
            return;
        }

        try {

            int customerId =
                    (Integer) session.getAttribute("customerId");

            Cart cart = cartService.getOrCreateCart(customerId);

            if (cart == null) {
                response.sendError(500, "Unable to load cart.");
                return;
            }

            List<Cart_item> cartItems =
                    cartService.getCartItems(cart.getId());

            if (cartItems == null || cartItems.isEmpty()) {
                response.sendRedirect(
                        request.getContextPath()
                        + "/cart?action=VIEW");
                return;
            }

            double subtotal =
                    cartService.getCartTotal(cart.getId());

            double deliveryCharge = 100.00;
            double totalAmount = subtotal + deliveryCharge;

            request.setAttribute("cartItems", cartItems);
            request.setAttribute("subtotal", subtotal);
            request.setAttribute("deliveryCharge", deliveryCharge);
            request.setAttribute("totalAmount", totalAmount);

            request.getRequestDispatcher(
                    "/Pages/Customer/payment.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(
                    "Unable to load payment page.", e);
        }
    }
}
