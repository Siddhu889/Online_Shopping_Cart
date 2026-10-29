
console.log("Electronics JavaScript loaded!");

const productsContainer = document.querySelector(".products");
const searchInput = document.querySelector(".search-input");

// Product search
if (productsContainer && searchInput) {

    searchInput.addEventListener("input", function () {

        const searchText = searchInput.value.trim().toLowerCase();
        const productCards =
            productsContainer.querySelectorAll(".products-card");

        productCards.forEach(function (card) {

            const productName =
                card.querySelector(".product-name").textContent.toLowerCase();

            card.style.display =
                productName.includes(searchText) ? "" : "none";
        });
    });
}

// Add to Cart without leaving the electronics page
const cartForms = document.querySelectorAll(".add-to-cart-form");

cartForms.forEach(function (form) {

    form.addEventListener("submit", function (event) {

        event.preventDefault();

        const button = form.querySelector(".cart-button");

        if (button && button.disabled) {
            return;
        }

        if (button) {
            button.disabled = true;
        }

        const formData = new FormData(form);
        const requestData = new URLSearchParams();

        formData.forEach(function (value, key) {
            requestData.append(key, value);
        });

        fetch(form.getAttribute("action"), {
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

				    if (response.status === 401) {
				        window.location.href = message.trim();
				        return null;
				    }

				    if (!response.ok) {
				        throw new Error(
				            message || "Unable to add product to cart."
				        );
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

            if (button) {
                button.disabled = false;
            }
        });
    });
});
