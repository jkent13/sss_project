/* TopSellersReportController Class
 * 
 * Controls application logic for Generate Top Seller Report UC
 * 
 * Original Author: Josh Kent 
 */
package sss.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import sss.services.ChartBuilder;
import sss.services.DbReader;
import sss.services.SqlBuilder;

public class TopSellersReportController extends ReportController {

	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private String[] tableColumns = {"Ranking", "Units Sold", "Product Name", "Product ID"};
	private String[] dateRangeOfCurrentReport = {"No Date", "No Date"};
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public TopSellersReportController() {
		initialise();
	}
	
	
	
	// ==========================================================================
	// Core Methods
	// ==========================================================================
	
	
	
	@Override
	protected void initialise() {
		currentTableView.setColumnIdentifiers(tableColumns);
	}
	
	public void getResults(TopSellerFilter filter) {
		try {
			Date inputStartDate = dateFormat.parse(filter.getStartDate());
			Date inputEndDate = dateFormat.parse(filter.getEndDate());
			
			filter.setStartDate(sqlDateFormat.format(inputStartDate)); 		// Convert to MySQL date string
			filter.setEndDate(sqlDateFormat.format(inputEndDate)); 				// Convert to MySQL date string
			
			dateRangeOfCurrentReport[0] = dateFormat.format(inputStartDate);
			dateRangeOfCurrentReport[1] = dateFormat.format(inputEndDate);
			
			// CLEAR DATA MODEL		
			for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
				currentTableView.removeRow(i);
			}
			
			// Get Query ============================================================
			
			String topSellerQuery = SqlBuilder.getTopSellerQuery(filter);
		
			// Populate table =======================================================
			
			ResultSet topSellerResultSet = DbReader.executeQuery(topSellerQuery);
			int rank = 1;
			while(topSellerResultSet.next()) {
				currentTableView.addRow(new Object[] 
						{rank,
						topSellerResultSet.getInt(1), 
						topSellerResultSet.getString(2),
						topSellerResultSet.getLong(3)});
				rank++;
			}
			
			topSellerResultSet.close();
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem processing the query", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	
	// ==========================================================================
	// Chart Methods
	// ==========================================================================
	
	
	
	public void showBarChart() {
		ChartBuilder.showTopSellerBarChart(dateRangeOfCurrentReport, currentTableView);
	}		

}
