/*
 * ModifyProductFrame Class
 * Frame design to support 'Modify Product' function
 * Original Author: Amethyst Mayer
 */

package sss.ui;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import sss.domain.Product;
import sss.domain.ProductEditFilter;


public class ModifyProductFrame extends JFrame {

	private ModifyProductController controller = new ModifyProductController();
	private InventoryFilter inventoryFilter = new InventoryFilter(false, false, false, false);
	private ProductEditFilter filter = new ProductEditFilter();
	
	private JTextField barcodeTextField = new JTextField();
	private JTextField searchTextField = new JTextField();
	private JTextField productCodeTextField = new JTextField();
	private JTextField nameTextField = new JTextField();
	private JTextField costPriceTextField = new JTextField();
	private JTextField salePriceTextField = new JTextField();
	private JTextField quantityTextField = new JTextField();
	
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
		JTable productInfoTable = new JTable(dataModel);
		JScrollPane scrlPane = new JScrollPane(productInfoTable);
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
		productCodeTextField.setEditable(false);
		
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
		productDetailsPanel.setLayout(new GridLayout(5,2,10,10));
		rightPanel.add(productDetailsPanel);

		JLabel productNameLabel = new JLabel ("Name:");
		JLabel productCostPriceLabel = new JLabel("Cost Price:");
		JLabel productSalePriceLabel = new JLabel ("Sale Price:");
		JLabel categoryLabel = new JLabel ("Category:");
		JLabel quantityLabel = new JLabel("Quantity On Hand:");

		productDetailsPanel.add(productCodeLabel);
		productDetailsPanel.add(productCodeTextField);
		productDetailsPanel.add(productNameLabel);
		productDetailsPanel.add(nameTextField);
		productDetailsPanel.add(productCostPriceLabel);
		productDetailsPanel.add(costPriceTextField);
		productDetailsPanel.add(productSalePriceLabel);
		productDetailsPanel.add(salePriceTextField);
		productDetailsPanel.add(quantityLabel);
		productDetailsPanel.add(quantityTextField);

		
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
				if(!searchTextField.getText().equals("")) {
					inventoryFilter.setNameSupplied(true);
					inventoryFilter.setProductName(searchTextField.getText());
					controller.getResults(inventoryFilter);
				}
				else {
					inventoryFilter.setNameSupplied(false);
					controller.getResults(inventoryFilter);
				}
			}
		});
		
		searchTextField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!searchTextField.getText().equals("")) {
						inventoryFilter.setNameSupplied(true);
						inventoryFilter.setProductName(searchTextField.getText());
						controller.getResults(inventoryFilter);
					}
					else {
						inventoryFilter.setNameSupplied(false);
						controller.getResults(inventoryFilter);
					}
				}
			}
		});

		productInfoTable.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int row = productInfoTable.getSelectedRow();
					if (row != -1) {
						try {
							Product selectedProduct = new Product();
							
							selectedProduct.setId((long) productInfoTable.getValueAt(row, 0));
							selectedProduct.setCode((String) productInfoTable.getValueAt(row,
									1));
							selectedProduct.setName((String) productInfoTable.getValueAt(row,
									2));
							selectedProduct.setCostPrice((BigDecimal)productInfoTable.getValueAt(row,
									3));
							selectedProduct.setPrice((BigDecimal)productInfoTable.getValueAt(row,
									4));
							selectedProduct.setQuantityOnHand((int) productInfoTable
									.getValueAt(row, 5));
							selectedProduct.setCategory((String) productInfoTable.getValueAt(
									row, 6));
							
							switch ((String) productInfoTable.getValueAt(row, 7)) {
							case "Global Industries":
								selectedProduct.setSupplierId(1);
								break;
							case "Fake Co.":
								selectedProduct.setSupplierId(2);
								break;
							case "Risky Biz Ltd.":
								selectedProduct.setSupplierId(3);
								break;
							case "Unreal Synergy":
								selectedProduct.setSupplierId(4);
								break;
							case "Monsters Inc.":
								selectedProduct.setSupplierId(5);
								break;
							default:
								JOptionPane.showMessageDialog(null,
										"Error: An invalid supplier was found", "Invalid Supplier",
										JOptionPane.ERROR_MESSAGE);
							}

							if (((String) productInfoTable.getValueAt(row, 8)).equals("Y")) {
								selectedProduct.setActive(true);
							}
							else {
								selectedProduct.setActive(false);
							}
							
							fillProductDetails(selectedProduct);
							filter.setOriginalProduct(selectedProduct);
						}
						catch (ClassCastException cce) {
							JOptionPane
									.showMessageDialog(
											null,
											"Error: An invalid value was found in the product you selected",
											"Product Row Invalid", JOptionPane.ERROR_MESSAGE);
						} // End catch
					} // End if
				} // End if
			} // End method
		});

		productInfoTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				Point p = event.getPoint();
				int row = productInfoTable.rowAtPoint(p);
				if(row != -1) {
					if (event.getClickCount() == 2) {
						try {
							Product selectedProduct = new Product();
							
							selectedProduct.setId((long) productInfoTable.getValueAt(row, 0));
							selectedProduct.setCode((String) productInfoTable.getValueAt(row,
									1));
							selectedProduct.setName((String) productInfoTable.getValueAt(row,
									2));
							selectedProduct.setCostPrice((BigDecimal)productInfoTable.getValueAt(row,
									3));
							selectedProduct.setPrice((BigDecimal)productInfoTable.getValueAt(row,
									4));
							selectedProduct.setQuantityOnHand((int) productInfoTable
									.getValueAt(row, 5));
							selectedProduct.setCategory((String) productInfoTable.getValueAt(
									row, 6));
							
							switch ((String) productInfoTable.getValueAt(row, 7)) {
							case "Global Industries":
								selectedProduct.setSupplierId(1);
								break;
							case "Fake Co.":
								selectedProduct.setSupplierId(2);
								break;
							case "Risky Biz Ltd.":
								selectedProduct.setSupplierId(3);
								break;
							case "Unreal Synergy":
								selectedProduct.setSupplierId(4);
								break;
							case "Monsters Inc.":
								selectedProduct.setSupplierId(5);
								break;
							default:
								JOptionPane.showMessageDialog(null,
										"Error: An invalid supplier was found", "Invalid Supplier",
										JOptionPane.ERROR_MESSAGE);
							}

							if (((String) productInfoTable.getValueAt(row, 8)).equals("Y")) {
								selectedProduct.setActive(true);
							}
							else {
								selectedProduct.setActive(false);
							}
							
							fillProductDetails(selectedProduct);
							filter.setOriginalProduct(selectedProduct);
						}
						catch (ClassCastException cce) {
							cce.printStackTrace();
							JOptionPane
									.showMessageDialog(
											null,
											"Error: An invalid value was found in the product you selected",
											"Product Row Invalid", JOptionPane.ERROR_MESSAGE);
						} // End catch
					}
				}
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
	
	private void fillProductDetails(Product product) {
		barcodeTextField.setText(String.valueOf(product.getId()));
		productCodeTextField.setText(product.getCode());
		nameTextField.setText(product.getName());
		costPriceTextField.setText(product.getCostPrice().toPlainString());
		salePriceTextField.setText(product.getPrice().toPlainString());
		quantityTextField.setText(String.valueOf(product.getQuantityOnHand()));
		int categoryComboBoxIndex = Arrays.asList(controller.getCategoryNames()).indexOf(product.getCategory());
		categoryComboBox.setSelectedIndex(categoryComboBoxIndex);
		supplierComboBox.setSelectedIndex(product.getSupplierId()-1);
		if(product.isActive()) {
			activeCheckBox.setSelected(true);
		}
		else {
			activeCheckBox.setSelected(false);
		}
	}
}