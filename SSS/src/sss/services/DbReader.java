package sss.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DbReader {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement productQuery;
	
	public DbReader() throws SQLException {
		if (DbConnector.establishConnection()) {
			connection = DbConnector.getConnection();
			statement = connection.createStatement();
		}
	}
	
	private boolean createProductQuery() {
		try {
			productQuery = connection.prepareStatement("SELECT * FROM product WHERE prod_id = ?;");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	} 
	
	public ResultSet lookUpProduct(long prod_id) throws SQLException {
		if (productQuery == null){
			createProductQuery();
		}
		
		productQuery.setLong(1, prod_id);
		ResultSet productDetails = productQuery.executeQuery();
		return productDetails;
	}
	
	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
			DbConnector.closeConnection();
		}
	}
}
