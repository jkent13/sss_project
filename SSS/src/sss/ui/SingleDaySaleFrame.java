/*
 * SingleDaySaleFrame Class
 * Frame design to support 'Single Day Sale' function
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class SingleDaySaleFrame extends JFrame {
	
	public SingleDaySaleFrame()
	{
		//-------------------Frame Details--------------------

		setTitle("Single Day Sale Report");
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
		TitledBorder datePanelTitle = new TitledBorder("View Date:");
		datePanel.setBorder(datePanelTitle);
		datePanel.setLayout(new GridLayout(3,2,10,10));
		
		JTextField viewDate = new JTextField();
		JLabel viewDateLabel = new JLabel("View Date:");
		JLabel viewDateExample = new JLabel("e.g. 24/03/2014");
		datePanel.add(viewDateLabel);
		datePanel.add(viewDate);
		datePanel.add(viewDateExample);

		rightPanel.add(datePanel);

		//--------------------Report Panel--------------------
		// the radio button for report type are held in this panel.
		
		JPanel reportTypePanel = new JPanel();
		TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
		reportTypePanel.setBorder(reportTypePanelTitle);
		reportTypePanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(reportTypePanel);

		JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
		JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
		JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
		ButtonGroup myGroup = new ButtonGroup();

		myGroup.add(salesByDollar);
		myGroup.add(salesByVolume);
		myGroup.add(profitByDollar);

		reportTypePanel.add(salesByDollar);
		reportTypePanel.add(salesByVolume);
		reportTypePanel.add(profitByDollar);

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
