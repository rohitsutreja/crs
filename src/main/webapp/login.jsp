<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%
if (session.getAttribute("user") != null) {
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login for CRS</title>
<%@include file='./bootstrap.html'%>
</head>
<body>
	<section>
		<%@ include file="./header.jsp"%>
		
		<input type="hidden" value="<%=request.getAttribute("status")%>" id="status">
		
		<div class="container">
			<div class="card-header text-center fs-4">
			<h1>Login Page</h1>
		</div>
			<form id="myForm" action="login" method="POST">
				<div class="mb-3">
					<label for="username" class="form-label">Username</label> <input
						type="text" class="form-control" id="username" name="username"
						placeholder="Enter your email">
					<div class="invalid-feedback" id="emailError">Please enter a
						valid username</div>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" id="password" name="password"
						placeholder="Enter your password">
					<div class="invalid-feedback" id="passwordError">Please enter
						a password</div>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>

	</section>
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
	
		let status = `<%= request.getParameter("status") %>`;
		
		if(status === "null"){
			status = document.getElementById('status').value;
		}
		
		console.log(status)
		
		if(status === "password_error"){
			swal("Login not successful", "Username or Password is incorrect!", "error");
		}else if(status === "failed"){
			swal("Login not successful", "User doesn't exist", "error");
		}else if(status === "access_denied"){
			swal("Cannot access restricted paths", "", "error");
		}
    
        function validateForm() {
            var email = document.getElementById("username").value;
            var password = document.getElementById("password").value;

            if (email == "") {
                document.getElementById("emailError").style.display = "block";
                return false;
            }
            
            if (password == "") {
                document.getElementById("passwordError").style.display = "block";
                return false;
            }

            return true;
        }

    </script>
</body>
</html>