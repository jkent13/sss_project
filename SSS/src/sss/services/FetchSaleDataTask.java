/* FetchSaleDataTask Class
 * 
 * A task designed to run on a seperate thread and poll the database 
 * every minute for new sale data
 * 
 * Original Author: Josh Kent
 */
package sss.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartPanel;

import sss.domain.DashboardController;
import sss.domain.NonEditableTableModel;

public class FetchSaleDataTask implements Runnable {

	private Connection connection = DbConnector.getConnection();
	private SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");	// Date format used to convert input into MySQL DateTime
	private Date currentDate = new Date();
	private String query;
	private Statement statement;
	private NonEditableTableModel dollarSalesData = new NonEditableTableModel();	// Containing grouped-on-hour sale info
	private String[] dollarSalesColNames = {"Hours", "Number of Transactions", "Sale Total"};
	private ResultSet results;
	private ChartPanel chartPanel;
	private DashboardController controller;
	
	public FetchSaleDataTask(ChartPanel chartPanel, DashboardController dc) throws SQLException {
		this.chartPanel = chartPanel;
		controller = dc;
		query = SqlBuilder.getSaleReportByHourQuery(sqlDateFormat.format(currentDate));
		statement = connection.createStatement();
		dollarSalesData.setColumnIdentifiers(dollarSalesColNames);
	}
	
	@Override
	public void run() {
		try {
			for(int i = dollarSalesData.getRowCount()-1; i != -1; i--) {
				dollarSalesData.removeRow(i);
			}
			
			results = statement.executeQuery(query);
			
			while(results.next()) {
				dollarSalesData.addRow(new Object[] 
						{results.getString(1),
						results.getInt(2), 
						new BigDecimal(results.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}
			results.close();
			results = null;
			chartPanel = ChartBuilder.createSingleDaySalePanel(dollarSalesData);
			controller.updateChart(chartPanel);
			System.out.println(new Date());
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}