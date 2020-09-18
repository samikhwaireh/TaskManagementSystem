package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TeamDao;
import dao.TeamDaoImplement;
import model.Team;
import model.User;

@WebServlet({ "/Teams", "/display_developers" })
public class TeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamDao teamDao;

	@Override
	public void init() throws ServletException {
		teamDao = new TeamDaoImplement();
		super.init();
	}

	public TeamController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servlet = request.getServletPath();

		try {
			switch (servlet) {
			case "/Teams":
				toTeams(request, response);
				break;
			case "/display_developers":
				displayTeamDevelopers(request, response);
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

	private void toTeams(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Resources/Header.jsp");
			dispatcher.forward(request, response);
		} else {
			List<Team> teamList = teamDao.getTeams();
			request.setAttribute("teamList", teamList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Teams/Teams.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void displayTeamDevelopers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int teamId;
		List<User> userList;

		if (!request.getParameter("team_id").equals(null)) {
			teamId = Integer.parseInt(request.getParameter("team_id"));
		} else {
			HttpSession session = request.getSession();
			teamId = (Integer) session.getAttribute("teamId");
		}

		userList = teamDao.getTeamDevelopers(teamId);
		request.setAttribute("userList", userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Users/TeamLeaderDashboard.jsp");
		dispatcher.forward(request, response);

	}
}
