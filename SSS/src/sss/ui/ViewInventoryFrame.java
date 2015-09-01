/*
 * ViewInventoryFrame Class
 * 
 * Frame design to support 'View Inventory' function
 * 
 * Original Author: Jasmina Pasalic
 */

package sss.ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

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
import javax.swing.border.TitledBorder;

import sss.domain.IMController;
import sss.domain.InventoryFilter;
import sss.domain.NonEditableTableModel;


@SuppressWarnings("serial")
public class ViewInventoryFrame extends JFrame {

	private IMController controller = new IMController();
	private InventoryFilter filter = new InventoryFilter();		// Contains all the user input and selections and search box input
	
	private JCheckBox supplierCheckBox;
	private JCheckBox categoryCheckBox;
	private JCheckBox qohCheckBox;
	private JCheckBox priceRangeCheckBox;
	
	private JComboBox<String> supplierComboBox;
	private JComboBox<String> categoryComboBox;
	
	private JTextField minTextField;
	private JTextField maxTextField;
	
	private JTextField searchField; 
	
	private JTextField qohTextField;
	private JRadioButton equalToRadioButton;
	private JRadioButton greaterThanRadioButton;
	private JRadioButton lessThanRadioButton;
 	
	public ViewInventoryFrame()
	{
		setTitle("View Inventory");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		JPanel MAINPANEL = new JPanel (new GridLayout(1,2,2,2));//Creating the main panel 
		TitledBorder MAINPANELtitle = new TitledBorder("");
		MAINPANEL.setBorder(MAINPANELtitle);
		add(MAINPANEL);

		JPanel p1 = new JPanel(new GridLayout(1, 1, 1, 1)); //Creating the panel for the grid 
		TitledBorder p1title = new TitledBorder("Grid Panel");
		p1.setBorder(p1title);//Setting border 

		JPanel p2 = new JPanel(new GridLayout(4, 1, 2, 2));//Creating the panel for the bar/buttons 
		TitledBorder p2title = new TitledBorder("View Product Inventory");
		p2.setBorder(p2title);


		NonEditableTableModel dataModel = controller.getDataModel();
		JTable lookUpTable = new JTable(dataModel);
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		p1.add(scrlPane);

		/** Creating a panel for the search functions**/

		JPanel searchPanel = new JPanel(new GridLayout(2,1,10,10));//Search panel inside p2
		TitledBorder searchPanelTitledBorder = new TitledBorder("Search");
		searchPanel.setBorder(searchPanelTitledBorder);
		
		JPanel aa = new JPanel(new GridLayout(1,3,10,10));
		
		JButton addProductButton = new JButton("Add Product");
		aa.add(addProductButton);

		JButton modifyProductButton = new JButton("Modify Product");
		aa.add(modifyProductButton);

		JButton importCSVButton = new JButton("Import CSV");
		aa.add(importCSVButton);
		
		
		JPanel bb = new JPanel(new GridLayout(2,1,10,10));

		searchField = new JTextField(); 
		//JButton searchButton = new JButton("Search");
		JLabel spaceForPanel = new JLabel("Enter Text To Search");//Creating space/so panel looks better

		bb.add(spaceForPanel);//Adding the space
		bb.add(searchField);
		searchPanel.add(aa);
		searchPanel.add(bb);
		//searchPanel.add(searchButton);

		p2.add(searchPanel);//Adding search panel 1st

		/** Creating all the buttons/labels etc for searching options**/

		priceRangeCheckBox = new JCheckBox ("Price Range"); 
		JLabel toLabel = new JLabel ("to");
		minTextField = new JTextField ("0.00");
		JLabel minLabel = new JLabel("Min Price");
		maxTextField = new JTextField("0.00");
		JLabel maxLabel = new JLabel("Max Price");

		categoryCheckBox = new JCheckBox ("Category");
		categoryComboBox = new JComboBox<String>(controller.getCategoryNames());

		qohCheckBox = new JCheckBox ("Quantity-on-hand");
		qohTextField = new JTextField ("10");
		equalToRadioButton = new JRadioButton ("Equal to");
		lessThanRadioButton = new JRadioButton ("Less than");
		greaterThanRadioButton = new JRadioButton ("Greater than");

		ButtonGroup mygroup = new ButtonGroup();
		mygroup.add(equalToRadioButton);
		mygroup.add(lessThanRadioButton);
		mygroup.add(greaterThanRadioButton);


		supplierCheckBox = new JCheckBox ("Supplier");		
		supplierComboBox = new JComboBox<String>(controller.getSupplierNames());

		/** End **/

		JPanel checkPANEL1 = new JPanel (new GridLayout(1,2,1,1));//Panel for check boxes - holds 2 Panels (Category + priceRange)

		/** Panels for PriceRange Panel **/

		JPanel priceRangeP = new JPanel (new GridLayout(3,1,4,4));//Panel for Price range options
		TitledBorder priceRangeTitledBorder = new TitledBorder("Price Range");
		priceRangeP.setBorder(priceRangeTitledBorder);

		JPanel priceCheckP = new JPanel(new GridLayout(1,1,1,1));//Panel for the checkbox (Inside price range panel)

		priceCheckP.add(priceRangeCheckBox);

		JPanel priceLabelP = new JPanel(new GridLayout(1,2,125,30));//Panel for Labels (Inside price range panel)

		priceLabelP.add(minLabel);
		priceLabelP.add(maxLabel);

		JPanel priceTextP = new JPanel(new GridLayout(1,3,10,10));//Panel for Textbox (Inside price range panel)

		priceTextP.add(minTextField);
		priceTextP.add(toLabel);
		priceTextP.add(maxTextField);

		//Adding (3) above panels to price range panel

		priceRangeP.add(priceCheckP);
		priceRangeP.add(priceLabelP);
		priceRangeP.add(priceTextP);

		minTextField.setEnabled(false);
		maxTextField.setEnabled(false);
		minLabel.setEnabled(false);
		maxLabel.setEnabled(false);
		toLabel.setEnabled(false);

		//price range panel to the panel holding (category + priceRange)
		checkPANEL1.add(priceRangeP);

		/** End OF Price range panel**/ 


		/** Creating category panel + adding features**/

		JPanel categoryPanel = new JPanel (new GridLayout(2,2,4,30));
		TitledBorder categoryTitledBorder = new TitledBorder("Category");
		categoryPanel.setBorder(categoryTitledBorder);

		categoryPanel.add(categoryCheckBox);
		categoryPanel.add(categoryComboBox);
		categoryComboBox.setEnabled(false);

		checkPANEL1.add(categoryPanel);

		/**End of Category panel**/

		JPanel checkPANEL2 = new JPanel (new GridLayout(1,2,1,1));//Panel for check boxes - holds 2 Panels (Quantity + Supplier)

		/** Creating the Quantity panel + adding features**/ 

		JPanel quantityPanel = new JPanel (new GridLayout(5,1,1,1));
		TitledBorder quantityTitledBorder = new TitledBorder("Quantity");
		quantityPanel.setBorder(quantityTitledBorder);

		quantityPanel.add(qohCheckBox);
		quantityPanel.add(equalToRadioButton);
		quantityPanel.add(lessThanRadioButton);
		quantityPanel.add(greaterThanRadioButton);
		quantityPanel.add(qohTextField);

		equalToRadioButton.setSelected(true);
		equalToRadioButton.setEnabled(false);
		lessThanRadioButton.setEnabled(false);
		greaterThanRadioButton.setEnabled(false);
		qohTextField.setEnabled(false);

		checkPANEL2.add(quantityPanel);

		/**End OF Quantity Panel**/

		/** Creating supplier panel **/

		JPanel supplierPanel = new JPanel (new GridLayout(2,2,4,30));//Creating Supplier panel 
		TitledBorder supplierTitledBorder = new TitledBorder("Supplier");
		supplierPanel.setBorder(supplierTitledBorder);

		supplierPanel.add(supplierCheckBox);
		supplierPanel.add(supplierComboBox);
		supplierComboBox.setEnabled(false);


		checkPANEL2.add(supplierPanel);//Adding supplier to checkbox panel 2

		/** End of Supplier panel **/

		//Adding fist and second panel for the check boxes to the main panel2
		p2.add(checkPANEL2);
		p2.add(checkPANEL1);


		/**Creating button panel for RESULT-CANCEL-EXPORT**/
		JPanel buttonPANEL = new JPanel(new GridLayout(2, 1, 1, 10));//Main button panel		
		TitledBorder resultsCancelExportTitledBorder = new TitledBorder("Get Results/Cancel/Export");
		buttonPANEL.setBorder(resultsCancelExportTitledBorder);

		JPanel resultCancelPANEL = new JPanel(new GridLayout(1, 2, 10, 1));//Panel for result and cancel

		JPanel exportPanel = new JPanel(new GridLayout(1, 1, 1, 1));//Panel for export 

		JButton resultButton = new JButton ("Get Results");
		JButton cancelButton = new JButton ("Cancel");
		JButton exportButton = new JButton ("Export");

		resultCancelPANEL.add(resultButton);//Adding buttons 
		resultCancelPANEL.add(cancelButton);//Adding buttons
		exportPanel.add(exportButton);//Adding button

		buttonPANEL.add(resultCancelPANEL);//Adding Result/Cancel panel to main button panel
		buttonPANEL.add(exportPanel);//Adding Export panel to main button panel

		p2.add(buttonPANEL);//Adding Button panel to the main function panel (P2)
		/** End of Button panel adding **/

		MAINPANEL.add(p1);
		MAINPANEL.add(p2);		

		// EVENT HANDLERS -----------------------------------------------
		
		addProductButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new AddProductFrame();
			}
		});
		
		modifyProductButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new ModifyProductFrame();
			}
		});
		
		importCSVButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				controller.readCsv();
				
			}
		});
		
		
		searchField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildFilter();
					controller.getResults(filter);
					searchField.requestFocusInWindow();
				}
			}
		});
		
		minTextField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildFilter();
					controller.getResults(filter);
				}
			}
		});
		
		maxTextField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildFilter();
					controller.getResults(filter);
				}
			}
		});
		
		qohTextField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildFilter();
					controller.getResults(filter);
				}
			}
		});
		
		resultButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				buildFilter();
				controller.getResults(filter);
				searchField.requestFocusInWindow();
			}
		});

		cancelButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				dispose();

			}
		});

		exportButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.exportProductReport();
			}
		});
		
		// --------------------------------------------------------------

		// CHECK BOXES --------------------------------------------------
		
		supplierCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (supplierCheckBox.isSelected())
				{
					supplierComboBox.setEnabled(true);
				}
				else{
					supplierComboBox.setEnabled(false);
				}
				searchField.requestFocusInWindow();
			}
		});

		qohCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (qohCheckBox.isSelected())
				{
					equalToRadioButton.setEnabled(true);
					lessThanRadioButton.setEnabled(true);
					greaterThanRadioButton.setEnabled(true);
					qohTextField.setEnabled(true);
				}
				else
				{
					equalToRadioButton.setEnabled(false);
					lessThanRadioButton.setEnabled(false);
					greaterThanRadioButton.setEnabled(false);
					qohTextField.setEnabled(false);
				}
				searchField.requestFocusInWindow();
			}
		});

		categoryCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (categoryCheckBox.isSelected())
				{
					categoryComboBox.setEnabled(true);
				}
				else{
					categoryComboBox.setEnabled(false);
				}
				searchField.requestFocusInWindow();
			}
		});
		
		priceRangeCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (priceRangeCheckBox.isSelected())
				{
					minTextField.setEnabled(true);
					maxTextField.setEnabled(true);
					minLabel.setEnabled(true);
					maxLabel.setEnabled(true);
					toLabel.setEnabled(true);
				}
				else{
					minTextField.setEnabled(false);
					maxTextField.setEnabled(false);
					minLabel.setEnabled(false);
					maxLabel.setEnabled(false);
					toLabel.setEnabled(false);
				}
				searchField.requestFocusInWindow();
			}
		});
		
		// --------------------------------------------------------------
		
		
		setVisible(true);
		searchField.requestFocusInWindow();
	}
	
	// ViewInventoryFrame Methods ---------------------------------
	/**
	 * Reads the state of the frame and all its values and updates the InventoryFilter to align with this state
	 */
	private void buildFilter() {
		if(supplierCheckBox.isSelected()) {
			filter.setSupplierSelected(true);
			filter.setSupplierId(supplierComboBox.getSelectedIndex() + 1);
		}
		else {
			filter.setSupplierSelected(false);
		}
		
		if(categoryCheckBox.isSelected()) {
			filter.setCategorySelected(true);
			filter.setCategory((String)categoryComboBox.getSelectedItem());
		}
		else {
			filter.setCategorySelected(false);
		}
		
		if(qohCheckBox.isSelected()) {
			
			if(equalToRadioButton.isSelected()) {
				filter.setQohOperator("=");
			}
			else if(greaterThanRadioButton.isSelected()) {
				filter.setQohOperator(">");
			}
			else {
				filter.setQohOperator("<");
			}
			
			try {
				if(Integer.parseInt(qohTextField.getText()) >= 0) {
					filter.setQohValue(Integer.valueOf(qohTextField.getText()));
					filter.setQohSelected(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Error: Invalid value for quantity-on-hand. Must be a positive digit", "Invalid Value", JOptionPane.ERROR_MESSAGE);
					qohTextField.setText("");
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Error: Invalid value for quantity-on-hand. Must be a positive digit", "Invalid Value", JOptionPane.ERROR_MESSAGE);
				qohTextField.setText("0");
			}
		}
		else {
			filter.setQohSelected(false);
		}
		
		if(priceRangeCheckBox.isSelected()) {
			try {
				Double minValue = Double.parseDouble(minTextField.getText());
				Double maxValue = Double.parseDouble(maxTextField.getText());
				
				if(maxValue.compareTo(minValue) > 0) {
					filter.setMinPrice(new BigDecimal(minValue).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					filter.setMaxPrice(new BigDecimal(maxValue).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					filter.setPriceRangeSelected(true);
				}
				else {
					throw new NumberFormatException();
				}
			}
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Error: Invalid values for price range. Min must be less than max, and both values must be numeric", "Invalid Values", JOptionPane.ERROR_MESSAGE);
				minTextField.setText("0.00");
				maxTextField.setText("0.00");
			}
		}
		else {
			filter.setPriceRangeSelected(false);
		}
		
		if(searchField.getText().length() > 0) {
			filter.setNameSupplied(true);
			filter.setProductName(searchField.getText());
		}
		else {
			filter.setNameSupplied(false);
		}
	}
}// End class