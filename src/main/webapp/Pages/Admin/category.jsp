<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Category Management</title>
     <link rel="stylesheet"
      href="${pageContext.request.contextPath}/Pages/Admin/category.css">
</head>
<body>

    <!-- Header -->
    <header class="header">
        <div class="logo">
            <h2>Online Shopping Cart</h2>
        </div>

        <div class="admin-info">
            <span>Welcome, Admin</span>
            <a href="../../index.html">Logout</a>
        </div>
    </header>

    <!-- Admin Container -->
    <div class="admin-container">

        <!-- Sidebar -->
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

        <!-- Main Content -->
        <main class="main">

            <div class="product-header">
                <h1>Category Management</h1>
            </div>

            <!-- Add Category -->
            <div class="add-new-category">

                <h2 id="form-title">Add New Category</h2>

                <form id="category-form">

                    <input type="hidden" id="category-id">

                    <div class="form-group">
                        <label for="category-name">Category Name</label>

                        <input
                            type="text"
                            id="category-name"
                            name="categoryName"
                            placeholder="Enter category name"
                            required>
                    </div>

                    <div class="form-group">
                        <label for="category-description">
                            Category Description
                        </label>

                        <textarea
                            id="category-description"
                            name="categoryDescription"
                            rows="4"
                            placeholder="Enter category description"
                            required></textarea>
                    </div>

                    <div class="form-actions">
                        <button type="submit" id="save-button">
                            Add Category
                        </button>

                        <button type="button" id="cancel-button">
                            Clear
                        </button>
                    </div>

                </form>
            </div>

            <!-- Search Category by ID -->
            <div class="search-category">

                <h2>Find Category by ID</h2>

                <div class="form-group">
                    <label for="search-category-id">Category ID</label>

                    <input
                        type="number"
                        id="search-category-id"
                        min="1"
                        placeholder="Enter category ID">
                </div>

                <div class="form-actions">
                    <button type="button" id="search-button">
                        Search Category
                    </button>

                    <button type="button" id="show-all-button">
                        View All Categories
                    </button>
                </div>

                <p id="search-message"></p>

            </div>

            <!-- View All Categories -->
            <div class="category-list">

                <h2>All Categories</h2>

                <table>
                    <thead>
                        <tr>
                            <th>Category ID</th>
                            <th>Category Name</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody id="category-table-body">
                        <!-- Categories will appear here -->
                    </tbody>
                </table>

                <p id="table-message"></p>

            </div>

        </main>
    </div>
   
<script
    src="${pageContext.request.contextPath}/Pages/Admin/category.js"
    data-context-path="${pageContext.request.contextPath}">
</script>
   
</body>
</html>
    