import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class RefundReportsMenuFrame extends JFrame {

	public RefundReportsMenuFrame()
	{

		setTitle("Refund Reports");
		//			myFrame.setSize(900,500);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		//Parameters: numRows, numColumns, Hgap, Vgap
		setLocationRelativeTo(null);

		//Full Screen Panel
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
		add(fullScreenPanel);


		//SECTION PANELS

		JPanel topPanel = new JPanel();
		TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
		topPanel.setBorder(topPanelTitle);
		topPanel.setLayout(new GridLayout(3,2,10,10));
		fullScreenPanel.add(topPanel);

		JLabel inventoryLabel = new JLabel("Refund Reports");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		inventoryLabel.setFont(myFont);
		topPanel.add(inventoryLabel);

		JPanel bottomPanel = new JPanel();
		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
		bottomPanel.setBorder(bottomPanelTitle);
		bottomPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(bottomPanel);


		JButton singleDayRefundButton = new JButton("Single Day Report");
		bottomPanel.add(singleDayRefundButton);

		JButton timePeriodRefundButton = new JButton("Time-Period Report");
		bottomPanel.add(timePeriodRefundButton);

		setVisible(true);
		
		singleDayRefundButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame singleDayRefundReportUi = new SingleDayRefundFrame();
			}
		});
		
		timePeriodRefundButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame timePeriodRefundReportUi = new TimePeriodRefundFrame();
			}
		});
		
	
	}
}
