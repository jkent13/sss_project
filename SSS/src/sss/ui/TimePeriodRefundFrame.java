/*
 * TimePeriodRefundFrame Class
 * Frame design to support 'Time-Period Refund' function
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class TimePeriodRefundFrame extends JFrame {

	public TimePeriodRefundFrame()
	{

		//-------------------Frame Details--------------------

		setTitle("Time-Period Refund Report");
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
				{"DGKF353","4256985216","Cat","Pet","$40"}
		};

		JTable lookUpTable = new JTable(data, colNames);
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		leftPanel.add(scrlPane);
		
		//--------------------Date Panels--------------------

		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(1,2,10,10));
		
		JPanel rightDatePanel = new JPanel();
		rightDatePanel.setLayout(new GridLayout(3,1,10,10));
		
		JPanel leftDatePanel = new JPanel();
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

		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(1,2,10,10));
		rightPanel.add(radioPanel);
		
		JPanel reportTypePanel = new JPanel();
		TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
		reportTypePanel.setBorder(reportTypePanelTitle);
		reportTypePanel.setLayout(new GridLayout(3,1,10,10));
		radioPanel.add(reportTypePanel);

		JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
		JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
		JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
		ButtonGroup reportTypeGroup = new ButtonGroup();

		reportTypeGroup.add(salesByDollar);
		reportTypeGroup.add(salesByVolume);
		reportTypeGroup.add(profitByDollar);

		reportTypePanel.add(salesByDollar);
		reportTypePanel.add(salesByVolume);
		reportTypePanel.add(profitByDollar);

		//--------------------Group By Panel--------------------
		// the group by (day, week, month) radio buttons are held in this panel.

		JPanel groupTypePanel = new JPanel();
		TitledBorder groupTypePanelTitle = new TitledBorder("Group by:");
		groupTypePanel.setBorder(groupTypePanelTitle);
		groupTypePanel.setLayout(new GridLayout(3,1,10,10));
		radioPanel.add(groupTypePanel);

		JRadioButton dayRadio = new JRadioButton("Day", true);
		JRadioButton weekRadio = new JRadioButton("Week", false);
		JRadioButton monthRadio = new JRadioButton("Month", false);
		ButtonGroup groupTypeGroup = new ButtonGroup();

		groupTypeGroup.add(dayRadio);
		groupTypeGroup.add(weekRadio);
		groupTypeGroup.add(monthRadio);

		groupTypePanel.add(dayRadio);
		groupTypePanel.add(weekRadio);
		groupTypePanel.add(monthRadio);
		
		//--------------------Shown As Panel--------------------
		// the type of graph view for the records are held in this panel.

		JPanel shownAsPanel = new JPanel();
		TitledBorder shownAsPanelTitle = new TitledBorder("Shown as:");
		shownAsPanel.setBorder(shownAsPanelTitle);
		shownAsPanel.setLayout(new GridLayout(1,2,10,10));
		rightPanel.add(shownAsPanel);

		JButton barGraph = new JButton(new ImageIcon("reportMenuIcons/BarGraphIcon2.png"));
		ImageIcon barGraphButtonHover = new ImageIcon("reportMenuIcons/BarGraphIcon3.png");
		barGraph.setBorderPainted(false);
		barGraph.setRolloverIcon(barGraphButtonHover);
		barGraph.setRolloverEnabled(true);
		barGraph.setFocusPainted(false);
		barGraph.setContentAreaFilled(false);
		shownAsPanel.add(barGraph);

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
