package service;

import dao.AdminDAO;
import model.Admin;

public class AdminService {

    private AdminDAO adminDAO;

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    // Validate admin login details
    public String validateAdmin(String email, String phoneNumber,
                                String password) {

        if (email == null || !email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Please enter a valid email address.";
        }

        if (phoneNumber == null || !phoneNumber.matches("[0-9]{10}")) {
            return "Please enter a valid 10-digit phone number.";
        }

        if (password == null || password.trim().isEmpty()) {
            return "Please enter your password.";
        }

        Admin admin = adminDAO.getAdminByEmail(email);

        if (admin == null) {
            return "Admin account not found.";
        }

        // Verify phone number
        if (!phoneNumber.equals(admin.getPhoneNumber())) {
            return "Incorrect phone number.";
        }

        // Verify password
        if (!password.equals(admin.getPassword())) {
            return "Incorrect password.";
        }

        return "Valid";
    }
    

public String registerAdmin(Admin admin, String confirmPassword) {

    if (admin == null) {
        return "Invalid admin details.";
    }

    if (admin.getName() == null || admin.getName().trim().isEmpty()) {
        return "Please enter admin name.";
    }

    if (admin.getEmail() == null || !admin.getEmail().matches(
            "^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {
        return "Please enter a valid email address using lowercase letters only.";
    }

    if (admin.getPhoneNumber() == null ||
            !admin.getPhoneNumber().matches("[0-9]{10}") ||
            admin.getPhoneNumber().matches("(\\d)\\1{9}")) {
        return "Please enter a valid 10-digit phone number.";
    }

    if (admin.getPassword() == null || admin.getPassword().trim().isEmpty()) {
        return "Please enter a password.";
    }
    
    if (!admin.getPassword().matches(".*[A-Z].*")) {
        return "Password must contain at least one uppercase letter.";
    }

    if (!admin.getPassword().matches(".*[0-9].*")) {
        return "Password must contain at least one number.";
    }

    if (!admin.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=].*")) {
        return "Password must contain at least one special character.";
    }

    if (admin.getPassword().length() < 8) {
        return "Password must contain at least 8 characters.";
    }

    if (confirmPassword == null || !admin.getPassword().equals(confirmPassword)) {
        return "Password and Confirm Password do not match.";
    }

    if (adminDAO.getAdminByEmail(admin.getEmail()) != null) {
        return "This email is already registered.";
    }

    adminDAO.addAdmin(admin);

    return "Success";
}

}

