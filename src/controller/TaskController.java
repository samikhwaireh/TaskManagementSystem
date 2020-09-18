package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TaskDao;
import dao.TaskDaoImplement;
import dao.UserDao;
import dao.UserDaoImplement;
import model.Task;
import model.User;

@WebServlet({ "/display_tasks", "/task_form", "/add_task", "/update_task_form", "/update_task", "/approve_task",
		"/unApproved_task", "/remove_task", "/make_it_done" })
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskDao;
	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		taskDao = new TaskDaoImplement();
		userDao = new UserDaoImplement();
		super.init();
	}

	public TaskController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servlet = request.getServletPath();

		try {
			switch (servlet) {
			case "/display_tasks":
				displayTasks(request, response);
				break;
			case "/task_form":
				toNewTaskForm(request, response);
				break;
			case "/add_task":
				addTask(request, response);
				break;
			case "/update_task_form":
				toUpadteTask(request, response);
				break;
			case "/update_task":
				updateTasks(request, response);
				break;
			case "/approve_task":
				approveTask(request, response);
				break;
			case "/unApproved_task":
				getUnApprovedTasks(request, response);
				break;
			case "/remove_task":
				removeTask(request, response);
				break;
			case "/make_it_done":
				completeTask(request, response);
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

	private void toNewTaskForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		User user = userDao.getUser(userId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/Tasks/TaskForm.jsp");
		request.setAttribute("user", user);
		dispatcher.forward(request, response);
	}

	private void toUpadteTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		Task task = taskDao.getTask(taskId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/Tasks/TaskForm.jsp");
		request.setAttribute("task", task);
		dispatcher.forward(request, response);
	}

	private void displayTasks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Task> taskList;

		int istenenUserId = Integer.parseInt(request.getParameter("user_id"));
		int currentUserId = (Integer) session.getAttribute("userId");

		if (session.getAttribute("role").equals("Developer")) {
			if (istenenUserId == currentUserId) {
				taskList = taskDao.getTasks(currentUserId);
				request.setAttribute("taskList", taskList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Tasks/Tasks.jsp");
				dispatcher.forward(request, response);
			} else {
				// access denied ...
			}
		} else {
			taskList = taskDao.getTasks(istenenUserId);
			request.setAttribute("taskList", taskList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Tasks/Tasks.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void addTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		HttpSession session = request.getSession();
		String taskDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		int taskCreaterId = (Integer) session.getAttribute("userId");

		int assignedFor = Integer.parseInt(request.getParameter("userID"));

		String taskDescription = request.getParameter("task_description");

		String expectedTime = request.getParameter("expected_time");

		String isApproved = null;
		if (session.getAttribute("role").equals("Developer")) {
			isApproved = "0";
		} else {
			isApproved = "1";
		}
		// int taskState = Integer.parseInt(request.getParameter("task_state"));

		Task task = new Task(taskDate, expectedTime, taskCreaterId, " ", taskDescription, assignedFor, " ", isApproved,
				0);

		taskDao.addTask(task);
		RequestDispatcher dispatcher = request.getRequestDispatcher("display_tasks?user_id=" + assignedFor);
		dispatcher.forward(request, response);
	}

	private void updateTasks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {

		HttpSession session = request.getSession();
		int taskId = Integer.parseInt(request.getParameter("taskID"));
		int userId = Integer.parseInt(request.getParameter("userID"));

		String taskDescription = request.getParameter("task_description");
		String expectedTime = request.getParameter("expected_time");
//		String isApproved = String.valueOf(request.getParameter("is_approved"));
		int taskState;

		if (!session.getAttribute("role").equals("Developer")) {
			taskState = 0;
		} else {
			taskState = Integer.parseInt(request.getParameter("task_state"));
		}

		Task task = new Task(taskId, " ", expectedTime, 0, " ", taskDescription, 0, " ", " ", taskState);

		taskDao.updateTask(task);

		RequestDispatcher dispatcher = request.getRequestDispatcher("display_tasks?user_id=" + userId);
		dispatcher.forward(request, response);
	}

	private void approveTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String role = (String) session.getAttribute("role");
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		int userId = (Integer) session.getAttribute("userId");

		if (!email.equals(null)) {
			if (role.equals("Manager") || role.equals("TeamLeader")) {
				Task task = new Task(taskId, " ", "", userId, " ", "", 0, " ", "", 0);
				taskDao.approveTask(task);

				RequestDispatcher dispatcher = request.getRequestDispatcher("unApproved_task");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Resources/Header.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Resources/Header.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void getUnApprovedTasks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Task> taskList;

		taskList = taskDao.getUnAprovedTasks();
		request.setAttribute("taskList", taskList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Tasks/Tasks.jsp");
		dispatcher.forward(request, response);
	}

	private void removeTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(request.getParameter("user_id"));
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		RequestDispatcher dispatcher;

		if (session.getAttribute("email") == null || !session.getAttribute("role").equals("Manager")) {
			if (session.getAttribute("role").equals("TeamLeader")) {
				taskDao.removeTask(taskId);
				dispatcher = request.getRequestDispatcher("display_tasks?user_id=" + userId);
				dispatcher.forward(request, response);
			} else {
				dispatcher = request.getRequestDispatcher("JSP/Resources/Header.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			taskDao.removeTask(taskId);
			dispatcher = request.getRequestDispatcher("display_tasks?user_id=" + userId);
			dispatcher.forward(request, response);
		}
	}

	private void completeTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {

		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userId");
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		taskDao.completeTask(taskId);

		RequestDispatcher dispatcher = request.getRequestDispatcher("display_tasks?user_id=" + userId);
		dispatcher.forward(request, response);
	}

}
