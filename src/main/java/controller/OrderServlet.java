
package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Order;
import service.OrderService;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    // Display all orders
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Order> orders = orderService.getAllOrders();

            request.setAttribute("orders", orders);

        } catch (Exception e) {

            getServletContext().log("Error loading orders", e);

            request.setAttribute(
                "errorMessage",
                "Unable to load orders. Please try again."
            );
        }

        RequestDispatcher dispatcher =
            request.getRequestDispatcher("/Pages/Admin/orders.jsp");

        dispatcher.forward(request, response);
    }

    // Update an order's status
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        String action = request.getParameter("action");

        getServletContext().log(
        	    "OrderServlet POST reached. Action: " + action
        	);
        
        // Check the requested action
        if (!"updateStatus".equals(action)) {

            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Invalid action."
            );

            return;
        }

        try {

            String orderIdText = request.getParameter("orderId");
            String newStatus = request.getParameter("status");

            // Check whether the order ID was provided
            if (orderIdText == null || orderIdText.trim().isEmpty()) {

                response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
                );

                response.getWriter().write("Order ID is required.");
                return;
            }

            int orderId = Integer.parseInt(orderIdText);

            boolean updated = orderService.updateOrderStatus(
                orderId,
                newStatus
            );

            if (updated) {
                response.sendRedirect(
                    request.getContextPath() + "/order"
                );
                return;
            
            } else {

                response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
                );

                response.getWriter().write(
                    "Invalid order ID or order status."
                );
            }

        } catch (NumberFormatException e) {

            response.setStatus(
                HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().write("Order ID must be a number.");

        } catch (Exception e) {

            getServletContext().log("Error updating order status", e);

            response.setStatus(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                "Unable to update order status."
            );
        }
    }
}
