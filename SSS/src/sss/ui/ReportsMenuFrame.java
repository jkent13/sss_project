/*
 * ReportsMenuFrame Class
 * Frame design to support the 'Sales Report', 'Refund Report', 'Slow Sellers Report ' and 'Top Sellers Report' functions
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ReportsMenuFrame extends JFrame {

	public ReportsMenuFrame()
	{
		//-------------------Frame Details--------------------

		setTitle("Reports Menu");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		//-------------------Load Image Resources--------------------
		
		URL reportsMenuLogoUrl = Main.class.getResource("/ReportsLogo.png");
		URL singleDaySaleReportIconUrl = Main.class.getResource("/SingleDaySalesReportIcon2.png");
		URL singleDaySaleReportIconHoverUrl = Main.class.getResource("/SingleDaySalesReportIconHover2.png");
		URL timePeriodSaleReportIconUrl = Main.class.getResource("/TimePeriodSalesReportIcon2.png");
		URL timePeriodSaleReportIconHoverUrl = Main.class.getResource("/TimePeriodSalesReportIconHover2.png");
		URL singleDayRefundReportIconUrl = Main.class.getResource("/SingleDayRefundReportIcon2.png");
		URL singleRefundReportIconHoverUrl = Main.class.getResource("/SingleDayRefundReportIconHover2.png");
		URL timePeriodRefundReportIconUrl = Main.class.getResource("/TimePeriodRefundReportIcon2.png");
		URL timePeriodRefundReportIconHoverUrl = Main.class.getResource("/TimePeriodRefundReportIconHover2.png");
		URL slowSellerReportIconUrl = Main.class.getResource("/SlowSellersReportIcon.png");
		URL slowSellerReportIconHoverUrl = Main.class.getResource("/SlowSellersReportIconHover.png");
		URL topSellersReportIconUrl = Main.class.getResource("/TopSellersReportIcon.png");
		URL topSellersReportIconHoverUrl = Main.class.getResource("/TopSellersReportIconHover.png");
		
		
		//-------------------Full Screen Panel--------------------

		JPanel fullScreenPanel = new JPanel();
		fullScreenPanel.setLayout(new GridLayout(3,1,10,10));
		add(fullScreenPanel);

		//--------------------Section Panels--------------------

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(topPanel);

		JLabel reportsTextLabel = new JLabel(new ImageIcon(reportsMenuLogoUrl));
		topPanel.add(reportsTextLabel);

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

		JButton singleDaySaleButton = new JButton(new ImageIcon(singleDaySaleReportIconUrl));
		ImageIcon singleDaySaleButtonHover = new ImageIcon(singleDaySaleReportIconHoverUrl);
		singleDaySaleButton.setBorderPainted(false);
		singleDaySaleButton.setRolloverIcon(singleDaySaleButtonHover);
		singleDaySaleButton.setRolloverEnabled(true);
		singleDaySaleButton.setFocusPainted(false);
		singleDaySaleButton.setContentAreaFilled(false);
		middlePanelLeft.add(singleDaySaleButton);

		JButton timePeriodSaleButton = new JButton(new ImageIcon(timePeriodSaleReportIconUrl));
		ImageIcon timePeriodSaleButtonHover = new ImageIcon(timePeriodSaleReportIconHoverUrl);
		timePeriodSaleButton.setBorderPainted(false);
		timePeriodSaleButton.setRolloverIcon(timePeriodSaleButtonHover);
		timePeriodSaleButton.setRolloverEnabled(true);
		timePeriodSaleButton.setFocusPainted(false);
		timePeriodSaleButton.setContentAreaFilled(false);
		middlePanelLeft.add(timePeriodSaleButton);

		JButton singleDayRefundButton = new JButton(new ImageIcon(singleDayRefundReportIconUrl));
		ImageIcon singleDayRefundButtonHover = new ImageIcon(singleRefundReportIconHoverUrl);
		singleDayRefundButton.setBorderPainted(false);
		singleDayRefundButton.setRolloverIcon(singleDayRefundButtonHover);
		singleDayRefundButton.setRolloverEnabled(true);
		singleDayRefundButton.setFocusPainted(false);
		singleDayRefundButton.setContentAreaFilled(false);
		middlePanelRight.add(singleDayRefundButton);

		JButton timePeriodRefundButton = new JButton(new ImageIcon(timePeriodRefundReportIconUrl));
		ImageIcon timePeriodRefundButtonHover = new ImageIcon(timePeriodRefundReportIconHoverUrl);
		timePeriodRefundButton.setBorderPainted(false);
		timePeriodRefundButton.setRolloverIcon(timePeriodRefundButtonHover);
		timePeriodRefundButton.setRolloverEnabled(true);
		timePeriodRefundButton.setFocusPainted(false);
		timePeriodRefundButton.setContentAreaFilled(false);
		middlePanelRight.add(timePeriodRefundButton);

		JButton slowSellersButton = new JButton(new ImageIcon(slowSellerReportIconUrl));
		ImageIcon slowSellersButtonHover = new ImageIcon(slowSellerReportIconHoverUrl);
		slowSellersButton.setBorderPainted(false);
		slowSellersButton.setRolloverIcon(slowSellersButtonHover);
		slowSellersButton.setRolloverEnabled(true);
		slowSellersButton.setFocusPainted(false);
		slowSellersButton.setContentAreaFilled(false);
		bottomPanel.add(slowSellersButton);

		JButton topSellersButton = new JButton(new ImageIcon(topSellersReportIconUrl));
		ImageIcon topSellersButtonHover = new ImageIcon(topSellersReportIconHoverUrl);
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
				new SingleDaySaleFrame();
			}
		});
		
		timePeriodSaleButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				new TimePeriodSaleFrame();
			}
		});
		
		singleDayRefundButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new SingleDayRefundFrame();
			}
		});
		
		timePeriodRefundButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new TimePeriodRefundFrame();
			}
		});
		
		
		slowSellersButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new SlowSellersFrame();
			}
			});
			
		
		topSellersButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new TopSellersFrame();
			}
			});
	
		setVisible(true);
	}
}