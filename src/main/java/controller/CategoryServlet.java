
package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Category;
import service.CategoryService;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryService();
    }

    // GET: View all categories or find a category by ID
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        try {
            if ("all".equals(action)) {

                List<Category> categories =
                        categoryService.getAllCategories();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Category category : categories) {

                    if (!first) {
                        json.append(",");
                    }

                    json.append(categoryToJson(category));
                    first = false;
                }

                json.append("]");
                sendJson(response, json.toString());

            } else if ("search".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.trim().isEmpty()) {
                    response.setStatus(
                            HttpServletResponse.SC_BAD_REQUEST);

                    sendResult(response, false,
                            "Category ID is required");
                    return;
                }

                int id = Integer.parseInt(idValue);

                Category category =
                        categoryService.getCategoryById(id);

                if (category == null) {
                    response.setStatus(
                            HttpServletResponse.SC_NOT_FOUND);

                    sendResult(response, false,
                            "Category not found");
                } else {
                    sendJson(response, categoryToJson(category));
                }

            } else {
                response.setStatus(
                        HttpServletResponse.SC_BAD_REQUEST);

                sendResult(response, false, "Invalid action");
            }

        } catch (NumberFormatException e) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST);

            sendResult(response, false, "Invalid category ID");
        }
    }

    // POST: Add, update or delete a category
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        try {
            String message;

            if ("add".equals(action)) {

                Category category = new Category();

                category.setName(
                        request.getParameter("categoryName"));

                category.setDescription(
                        request.getParameter("categoryDescription"));

                message = categoryService.addCategory(category);

            } else if ("update".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.trim().isEmpty()) {
                    response.setStatus(
                            HttpServletResponse.SC_BAD_REQUEST);

                    sendResult(response, false,
                            "Category ID is required");
                    return;
                }

                Category category = new Category();

                category.setId(Integer.parseInt(idValue));

                category.setName(
                        request.getParameter("categoryName"));

                category.setDescription(
                        request.getParameter("categoryDescription"));

                message = categoryService.updateCategory(category);

            } else if ("delete".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.trim().isEmpty()) {
                    response.setStatus(
                            HttpServletResponse.SC_BAD_REQUEST);

                    sendResult(response, false,
                            "Category ID is required");
                    return;
                }

                message = categoryService.deleteCategory(
                        Integer.parseInt(idValue));

            } else {
                sendResult(response, false, "Invalid action");
                return;
            }

            boolean success = message.endsWith("successfully");

            sendResult(response, success, message);

        } catch (NumberFormatException e) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST);

            sendResult(response, false, "Invalid category ID");

        } catch (Exception e) {

            getServletContext().log(
                    "Category operation failed", e);

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            sendResult(response, false,
                    "An error occurred while processing the category");
        }
    }

    // Convert a category object into JSON
    private String categoryToJson(Category category) {

        return "{\"id\":" + category.getId()
                + ",\"name\":\"" + escapeJson(category.getName())
                + "\",\"description\":\""
                + escapeJson(category.getDescription())
                + "\"}";
    }

    // Escape special characters in JSON strings
    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

    // Send a JSON response
    private void sendJson(HttpServletResponse response,
                          String json) throws IOException {

        response.getWriter().write(json);
    }

    // Send success or failure messages
    private void sendResult(HttpServletResponse response,
                            boolean success,
                            String message) throws IOException {

        String json = "{\"success\":" + success
                + ",\"message\":\"" + escapeJson(message) + "\"}";

        sendJson(response, json);
    }
}
