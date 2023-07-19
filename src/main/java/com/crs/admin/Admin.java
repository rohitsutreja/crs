package com.crs.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crs.models.Complaint;
import com.crs.models.User;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin/")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		Connection conn = null;
		PrintWriter out = res.getWriter();

		User user = (User) session.getAttribute("user");

		if (user != null && user.getUsertype().equalsIgnoreCase("Admin")) {
			try {
				System.out.println("Inside Admin Panel");
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_reg", "root", "123456");

				PreparedStatement stmt = conn.prepareStatement(
						"SELECT c.ComplaintID, c.ComplaintTitle, c.ComplaintText, c.ComplaintDate, c.Status, u.UserName, u.Email, u.UserID, u.UserType FROM Complaint c JOIN UserTable u ON c.UserID = u.UserID");

				ResultSet rs = stmt.executeQuery();

				ArrayList<Complaint> complaintData = new ArrayList<>();


				while (rs.next()) {
					int id = rs.getInt("c.ComplaintID");
					String title = rs.getString("c.ComplaintTitle");
					String text = rs.getString("c.ComplaintText");
					Date date = (rs.getDate("c.ComplaintDate"));
					String status = rs.getString("c.Status");
					int userId = rs.getInt("u.UserID");
					String userName = rs.getString("u.UserName");
					String userType = rs.getString("u.UserType");
					String userEmail = rs.getString("u.Email");

					Complaint userComp = new Complaint(id, title, text, userId, status, date, userName, userType,
							userEmail);

					complaintData.add(userComp);

				}
				
				ArrayList<User> userData = new ArrayList<>();

				PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM UserTable");
				ResultSet rs2 = stmt2.executeQuery();

				while (rs2.next()) {
					int id = rs2.getInt("UserID");
					String username = rs2.getString("UserName");
					String userType = rs2.getString("UserType");
					String email = rs2.getString("Email");

					User newUser = new User(id, username, email, userType);
					userData.add(newUser);
				}
				
				// User Data
				for (User userX : userData) {
					System.out.println("ID: " + userX.getId());
					System.out.println("Username: " + userX.getUsername());
					System.out.println("Email: " + userX.getEmail());
					System.out.println("User Type: " + userX.getUsertype());
					System.out.println();
				}
				
				// Complaint Data
				for (Complaint complaint : complaintData) {
					System.out.println("ID: " + complaint.getId());
					System.out.println("Title: " + complaint.getTitle());
					System.out.println("Text: " + complaint.getText());
					System.out.println("User ID: " + complaint.getUserId());
					System.out.println("Status: " + complaint.getStatus());
					System.out.println("Date: " + complaint.getDate());
					System.out.println("User Name: " + complaint.getUserName());
					System.out.println("User Type: " + complaint.getUserType());
					System.out.println("User Email: " + complaint.getUserEmail());
					System.out.println();
				}

				// getting the info and then forwarding to the user
				req.setAttribute("user", user);
				req.setAttribute("userData", userData);
				req.setAttribute("complaintData", complaintData);

				rd = req.getRequestDispatcher("/admin/dashboard.jsp");
				rd.forward(req, res);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			res.sendRedirect("/CRS/login.jsp?status=access_denied");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
