/* WatchProductFrame Class
 * 
 * Shows a user a table of products from the database, allowing them 
 * to select one to add to the watch list (on the Dashboard)
 * 
 * Original Author: Josh Kent
 */

package sss.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import sss.domain.NonEditableTableModel;
import sss.domain.DashboardController;

@SuppressWarnings("serial")
public class WatchProductFrame extends JFrame {

	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private JTable productDisplayTable;
	private NonEditableTableModel tableModel;
	
	private DashboardController controller;
	private WatchedProduct product;
	
	private int buttonNo;


	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public WatchProductFrame(NonEditableTableModel productData, DashboardController dc, int buttonNumber) {
		tableModel = productData;
		this.controller = dc;
		buttonNo = buttonNumber;

		// Set up frame
		setTitle("Choose new product to watch");
		setPreferredSize(new Dimension(850, 500));
		setMinimumSize(new Dimension(850, 500));
		setMaximumSize(new Dimension(850, 500));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanelSouth = new JPanel(new GridLayout(1, 3, 5, 5));
		JPanel tablePanel = new JPanel(new BorderLayout());

		JButton selectButton = new JButton("Select");
		JButton cancelButton = new JButton("Cancel");

		buttonPanelSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonPanelSouth.add(selectButton);
		buttonPanelSouth.add(cancelButton);

		productDisplayTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(productDisplayTable);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		add(tablePanel, BorderLayout.CENTER);
		add(buttonPanelSouth, BorderLayout.SOUTH);



		// ========================================================================
		// Define Listeners (in Constructor)
		// ========================================================================



		selectButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) 
			{
				if(productDisplayTable.getSelectedRow() != -1) {
					String code = (String) tableModel.getValueAt(productDisplayTable.getSelectedRow(), 0);
					String name = (String) tableModel.getValueAt(productDisplayTable.getSelectedRow(), 1);
					int quantity = (int) tableModel.getValueAt(productDisplayTable.getSelectedRow(), 4);
					product = new WatchedProduct(code, quantity, buttonNo);
					product.setCurrentQuantity(quantity);
					product.setName(name);
					switch(buttonNo) {
					case 1:
						controller.setWatchedProductOne(product);
						break;
					case 2:
						controller.setWatchedProductTwo(product);
						break;
					case 3: 
						controller.setWatchedProductThree(product);
						break;
					}
					dispose();
				}
			}
		});

		cancelButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});

		setVisible(true);
	}

}
