package sss.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sss.domain.WatchedProduct;
import sss.ui.DashboardFrame;

public class FetchQuantityChangesTask implements Runnable {

	private DashboardFrame parent;
	
	private Connection connection = DbConnector.getConnection();
	private Statement statement;
	private ResultSet results;
	
	
	public FetchQuantityChangesTask(DashboardFrame parent) throws SQLException {
		this.parent = parent;
		statement = connection.createStatement();
	}
	
	@Override
	public void run() {
		WatchedProduct one = parent.getWatchedProductOne();
		WatchedProduct two = parent.getWatchedProductTwo();
		WatchedProduct three = parent.getWatchedProductThree();
		try {
			if(one != null) {
				String query = SqlBuilder.getProductQuantityQuery(one.getProductCode());
				results = statement.executeQuery(query);
				if(results.next()) {
					int quantity = results.getInt("prod_qoh");
					one.setCurrentQuantity(quantity);
				}
			}
			if(two != null) {
				String query = SqlBuilder.getProductQuantityQuery(two.getProductCode());
				results = statement.executeQuery(query);
				if(results.next()) {
					int quantity = results.getInt("prod_qoh");
					two.setCurrentQuantity(quantity);
				}
			}
			if(three != null) {
				String query = SqlBuilder.getProductQuantityQuery(three.getProductCode());
				results = statement.executeQuery(query);
				if(results.next()) {
					int quantity = results.getInt("prod_qoh");
					three.setCurrentQuantity(quantity);
				}
			}
			
			parent.refreshWatchedProducts();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
