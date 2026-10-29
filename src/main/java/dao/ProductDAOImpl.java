
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import util.DBconnection;

public class ProductDAOImpl implements ProductDAO {

    // Add product
    @Override
    public void addProduct(Product product) {

        String sql = "INSERT INTO product "
                + "(product_name, price, stock_quantity, product_image, "
                + "category_id, product_description, product_details, "
                + "product_features, availability) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getCategory_id());
            ps.setString(6, product.getDescription());
            ps.setString(7, product.getDetails());
            ps.setString(8, product.getFeatures());

            ps.setString(9, product.getAvailability() == null
                    ? "In Stock" : product.getAvailability());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL error during product update:");
            e.printStackTrace();
            throw new RuntimeException("Unable to update product", e);
        }
    }

    // View all products
    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapProduct(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Unable to retrieve products from database", e);
        }

        return products;
    }

    // Get product by ID
    @Override
    public Product getProductById(int id) {

        Product product = null;

        String sql = "SELECT * FROM product WHERE product_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = mapProduct(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve product", e);
        }

        return product;
    }


    // Update product
    @Override
    public boolean updateProduct(Product product) {

        String sql = "UPDATE product SET "
                + "product_name = ?, "
                + "price = ?, "
                + "stock_quantity = ?, "
                + "product_image = COALESCE(?, product_image), "
                + "category_id = ?, "
                + "product_description = ?, "
                + "product_details = ?, "
                + "product_features = ?, "
                + "availability = ? "
                + "WHERE product_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getCategory_id());
            ps.setString(6, product.getDescription());
            ps.setString(7, product.getDetails());
            ps.setString(8, product.getFeatures());
            ps.setString(9, product.getAvailability());
            ps.setInt(10, product.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to update product", e);
        }
    }
    
 // Delete product from database
    @Override
    public boolean deleteProduct(int id) {

        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to delete product. It may be referenced by another table.",
                    e);
        }
    }
    
    // Dashboard: Get products with low stock
    @Override
    public List<Product> getLowStockProducts(int threshold) {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product "
                   + "WHERE stock_quantity < ? "
                   + "ORDER BY stock_quantity ASC";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, threshold);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    products.add(mapProduct(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Unable to retrieve low stock products", e);
        }

        return products;
    }

    
 // Get products by category
 @Override
 public List<Product> getProductsByCategory(int categoryId) {

     List<Product> products = new ArrayList<>();

     String sql = "SELECT * FROM product WHERE category_id = ?";

     try (Connection con = DBconnection.getConnection();
          PreparedStatement ps = con.prepareStatement(sql)) {

         ps.setInt(1, categoryId);

         try (ResultSet rs = ps.executeQuery()) {
             while (rs.next()) {
                 products.add(mapProduct(rs));
             }
         }

     } catch (SQLException e) {
         throw new RuntimeException(
                 "Unable to retrieve products by category", e);
     }

     return products;
 }
    
    // Convert database record into Product object
    private Product mapProduct(ResultSet rs) throws SQLException {

        Product product = new Product();

        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setPrice(rs.getDouble("price"));
        product.setQuantity(rs.getInt("stock_quantity"));
        product.setImage(rs.getString("product_image"));
        product.setCategory_id(rs.getInt("category_id"));
        product.setDescription(rs.getString("product_description"));
        product.setDetails(rs.getString("product_details"));
        product.setAvailability(rs.getString("availability"));
        product.setFeatures(rs.getString("product_features"));

        return product;
    }
}
