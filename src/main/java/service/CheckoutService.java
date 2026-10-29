
package service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import dao.OrderDAO;
import dao.OrderDAOImpl;
import dao.PaymentDAO;
import dao.PaymentDAOImpl;
import dao.ShippingAddressDAO;
import dao.ShippingAddressDAOImpl;

import model.Order;
import model.Payment;
import model.Shipping_address;
import model.Cart_item;
import util.DBconnection;

public class CheckoutService {

    private final ShippingAddressDAO addressDAO =
            new ShippingAddressDAOImpl();

    private final OrderDAO orderDAO = new OrderDAOImpl();

    private final PaymentDAO paymentDAO = new PaymentDAOImpl();

    private final PaymentService paymentService =
            new PaymentService();

    public int placeOrder(
            Shipping_address address,
            List<Cart_item> cartItems,
            double totalAmount,
            String paymentMethod) throws Exception {

        if (address == null || cartItems == null
                || cartItems.isEmpty() || totalAmount <= 0) {
            return 0;
        }

        Payment payment = new Payment();
        payment.setPayment_method(paymentMethod);
        payment.setAmount(totalAmount);

        if (!paymentService.processPayment(payment)) {
            return 0;
        }

        String paymentStatus =
                paymentService.getPaymentStatus(paymentMethod);

        try (Connection con = DBconnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                // 1. Save shipping address
            	int addressId = addressDAO.addShippingAddress(address, con);

                if (addressId <= 0) {
                    throw new Exception("Unable to save address.");
                }

                // 2. Create order
                Order order = new Order();
                order.setCustomer_id(address.getCustomerid());
                order.setShipping_address_id(addressId);
                order.setOrder_date(LocalDateTime.now());
                order.setTotal_amount(totalAmount);
                order.setStatus("confirmed");
                order.setPayment(paymentStatus);

                int orderId = orderDAO.addOrder(order, con);

                if (orderId <= 0) {
                    throw new Exception("Unable to create order.");
                }

                // 3. Save order items
                if (!orderDAO.addOrderItems(
                        orderId, cartItems, con)) {
                    throw new Exception("Unable to save order items.");
                }

                // 4. Save simulated payment
                payment.setOrderid(orderId);
                payment.setStatus(paymentStatus);
                payment.setTransaction_id(
                        "DEMO-" + System.currentTimeMillis());
                payment.setPayment_date(LocalDateTime.now());

                if (!paymentDAO.addPayment(payment, con)) {
                    throw new Exception("Unable to save payment.");
                }

                con.commit();
                return orderId;

            } catch (Exception e) {
                con.rollback();
                throw e;

            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
