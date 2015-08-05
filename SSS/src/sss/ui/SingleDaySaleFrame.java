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
public class SingleDaySaleFrame extends JFrame {
	
	private String reportType = "dollar";
	private String viewType = "summary";
	
	private ReportController controller = new ReportController();
	
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
		TitledBorder leftPanelTitle = new TitledBorder("Sale Data:");
		leftPanel.setBorder(leftPanelTitle);
		leftPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(4,1,10,10));
		fullScreenPanel.add(rightPanel);
		
		NonEditableTableModel dataModel = controller.getDataModel();
		JTable lookUpTable = new JTable(dataModel);
		lookUpTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row of table able to be selected at a time
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
		
		JPanel reportPanel = new JPanel();
		reportPanel.setLayout(new GridLayout(1,2,10,10));
		rightPanel.add(reportPanel);
		
		JPanel reportTypePanel = new JPanel();
		TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
		reportTypePanel.setBorder(reportTypePanelTitle);
		reportTypePanel.setLayout(new GridLayout(3,1,10,10));
		reportPanel.add(reportTypePanel);

		JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
		JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
		JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
		ButtonGroup myGroup = new ButtonGroup();
		
		salesByDollar.setActionCommand("dollar");
		salesByVolume.setActionCommand("volume");
		profitByDollar.setActionCommand("profit");
		
		myGroup.add(salesByDollar);
		myGroup.add(salesByVolume);
		myGroup.add(profitByDollar);

		JPanel viewPanel = new JPanel();
		TitledBorder viewPanelTitle = new TitledBorder("View Type:");
		viewPanel.setBorder(viewPanelTitle);
		viewPanel.setLayout(new GridLayout(2,1,10,10));
		reportPanel.add(viewPanel);
		
		JRadioButton allView = new JRadioButton("All Sales View", false);
		JRadioButton summView = new JRadioButton("Hour Summary View", true);
		ButtonGroup myView = new ButtonGroup();
		
		allView.setActionCommand("all");
		summView.setActionCommand("summary");
		
		myView.add(allView);
		myView.add(summView);
		
		reportTypePanel.add(salesByDollar);
		reportTypePanel.add(salesByVolume);
		reportTypePanel.add(profitByDollar);
		
		viewPanel.add(allView);
		viewPanel.add(summView);

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

		// View Changers
		allView.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				viewType = ae.getActionCommand();
				controller.switchView(reportType, viewType);
			}
				
		});
		
		summView.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				viewType = ae.getActionCommand();
				controller.switchView(reportType, viewType);
			}
				
		});
		
		// Report Type Changers
		salesByDollar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				reportType = ae.getActionCommand();
				controller.switchView(reportType, viewType);
			}
				
		});
		
		salesByVolume.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				reportType = ae.getActionCommand();
				controller.switchView(reportType, viewType);
			}
				
		});
		
		profitByDollar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				reportType = ae.getActionCommand();
				controller.switchView(reportType, viewType);
			}
				
		});
		
		
		// Get Results button
		getResultsButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				if(viewDate.getText() != null){
					
					String inputDateString = viewDate.getText();
					
					if(!controller.isValidDate(inputDateString)) {
						viewDate.setText("");
					}
					else {
						controller.getResults(inputDateString);
						controller.switchView(reportType, viewType);
					}
				}
			}
				
		});

		// Pressing Enter key on View Date textbox
		viewDate.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(viewDate.getText() != null) {
						String inputDateString = viewDate.getText();
						
						if(!controller.isValidDate(inputDateString)) {
							viewDate.setText("");
						}
						else {
							controller.getResults(inputDateString);
							controller.switchView(reportType, viewType);
						}
					}
				}
			}
		});
		
		// Back Button
		backButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				dispose();
			}
		});


		lineGraph.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.showLineChart(reportType);
			}
		});
		
		barGraph.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.showBarChart(reportType);
			}
		});
		
		setVisible(true);
		viewDate.requestFocusInWindow();
		
	}// End constructor
}// End class