package com.crs.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationServlet
 */

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = res.getWriter();

		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String usertype = req.getParameter("usertype");

//		out.println(username + " "+ email + " " + password + " " + usertype);
		RequestDispatcher rd = null;
		Connection conn = null;
		HttpSession session = req.getSession();

		if (session.getAttribute("user") != null) {
			res.sendRedirect("/CRS/index.jsp");
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_reg", "root",
					"123456");
			
//			Query to find duplicate email
			PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM usertable WHERE Email = ? OR username = ?");
			stmt3.setString(1, email);
			stmt3.setString(2, username);
			
			ResultSet rs = stmt3.executeQuery();
			
			rd = req.getRequestDispatcher("register.jsp");
			if(rs.next()) {
				System.out.println("ERROR");

				req.setAttribute("status", "email_error");	
				rd.forward(req, res);
				return;
			}
				

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO usertable (UserName, Email, Password, UserType) VALUES (?, ?, ?, ?)");
			stmt.setString(1, username);
			stmt.setString(2, email);
			stmt.setString(3, PasswordHasher.hashPassword(password));
			stmt.setString(4, usertype);
			int rowCount = stmt.executeUpdate();

			if(rowCount > 0) {
				req.setAttribute("status", "success");
				
			}else {
				req.setAttribute("status", "failed");
			}
			
			rd.forward(req, res);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			out.println("error");
		}finally {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
