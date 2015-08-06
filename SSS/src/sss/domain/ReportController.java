package sss.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public abstract class ReportController {
	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		// Date format used to validate input
	protected SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");	// Date format used to convert input into MySQL DateTime
	
	protected NonEditableTableModel currentTableView = new NonEditableTableModel();
	
	protected abstract void initialise();
	
	protected abstract void showLineChart(String reportType);
	
	protected abstract void showBarChart(String reportType);
	
	public abstract void switchView(String reportType, String viewType);
	
	public NonEditableTableModel getDataModel() {
		return currentTableView;
	}
	
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
}
