const editProductForm = document.getElementById("edit-product-form");
const productImage = document.getElementById("product-image");

productImage.addEventListener("change", function () {
    const file = productImage.files[0];

    if (file && !file.type.startsWith("image/")) {
        alert("Please select a valid image file.");
        productImage.value = "";
    }
});

editProductForm.addEventListener("submit", function (event) {
    const productId = document.getElementById("product-id").value;
    const productName = document.getElementById("product-name").value.trim();
    const category = document.getElementById("product-category").value;
    const price = Number(document.getElementById("product-price").value);
    const stock = Number(document.getElementById("product-stock").value);

    if (!productId) {
        event.preventDefault();
        alert("Please select a product from the table before updating.");
        return;
    }

    if (!productName || !category) {
        event.preventDefault();
        alert("Please enter the product name and select a category.");
        return;
    }

    if (!Number.isFinite(price) || price <= 0) {
        event.preventDefault();
        alert("Price must be greater than zero.");
        return;
    }

    if (!Number.isInteger(stock) || stock < 0) {
        event.preventDefault();
        alert("Stock must be zero or greater.");
    }
});
