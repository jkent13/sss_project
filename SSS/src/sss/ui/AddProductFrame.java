/*
 * AddProductFrame Class
 * Frame design to support 'Add Product' function
 * Original Author: Jasmina Pasalic
 */

package sss.ui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AddProductFrame extends JFrame {
	
	public AddProductFrame()
	{
		
//-------------------Frame Details--------------------

		setTitle("Add Product");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
//-------------------Full Screen Panel--------------------
		
		JPanel addProductpanel = new JPanel(new GridLayout (3, 1, 1, 1));
		TitledBorder mainPanelT = new TitledBorder("Add Product");
		addProductpanel.setBorder(mainPanelT);
		add(addProductpanel);
		
//--------------------Section Panels--------------------
		
		JPanel topPanel = new JPanel(new BorderLayout ());
		TitledBorder outPanel = new TitledBorder("Out Panel");
		topPanel.setBorder(outPanel);
		addProductpanel.add(topPanel);
		
		JPanel titlePanel = new JPanel(new GridLayout(1,1,1,1));
		JLabel addProductTitle = new JLabel ("Add Product");
		Font font = new Font("SansSerif", Font.BOLD, 42);
		addProductTitle.setFont(font);
		titlePanel.add(addProductTitle);
		topPanel.add(titlePanel, BorderLayout.NORTH);
		
		JPanel middlePanel = new JPanel(new GridLayout(1, 2, 50, 10));
		addProductpanel.add(middlePanel);
		
		JPanel productPanel = new JPanel(new GridLayout(6, 2, 10, 10));
		TitledBorder productPanelTitle = new TitledBorder("PRODUCT");
		productPanel.setBorder(productPanelTitle);
		middlePanel.add(productPanel);
		
		JPanel pricingPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		TitledBorder pricingPanelTitle = new TitledBorder("Pricing");
		pricingPanel.setBorder(pricingPanelTitle);
		middlePanel.add(pricingPanel);
		
		
//---------------------Create Fields---------------------
		
		JLabel barcodeLabel = new JLabel ("Barcode:");
		JTextField barcodeText = new JTextField ("");
		
		JLabel productCodeLabel = new JLabel ("Product Code:");
		JTextField productCodeText = new JTextField ("");
		
		JLabel nameLabel = new JLabel ("Name:");
		JTextField nameText = new JTextField ("");
		
		JLabel categoryLabel = new JLabel ("Category:");
		JComboBox categoryComboBox = new JComboBox ();
		
		JLabel quantityLabel = new JLabel ("Quantity:");
		JTextField quantityText = new JTextField ("");
		
		JLabel supplierLabel = new JLabel ("Supplier:");
		JComboBox supplierComboBox = new JComboBox ();
		
		productPanel.add(barcodeLabel);
		productPanel.add(barcodeText);
		productPanel.add(productCodeLabel);
		productPanel.add(productCodeText);
		productPanel.add(nameLabel);
		productPanel.add(nameText);
		productPanel.add(categoryLabel);
		productPanel.add(categoryComboBox);
		productPanel.add(quantityLabel);
		productPanel.add(quantityText);
		productPanel.add(supplierLabel);
		productPanel.add(supplierComboBox);
		
		
		JPanel leftPricePanel = new JPanel (new GridLayout(4, 2, 7, 7));
		JPanel rightPricePanel = new JPanel (new GridLayout(2, 2, 7, 7));
		
		JLabel costpriceLabel = new JLabel("Cost Price:");
		JTextField costpriceTextfield = new JTextField ("");
		
		JLabel salepriceLabel = new JLabel("Selling Price:");
		JTextField salepriceTextfield = new JTextField ("");
		
		JLabel gstLabel = new JLabel("GST (10%)");
		JLabel gstLabelinfo = new JLabel("$0");
		
		JLabel lineLabel = new JLabel("_____________________________________________");
		JLabel lineLabel1 = new JLabel("_____________________________________________");
		JLabel profitmarginLabel = new JLabel("Profit Margin:");
		JLabel profitmarginAmount = new JLabel("$ ###");
		
		JLabel profitPercentageLabel = new JLabel("Profit Percentage (%):");
		JLabel profitPercentageAmount = new JLabel("###% ");
		
		leftPricePanel.add(costpriceLabel);
		leftPricePanel.add(costpriceTextfield);
		leftPricePanel.add(salepriceLabel);
		leftPricePanel.add(salepriceTextfield);
		leftPricePanel.add(gstLabel);
		leftPricePanel.add(gstLabelinfo);
		leftPricePanel.add(lineLabel);
		leftPricePanel.add(lineLabel1);		
		rightPricePanel.add(profitmarginLabel);
		rightPricePanel.add(profitmarginAmount);
		rightPricePanel.add(profitPercentageLabel);
		rightPricePanel.add(profitPercentageAmount);
		
		pricingPanel.add(leftPricePanel);
		pricingPanel.add(rightPricePanel);
		
//---------------------Create Buttons---------------------
		
		JPanel outbuttonPanel = new JPanel(new GridLayout(2,1,70,100));
		JPanel a = new JPanel();
		JPanel inbuttonPanel = new JPanel(new GridLayout(1,2,50,50));
		
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		
		inbuttonPanel.add(saveButton);
		inbuttonPanel.add(cancelButton);
		
		outbuttonPanel.add(a);
		outbuttonPanel.add(inbuttonPanel, BorderLayout.SOUTH);
		addProductpanel.add(outbuttonPanel);
				
//---------------------Event Handlers---------------------		

		saveButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
//				dispose();
				
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
		barcodeText.requestFocusInWindow();
	}
}