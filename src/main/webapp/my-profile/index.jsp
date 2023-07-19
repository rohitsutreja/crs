<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Profile</title>
<%@ include file="../bootstrap.html"%>

<%@ page import="com.crs.models.*"%>
<%@ page import="java.util.ArrayList"%>

<style>
	table, th, td {
  border: 1px solid white;
  border-collapse: collapse;
  padding: .5rem;
}
th, td {
  background-color: #96D4D4;
}
</style>

</head>
<body>
	<%@ include file="../header.jsp"%>

	<div class="container">
		<%
		if (user != null) {
		%>

		<%=user.getUsername()%>
		<%=user.getEmail()%>
		<%=user.getId()%>

		<%
		ArrayList<Complaint> complaintData = (ArrayList<Complaint>) request.getAttribute("complaintData");
		%>

		<table border=2>
			<%
			for (Complaint complaint : complaintData) {
			%>
			<tr>
				<td><%=complaint.getTitle()%></td>
				<td><%=complaint.getText()%></td>
				<td><%= complaint.getStatus() %>
				<td><%= String.valueOf(complaint.getDate()) %></td>
				
				<!-- Add more columns as needed -->
			</tr>
			<%
			}
			%>
		</table>


		<%
		}
		%>
	</div>
</body>
</html>