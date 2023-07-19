package com.crs;

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
 * Servlet implementation class MyProfile
 */
@WebServlet("/my-profile")
public class MyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		Connection conn = null;
		PrintWriter out = res.getWriter();

		if (session.getAttribute("user") != null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint_reg", "root", "123456");
				User user = (User) session.getAttribute("user");
				
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Complaint WHERE UserID = ?");
				stmt.setInt(1, user.getId());
				ResultSet rs = stmt.executeQuery();

				ArrayList<Complaint> complaintData = new ArrayList<>();

				while (rs.next()) {
					int id = rs.getInt("ComplaintID");
					String title = rs.getString("ComplaintTitle");
					String text = rs.getString("ComplaintText");
					Date date = (rs.getDate("ComplaintDate"));
					String status = rs.getString("Status");
					int userId = rs.getInt("UserID");
					
					Complaint userComp = new Complaint(id, title, text, userId, status, date);

					complaintData.add(userComp);

				}

				// getting the info and then forwarding to the user
				req.setAttribute("user", user);
				req.setAttribute("complaintData", complaintData);
				rd = req.getRequestDispatcher("/my-profile/index.jsp");
				rd.forward(req, res);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			res.sendRedirect("/CRS/login.jsp?status=access_denied");
		}
	}


}
