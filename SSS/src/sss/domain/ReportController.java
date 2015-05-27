/* ReportController Class
 * 
 * Serves as the controlling class behind all reporting use cases and UIs
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import sss.services.DbReader;
import sss.services.SqlBuilder;

public class ReportController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private NonEditableTableModel currentTableView = new NonEditableTableModel(); // IMPLEMENT AS OBSERVABLE
	
	private NonEditableTableModel allSalesData = new NonEditableTableModel();		// Containing SELECT * sale info
	private NonEditableTableModel summarySalesData = new NonEditableTableModel();	// Containing grouped-on-hour sale info
	
	private String[] summaryColNames = {"Hours", "Number of Transactions", "Sale Total"};
	private String[] allSalesColNames = {"Sale ID", "Timestamp", "Total", "Subtotal", "GST", "Amount Tendered", "Change Given", "Type"};
	
	public ReportController() {
		initialise();
	}
	
	private void initialise() {
		allSalesData.setColumnIdentifiers(allSalesColNames);
		summarySalesData.setColumnIdentifiers(summaryColNames);
	}
	
	public NonEditableTableModel getDataModel() {
		return currentTableView;
	}
	
	public boolean isValidDate(String inputDateString) {
		try {
			Date inputDate = dateFormat.parse(inputDateString);
			if(!dateFormat.format(inputDate).equals(inputDateString)) {
				JOptionPane.showMessageDialog(null, "Error: Invalid date!", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else {
				return true;
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public void switchModel(String radioButton) {
		currentTableView = allSalesData;
	}
	
	public void getResults(String inputDateString) {

		try {
			Date inputDate = dateFormat.parse(inputDateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inputDate);
			calendar.add(Calendar.DATE, 1);

			String endDate = sqlDateFormat.format(calendar.getTime()); // Convert to MySQL date string
			String startDate = sqlDateFormat.format(inputDate); // Convert to MySQL date string
			
			// CLEAR DATA MODELS
			if(summarySalesData.getRowCount() != 0) {
				for(int i = summarySalesData.getRowCount()-1; i != -1; i--) {
					summarySalesData.removeRow(i);
				}
			}
			
			if(allSalesData.getRowCount() != 0) {
				for(int i = allSalesData.getRowCount()-1; i != -1; i--) {
					allSalesData.removeRow(i);
				}
			}
			
			// GET QUERIES
			String allSalesQuery = SqlBuilder.getSaleReportQuery(startDate, endDate);
			String summarySalesQuery = SqlBuilder.getSaleReportByHour(startDate, endDate);
			
			ResultSet summaryResultSet = DbReader.executeQuery(summarySalesQuery);
			
			while(summaryResultSet.next()) {
				summarySalesData.addRow(new Object[] 
						{summaryResultSet.getString(1),
						summaryResultSet.getInt(2), 
						summaryResultSet.getDouble(3)});
			}
			
			summaryResultSet.close(); // MUST CLOSE RESULTSET AFTER USE 
			ResultSet allSalesResultSet = DbReader.executeQuery(allSalesQuery);
			
			while(allSalesResultSet.next()) {
				allSalesData.addRow(new Object[] 
						{allSalesResultSet.getLong("sale_id"), 
						allSalesResultSet.getDate("sale_date"), 
						allSalesResultSet.getDouble("sale_total") , 
						allSalesResultSet.getDouble("sale_subtotal"), 
						allSalesResultSet.getDouble("sale_gst"), 
						allSalesResultSet.getDouble("sale_amt_tendered"), 
						allSalesResultSet.getDouble("sale_balance"), 
						allSalesResultSet.getString("sale_type")});
			}
			
			currentTableView = summarySalesData;
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem processing the query", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
