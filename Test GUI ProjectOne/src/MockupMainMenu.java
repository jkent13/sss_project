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

		JFrame myFrame = new JFrame();
		myFrame.setTitle("Main Menu");
		myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);


		//Full Screen Panel
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
		myFrame.add(fullScreenPanel);


		//SECTION PANELS

		JPanel topPanel = new JPanel();
//		TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
//		topPanel.setBorder(topPanelTitle);
		topPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(topPanel);

		JLabel inventoryLabel = new JLabel("Welcome to the SSS Project");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		inventoryLabel.setFont(myFont);
		topPanel.add(inventoryLabel);

		JPanel bottomPanel = new JPanel();
//		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
//		bottomPanel.setBorder(bottomPanelTitle);
		bottomPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(bottomPanel);


		JButton salesTransButton = new JButton("Sales Transactions");
		bottomPanel.add(salesTransButton);

		JButton dashboardButton = new JButton("Dashboard");
		bottomPanel.add(dashboardButton);

		JButton inventoryButton = new JButton("Inventory");
		bottomPanel.add(inventoryButton);

		JButton reportsButton = new JButton("Reports");
		bottomPanel.add(reportsButton);

		myFrame.setVisible(true);


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




	}
}
