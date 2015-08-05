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

import javax.swing.ImageIcon;
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
		fullScreenPanel.setLayout(new GridLayout(3,1,10,10));
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
		middlePanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(middlePanel);
		
		JPanel middlePanelLeft = new JPanel();
		middlePanelLeft.setLayout(new GridLayout(1,1,10,10));
		middlePanel.add(middlePanelLeft);
		
		JPanel middlePanelRight = new JPanel();
		middlePanelRight.setLayout(new GridLayout(1,1,10,10));
		middlePanel.add(middlePanelRight);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(bottomPanel);

		//---------------------Create Buttons---------------------

		JButton singleDaySaleButton = new JButton(new ImageIcon("reportMenuIcons/SingleDaySalesReportIcon2.png"));
		ImageIcon singleDaySaleButtonHover = new ImageIcon("reportMenuIcons/SingleDaySalesReportIconHover2.png");
		singleDaySaleButton.setBorderPainted(false);
		singleDaySaleButton.setRolloverIcon(singleDaySaleButtonHover);
		singleDaySaleButton.setRolloverEnabled(true);
		singleDaySaleButton.setFocusPainted(false);
		singleDaySaleButton.setContentAreaFilled(false);
		middlePanelLeft.add(singleDaySaleButton);

		JButton timePeriodSaleButton = new JButton(new ImageIcon("reportMenuIcons/TimePeriodSalesReportIcon2.png"));
		ImageIcon timePeriodSaleButtonHover = new ImageIcon("reportMenuIcons/TimePeriodSalesReportIconHover2.png");
		timePeriodSaleButton.setBorderPainted(false);
		timePeriodSaleButton.setRolloverIcon(timePeriodSaleButtonHover);
		timePeriodSaleButton.setRolloverEnabled(true);
		timePeriodSaleButton.setFocusPainted(false);
		timePeriodSaleButton.setContentAreaFilled(false);
		middlePanelLeft.add(timePeriodSaleButton);

		JButton singleDayRefundButton = new JButton(new ImageIcon("reportMenuIcons/SingleDayRefundReportIcon2.png"));
		ImageIcon singleDayRefundButtonHover = new ImageIcon("reportMenuIcons/SingleDayRefundReportIconHover2.png");
		singleDayRefundButton.setBorderPainted(false);
		singleDayRefundButton.setRolloverIcon(singleDayRefundButtonHover);
		singleDayRefundButton.setRolloverEnabled(true);
		singleDayRefundButton.setFocusPainted(false);
		singleDayRefundButton.setContentAreaFilled(false);
		middlePanelRight.add(singleDayRefundButton);

		JButton timePeriodRefundButton = new JButton(new ImageIcon("reportMenuIcons/TimePeriodRefundReportIcon2.png"));
		ImageIcon timePeriodRefundButtonHover = new ImageIcon("reportMenuIcons/TimePeriodRefundReportIconHover2.png");
		timePeriodRefundButton.setBorderPainted(false);
		timePeriodRefundButton.setRolloverIcon(timePeriodRefundButtonHover);
		timePeriodRefundButton.setRolloverEnabled(true);
		timePeriodRefundButton.setFocusPainted(false);
		timePeriodRefundButton.setContentAreaFilled(false);
		middlePanelRight.add(timePeriodRefundButton);

		JButton slowSellersButton = new JButton(new ImageIcon("reportMenuIcons/SlowSellersReportIcon.png"));
		ImageIcon slowSellersButtonHover = new ImageIcon("reportMenuIcons/SlowSellersReportIconHover.png");
		slowSellersButton.setBorderPainted(false);
		slowSellersButton.setRolloverIcon(slowSellersButtonHover);
		slowSellersButton.setRolloverEnabled(true);
		slowSellersButton.setFocusPainted(false);
		slowSellersButton.setContentAreaFilled(false);
		bottomPanel.add(slowSellersButton);

		JButton topSellersButton = new JButton(new ImageIcon("reportMenuIcons/TopSellersReportIcon.png"));
		ImageIcon topSellersButtonHover = new ImageIcon("reportMenuIcons/TopSellersReportIconHover.png");
		topSellersButton.setBorderPainted(false);
		topSellersButton.setRolloverIcon(topSellersButtonHover);
		topSellersButton.setRolloverEnabled(true);
		topSellersButton.setFocusPainted(false);
		topSellersButton.setContentAreaFilled(false);
		bottomPanel.add(topSellersButton);

		//---------------------Event Handlers---------------------

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