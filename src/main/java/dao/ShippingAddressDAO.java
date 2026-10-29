package dao;

import java.sql.SQLException;
import java.sql.Connection;
import model.Shipping_address;

public interface ShippingAddressDAO {
	
	int addShippingAddress(Shipping_address address)
            throws SQLException;

    int addShippingAddress(Shipping_address address, Connection con)
            throws SQLException;

    Shipping_address getShippingAddressById(int addressId)
            throws SQLException;
}