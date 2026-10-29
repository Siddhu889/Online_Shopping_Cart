
package service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import model.Payment;

public class PaymentService {

    // Validate the payment amount and method.
    public boolean processPayment(Payment payment) {

        if (payment == null) {
            return false;
        }

        String method = payment.getPayment_method();

        if (method == null || method.trim().isEmpty()) {
            return false;
        }

        if (payment.getAmount() <= 0
                || !Double.isFinite(payment.getAmount())) {
            return false;
        }

        return method.equals("debit-card")
                || method.equals("credit-card")
                || method.equals("upi")
                || method.equals("cod");
    }

    // Validate demonstration card details without storing them.

public boolean validateCardDetails(
        String cardNumber,
        String expiryDate,
        String cvv) {

    if (cardNumber == null
            || !cardNumber.trim().matches("[0-9]{16}")) {
        return false;
    }

    if (cvv == null
            || !cvv.trim().matches("[0-9]{3}")) {
        return false;
    }

    if (expiryDate == null) {
        return false;
    }

    return isValidExpiryDate(expiryDate.trim());
}


    // Validate expiry format and reject expired cards.
    public boolean isValidExpiryDate(String expiryDate) {

        if (expiryDate == null
                || !expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            return false;
        }

        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM/yy");

            YearMonth expiry =
                    YearMonth.parse(expiryDate, formatter);

            YearMonth currentMonth = YearMonth.now();

            return !expiry.isBefore(currentMonth);

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Validate a demonstration UPI ID.
    public boolean validateUpiId(String upiId) {

        return upiId != null
                && upiId.trim().matches(
                    "[A-Za-z0-9._-]+@[A-Za-z0-9.-]+");
    }

    // Return the correct simulated payment status.
    public String getPaymentStatus(String paymentMethod) {

        if ("cod".equals(paymentMethod)) {
            return "PENDING";
        }

        return "DEMO_SUCCESS";
    }

    // Validate the stored payment information.
    public boolean validatePayment(Payment payment) {

        if (payment == null) {
            return false;
        }

        String method = payment.getPayment_method();
        String status = payment.getStatus();

        return payment.getOrderid() > 0
                && payment.getAmount() > 0
                && Double.isFinite(payment.getAmount())
                && method != null
                && !method.trim().isEmpty()
                && status != null
                && !status.trim().isEmpty();
    }

    // Check whether a payment has a status.
    public boolean checkPaymentStatus(Payment payment) {

        return payment != null
                && payment.getStatus() != null
                && !payment.getStatus().trim().isEmpty();
    }
}
