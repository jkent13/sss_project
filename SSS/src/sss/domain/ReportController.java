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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import sss.services.DatasetConverter;
import sss.services.DbConnector;
import sss.services.DbReader;
import sss.services.SqlBuilder;

public class ReportController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		// Date format used to validate input
	private SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");	// Date format used to convert input into MySQL DateTime
	
	private String dateOfCurrentReport = "No Date";
	
	private NonEditableTableModel currentTableView = new NonEditableTableModel();
	
	private NonEditableTableModel allSalesData = new NonEditableTableModel();		// Containing SELECT * sale info
	private NonEditableTableModel dollarSalesData = new NonEditableTableModel();	// Containing grouped-on-hour sale info
	private NonEditableTableModel volumeSalesData = new NonEditableTableModel();	// Contains only hour and sale volume
	private NonEditableTableModel grossProfitSalesData = new NonEditableTableModel(); // Contains only GP and sale volume
	
	// Column names of table models
	private String[] allSalesColNames = {"Sale ID", "Timestamp", "Total", "Amount Tendered", "Change Given"};
	private String[] dollarColNames = {"Hours", "Number of Transactions", "Sale Total"};
	private String[] volumeColNames = {"Hours", "Number of Transactions"};
	private String[] grossProfitColNames = {"Hours", "Number of Products Sold", "Gross Profit"};
	
	
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
		grossProfitSalesData.setColumnIdentifiers(grossProfitColNames);
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
			else {
			// REMOVE ALL ROWS
				for(int i = currentTableView.getRowCount()-1; i != -1; i--) {
					currentTableView.removeRow(i);
				}

				// EXTRACT ALL DATA FROM NEW DATAMODEL
				Object[][] nextProfitRow = new Object[grossProfitSalesData.getRowCount()][grossProfitSalesData.getColumnCount()];
				for(int i = 0; i < nextProfitRow.length; i++) {
					for(int j = 0; j < nextProfitRow[0].length; j++) {
						nextProfitRow[i][j] = grossProfitSalesData.getValueAt(i, j);
					}
				}

				// SET NEW COLUMNS
				currentTableView.setColumnIdentifiers(grossProfitColNames);

				// ADD NEW ROWS
				for(int i = 0; i < nextProfitRow.length; i++) {
					currentTableView.addRow(nextProfitRow[i]);
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
			dateOfCurrentReport = startDate;
			
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
			
			if(grossProfitSalesData.getRowCount() != 0) {
				for(int i = grossProfitSalesData.getRowCount()-1; i != -1; i--) {
					grossProfitSalesData.removeRow(i);
				}
			}
			
			// GET QUERIES
			String allSalesQuery = SqlBuilder.getSaleReportQuery(startDate, endDate);
			String summarySalesQuery = SqlBuilder.getSaleReportByHourQuery(startDate);
			String grossProfitSalesQuery = SqlBuilder.getSingleDayGrossProfitQuery(startDate);
			
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
			
			
			allSalesResultSet.close();
			ResultSet grossProfitResultSet = DbReader.executeQuery(grossProfitSalesQuery);
			
			while(grossProfitResultSet.next()) {
				grossProfitSalesData.addRow(new Object[] 
						{grossProfitResultSet.getString(1),
						grossProfitResultSet.getInt(2), 
						new BigDecimal(grossProfitResultSet.getDouble(3)).setScale(2, BigDecimal.ROUND_HALF_EVEN) });
			}
			
			grossProfitResultSet.close();
			
		// Reset view to default
		switchView("dollar","summary");
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem processing the query", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	public void showLineChart(String reportType) {
		
		switch(reportType) {
		case "dollar" :
			DefaultCategoryDataset dollarChartData = DatasetConverter.convertSingleDayDollar(dollarSalesData);
			
			JFreeChart dollarLineChart = ChartFactory.createLineChart(
	        "",
	        "Hour","Sale Amount ($)",
	        dollarChartData,
	        PlotOrientation.VERTICAL,
	        false,true,false);
			
	    CategoryPlot dollarPlot = (CategoryPlot)dollarLineChart.getPlot();
	    dollarPlot.setDomainGridlinesVisible(true);
	    CategoryAxis dollarXAxis = (CategoryAxis)dollarPlot.getDomainAxis();
	    dollarXAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	    ChartPanel dollarChartPanel = new ChartPanel( dollarLineChart );
	    dollarChartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );
	    
	    JFrame dollarChartFrame = new JFrame();
	    dollarChartFrame.setContentPane(dollarChartPanel);
	    dollarChartFrame.setTitle("Viewing Sales by Dollar for: " + dateOfCurrentReport);
	    dollarChartFrame.setLocationRelativeTo(null);
	    dollarChartFrame.pack();
			
	    dollarChartFrame.setVisible(true);
			break;
		
		case "volume" :
			DefaultCategoryDataset volumeChartData = DatasetConverter.convertSingleDayVolume(dollarSalesData);
			
			JFreeChart volumeLineChart = ChartFactory.createLineChart(
	        "",
	        "Hour","No. of Transactions",
	        volumeChartData,
	        PlotOrientation.VERTICAL,
	        false,true,false);
			
	    CategoryPlot volumePlot = (CategoryPlot)volumeLineChart.getPlot();
	    volumePlot.setDomainGridlinesVisible(true);
	    CategoryAxis volumeXAxis = (CategoryAxis)volumePlot.getDomainAxis();
	    volumeXAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	    ChartPanel volumeChartPanel = new ChartPanel( volumeLineChart );
	    volumeChartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );
	    
	    JFrame volumeChartFrame = new JFrame();
	    volumeChartFrame.setContentPane(volumeChartPanel);
	    volumeChartFrame.setTitle("Viewing Sales by Volume for: " + dateOfCurrentReport);
	    volumeChartFrame.setLocationRelativeTo(null);
	    volumeChartFrame.pack();

	    volumeChartFrame.setVisible(true);
	    break;
		case "profit" :
			DefaultCategoryDataset grossProfitChartData = DatasetConverter.convertSingleDayGrossProfit(grossProfitSalesData);

			JFreeChart grossProfitLineChart = ChartFactory.createLineChart(
					"",
					"Hour","GP Amount ($)",
					grossProfitChartData,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot grossProfitPlot = (CategoryPlot)grossProfitLineChart.getPlot();
			grossProfitPlot.setDomainGridlinesVisible(true);
			CategoryAxis grossProfitXAxis = (CategoryAxis)grossProfitPlot.getDomainAxis();
			grossProfitXAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			ChartPanel grossProfitChartPanel = new ChartPanel( grossProfitLineChart );
			grossProfitChartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );

			JFrame grossProfitChartFrame = new JFrame();
			grossProfitChartFrame.setContentPane(grossProfitChartPanel);
			grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: " + dateOfCurrentReport);
			grossProfitChartFrame.setLocationRelativeTo(null);
			grossProfitChartFrame.pack();

			grossProfitChartFrame.setVisible(true);
			break;
		default:
			break;
		}
		
		
	}
	
	public void showBarChart(String reportType) {
		
		switch(reportType) {
		case "dollar" :
			DefaultCategoryDataset dollarChartData = DatasetConverter.convertSingleDayDollar(dollarSalesData);
			
			JFreeChart dollarBarChart = ChartFactory.createBarChart(
	        "",
	        "Hour","Sale Amount ($)",
	        dollarChartData,
	        PlotOrientation.VERTICAL,
	        false,true,false);
			
	    CategoryPlot dollarPlot = (CategoryPlot)dollarBarChart.getPlot();
	    dollarPlot.setDomainGridlinesVisible(true);
	    CategoryAxis dollarXAxis = (CategoryAxis)dollarPlot.getDomainAxis();
	    dollarXAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	    ChartPanel dollarChartPanel = new ChartPanel( dollarBarChart );
	    dollarChartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );
	    
	    JFrame dollarChartFrame = new JFrame();
	    dollarChartFrame.setContentPane(dollarChartPanel);
	    dollarChartFrame.setTitle("Viewing Sales by Dollar for: " + dateOfCurrentReport);
	    dollarChartFrame.pack();
	    dollarChartFrame.setLocationRelativeTo(null);

	    dollarChartFrame.setVisible(true);
			break;

		case "volume" :
			DefaultCategoryDataset volumeChartData = DatasetConverter.convertSingleDayVolume(dollarSalesData);

			JFreeChart volumeBarChart = ChartFactory.createBarChart(
					"",
					"Hour","No. of Transactions",
					volumeChartData,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot volumePlot = (CategoryPlot)volumeBarChart.getPlot();
			volumePlot.setDomainGridlinesVisible(true);
			CategoryAxis volumeXAxis = (CategoryAxis)volumePlot.getDomainAxis();
			volumeXAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			ChartPanel volumeChartPanel = new ChartPanel( volumeBarChart );
			volumeChartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );

			JFrame volumeChartFrame = new JFrame();
			volumeChartFrame.setContentPane(volumeChartPanel);
			volumeChartFrame.setTitle("Viewing Sales by Volume for: " + dateOfCurrentReport);
			volumeChartFrame.pack();
			volumeChartFrame.setLocationRelativeTo(null);

			volumeChartFrame.setVisible(true);
			break;
		case "profit" :
			DefaultCategoryDataset grossProfitChartData = DatasetConverter.convertSingleDayGrossProfit(grossProfitSalesData);

			JFreeChart grossProfitLineChart = ChartFactory.createBarChart(
					"",
					"Hour","GP Amount ($)",
					grossProfitChartData,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot grossProfitPlot = (CategoryPlot)grossProfitLineChart.getPlot();
			grossProfitPlot.setDomainGridlinesVisible(true);
			CategoryAxis grossProfitXAxis = (CategoryAxis)grossProfitPlot.getDomainAxis();
			grossProfitXAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			ChartPanel grossProfitChartPanel = new ChartPanel( grossProfitLineChart );
			grossProfitChartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );

			JFrame grossProfitChartFrame = new JFrame();
			grossProfitChartFrame.setContentPane(grossProfitChartPanel);
			grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: " + dateOfCurrentReport);
			grossProfitChartFrame.setLocationRelativeTo(null);
			grossProfitChartFrame.pack();

			grossProfitChartFrame.setVisible(true);
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
