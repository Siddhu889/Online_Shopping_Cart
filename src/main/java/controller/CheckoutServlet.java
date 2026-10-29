
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
import service.CartService;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init() {
        CartDAO cartDAO = new CartDAOImpl();
        cartService = new CartService(cartDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check whether the customer is logged in
        if (session == null
                || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))
                || !"customer".equals(session.getAttribute("loginType"))
                || !(session.getAttribute("customerId") instanceof Integer)) {

            response.sendRedirect(
                request.getContextPath()
                + "/Pages/Customer/login.jsp");
            return;
        }

        int customerId =
            (Integer) session.getAttribute("customerId");

        // Get the customer's cart
        Cart cart = cartService.getOrCreateCart(customerId);

        if (cart == null) {
            response.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Unable to load your cart.");
            return;
        }

        // Fetch products from MySQL
        List<Cart_item> cartItems =
            cartService.getCartItems(cart.getId());

        if (cartItems == null || cartItems.isEmpty()) {
            session.setAttribute(
                "cartMessage",
                "Your cart is empty. Please add products first.");

            response.sendRedirect(
                request.getContextPath() + "/cart?action=VIEW");
            return;
        }

        int totalItems = 0;

        for (Cart_item item : cartItems) {
            totalItems += item.getQuantity();
        }

        // Calculate subtotal using the backend
        double subtotal =
            cartService.getCartTotal(cart.getId());

        double deliveryCharge = 100.00;
        double totalAmount = subtotal + deliveryCharge;

        // Send data to checkout.jsp
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalItems", totalItems);
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("deliveryCharge", deliveryCharge);
        request.setAttribute("totalAmount", totalAmount);

        request.getRequestDispatcher(
            "/Pages/Customer/checkout.jsp")
            .forward(request, response);
    }
    

@Override
protected void doPost(HttpServletRequest request,
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

    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String mobile = request.getParameter("mobile-number");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    String pincode = request.getParameter("pincode");
    String address = request.getParameter("address");

    if (name == null || email == null || mobile == null
            || city == null || state == null
            || pincode == null || address == null
            || name.trim().isEmpty()
            || email.trim().isEmpty()
            || mobile.trim().isEmpty()
            || city.trim().isEmpty()
            || state.trim().isEmpty()
            || pincode.trim().isEmpty()
            || address.trim().isEmpty()) {

        response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Please provide all checkout details.");
        return;
    }

    if (!email.trim().matches(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
            || !mobile.trim().matches("[6-9][0-9]{9}")
            || !pincode.trim().matches("[1-9][0-9]{5}")) {

        response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Invalid checkout details.");
        return;
    }

    session.setAttribute("checkoutName", name.trim());
    session.setAttribute("checkoutEmail", email.trim());
    session.setAttribute("checkoutMobile", mobile.trim());
    session.setAttribute("checkoutCity", city.trim());
    session.setAttribute("checkoutState", state.trim());
    session.setAttribute("checkoutPincode", pincode.trim());
    session.setAttribute("checkoutAddress", address.trim());

    response.sendRedirect(
            request.getContextPath() + "/payment");
}

}
