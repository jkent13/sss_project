package sss.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	private static Connection connection;
	
	protected static Connection getConnection() {
		return connection;
	}
	
	protected static boolean establishConnection() {
	    // Load JDBC Driver
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}
	    
	    // Connect to existing DB
	    try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sss_project" , "root" , "abc123");
			DbConnector.connection = connection;
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}
	}
	
	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}
