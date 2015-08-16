/*
 * ModifyProductFrame Class
 * Frame design to support 'Modify Product' function
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sss.domain.InventoryFilter;
import sss.domain.ModifyProductController;
import sss.domain.NonEditableTableModel;


public class ModifyProductFrame extends JFrame {

	private ModifyProductController controller = new ModifyProductController();
	private InventoryFilter inventoryFilter = new InventoryFilter(false, false, false, false);
	
	private JTextField barcodeTextField = new JTextField();
	private JTextField searchTextField = new JTextField();
	private JTextField productCodeTextField = new JTextField();
	private JTextField nameTextField = new JTextField();
	private JTextField salePriceTextField = new JTextField();
	
	private JComboBox<String> categoryComboBox = new JComboBox<>(controller.getCategoryNames());
	private JComboBox<String> supplierComboBox = new JComboBox<>(controller.getSupplierNames());
	
	private JCheckBox activeCheckBox = new JCheckBox();
	
	public ModifyProductFrame()
	{

//-------------------Frame Details--------------------
		
		setTitle("Modify Product");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
//-------------------Full Screen Panel--------------------
		
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
		add(fullScreenPanel);


//--------------------Section Panels--------------------

		JPanel leftPanel = new JPanel();
		TitledBorder leftPanelTitle = new TitledBorder("Product Inventory:");
		leftPanel.setBorder(leftPanelTitle);
		leftPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
		rightPanel.setBorder(rightPanelTitle);
		rightPanel.setLayout(new GridLayout(4,1,10,10));
		fullScreenPanel.add(rightPanel);

		NonEditableTableModel dataModel = controller.getDataModel();
		JTable lookUpTable = new JTable(dataModel);
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		leftPanel.add(scrlPane);
		
//--------------------Code Panel--------------------
// the barcode and product code fields are held in this panel.
		
		JPanel codesPanel = new JPanel();
		codesPanel.setLayout(new GridLayout(3,2,10,10));
		rightPanel.add(codesPanel);

		JLabel searchLabel = new JLabel ("Search Product by Name:");
		JLabel productCodeLabel = new JLabel ("Product Code:");
		JLabel productBarcodeLabel = new JLabel ("Barcode:");
		JLabel blankLabel = new JLabel ();

		JButton searchButton = new JButton("Search");
		
		barcodeTextField.setEditable(false);
		
		codesPanel.add(searchLabel);
		codesPanel.add(blankLabel);
		codesPanel.add(searchTextField);
		codesPanel.add(searchButton);
		codesPanel.add(searchTextField);
		codesPanel.add(searchButton);
		codesPanel.add(productBarcodeLabel);
		codesPanel.add(barcodeTextField);

		
//--------------------Details Panel--------------------
// the product name and price fields are held in this panel.
		
		JPanel productDetailsPanel = new JPanel();
		productDetailsPanel.setLayout(new GridLayout(3,2,10,10));
		rightPanel.add(productDetailsPanel);

		JLabel productNameLabel = new JLabel ("Name:");
		JLabel productSalePriceLabel = new JLabel ("Sale Price:");
		JLabel categoryLabel = new JLabel ("Category:");

		productDetailsPanel.add(productCodeLabel);
		productDetailsPanel.add(productCodeTextField);
		productDetailsPanel.add(productNameLabel);
		productDetailsPanel.add(nameTextField);
		productDetailsPanel.add(productSalePriceLabel);
		productDetailsPanel.add(salePriceTextField);

		
//--------------------Supplier/Active Panel--------------------
// the supplier and active fields are held in this panel.
		JPanel suppActivePanel = new JPanel();
		suppActivePanel.setLayout(new GridLayout(3,2,10,10));
		rightPanel.add(suppActivePanel);
		
		JLabel productSupplierLabel = new JLabel ("Supplier:");
		JLabel activeCheckBoxLabel = new JLabel ("Active:");
		
		suppActivePanel.add(categoryLabel);
		suppActivePanel.add(categoryComboBox);
		suppActivePanel.add(productSupplierLabel);
		suppActivePanel.add(supplierComboBox);
		suppActivePanel.add(activeCheckBoxLabel);
		suppActivePanel.add(activeCheckBox);
		
//---------------------Create Buttons---------------------

		JPanel resultsButtonPanel = new JPanel();
		resultsButtonPanel.setBorder(new EmptyBorder(50,50,50,50));
		resultsButtonPanel.setLayout(new GridLayout(1,2,50,50));
		rightPanel.add(resultsButtonPanel);
		
		JButton saveButton = new JButton("Save Changes");
		resultsButtonPanel.add(saveButton);

		JButton backButton = new JButton("Back");
		resultsButtonPanel.add(backButton);

		// ---------------------Event Handlers---------------------

		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});

		saveButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			}
		});

		

		backButton.addActionListener(new ActionListener()
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