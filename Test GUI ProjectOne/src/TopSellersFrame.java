/*
 * TopSellersFrame Class
 * Frame design to support 'Top Sellers' function
 * Original Author: Amethyst Mayer
 */

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class TopSellersFrame extends JFrame {

	public TopSellersFrame()
	{
		//-------------------Frame Details--------------------

		setTitle("Top Sellers Report");
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
		rightPanel.setLayout(new GridLayout(4,1,10,10));
		fullScreenPanel.add(rightPanel);

		String[] colNames = {"Product id","Barcode","Name","Category","Sale Price"};
		Object[][] data = {
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"}
		};

		JTable lookUpTable = new JTable(data, colNames);
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		leftPanel.add(scrlPane);
		
		//--------------------Date Panels--------------------

		JPanel datePanel = new JPanel();
		TitledBorder datePanelTitle = new TitledBorder("Select Date:");
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
		
		//--------------------Units Entry Panel--------------------

		JPanel labelPanel = new JPanel();
		TitledBorder labelPanelTitle = new TitledBorder("Report Type:");
		labelPanel.setBorder(labelPanelTitle);
		labelPanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(labelPanel);

		JLabel soldUnitsLabel = new JLabel("Number of Top");
		JLabel soldUnitsLabel2 = new JLabel("Products:");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		soldUnitsLabel.setFont(myFont);
		soldUnitsLabel2.setFont(myFont);
		labelPanel.add(soldUnitsLabel);
		labelPanel.add(soldUnitsLabel2);

		JPanel inputUnitsPanel = new JPanel();
		TitledBorder inputUnitsPanelTitle = new TitledBorder("Input:");
		inputUnitsPanel.setBorder(inputUnitsPanelTitle);
		inputUnitsPanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(inputUnitsPanel);

		JLabel unitsInputLabel = new JLabel("Input:");
		JTextField units = new JTextField("");
		JLabel unitsInputExample = new JLabel("e.g. 10");
		inputUnitsPanel.add(unitsInputLabel);
		inputUnitsPanel.add(units);
		inputUnitsPanel.add(unitsInputExample);

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

		getResultsButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
//				myFrame.dispose();
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
