<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
if (session.getAttribute("user") == null) {
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaint Registration System</title>

	<%@ include file="./bootstrap.html"%>
</head>
<body>
	<%@ include file="./header.jsp" %>
	Index Page
</body>
</html>