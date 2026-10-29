
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Order;
import model.Cart_item;

public interface OrderDAO {

    // Get all orders
    List<Order> getAllOrders() throws Exception;

    // Update order status
    boolean updateOrderStatus(int orderId, String newStatus)
            throws Exception;

    // Save a new order and return its generated ID
    int addOrder(Order order, Connection con)
            throws SQLException;

    // Save products belonging to an order
    boolean addOrderItems(int orderId, List<Cart_item> cartItems,
                          Connection con)
            throws SQLException;
    
    int getTotalOrders() 
           throws Exception;
}
