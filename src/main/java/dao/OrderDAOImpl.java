
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Cart_item;
import util.DBconnection;

public class OrderDAOImpl implements OrderDAO {

    // Get all orders
    @Override
    public List<Order> getAllOrders() throws SQLException {

        List<Order> orders = new ArrayList<>();

        String sql =
            "SELECT o.order_id, o.customer_id, " +
            "c.customer_name, o.shipping_address_id, " +
            "o.order_date, o.total_amount, " +
            "o.order_status, o.payment_status " +
            "FROM orders o " +
            "JOIN customer c ON o.customer_id = c.customer_id " +
            "ORDER BY o.order_date DESC, o.order_id DESC";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Order order = new Order();

                order.setId(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setShipping_address_id(
                    rs.getInt("shipping_address_id"));

                Timestamp timestamp = rs.getTimestamp("order_date");

                if (timestamp != null) {
                    order.setOrder_date(timestamp.toLocalDateTime());
                }

                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("order_status"));
                order.setPayment(rs.getString("payment_status"));

                orders.add(order);
            }
        }

        return orders;
    }

    // Update order status
    @Override
    public boolean updateOrderStatus(
            int orderId, String newStatus) throws SQLException {

        String sql =
            "UPDATE orders SET order_status = ? WHERE order_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, orderId);

            return ps.executeUpdate() == 1;
        }
    }

    // Insert a new order and return its generated ID
    @Override
    public int addOrder(Order order, Connection con)
            throws SQLException {

        String sql =
            "INSERT INTO orders " +
            "(customer_id, shipping_address_id, order_date, " +
            "total_amount, order_status, payment_status) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getCustomer_id());
            ps.setInt(2, order.getShipping_address_id());
            ps.setTimestamp(3, Timestamp.valueOf(order.getOrder_date()));
            ps.setDouble(4, order.getTotal_amount());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPayment());

            int rows = ps.executeUpdate();

            if (rows == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        }

        return 0;
    }
    

    // Dashboard: Count all orders
    @Override
    public int getTotalOrders() throws SQLException {

        String sql = "SELECT COUNT(*) FROM orders";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }


    // Insert all products belonging to an order
    @Override
    public boolean addOrderItems(
            int orderId, List<Cart_item> cartItems, Connection con)
            throws SQLException {

        if (orderId <= 0 || cartItems == null || cartItems.isEmpty()) {
            return false;
        }

        String sql =
            "INSERT INTO order_item " +
            "(order_id, product_id, quantity, unit_price, subtotal) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            for (Cart_item item : cartItems) {

                ps.setInt(1, orderId);

                // Change this getter if your Cart_item model
                // uses a different name for the product ID.
                ps.setInt(2, item.getProduct_id());

                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getUnit_price());

                double subtotal =
                    item.getUnit_price() * item.getQuantity();

                ps.setDouble(5, subtotal);

                ps.addBatch();
            }

            int[] results = ps.executeBatch();

            for (int result : results) {
                if (result == Statement.EXECUTE_FAILED) {
                    return false;
                }
            }

            return results.length == cartItems.size();
        }
    }
}
