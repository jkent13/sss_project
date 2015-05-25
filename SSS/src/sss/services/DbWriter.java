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

public class DbWriter {
	private static Connection connection = DbConnector.getConnection();
	private static Statement statement;
	
	private DbWriter() {
		
	}
	
	public static void executeStatement(String sql) throws SQLException {
		statement = connection.createStatement();
		statement.execute(sql);
		statement.close();
	}
}
