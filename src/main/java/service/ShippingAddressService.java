package service;

import java.sql.SQLException;

import dao.ShippingAddressDAO;
import dao.ShippingAddressDAOImpl;
import model.Shipping_address;

public class ShippingAddressService {

    private final ShippingAddressDAO shippingAddressDAO =
            new ShippingAddressDAOImpl();

    public int saveShippingAddress(Shipping_address address)
            throws SQLException {

        if (address == null) {
            return 0;
        }

        if (address.getCustomerid() <= 0) {
            return 0;
        }

        if (isEmpty(address.getFull_name())
                || isEmpty(address.getMobile_number())
                || isEmpty(address.getAddress_line())
                || isEmpty(address.getCity())
                || isEmpty(address.getState())
                || isEmpty(address.getPincode())) {
            return 0;
        }

        if (!address.getMobile_number().matches("[6-9][0-9]{9}")) {
            return 0;
        }

        if (!address.getPincode().matches("[1-9][0-9]{5}")) {
            return 0;
        }

        address.setFull_name(address.getFull_name().trim());
        address.setMobile_number(address.getMobile_number().trim());
        address.setAddress_line(address.getAddress_line().trim());
        address.setCity(address.getCity().trim());
        address.setState(address.getState().trim());
        address.setPincode(address.getPincode().trim());

        if (address.getAddress_type() == null
                || address.getAddress_type().trim().isEmpty()) {
            address.setAddress_type("Home");
        }

        return shippingAddressDAO.addShippingAddress(address);
    }

    public Shipping_address getShippingAddressById(int addressId)
            throws SQLException {

        if (addressId <= 0) {
            return null;
        }

        return shippingAddressDAO.getShippingAddressById(addressId);
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}