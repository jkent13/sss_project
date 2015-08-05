/* DatasetConverter Class
 * 
 * Used to convert the Java ResultSet (returned by SQL SELECT queries) into a DataSet usable 
 * by JFreeCharts library in bar and line charts
 * 
 * Original Author: Josh Kent
 */

package sss.services;

import java.util.ArrayList;

import org.jfree.data.category.DefaultCategoryDataset;

import sss.domain.NonEditableTableModel;

public class DatasetConverter {
	
	private DatasetConverter() {
		
	}
	
	public static DefaultCategoryDataset convertSingleDayDollar(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> totals = new ArrayList<Double>();
		ArrayList<String> hours = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if((j % 3) == 0)
					hours.add((String)dbData.getValueAt(i, j));
				else if (j % 2 == 0)
					totals.add(Double.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
	
		for(int i = 0; i < totals.size(); i++) {
			chartData.addValue(totals.get(i), "Amount ($)", hours.get(i));
		}
		return chartData;
	}
	
	public static DefaultCategoryDataset convertSingleDayVolume(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> sales = new ArrayList<Integer>();
		ArrayList<String> hours = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if((j % 3) == 0)
					hours.add((String)dbData.getValueAt(i, j));
				else if (j % 2 == 1)
					sales.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < sales.size(); i++) {
			chartData.addValue(sales.get(i), "No. of Sales", hours.get(i));
		}
		return chartData;
	}
	
	public static DefaultCategoryDataset convertSingleDayGrossProfit(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> profitTotals = new ArrayList<Double>();
		ArrayList<String> hours = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if((j % 3) == 0)
					hours.add((String)dbData.getValueAt(i, j));
				else if (j % 2 == 0)
					profitTotals.add(Double.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
	
		for(int i = 0; i < profitTotals.size(); i++) {
			chartData.addValue(profitTotals.get(i), "GP Amount ($)", hours.get(i));
		}
		return chartData;
	}
	
	public static DefaultCategoryDataset convertSingleDayRefundVolume(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> refunds = new ArrayList<Integer>();
		ArrayList<String> hours = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if((j % 3) == 0)
					hours.add((String)dbData.getValueAt(i, j));
				else if (j % 2 == 1)
					refunds.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < refunds.size(); i++) {
			chartData.addValue(refunds.get(i), "No. of Refunds", hours.get(i));
		}
		return chartData;
	}
	
}
