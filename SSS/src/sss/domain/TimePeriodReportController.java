package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import sss.services.ChartBuilder;
import sss.services.DbReader;
import sss.services.SqlBuilder;

public class TimePeriodReportController {
		
		private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		// Date format used to validate input
		private SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");	// Date format used to convert input into MySQL DateTime
		
		private String[] dateRangeOfCurrentReport = {"No Date", "No Date"}; 
		
		private NonEditableTableModel currentTableView = new NonEditableTableModel();
		
		private NonEditableTableModel daySalesData = new NonEditableTableModel();
		private NonEditableTableModel dayVolumeData = new NonEditableTableModel();
		private NonEditableTableModel dayGrossProfitData = new NonEditableTableModel();
		
		// Column names of table models
		private String[] daySalesColNames = {"Day", "Number of Transactions", "Sale Total"};
		private String[] dayVolumeColNames = {"Day", "Number of Transactions"};
		private String[] dayGrossProfitColNames = {"Day", "Number of Products Sold", "Gross Profit"};
		
		
		/**
		 * Constructor - calls initialise to set up controller
		 */
		public TimePeriodReportController() {
			initialise();
		}
		
		//--------------- Core Methods-------------------------------------
		/**
		 * Establishes column identifiers for table models
		 */
		private void initialise() {
			daySalesData.setColumnIdentifiers(daySalesColNames);
			dayVolumeData.setColumnIdentifiers(dayVolumeColNames);
			dayGrossProfitData.setColumnIdentifiers(dayGrossProfitColNames);
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
		
		/**
		 * Method to switch the table model data based on user input
		 * @param reportType the report type (dollar, volume, profit)
		 * @param groupBy the view type (all, summary)
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
				break;
			case "month" :
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
				
				dateRangeOfCurrentReport[0] = startDate;
				dateRangeOfCurrentReport[1] = endDate;
				
				// CLEAR DATA MODELS			
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
				
				// GET QUERIES
				String daySalesQuery = SqlBuilder.getSaleDollarByDayQuery(startDateString, endDateString);
				String dayGrossProfitQuery = SqlBuilder.getGrossProfitByDayQuery(startDateString, endDateString);

				
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
				ResultSet grossProfitResultSet = DbReader.executeQuery(dayGrossProfitQuery);
				
				// Populate grossProfitSalesData
				while(grossProfitResultSet.next()) {
					dayGrossProfitData.addRow(new Object[] 
							{grossProfitResultSet.getString(1),
							grossProfitResultSet.getInt(2), 
							new BigDecimal(grossProfitResultSet.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
				}
				
				grossProfitResultSet.close();
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error: There was a problem processing the query", "SQL Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
		public void showLineChart(String reportType) {
			
			switch(reportType) {
			case "dollar" :
				break;
			case "volume" :
		    break;
			case "profit" :
				break;
			default:
				break;
			}
		}
		
		public void showBarChart(String reportType) {
			
			switch(reportType) {
			case "dollar" :
				break;
			case "volume" :
				break;
			case "profit" :
				break;
			case "refundDollar" :
				break;
			case "refundVolume" :
				break;
			default :
				break;
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
		
		//------------------ Validator Methods -----------------------------
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

		public boolean isStartDateBeforeEndDate(String startDate, String endDate) {
			try {
				Date firstDate = dateFormat.parse(startDate);
				Date secondDate = dateFormat.parse(endDate);

				if(firstDate.before(secondDate)) { 
					return true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Error: The start date must be before the end date!", "Invalid Dates Input", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (ParseException e) { // If the either date string does not parse, it is not in the correct format
				JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		//-----------------------------------------------------------------
	}// End class
