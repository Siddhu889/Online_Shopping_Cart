
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBconnection;

public class OrderConfirmationDAO {

    // Get the order details and delivery address.
    public Map<String, Object> getOrderDetails(
            int orderId, int customerId) throws Exception {

        Map<String, Object> order = new HashMap<>();

        String sql =
            "SELECT o.order_id, o.order_date, o.total_amount, " +
            "o.order_status, o.payment_status, " +
            "s.full_name, s.mobile_number, s.address_line, " +
            "s.city, s.state, s.pin_code " +
            "FROM orders o " +
            "JOIN shipping_address s " +
            "ON o.shipping_address_id = s.shipping_address_id " +
            "WHERE o.order_id = ? AND o.customer_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, customerId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    order.put("orderId", rs.getInt("order_id"));
                    order.put("orderDate", rs.getTimestamp("order_date"));
                    order.put("totalAmount", rs.getBigDecimal("total_amount"));
                    order.put("orderStatus", rs.getString("order_status"));
                    order.put("paymentStatus", rs.getString("payment_status"));

                    order.put("fullName", rs.getString("full_name"));
                    order.put("mobile", rs.getString("mobile_number"));
                    order.put("address", rs.getString("address_line"));
                    order.put("city", rs.getString("city"));
                    order.put("state", rs.getString("state"));
                    order.put("pincode", rs.getString("pin_code"));
                } else {
                    return null;
                }
            }
        }

        return order;
    }

    // Get all products saved for this order.
    public List<Map<String, Object>> getOrderItems(
            int orderId) throws Exception {

        List<Map<String, Object>> items = new ArrayList<>();

        String sql =
            "SELECT oi.product_id, oi.quantity, oi.unit_price, " +
            "oi.subtotal, p.product_name, p.product_image " +
            "FROM order_item oi " +
            "JOIN product p ON oi.product_id = p.product_id " +
            "WHERE oi.order_id = ? " +
            "ORDER BY oi.order_item_id";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Map<String, Object> item = new HashMap<>();

                    item.put("productId", rs.getInt("product_id"));
                    item.put("productName", rs.getString("product_name"));
                    item.put("productImage", rs.getString("product_image"));
                    item.put("quantity", rs.getInt("quantity"));
                    item.put("unitPrice", rs.getBigDecimal("unit_price"));
                    item.put("subtotal", rs.getBigDecimal("subtotal"));

                    items.add(item);
                }
            }
        }

        return items;
    }
}
