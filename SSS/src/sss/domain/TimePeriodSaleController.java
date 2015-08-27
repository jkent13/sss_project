/* TimePeriodSaleController Class
 * 
 * Controls application logic for Generate Time Period Sale Report UC
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

public class TimePeriodSaleController extends ReportController {

	// ==========================================================================
	// Variables
	// ==========================================================================



	private String[] dateRangeOfCurrentReport = {"No Date", "No Date"}; 

	private NonEditableTableModel daySalesData = new NonEditableTableModel();
	private NonEditableTableModel dayVolumeData = new NonEditableTableModel();
	private NonEditableTableModel dayGrossProfitData = new NonEditableTableModel();

	private NonEditableTableModel weekSalesData = new NonEditableTableModel();
	private NonEditableTableModel weekVolumeData = new NonEditableTableModel();
	private NonEditableTableModel weekGrossProfitData = new NonEditableTableModel();

	private NonEditableTableModel monthSalesData = new NonEditableTableModel();
	private NonEditableTableModel monthVolumeData = new NonEditableTableModel();
	private NonEditableTableModel monthGrossProfitData = new NonEditableTableModel();

	// Column names of table models
	private String[] daySalesColNames = {"Day", "Number of Transactions", "Sale Total"};
	private String[] dayVolumeColNames = {"Day", "Number of Transactions"};
	private String[] dayGrossProfitColNames = {"Day", "Number of Products Sold", "Gross Profit"};

	private String[] weekSalesColNames = {"Week No.", "Starting Date", "Number of Transactions", "Sale Total"};
	private String[] weekVolumeColNames = {"Week No.", "Starting Date", "Number of Transactions"};
	private String[] weekGrossProfitColNames = {"Week No.", "Starting Date", "Number of Products Sold", "Gross Profit"};

	private String[] monthSalesColNames = {"Month", "Starting Date", "Number of Transactions","Sale Total"};
	private String[] monthVolumeColNames = {"Month", "Starting Date", "Number of Transactions"};
	private String[] monthGrossProfitColNames = {"Month", "Starting Date", "Number of Products Sold", "Gross Profit"};



	// ==========================================================================
	// Constructor
	// ==========================================================================



	/**
	 * Constructor - calls initialise to set up controller
	 */
	public TimePeriodSaleController() {
		initialise();
	}



	// ==========================================================================
	// Core Methods
	// ==========================================================================



	/**
	 * Establishes column identifiers for table models
	 */
	protected void initialise() {
		daySalesData.setColumnIdentifiers(daySalesColNames);
		dayVolumeData.setColumnIdentifiers(dayVolumeColNames);
		dayGrossProfitData.setColumnIdentifiers(dayGrossProfitColNames);

		weekSalesData.setColumnIdentifiers(weekSalesColNames);
		weekVolumeData.setColumnIdentifiers(weekVolumeColNames);
		weekGrossProfitData.setColumnIdentifiers(weekGrossProfitColNames);

		monthSalesData.setColumnIdentifiers(monthSalesColNames);
		monthVolumeData.setColumnIdentifiers(monthVolumeColNames);
		monthGrossProfitData.setColumnIdentifiers(monthGrossProfitColNames);
	}



	private void switchToSalesDayDollarView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextDayDollarRow = new Object[daySalesData.getRowCount()][daySalesData.getColumnCount()];
		for(int i = 0; i < nextDayDollarRow.length; i++) {
			for(int j = 0; j < nextDayDollarRow[0].length; j++) {
				nextDayDollarRow[i][j] = daySalesData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(daySalesColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextDayDollarRow.length; i++) {
			currentTableView.addRow(nextDayDollarRow[i]);
		}
	}



	private void switchToSalesDayVolumeView() {
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



	private void switchToGrossProfitDayView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextDayGrossProfitRow = new Object[dayGrossProfitData.getRowCount()][dayGrossProfitData.getColumnCount()];
		for(int i = 0; i < nextDayGrossProfitRow.length; i++) {
			for(int j = 0; j < nextDayGrossProfitRow[0].length; j++) {
				nextDayGrossProfitRow[i][j] = dayGrossProfitData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(dayGrossProfitColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextDayGrossProfitRow.length; i++) {
			currentTableView.addRow(nextDayGrossProfitRow[i]);
		}
	}



	private void switchToSalesWeekDollarView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextWeekSalesRow = new Object[weekSalesData.getRowCount()][weekSalesData.getColumnCount()];
		for(int i = 0; i < nextWeekSalesRow.length; i++) {
			for(int j = 0; j < nextWeekSalesRow[0].length; j++) {
				nextWeekSalesRow[i][j] = weekSalesData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(weekSalesColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextWeekSalesRow.length; i++) {
			currentTableView.addRow(nextWeekSalesRow[i]);
		}
	}



	private void switchToSalesWeekVolumeView() {
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



	private void switchToGrossProfitWeekView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextWeekGrossProfitRow = new Object[weekGrossProfitData.getRowCount()][weekGrossProfitData.getColumnCount()];
		for(int i = 0; i < nextWeekGrossProfitRow.length; i++) {
			for(int j = 0; j < nextWeekGrossProfitRow[0].length; j++) {
				nextWeekGrossProfitRow[i][j] = weekGrossProfitData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(weekGrossProfitColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextWeekGrossProfitRow.length; i++) {
			currentTableView.addRow(nextWeekGrossProfitRow[i]);
		}
	}



	private void switchToSalesMonthDollarView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextMonthDollarRow = new Object[monthSalesData.getRowCount()][monthSalesData.getColumnCount()];
		for(int i = 0; i < nextMonthDollarRow.length; i++) {
			for(int j = 0; j < nextMonthDollarRow[0].length; j++) {
				nextMonthDollarRow[i][j] = monthSalesData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(monthSalesColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextMonthDollarRow.length; i++) {
			currentTableView.addRow(nextMonthDollarRow[i]);
		}
	}



	private void switchToSalesMonthVolumeView() {
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



	private void switchToGrossProfitMonthView() {
		// REMOVE ALL ROWS
		for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
			currentTableView.removeRow(i);
		}

		// EXTRACT ALL DATA FROM NEW DATAMODEL
		Object[][] nextMonthGrossProfitRow = new Object[monthGrossProfitData.getRowCount()][monthGrossProfitData.getColumnCount()];
		for(int i = 0; i < nextMonthGrossProfitRow.length; i++) {
			for(int j = 0; j < nextMonthGrossProfitRow[0].length; j++) {
				nextMonthGrossProfitRow[i][j] = monthGrossProfitData.getValueAt(i, j);
			}
		}

		// SET NEW COLUMNS
		currentTableView.setColumnIdentifiers(monthGrossProfitColNames);

		// ADD NEW ROWS
		for(int i = 0; i < nextMonthGrossProfitRow.length; i++) {
			currentTableView.addRow(nextMonthGrossProfitRow[i]);
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
			if(reportType.equals("dollar")) {
				switchToSalesDayDollarView();
			}
			else if (reportType.equals("volume")){
				switchToSalesDayVolumeView();
			}
			else {
				switchToGrossProfitDayView();
			}
			break;
		case "week" :
			if(reportType.equals("dollar")) {
				switchToSalesWeekDollarView();
			}
			else if (reportType.equals("volume")){
				switchToSalesWeekVolumeView();
			}
			else {
				switchToGrossProfitWeekView();
			}
			break;
		case "month" :
			if(reportType.equals("dollar")) {
				switchToSalesMonthDollarView();
			}
			else if (reportType.equals("volume")){
				switchToSalesMonthVolumeView();
			}
			else {
				switchToGrossProfitMonthView();
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

			if(daySalesData.getRowCount() != 0) {
				for(int i = daySalesData.getRowCount()-1; i != -1; i--) {
					daySalesData.removeRow(i);
				}
			}

			if(dayVolumeData.getRowCount() != 0) {
				for(int i = dayVolumeData.getRowCount()-1; i != -1; i--) {
					dayVolumeData.removeRow(i);
				}
			}

			if(dayGrossProfitData.getRowCount() != 0) {
				for(int i = dayGrossProfitData.getRowCount()-1; i != -1; i--) {
					dayGrossProfitData.removeRow(i);
				}
			}

			// Week Tables =========================================================

			if(weekSalesData.getRowCount() != 0) {
				for(int i = weekSalesData.getRowCount()-1; i != -1; i--) {
					weekSalesData.removeRow(i);
				}
			}

			if(weekVolumeData.getRowCount() != 0) {
				for(int i = weekVolumeData.getRowCount()-1; i != -1; i--) {
					weekVolumeData.removeRow(i);
				}
			}

			if(weekGrossProfitData.getRowCount() != 0) {
				for(int i = weekGrossProfitData.getRowCount()-1; i != -1; i--) {
					weekGrossProfitData.removeRow(i);
				}
			}

			// Month Tables ========================================================

			if(monthSalesData.getRowCount() != 0) {
				for(int i = monthSalesData.getRowCount()-1; i != -1; i--) {
					monthSalesData.removeRow(i);
				}
			}

			if(monthVolumeData.getRowCount() != 0) {
				for(int i = monthVolumeData.getRowCount()-1; i != -1; i--) {
					monthVolumeData.removeRow(i);
				}
			}

			if(monthGrossProfitData.getRowCount() != 0) {
				for(int i = monthGrossProfitData.getRowCount()-1; i != -1; i--) {
					monthGrossProfitData.removeRow(i);
				}
			}

			// Get Day Queries =====================================================

			String daySalesQuery = SqlBuilder.getSaleDollarByDayQuery(startDateString, endDateString);
			String dayGrossProfitQuery = SqlBuilder.getGrossProfitByDayQuery(startDateString, endDateString);

			// Get Week Queries ====================================================

			String weekSalesQuery = SqlBuilder.getSaleDollarByWeekQuery(startDateString, endDateString);
			String weekGrossProfitQuery = SqlBuilder.getGrossProfitByWeekQuery(startDateString, endDateString);

			// Get Month Queries ===================================================

			String monthSalesQuery = SqlBuilder.getSaleDollarByMonthQuery(startDateString, endDateString);
			String monthGrossProfitQuery = SqlBuilder.getGrossProfitByMonthQuery(startDateString, endDateString);

			// Populate Day tables =================================================

			ResultSet daySalesResultSet = DbReader.executeQuery(daySalesQuery);

			// Populate daySalesData
			while(daySalesResultSet.next()) {
				daySalesData.addRow(new Object[] 
						{daySalesResultSet.getString(1),
						daySalesResultSet.getInt(2), 
						new BigDecimal(daySalesResultSet.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}

			daySalesResultSet.beforeFirst(); // Go back to before first row of ResultSet

			// Populate dayVolumeData with same ResultSet
			while(daySalesResultSet.next()) {
				dayVolumeData.addRow(new Object[] 
						{daySalesResultSet.getString(1),
						daySalesResultSet.getInt(2)});
			}

			daySalesResultSet.close(); // MUST CLOSE RESULTSET AFTER USE
			ResultSet dayGrossProfitResultSet = DbReader.executeQuery(dayGrossProfitQuery);

			// Populate grossProfitSalesData
			while(dayGrossProfitResultSet.next()) {
				dayGrossProfitData.addRow(new Object[] 
						{dayGrossProfitResultSet.getString(1),
						dayGrossProfitResultSet.getInt(2), 
						new BigDecimal(dayGrossProfitResultSet.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}

			dayGrossProfitResultSet.close();

			// Populate Week Tables ================================================

			ResultSet weekSalesResultSet = DbReader.executeQuery(weekSalesQuery);

			// Populate weekSalesData
			while(weekSalesResultSet.next()) {
				weekSalesData.addRow(new Object[] 
						{weekSalesResultSet.getString(1),
						weekSalesResultSet.getString(2),
						weekSalesResultSet.getInt(3), 
						new BigDecimal(weekSalesResultSet.getDouble(4)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}

			weekSalesResultSet.beforeFirst(); // Go back to before first row of ResultSet

			// Populate weekVolumeData with same ResultSet
			while(weekSalesResultSet.next()) {
				weekVolumeData.addRow(new Object[] 
						{weekSalesResultSet.getString(1),
						weekSalesResultSet.getString(2),
						weekSalesResultSet.getInt(3)});
			}

			weekSalesResultSet.close(); // MUST CLOSE RESULTSET AFTER USE
			ResultSet weekGrossProfitResultSet = DbReader.executeQuery(weekGrossProfitQuery);

			// Populate grossProfitSalesData
			while(weekGrossProfitResultSet.next()) {
				weekGrossProfitData.addRow(new Object[] 
						{weekGrossProfitResultSet.getString(1),
						weekGrossProfitResultSet.getString(2),
						weekGrossProfitResultSet.getInt(3), 
						new BigDecimal(weekGrossProfitResultSet.getDouble(4)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}

			weekGrossProfitResultSet.close();

			// Populate Month Tables ===============================================

			ResultSet monthSalesResultSet = DbReader.executeQuery(monthSalesQuery);

			// Populate weekSalesData
			while(monthSalesResultSet.next()) {
				monthSalesData.addRow(new Object[] 
						{monthSalesResultSet.getString(1),
						monthSalesResultSet.getString(2),
						monthSalesResultSet.getInt(3), 
						new BigDecimal(monthSalesResultSet.getDouble(4)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}

			monthSalesResultSet.beforeFirst(); // Go back to before first row of ResultSet

			// Populate weekVolumeData with same ResultSet
			while(monthSalesResultSet.next()) {
				monthVolumeData.addRow(new Object[] 
						{monthSalesResultSet.getString(1),
						monthSalesResultSet.getString(2),
						monthSalesResultSet.getInt(3)});
			}

			monthSalesResultSet.close(); // MUST CLOSE RESULTSET AFTER USE
			ResultSet monthGrossProfitResultSet = DbReader.executeQuery(monthGrossProfitQuery);

			// Populate grossProfitSalesData
			while(monthGrossProfitResultSet.next()) {
				monthGrossProfitData.addRow(new Object[] 
						{monthGrossProfitResultSet.getString(1),
						monthGrossProfitResultSet.getString(2),
						monthGrossProfitResultSet.getInt(3), 
						new BigDecimal(monthGrossProfitResultSet.getDouble(4)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}

			monthGrossProfitResultSet.close();


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



	public void showLineChart(String reportType, String groupBy) {

		switch(reportType) {
		case "dollar" :
			if(groupBy.equals("day")) {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, daySalesData);
			}
			else if (groupBy.equals("week")) {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, weekSalesData);
			}
			else {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, monthSalesData);
			}
			break;
		case "volume" :
			if(groupBy.equals("day")) {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, dayVolumeData);
			}
			else if (groupBy.equals("week")) {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, weekVolumeData);
			}
			else {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, monthVolumeData);
			}
			break;
		case "profit" :
			if(groupBy.equals("day")) {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, dayGrossProfitData);
			}
			else if (groupBy.equals("week")) {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, weekGrossProfitData);
			}
			else {
				ChartBuilder.showTimePeriodLineChart(reportType, groupBy, dateRangeOfCurrentReport, monthGrossProfitData);
			}
			break;
		default:
			break;
		}
	}



	public void showBarChart(String reportType, String groupBy) {

		switch(reportType) {
		case "dollar" :
			if(groupBy.equals("day")) {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, daySalesData);
			}
			else if (groupBy.equals("week")) {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, weekSalesData);
			}
			else {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, monthSalesData);
			}
			break;
		case "volume" :
			if(groupBy.equals("day")) {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, dayVolumeData);
			}
			else if (groupBy.equals("week")) {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, weekVolumeData);
			}
			else {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, monthVolumeData);
			}
			break;
		case "profit" :
			if(groupBy.equals("day")) {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, dayGrossProfitData);
			}
			else if (groupBy.equals("week")) {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, weekGrossProfitData);
			}
			else {
				ChartBuilder.showTimePeriodBarChart(reportType, groupBy, dateRangeOfCurrentReport, monthGrossProfitData);
			}
			break;
		default :
			break;
		}
	}		

}// End class