
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import model.Payment;

public interface PaymentDAO {

    boolean addPayment(Payment payment, Connection con)
            throws SQLException;
}
