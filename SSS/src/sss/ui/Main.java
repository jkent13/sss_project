/*
 * Main Class
 * The initial entry point into the application
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sss.services.DbConnector;
import sss.services.EventWatcher;

public class Main {

	private static JFrame dashboardFrame = null;
	private static JFrame posFrame = null;
	
	public static void main(String[] args) {

//-------------------Frame Details--------------------		

		JFrame mainMenuFrame = new JFrame();
		mainMenuFrame.setTitle("Main Menu");
		mainMenuFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainMenuFrame.setLocationRelativeTo(null);
		mainMenuFrame.setUndecorated(false);
		mainMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

//-------------------Load Image Resources--------------------
		
		URL sssLogoUrl = Main.class.getResource("/SimpleSaleSystemLogo.png");
		URL posIconUrl = Main.class.getResource("/Point-of-SaleIcon.png");
		URL posIconHoverUrl = Main.class.getResource("/Point-of-SaleIconHover.png");
		URL dashboardIconUrl = Main.class.getResource("/DashboardIcon.png");
		URL dashboardIconHoverUrl = Main.class.getResource("/DashboardIconHover.png");
		URL inventoryIconUrl = Main.class.getResource("/InventoryIcon.png");
		URL inventoryIconHoverUrl = Main.class.getResource("/InventoryIconHover.png");
		URL reportIconUrl = Main.class.getResource("/ReportsIcon.png");
		URL reportIconHoverUrl = Main.class.getResource("/ReportsIconHover.png");
		
		
//-------------------Full Screen Panel--------------------
		
		JPanel fullScreenPanel = new JPanel();
		fullScreenPanel.setBorder(new EmptyBorder(10,10,10,10));
		fullScreenPanel.setLayout(new GridLayout(3,1,10,10));
		mainMenuFrame.add(fullScreenPanel);


//--------------------Section Panels--------------------

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(topPanel);

		JLabel mainMenuTextLabel = new JLabel(new ImageIcon(sssLogoUrl));
		topPanel.add(mainMenuTextLabel);

		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(middlePanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(bottomPanel);

//---------------------Create Buttons---------------------


		JButton salesTransButton = new JButton(new ImageIcon(posIconUrl));
		ImageIcon salesTransButtonHover = new ImageIcon(posIconHoverUrl);
		salesTransButton.setBorderPainted(false);
		salesTransButton.setRolloverIcon(salesTransButtonHover);
		salesTransButton.setRolloverEnabled(true);
		salesTransButton.setFocusPainted(false);
		salesTransButton.setContentAreaFilled(false);
		middlePanel.add(salesTransButton);
		
		JButton dashboardButton = new JButton(new ImageIcon(dashboardIconUrl));
		ImageIcon dashboardButtonHover = new ImageIcon(dashboardIconHoverUrl);
		dashboardButton.setBorderPainted(false);
		dashboardButton.setRolloverIcon(dashboardButtonHover);
		dashboardButton.setRolloverEnabled(true);
		dashboardButton.setFocusPainted(false);
		dashboardButton.setContentAreaFilled(false);
		middlePanel.add(dashboardButton);

		JButton inventoryButton = new JButton(new ImageIcon(inventoryIconUrl));
		ImageIcon inventoryButtonHover = new ImageIcon(inventoryIconHoverUrl);
		inventoryButton.setBorderPainted(false);
		inventoryButton.setRolloverIcon(inventoryButtonHover);
		inventoryButton.setRolloverEnabled(true);
		inventoryButton.setFocusPainted(false);
		inventoryButton.setContentAreaFilled(false);
		bottomPanel.add(inventoryButton);

		JButton reportsButton = new JButton(new ImageIcon(reportIconUrl));
		ImageIcon reportsButtonHover = new ImageIcon(reportIconHoverUrl);
		reportsButton.setBorderPainted(false);
		reportsButton.setRolloverIcon(reportsButtonHover);
		reportsButton.setRolloverEnabled(true);
		reportsButton.setFocusPainted(false);
		reportsButton.setContentAreaFilled(false);
		bottomPanel.add(reportsButton);

		
//---------------------Event Handlers---------------------

		salesTransButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(posFrame == null) {
					posFrame = new PosFrame();
				}
				else {
					posFrame.dispose();
					posFrame = new PosFrame();
				}
			}
		});

		dashboardButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(dashboardFrame == null) {
					dashboardFrame = new DashboardFrame();
				}
				else {
					dashboardFrame.dispatchEvent(new WindowEvent(dashboardFrame, WindowEvent.WINDOW_CLOSING));
					dashboardFrame = new DashboardFrame();
				}
			}
		});

		inventoryButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new ViewInventoryFrame();
			}
		});

		reportsButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new ReportsMenuFrame();
			}
		});
		
		// Close window handler (shows confirm dialog)
		mainMenuFrame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{

				int confirm = JOptionPane.showOptionDialog(null, "Are you sure you want to close this window?", "Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == JOptionPane.YES_OPTION) {
					try {
						DbConnector.closeConnection();
						System.out.println("DB connection closed.");
					} catch (SQLException sqle) {
						JOptionPane.showMessageDialog(null, "Error: The connection to the database could not be closed properly", "DB Connection Error", JOptionPane.ERROR_MESSAGE);
						sqle.printStackTrace();
					}
					EventWatcher.getInstance().saveEvents(); // Save event feed before close
					System.exit(0); // Close all windows
				}
			}
		});
		
		mainMenuFrame.setVisible(true);
	}
	
}
