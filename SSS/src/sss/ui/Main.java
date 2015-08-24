/*
 * MockupMainMenu Class
 * The intial entry point into the application
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import sss.services.DbConnector;

public class Main {

	public static void main(String[] args) {

//-------------------Frame Details--------------------		

		JFrame mainMenuFrame = new JFrame();
		mainMenuFrame.setTitle("Main Menu");
		mainMenuFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainMenuFrame.setLocationRelativeTo(null);
		mainMenuFrame.setUndecorated(false);
		mainMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


//-------------------Full Screen Panel--------------------
		
		JPanel fullScreenPanel = new JPanel();
		fullScreenPanel.setBorder(new EmptyBorder(10,10,10,10));
		fullScreenPanel.setLayout(new GridLayout(3,1,10,10));
		mainMenuFrame.add(fullScreenPanel);


//--------------------Section Panels--------------------

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(topPanel);

		JLabel mainMenuLabel = new JLabel("Simple Sale System V0.75");
		Font myFont = new Font("SansSerif", Font.BOLD, 57);
		mainMenuLabel.setFont(myFont);
		topPanel.add(mainMenuLabel);

		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(middlePanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2,10,10));
		fullScreenPanel.add(bottomPanel);

//---------------------Create Buttons---------------------
		
		JButton salesTransButton = new JButton(new ImageIcon("mainMenuIcons/Point-of-SaleIcon.png"));
		ImageIcon salesTransButtonHover = new ImageIcon("mainMenuIcons/Point-of-SaleIconHover.png");
		salesTransButton.setBorderPainted(false);
		salesTransButton.setRolloverIcon(salesTransButtonHover);
		salesTransButton.setRolloverEnabled(true);
		salesTransButton.setFocusPainted(false);
		salesTransButton.setContentAreaFilled(false);
		middlePanel.add(salesTransButton);
		
		JButton dashboardButton = new JButton(new ImageIcon("mainMenuIcons/DashboardIcon.png"));
		ImageIcon dashboardButtonHover = new ImageIcon("mainMenuIcons/DashboardIconHover.png");
		dashboardButton.setBorderPainted(false);
		dashboardButton.setRolloverIcon(dashboardButtonHover);
		dashboardButton.setRolloverEnabled(true);
		dashboardButton.setFocusPainted(false);
		dashboardButton.setContentAreaFilled(false);
		middlePanel.add(dashboardButton);

		JButton inventoryButton = new JButton(new ImageIcon("mainMenuIcons/InventoryIcon.png"));
		ImageIcon inventoryButtonHover = new ImageIcon("mainMenuIcons/InventoryIconHover.png");
		inventoryButton.setBorderPainted(false);
		inventoryButton.setRolloverIcon(inventoryButtonHover);
		inventoryButton.setRolloverEnabled(true);
		inventoryButton.setFocusPainted(false);
		inventoryButton.setContentAreaFilled(false);
		bottomPanel.add(inventoryButton);

		JButton reportsButton = new JButton(new ImageIcon("mainMenuIcons/ReportsIcon.png"));
		ImageIcon reportsButtonHover = new ImageIcon("mainMenuIcons/ReportsIconHover.png");
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
				new PosFrame();
			}
		});

		dashboardButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame myFrame = new JFrame();
				myFrame.setTitle("Make Sale");
				myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
				myFrame.setLocationRelativeTo(null);
				myFrame.setVisible(true);
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
						mainMenuFrame.dispose();
					} catch (SQLException sqle) {
						JOptionPane.showMessageDialog(null, "Error: The connection to the database could not be closed properly", "DB Connection Error", JOptionPane.ERROR_MESSAGE);
						sqle.printStackTrace();
					}
				}
			}
		});
		
		mainMenuFrame.setVisible(true);
	}
}
