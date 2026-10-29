
/*
 * Cart data is managed by Java Servlet/JSP and MySQL.
 * Do not overwrite server-rendered cart items with localStorage.
 */

document.addEventListener("DOMContentLoaded", function () {

    // Validate quantity inputs.
    const quantityInputs = document.querySelectorAll(
        '.cart-item form input[name="quantity"]'
    );

    quantityInputs.forEach(function (input) {
        input.addEventListener("change", function () {
            if (Number(input.value) < 1) {
                input.value = 1;
            }
        });
    });

    // Check whether the cart is empty before checkout.
    const checkoutButton = document.querySelector(".checkout-button");

    if (checkoutButton) {
        checkoutButton.addEventListener("click", function (event) {

            const cartItems = document.querySelectorAll(".cart-item");

            if (cartItems.length === 0) {
                event.preventDefault();
                alert("Your cart is empty! Please add products before checkout.");
            }

        });
    }

});
