
package service;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import model.Product;

public class ProductService {

    private ProductDAO productDAO = new ProductDAOImpl();
    private CategoryService categoryService = new CategoryService();

    // Add product
    public void addProduct(Product product) {

        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Product name is required");
        }

        if (product.getName().trim().length() > 100) {
            throw new IllegalArgumentException(
                    "Product name cannot exceed 100 characters");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "Product price must be greater than zero");
        }

        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Stock quantity cannot be negative");
        }

        if (product.getCategory_id() <= 0 ||
                categoryService.getCategoryById(
                        product.getCategory_id()) == null) {
            throw new IllegalArgumentException(
                    "Please select a valid category");
        }

        if (product.getImage() == null ||
                product.getImage().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Product image is required");
        }

        if (product.getDescription() != null &&
                product.getDescription().length() > 500) {
            throw new IllegalArgumentException(
                    "Description cannot exceed 500 characters");
        }
        
        if (product.getDetails() != null &&
                product.getDetails().length() > 1000) {
            throw new IllegalArgumentException(
                    "Product details cannot exceed 1000 characters");
        }

        productDAO.addProduct(product);
    }

    // View all products
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
    
 // Get products by category
    public List<Product> getProductsByCategory(int categoryId) {

        if (categoryId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid category ID");
        }

        return productDAO.getProductsByCategory(categoryId);
    }
    
    // Get product by ID
    public Product getProductById(int id) {

        if (id <= 0) {
            return null;
        }

        return productDAO.getProductById(id);
    }

    // Update product
    public boolean updateProduct(Product product) {

        if (product.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid product ID");
        }

        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Product name is required");
        }

        if (product.getName().trim().length() > 100) {
            throw new IllegalArgumentException(
                    "Product name cannot exceed 100 characters");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "Product price must be greater than zero");
        }

        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Stock quantity cannot be negative");
        }

        if (product.getCategory_id() <= 0 ||
                categoryService.getCategoryById(
                        product.getCategory_id()) == null) {
            throw new IllegalArgumentException(
                    "Please select a valid category");
        }

        if (product.getDescription() != null &&
                product.getDescription().length() > 500) {
            throw new IllegalArgumentException(
                    "Description cannot exceed 500 characters");
        }
        
        if (product.getDetails() != null &&
                product.getDetails().length() > 1000) {
            throw new IllegalArgumentException(
                    "Product details cannot exceed 1000 characters");
        }

        product.setAvailability(
                product.getQuantity() > 0
                        ? "In Stock"
                        : "Out of Stock");

        return productDAO.updateProduct(product);
    }
    
 // Delete product after validating its ID
    public boolean deleteProduct(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid product ID");
        }

        return productDAO.deleteProduct(id);
    }

    // Search products by name
    public List<Product> searchProductsByName(String name) {

        List<Product> matchingProducts = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            return getAllProducts();
        }

        for (Product product : getAllProducts()) {

            if (product.getName() != null &&
                    product.getName().toLowerCase()
                    .contains(name.trim().toLowerCase())) {

                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

}
