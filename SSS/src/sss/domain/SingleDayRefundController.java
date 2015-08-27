/* SingleDayRefundController Class
 * 
 * Controls application logic for the Generate Single Day Refund UC
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import sss.services.ChartBuilder;
import sss.services.DbReader;
import sss.services.SqlBuilder;

public class SingleDayRefundController extends ReportController {

	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private String dateOfCurrentReport = "No Date";
	
	private NonEditableTableModel dollarRefundsData = new NonEditableTableModel(); // Contains grouped-on-hour refund dollar info
	private NonEditableTableModel volumeRefundsData = new NonEditableTableModel(); // Contains grouped-on-hour refund volume info
	
	// Column names of table models
	private String[] dollarRefundsColNames = {"Hours", "Number of Refunds", "Refund Total"};
	private String[] volumeRefundsColNames = {"Hours", "Number of Refunds"};
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	/**
	 * Constructor - calls initialise to set up controller
	 */
	public SingleDayRefundController() {
		initialise();
	}
	
	
	
	// ==========================================================================
	// Core Methods
	// ==========================================================================
	
	
	
	/**
	 * Establishes column identifiers for table models
	 */
	protected void initialise() {
		dollarRefundsData.setColumnIdentifiers(dollarRefundsColNames);
		volumeRefundsData.setColumnIdentifiers(volumeRefundsColNames);
	}
	
	
	
	private void switchViewToRefundDollarView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextDollarRefundRow = new Object[dollarRefundsData.getRowCount()][dollarRefundsData.getColumnCount()];
		for(int i = 0; i < nextDollarRefundRow.length; i++) {
			for(int j = 0; j < nextDollarRefundRow[0].length; j++) {
				nextDollarRefundRow[i][j] = dollarRefundsData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(dollarRefundsColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextDollarRefundRow.length; i++) {
			currentTableView.addRow(nextDollarRefundRow[i]);
		}
	}
	
	
	
	private void switchViewToRefundVolumeView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextVolumeRefundRow = new Object[volumeRefundsData.getRowCount()][volumeRefundsData.getColumnCount()];
		for(int i = 0; i < nextVolumeRefundRow.length; i++) {
			for(int j = 0; j < nextVolumeRefundRow[0].length; j++) {
				nextVolumeRefundRow[i][j] = volumeRefundsData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(volumeRefundsColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextVolumeRefundRow.length; i++) {
			currentTableView.addRow(nextVolumeRefundRow[i]);
		}
	}
	
	
	
	/**
	 * Method to switch the table model data based on user input
	 * @param reportType the report type (dollar, volume, profit)
	 * @param viewType the view type (all, summary)
	 */
	public void switchView(String reportType, String viewType) {

		switch(viewType) {
		case "summary":
			if(reportType.equals("refundDollar")) {
				switchViewToRefundDollarView();
			}
			else {
				switchViewToRefundVolumeView();
			}
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	 * Populates data models with sale data from the database
	 * @param inputDateString the date of the day for which to get sale data
	 */
	public void getResults(String inputDateString) {

		try {
			Date inputDate = dateFormat.parse(inputDateString);
			String startDate = sqlDateFormat.format(inputDate); 		// Convert to MySQL date string
			dateOfCurrentReport = dateFormat.format(inputDate);
			
			// CLEAR DATA MODELS			
			if(dollarRefundsData.getRowCount() != 0) {
				for(int i = dollarRefundsData.getRowCount()-1; i != -1; i--) {
					dollarRefundsData.removeRow(i);
				}
			}
			
			if(volumeRefundsData.getRowCount() != 0) {
				for(int i = volumeRefundsData.getRowCount()-1; i != -1; i--) {
					volumeRefundsData.removeRow(i);
				}
			}
			
			String refundQuery = SqlBuilder.getSingleDayRefundQuery(startDate);
			ResultSet refundResultSet = DbReader.executeQuery(refundQuery);
			
			// Populate dollarRefundsData
			while(refundResultSet.next()) {
				dollarRefundsData.addRow(new Object[] 
						{refundResultSet.getString(1),
						refundResultSet.getInt(2), 
						new BigDecimal(refundResultSet.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}
			
			refundResultSet.beforeFirst(); // Go back to before first row of ResultSet
			
			// Populate volumeSalesData with same ResultSet
			while(refundResultSet.next()) {
				volumeRefundsData.addRow(new Object[] 
						{refundResultSet.getString(1),
						refundResultSet.getInt(2)});
			}
			
			refundResultSet.close();
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem processing the query", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	
	// ==========================================================================
	// Chart Methods
	// ==========================================================================
	
	
	
	public void showBarChart(String reportType) {
			ChartBuilder.showSingleDayBarChart(reportType, dateOfCurrentReport, dollarRefundsData);
	}
	
}
