/*
 * DashboardFrame Class
 * Window design to provide lots of useful information at a glance.
 * Original Author: Amethyst Mayer
 */

package sss.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;

import sss.domain.DashboardController;
import sss.domain.WatchedProduct;

@SuppressWarnings("serial")
public class DashboardFrame extends JFrame {
	
	private ChartPanel cp;
	private JPanel leftPanel = new JPanel();
	private JPanel topLeftPanel = new JPanel();
	
	private DashboardController controller;
	
	private WatchedProduct watchedProductOne;
	private WatchedProduct watchedProductTwo;
	private WatchedProduct watchedProductThree;
	
	private JPanel barGraphPanel = new JPanel();
	private JButton addProductOneButton = new JButton("Add Product");
	private JButton addProductTwoButton = new JButton("Add Product");
	private JButton addProductThreeButton = new JButton("Add Product");
	
	private JButton barOneButton = new JButton("Change Product");
	private JButton barTwoButton = new JButton("Change Product");
	private JButton barThreeButton = new JButton("Change Product"); 
	
	@SuppressWarnings("unused")
	public DashboardFrame()
	{
		controller = new DashboardController(this);
		watchedProductOne = controller.getWatchedProductOne();
		watchedProductTwo = controller.getWatchedProductTwo();
		watchedProductThree = controller.getWatchedProductThree();
		
	//-------------------Frame Details--------------------

			setTitle("Dashboard");
			setExtendedState(Frame.MAXIMIZED_BOTH);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	//-------------------Full Screen Panel--------------------
	
			JPanel fullScreenPanel = new JPanel();
			fullScreenPanel.setBorder(new EmptyBorder(10,10,10,10));
			fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
			add(fullScreenPanel);
		
	//-------------------Load Image Resources--------------------
			
			URL dashboardLogoUrl = Main.class.getResource("/DashboardLogo.png");
			
	//--------------------Section Panels-----------------------

			JLabel memo = new JLabel("Live Summary of Today's Sales");
			Font myFont = new Font("SansSerif",Font.BOLD, 28);
			memo.setFont(myFont);
			
			
			JPanel middlePanel = new JPanel();
			TitledBorder middlePanelTitle = new TitledBorder("Middle Panel:");
//			middlePanel.setBorder(middlePanelTitle);
			middlePanel.setLayout(new GridLayout(1,1,10,10));
			fullScreenPanel.add(middlePanel);
			
			JPanel topMiddlePanel = new JPanel();
			topMiddlePanel.setLayout(new GridLayout(1,1,10,10));
			JButton blank = new JButton("button");
//			topMiddlePanel.add(blank);
//			middlePanel.add(topMiddlePanel);
			
			JPanel bottomMiddlePanel = new JPanel();
			bottomMiddlePanel.setLayout(new BorderLayout());
			middlePanel.add(bottomMiddlePanel);
			JLabel watchListLabel = new JLabel("Item Watch List:");
			watchListLabel.setFont(myFont);
			bottomMiddlePanel.add(watchListLabel, BorderLayout.NORTH);
			
			TitledBorder leftPanelTitle = new TitledBorder("Left Panel:");
//			leftPanel.setBorder(leftPanelTitle);
//			leftPanel.setLayout(new BorderLayout());
			leftPanel.setLayout(new GridLayout(2,1,10,10));
			fullScreenPanel.add(leftPanel);
			

			topLeftPanel.setLayout(new GridLayout(1,1,10,10));
			JLabel dashboardTextLabel = new JLabel(new ImageIcon(dashboardLogoUrl));
			topLeftPanel.add(dashboardTextLabel);
			leftPanel.add(topLeftPanel);
			
			JPanel bottomLeftPanel = new JPanel();
			bottomLeftPanel.setLayout(new BorderLayout());
			

//			leftPanel.add(memo, BorderLayout.NORTH);
			
			cp = new ChartPanel(null);
//			bottomLeftPanel.add(cp, BorderLayout.CENTER);
//			leftPanel.add(cp);
			
			barGraphPanel.setLayout(new GridLayout(1,3,10,10));
			if(watchedProductOne != null) {
				barGraphPanel.add(watchedProductOne);
			}
			else {
				barGraphPanel.add(addProductOneButton);
			}
			if(watchedProductTwo != null) {
				barGraphPanel.add(watchedProductTwo);
			}
			else {
				barGraphPanel.add(addProductTwoButton);
			}
			if(watchedProductThree != null) {
				barGraphPanel.add(watchedProductThree);
			}
			else {
				barGraphPanel.add(addProductThreeButton);
			}
			
			JPanel barGraphButtonsPanel = new JPanel();
			barGraphButtonsPanel.setLayout(new GridLayout(1,3,30,10));
			barGraphButtonsPanel.setBorder(new EmptyBorder(5,10,5,10));

			barOneButton.setMargin(new Insets(1,1,1,1));
			barGraphButtonsPanel.add(barOneButton);

			barTwoButton.setMargin(new Insets(1,1,1,1));
			barGraphButtonsPanel.add(barTwoButton);

			barThreeButton.setMargin(new Insets(1,1,1,1));
			barGraphButtonsPanel.add(barThreeButton);
			
			bottomMiddlePanel.add(barGraphPanel);
			bottomMiddlePanel.add(barGraphButtonsPanel, BorderLayout.SOUTH);
			
			
			JPanel rightPanel = new JPanel();
			TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
//			rightPanel.setBorder(rightPanelTitle);
			rightPanel.setLayout(new GridLayout(1,1,10,10));
			fullScreenPanel.add(rightPanel);

			String[] colNames = {"Type","Time","Amount","Name"};
			Object[][] data = {
					{"Sale","4256985216","Cat","Pet"},
					{"Refund","4256985216","Cat","Pet"},
					{"Discount","4256985216","Cat","Pet"},
					{"Refund","4256985216","Cat","Pet"},
					{"Discount","4256985216","Cat","Pet"},
					{"Dsicount","4256985216","Cat","Pet"},
			};

			JTable lookUpTable = new JTable(data, colNames);
			JScrollPane scrlPane = new JScrollPane(lookUpTable);
			rightPanel.add(scrlPane);
			
			
	//--------------------Inside Left Panel-----------------------
			
			barOneButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					getProductData(1);
				}
			});
			
			barTwoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					getProductData(2);
				}
			});
			
			barThreeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					getProductData(3);
				}
			});
			
			addProductOneButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					getProductData(1);
				}
			});
			
			addProductTwoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					getProductData(2);
				}
			});
			
			addProductThreeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					getProductData(3);
				}
			});
			
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					controller.shutdown();
				}
			});
			
			SwingUtilities.invokeLater(new Runnable() {
		  	public void run() {
		      setVisible(true);
		   }
		});
	}
	
	public void getProductData(int button) {
		controller.getProductData(button);
	}
	
	public void updateChart(ChartPanel panel) {
		leftPanel.remove(cp);
		cp = panel;
		leftPanel.add(cp);
		leftPanel.revalidate();
	}
	
	public void setWatchedProductOne(WatchedProduct one) {
		watchedProductOne = one;
		barGraphPanel.remove(0);
		barGraphPanel.add(watchedProductOne, 0);
		barGraphPanel.revalidate();
		barGraphPanel.repaint();
	}
	
	public void setWatchedProductTwo(WatchedProduct two) {
		watchedProductTwo = two;
		barGraphPanel.remove(1);
		barGraphPanel.add(watchedProductTwo, 1);
		barGraphPanel.revalidate();
		barGraphPanel.repaint();
	}
	
	public void setWatchedProductThree(WatchedProduct three) {
		watchedProductThree = three;
		barGraphPanel.remove(2);
		barGraphPanel.add(watchedProductThree, 2);
		barGraphPanel.revalidate();
		barGraphPanel.repaint();
	}
	
	public void refreshWatchedProducts() {
		barGraphPanel.revalidate();
		barGraphPanel.repaint();
	}
}