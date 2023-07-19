package com.crs.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crs.models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Connection conn = null;
		RequestDispatcher rd = null;
//		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();

		if (session.getAttribute("user") != null) {
			res.sendRedirect("/CRS/index.jsp");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_reg", "root",
					"123456");
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * from UserTable WHERE UserName = ?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			
			System.out.println("Printing the Result");

			if (rset.next()) {
				String hashedPassword = rset.getString("Password");
				boolean matched = PasswordHasher.verifyPassword(password, hashedPassword);

				if (matched) {

//					session.setAttribute("name", rset.getString("UserName"));
//					session.setAttribute("Email", rset.getString("Email"));

//					rd = req.getRequestDispatcher("index.jsp");
//					rd.forward(req, res);
					
					int id = rset.getInt("UserID");
					String uname = rset.getString("UserName");
					String email = rset.getString("Email");
					String usertype = rset.getString("UserType");

					User user = new User(id, uname, email, usertype);

					session.setAttribute("user", user);

					res.sendRedirect("/CRS/index.jsp");

				} else {
					req.setAttribute("status", "password_error");

					rd = req.getRequestDispatcher("login.jsp");
					rd.forward(req, res);
				}
			}else {
				req.setAttribute("status", "failed");

				rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req, res);
			}
			
		}catch(Exception e) {
			System.out.println("Error Connecting");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
