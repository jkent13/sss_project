/* DbWriter Class
 * 
*  Responsible for executing 'write' statements on the database such as INSERT and UPDATE 
*  
*  Original Author: Josh Kent
*/

package sss.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbWriter {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private static Connection connection = DbConnector.getConnection();		// Connection to DB
	private static Statement statement;																		// Statement for executing INSERT/UPDATEs
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	private DbWriter() {
	}
	
	
	
	// ==========================================================================
	// Static Methods
	// ==========================================================================
	
	
	
	/**
	 * Static method for executing any kind of SQL INSERT or UPDATE statements
	 * @param sql the SQL string to execute
	 */
	public static boolean executeStatement(String sql) {
		try {
			if(connection == null) {
				connection = DbConnector.getConnection();
			}
			
			if(statement == null) {
				statement = connection.createStatement();
			}
			statement.execute(sql);
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: The SQL statements could not be executed", "SQL Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
}