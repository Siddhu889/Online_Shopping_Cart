
package service;

import java.util.List;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import model.Category;

public class CategoryService {

    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    // Add category
    public String addCategory(Category category) {

        if (category == null) {
            return "Invalid category data";
        }

        String name = category.getName();
        String description = category.getDescription();

        if (name == null || name.trim().isEmpty()) {
            return "Category name is required";
        }

        if (name.trim().length() > 100) {
            return "Category name cannot exceed 100 characters";
        }

        if (description == null || description.trim().isEmpty()) {
            return "Category description is required";
        }

        if (description.trim().length() > 500) {
            return "Category description cannot exceed 500 characters";
        }

        category.setName(name.trim());
        category.setDescription(description.trim());

        // Check for duplicate category names
        List<Category> categories = categoryDAO.getAllCategories();

        for (Category existing : categories) {
            if (existing.getName().equalsIgnoreCase(category.getName())) {
                return "This category name already exists";
            }
        }

        boolean added = categoryDAO.addCategory(category);

        if (added) {
            return "Category added successfully";
        }

        return "Unable to add category. Please check the database connection.";
    }

    // View all categories
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    // Find category by ID
    public Category getCategoryById(int id) {

        if (id <= 0) {
            return null;
        }

        return categoryDAO.getCategoryById(id);
    }

    // Update category
    public String updateCategory(Category category) {

        if (category == null || category.getId() <= 0) {
            return "Invalid category ID";
        }

        String name = category.getName();
        String description = category.getDescription();

        if (name == null || name.trim().isEmpty()) {
            return "Category name is required";
        }

        if (name.trim().length() > 100) {
            return "Category name cannot exceed 100 characters";
        }

        if (description == null || description.trim().isEmpty()) {
            return "Category description is required";
        }

        if (description.trim().length() > 500) {
            return "Category description cannot exceed 500 characters";
        }

        // Confirm that the category exists
        Category existingCategory = categoryDAO.getCategoryById(category.getId());

        if (existingCategory == null) {
            return "Category not found";
        }

        category.setName(name.trim());
        category.setDescription(description.trim());

        // Prevent duplicate names while allowing the current category's name
        List<Category> categories = categoryDAO.getAllCategories();

        for (Category existing : categories) {
            if (existing.getId() != category.getId()
                    && existing.getName().equalsIgnoreCase(category.getName())) {
                return "This category name already exists";
            }
        }

        boolean updated = categoryDAO.updateCategory(category);

        if (updated) {
            return "Category updated successfully";
        }

        return "Unable to update category";
    }

    // Delete category
    public String deleteCategory(int id) {

        if (id <= 0) {
            return "Invalid category ID";
        }

        boolean deleted = categoryDAO.deleteCategory(id);

        if (deleted) {
            return "Category deleted successfully";
        }

        return "Category not found or could not be deleted";
    }
}
