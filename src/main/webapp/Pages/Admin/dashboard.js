document.addEventListener("DOMContentLoaded", function() {
   /* const totalProducts = document.getElementById("total-products");
    const totalUsers = document.getElementById("total-users");
    const totalOrders = document.getElementById("total-orders");
    const totalRevenue = document.getElementById("total-revenue");*/
    const recentOrdersBody = document.getElementById("recent-orders-body");
    const productOverviewBody = document.getElementById("product-overview-body");
    const lowStockBody = document.getElementById("low-stock-body");

    /*function displaySummaryData(products, users, orders, revenue){
        totalProducts.textContent = products;
        totalUsers.textContent = users;
        totalOrders.textContent = orders;
        totalRevenue.textContent = revenue;
    }*/

function displayRecentOrders(orders){
    recentOrdersBody.innerHTML = "";

    if(!orders || orders.length === 0){
        recentOrdersBody.innerHTML = `<tr>
        <td colspan="5">No recent orders available.</td>
        </tr>`;

    return;
}

orders.forEach(function (order){
     const row = document.createElement("tr");

     row.innerHTML = 
     `<td>${order.id}</td>
     <td>${order.customer}</td>
     <td>${order.date}</td>
     <td>${order.amount}</td>
     <td>${order.status}</td>`;

     recentOrdersBody.appendChild(row);
});

}

function displayProducts(products){
    productOverviewBody.innerHTML = "";

    if(!products || products.length === 0){
        productOverviewBody.innerHTML =`
    <tr>
        <td colspan="5">No products available.</td>
    </tr>
`;
    return;
}

products.forEach(function (product){
     const row = document.createElement("tr");

     row.innerHTML = `
    <td>${product.id}</td>
    <td>${product.name}</td>
    <td>${product.category}</td>
    <td>${product.price}</td>
    <td>${product.stock}</td>
`;

productOverviewBody.appendChild(row);

});

}

function displayLowStockProducts(products){
    lowStockBody.innerHTML = "";

    if(!products || products.length === 0)
    {
        lowStockBody.innerHTML = `<tr>
        <td colspan="5">
        No low stcok products available.
        </td>
        </tr>`;

        return;
}


products.forEach(function (product){
    const row = document.createElement("tr");

    row.innerHTML = `
    <td>${product.id}</td>
    <td>${product.name}</td>
    <td>${product.category}</td>
    <td>${product.stock}</td>
    <td>
    <a href="edit-products.html">Edit</a>
    </td>`;

    lowStockBody.appendChild(row);
});

}

});