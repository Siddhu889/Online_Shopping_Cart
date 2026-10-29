
const productTableBody = document.getElementById("product-table-body");

function deleteProduct(id) {
    if (confirm("Are you sure you want to delete this product?")) {
        window.location.href = "../../product?action=delete&id=" + id;
    }
}


/*const productTableBody = document.getElementById("product-table-body");

function displayProducts(products){
    productTableBody.innerHTML = "";
    products.forEach(function(product){
        const row = document.createElement("tr");

        row.innerHTML = `
        <td>${product.id}</td>
        <td>
        <img 
        src="${product.image}"
        alt="${product.name}"
        class="product-image">
        </td>
        
        <td>${product.name}</td>
        <td>${product.category}</td>
        <td>${product.stock}</td>
        
        <td>
        <span class="status">
        ${product.status}
        </span>
        </td>
        
        <td>
        <div class="action-buttons">
        <a href="edit-products.jsp" class="edit-btn">
                        Edit
                    </a>

                    <button 
                        class="delete-btn"
                        onclick="deleteProduct(${product.id})">
                        Delete
                    </button>

                </div>
            </td>`;
            productTableBody.appendChild(row);
    });
}*/