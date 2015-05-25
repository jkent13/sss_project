/*
 * MockupMainMenu Class
 * The intial entry point into the application
 * Original Author: Amethyst Mayer
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class MockupMainMenu {

	public static void main(String[] args) {

//-------------------Frame Details--------------------		

		JFrame mainMenuFrame = new JFrame();
		mainMenuFrame.setTitle("Main Menu");
		mainMenuFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainMenuFrame.setLocationRelativeTo(null);
		mainMenuFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);


//-------------------Full Screen Panel--------------------
		
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
		mainMenuFrame.add(fullScreenPanel);


//--------------------Section Panels--------------------

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(topPanel);

		JLabel mainMenuLabel = new JLabel("Welcome to the SSS Project");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		mainMenuLabel.setFont(myFont);
		topPanel.add(mainMenuLabel);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(bottomPanel);

//---------------------Create Buttons---------------------
		
		JButton salesTransButton = new JButton("Sales Transactions");
		bottomPanel.add(salesTransButton);

		JButton dashboardButton = new JButton("Dashboard");
		bottomPanel.add(dashboardButton);

		JButton inventoryButton = new JButton("Inventory");
		bottomPanel.add(inventoryButton);

		JButton reportsButton = new JButton("Reports");
		bottomPanel.add(reportsButton);

		
//---------------------Event Handlers---------------------

		salesTransButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame posUI = new PosFrame();
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
				JFrame inventoryMenuUI = new InventoryMenuFrame();
			}
		});

		reportsButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame reportsMenuUI = new ReportsMenuFrame();
			}
		});
		
		mainMenuFrame.setVisible(true);
	}
}
