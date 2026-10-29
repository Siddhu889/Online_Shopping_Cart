let registerForm = document.querySelector("form");

registerForm.addEventListener("submit", function(event) {

let username = document.getElementById("username").value;
let email = document.getElementById("email").value;
let phoneno = document.getElementById("phoneno").value;
let passwordInputs = document.querySelectorAll('input[type="password"]');
let password = passwordInputs[0].value;
let confirmPassword = passwordInputs[1].value;

if (username == "" || email == "" || phoneno == "" || password == "" || confirmPassword == "") {
    alert("Please fill all the fields.");
	event.preventDefault();
    return;
}

            if (!/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(email)) 
			   {
			       alert("Please enter a valid email address.");
			       event.preventDefault();
			       return;
			   }
			   
			
			if (!/^[0-9]{10}$/.test(phoneno)) {
			    alert("Please enter a valid 10-digit phone number.");
			    event.preventDefault();
			    return;
			}	
			
			if (!/[A-Z]/.test(password)) 
			    {
			        alert("Password must contain at least one uppercase letter.");
			        event.preventDefault();
			        return;
			    }

			    if (!/[0-9]/.test(password)) 
			    {
			        alert("Password must contain at least one number.");
			        event.preventDefault();
			        return;
			    }

			    if (!/[!@#$%^&*(),.?":{}|<>_\-+=]/.test(password)) 
			    {
			        alert("Password must contain at least one special character.");
			        event.preventDefault();
			        return;
			    }

			    if (password.length < 8) 
			    {
			        alert("Password must contain at least 8 characters.");
			        event.preventDefault();
			        return;
			    }

    if (password != confirmPassword) {

        alert("Password and Confirm Password do not match.");
        event.preventDefault();
        return;
    }

});
