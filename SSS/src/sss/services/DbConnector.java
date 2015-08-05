/* DbConnector Class
* 
*  Responsible for establishing the database connection and controlling access to the Connection object
*  Opens and closes the connection to the MySQL DB using JDBC
*  
*  Original Author: Josh Kent
*/

package sss.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConnector {
	
	private static Connection connection;			// This is the sole connection to the database
	
	/**
	 * Getter method for obtaining a reference to the database connection. Only visible to classes inside the service package.
	 * @return a reference to the database Connection object
	 */
	protected static Connection getConnection() {
		if(connection == null) {
			establishConnection();
			return connection;
		}
		else {
			return connection;
		}
	}
	
	/**
	 * Establishes the connection to the MySQL database 
	 * @return true if the connection was successful, false otherwise
	 */
	protected static boolean establishConnection() {
		
	    // Load JDBC Driver
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: JDBC Driver not found", "Missing DB driver", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
			
		}
	    
	    // Connect to existing DB
	    try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sss_project" , "root" , "abc123");
			DbConnector.connection = connection;
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: Failed to connect to database on localhost", "DB connection failure", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Closes the connection to the database
	 * @throws SQLException if there is a problem with closing the connection
	 */
	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}// End class