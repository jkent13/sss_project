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
	
	private static Connection connection = DbConnector.getConnection();
	private static Statement statement;
	
	private DbReader() {
	}
	
	public static ResultSet executeQuery(String sqlQuery) {
		try {
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
	
	public static void closeConnection() throws SQLException {
		if(statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
