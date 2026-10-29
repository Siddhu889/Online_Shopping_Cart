package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

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
    public void addCustomer(Customer customer) {

        String sql = "INSERT INTO customer "
                + "(customer_name, email, phone_number, password, registration_date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone_number());
            ps.setString(4, customer.getPassword());
            ps.setTimestamp(5,  java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            ps.setString(6, customer.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to add User.", e);
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        String sql = "SELECT * FROM customer WHERE email = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Customer customer = new Customer();

                    customer.setId(rs.getInt("customer_id"));
                    customer.setName(rs.getString("customer_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setPassword(rs.getString("password"));

                    return customer;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve customer.", e);
        }
        
        

        return null;
    }
    

    // Dashboard: Count registered customers
    @Override
    public int getTotalCustomers() {

        String sql = "SELECT COUNT(*) FROM customer";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Unable to count registered customers.", e);
        }

        return 0;
    }

}

