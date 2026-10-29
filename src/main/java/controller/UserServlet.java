package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            showUsers(request, response);

        } else if (action.equals("search")) {
            searchUsers(request, response);

        } else {
            response.getWriter().println("Invalid user action.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("status".equals(action)) {
            changeStatus(request, response);

        } else if ("delete".equals(action)) {
            deleteUser(request, response);

        } else {
            response.getWriter().println("Invalid user action.");
        }
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = userService.getAllUsers();

        request.setAttribute("users", users);

        request.getRequestDispatcher(
                "/Pages/Admin/users.jsp"
        ).forward(request, response);
    }

    private void searchUsers(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        String searchText = request.getParameter("search");

        List<User> users = userService.searchUsers(searchText);

        request.setAttribute("users", users);

        request.getRequestDispatcher(
                "/Pages/Admin/users.jsp"
        ).forward(request, response);
    }

    private void changeStatus(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.changeStatus(id);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid user ID.");
            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/user?action=list"
        );
    }

    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.deleteUser(id);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid user ID.");
            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/user?action=list"
        );
    }
}