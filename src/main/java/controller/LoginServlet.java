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
import jakarta.servlet.http.HttpSession;

import service.AdminService;
import service.CustomerService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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

        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneno");
        String password = request.getParameter("password");
        String loginType = request.getParameter("loginType");

        String result;
        String loginPage;
        String dashboardPage;

        if ("admin".equals(loginType)) {

            // Admin login
            result = adminService.validateAdmin(
                    email, phoneNumber, password);

            loginPage = "/Pages/Admin/login.jsp";
            dashboardPage = "/Pages/Admin/dashboard.jsp";

        } else if ("customer".equals(loginType)) {

            // Customer login
            result = customerService.validateCustomer(
                    email, phoneNumber, password);

            loginPage = "/Pages/Customer/login.jsp";
            dashboardPage = "/index.jsp";

        } else {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid login type.");
            return;
        }

        if ("Valid".equals(result)) {

            HttpSession session = request.getSession();

            session.setAttribute("email", email);
            session.setAttribute("phoneno", phoneNumber);
            session.setAttribute("loggedIn", true);
            session.setAttribute("loginType", loginType);

            // Store customer ID for cart operations

if ("customer".equals(loginType)) {

    model.Customer customer =
            customerService.getCustomerByEmail(email);

    System.out.println("=== CUSTOMER LOGIN DEBUG ===");
    System.out.println("Customer found: " + (customer != null));

    if (customer != null) {

        System.out.println("Customer ID: " + customer.getId());

        session.setAttribute("customerId", customer.getId());

    } else {
        System.out.println(
                "ERROR: Customer ID was not stored in session.");
    }

    System.out.println("Session loggedIn: "
            + session.getAttribute("loggedIn"));

    System.out.println("Session loginType: "
            + session.getAttribute("loginType"));

    System.out.println("Session customerId: "
            + session.getAttribute("customerId"));
}


            response.sendRedirect(
                    request.getContextPath() + dashboardPage);

        } else {

            request.setAttribute("email", email);
            request.setAttribute("phoneno", phoneNumber);

            request.setAttribute("errorMessage", result);
            
            request.getRequestDispatcher(loginPage)
                   .forward(request, response);
        }
    }
}