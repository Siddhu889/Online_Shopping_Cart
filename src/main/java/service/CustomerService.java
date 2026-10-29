
package service;

import dao.CustomerDAO;
import model.Customer;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

 // Get customer by email
    public Customer getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }
    
    // Customer Login
    public String validateCustomer(String email, String phoneno, String password) {

        if (email == null || !email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Please enter a valid email address.";
        }

        if (phoneno == null || !phoneno.matches("[0-9]{10}")) {
            return "Please enter a valid 10-digit phone number.";
        }

        if (password == null || password.trim().isEmpty()) {
            return "Please enter your password.";
        }

        Customer customer = customerDAO.getCustomerByEmail(email);

        if (customer == null) {
            return "Customer account not found.";
        }

        if (!phoneno.equals(customer.getPhone_number())) {
            return "Incorrect phone number.";
        }

        if (!password.equals(customer.getPassword())) {
            return "Incorrect password.";
        }

        return "Valid";
    }

    // Customer Registration
    public String registerCustomer(Customer customer, String confirmPassword) {

        if (customer == null) {
            return "Invalid customer details.";
        }

        if (customer.getName() == null ||
                customer.getName().trim().isEmpty()) {
            return "Please enter customer name.";
        }

        if (customer.getEmail() == null || !customer.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Please enter a valid email address.";
        }

        if (customer.getPhone_number() == null ||
                !customer.getPhone_number().matches("[0-9]{10}")) {
            return "Please enter a valid 10-digit phone number.";
        }

        String password = customer.getPassword();

        if (password == null || password.trim().isEmpty()) {
            return "Please enter a password.";
        }

        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter.";
        }

        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least one number.";
        }

        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=].*")) {
            return "Password must contain at least one special character.";
        }

        if (password.length() < 8) {
            return "Password must contain at least 8 characters.";
        }

        if (confirmPassword == null ||
                !password.equals(confirmPassword)) {
            return "Password and Confirm Password do not match.";
        }

        if (customerDAO.getCustomerByEmail(customer.getEmail()) != null) {
            return "This email is already registered.";
        }

        
        customer.setStatus("Active");
        
        customerDAO.addCustomer(customer);

        return "Success";
    }
}
