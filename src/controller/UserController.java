package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoImplement;
import model.User;

@WebServlet({ "/Add_User", "/display_users", "/new_user_form", "/submit_new_user", "/remove_user", "/update_user",
		"/update_user_form", "/search_user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImplement();
		super.init();
	}

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servlet = request.getServletPath();
		try {

			switch (servlet) {
			case "/Add_User":
				addUser(request, response);
				break;
			case "/display_users":
				displayUser(request, response);
				break;
			case "/new_user_form":
				toNewUserForm(request, response);
				break;
			case "/submit_new_user":
				addUser(request, response);
				break;
			case "/update_user_form":
				toUpadteUserForm(request, response);
				break;
			case "/update_user":
				updateUser(request, response);
				break;
			case "/remove_user":
				removeUser(request, response);
				break;
			case "/search_user":
				searchUser(request, response);
				break;

			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/*
	 * // check if the current user is manager private boolean
	 * isManager(HttpServletRequest request) { HttpSession session =
	 * request.getSession(); if (session.getAttribute("email") == null ||
	 * !session.getAttribute("role").equals("Manager")) { return false; } else {
	 * return true; } }
	 */

	private void displayUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<User> userList = userDao.getAllUsers("");
		request.setAttribute("userList", userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Users/ManagerDashboard.jsp");
		dispatcher.forward(request, response);

	}

	private void toNewUserForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null || !session.getAttribute("role").equals("Manager")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/Resources/Header.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Users/UserForm.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void toUpadteUserForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		User user = userDao.getUser(userId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Users/UserForm.jsp");
		request.setAttribute("user", user);
		dispatcher.forward(request, response);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null || !session.getAttribute("role").equals("Manager")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Resources/Header.jsp");
			dispatcher.forward(request, response);
		} else {

			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String userName = request.getParameter("user_name");
			String email = request.getParameter("email");
			String gender = String.valueOf(request.getParameter("gender"));
			String role = String.valueOf(request.getParameter("role"));
			int teamId = Integer.parseInt(request.getParameter("team"));

			User user = new User(firstName, lastName, userName, email, gender, role, teamId);

			userDao.addNewUser(user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("display_users");
			dispatcher.forward(request, response);
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int userId = Integer.parseInt(request.getParameter("userID"));
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String userName = request.getParameter("user_name");
		String email = request.getParameter("email");
		String gender = String.valueOf(request.getParameter("gender"));
		String role = String.valueOf(request.getParameter("role"));
		int teamId = Integer.parseInt(request.getParameter("team"));
		User user = new User(userId, firstName, lastName, userName, email, gender, role, teamId);

		userDao.updateUser(user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("display_users");
		dispatcher.forward(request, response);
	}

	private void removeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null || !session.getAttribute("role").equals("Manager")) {
			if (session.getAttribute("role").equals("TeamLeader")) {
				int userId = Integer.parseInt(request.getParameter("user_id"));
				userDao.removeUser(userId);
				RequestDispatcher dispatcher = request.getRequestDispatcher("display_developers");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Resources/Header.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			int userId = Integer.parseInt(request.getParameter("user_id"));
			userDao.removeUser(userId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("display_users");
			dispatcher.forward(request, response);
		}
	}

	private void searchUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String search = request.getParameter("search");
		List<User> userList = userDao.getAllUsers(search);
		request.setAttribute("userList", userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Users/ManagerDashboard.jsp");
		dispatcher.forward(request, response);

	}
}
