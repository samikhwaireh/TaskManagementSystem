package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Team;
import model.User;

/*
*
* @author Sami Khwaireh
*/
public class TeamDaoImplement extends UserDaoImplement implements TeamDao {

	/*
	 * 
	 * Initialize database connection
	 */
	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

	public TeamDaoImplement() {
	}

	@Override
	public List<User> getTeamDevelopers(int teamId) {

		List<User> userList = new ArrayList<>();

		String sql = "SELECT u.userId, u.firstName, u.lastName, u.username, u.email, u.gender, u.role, t.teamName "
				+ "FROM users u " + "INNER JOIN teams t " + "ON u.teamId = t.teamId " + "WHERE u.teamId = " + teamId
				+ " AND u.role = 'Developer'";

		userHolder(userList, sql);
		return userList;
	}

	@Override
	public User getTeamLeader(int teamId) {

		User user = null;

		String sql = "SELECT * FROM users WHERE teamId = " + teamId + " AND role = 'TeamLeader'";
		try {
			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String username = resultSet.getString("username");
				String email = resultSet.getString("email");
				String gender = resultSet.getString("gender");
				String role = resultSet.getString("role");

				user = new User(userId, firstName, lastName, username, email, gender, role, teamId);
			}
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

	public List<Team> getTeams() {

		List<Team> teamsList = new ArrayList<>();
		String sql = "SELECT * FROM teams";

		try {

			PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int teamId = resultSet.getInt("teamId");
				String teamName = resultSet.getString("teamName");

				User user = getTeamLeader(teamId);
				String teamLeaders = user.getFirstName() + " " + user.getLastName();
				if (user.equals(null)) {
					teamLeaders = "No TeamLeader";
				}

				teamsList.add(new Team(teamId, teamName, teamLeaders));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return teamsList;
	}

}
