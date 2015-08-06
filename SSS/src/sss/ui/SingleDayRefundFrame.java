/*
 * SingleDayRefundFrame Class
 * Frame design to support 'Single Day Refund' function
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sss.domain.NonEditableTableModel;
import sss.domain.SingleDayRefundController;


@SuppressWarnings("serial")
public class SingleDayRefundFrame extends JFrame {

	private String reportType = "refundDollar";
	private String viewType = "summary";
	
	private SingleDayRefundController controller = new SingleDayRefundController();
	
	public SingleDayRefundFrame()
	{
		
		//-------------------Frame Details--------------------

		setTitle("Single Day Refund Report");
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

		NonEditableTableModel dataModel = controller.getDataModel();
		JTable lookUpTable = new JTable(dataModel);
		lookUpTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row of table able to be selected at a time
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		leftPanel.add(scrlPane);
		
		//--------------------Date Panels--------------------

		JPanel datePanel = new JPanel();
		TitledBorder datePanelTitle = new TitledBorder("Report Type:");
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
		reportTypePanel.setLayout(new GridLayout(2,1,10,10));
		rightPanel.add(reportTypePanel);

		JRadioButton refundsByDollarRadioButton = new JRadioButton("Refunds by Dollar", true);
		JRadioButton refundsByVolumeRadioButton = new JRadioButton("Refunds by Volume", false);
		
		ButtonGroup myGroup = new ButtonGroup();
		
		refundsByDollarRadioButton.setActionCommand("refundDollar");
		refundsByVolumeRadioButton.setActionCommand("refundVolume");

		myGroup.add(refundsByDollarRadioButton);
		myGroup.add(refundsByVolumeRadioButton);

		reportTypePanel.add(refundsByDollarRadioButton);
		reportTypePanel.add(refundsByVolumeRadioButton);

		//--------------------Show As Panel--------------------
		// the type of graph view for the records are held in this panel.

		JPanel showAsPanel = new JPanel();
		TitledBorder shownAsPanelTitle = new TitledBorder("Show as:");
		showAsPanel.setBorder(shownAsPanelTitle);
		showAsPanel.setLayout(new GridLayout(1,1,10,10));
		rightPanel.add(showAsPanel);

		JButton barGraphButton = new JButton(new ImageIcon("reportMenuIcons/BarGraphIcon2.png"));
		ImageIcon barGraphButtonHover = new ImageIcon("reportMenuIcons/BarGraphIcon3.png");
		barGraphButton.setBorderPainted(false);
		barGraphButton.setRolloverIcon(barGraphButtonHover);
		barGraphButton.setRolloverEnabled(true);
		barGraphButton.setFocusPainted(false);
		barGraphButton.setContentAreaFilled(false);
		showAsPanel.add(barGraphButton);

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

		// Report Type Changers
		refundsByDollarRadioButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				reportType = ae.getActionCommand();
				controller.switchView(reportType, viewType);
			}
				
		});
		
		refundsByVolumeRadioButton.addActionListener(new ActionListener()
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
					} // End else
				} // End if
			} // End method
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
						} // End else
					} // End if
				} // End if
			} // End method
		});

		barGraphButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.showBarChart(reportType);
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
		viewDate.requestFocusInWindow();
		
	}
}
