console.log("Fashion Details JavaScript loaded!");

const quantity = document.getElementById("quantity");
const cartButton = document.querySelector(".cart-button");
const cartForm = document.querySelector(".add-to-cart-form");

// Validate the selected quantity
if (quantity && cartButton) {

    quantity.addEventListener("change", function () {

        const selectedQuantity = Number(quantity.value);
        const maximumQuantity = Number(quantity.max);

        if (
            !Number.isInteger(selectedQuantity) ||
            selectedQuantity < 1 ||
            selectedQuantity > maximumQuantity
        ) {
            alert("Please enter a valid quantity within available stock.");

            quantity.value = maximumQuantity >= 1 ? 1 : "";
        }
    });
}

// Add product to cart without leaving the details page
if (cartForm) {

    cartForm.addEventListener("submit", function (event) {

        event.preventDefault();

        if (cartButton && cartButton.disabled) {
            return;
        }

        const selectedQuantity = Number(quantity.value);
        const maximumQuantity = Number(quantity.max);

        if (
            !Number.isInteger(selectedQuantity) ||
            selectedQuantity < 1 ||
            selectedQuantity > maximumQuantity
        ) {
            alert("Please enter a valid quantity within available stock.");
            return;
        }

        if (cartButton) {
            cartButton.disabled = true;
        }

        const formData = new FormData(cartForm);
        const requestData = new URLSearchParams();

        formData.forEach(function (value, key) {
            requestData.append(key, value);
        });

        fetch(cartForm.getAttribute("action"), {
            method: "POST",
            headers: {
                "X-Requested-With": "XMLHttpRequest",
                "Content-Type":
                    "application/x-www-form-urlencoded; charset=UTF-8"
            },
            body: requestData.toString()
        })
        .then(async function (response) {

			const message = await response.text();

			// Redirect unauthenticated customers to login
			if (response.status === 401) {
			    window.location.href = message.trim();
			    return null;
			}

            if (!response.ok) {
                throw new Error(message || "Unable to add product to cart.");
            }

            return message;
        })
        .then(function (message) {

            if (message !== null && message !== undefined) {
                alert(message);
            }
        })
        .catch(function (error) {

            console.error("Add to Cart failed:", error);
            alert(error.message);
        })
        .finally(function () {

            if (cartButton) {
                cartButton.disabled = false;
            }
        });
    });
}