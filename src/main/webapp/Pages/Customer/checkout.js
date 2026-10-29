
document.addEventListener("DOMContentLoaded", function () {

    const button = document.getElementById("confirm-button");

    button.addEventListener("click", function (event) {

        event.preventDefault();

        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const mobile = document.getElementById("mobile-number").value.trim();

        const city = document.getElementById("city").value.trim();
        const state = document.getElementById("state").value.trim();
        const pincode = document.getElementById("pincode").value.trim();
        const address = document.getElementById("address").value.trim();

        if (!name || !email || !mobile || !city ||
            !state || !pincode || !address) {
            alert("Please fill in all the required fields.");
            return;
        }

        if (!/^[A-Za-z ]{2,100}$/.test(name)) {
            alert("Please enter a valid name.");
            return;
        }

        if (!/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(email)) {
            alert("Please enter a valid email address.");
            return;
        }

        if (!/^[6-9][0-9]{9}$/.test(mobile)) {
            alert("Please enter a valid 10-digit mobile number.");
            return;
        }

        if (!/^[A-Za-z ]{2,100}$/.test(city) ||
            !/^[A-Za-z ]{2,100}$/.test(state)) {
            alert("Please enter a valid city and state.");
            return;
        }

        if (!/^[1-9][0-9]{5}$/.test(pincode)) {
            alert("Please enter a valid 6-digit pincode.");
            return;
        }

        if (address.length < 5 || address.length > 255) {
            alert("Address must contain 5 to 255 characters.");
            return;
        }

        // Send checkout details to CheckoutServlet.
        const form = document.createElement("form");
        form.method = "POST";
        form.action = checkoutServletUrl;

        const details = {
            name: name,
            email: email,
            "mobile-number": mobile,
            city: city,
            state: state,
            pincode: pincode,
            address: address
        };

        for (const key in details) {
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = key;
            input.value = details[key];
            form.appendChild(input);
        }

        document.body.appendChild(form);
        form.submit();
    });

});
