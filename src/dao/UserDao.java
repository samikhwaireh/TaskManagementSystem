package dao;

import java.sql.SQLException;
import java.util.List;

import model.User;

/*
*
* @author Sami Khwaireh
*/
public interface UserDao {
	
	List<User> getAllUsers(String search);
			
	User getUser(int userId);
	
	void addNewUser(User user) throws SQLException;
	
	boolean updateUser(User user) throws SQLException;
	
	boolean removeUser(int userId) throws SQLException;

}
