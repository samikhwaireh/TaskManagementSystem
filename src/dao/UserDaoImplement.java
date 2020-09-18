package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.User;

/*
*
* @author Sami Khwaireh
*/
public class UserDaoImplement implements UserDao {

	public UserDaoImplement() {

	}

	/*
	 * 
	 * Initialize database connection
	 */
	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

	/*
	 * 
	 * Insert new recored in users table
	 */
	@Override
	public void addNewUser(User user) throws SQLException {

		String sql = "INSERT INTO users (firstName, lastName, username, email, gender, role, teamId) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getGender());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setInt(7, user.getTeamId());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * Get a user using id
	 */
	@Override
	public User getUser(int userId) {
		User user = null;
		String sql = "SELECT * FROM users WHERE userId = ?";
		try {
			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String username = resultSet.getString("username");
				String email = resultSet.getString("email");
				String gender = resultSet.getString("gender");
				String role = resultSet.getString("role");
				int teamId = resultSet.getInt("teamId");
				user = new User(userId, firstName, lastName, username, email, gender, role, teamId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * 
	 * Hold a user data from dB users table
	 */
	public void userHolder(List<User> userList, String sql) {
		try {

			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int userId = resultSet.getInt("u.userId");
				String firstName = resultSet.getString("u.firstName");
				String lastName = resultSet.getString("u.lastName");
				String username = resultSet.getString("u.username");
				String email = resultSet.getString("u.email");
				String gender = resultSet.getString("u.gender");
				String role = resultSet.getString("u.role");
				String teamName = resultSet.getString("t.teamName");
				userList.add(new User(userId, firstName, lastName, username, email, gender, role, teamName));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * Display all users without their passwords
	 */
	@Override
	public List<User> getAllUsers(String search) {

		List<User> userList = new ArrayList<>();

		assert search != null;
		String sql = "SELECT u.userId, u.firstName, u.lastName, u.username, u.email, u.gender, u.role, t.teamName "
				+ "FROM users u " + "INNER JOIN teams t " + "ON u.teamId = t.teamId " + "WHERE u.firstName LIKE '"
				+ search + "%' OR u.lastName LIKE '" + search + "'";

		userHolder(userList, sql);
		return userList;
	}

	public boolean updateUser(User user) throws SQLException {

		String sql = "UPDATE users SET firstName = ?, lastName = ?, username = ?, email = ?, gender = ?, role = ?, teamId = ? WHERE userId= ?";
		boolean isUpdated = false;

		try {

			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getGender());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setInt(7, user.getTeamId());
			preparedStatement.setInt(8, user.getUserId());

			isUpdated = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	// for security
	// to check user role
	// prevent user to remove Manager-role users
	private boolean isManager(int userId) {
		String sql = "SELECT role FROM users WHERE userId = ?";
		boolean isMANAGER = false;
		try {
			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("role").equals("Manager")) {
					isMANAGER = true;
				} else {
					isMANAGER = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isMANAGER;
	}

	private boolean isTeamLeader(int userId) {
		String sql = "SELECT role FROM users WHERE userId = ?";
		boolean isTeamLeader = false;
		try {
			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("role").equals("TeamLeader")) {
					isTeamLeader = true;
				} else {
					isTeamLeader = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTeamLeader;
	}

	public boolean removeUser(int userId) throws SQLException {
		if (!isManager(userId) || !isTeamLeader(userId)) {

			String sql = "DELETE FROM users WHERE userId = ?";
			boolean isRemoved = false;

			try {

				PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
				preparedStatement.setInt(1, userId);

				isRemoved = preparedStatement.executeUpdate() > 0;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return isRemoved;
		} else {
			return false;
		}
	}
}
