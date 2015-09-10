/* DbReader Class
 * 
*  Responsible for executing queries on the database and returning the results to calling objects
*  
*  Original Author: Josh Kent
*/

package sss.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbReader {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private static Connection connection = DbConnector.getConnection();		// Connection to DB
	private static Statement statement;																		// Statement for executing queries
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	private DbReader() {
	}
	
	
	
	// ==========================================================================
	// Static Methods
	// ==========================================================================
	
	
	
	/**
	 * Static method for executing any kind of SQL SELECT statement on the database
	 * @param sqlQuery the SQL query to be executed (must be SELECT statement)
	 * @return a ResultSet containing the results of the query. Contains a single row of null values if no results are found.
	 */
	public static ResultSet executeQuery(String sqlQuery) {
		try {
			if(connection == null) {
				connection = DbConnector.getConnection();
			}
			
			if(statement == null) {
				statement = connection.createStatement();
			}
			
			if(sqlQuery != null){
				ResultSet productDetails = statement.executeQuery(sqlQuery);
				return productDetails;
			}
			else {
				return null;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: The query could not be executed", "SQL Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	
	
	/**
	 * Static method for shutting down the DbReader by safely closing its Statement and the database connection
	 * @throws SQLException if there is an error in closing the statement or connection
	 */
	public static void closeConnection() throws SQLException {
		if(statement != null) {
			statement.close();
		}
		if (connection != null) {
			DbConnector.closeConnection();
		}
	}
	
}