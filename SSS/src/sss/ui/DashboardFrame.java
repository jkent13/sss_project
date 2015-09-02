/*
 * DashboardFrame Class
 * 
 * Window design to provide lots of useful information at a glance.
 * 
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
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

import sss.domain.DashboardController;

@SuppressWarnings("serial")
public class DashboardFrame extends JFrame {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private ChartPanel cp;
	private JPanel middlePanel = new JPanel();
	private JPanel dashLogoPanel = new JPanel();
	
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
	
	private JTextPane eventFeedPane;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public DashboardFrame()
	{
		controller = new DashboardController(this);
		watchedProductOne = controller.getWatchedProductOne();
		watchedProductTwo = controller.getWatchedProductTwo();
		watchedProductThree = controller.getWatchedProductThree();
		eventFeedPane = controller.getEventFeedPane();
		
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

			Font myFont = new Font("SansSerif",Font.BOLD, 28);
			
			JPanel leftParentPanel = new JPanel();
//			TitledBorder middlePanelTitle = new TitledBorder("Middle Panel:");
//			middlePanel.setBorder(middlePanelTitle);
			leftParentPanel.setLayout(new GridLayout(1,1,10,10));
			fullScreenPanel.add(leftParentPanel);
			
			JPanel watchListPanel = new JPanel();
			watchListPanel.setLayout(new BorderLayout());
			leftParentPanel.add(watchListPanel);
			JLabel watchListLabel = new JLabel("Item Watch List:");
			watchListLabel.setFont(myFont);
			watchListPanel.add(watchListLabel, BorderLayout.NORTH);
			
//			TitledBorder leftPanelTitle = new TitledBorder("Left Panel:");
//			leftPanel.setBorder(leftPanelTitle);
//			leftPanel.setLayout(new BorderLayout());
			middlePanel.setLayout(new GridLayout(2,1,10,10));
			fullScreenPanel.add(middlePanel);
			

			dashLogoPanel.setLayout(new GridLayout(1,1,10,10));
			JLabel dashboardTextLabel = new JLabel(new ImageIcon(dashboardLogoUrl));
			dashLogoPanel.add(dashboardTextLabel);
			middlePanel.add(dashLogoPanel);
			
			cp = new ChartPanel(null);
			
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
			
			watchListPanel.add(barGraphPanel);
			watchListPanel.add(barGraphButtonsPanel, BorderLayout.SOUTH);
			
			
			JPanel rightPanel = new JPanel();
//			TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
//			rightPanel.setBorder(rightPanelTitle);
			rightPanel.setLayout(new GridLayout(1,1,10,10));
			fullScreenPanel.add(rightPanel);
			
			JPanel eventFeedPanel = new JPanel();
			eventFeedPanel.setLayout(new BorderLayout());
			rightPanel.add(eventFeedPanel);
			JLabel eventFeedLabel = new JLabel("Event Feed:");
			eventFeedLabel.setFont(myFont);
			eventFeedPanel.add(eventFeedLabel, BorderLayout.NORTH);

			JScrollPane scrlPane = new JScrollPane(eventFeedPane);
			eventFeedPanel.add(scrlPane, BorderLayout.CENTER);
			
			// ======================================================================
			// Event Handlers
			// ======================================================================
			
			
			
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
			
			
			// ======================================================================
			// Window Closing
			// ======================================================================
			
			
			
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					controller.shutdown();
				}
			});
			
			
			
			// Set Visible
			SwingUtilities.invokeLater(new Runnable() {
		  	public void run() {
		      setVisible(true);
		   }
		});
	}
	
	
	
	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
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
	
	
	
	// Called by event handlers
	public void getProductData(int buttonNo) {
		controller.getProductData(buttonNo);
	}
	
	
	
	// ==========================================================================
	// Update/Refresh Methods
	// ==========================================================================
	
	
	
	public void updateChart(ChartPanel panel) {
		middlePanel.remove(cp);
		cp = panel;
		middlePanel.add(cp);
		middlePanel.revalidate();
	}
	

	
	public void refreshWatchedProducts() {
		barGraphPanel.revalidate();
		barGraphPanel.repaint();
	}
	
}