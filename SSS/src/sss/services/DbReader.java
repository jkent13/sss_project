/* DbReader Class
*  Responsible for executing queries on the database and returning the results to calling classes
*  Original Author: Josh Kent
*/

package sss.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbReader {
	
	private static Connection connection = DbConnector.getConnection();
	private static Statement statement;
	
	private DbReader() {
	}
	
	public static ResultSet executeQuery(String sqlQuery) throws SQLException {
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
