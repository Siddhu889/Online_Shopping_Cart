package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import util.DBconnection;

public class CategoryDAOImpl implements CategoryDAO {

    // Add category
    @Override
    public boolean addCategory(Category category) {

        String sql = "INSERT INTO category (category_name, description) VALUES (?, ?)";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // View all categories
    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        String sql = "SELECT category_id, category_name, description FROM category ORDER BY category_id";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Category category = new Category();

                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                category.setDescription(rs.getString("description"));

                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    // Get category by ID
    @Override
    public Category getCategoryById(int id) {

        String sql = "SELECT category_id, category_name, description FROM category WHERE category_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Category category = new Category();

                    category.setId(rs.getInt("category_id"));
                    category.setName(rs.getString("category_name"));
                    category.setDescription(rs.getString("description"));
                    
                    return category;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update category
    @Override
    public boolean updateCategory(Category category) {

        String sql = "UPDATE category SET category_name = ?, description = ? WHERE category_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete category
    @Override
    public boolean deleteCategory(int id) {

        String sql = "DELETE FROM category WHERE category_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
