
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Category" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/Pages/Admin/edit-products.css">
</head>
<body>

    <header class="header">
        <div class="logo">
            <h2>Online Shopping Cart</h2>
        </div>

        <div class="admin-info">
            <span>Welcome, Admin</span>
            <a href="../../index.html">Logout</a>
        </div>
    </header>

    <div class="admin-container">
        <aside class="sidebar">
            <h2>Admin Panel</h2>
             <nav class="nav">
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/product?action=list">Products</a>
    <a href="${pageContext.request.contextPath}/Pages/Admin/category.jsp">Categories</a>
    <a href="${pageContext.request.contextPath}/product?action=add">Add Products</a>
    <a href="${pageContext.request.contextPath}/product?action=list">Edit Product</a>
    <a href="${pageContext.request.contextPath}/user?action=list">Users</a>
    <a href="${pageContext.request.contextPath}/order">Orders</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
        </aside>

        <main class="main">
            <div class="product-header">
                <h1>Product Management</h1>
                <a href="add-products.html">Add New Product</a>
            </div>

            <section class="product-table-section">
                <h2>Edit Products</h2>

                
            <form class="product-controls"
             id="search-form"
             action="${pageContext.request.contextPath}/product"
             method="get">

            <input type="hidden" name="action" value="manage">

            <input type="text"
             id="search-product"
             name="name"
             placeholder="Search product..."
             required>

             <button type="submit" id="search-btn">Search</button>

             </form>
                

                <table>
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Product Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody id="product-list">
                        <%
                            List<Product> products =
                                (List<Product>) request.getAttribute("products");

                            if (products != null && !products.isEmpty()) {
                                for (Product p : products) {
                                    String image = p.getImage() == null
                                        ? "" : p.getImage();
                                    String name = p.getName() == null
                                        ? "" : p.getName();
                        %>
                        <tr>
                            <td>
                                <% if (!image.isEmpty()) { %>
                                    <img src="<%= request.getContextPath() %>/<%= image %>"
                                         alt="Product image" width="60">
                                <% } else { %>
                                    No image
                                <% } %>
                            </td>
                            <td><%= name %></td>
                            <td data-category-id="<%= p.getCategory_id() %>">
                                <%= p.getCategory_id() %>
                            </td>
                            <td>₹<%= p.getPrice() %></td>
                            <td><%= p.getQuantity() %></td>
                            
                            <td>
                              <a href="<%= request.getContextPath() %>/product?action=edit&id=<%= p.getId() %>&name=<%= java.net.URLEncoder.encode(request.getParameter("name") == null ? "" : request.getParameter("name"), "UTF-8") %>">
                             Edit
                              </a>
                            </td>
                            
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="6">No products found.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </section>

            <section class="edit-product-form">
                <h2>Edit Product</h2>

                <%
                    Product selectedProduct =
                        (Product) request.getAttribute("product");
                    List<Category> categories =
                        (List<Category>) request.getAttribute("categories");
                %>

                <form id="edit-product-form"
                      action="<%= request.getContextPath() %>/product?action=update"
                      method="post"
                      enctype="multipart/form-data">

                    <input type="hidden" id="product-id" name="id"
                           value="<%= selectedProduct == null ? "" : selectedProduct.getId() %>">

                    <div class="form-group">
                        <label for="product-name">Product Name</label>
                        <input type="text" id="product-name"
                               name="productName" maxlength="100"
                               value="<%= selectedProduct == null || selectedProduct.getName() == null ? "" : selectedProduct.getName() %>"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="product-category">Category</label>
                        <select id="product-category" name="category" required>
                            <option value="">Select Category</option>
                            <% if (categories != null) {
                                for (Category category : categories) { %>
                                    <option value="<%= category.getId() %>"
                                        <%= selectedProduct != null
                                            && category.getId() == selectedProduct.getCategory_id()
                                            ? "selected" : "" %>>
                                        <%= category.getName() %>
                                    </option>
                            <%  }
                               } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="product-price">Price</label>
                        <input type="number" id="product-price"
                               name="price" min="0.01" step="0.01"
                               value="<%= selectedProduct == null ? "" : selectedProduct.getPrice() %>"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="product-stock">Stock</label>
                        <input type="number" id="product-stock"
                               name="stock" min="0" step="1"
                               value="<%= selectedProduct == null ? "" : selectedProduct.getQuantity() %>"
                               required>
                    </div>

                    <div class="form-group">
                        <label>Current Product Image</label>
                        <% if (selectedProduct != null
                               && selectedProduct.getImage() != null
                               && !selectedProduct.getImage().trim().isEmpty()) { %>
                            <img src="<%= request.getContextPath() %>/<%= selectedProduct.getImage() %>"
                                 alt="Current product image" width="100">
                        <% } else { %>
                            <p>No image selected.</p>
                        <% } %>
                    </div>

                    <div class="form-group">
                        <label for="product-image">Product Image</label>
                        <input type="file" id="product-image"
                               name="productImage" accept="image/*">
                    </div>

                    <div class="form-group">
                        <label for="product-description">Description</label>
                        <textarea id="product-description" name="description"
                                  rows="5" maxlength="500" required><%= selectedProduct == null || selectedProduct.getDescription() == null ? "" : selectedProduct.getDescription() %></textarea>
                    </div>

                    <div class="form-group">
                        <label for="product-details">Product Details</label>
                        <textarea id="product-details" name="details"
                                  rows="5" maxlength="1000"><%= selectedProduct == null || selectedProduct.getDetails() == null ? "" : selectedProduct.getDetails() %></textarea>
                    </div>

                    <div class="form-group">
                        <label for="product-features">Product Features</label>
                        <textarea id="product-features" name="features"
                                  rows="5"><%= selectedProduct == null || selectedProduct.getFeatures() == null ? "" : selectedProduct.getFeatures() %></textarea>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="update-product-btn">
                            Update Product
                        </button>

                        <a href="edit-products.html" class="cancel-btn">
                            Cancel
                        </a>
                    </div>
                </form>
            </section>
        </main>
    </div>

   <script src="${pageContext.request.contextPath}/Pages/Admin/edit-products.js"></script>
</body>
</html>
