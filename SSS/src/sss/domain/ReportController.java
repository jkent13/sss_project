/* ReportController Class
 * 
 * An abstract class designed to implement and reuse common features of all report controller classes. 
 * All report controller classes must extend this class
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ReportController {
	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");			// Date format used to validate input
	protected SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");	// Date format used to convert input into MySQL DateTime
	
	protected NonEditableTableModel currentTableView = new NonEditableTableModel();
	
	protected abstract void initialise();
	
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
				return false;
			}
			else {
				return true;
			}
		} catch (ParseException e) { // If the inputDateString does not parse, it is not in the correct format
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
				return false;
			}
		} catch (ParseException e) { // If either date string does not parse, it is not in the correct format
			return false;
		}
	}
}
