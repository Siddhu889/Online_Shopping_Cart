<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Shopping Cart</title>
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <div class ="header">
        <div class="logo">
            Online Shopping
        </div>

        <div class="nav">
            <a href="index.jsp">Home</a>
            <a href="${pageContext.request.contextPath}/cart?action=VIEW">Cart</a>
            <a href="Pages/login-option.html">Login</a>
        </div>
    </div>

    <div class="main">
        <h1>Welcome to Online Shopping Cart</h1>
        <p>Find the products you need at the best prices!!!.</p>
        <a href="${pageContext.request.contextPath}/product?action=category&id=2"
           class="shop-now">Shop Now</a>
    </div>
    <br>
    <div class="category-section">
        <h2>Shop By Category</h2>
    <br>
        <div class="category-container">
           <div class="category-card">
               <a href="${pageContext.request.contextPath}/product?action=category&id=1"
                  class="electronics-page">

                <img src="Images/logo/electronics (1).png" width="400">
  
                 <h3>Electronics</h3>
                 <p>Electronics Products</p>

               </a>
          </div>

            <div class="category-card">
               
                <a href="${pageContext.request.contextPath}/product?action=category&id=2"
                 class="fashion-page">
                <img src="${pageContext.request.contextPath}/Images/logo/fashion.png"
                 width="400" alt="Fashion">
                 
                <h3>Clothing</h3>
                <p>Explore Clothing Products</p>
           </a>
               
            </div>

            <div class="category-card">
                  
               <a href="${pageContext.request.contextPath}/product?action=category&id=3"
               class="home-page">
               <img src="${pageContext.request.contextPath}/Images/logo/home appliances.png"
               width="400" alt="Home Appliances">
            
              <h3>Home Appliances</h3>
              <p>Explore accessories</p>
           </a>
                  
                </a>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>@ 2026 Online Shopping Cart</p>
</div>
 <script src="${pageContext.request.contextPath}/app.js"></script>
</body>
</html>