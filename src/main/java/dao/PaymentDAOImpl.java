
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.Payment;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean addPayment(Payment payment, Connection con)
            throws SQLException {

        String sql =
            "INSERT INTO payment " +
            "(order_id, payment_method, payment_amount, " +
            "payment_status, transaction_id, payment_date) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, payment.getOrderid());
            ps.setString(2, payment.getPayment_method());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getStatus());
            ps.setString(5, payment.getTransaction_id());

            if (payment.getPayment_date() != null) {
                ps.setTimestamp(6,
                    Timestamp.valueOf(payment.getPayment_date()));
            } else {
                ps.setTimestamp(6,
                    new Timestamp(System.currentTimeMillis()));
            }

            return ps.executeUpdate() == 1;
        }
    }
}
