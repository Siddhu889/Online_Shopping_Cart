let loginForm = document.querySelector("form");

loginForm.addEventListener("submit", function(event) 
{
    let email = document.getElementById("email").value;
    let phoneno = document.getElementById("phoneno").value;
    let password = document.getElementById("password").value;

    if(email === "")
        {
           alert("Please enter your email.")
		   event.preventDefault();
           return;
        }
		   
        if(phoneno === "")
        {
            alert("Please enter your phone number.");
			event.preventDefault();
            return;
        }
		
		if (!/^[0-9]{10}$/.test(phoneno)) {
		    alert("Please enter a valid 10-digit phone number.");
		    event.preventDefault();
		    return;
		}	

        if(password === "")
        {
            alert("Please enter your password.");
			event.preventDefault();
            return;
        }
		

    });