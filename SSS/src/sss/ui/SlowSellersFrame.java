/*
 * SlowSellersFrame Class
 * Frame design to support 'Slow Sellers' function
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sss.domain.SlowSellersReportController;


@SuppressWarnings("serial")
public class SlowSellersFrame extends JFrame {
	private SlowSellersReportController controller = new SlowSellersReportController();

	private JTextField unitsTextField = new JTextField();
	private JTextField startDateTextField = new JTextField();
	private JTextField endDateTextField = new JTextField();

	public SlowSellersFrame()
	{

		//-------------------Frame Details--------------------

		setTitle("Slow Sellers Report");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		//-------------------Full Screen Panel--------------------

		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
		add(fullScreenPanel);

		//--------------------Section Panels--------------------

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(4,1,10,10));
		fullScreenPanel.add(rightPanel);

		JTable resultsTable = new JTable(controller.getDataModel());
		resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(resultsTable);
		leftPanel.add(scrollPane);

		//--------------------Date Panels--------------------

		JPanel datePanel = new JPanel();
		TitledBorder datePanelTitle = new TitledBorder("Select Dates:");
		datePanel.setBorder(datePanelTitle);
		datePanel.setLayout(new GridLayout(1,2,10,10));

		JPanel rightDatePanel = new JPanel();
		rightDatePanel.setLayout(new GridLayout(3,1,10,10));

		JPanel leftDatePanel = new JPanel();
		leftDatePanel.setLayout(new GridLayout(3,1,10,10));

		//--------------------Date Panel Fields--------------------

		JLabel viewStartDateLabel = new JLabel("Start Date:");
		JLabel viewStartDateExample = new JLabel("e.g. 01/03/2014");
		rightDatePanel.add(viewStartDateLabel);
		rightDatePanel.add(startDateTextField);
		rightDatePanel.add(viewStartDateExample);


		JLabel viewEndDateLabel = new JLabel("End Date:");
		JLabel viewEndDateExample = new JLabel("e.g. 24/03/2014");
		leftDatePanel.add(viewEndDateLabel);
		leftDatePanel.add(endDateTextField);
		leftDatePanel.add(viewEndDateExample);

		datePanel.add(rightDatePanel);
		datePanel.add(leftDatePanel);
		rightPanel.add(datePanel);

		//--------------------Units Entry Panel--------------------

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(labelPanel);
		TitledBorder inputBorder = new TitledBorder("Product units sold");
		JLabel soldUnitsLabel = new JLabel("Less than or Equal to:");
		Font myFont = new Font("SansSerif", Font.BOLD, 12);
		labelPanel.setBorder(inputBorder);
		soldUnitsLabel.setFont(myFont);
		labelPanel.add(soldUnitsLabel);

		JLabel unitsInputExample = new JLabel("e.g. 10");
		labelPanel.add(unitsTextField);
		labelPanel.add(unitsInputExample);

		JPanel blankPanel = new JPanel();
		blankPanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(blankPanel);

		//---------------------Create Buttons---------------------

		JPanel resultsButtonPanel = new JPanel();
		resultsButtonPanel.setBorder(new EmptyBorder(50,50,50,50));
		resultsButtonPanel.setLayout(new GridLayout(1,2,50,50));
		rightPanel.add(resultsButtonPanel);

		JButton getResultsButton = new JButton("Get Results");
		resultsButtonPanel.add(getResultsButton);

		JButton backButton = new JButton("Back");
		resultsButtonPanel.add(backButton);

		//---------------------Event Handlers---------------------

		getResultsButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(validateAllInput()) {
					String startDate = startDateTextField.getText();
					String endDate = endDateTextField.getText();
					int units = Integer.parseInt(unitsTextField.getText());
					controller.getResults(startDate, endDate, units);
				}
			}
		});

		// Pressing Enter key on View Start Date textbox
		startDateTextField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(validateAllInput()) {
						String startDate = startDateTextField.getText();
						String endDate = endDateTextField.getText();
						int units = Integer.parseInt(unitsTextField.getText());
						controller.getResults(startDate, endDate, units);
					}
				}
			}
		});

		// Pressing Enter key on View End Date textbox
		endDateTextField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(validateAllInput()) {
						String startDate = startDateTextField.getText();
						String endDate = endDateTextField.getText();
						int units = Integer.parseInt(unitsTextField.getText());
						controller.getResults(startDate, endDate, units);
					}
				}
			}
		});

		unitsTextField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(validateAllInput()) {
						String startDate = startDateTextField.getText();
						String endDate = endDateTextField.getText();
						int units = Integer.parseInt(unitsTextField.getText());
						controller.getResults(startDate, endDate, units);
					}
				}
			}
		});

		backButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});


		setVisible(true);
	}

	private boolean isDateFieldNull() {
		boolean isStartNull = startDateTextField.getText().equals("");
		boolean isEndNull = endDateTextField.getText().equals("");
		if(isStartNull || isEndNull) {
			JOptionPane.showMessageDialog(null, "Error: One or both of the date fields are empty!", "Empty Date Field", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else {
			return false;
		}
	}

	private boolean areDatesValid() {
		String startDateString = startDateTextField.getText();
		String endDateString = endDateTextField.getText();
		boolean isStartValid = controller.isValidDate(startDateString);
		boolean isEndValid = controller.isValidDate(endDateString);
		if(isStartValid && isEndValid) {
			boolean isStartBeforeEnd = controller.isStartDateBeforeEndDate(startDateString, endDateString);
			if (isStartBeforeEnd) {
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error: The start date must be before the end date!", "Invalid Date Range", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: Invalid date format! Please enter a date in the format dd/mm/yyyy", "Invalid Date", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private boolean isUnitValid() {
		try{
			int units = Integer.parseInt(unitsTextField.getText());
			if(units > 0) {
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error: Invalid input for the units. Please make sure the value is numerical and > 0", "Invalid Limit", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Error: Invalid input for the units. Please make sure the value is numerical and > 0", "Invalid Limit", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private boolean validateAllInput() {
		if(!isDateFieldNull()) {
			if(isUnitValid() && areDatesValid()) {
				return true;
			}
		}
		return false;
	}
}