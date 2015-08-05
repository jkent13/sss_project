/*
 * AddSupplierFrame Class
 * Frame design to support the 'Add Supplier' functions
 * Original Author: Amethyst Mayer
 */

package sss.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AddSupplierFrame extends JFrame {

	public AddSupplierFrame()
	{
		//-------------------Frame Details--------------------

				setTitle("Add Supplier");
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				setLocationRelativeTo(null);
				
		//-------------------Full Screen Panel--------------------
				
				JPanel addSupplierPanel = new JPanel(new GridLayout (3, 1, 1, 1));
				TitledBorder mainPanelTitle = new TitledBorder("Add Supplier");
				addSupplierPanel.setBorder(mainPanelTitle);
				add(addSupplierPanel);
				
		//--------------------Section Panels--------------------
				
				JPanel topPanel = new JPanel(new BorderLayout ());
				JLabel addSupplierTitle = new JLabel ("Add Supplier");
				Font font = new Font("SansSerif", Font.BOLD, 42);
				addSupplierTitle.setFont(font);
				topPanel.add(addSupplierTitle);
				addSupplierPanel.add(topPanel);
				
				JPanel centerPanel = new JPanel(new GridLayout (1, 1, 10, 10));
				addSupplierPanel.add(centerPanel);
				
				JPanel bottomPanel = new JPanel(new GridLayout (1, 2, 10, 10));
				addSupplierPanel.add(bottomPanel);
				
				JPanel leftPanel = new JPanel(new BorderLayout ());
				centerPanel.add(leftPanel);
				
				JPanel middlePanel = new JPanel(new GridLayout (1, 1, 10, 10));
				centerPanel.add(middlePanel);
				
				JPanel middleMidPanel = new JPanel(new GridLayout (4, 1, 10, 10));
				TitledBorder middleMidPanelTitle = new TitledBorder("Supplier Details:");
				middleMidPanel.setBorder(middleMidPanelTitle);
				middlePanel.add(middleMidPanel);
				
				JPanel rightPanel = new JPanel(new BorderLayout ());
				centerPanel.add(rightPanel);
				
				//---------------------Create Fields---------------------
				
				JLabel supplierIDLabel = new JLabel ("Supplier ID:");
				JLabel IDLabel = new JLabel ("6");
				
				JLabel supplierNameLabel = new JLabel ("Company Name:");
				JTextField supplierNameText = new JTextField ("");
				
				JLabel supplierPhoneLabel = new JLabel ("Phone:");
				JTextField supplierPhoneText = new JTextField ("");
				
				middleMidPanel.add(supplierIDLabel);
				middleMidPanel.add(IDLabel);
				middleMidPanel.add(supplierNameLabel);
				middleMidPanel.add(supplierNameText);
				middleMidPanel.add(supplierPhoneLabel);
				middleMidPanel.add(supplierPhoneText);
				
				//---------------------Create Buttons---------------------
					
				JPanel buttonsPanel = new JPanel(new GridLayout (2, 2, 10, 10));
				bottomPanel.add(buttonsPanel);
				
				JButton saveButton = new JButton("Save");
				JButton cancelButton = new JButton("Cancel");
				JLabel a = new JLabel();
				JLabel b = new JLabel();
				
				buttonsPanel.add(a);
				buttonsPanel.add(b);
				buttonsPanel.add(saveButton);
				buttonsPanel.add(cancelButton);
						
		//---------------------Event Handlers---------------------		

				saveButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
//						dispose();
						
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