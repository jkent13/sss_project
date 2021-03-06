/* FetchQuantityChangesTask Class
 * 
 * A runnable background task that will constantly check for quantity changes for 
 * all products on the Dashboard watch list, so that they can be updated
 * 
 * Original Author: Josh Kent
 */

package sss.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sss.domain.DashboardController;
import sss.ui.WatchedProduct;

public class FetchQuantityChangesTask implements Runnable {

	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private DashboardController controller;
	
	private Connection connection = DbConnector.getConnection();
	private Statement statement;
	private ResultSet results;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public FetchQuantityChangesTask(DashboardController dc) throws SQLException {
		this.controller = dc;
		statement = connection.createStatement();
	}
	
	
	
	// ==========================================================================
	// Runnable Interface Method
	// ==========================================================================
	
	
	
	@Override
	public void run() {
		WatchedProduct one = controller.getWatchedProductOne();
		WatchedProduct two = controller.getWatchedProductTwo();
		WatchedProduct three = controller.getWatchedProductThree();
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
			controller.refreshWatchedProducts();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
