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
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	private DatasetConverter() {
	}
	
	
	
	// ==========================================================================
	// Sales Conversion Methods
	// ==========================================================================
	
	
	
	public static DefaultCategoryDataset convertSalesDollarByHour(NonEditableTableModel dataModel) {
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
				else if (j % 2 == 0) {
					Double absValue = Math.abs(Double.valueOf(dbData.getValueAt(i, j).toString()));
					totals.add(absValue);
				}
			}
		}
	
		for(int i = 0; i < totals.size(); i++) {
			chartData.addValue(totals.get(i), "Amount ($)", hours.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertSalesVolumeByHour(NonEditableTableModel dataModel) {
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
	
	
	
	public static DefaultCategoryDataset convertGrossProfitByHour(NonEditableTableModel dataModel) {
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
	
	
	
	public static DefaultCategoryDataset convertSalesDollarByDay(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> totals = new ArrayList<Double>();
		ArrayList<String> days = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if((j % 3) == 0)
					days.add((String)dbData.getValueAt(i, j));
				else if (j % 2 == 0) {
					Double absValue = Math.abs(Double.valueOf(dbData.getValueAt(i, j).toString()));
					totals.add(absValue);
				}
			}
		}
	
		for(int i = 0; i < totals.size(); i++) {
			chartData.addValue(totals.get(i), "Amount ($)", days.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertSalesVolumeByDay(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> sales = new ArrayList<Integer>();
		ArrayList<String> days = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if((j % 3) == 0)
					days.add((String)dbData.getValueAt(i, j));
				else if (j % 2 == 1)
					sales.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < sales.size(); i++) {
			chartData.addValue(sales.get(i), "No. of Sales", days.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertGrossProfitByDay(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> profitTotals = new ArrayList<Double>();
		ArrayList<String> days = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if((j % 3) == 0)
					days.add((String)dbData.getValueAt(i, j));
				else if (j % 2 == 0)
					profitTotals.add(Double.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
	
		for(int i = 0; i < profitTotals.size(); i++) {
			chartData.addValue(profitTotals.get(i), "GP Amount ($)", days.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertSalesDollarByWeek(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> totals = new ArrayList<Double>();
		ArrayList<String> weeks = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					weeks.add((String)dbData.getValueAt(i, j));
				else if (j == 3) {
					Double absValue = Math.abs(Double.valueOf(dbData.getValueAt(i, j).toString()));
					totals.add(absValue);
				}
			}
		}
	
		for(int i = 0; i < totals.size(); i++) {
			chartData.addValue(totals.get(i), "Amount ($)", weeks.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertSalesVolumeByWeek(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> sales = new ArrayList<Integer>();
		ArrayList<String> weeks = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					weeks.add((String)dbData.getValueAt(i, j));
				else if (j == 2)
					sales.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < sales.size(); i++) {
			chartData.addValue(sales.get(i), "No. of Sales", weeks.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertGrossProfitByWeek(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> profitTotals = new ArrayList<Double>();
		ArrayList<String> weeks = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					weeks.add((String)dbData.getValueAt(i, j));
				else if (j == 3)
					profitTotals.add(Double.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
	
		for(int i = 0; i < profitTotals.size(); i++) {
			chartData.addValue(profitTotals.get(i), "GP Amount ($)", weeks.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertSalesDollarByMonth(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> totals = new ArrayList<Double>();
		ArrayList<String> months = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					months.add((String)dbData.getValueAt(i, j));
				else if (j == 3) {
					Double absValue = Math.abs(Double.valueOf(dbData.getValueAt(i, j).toString()));
					totals.add(absValue);
				}
			}
		}
	
		for(int i = 0; i < totals.size(); i++) {
			chartData.addValue(totals.get(i), "Amount ($)", months.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertSalesVolumeByMonth(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> sales = new ArrayList<Integer>();
		ArrayList<String> months = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					months.add((String)dbData.getValueAt(i, j));
				else if (j == 2)
					sales.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < sales.size(); i++) {
			chartData.addValue(sales.get(i), "No. of Sales", months.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertGrossProfitByMonth(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Double> profitTotals = new ArrayList<Double>();
		ArrayList<String> months = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					months.add((String)dbData.getValueAt(i, j));
				else if (j == 3)
					profitTotals.add(Double.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
	
		for(int i = 0; i < profitTotals.size(); i++) {
			chartData.addValue(profitTotals.get(i), "GP Amount ($)", months.get(i));
		}
		return chartData;
	}
	
	
	
	// ==========================================================================
	// Refunds Conversion Methods
	// ==========================================================================
	
	
	
	public static DefaultCategoryDataset convertRefundVolumeByHour(NonEditableTableModel dataModel) {
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

	
	
	public static DefaultCategoryDataset convertRefundVolumeByDay(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> refunds = new ArrayList<Integer>();
		ArrayList<String> days = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					days.add((String)dbData.getValueAt(i, j));
				else if (j == 1)
					refunds.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < refunds.size(); i++) {
			chartData.addValue(refunds.get(i), "No. of Refunds", days.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertRefundVolumeByWeek(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> refunds = new ArrayList<Integer>();
		ArrayList<String> weeks = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					weeks.add((String)dbData.getValueAt(i, j));
				else if (j == 2)
					refunds.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < refunds.size(); i++) {
			chartData.addValue(refunds.get(i), "No. of Refunds", weeks.get(i));
		}
		return chartData;
	}
	
	
	
	public static DefaultCategoryDataset convertRefundVolumeByMonth(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> refunds = new ArrayList<Integer>();
		ArrayList<String> months = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 0)
					months.add((String)dbData.getValueAt(i, j));
				else if (j == 2)
					refunds.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < refunds.size(); i++) {
			chartData.addValue(refunds.get(i), "No. of Refunds", months.get(i));
		}
		return chartData;
	}
	
	
	
	// ==========================================================================
	// Top Seller Conversion
	// ==========================================================================
	
	
	
	public static DefaultCategoryDataset convertTopSeller(NonEditableTableModel dataModel) {
		NonEditableTableModel dbData = dataModel;
		ArrayList<Integer> units = new ArrayList<Integer>();
		ArrayList<String> products = new ArrayList<String>();
		DefaultCategoryDataset chartData = new DefaultCategoryDataset();
		
		int rowCount = dbData.getRowCount();
		int colCount = dbData.getColumnCount();
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				if(j == 2)
					products.add((String)dbData.getValueAt(i, j));
				else if (j == 1)
					units.add(Integer.valueOf(dbData.getValueAt(i, j).toString()));
			}
		}
		
		for(int i = 0; i < units.size(); i++) {
			chartData.addValue(units.get(i), "Units", products.get(i));
		}
		return chartData;
	}
	
}