<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.crs.models.User" %>
    
    <% User user = (User) session.getAttribute("user"); %>
    
<nav class="mb-3 navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid" id="navbar">
        
        	<a class="navbar-brand" href="#">CRS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/CRS/index.jsp">Home</a>
                </li>
                
                <% if(user == null) {%>
                
                <li class="nav-item">
                    <a class="nav-link" href="/CRS/register.jsp">Register</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="/CRS/login.jsp">Login</a>
                </li>
                
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="/CRS/complaints/register">Complaints</a>
                </li>
                	<li class="nav-item">
                    <a class="nav-link" href="/CRS/my-profile">Welcome, <%= user.getUsername() %></a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="/CRS/logout">Logout</a>
                </li>
                <% } %>
                
            </ul>
        </div>
    </div>
</nav>

<style>
	nav {
		border-bottom-right-radius: .5rem;
		border-bottom-left-radius: .5rem;
	}
	
	#navbar{
		display:flex;
		
	}
</style>
