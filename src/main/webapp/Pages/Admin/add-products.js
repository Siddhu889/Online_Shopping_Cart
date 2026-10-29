
const addProductForm = document.getElementById("add-new-product");

addProductForm.addEventListener("submit", function(event) {

    event.preventDefault();

    const name = document.getElementById("product-name").value.trim();
    const category = document.getElementById("product-category").value;
    const price = document.getElementById("product-price").value;
    const stock = document.getElementById("product-stock").value;
    const image = document.getElementById("product-image");
    const description = document.getElementById("product-description").value;

    if (name === "") {
        alert("Please enter the product name.");
        document.getElementById("product-name").focus();
        return;
    }

    if (name.length > 100) {
        alert("Product name cannot exceed 100 characters.");
        document.getElementById("product-name").focus();
        return;
    }

    if (category === "") {
        alert("Please select a product category.");
        document.getElementById("product-category").focus();
        return;
    }

    if (price.trim() === "" || !Number.isFinite(Number(price)) ||
        Number(price) <= 0) {
        alert("Please enter a valid price greater than zero.");
        document.getElementById("product-price").focus();
        return;
    }

    if (stock.trim() === "" || !Number.isInteger(Number(stock)) ||
        Number(stock) < 0) {
        alert("Please enter a valid stock quantity.");
        document.getElementById("product-stock").focus();
        return;
    }
	
    if (image.files.length === 0) {
        alert("Please select a product image.");
        image.focus();
        return;
    }

    const allowedTypes = [
        "image/jpeg",
        "image/png",
        "image/gif",
        "image/webp"
    ];

    if (!allowedTypes.includes(image.files[0].type)) {
        alert("Please select a JPG, PNG, GIF, or WEBP image.");
        image.focus();
        return;
    }

    if (description.length > 500) {
        alert("Description cannot exceed 500 characters.");
        document.getElementById("product-description").focus();
        return;
    }

    addProductForm.submit();
});
