<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register a Complaint Here !!!!!</title>
<%@ include file="../bootstrap.html" %>
</head>
<body>
	
	<%@ include file="../header.jsp" %>
	
	Killa - West Side Killa
	
	<h1>Complaint Form</h1>

    <form action="register" method="post">
        <label for="userID">User ID:</label>
        <input type="text" name="userID" id="userID" required><br>

        <label for="complaintTitle">Complaint Title:</label>
        <input type="text" name="complaintTitle" id="complaintTitle" required><br>

        <label for="complaintText">Complaint Text:</label><br>
        <textarea name="complaintText" id="complaintText" rows="5" cols="40" required></textarea><br>

        <label for="complaintDate">Complaint Date:</label>
        <input type="date" name="complaintDate" id="complaintDate" required><br>

        <label for="status">Status:</label>
        <select name="status" id="status" required>
            <option value="pending">Pending</option>
            <option value="resolved">Resolved</option>
        </select><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>