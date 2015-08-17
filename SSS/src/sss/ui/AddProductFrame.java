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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import sss.domain.AddProductController;
import sss.domain.Product;

@SuppressWarnings("serial")
public class AddProductFrame extends JFrame {
	private AddProductController controller = new AddProductController();
	private Product newProduct = new Product();
	
	private JTextField barcodeTextField = new JTextField ("");
	private JTextField productCodeTextField = new JTextField ("");
	private JTextField nameTextField = new JTextField ("");
	private JTextField quantityTextField = new JTextField ("");
	
	private JTextField costPriceTextField = new JTextField ("");
	private JTextField salePriceTextField = new JTextField ("");
	
	private JComboBox<String> categoryComboBox = new JComboBox<>(controller.getCategoryNames());
	private JComboBox<String> supplierComboBox = new JComboBox<>(controller.getSupplierNames());
	
	private JLabel gstAmountLabel = new JLabel("$0");
	private JLabel profitMarginAmountLabel = new JLabel("$0");
	private JLabel profitPercentageAmountLabel = new JLabel("0%");
	
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
		JLabel productCodeLabel = new JLabel ("Product Code:");
		JLabel nameLabel = new JLabel ("Name:");
		JLabel categoryLabel = new JLabel ("Category:");
		JLabel quantityLabel = new JLabel ("Quantity:");
		JLabel supplierLabel = new JLabel ("Supplier:");
		
		
		productPanel.add(barcodeLabel);
		productPanel.add(barcodeTextField);
		productPanel.add(productCodeLabel);
		productPanel.add(productCodeTextField);
		productPanel.add(nameLabel);
		productPanel.add(nameTextField);
		productPanel.add(categoryLabel);
		productPanel.add(categoryComboBox);
		productPanel.add(quantityLabel);
		productPanel.add(quantityTextField);
		productPanel.add(supplierLabel);
		productPanel.add(supplierComboBox);
		
		
		JPanel leftPricePanel = new JPanel (new GridLayout(4, 2, 7, 7));
		JPanel rightPricePanel = new JPanel (new GridLayout(2, 2, 7, 7));
		
		JLabel costpriceLabel = new JLabel("Cost Price:");
		
		JLabel salepriceLabel = new JLabel("Selling Price:");
		
		JLabel gstLabel = new JLabel("GST (10%)");
		
		JLabel lineLabel = new JLabel("_____________________________________________");
		JLabel lineLabel1 = new JLabel("_____________________________________________");
		JLabel profitmarginLabel = new JLabel("Profit Margin:");
		
		JLabel profitPercentageLabel = new JLabel("Profit Percentage (%):");

		
		leftPricePanel.add(costpriceLabel);
		leftPricePanel.add(costPriceTextField);
		leftPricePanel.add(salepriceLabel);
		leftPricePanel.add(salePriceTextField);
		leftPricePanel.add(gstLabel);
		leftPricePanel.add(gstAmountLabel);
		leftPricePanel.add(lineLabel);
		leftPricePanel.add(lineLabel1);		
		rightPricePanel.add(profitmarginLabel);
		rightPricePanel.add(profitMarginAmountLabel);
		rightPricePanel.add(profitPercentageLabel);
		rightPricePanel.add(profitPercentageAmountLabel);
		
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
				if(buildProduct()) {
					controller.saveNewProduct(newProduct);
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
		
		
		costPriceTextField.addFocusListener(new FocusListener()
    {

			@Override
			public void focusGained(FocusEvent arg0) {				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!(costPriceTextField.getText().equals("") || salePriceTextField.equals(""))) {
					try {
						BigDecimal costPrice = new BigDecimal(costPriceTextField.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal price = new BigDecimal(salePriceTextField.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal profitMarginValue = price.subtract(costPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal gstAmountValue = price.divide(new BigDecimal(100), 12, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						
						String profitMargin = price.subtract(gstAmountValue).subtract(costPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();
						profitMarginAmountLabel.setText("$" + profitMargin);
						String profitPercentage = profitMarginValue.subtract(gstAmountValue).divide(price, 12, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();
						profitPercentageAmountLabel.setText(profitPercentage + "%");
					}
					catch (NumberFormatException nfe) {
						return;
					}
					catch (ArithmeticException ae) {
						return;
					}
				}
			}
    });
		
		
		salePriceTextField.addFocusListener(new FocusListener()
    {

			@Override
			public void focusGained(FocusEvent arg0) {				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!(costPriceTextField.getText().equals("") || salePriceTextField.equals(""))) {
					try {
						BigDecimal costPrice = new BigDecimal(costPriceTextField.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal price = new BigDecimal(salePriceTextField.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal profitMarginValue = price.subtract(costPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal gstAmountValue = price.divide(new BigDecimal(100), 12, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						
						String gstAmount = price.divide(new BigDecimal(100), 12, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();
						gstAmountLabel.setText("$" + gstAmount);
						String profitMargin = price.subtract(gstAmountValue).subtract(costPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();
						profitMarginAmountLabel.setText("$" + profitMargin);
						String profitPercentage = profitMarginValue.subtract(gstAmountValue).divide(price, 12, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();
						profitPercentageAmountLabel.setText(profitPercentage + "%");
					}
					catch (NumberFormatException nfe) {
						return;
					}
					catch (ArithmeticException ae) {
						return;
					}
				}
				
			}
    });
		
		
		setVisible(true);
		barcodeTextField.requestFocusInWindow();
	}

	
	// ==========================================================================
	// Methods 
  // ==========================================================================
	
	
	
	private boolean buildProduct() {
		if(barcodeTextField.getText().matches("^\\d{13}$")) {
			newProduct.setId(Long.valueOf(barcodeTextField.getText()));
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: Product barcode must be a 13 "
					+ "digit number", "Invalid Barcode", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			int quantity = Integer.parseInt(quantityTextField.getText());
			if(quantity < 0) {
				JOptionPane.showMessageDialog(null, "Error: Product quantity on hand must "
						+ "be numerical and >= 0", "Invalid Quantity on Hand", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else {
				newProduct.setQuantityOnHand(quantity);
			}
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Error: Product quantity on hand must "
					+ "be numerical and >= 0", "Invalid Quantity on Hand", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			BigDecimal costPrice = new BigDecimal(costPriceTextField.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			BigDecimal price = new BigDecimal(salePriceTextField.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			newProduct.setCostPrice(costPrice);
			newProduct.setPrice(price);
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Error: Invalid price input", 
					"Invalid Pricing", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		newProduct.setCode(productCodeTextField.getText());
		newProduct.setName(nameTextField.getText());
		newProduct.setCategory((String)categoryComboBox.getSelectedItem());
		newProduct.setSupplierId(supplierComboBox.getSelectedIndex() + 1);
		newProduct.setActive(true);
		
		if(newProduct.validateProduct()) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
}