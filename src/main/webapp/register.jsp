<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register for CRS</title>

<%@ include file="./bootstrap.html"%>

<%
if (session.getAttribute("user") != null) {
	response.sendRedirect("index.jsp");
}
%>
</head>
<body>

	<%@ include file="./header.jsp"%>

	<input type="hidden" value="<%=request.getAttribute("status")%>"
		id="status">

	<div class="container">

		<div class="card-header text-center fs-4">
			<h1>Register Page</h1>
		</div>

		<form class="container" id="myForm" action="register" method="POST"
			novalidate>
			<div class="mb-3">
				<label for="username" class="form-label">Username</label> <input
					type="text" class="form-control" id="username"
					placeholder="Enter your full name" name="username" required>
				<div class="invalid-feedback" id="fullNameError">Please enter
					your username</div>
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email</label> <input required
					type="email" class="form-control" id="email"
					placeholder="Enter your email" name="email">
				<div class="invalid-feedback" id="emailError">Please enter a
					valid email address</div>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					required type="password" class="form-control" id="password"
					placeholder="Enter your password" name="password">
				<div class="invalid-feedback" id="passwordError">Please enter
					a password</div>
			</div>
			<div class="mb-3">
				<label for="usertype" class="form-label">UserType</label> <select
					required id="usertype" name="usertype" class="form-select">
					<option value="Student">Student</option>
					<option value="Faculty">Faculty</option>
					<option value="Admin">Admin</option>
				</select><br>
				<div class="invalid-feedback" id="qualificationError">Please
					enter your educational qualification</div>
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>


	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
    	
    	const form = document.querySelector('#myForm');
    	form.addEventListener('submit', (e) => {
    		e.preventDefault();
    		
    		if(validateForm()){
    			form.submit();
    		}
    		
    		return;
    	});
    	
    	const status = document.querySelector("#status").value;
    	
    	if(status === "success"){
    		swal("Account Created", "Please go to login page!")
    	}else if(status === "failed"){
    		swal("Account was not created", "Internal Server Error", "success")
    	}else if(status === "email_error"){
    		swal("Account was not created", "Possible Error: Email or Username is duplicate", "error")
    	}
    	
    	
        function validateForm() {
            var fullName = document.getElementById("username").value;
            var email = document.getElementById("email").value;
            var qualification = document.getElementById("usertype").value;
            var password = document.getElementById("password").value;

            if (fullName == "") {
                document.getElementById("fullNameError").style.display = "block";
                return false;
            }
            if (email == "" || !validateEmail(email)) {
                document.getElementById("emailError").style.display = "block";
                return false;
            }
            if (qualification == "") {
                document.getElementById("qualificationError").style.display = "block";
                return false;
            }
            if (password == "") {
                document.getElementById("passwordError").style.display = "block";
                return false;
            }

            return true;
        }
        
        function validateEmail(email) {
        	  // Regular expression pattern for email validation
        	  var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        	  
        	  // Check if the email matches the pattern
        	  if (emailPattern.test(email)) {
        	    return true; // Valid email
        	  } else {
        	    return false; // Invalid email
        	  }
        	}

    </script>
</body>
</html>