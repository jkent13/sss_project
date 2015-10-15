/*
 * TopSellersFrame Class
 * Frame design to support 'Top Sellers' function
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
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sss.domain.TopSellerFilter;
import sss.domain.TopSellersReportController;


@SuppressWarnings("serial")
public class TopSellersFrame extends JFrame {

	private TopSellersReportController controller = new TopSellersReportController();
	private TopSellerFilter filter = new TopSellerFilter();
	
	private JTextField startDateTextField = new JTextField();
	private JTextField endDateTextField = new JTextField();
	private JTextField limitTextField = new JTextField();
	
	public TopSellersFrame()
	{
		//-------------------Frame Details--------------------

		setTitle("Top Sellers Report");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		//----------------------Load Image Resources-----------------------
		
		URL barGraphIconUrl = Main.class.getResource("/BarGraphIcon2.png");
		URL barGraphIconHoverUrl = Main.class.getResource("/BarGraphIcon3.png");

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
		TitledBorder labelPanelTitle = new TitledBorder("Number of Top Products:");
		labelPanel.setBorder(labelPanelTitle);
		Font myFont = new Font("SansSerif", Font.BOLD, 12);
		labelPanel.setLayout(new GridLayout(3,1,10,10));
		labelPanelTitle.setTitleFont(myFont);
		rightPanel.add(labelPanel);

		JPanel inputUnitsPanel = new JPanel();
		TitledBorder inputUnitsPanelTitle = new TitledBorder("Shown as:");
		inputUnitsPanel.setBorder(inputUnitsPanelTitle);
		inputUnitsPanel.setLayout(new GridLayout(1,1,10,10));
		rightPanel.add(inputUnitsPanel);

		JLabel unitsInputLabel = new JLabel("");

		JLabel unitsInputExample = new JLabel("e.g. 10");
		
		JButton barGraph = new JButton(new ImageIcon(barGraphIconUrl));
		ImageIcon barGraphButtonHover = new ImageIcon(barGraphIconHoverUrl);
		barGraph.setBorderPainted(false);
		barGraph.setRolloverIcon(barGraphButtonHover);
		barGraph.setRolloverEnabled(true);
		barGraph.setFocusPainted(false);
		barGraph.setContentAreaFilled(false);
		inputUnitsPanel.add(barGraph);
		
		
		labelPanel.add(unitsInputLabel);
		labelPanel.add(limitTextField);
		labelPanel.add(unitsInputExample);

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

		barGraph.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				controller.showBarChart();
			}
		});
		
		// Get Results button
		getResultsButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				if(buildFilter()) {
					controller.getResults(filter);
				}
			}
		});

		// Pressing Enter key on View Start Date textbox
		startDateTextField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(buildFilter()) {
						controller.getResults(filter);
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
					if(buildFilter()) {
						controller.getResults(filter);
					}
				}
			}
		});
		
		limitTextField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(buildFilter()) {
						controller.getResults(filter);
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
	
	
	
	private boolean isLimitValid() {
		try{
			int limit = Integer.parseInt(limitTextField.getText());
			if(limit > 0) {
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error: Invalid input for the limit. Please make sure the value is numerical and > 0", "Invalid Limit", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Error: Invalid input for the limit. Please make sure the value is numerical and > 0", "Invalid Limit", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}



	private boolean buildFilter() {
		if (!isDateFieldNull()) {
			if (areDatesValid() && isLimitValid()) {
				filter.setStartDate(startDateTextField.getText());
				filter.setEndDate(endDateTextField.getText());
				filter.setLimit(Integer.parseInt(limitTextField.getText()));
				return true;
			}
		}
		return false;
	}
	
}