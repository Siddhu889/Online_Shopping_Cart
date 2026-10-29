
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBconnection;
import model.Cart;
import model.Cart_item;

public class CartDAOImpl implements CartDAO {

    @Override
    public Cart getOrCreateCart(int customerId) {

        String sql = "SELECT * FROM cart WHERE customer_id=? AND cart_status='Active'";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cart cart = new Cart();
                    cart.setId(rs.getInt("cart_id"));
                    cart.setCustomer_id(rs.getInt("customer_id"));
                    cart.setStatus(rs.getString("cart_status"));
                    return cart;
                }
            }

            String insertSql = "INSERT INTO cart " +
                    "(customer_id, created_at, updated_at, cart_status) " +
                    "VALUES (?, NOW(), NOW(), 'Active')";

            try (PreparedStatement insert = con.prepareStatement(
                    insertSql, Statement.RETURN_GENERATED_KEYS)) {

                insert.setInt(1, customerId);
                insert.executeUpdate();

                try (ResultSet keys = insert.getGeneratedKeys()) {
                    if (keys.next()) {
                        Cart cart = new Cart();
                        cart.setId(keys.getInt(1));
                        cart.setCustomer_id(customerId);
                        cart.setStatus("Active");
                        return cart;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to get or create cart.", e);
        }

        return null;
    }

    @Override
    public boolean addToCart(Cart_item item) {

        String priceSql = "SELECT price, stock_quantity FROM product WHERE product_id=?";
        String findSql = "SELECT cart_item_id, quantity FROM cart_item WHERE cart_id=? AND product_id=?";
        String insertSql = "INSERT INTO cart_item " +
                "(cart_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
        String updateSql = "UPDATE cart_item SET quantity=?, unit_price=?, subtotal=? WHERE cart_item_id=?";

        try (Connection con = DBconnection.getConnection()) {

            double price;
            int stock;

            try (PreparedStatement ps = con.prepareStatement(priceSql)) {
                ps.setInt(1, item.getProduct_id());

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return false;
                    }

                    price = rs.getDouble("price");
                    stock = rs.getInt("stock_quantity");
                }
            }

            int itemId = 0;
            int oldQuantity = 0;

            try (PreparedStatement ps = con.prepareStatement(findSql)) {
                ps.setInt(1, item.getCart_id());
                ps.setInt(2, item.getProduct_id());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        itemId = rs.getInt("cart_item_id");
                        oldQuantity = rs.getInt("quantity");
                    }
                }
            }

            int quantity = oldQuantity + item.getQuantity();

            if (item.getQuantity() <= 0 || quantity > stock) {
                return false;
            }

            if (itemId == 0) {

                try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                    ps.setInt(1, item.getCart_id());
                    ps.setInt(2, item.getProduct_id());
                    ps.setInt(3, quantity);
                    ps.setDouble(4, price);
                    ps.setDouble(5, price * quantity);
                    ps.executeUpdate();
                }

            } else {

                try (PreparedStatement ps = con.prepareStatement(updateSql)) {
                    ps.setInt(1, quantity);
                    ps.setDouble(2, price);
                    ps.setDouble(3, price * quantity);
                    ps.setInt(4, itemId);
                    ps.executeUpdate();
                }
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to add product to cart.", e);
        }
    }

@Override
public List<Cart_item> getCartItems(int cartId) {

    List<Cart_item> items = new ArrayList<>();

    String sql = "SELECT ci.*, p.product_name, p.product_image " +
                 "FROM cart_item ci " +
                 "JOIN product p ON ci.product_id = p.product_id " +
                 "WHERE ci.cart_id = ?";

    try (Connection con = util.DBconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, cartId);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cart_item item = new Cart_item();

                item.setId(rs.getInt("cart_item_id"));
                item.setCart_id(rs.getInt("cart_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnit_price(rs.getDouble("unit_price"));
                item.setSubtotal(rs.getDouble("subtotal"));

                item.setProductName(rs.getString("product_name"));
                item.setProductImage(rs.getString("product_image"));

                items.add(item);
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException("Unable to get cart items.", e);
    }

    return items;
}

    @Override
    public boolean updateCartItem(int itemId, int cartId, int quantity) {

        if (quantity <= 0) {
            return false;
        }

        String sql = "UPDATE cart_item SET quantity=?, subtotal=unit_price*? " +
                "WHERE cart_item_id=? AND cart_id=?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, quantity);
            ps.setInt(3, itemId);
            ps.setInt(4, cartId);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to update cart item.", e);
        }
    }

    @Override
    public boolean removeCartItem(int itemId, int cartId) {

        String sql = "DELETE FROM cart_item WHERE cart_item_id=? AND cart_id=?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, itemId);
            ps.setInt(2, cartId);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to remove cart item.", e);
        }
    }

    @Override
    public double getCartTotal(int cartId) {

        String sql = "SELECT COALESCE(SUM(subtotal), 0) AS total " +
                "FROM cart_item WHERE cart_id=?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to calculate cart total.", e);
        }

        return 0;
    }
}
