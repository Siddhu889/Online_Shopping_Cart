
package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import dao.OrderConfirmationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order-confirmation")
public class OrderConfirmationServlet extends HttpServlet {

    private OrderConfirmationDAO confirmationDAO;

    @Override
    public void init() {
        confirmationDAO = new OrderConfirmationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check whether the customer is logged in.
        if (session == null
                || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))
                || !"customer".equals(session.getAttribute("loginType"))
                || !(session.getAttribute("customerId") instanceof Integer)
                || !(session.getAttribute("orderId") instanceof Integer)) {

            response.sendRedirect(
                request.getContextPath() + "/Pages/Customer/login.jsp");
            return;
        }

        int customerId = (Integer) session.getAttribute("customerId");
        int orderId = (Integer) session.getAttribute("orderId");

        try {
            // Retrieve the saved order and its products.
            Map<String, Object> order =
                confirmationDAO.getOrderDetails(orderId, customerId);

            if (order == null) {
                response.sendError(404, "Order not found.");
                return;
            }

            List<Map<String, Object>> items =
                confirmationDAO.getOrderItems(orderId);

            if (items.isEmpty()) {
                response.sendError(404, "No products found for this order.");
                return;
            }

            // Generate the estimated delivery date once per order.
            String dateKey = "deliveryDate_" + orderId;

            LocalDate deliveryDate =
                (LocalDate) session.getAttribute(dateKey);

            if (deliveryDate == null) {
                int days = ThreadLocalRandom.current().nextInt(3, 8);
                deliveryDate = LocalDate.now().plusDays(days);
                session.setAttribute(dateKey, deliveryDate);
            }

            // Send data to the JSP.
            request.setAttribute("order", order);
            request.setAttribute("orderItems", items);
            request.setAttribute("deliveryDate", deliveryDate);
            request.setAttribute("paymentMethod",
                session.getAttribute("paymentMethod"));

            request.getRequestDispatcher(
                "/Pages/Customer/order-confirmation.jsp")
                .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(
                "Unable to load order confirmation.", e);
        }
    }
}
