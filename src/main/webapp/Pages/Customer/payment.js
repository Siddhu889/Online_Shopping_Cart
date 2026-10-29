
document.addEventListener("DOMContentLoaded", function () {

    const paymentForm = document.getElementById("payment-form");
    const paymentDetails = document.getElementById("payment-method-details");

    if (!paymentForm || !paymentDetails) {
        console.error("Payment form or payment details container not found.");
        return;
    }

    const paymentMethods = document.querySelectorAll(
        'input[name="payment-method"]'
    );

    // Display the correct fields for the selected payment method.
    paymentMethods.forEach(function (radio) {

        radio.addEventListener("change", function () {

            const method = this.value;

            paymentDetails.innerHTML = "";

            if (method === "debit-card" || method === "credit-card") {

                paymentDetails.innerHTML = `
                    <div>
                        <h3>${method === "debit-card" ? "Debit Card Details" : "Credit Card Details"}</h3>

                        <label for="card-number">Card Number</label>
                        <input type="text"
                               id="card-number"
                               name="card-number"
                               inputmode="numeric"
                               maxlength="16"
                               autocomplete="cc-number"
                               placeholder="Enter 16-digit card number">

                        <label for="expiry-date">Expiry Date (MM/YY)</label>
                        <input type="text"
                               id="expiry-date"
                               name="expiry-date"
                               maxlength="5"
                               autocomplete="cc-exp"
                               placeholder="MM/YY">

                        <label for="cvv">CVV</label>
                        <input type="password"
                               id="cvv"
                               name="cvv"
                               inputmode="numeric"
                               maxlength="3"
                               autocomplete="off"
                               placeholder="Enter 3-digit CVV">
                    </div>
                `;

            } else if (method === "upi") {

                paymentDetails.innerHTML = `
                    <div>
                        <h3>UPI Payment Details</h3>

                        <label for="upi-id">UPI ID</label>
                        <input type="text"
                               id="upi-id"
                               name="upi-id"
                               autocomplete="off"
                               placeholder="example@upi">
                    </div>
                `;

            } else if (method === "cod") {

                paymentDetails.innerHTML = `
                    <div>
                        <h3>Cash on Delivery</h3>
                        <p>You can pay when your order is delivered.</p>
                    </div>
                `;
            }

        });

    });

    // Validate before submitting to the servlet.
	paymentForm.addEventListener("submit", function (event) {

	    console.log("PAYMENT JS VALIDATION RUNNING");

	    const selectedMethod = document.querySelector(
	        'input[name="payment-method"]:checked'
	    );

        if (!selectedMethod) {
            event.preventDefault();
            alert("Please select a payment method.");
            return;
        }

        const method = selectedMethod.value;

        if (method === "debit-card" || method === "credit-card") {

            const card = document.getElementById("card-number");
            const expiry = document.getElementById("expiry-date");
            const cvv = document.getElementById("cvv");

            if (!card || !expiry || !cvv) {
                event.preventDefault();
                alert("Please select your card payment method again.");
                return;
            }

            const cardNumber = card.value.trim();
            const expiryDate = expiry.value.trim();
            const cvvNumber = cvv.value.trim();

            if (!/^[0-9]{16}$/.test(cardNumber)) {
                event.preventDefault();
                alert("Please enter a valid 16-digit card number.");
                card.focus();
                return;
            }

            if (!/^(0[1-9]|1[0-2])\/[0-9]{2}$/.test(expiryDate)) {
                event.preventDefault();
                alert("Enter the expiry date in MM/YY format.");
                expiry.focus();
                return;
            }

            const parts = expiryDate.split("/");
            const month = Number(parts[0]);
            const year = 2000 + Number(parts[1]);

            const now = new Date();
            const currentMonth = now.getMonth() + 1;
            const currentYear = now.getFullYear();

            if (
                year < currentYear ||
                (year === currentYear && month < currentMonth)
            ) {
                event.preventDefault();
                alert("Your card has expired. Please enter a valid expiry date.");
                expiry.focus();
                return;
            }

            if (!/^[0-9]{3}$/.test(cvvNumber)) {
                event.preventDefault();
                alert("Please enter a valid 3-digit CVV.");
                cvv.focus();
                return;
            }

        } else if (method === "upi") {

            const upiInput = document.getElementById("upi-id");

            if (!upiInput) {
                event.preventDefault();
                alert("Please select UPI payment again.");
                return;
            }

            const upiId = upiInput.value.trim();

            if (!/^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+$/.test(upiId)) {
                event.preventDefault();
                alert("Please enter a valid UPI ID.");
                upiInput.focus();
                return;
            }

        }

        // COD does not require card or UPI details.

        const confirmation = document.getElementById("payment-confirm");

        if (!confirmation || !confirmation.checked) {
            event.preventDefault();
            alert("Please confirm your payment details.");
            if (confirmation) {
                confirmation.focus();
            }
            return;
        }

        // If validation passes, the form submits normally to PaymentServlet.
    });

	console.log("PAYMENT VALIDATION FINISHED");
});
