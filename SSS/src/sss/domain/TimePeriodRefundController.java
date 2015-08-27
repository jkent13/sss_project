/* TimePeriodRefundController Class
 * 
 * Controls application logic for Generate Time Period Refund Report UC
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

public class TimePeriodRefundController extends ReportController {

	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private String[] dateRangeOfCurrentReport = {"No Date", "No Date"}; 
	
	private NonEditableTableModel dayDollarData = new NonEditableTableModel();
	private NonEditableTableModel dayVolumeData = new NonEditableTableModel();
	
	private NonEditableTableModel weekDollarData = new NonEditableTableModel();
	private NonEditableTableModel weekVolumeData = new NonEditableTableModel();
	
	private NonEditableTableModel monthDollarData = new NonEditableTableModel();
	private NonEditableTableModel monthVolumeData = new NonEditableTableModel();
	
	// Column names of table models
	private String[] dayDollarColNames = {"Day", "Number of Refunds", "Refund Total"};
	private String[] dayVolumeColNames = {"Day", "Number of Refunds"};
	
	private String[] weekDollarColNames = {"Week No.", "Starting Date", "Number of Refunds", "Refund Total"};
	private String[] weekVolumeColNames = {"Week No.", "Starting Date", "Number of Refunds"};
	
	private String[] monthDollarColNames = {"Month", "Starting Date", "Number of Refunds","Refund Total"};
	private String[] monthVolumeColNames = {"Month", "Starting Date", "Number of Refunds"};
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	/**
	 * Constructor - calls initialise to set up controller
	 */
	public TimePeriodRefundController() {
		initialise();
	}
	
	
	
	// ==========================================================================
	// Core Methods
	// ==========================================================================
	
	
	
	/**
	 * Establishes column identifiers for table models
	 */
	protected void initialise() {
		dayDollarData.setColumnIdentifiers(dayDollarColNames);
		dayVolumeData.setColumnIdentifiers(dayVolumeColNames);
		
		weekDollarData.setColumnIdentifiers(weekDollarColNames);
		weekVolumeData.setColumnIdentifiers(weekVolumeColNames);
				
		monthDollarData.setColumnIdentifiers(monthDollarColNames);
		monthVolumeData.setColumnIdentifiers(monthVolumeColNames);
	}
	
	
	
	private void switchToDayDollarView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextDayDollarRow = new Object[dayDollarData.getRowCount()][dayDollarData.getColumnCount()];
		for(int i = 0; i < nextDayDollarRow.length; i++) {
			for(int j = 0; j < nextDayDollarRow[0].length; j++) {
				nextDayDollarRow[i][j] = dayDollarData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(dayDollarColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextDayDollarRow.length; i++) {
			currentTableView.addRow(nextDayDollarRow[i]);
		}
	}
	
	
	
	private void switchToDayVolumeView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextDayVolumeRow = new Object[dayVolumeData.getRowCount()][dayVolumeData.getColumnCount()];
		for(int i = 0; i < nextDayVolumeRow.length; i++) {
			for(int j = 0; j < nextDayVolumeRow[0].length; j++) {
				nextDayVolumeRow[i][j] = dayVolumeData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(dayVolumeColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextDayVolumeRow.length; i++) {
			currentTableView.addRow(nextDayVolumeRow[i]);
		}
	}
	
	
	
	private void switchToWeekDollarView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextWeekDollarRow = new Object[weekDollarData.getRowCount()][weekDollarData.getColumnCount()];
		for(int i = 0; i < nextWeekDollarRow.length; i++) {
			for(int j = 0; j < nextWeekDollarRow[0].length; j++) {
				nextWeekDollarRow[i][j] = weekDollarData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(weekDollarColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextWeekDollarRow.length; i++) {
			currentTableView.addRow(nextWeekDollarRow[i]);
		}
	}
	
	
	
	private void switchToWeekVolumeView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextWeekVolumeRow = new Object[weekVolumeData.getRowCount()][weekVolumeData.getColumnCount()];
		for(int i = 0; i < nextWeekVolumeRow.length; i++) {
			for(int j = 0; j < nextWeekVolumeRow[0].length; j++) {
				nextWeekVolumeRow[i][j] = weekVolumeData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(weekVolumeColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextWeekVolumeRow.length; i++) {
			currentTableView.addRow(nextWeekVolumeRow[i]);
		}
	}
	
	
	
	private void switchToMonthDollarView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextMonthDollarRow = new Object[monthDollarData.getRowCount()][monthDollarData.getColumnCount()];
		for(int i = 0; i < nextMonthDollarRow.length; i++) {
			for(int j = 0; j < nextMonthDollarRow[0].length; j++) {
				nextMonthDollarRow[i][j] = monthDollarData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(monthDollarColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextMonthDollarRow.length; i++) {
			currentTableView.addRow(nextMonthDollarRow[i]);
		}
	}
	
	
	
	private void switchToMonthVolumeView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextMonthVolumeRow = new Object[monthVolumeData.getRowCount()][monthVolumeData.getColumnCount()];
		for(int i = 0; i < nextMonthVolumeRow.length; i++) {
			for(int j = 0; j < nextMonthVolumeRow[0].length; j++) {
				nextMonthVolumeRow[i][j] = monthVolumeData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(monthVolumeColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextMonthVolumeRow.length; i++) {
			currentTableView.addRow(nextMonthVolumeRow[i]);
		}
	}

	
	
	/**
	 * Method to switch the table model data based on user input
	 * @param reportType the report type (dollar, volume, profit)
	 * @param groupBy the level of grouping (day, week, month)
	 */
	public void switchView(String reportType, String groupBy) {

		switch(groupBy) {
		case "day" :
			if(reportType.equals("refundDollar")) {
				switchToDayDollarView();
			}
			else {
				switchToDayVolumeView();
			}
			break;
		case "week" :
			if(reportType.equals("refundDollar")) {
				switchToWeekDollarView();
			}
			else {
				switchToWeekVolumeView();
			}
			break;
		case "month" :
			if(reportType.equals("refundDollar")) {
				switchToMonthDollarView();
			}
			else {
				switchToMonthVolumeView();
			}
			break;
		default:
			break;
		}
	}
	
	
	
	public void getResults(String startDate, String endDate) {
		try {
			Date inputStartDate = dateFormat.parse(startDate);
			Date inputEndDate = dateFormat.parse(endDate);
			
			String startDateString = sqlDateFormat.format(inputStartDate); 		// Convert to MySQL date string
			String endDateString = sqlDateFormat.format(inputEndDate); 	// Convert to MySQL date string
			
			dateRangeOfCurrentReport[0] = dateFormat.format(inputStartDate);
			dateRangeOfCurrentReport[1] = dateFormat.format(inputEndDate);
			
			// CLEAR DATA MODELS			
			// Day Tables ==========================================================
			
			if(dayDollarData.getRowCount() != 0) {
				for(int i = dayDollarData.getRowCount()-1; i != -1; i--) {
					dayDollarData.removeRow(i);
				}
			}
			
			if(dayVolumeData.getRowCount() != 0) {
				for(int i = dayVolumeData.getRowCount()-1; i != -1; i--) {
					dayVolumeData.removeRow(i);
				}
			}

			// Week Tables =========================================================
			
			if(weekDollarData.getRowCount() != 0) {
				for(int i = weekDollarData.getRowCount()-1; i != -1; i--) {
					weekDollarData.removeRow(i);
				}
			}
			
			if(weekVolumeData.getRowCount() != 0) {
				for(int i = weekVolumeData.getRowCount()-1; i != -1; i--) {
					weekVolumeData.removeRow(i);
				}
			}
			
			// Month Tables ========================================================
			
			if(monthDollarData.getRowCount() != 0) {
				for(int i = monthDollarData.getRowCount()-1; i != -1; i--) {
					monthDollarData.removeRow(i);
				}
			}
			
			if(monthVolumeData.getRowCount() != 0) {
				for(int i = monthVolumeData.getRowCount()-1; i != -1; i--) {
					monthVolumeData.removeRow(i);
				}
			}
			
			// Get Day Queries =====================================================
			
			String dayQuery = SqlBuilder.getRefundByDayQuery(startDateString, endDateString);
			
			// Get Week Queries ====================================================
			
			String weekQuery = SqlBuilder.getRefundByWeekQuery(startDate, endDateString);
			
			// Get Month Queries ==================================================
			
			String monthQuery = SqlBuilder.getRefundByMonthQuery(startDateString, endDateString);
			
			// Populate Day tables =================================================
			
			ResultSet dayResultSet = DbReader.executeQuery(dayQuery);
			
			// Populate daySalesData
			while(dayResultSet.next()) {
				dayDollarData.addRow(new Object[] 
						{dayResultSet.getString(1),
						dayResultSet.getInt(2), 
						new BigDecimal(dayResultSet.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}
		
			dayResultSet.beforeFirst(); // Go back to before first row of ResultSet
			
			// Populate dayVolumeData with same ResultSet
			while(dayResultSet.next()) {
				dayVolumeData.addRow(new Object[] 
						{dayResultSet.getString(1),
						dayResultSet.getInt(2)});
			}
			
			dayResultSet.close(); // MUST CLOSE RESULTSET AFTER USE
			
			// Populate Week Tables ================================================
			
			ResultSet weekResultSet = DbReader.executeQuery(weekQuery);
			
			// Populate weekSalesData
			while(weekResultSet.next()) {
				weekDollarData.addRow(new Object[] 
						{weekResultSet.getString(1),
						weekResultSet.getString(2),
						weekResultSet.getInt(3), 
						new BigDecimal(weekResultSet.getDouble(4)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}
			
			weekResultSet.beforeFirst(); // Go back to before first row of ResultSet
			
			// Populate weekVolumeData with same ResultSet
			while(weekResultSet.next()) {
				weekVolumeData.addRow(new Object[] 
						{weekResultSet.getString(1),
						weekResultSet.getString(2),
						weekResultSet.getInt(3)});
			}
			
			weekResultSet.close(); // MUST CLOSE RESULTSET AFTER USE
			
			// Populate Month Tables ===============================================
			
			ResultSet monthResultSet = DbReader.executeQuery(monthQuery);
			
			// Populate weekSalesData
			while(monthResultSet.next()) {
				monthDollarData.addRow(new Object[] 
						{monthResultSet.getString(1),
						monthResultSet.getString(2),
						monthResultSet.getInt(3), 
						new BigDecimal(monthResultSet.getDouble(4)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}
			
			monthResultSet.beforeFirst(); // Go back to before first row of ResultSet
			
			// Populate weekVolumeData with same ResultSet
			while(monthResultSet.next()) {
				monthVolumeData.addRow(new Object[] 
						{monthResultSet.getString(1),
						monthResultSet.getString(2),
						monthResultSet.getInt(3)});
			}
			
			monthResultSet.close(); // MUST CLOSE RESULTSET AFTER USE
			
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
	
	
	
	public void showBarChart(String reportType, String groupBy) {
		
		switch(groupBy) {
		case "day" :
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, dayDollarData);
				break;
		case "week" :
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, weekDollarData);
				break;
		case "month" :
			ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, monthDollarData);
			break;
		default :
			break;
		}
	}

}// End class
