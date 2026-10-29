window.addEventListener("load", function () {

    const registerForm = document.querySelector("form");

    registerForm.addEventListener("submit", function (event) {

		event.preventDefault();
		console.log("REGISTER VALIDATION RUNNING");
		console.log("Email:", document.getElementById("email").value);
		console.log("Phone:", document.getElementById("phoneno").value);
		
        const username = document.getElementById("username").value.trim();
        const email = document.getElementById("email").value.trim();
        const phoneno = document.getElementById("phoneno").value.trim();
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;

        // 1. Empty fields
        if (!username || !email || !phoneno || !password || !confirmPassword) {
            event.preventDefault();
            alert("Please fill all the fields.");
            return;
        }

        // 2. Email validation
		if (!/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(email)) {
            event.preventDefault();
            alert("Please enter a valid email address using lowercase letters only.");
            return;
        }

        // 3. Phone validation
        if (!/^[0-9]{10}$/.test(phoneno) || /^(\d)\1{9}$/.test(phoneno)) {
            event.preventDefault();
            alert("Please enter a valid 10-digit phone number.");
            return;
        }

        // 4. Password validation
        if (!/[A-Z]/.test(password)) {
            event.preventDefault();
            alert("Password must contain at least one uppercase letter.");
            return;
        }

        if (!/[0-9]/.test(password)) {
            event.preventDefault();
            alert("Password must contain at least one number.");
            return;
        }

        if (!/[!@#$%^&*(),.?":{}|<>_\-+=]/.test(password)) {
            event.preventDefault();
            alert("Password must contain at least one special character.");
            return;
        }

        if (password.length < 8) {
            event.preventDefault();
            alert("Password must contain at least 8 characters.");
            return;
        }

        // 5. Confirm password
        if (password !== confirmPassword) {
            event.preventDefault();
            alert("Password and Confirm Password do not match.");
            return;
        }

    });

});
