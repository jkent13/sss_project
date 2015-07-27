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
		
		JPanel panelforinfo = new JPanel(new GridLayout(1, 2, 50, 10));
		addProductpanel.add(panelforinfo);
		
		JPanel productINFO = new JPanel(new GridLayout(6, 2, 10, 10));
		TitledBorder productINFOT = new TitledBorder("PRODUCT");
		productINFO.setBorder(productINFOT);
		panelforinfo.add(productINFO);
		
		JPanel pricingINFO = new JPanel(new GridLayout(2, 1, 10, 10));
		TitledBorder pricingINFOT = new TitledBorder("Pricing");
		pricingINFO.setBorder(pricingINFOT);
		panelforinfo.add(pricingINFO);
		
//---------------------Create Fields---------------------
		
		JLabel barcodeLabel = new JLabel ("Barcode");
		JTextField barcodeText = new JTextField ("");
		
		JLabel productCLabel = new JLabel ("Product Code");
		JTextField productCText = new JTextField ("");
		
		JLabel nameLabel = new JLabel ("Name");
		JTextField nameText = new JTextField ("");
		
		JLabel categoryLabel = new JLabel ("Category");
		JComboBox categoryComboBox = new JComboBox ();
		
		JLabel quantityLabel = new JLabel ("Quantity");
		JTextField quantityText = new JTextField ("");
		
		JLabel supplierLabel = new JLabel ("Supplier");
		JComboBox supplierComboBox = new JComboBox ();
		
		productINFO.add(barcodeLabel);
		productINFO.add(barcodeText);
		productINFO.add(productCLabel);
		productINFO.add(productCText);
		productINFO.add(nameLabel);
		productINFO.add(nameText);
		productINFO.add(categoryLabel);
		productINFO.add(categoryComboBox);
		productINFO.add(quantityLabel);
		productINFO.add(quantityText);
		productINFO.add(supplierLabel);
		productINFO.add(supplierComboBox);
		
		
		JPanel pricingPanel1 = new JPanel (new GridLayout(4, 2, 7, 7));
		JPanel pricingPanel2 = new JPanel (new GridLayout(2, 2, 7, 7));
		
		JLabel costpriceLabel = new JLabel("Cost Pricing");
		JTextField costpriceText = new JTextField ("");
		
		JLabel salepriceLabel = new JLabel("Cost Pricing");
		JTextField salepriceText = new JTextField ("");
		
		JLabel gstLabel = new JLabel("GST (10%) ");
		JLabel gstLabelinfo = new JLabel("$0");
		
		JLabel lineLabel = new JLabel("_____________________________________________");
		JLabel lineLabel1 = new JLabel("_____________________________________________");
		JLabel profitmargLabel = new JLabel("Cost Pricing");
		JLabel profitmargLabelinfo = new JLabel("$ ###");
		
		JLabel profitmargLabelinf = new JLabel("Cost Pricing");
		JLabel profitLabelinfo = new JLabel("###% ");
		
		pricingPanel1.add(costpriceLabel);
		pricingPanel1.add(costpriceText);
		pricingPanel1.add(salepriceLabel);
		pricingPanel1.add(salepriceText);
		pricingPanel1.add(gstLabel);
		pricingPanel1.add(gstLabelinfo);
		pricingPanel1.add(lineLabel);
		pricingPanel1.add(lineLabel1);		
		pricingPanel2.add(profitmargLabel);
		pricingPanel2.add(profitmargLabelinfo);
		pricingPanel2.add(profitmargLabelinf);
		pricingPanel2.add(profitLabelinfo);
		
		pricingINFO.add(pricingPanel1);
		pricingINFO.add(pricingPanel2);
		
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

