
package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;

@WebServlet("/product")
@MultipartConfig
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {

            showProducts(request, response);

        }  else if (action.equals("category")) {

            try {
                int categoryId = Integer.parseInt(
                        request.getParameter("id"));

                List<Product> products =
                        productService.getProductsByCategory(categoryId);

                request.setAttribute("products", products);

                String page;

                if (categoryId == 1) {
                    page = "/Pages/Customer/electronics.jsp";
                } else if (categoryId == 2) {
                    page = "/Pages/Customer/fashion.jsp";
                } else if (categoryId == 3) {
                    page = "/Pages/Customer/home.jsp";
                } else {
                    response.sendError(
                            HttpServletResponse.SC_BAD_REQUEST,
                            "Invalid category ID");
                    return;
                }

                request.getRequestDispatcher(page)
                        .forward(request, response);
                
            } catch (NumberFormatException e) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid category ID");
            }
        }

              else if ("details".equals(action)) {

    try {
        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.getProductById(id);

        if (product == null) {
            response.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Product not found"
            );
            return;
        }

        Category category =
            categoryService.getCategoryById(product.getCategory_id());

        request.setAttribute("product", product);
        request.setAttribute(
            "categoryName",
            category != null ? category.getName() : "Unknown"
        );

        String page;

        if (product.getCategory_id() == 1) {
            page = "/Pages/Customer/electronics-details.jsp";
        } else if (product.getCategory_id() == 2) {
            page = "/Pages/Customer/fashion-details.jsp";
        } else if (product.getCategory_id() == 3) {
            page = "/Pages/Customer/home-details.jsp";
        } else {
            response.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Unsupported product category"
            );
            return;
        }

        request.getRequestDispatcher(page).forward(request, response);

    } catch (NumberFormatException e) {
        response.sendError(
            HttpServletResponse.SC_BAD_REQUEST,
            "Invalid product ID"
        );
    }

            
        
        } else if (action.equals("manage")) {

            String name = request.getParameter("name");

            List<Product> products;

            if (name == null || name.trim().isEmpty()) {
                products = new ArrayList<Product>();
            } else {
                products = productService.searchProductsByName(name);
            }

            request.setAttribute("products", products);
            request.setAttribute("categories",
                    categoryService.getAllCategories());
            request.setAttribute("product", null);

            request.getRequestDispatcher(
                    "/Pages/Admin/edit-products.jsp")
                    .forward(request, response);

        } else if (action.equals("search")) {

            String name = request.getParameter("name");

            List<Product> products =
                    productService.searchProductsByName(name);

            request.setAttribute("products", products);

            request.getRequestDispatcher(
                    "/Pages/Admin/product.jsp")
                    .forward(request, response);

        } else if (action.equals("add")) {

            request.setAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            request.getRequestDispatcher(
                    "/Pages/Admin/add-products.jsp")
                    .forward(request, response);

        } else if (action.equals("edit")) {

            try {
                int id = Integer.parseInt(
                        request.getParameter("id"));

                Product product =
                        productService.getProductById(id);

                if (product == null) {
                    response.sendRedirect(
                            request.getContextPath()
                                    + "/product?action=manage");
                    return;
                }
                
                String name = request.getParameter("name");

                // Load the selected product and keep the full table
                showEditProducts(request, response, product, name);

            } catch (NumberFormatException e) {
                response.sendRedirect(
                        request.getContextPath()
                                + "/product?action=manage");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        try {

            if ("add".equals(action)) {

                Product product = getProductFromRequest(
                        request, response, false);

                productService.addProduct(product);

                response.sendRedirect(
                        request.getContextPath()
                                + "/product?action=list");

            } else if ("update".equals(action)) {

                int id = Integer.parseInt(
                        request.getParameter("id"));

                Product product = getProductFromRequest(
                        request, response, true);

                product.setId(id);

                boolean updated =
                        productService.updateProduct(product);

                if (updated) {
                    response.sendRedirect(
                            request.getContextPath()
                                    + "/product?action=list");
                } else {
                    response.sendError(
                            HttpServletResponse.SC_NOT_FOUND,
                            "Product not found");
                }
                
            } else if ("delete".equals(action)) {

                int id = Integer.parseInt(
                        request.getParameter("id"));

                boolean deleted = productService.deleteProduct(id);

                if (deleted) {
                    response.sendRedirect(
                            request.getContextPath()
                                    + "/product?action=list");
                } else {
                    response.sendError(
                            HttpServletResponse.SC_NOT_FOUND,
                            "Product not found or already deleted");
                }

            } else {
                response.sendRedirect(
                        request.getContextPath()
                                + "/product?action=list");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid product ID, price, stock, or category");

        } catch (IllegalArgumentException e) {
            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    e.getMessage());

        } catch (RuntimeException e) {
            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Product update failed. Check the server error log.");
        }
    }

    // Load the edit page, product table, and categories
    private void showEditProducts(
            HttpServletRequest request,
            HttpServletResponse response,
            Product selectedProduct,
            String searchName)
            throws ServletException, IOException {

    	List<Product> products;

    	if (searchName == null || searchName.trim().isEmpty()) {
    	    products = new ArrayList<Product>();
    	} else {
    	    products = productService.searchProductsByName(searchName);
    	}

    	List<Category> categories =
    	        categoryService.getAllCategories();

    	request.setAttribute("products", products);
    	request.setAttribute("categories", categories);
    	request.setAttribute("product", selectedProduct);
        request.getRequestDispatcher(
                "/Pages/Admin/edit-products.jsp")
                .forward(request, response);
    }

    // Read product details from the form
    private Product getProductFromRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            boolean isUpdate)
            throws ServletException, IOException {

    	Product product;

    	if (isUpdate) {
    	    int id = Integer.parseInt(request.getParameter("id"));
    	    product = productService.getProductById(id);

    	    if (product == null) {
    	        throw new IllegalArgumentException("Product not found");
    	    }
    	} else {
    	    product = new Product();
    	}

    	String name = request.getParameter("productName");
    	double price = Double.parseDouble(request.getParameter("price"));
    	int quantity = Integer.parseInt(request.getParameter("stock"));
    	int categoryId = Integer.parseInt(request.getParameter("category"));

    	String description = request.getParameter("description");
    	String details = request.getParameter("details");
    	String features = request.getParameter("features");

    	product.setName(name);
    	product.setPrice(price);
    	product.setQuantity(quantity);
    	product.setCategory_id(categoryId);
    	product.setDescription(description);
    	product.setDetails(details);
    	product.setFeatures(features);
    	product.setAvailability(quantity > 0 ? "In Stock" : "Out of Stock");

        // Check the selected category
        Category category =
                categoryService.getCategoryById(categoryId);

        if (category == null) {
            throw new IllegalArgumentException(
                    "Please select a valid category");
        }

        Part imagePart = request.getPart("productImage");

        // Upload a new image if the user selected one
        if (imagePart != null && imagePart.getSize() > 0) {

            String originalName =
                    imagePart.getSubmittedFileName();

            String extension = "";

            if (originalName != null
                    && originalName.lastIndexOf('.') >= 0) {

                extension = originalName.substring(
                        originalName.lastIndexOf('.'))
                        .toLowerCase();
            }

            String categoryName =
                    category.getName().toLowerCase();

            String folderName;

            if (categoryName.contains("electronics")) {
                folderName = "electronics";
            } else if (categoryName.contains("fashion")) {
                folderName = "fashion";
            } else {
                folderName = "home";
            }

            String uploadPath = getServletContext()
                    .getRealPath("/Images/" + folderName);

            if (uploadPath == null) {
                throw new ServletException(
                        "Unable to locate the image upload folder");
            }

            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()
                    && !uploadFolder.mkdirs()) {
                throw new ServletException(
                        "Unable to create the image upload folder");
            }

            String fileName = UUID.randomUUID() + extension;

            imagePart.write(
                    new File(uploadFolder, fileName)
                            .getAbsolutePath());

            product.setImage(
                    "Images/" + folderName + "/" + fileName);

        } else if (isUpdate) {
            // Keep the existing product image.
            // The product already contains its old image.
        } else {
            throw new IllegalArgumentException("Product image is required");
        }

        return product;
    }

    // Display products on the existing product list page
    private void showProducts(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products =
                productService.getAllProducts();

        request.setAttribute("products", products);

        request.getRequestDispatcher(
                "/Pages/Admin/product.jsp")
                .forward(request, response);
    }
}
