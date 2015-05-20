/*
 * ReportsMenuFrame Class
 * Frame design to support the 'Sales Report', 'Refund Report', 'Slow Sellers Report ' and 'Top Sellers Report' functions
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
import javax.swing.border.TitledBorder;


public class ReportsMenuFrame extends JFrame {

	public ReportsMenuFrame()
	{
 
		setTitle("Reports Menu");
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

		JLabel inventoryLabel = new JLabel("Reports");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		inventoryLabel.setFont(myFont);
		topPanel.add(inventoryLabel);

		JPanel bottomPanel = new JPanel();
		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
		bottomPanel.setBorder(bottomPanelTitle);
		bottomPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(bottomPanel);


		JButton salesReportMenuButton = new JButton("Sales Report");
		bottomPanel.add(salesReportMenuButton);

		JButton refundReportMenuButton = new JButton("Refund Report");
		bottomPanel.add(refundReportMenuButton);

		JButton slowSellersButton = new JButton("Slow Sellers Report");
		bottomPanel.add(slowSellersButton);

		JButton topSellersButton = new JButton("Top Seller Report");
		bottomPanel.add(topSellersButton);


		salesReportMenuButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame salesReportsMenuUI = new SalesReportsMenuFrame();
			}
		});


		refundReportMenuButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame refundReportsMenuUI = new RefundReportsMenuFrame();
			}
		});
		
		
		slowSellersButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame slowSellersReportUI = new SlowSellersFrame();
			}
			});
			
		
		topSellersButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame topSellersReportUI = new TopSellersFrame();
			}
			});
	
		setVisible(true);
	}
}
