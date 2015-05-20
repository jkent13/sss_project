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


@SuppressWarnings("serial")
public class SalesReportsMenuFrame extends JFrame {

	public SalesReportsMenuFrame()
	{

		setTitle("Sales Reports");
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

		JLabel salesReportsLabel = new JLabel("Sales Reports");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		salesReportsLabel.setFont(myFont);
		topPanel.add(salesReportsLabel);

		JPanel bottomPanel = new JPanel();
		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
		bottomPanel.setBorder(bottomPanelTitle);
		bottomPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(bottomPanel);


		JButton singleDaySaleButton = new JButton("Single Day Report");
		bottomPanel.add(singleDaySaleButton);

		JButton timePeriodSaleButton = new JButton("Time-Period Report");
		bottomPanel.add(timePeriodSaleButton);

		setVisible(true);

		singleDaySaleButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame singleDaySaleReportUi = new SingleDaySaleFrame();
			}
		});
		
		timePeriodSaleButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				JFrame timePeriodSaleReportUi = new TimePeriodSaleFrame();
			}
		});
	
	}
}
