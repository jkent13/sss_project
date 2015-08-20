package sss.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import sss.services.DbReader;
import sss.services.SqlBuilder;

public class SlowSellersReportController extends ReportController {

	private String[] tableColumns = {"Units Sold", "Product Name", "Product ID"};
	
	private String[] dateRangeOfCurrentReport = {"No Date", "No Date"};
	
	public SlowSellersReportController() {
		initialise();
	}
	
	@Override
	protected void initialise() {
		currentTableView.setColumnIdentifiers(tableColumns);
	}
	
	public void getResults(String startDate, String endDate, int unitsSold) {
		try {
			Date inputStartDate = dateFormat.parse(startDate);
			Date inputEndDate = dateFormat.parse(endDate);
			
			String sqlStartDate = (sqlDateFormat.format(inputStartDate)); 		// Convert to MySQL date string
			String sqlEndDate = (sqlDateFormat.format(inputEndDate)); 				// Convert to MySQL date string
			
			dateRangeOfCurrentReport[0] = dateFormat.format(inputStartDate);
			dateRangeOfCurrentReport[1] = dateFormat.format(inputEndDate);
			
			// CLEAR DATA MODEL		
			for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
				currentTableView.removeRow(i);
			}
			
			// ======================================================================
			// Get Query ============================================================
			
			String slowSellerQueryPartOne = SqlBuilder.getSlowSellerQuery(sqlStartDate, sqlEndDate, unitsSold);
			String slowSellerQueryPartTwo = SqlBuilder.getZeroSaleProducts(sqlStartDate, sqlEndDate);
			
			// ======================================================================
			// Populate table ================================================
			
			ResultSet queryOneResultSet = DbReader.executeQuery(slowSellerQueryPartOne);
			
			while(queryOneResultSet.next()) {
				currentTableView.addRow(new Object[] 
						{queryOneResultSet.getInt(1), 
						queryOneResultSet.getString(2),
						queryOneResultSet.getLong(3)});
			}
			
			queryOneResultSet.close();
			ResultSet queryTwoResultSet = DbReader.executeQuery(slowSellerQueryPartTwo);
			
			while(queryTwoResultSet.next()) {
				currentTableView.addRow(new Object[] 
						{queryTwoResultSet.getInt(1), 
						queryTwoResultSet.getString(2),
						queryTwoResultSet.getLong(3)});
			}
			
			queryTwoResultSet.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem processing the query", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
