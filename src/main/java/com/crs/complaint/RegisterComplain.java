package com.crs.complaint;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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
 * Servlet implementation class RegisterComplain
 */
@WebServlet("/complaints/register")
public class RegisterComplain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterComplain() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;

		if (session.getAttribute("user") != null) {
			rd = req.getRequestDispatcher("register.jsp");
			rd.forward(req, res);

		} else {
			rd = req.getRequestDispatcher("../login.jsp");
			res.sendRedirect("/CRS/login.jsp?status=access_denied");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		Connection conn = null;
		PrintWriter out = res.getWriter();

		if (session.getAttribute("user") != null) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_reg", "root", "123456");
				User user = (User) session.getAttribute("user");

				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UserTable WHERE UserID = ?");
				stmt.setInt(1, user.getId());
				ResultSet rs = stmt.executeQuery();

				if(!rs.next()) {
					throw new Exception("Illegal id found!!");
				} else {

					int userID = rs.getInt("UserID");
					String title = req.getParameter("complaintTitle");
					String text = req.getParameter("complaintText");
					Date complaintDate = Date.valueOf(req.getParameter("complaintDate"));
					String status = req.getParameter("status");

					System.out.println(userID + "__" + title + "__" + text + "__" + complaintDate + "__" + status);

					PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO complaint (UserID, ComplaintTitle, ComplaintText, ComplaintDate, Status) VALUES(?,?,?,?,?)");
					stmt2.setInt(1, userID);
					stmt2.setString(2, title);
					stmt2.setString(3, text);
					stmt2.setDate(4, complaintDate);
					stmt2.setString(5, status);

					int rowCount = stmt2.executeUpdate();

					if (rowCount == 1) {
						req.setAttribute("status", "success");
						res.sendRedirect("/CRS/my-profile");
					} else {
						out.println("Error");
					}
				}

			} catch (Exception e) {
				System.out.println("Error");
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			rd = req.getRequestDispatcher("../login.jsp");
			res.sendRedirect("/CRS/login.jsp?status=access_denied");
		}
	}

}
