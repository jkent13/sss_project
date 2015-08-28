/*
 * DashboardFrame Class
 * Window design to provide lots of useful information at a glance.
 * Original Author: Amethyst Mayer
 */

package sss.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;

import sss.domain.WatchedProduct;
import sss.services.FetchSaleDataTask;

@SuppressWarnings("serial")
public class DashboardFrame extends JFrame {
	
	private ChartPanel cp;
	private JPanel leftPanel = new JPanel();
	private JPanel topLeftPanel = new JPanel();
	
	private WatchedProduct watchedProductOne;
	private WatchedProduct watchedProductTwo;
	private WatchedProduct watchedProductThree;
	
	private JPanel barGraphPanel = new JPanel();
	private JButton addProductOneButton = new JButton("Add Product");
	private JButton addProductTwoButton = new JButton("Add Product");
	private JButton addProductThreeButton = new JButton("Add Product");
	
	@SuppressWarnings("unused")
	public DashboardFrame()
	{
		initialise();
		
	//-------------------Frame Details--------------------

			setTitle("Dashboard");
			setExtendedState(Frame.MAXIMIZED_BOTH);
			setLocationRelativeTo(null);

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
			JButton barOneButton = new JButton("Change Product");
			barOneButton.setMargin(new Insets(1,1,1,1));
			barGraphButtonsPanel.add(barOneButton);
			JButton barTwoButton = new JButton("Change Product");
			barTwoButton.setMargin(new Insets(1,1,1,1));
			barGraphButtonsPanel.add(barTwoButton);
			JButton barThreeButton = new JButton("Change Product");
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


			ScheduledExecutorService service = Executors.newScheduledThreadPool(1); 
			try {
				service.scheduleWithFixedDelay(new FetchSaleDataTask(cp, this), 0, 1, TimeUnit.MINUTES);
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error: There was a problem starting the background database reader thread", "SQL Error", JOptionPane.ERROR_MESSAGE);
				dispose();
			}
			catch (RuntimeException re) {
				JOptionPane.showMessageDialog(null, "Error: There was a problem starting the background database reader thread", "SQL Error", JOptionPane.ERROR_MESSAGE);
				dispose();
			}
			System.out.println("Data Fetch Thread Started!");
			
			addProductOneButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					watchedProductOne = new WatchedProduct("ABCD001", 45, 1);
					watchedProductOne.setCurrentQuantity(40);
					barGraphPanel.remove(0);
					barGraphPanel.add(watchedProductOne, 0);
					barGraphPanel.revalidate();
					barGraphPanel.repaint();
				}
			});
			
			addProductTwoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					watchedProductTwo = new WatchedProduct("JKME990", 5, 2);
					watchedProductTwo.setCurrentQuantity(1);
					barGraphPanel.remove(1);
					barGraphPanel.add(watchedProductTwo, 1);
					barGraphPanel.revalidate();
					barGraphPanel.repaint();
				}
			});
			
			addProductThreeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					watchedProductThree = new WatchedProduct("AUIE121", 110, 3);
					watchedProductThree.setCurrentQuantity(57);
					barGraphPanel.remove(2);
					barGraphPanel.add(watchedProductThree, 2);
					barGraphPanel.revalidate();
					barGraphPanel.repaint();
				}
			});
			
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					service.shutdown();
					int i = 0;
					if(watchedProductOne != null) {
						i++;
					}
					if(watchedProductTwo != null) {
						i++;
					}
					if(watchedProductThree != null) {
						i++;
					}
					if(i > 0) {
						try {
							File file = new File("data/dashData.dat");
							ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
							output.writeInt(i);
							if(watchedProductOne != null) {
								output.writeObject(watchedProductOne);
							}
							if(watchedProductTwo != null) {
								output.writeObject(watchedProductTwo);
							}
							if(watchedProductThree != null) {
								output.writeObject(watchedProductThree);
							}
							output.close();
						}
						catch (IOException ioe) {
							JOptionPane.showMessageDialog(null, "Error: the watched products could not be saved", 
									"Could not save watch list", JOptionPane.ERROR_MESSAGE);
							ioe.printStackTrace();
						}
					}
				}
			});
			
			SwingUtilities.invokeLater(new Runnable() {
		  	public void run() {
		      setVisible(true);
		   }
		});
	}
	
	public void updateChart(ChartPanel panel) {
		leftPanel.remove(cp);
		cp = panel;
		leftPanel.add(cp);
		leftPanel.revalidate();
	}
	
	public void initialise() {
		try {
			File dashData = new File("data/dashData.dat");
			if(dashData.exists()) {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(dashData));
				int noObjects = input.readInt();
				WatchedProduct[] watchedProducts = new WatchedProduct[noObjects];
				for(int i = 0; i < noObjects; i++) {
					watchedProducts[i] = (WatchedProduct) input.readObject();
				}
				input.close();
				
				if(watchedProducts[0] != null) {
					watchedProductOne = watchedProducts[0];
				}
				if(watchedProducts[1] != null) {
					watchedProductTwo = watchedProducts[1];
				}
				if(watchedProducts[2] != null) {
					watchedProductThree = watchedProducts[2];
				}
			}
			else {
				watchedProductOne = null;
				watchedProductTwo = null;
				watchedProductThree = null;
			}
			
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Error: could not read dashData.dat", "File Read Error", JOptionPane.ERROR_MESSAGE);
			watchedProductOne = null;
			watchedProductTwo = null;
			watchedProductThree = null;
		}
		catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "Error: could not find WatchedProduct class", "Class Not Found", JOptionPane.ERROR_MESSAGE);
			watchedProductOne = null;
			watchedProductTwo = null;
			watchedProductThree = null;
		}
	}
	
}