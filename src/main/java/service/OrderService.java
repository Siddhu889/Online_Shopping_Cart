
package service;

import java.util.List;

import dao.OrderDAO;
import dao.OrderDAOImpl;
import model.Order;

public class OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();

    // Get all orders from the database
    public List<Order> getAllOrders() throws Exception {

        return orderDAO.getAllOrders();
    }

    // Update the status of an order
    public boolean updateOrderStatus(int orderId, String newStatus)
            throws Exception {

        // Check whether the order ID is valid
        if (orderId <= 0) {
            return false;
        }

        // Check whether status was provided
        if (newStatus == null || newStatus.trim().isEmpty()) {
            return false;
        }

        // Remove extra spaces and convert to lowercase
        newStatus = newStatus.trim().toLowerCase();

        // Allow only these five statuses
        if (!newStatus.equals("pending")
                && !newStatus.equals("confirmed")
                && !newStatus.equals("shipped")
                && !newStatus.equals("delivered")
                && !newStatus.equals("cancelled")) {

            return false;
        }

        // Send the update request to the DAO
        return orderDAO.updateOrderStatus(orderId, newStatus);
    }
}
