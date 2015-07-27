/* ReportController Class
 * 
 * Serves as the controlling class behind all reporting use cases and UIs
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import sss.services.DbConnector;
import sss.services.DbReader;
import sss.services.SqlBuilder;

public class ReportController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		// Date format used to validate input
	private SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");	// Date format used to convert input into MySQL DateTime
	
	private NonEditableTableModel currentTableView = new NonEditableTableModel();
	
	private NonEditableTableModel allSalesData = new NonEditableTableModel();		// Containing SELECT * sale info
	private NonEditableTableModel dollarSalesData = new NonEditableTableModel();	// Containing grouped-on-hour sale info
	private NonEditableTableModel volumeSalesData = new NonEditableTableModel();	// Contains only hour and sale volume
	
	// Column names of table models
	private String[] allSalesColNames = {"Sale ID", "Timestamp", "Total", "Amount Tendered", "Change Given"};
	private String[] dollarColNames = {"Hours", "Number of Transactions", "Sale Total"};
	private String[] volumeColNames = {"Hours", "Number of Transactions"};
	
	/**
	 * Constructor - calls initialise to set up controller
	 */
	public ReportController() {
		initialise();
	}
	
	//--------------- Core Methods-------------------------------------
	/**
	 * Establishes column identifiers for table models
	 */
	private void initialise() {
		allSalesData.setColumnIdentifiers(allSalesColNames);
		dollarSalesData.setColumnIdentifiers(dollarColNames);
		volumeSalesData.setColumnIdentifiers(volumeColNames);
	}
	
	/**
	 * Method to switch the table model data based on user input
	 * @param reportType the report type (dollar, volume, profit)
	 * @param viewType the view type (all, summary)
	 */
	public void switchView(String reportType, String viewType) {

		switch(viewType) {
		case "all":
			if(!(currentTableView.getColumnCount() == allSalesData.getColumnCount())) {
				// REMOVE ALL ROWS
				for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
					currentTableView.removeRow(i);
				}

				// EXTRACT ALL DATA FROM NEW DATAMODEL
				Object[][] nextRow = new Object[allSalesData.getRowCount()][allSalesData.getColumnCount()];
				for(int i = 0; i < nextRow.length; i++) {
					for(int j = 0; j < nextRow[0].length; j++) {
						nextRow[i][j] = allSalesData.getValueAt(i, j);
					}
				}

				// SET NEW COLUMNS
				currentTableView.setColumnIdentifiers(allSalesColNames);

				// ADD NEW ROWS
				for(int i = 0; i < nextRow.length; i++) {
					currentTableView.addRow(nextRow[i]);
				}
			}
			break;
		case "summary":
			if(reportType.equals("dollar")) {
				// REMOVE ALL ROWS
				for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
					currentTableView.removeRow(i);
				}

				// EXTRACT ALL DATA FROM NEW DATAMODEL
				Object[][] nextDollarRow = new Object[dollarSalesData.getRowCount()][dollarSalesData.getColumnCount()];
				for(int i = 0; i < nextDollarRow.length; i++) {
					for(int j = 0; j < nextDollarRow[0].length; j++) {
						nextDollarRow[i][j] = dollarSalesData.getValueAt(i, j);
					}
				}

				// SET NEW COLUMNS
				currentTableView.setColumnIdentifiers(dollarColNames);

				// ADD NEW ROWS
				for(int i = 0; i < nextDollarRow.length; i++) {
					currentTableView.addRow(nextDollarRow[i]);
				}
			}
			else if(reportType.equals("volume")) {
				// REMOVE ALL ROWS
				for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
					currentTableView.removeRow(i);
				}

				// EXTRACT ALL DATA FROM NEW DATAMODEL
				Object[][] nextVolumeRow = new Object[volumeSalesData.getRowCount()][volumeSalesData.getColumnCount()];
				for(int i = 0; i < nextVolumeRow.length; i++) {
					for(int j = 0; j < nextVolumeRow[0].length; j++) {
						nextVolumeRow[i][j] = volumeSalesData.getValueAt(i, j);
					}
				}

				// SET NEW COLUMNS
				currentTableView.setColumnIdentifiers(volumeColNames);

				// ADD NEW ROWS
				for(int i = 0; i < nextVolumeRow.length; i++) {
					currentTableView.addRow(nextVolumeRow[i]);
				}
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
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inputDate);
			calendar.add(Calendar.DATE, 1);

			String endDate = sqlDateFormat.format(calendar.getTime()); 	// Convert to MySQL date string
			String startDate = sqlDateFormat.format(inputDate); 		// Convert to MySQL date string
			
			// CLEAR DATA MODELS
			if(dollarSalesData.getRowCount() != 0) {
				for(int i = dollarSalesData.getRowCount()-1; i != -1; i--) {
					dollarSalesData.removeRow(i);
				}
			}
			
			if(allSalesData.getRowCount() != 0) {
				for(int i = allSalesData.getRowCount()-1; i != -1; i--) {
					allSalesData.removeRow(i);
				}
			}
			
			if(volumeSalesData.getRowCount() != 0) {
				for(int i = volumeSalesData.getRowCount()-1; i != -1; i--) {
					volumeSalesData.removeRow(i);
				}
			}
			
			// GET QUERIES
			String allSalesQuery = SqlBuilder.getSaleReportQuery(startDate, endDate);
			String summarySalesQuery = SqlBuilder.getSaleReportByHourQuery(startDate, endDate);
			
			ResultSet summaryResultSet = DbReader.executeQuery(summarySalesQuery);
			
			// Populate dollarSalesData
			while(summaryResultSet.next()) {
				dollarSalesData.addRow(new Object[] 
						{summaryResultSet.getString(1),
						summaryResultSet.getInt(2), 
						new BigDecimal(summaryResultSet.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}
			
		
			summaryResultSet.beforeFirst(); // Go back to before first row of ResultSet
			
			// Populate volumeSalesData with same ResultSet
			while(summaryResultSet.next()) {
				volumeSalesData.addRow(new Object[] 
						{summaryResultSet.getString(1),
						summaryResultSet.getInt(2)});
			}
			
			summaryResultSet.close(); // MUST CLOSE RESULTSET AFTER USE 
			ResultSet allSalesResultSet = DbReader.executeQuery(allSalesQuery);
			
			// Populate allSalesData
			while(allSalesResultSet.next()) {
				allSalesData.addRow(new Object[] 
						{allSalesResultSet.getLong("sale_id"), 
						allSalesResultSet.getString("sale_date"), 
						new BigDecimal(allSalesResultSet.getDouble("sale_total")).setScale(2, BigDecimal.ROUND_HALF_EVEN) , 
						new BigDecimal(allSalesResultSet.getDouble("sale_amt_tendered")).setScale(2, BigDecimal.ROUND_HALF_EVEN), 
						new BigDecimal(allSalesResultSet.getDouble("sale_balance")).setScale(2, BigDecimal.ROUND_HALF_EVEN)});
			}
			
			// Reset view to default
			switchView("dollar","summary");
			allSalesResultSet.close();
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem processing the query", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	//-----------------------------------------------------------------
	
	//------ Getter Methods -------------------------------------------
	/**
	 * Getter method for the current data model
	 * @return a reference to the current data model
	 */
	public NonEditableTableModel getDataModel() {
		return currentTableView;
	}
	
	//-----------------------------------------------------------------
	
	//------------------ Validator Method -----------------------------
	/**
	 * Validation method for checking whether an input String is a valid date in the format: 12/02/2014
	 * @param inputDateString the String to be validated
	 * @return true if inputDateString is a valid date in the correct format, false otherwise
	 */
	public boolean isValidDate(String inputDateString) {
		if(inputDateString.equals("")){
			return false;
		}
		try {
			Date inputDate = dateFormat.parse(inputDateString);
			if(!dateFormat.format(inputDate).equals(inputDateString)) { // If the formatted inputDate != inputDateString, then the input date was invalid
				JOptionPane.showMessageDialog(null, "Error: Invalid date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else {
				return true;
			}
		} catch (ParseException e) { // If the inputDateString does not parse, it is not in the correct format
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	//-----------------------------------------------------------------
}// End class
