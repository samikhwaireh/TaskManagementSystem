package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Task;
import model.User;

/*
* @author Sami Khwaireh
*/
public class TaskDaoImplement extends UserDaoImplement implements TaskDao {

	/*
	 * 
	 * Initialize database connection
	 */
	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

	public TaskDaoImplement() {
	}

	public List<Task> getTasks(int userId) {
		
		List<Task> taskList = new ArrayList<>();

		String sql = "SELECT * FROM tasks WHERE forUserId = " + userId;

		try {

			//set dB connection
			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int taskId = resultSet.getInt("taskId");
				String taskDate = resultSet.getString("taskDate");
				String expectedTime = resultSet.getString("expectedTime");
				int taskCreaterId = resultSet.getInt("taskCreater_ID");
				String taskDescription = resultSet.getString("taskDescription");
				int forUserId = resultSet.getInt("forUserId");
				String isApproved = resultSet.getString("isApproved");
				int taskState = resultSet.getInt("taskState");

				// you can abbreviate this section
				  String isApprove = resultSet.getString("isApproved"); if
				  (isApprove.equals("1")) { isApproved = "true"; } else { isApproved = "false"; }
				 

				// get user names and info's
				  User userCreater = getUser(taskCreaterId); User taskedUser =
				  getUser(forUserId);
				  
				// assign them to Strings
				  String craeterName = userCreater.getUsername(); String assignedFor =
				  taskedUser.getUsername();
				 
				  	 // hold info's in a list 
				taskList.add(new Task(taskId, taskDate, expectedTime, taskCreaterId, craeterName, taskDescription, forUserId,
						assignedFor, isApproved, taskState));	

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}
	
	/*
	 * add new task adds a task for one user
	 */
	@Override
	public void addTask(Task task) throws SQLException{
		
		String sql = "INSERT INTO tasks (taskDate, expectedTime, taskCreater_ID, taskDescription, forUserId, isApproved, taskState) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			
			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, task.getTaskDate());
			preparedStatement.setString(2, task.getExpectedTime());
			preparedStatement.setInt(3, task.getTaskCreaterId());
			preparedStatement.setString(4, task.getTaskDescription());
			preparedStatement.setInt(5, task.getForUserId());
			preparedStatement.setString(6, task.getIsApproved());
			preparedStatement.setInt(7, task.getTaskState());
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// get a user using id
		@Override
		public Task getTask(int taskId) {
			Task task = null;
			String sql = "SELECT * from tasks WHERE taskId = ?";
			try {
				PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);	
				preparedStatement.setInt(1, taskId);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					
					String taskDate = resultSet.getString("taskDate");
					String expectedTime = resultSet.getString("expectedTime");
					int taskCreaterId = resultSet.getInt("taskCreater_ID");
					String taskDescription = resultSet.getString("taskDescription");
					int forUserId = resultSet.getInt("forUserId");
					String isApproved = resultSet.getString("isApproved");
					int taskState = resultSet.getInt("taskState");
					
					// you can abbreviate this section
					String isApprove = resultSet.getString("isApproved");
					if (isApprove.equals("1")) {
						isApproved = "true";
					} else {
						isApproved = "false";
					}

					// get user names and info's
					User userCreater = getUser(taskCreaterId);
					User taskedUser = getUser(forUserId);

					// assign them to Strings
					String craeterName = userCreater.getUsername();
					String assignedFor = taskedUser.getUsername();
					
					task = new Task(taskId, taskDate, expectedTime, taskCreaterId, craeterName, taskDescription, forUserId,
							assignedFor, isApproved, taskState);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return task;
		}
		
		public List<Task> getUnAprovedTasks() {
			
			List<Task> taskList = new ArrayList<>();

			String sql = "SELECT * FROM tasks WHERE isApproved = 0";

			try {

				//set dB connection
				PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					int taskId = resultSet.getInt("taskId");
					String taskDate = resultSet.getString("taskDate");
					String expectedTime = resultSet.getString("expectedTime");
					int taskCreaterId = resultSet.getInt("taskCreater_ID");
					String taskDescription = resultSet.getString("taskDescription");
					int forUserId = resultSet.getInt("forUserId");
					String isApproved = resultSet.getString("isApproved");
					int taskState = resultSet.getInt("taskState");

					// you can abbreviate this section
					String isApprove = resultSet.getString("isApproved");
					if (isApprove.equals("1")) {
						isApproved = "true";
					} else {
						isApproved = "false";
					}
					 
					// get user names and info's
					User userCreater = getUser(taskCreaterId);
					User taskedUser = getUser(21);

					// assign them to Strings
					String craeterName = userCreater.getUsername();
					String assignedFor = taskedUser.getUsername();

					// hold info's in a list 
					taskList.add(new Task(taskId, taskDate, expectedTime, taskCreaterId, 
										craeterName, taskDescription, forUserId,
										assignedFor, isApproved, taskState));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return taskList;
		}
		
		public boolean updateTask(Task task) throws SQLException {
			
			String sql = "UPDATE tasks SET expectedTime = ?, taskDescription = ? WHERE taskId = ?";
			boolean isUpdated = false;
			
			try {
				
				PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
				preparedStatement.setString(1, task.getExpectedTime());
				preparedStatement.setString(2, task.getTaskDescription());
				preparedStatement.setInt(3, task.getTaskId());
				
				isUpdated = preparedStatement.executeUpdate() > 0;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return isUpdated;	
		}
		
		public boolean approveTask(Task task) throws SQLException {
			
			String sql = "UPDATE tasks SET taskCreater_ID = ?, isApproved = 1 WHERE taskId = ?";
			boolean isUpdated = false;
			
			try {
				
				PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
				preparedStatement.setInt(1, task.getTaskCreaterId());
				preparedStatement.setInt(2, task.getTaskId());
				
				isUpdated = preparedStatement.executeUpdate() > 0;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return isUpdated;	
		}
		
		public boolean removeTask(int taskId) throws SQLException {

		    String sql = "DELETE FROM tasks WHERE taskId = ?";
		    boolean isRemoved = false;

		    try {

		        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
		        preparedStatement.setInt(1, taskId);

//		        if query executed correctly return 1 then make BOOLEAN true
		        isRemoved = preparedStatement.executeUpdate() > 0; 

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return isRemoved;
		}
		
		public boolean completeTask(int taskId) throws SQLException {
			
			String sql = "UPDATE tasks SET taskState = 1 WHERE taskId = ?";
			boolean isUpdated = false;
			
			try {
				
				PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
				preparedStatement.setInt(1, taskId);
				
				isUpdated = preparedStatement.executeUpdate() > 0;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return isUpdated;	
		}
}
