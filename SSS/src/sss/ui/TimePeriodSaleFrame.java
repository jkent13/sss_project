/*
 * TimePeriodSaleFrame Class
 * Frame design to support 'Time-Period Sale' function
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import sss.domain.NonEditableTableModel;
import sss.domain.ReportController;


@SuppressWarnings("serial")
public class TimePeriodSaleFrame extends JFrame {
	
	private String reportType = "dollar";
	private String groupBy = "day";
	
	private ReportController controller = new ReportController();

	public TimePeriodSaleFrame()
	{
		//-------------------Frame Details--------------------

		setTitle("Time-Period Sale Report");
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
		TitledBorder leftPanelTitle = new TitledBorder("Product Inventory:");
		leftPanel.setBorder(leftPanelTitle);
		leftPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
		rightPanel.setBorder(rightPanelTitle);
		rightPanel.setLayout(new GridLayout(5,1,10,10));
		fullScreenPanel.add(rightPanel);

		NonEditableTableModel dataModel = controller.getDataModel();
		JTable lookUpTable = new JTable(dataModel);
		lookUpTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row of table able to be selected at a time
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		leftPanel.add(scrlPane);

		//--------------------Date Panels--------------------

		JPanel datePanel = new JPanel();
		TitledBorder datePanelTitle = new TitledBorder("Time-Period View:");
		datePanel.setBorder(datePanelTitle);
		datePanel.setLayout(new GridLayout(1,2,10,10));


		JPanel rightDatePanel = new JPanel();
		TitledBorder rightDatePanelTitle = new TitledBorder("Select Date:");
		rightDatePanel.setBorder(rightDatePanelTitle);
		rightDatePanel.setLayout(new GridLayout(3,1,10,10));

		JPanel leftDatePanel = new JPanel();
		TitledBorder leftDatePanelTitle = new TitledBorder("Select Date:");
		leftDatePanel.setBorder(leftDatePanelTitle);
		leftDatePanel.setLayout(new GridLayout(3,1,10,10));

		//--------------------Date Panel Fields--------------------

		JTextField viewStartDate = new JTextField();
		JLabel viewStartDateLabel = new JLabel("Start Date:");
		JLabel viewStartDateExample = new JLabel("e.g. 01/03/2014");
		rightDatePanel.add(viewStartDateLabel);
		rightDatePanel.add(viewStartDate);
		rightDatePanel.add(viewStartDateExample);

		JTextField viewEndDate = new JTextField();
		JLabel viewEndDateLabel = new JLabel("End Date:");
		JLabel viewEndDateExample = new JLabel("e.g. 24/03/2014");
		leftDatePanel.add(viewEndDateLabel);
		leftDatePanel.add(viewEndDate);
		leftDatePanel.add(viewEndDateExample);

		datePanel.add(rightDatePanel);
		datePanel.add(leftDatePanel);
		rightPanel.add(datePanel);

		//--------------------Report Panel--------------------
		// the radio button for report type are held in this panel.

		JPanel reportTypePanel = new JPanel();
		TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
		reportTypePanel.setBorder(reportTypePanelTitle);
		reportTypePanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(reportTypePanel);

		JRadioButton dollarRadioButton = new JRadioButton("Sales by Dollar", true);
		JRadioButton volumeRadioButton = new JRadioButton("Sales by Volume", false);
		JRadioButton profitRadioButton = new JRadioButton("Gross profit by Dollar", false);
		ButtonGroup reportTypeGroup = new ButtonGroup();

		dollarRadioButton.setActionCommand("dollar");
		volumeRadioButton.setActionCommand("volume");
		profitRadioButton.setActionCommand("profit");
		
		reportTypeGroup.add(dollarRadioButton);
		reportTypeGroup.add(volumeRadioButton);
		reportTypeGroup.add(profitRadioButton);

		reportTypePanel.add(dollarRadioButton);
		reportTypePanel.add(volumeRadioButton);
		reportTypePanel.add(profitRadioButton);

		//--------------------Group By Panel--------------------
		// the group by (day, week, month) radio buttons are held in this panel.

		JPanel groupTypePanel = new JPanel();
		TitledBorder groupTypePanelTitle = new TitledBorder("Group by:");
		groupTypePanel.setBorder(groupTypePanelTitle);
		groupTypePanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(groupTypePanel);

		JRadioButton dayRadioButton = new JRadioButton("Day", true);
		JRadioButton weekRadioButton = new JRadioButton("Week", false);
		JRadioButton monthRadioButton = new JRadioButton("Month", false);
		ButtonGroup groupTypeGroup = new ButtonGroup();

		dayRadioButton.setActionCommand("day");
		weekRadioButton.setActionCommand("week");
		monthRadioButton.setActionCommand("month");
		
		groupTypeGroup.add(dayRadioButton);
		groupTypeGroup.add(weekRadioButton);
		groupTypeGroup.add(monthRadioButton);

		groupTypePanel.add(dayRadioButton);
		groupTypePanel.add(weekRadioButton);
		groupTypePanel.add(monthRadioButton);

		//--------------------Shown As Panel--------------------
		// the type of graph view for the records are held in this panel.

		JPanel shownAsPanel = new JPanel();
		TitledBorder shownAsPanelTitle = new TitledBorder("Shown as:");
		shownAsPanel.setBorder(shownAsPanelTitle);
		shownAsPanel.setLayout(new GridLayout(1,2,10,10));
		rightPanel.add(shownAsPanel);

		JButton barGraph = new JButton("Bar Graph");
		shownAsPanel.add(barGraph);
		
		JButton lineGraph = new JButton("Line Graph");
		shownAsPanel.add(lineGraph);

		//---------------------Create Buttons---------------------

		JPanel resultsButtonPanel = new JPanel();
		resultsButtonPanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(resultsButtonPanel);

		JLabel blank = new JLabel();
		resultsButtonPanel.add(blank);
		
		JButton getResultsButton = new JButton("Get Results");
		resultsButtonPanel.add(getResultsButton);

		JButton backButton = new JButton("Back");
		resultsButtonPanel.add(backButton);
		
		//---------------------Event Handlers---------------------

	// Report Type Changers
			dollarRadioButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					reportType = ae.getActionCommand();
					controller.switchView(reportType, groupBy);
				}
					
			});
			
			volumeRadioButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					reportType = ae.getActionCommand();
					controller.switchView(reportType, groupBy);
				}
					
			});
			
			profitRadioButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					reportType = ae.getActionCommand();
					controller.switchView(reportType, groupBy);
				}
					
			});
			
			
			// Get Results button
			getResultsButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					boolean isStartNull = viewStartDate.getText() == null;
					boolean isEndNull = viewEndDate.getText() == null;
					if((!isStartNull) && (!isEndNull)) {
						String startDateString = viewStartDate.getText();
						String endDateString = viewEndDate.getText();
						boolean isStartValid = controller.isValidDate(startDateString);
						boolean isEndValid = controller.isValidDate(endDateString);
						if((!isStartValid) || (!isEndValid)) {
							viewStartDate.setText("");
							viewEndDate.setText("");
						}
						else {
							if(controller.isStartDateBeforeEndDate(startDateString, endDateString)) {
								controller.getResults(startDateString, endDateString);
								controller.switchView(reportType, groupBy);
							}
							else {
								viewStartDate.setText("");
								viewEndDate.setText("");
							}
						}
					}
				}
					
			});

			// Pressing Enter key on View Start Date textbox
			viewStartDate.addKeyListener(new KeyAdapter()
			{
					public void keyReleased(KeyEvent e)
					{
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							boolean isStartNull = viewStartDate.getText() == null;
							boolean isEndNull = viewEndDate.getText() == null;
							if((!isStartNull) && (!isEndNull)) {
								String startDateString = viewStartDate.getText();
								String endDateString = viewEndDate.getText();
								boolean isStartValid = controller.isValidDate(startDateString);
								boolean isEndValid = controller.isValidDate(endDateString);
								if((!isStartValid) || (!isEndValid)) {
									viewStartDate.setText("");
									viewEndDate.setText("");
								}
								else {
									if(controller.isStartDateBeforeEndDate(startDateString, endDateString)) {
										controller.getResults(startDateString, endDateString);
										controller.switchView(reportType, groupBy);
									}
									else {
										viewStartDate.setText("");
										viewEndDate.setText("");
									}
								}
							}
						}
					}
				});
			
			// Pressing Enter key on View End Date textbox
			viewEndDate.addKeyListener(new KeyAdapter()
			{
				public void keyReleased(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						boolean isStartNull = viewStartDate.getText() == null;
						boolean isEndNull = viewEndDate.getText() == null;
						if((!isStartNull) && (!isEndNull)) {
							String startDateString = viewStartDate.getText();
							String endDateString = viewEndDate.getText();
							boolean isStartValid = controller.isValidDate(startDateString);
							boolean isEndValid = controller.isValidDate(endDateString);
							if((!isStartValid) || (!isEndValid)) {
								viewStartDate.setText("");
								viewEndDate.setText("");
							}
							else {
								if(controller.isStartDateBeforeEndDate(startDateString, endDateString)) {
									controller.getResults(startDateString, endDateString);
									controller.switchView(reportType, groupBy);
								}
								else {
									viewStartDate.setText("");
									viewEndDate.setText("");
								}
							}
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

}
