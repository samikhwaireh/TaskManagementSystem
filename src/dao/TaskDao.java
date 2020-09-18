package dao;

import java.sql.SQLException;
import java.util.List;

import model.Task;

/*
* @author Sami Khwaireh
*/
public interface TaskDao {
	
	List<Task> getTasks(int userId);
	void addTask(Task task) throws SQLException;
	boolean updateTask(Task task) throws SQLException;
	Task getTask(int taskId);
	boolean approveTask(Task task) throws SQLException;
	List<Task> getUnAprovedTasks();
	boolean completeTask(int taskId) throws SQLException;
	boolean removeTask(int taskId) throws SQLException; 
}
