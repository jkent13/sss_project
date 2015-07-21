/*
 * InventoryMenuFrame Class
 * Frame design to support the 'Add Product', 'Modify Product', 'View Inventory', 'Import CSV' and 'Add Supplier' functions
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class InventoryMenuFrame extends JFrame {

	public InventoryMenuFrame()
	{
		
		//-------------------Frame Details--------------------

		setTitle("Product Inventory");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		//-------------------Full Screen Panel--------------------

		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
		add(fullScreenPanel);

		//--------------------Section Panels--------------------

		JPanel topPanel = new JPanel();		//top panel in the FULL SCREEN Panel
		TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
		topPanel.setBorder(topPanelTitle);
		topPanel.setLayout(new GridLayout(3,2,10,10));
		fullScreenPanel.add(topPanel);

		JLabel inventoryLabel = new JLabel("Product Inventory");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		inventoryLabel.setFont(myFont);
		topPanel.add(inventoryLabel);

		JPanel bottomPanel = new JPanel();		//bottom panel in the FULL SCREEN Panel
		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
		bottomPanel.setBorder(bottomPanelTitle);
		bottomPanel.setLayout(new GridLayout(1,3,10,10));
		fullScreenPanel.add(bottomPanel);

		JPanel bottomLeftPanel = new JPanel();	//left panel in the BOTTOM Panel
		bottomLeftPanel.setLayout(new GridLayout(2,1,10,10));
		fullScreenPanel.add(bottomLeftPanel);
		bottomPanel.add(bottomLeftPanel);

		JPanel bottomMiddlePanel = new JPanel();	//middle panel in the BOTTOM Panel
		bottomMiddlePanel.setLayout(new GridLayout(1,1,10,10));
		bottomPanel.add(bottomMiddlePanel);

		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new GridLayout(2,1,10,10));
		bottomPanel.add(bottomRightPanel);

		//---------------------Create Buttons---------------------
		
		JButton viewStockButton = new JButton("View Inventory Stock");
		bottomMiddlePanel.add(viewStockButton);

		JButton addProductButton = new JButton("Add Product");
		bottomLeftPanel.add(addProductButton);

		JButton modifyProductButton = new JButton("Modify Product");
		bottomLeftPanel.add(modifyProductButton);

		JButton importCSVButton = new JButton("Import CSV");
		bottomRightPanel.add(importCSVButton);

		JButton addSupplierButton = new JButton("Add Supplier");
		bottomRightPanel.add(addSupplierButton);

		//---------------------Event Handlers---------------------

		viewStockButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame viewInventoryUI = new ViewInventoryFrame();
			}
		});

		addProductButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame addProductUI = new AddProductFrame();
			}
		});
		
		modifyProductButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame modifyProductUI = new ModifyProductFrame();
			}
		});
		
		importCSVButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
		addSupplierButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame addSupplierUI = new AddSupplierFrame();				
			}
		});
		
		setVisible(true);
	
	}
}
