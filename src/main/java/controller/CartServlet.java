
package controller;

import java.io.IOException;
import java.util.List;

import dao.CartDAO;
import dao.CartDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Cart_item;
import service.CartService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init() {
        CartDAO cartDAO = new CartDAOImpl();
        cartService = new CartService(cartDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String action = request.getParameter("action");
        
        System.out.println("CartServlet action: " + action);
        System.out.println("AJAX header: "
                + request.getHeader("X-Requested-With"));
        
		/*
		 * System.out.println("Content-Type: " + request.getContentType());
		 * System.out.println("Parameter names:");
		 * 
		 * request.getParameterMap().forEach((name, values) -> System.out.println(name +
		 * " = " + java.util.Arrays.toString(values)) );
		 */

        Integer customerId = getCustomerId(request, response);
        if (customerId == null) {
            return;
        }

        Cart cart = cartService.getOrCreateCart(customerId);

        if (cart == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "Unable to open customer cart.");
            return;
        }

        try {
            boolean success = false;

            if ("ADD".equals(action)) {

                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                Cart_item item = new Cart_item();
                item.setCart_id(cart.getId());
                item.setProduct_id(productId);
                item.setQuantity(quantity);

                success = cartService.addToCart(item);

            } else if ("UPDATE".equals(action)) {

                int itemId = Integer.parseInt(request.getParameter("cartItemId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                success = cartService.updateCart(itemId, cart.getId(), quantity);

            } else if ("REMOVE".equals(action)) {

                int itemId = Integer.parseInt(request.getParameter("cartItemId"));

                success = cartService.removeFromCart(itemId, cart.getId());

            } else {
                System.out.println("ERROR: Invalid cart action received.");
                System.out.println("Action value: " + action);
                System.out.println("Request URI: " + request.getRequestURI());

                boolean ajaxRequest = "XMLHttpRequest".equals(
                        request.getHeader("X-Requested-With"));

                if (ajaxRequest) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(
                        "Invalid cart action. Received action: " + action
                    );
                } else {
                    response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid cart action."
                    );
                }

                return;
            }

            boolean ajaxRequest = "XMLHttpRequest".equals(
                    request.getHeader("X-Requested-With"));

            if ("ADD".equals(action) && ajaxRequest) {

                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");

                if (success) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(
                        "Your product has been added to cart successfully!");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write(
                        "Unable to add the product to cart. Please check availability.");
                }

                return;
            }

            if (success) {
                request.getSession().setAttribute(
                    "cartMessage", "Cart updated successfully.");
            } else {
                request.getSession().setAttribute(
                    "cartMessage",
                    "Unable to update cart. Check the product and quantity.");
            }

            response.sendRedirect(
            	    request.getContextPath() + "/cart?action=VIEW"
            	);

        } catch (NumberFormatException e) {

            boolean ajaxRequest = "XMLHttpRequest".equals(
                request.getHeader("X-Requested-With")
            );

            if (ajaxRequest) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(
                    "Invalid product ID, cart item ID, or quantity."
                );
            } else {
                request.getSession().setAttribute(
                    "cartMessage",
                    "Please enter a valid product ID and quantity."
                );

                response.sendRedirect(
                    request.getContextPath() + "/cart?action=VIEW"
                );
            }

        } catch (Exception e) {

            throw new ServletException(
                "Error processing cart request.", e
            );
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        String action = request.getParameter("action");

        if (!"VIEW".equals(action)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                               "Invalid cart action.");
            return;
        }

        Integer customerId = getCustomerId(request, response);
        if (customerId == null) {
            return;
        }

        Cart cart = cartService.getOrCreateCart(customerId);

        if (cart == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "Unable to open customer cart.");
            return;
        }


List<Cart_item> items = cartService.getCartItems(cart.getId());

int totalItems = 0;
for (Cart_item item : items) {
    totalItems += item.getQuantity();
}

double totalAmount = cartService.getCartTotal(cart.getId());

System.out.println("Customer ID: " + customerId);
System.out.println("Cart ID: " + cart.getId());
System.out.println("Cart items retrieved: " + items.size());
System.out.println("Cart total: " + totalAmount);

request.setAttribute("cartItems", items);
request.setAttribute("totalItems", totalItems);
request.setAttribute("totalAmount", totalAmount);

request.getRequestDispatcher("/Pages/Customer/cart.jsp")
       .forward(request, response);

    }


private Integer getCustomerId(HttpServletRequest request,
                              HttpServletResponse response)
                              throws IOException {

    HttpSession session = request.getSession(false);

    if (session == null
            || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))
            || !"customer".equals(session.getAttribute("loginType"))
            || !(session.getAttribute("customerId") instanceof Integer)) {

        boolean ajaxRequest = "XMLHttpRequest".equals(
                request.getHeader("X-Requested-With"));

        if (ajaxRequest) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(
                    request.getContextPath()
                    + "/Pages/Customer/login.jsp");
        } else {
            response.sendRedirect(
                    request.getContextPath()
                    + "/Pages/Customer/login.jsp");
        }

        return null;
    }

    return (Integer) session.getAttribute("customerId");
}

}
