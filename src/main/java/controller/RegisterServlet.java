
package controller;

import java.io.IOException;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Admin;
import model.Customer;

import service.AdminService;
import service.CustomerService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AdminService adminService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {

        // Admin setup
        AdminDAO adminDAO = new AdminDAOImpl();
        adminService = new AdminService(adminDAO);

        // Customer setup
        CustomerDAO customerDAO = new CustomerDAOImpl();
        customerService = new CustomerService(customerDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Identify which registration form was submitted
        String registerType = request.getParameter("registerType");

        // Get form data
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneno = request.getParameter("phoneno");
        String password = request.getParameter("password");
        String confirmPassword =
                request.getParameter("confirmPassword");

        String result;
        String registerPage;
        String loginPage;

        if ("admin".equals(registerType)) {

            // ADMIN REGISTRATION

            Admin admin = new Admin();

            admin.setName(username);
            admin.setEmail(email);
            admin.setPhoneNumber(phoneno);
            admin.setPassword(password);

            result = adminService.registerAdmin(
                    admin, confirmPassword);

            registerPage = "/Pages/Admin/register.jsp";
            loginPage = "/Pages/Admin/login.jsp";

        } else if ("customer".equals(registerType)) {

            // CUSTOMER REGISTRATION

            Customer customer = new Customer();

            customer.setName(username);
            customer.setEmail(email);
            customer.setPhone_number(phoneno);
            customer.setPassword(password);

            result = customerService.registerCustomer(
                    customer, confirmPassword);

            registerPage = "/Pages/Customer/register.jsp";
            loginPage = "/Pages/Customer/login.jsp";

        } else {

            // Unknown registration type
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid registration type.");
            return;
        }

        if ("Success".equals(result)) {

            // Registration successful
            response.sendRedirect(
                    request.getContextPath()
                    + loginPage + "?registered=success");

        } else {

            // Registration failed: preserve entered details
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("phoneno", phoneno);
            request.setAttribute("errorMessage", result);

            request.getRequestDispatcher(registerPage)
                   .forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String registerType =
                request.getParameter("registerType");

        if ("admin".equals(registerType)) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/Pages/Admin/register.jsp");

        } else {

            // Default registration page is Customer
            response.sendRedirect(
                    request.getContextPath()
                    + "/Pages/Customer/register.jsp");
        }
    }
}
