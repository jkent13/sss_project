/*
 * ReportsMenuFrame Class
 * Frame design to support the 'Sales Report', 'Refund Report', 'Slow Sellers Report ' and 'Top Sellers Report' functions
 * Original Author: Amethyst Mayer
 */

package sss.ui;
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
		//-------------------Frame Details--------------------

		setTitle("Reports Menu");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		//-------------------Full Screen Panel--------------------

		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
		add(fullScreenPanel);

		//--------------------Section Panels--------------------

		JPanel topPanel = new JPanel();
		TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
		topPanel.setBorder(topPanelTitle);
		topPanel.setLayout(new GridLayout(3,2,10,10));
		fullScreenPanel.add(topPanel);

		JLabel inventoryLabel = new JLabel("Reports");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		inventoryLabel.setFont(myFont);
		topPanel.add(inventoryLabel);

		JPanel middlePanel = new JPanel();
		TitledBorder middlePanelTitle = new TitledBorder("Middle Panel:");
		middlePanel.setBorder(middlePanelTitle);
		middlePanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(middlePanel);
		
		JPanel bottomPanel = new JPanel();
		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
		bottomPanel.setBorder(bottomPanelTitle);
		bottomPanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(bottomPanel);

		//---------------------Create Buttons---------------------

		JButton salesReportMenuButton = new JButton("Sales Report");
		bottomPanel.add(salesReportMenuButton);

		JButton refundReportMenuButton = new JButton("Refund Report");
		bottomPanel.add(refundReportMenuButton);

		JButton slowSellersButton = new JButton("Slow Sellers Report");
		bottomPanel.add(slowSellersButton);

		JButton topSellersButton = new JButton("Top Seller Report");
		bottomPanel.add(topSellersButton);

		//---------------------Event Handlers---------------------

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
