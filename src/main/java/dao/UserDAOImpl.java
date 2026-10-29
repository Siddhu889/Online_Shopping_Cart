package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DBconnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM customer ORDER BY customer_id";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("customer_id"));
                user.setName(rs.getString("customer_name"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPassword(rs.getString("password"));

                Timestamp registrationDate =
                        rs.getTimestamp("registration_date");

                if (registrationDate != null) {
                    user.setRegistrationDate(
                            registrationDate.toLocalDateTime());
                }

                user.setStatus(rs.getString("status"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public List<User> searchUsers(String searchText) {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM customer "
                   + "WHERE customer_name LIKE ? OR email LIKE ? "
                   + "ORDER BY customer_id";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String searchValue = "%" + searchText + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    User user = new User();

                    user.setId(rs.getInt("customer_id"));
                    user.setName(rs.getString("customer_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setPassword(rs.getString("password"));

                    Timestamp registrationDate =
                            rs.getTimestamp("registration_date");

                    if (registrationDate != null) {
                        user.setRegistrationDate(
                                registrationDate.toLocalDateTime());
                    }

                    user.setStatus(rs.getString("status"));

                    users.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public String changeStatus(int id) {

        String sql = "UPDATE customer SET status = "
                   + "CASE WHEN status = 'Active' THEN 'Blocked' "
                   + "ELSE 'Active' END "
                   + "WHERE customer_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return "User status updated successfully";
            }

            return "User not found";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to update user status";
        }
    }

    @Override
    public String deleteUser(int id) {

        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return "User deleted successfully";
            }

            return "User not found";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to delete user";
        }
    }
}