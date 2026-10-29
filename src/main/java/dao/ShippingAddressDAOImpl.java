package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Shipping_address;
import util.DBconnection;

public class ShippingAddressDAOImpl implements ShippingAddressDAO {

@Override
public int addShippingAddress(Shipping_address address)
        throws SQLException {

    String sql =
        "INSERT INTO shipping_address " +
        "(customer_id, full_name, mobile_number, address_line, " +
        "city, state, pin_code, address_type) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection con = DBconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(
                 sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, address.getCustomerid());
        ps.setString(2, address.getFull_name());
        ps.setString(3, address.getMobile_number());
        ps.setString(4, address.getAddress_line());
        ps.setString(5, address.getCity());
        ps.setString(6, address.getState());
        ps.setString(7, address.getPincode());
        ps.setString(8, address.getAddress_type());

        int rows = ps.executeUpdate();

        if (rows == 1) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
    }

    return 0;
}


public int addShippingAddress(
        Shipping_address address, Connection con)
        throws SQLException {

    String sql =
        "INSERT INTO shipping_address " +
        "(customer_id, full_name, mobile_number, address_line, " +
        "city, state, pin_code, address_type) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = con.prepareStatement(
            sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, address.getCustomerid());
        ps.setString(2, address.getFull_name());
        ps.setString(3, address.getMobile_number());
        ps.setString(4, address.getAddress_line());
        ps.setString(5, address.getCity());
        ps.setString(6, address.getState());
        ps.setString(7, address.getPincode());
        ps.setString(8, address.getAddress_type());

        if (ps.executeUpdate() == 1) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
    }

    return 0;
}


    @Override
    public Shipping_address getShippingAddressById(int addressId)
            throws SQLException {

        String sql = "SELECT * FROM shipping_address "
                + "WHERE shipping_address_id = ?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, addressId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Shipping_address address = new Shipping_address();

                    address.setId(rs.getInt("shipping_address_id"));
                    address.setCustomerid(rs.getInt("customer_id"));
                    address.setFull_name(rs.getString("full_name"));
                    address.setMobile_number(
                            rs.getString("mobile_number"));
                    address.setAddress_line(rs.getString("address_line"));
                    address.setCity(rs.getString("city"));
                    address.setState(rs.getString("state"));
                    address.setPincode(rs.getString("pin_code"));
                    address.setAddress_type(rs.getString("address_type"));

                    return address;
                }
            }
        }

        return null;
    }
}