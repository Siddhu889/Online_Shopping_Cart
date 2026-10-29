
package controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dao.OrderDAO;
import dao.OrderDAOImpl;

import model.Product;
import model.Order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private ProductDAO productDAO;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAOImpl();
        customerDAO = new CustomerDAOImpl();
        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        try {
            // 1. Get all products
            List<Product> allProducts = productDAO.getAllProducts();

            request.setAttribute(
                    "totalProducts", allProducts.size());

            // 2. Get total registered customers
            request.setAttribute(
                    "totalUsers", customerDAO.getTotalCustomers());

            // 3. Get total orders
            request.setAttribute(
                    "totalOrders", orderDAO.getTotalOrders());

            // 4. Get the latest five orders
            List<Order> allOrders = orderDAO.getAllOrders();

            List<Order> recentOrders = new ArrayList<>();

            for (int i = 0; i < allOrders.size() && i < 5; i++) {
                recentOrders.add(allOrders.get(i));
            }

            request.setAttribute("recentOrders", recentOrders);

            // 5. Get products with stock below 10
            List<Product> lowStockProducts =
                    productDAO.getLowStockProducts(10);

            request.setAttribute(
                    "lowStockProducts", lowStockProducts);

            // 6. Revenue will be connected after confirming
            // the successful payment status used by your project.
            request.setAttribute("totalRevenue", 0.0);

            // 7. Forward data to the dashboard JSP
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(
                            "/Pages/Admin/dashboard.jsp");

            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(
                    "Unable to load dashboard data.", e);
        }
    }
}
