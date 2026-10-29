<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add-product.html</title>
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/Pages/Admin/add-products.css">
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
                <a href="${pageContext.request.contextPath}/product?action=add"> Add New Product</a>
            </div>

            <div class="add-new-product">
                <h1>Add New Product</h1>

                <form id="add-new-product"
                 action="${pageContext.request.contextPath}/product?action=add"
                 method="post"
                 enctype="multipart/form-data">
                 
                    <div class="form-group">
                        <label for="product-name">
                            Product Name
                        </label>

                        <input
                        type="text"
                        id="product-name"
                        name="productName"
                        placeholder="Enter product name"
                        required>
                    </div>

                    <div class="form-group">
                        <label for="product-category">Category</label>

                      <select id="product-category" name="category" required>
                      <option value="">Select Category</option>

                      <% java.util.List<model.Category> categories =
                         (java.util.List<model.Category>)
                         request.getAttribute("categories");

                        if (categories != null) {
                        for (model.Category category : categories) {
                       %>
                    <option value="<%= category.getId() %>">
                        <%= category.getId() %> -
                        <%= category.getName() %>
                    </option>
                       <%
                           }
                          }
                        %>
                    </select>
                     </div>
                
                    <div class="form-group">
                        <label for="product-price">
                            Price
                        </label>

                       <input type="number"
                          id="product-price"
                          name="price"
                          placeholder="Enter product price"
                          min="0.01"
                          step="0.01"
                          required>
                    </div>

                    <div class="form-group">
                        <label for="product-stock">
                            Stock Quantity
                        </label>

                        <input 
                        type="number"
                        id="product-stock"
                        name="stock"
                        placeholder="Enter stock quantity"
                        min="0"
                        step="1"
                        required
                        >
                    </div>

                    <div class="form-group">
                        <label for="product-image">
                            Product Image
                        </label>

                        <input
                        type="file"
                        id="product-image"
                        name="productImage"
                        accept="image/*"
                        required>

                    </div>


                     <div class="form-group">

                        <label for="product-description">
                            Product Description
                        </label>

                        <textarea id="product-description"
                           name="description"
                           rows="5"
                           maxlength="500"
                           placeholder="Enter product description"
                           required>
                           </textarea>

                    </div>
                    
                    <div class="form-group">
                    <label for="product-details">Product Details</label>

                    <textarea
        id="product-details"
        name="details"
        rows="5"
        maxlength="1000"
        placeholder="Enter product details"></textarea>
</div>

<div class="form-group">
    <label for="product-features">Product Features</label>

    <textarea
        id="product-features"
        name="features"
        rows="5"
        placeholder="Enter product features"></textarea>
</div>

                    <div class="form-actions">
                        <button 
                        type="submit"
                        class="add-product-btn"
                        >
                    Add Product</button>

                   <a href="${pageContext.request.contextPath}/product?action=list"
                      class="cancel-btn">Cancel</a>

                    </div>
                    
                </form>
            </div>
      </main>
    </div> 
   <script src="${pageContext.request.contextPath}/Pages/Admin/add-products.js"></script>
</body>
</html>