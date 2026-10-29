
package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session, if one exists.
        HttpSession session = request.getSession(false);

        // End the current user's session.
        if (session != null) {
            session.invalidate();
        }

        // Return to the home page.
        response.sendRedirect(
            request.getContextPath() + "/index.jsp"
        );
    }
}
