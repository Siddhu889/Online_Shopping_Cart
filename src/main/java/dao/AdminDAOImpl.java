package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Admin;

public class AdminDAOImpl implements AdminDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/onlineshoppingcart";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "Kaju@123";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public void addAdmin(Admin admin) {

        String sql = "INSERT INTO admin "
                + "(admin_name, email, phone_number, password) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPhoneNumber());
            ps.setString(4, admin.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to add admin.", e);
        }
    }

    @Override
    public Admin getAdminByEmail(String email) {

        String sql = "SELECT * FROM admin WHERE email = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Admin admin = new Admin();

                    admin.setId(rs.getInt("admin_id"));
                    admin.setName(rs.getString("admin_name"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPhoneNumber(rs.getString("phone_number"));
                    admin.setPassword(rs.getString("password"));

                    return admin;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve admin.", e);
        }

        return null;
    }
}

