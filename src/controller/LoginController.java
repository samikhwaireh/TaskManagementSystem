package controller;

import java.io.IOException;
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

import database.DatabaseConnection;

@WebServlet("/Home")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		DatabaseConnection connection = DatabaseConnection.getInstance();

		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		String sql = "SELECT * FROM users WHERE email = ? and password = ?";

		try {

			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				String role = result.getString("role");

				session.setAttribute("userId", result.getInt("userId"));
				session.setAttribute("firstName", result.getString("firstName"));
				session.setAttribute("lastName", result.getString("lastName"));
				session.setAttribute("role", role);
				session.setAttribute("email", email);
				session.setAttribute("teamId", result.getInt("teamId"));
				session.setAttribute("lock", "false");

				if (role.equals("Manager")) {
					// request.getRequestDispatcher("/JSP/Users/ManagerDashboard.jsp").forward(request,
					// response);
					RequestDispatcher dispatcher = request.getRequestDispatcher("display_users");
					dispatcher.forward(request, response);
					// response.sendRedirect(request.getContextPath()+"/JSP/Users/ManagerDashboard.jsp");
					return;
				} else if (role.equals("TeamLeader")) {
					request.getRequestDispatcher("display_developers?team_id=" + result.getInt("teamId"))
							.forward(request, response);
					return;
				} else if (role.equals("Developer")) {
					request.getRequestDispatcher("display_tasks?user_id=" + result.getInt("userId")).forward(request,
							response);
					return;
				}
			}
			response.sendRedirect("login.jsp");
			return;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
