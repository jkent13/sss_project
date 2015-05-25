package sss.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class ReportController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public ReportController() {
		
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
	
	public void getResults(String inputDateString) {

		try {
			Date inputDate = dateFormat.parse(inputDateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inputDate);
			calendar.add(Calendar.DATE, 1);

			String endDate = sqlDateFormat.format(calendar.getTime()); // Convert to MySQL date string
			String startDate = sqlDateFormat.format(inputDate); // Convert to MySQL date string

			System.out.println("VIEW DATE: " + startDate); // For Testing
			System.out.println("END DATE: " + endDate); // For Testing
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
		}


	}
}
