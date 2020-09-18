package database;

import java.sql.*;

/*
*
* @author Sami Khwaireh
*/
public final class DatabaseConnection {

	private String urlDB = "jdbc:mysql://localhost:3306/taskmanagementsystem";
	private String userDB = "root";
	private String passwordDB = "123456";

	private static Connection connection = null;
	private static DatabaseConnection instance;

	private DatabaseConnection() {
		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}

			connection = (Connection) DriverManager.getConnection(urlDB, userDB, passwordDB);
  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized DatabaseConnection getInstance() {

		if (instance == null) {
			instance = new DatabaseConnection();
		}

		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
