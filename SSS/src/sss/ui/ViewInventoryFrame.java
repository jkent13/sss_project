/*
 * MockupMainMenu Class
 * Frame design to support 'View Inventory' function
 * Original Author: Jasmina Pasalic
 */

package sss.ui;
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
import javax.swing.border.TitledBorder;

import sss.domain.IMController;
import sss.domain.InventoryFilter;
import sss.domain.NonEditableTableModel;


@SuppressWarnings("serial")
public class ViewInventoryFrame extends JFrame {

	private IMController controller = new IMController();
	
	private InventoryFilter filter = new InventoryFilter();
	
	public ViewInventoryFrame()
	{
		setTitle("View Inventory");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		JPanel MAINPANEL = new JPanel (new GridLayout(1,2,2,2));//Creating the main panel 
		TitledBorder MAINPANELtitle = new TitledBorder("MAIN");
		MAINPANEL.setBorder(MAINPANELtitle);
		add(MAINPANEL);

		JPanel p1 = new JPanel(new GridLayout(1, 1, 1, 1)); //Creating the panel for the grid 
		TitledBorder p1title = new TitledBorder("Grid Panel");
		p1.setBorder(p1title);//Setting border 

		JPanel p2 = new JPanel(new GridLayout(4, 1, 2, 2));//Creating the panel for the bar/buttons 
		TitledBorder p2title = new TitledBorder("VIEW PRODUCT INVENTORY");
		p2.setBorder(p2title);


		NonEditableTableModel dataModel = controller.getDataModel();
		JTable lookUpTable = new JTable(dataModel);
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		p1.add(scrlPane);

		/** Creating a panel for the search functions**/

		JPanel searchPanel = new JPanel(new GridLayout(3,2,2,9));//Search panel inside p2
		TitledBorder searchPanelTitledBorder = new TitledBorder("SEARCH");
		searchPanel.setBorder(searchPanelTitledBorder);

		JTextField searchField= new JTextField(); 
		JButton searchButton = new JButton("Search");
		JLabel spaceForPanel = new JLabel("ENTER TEXT TO SEARCH");//Creating space/so panel looks better

		searchPanel.add(spaceForPanel);//Adding the space
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		p2.add(searchPanel);//Adding search panel 1st

		/** Creating all the buttons/labels etc for searching options**/

		JCheckBox priceRangeCheckBox = new JCheckBox ("Price Range"); 
		JLabel toLabel = new JLabel ("to");
		JTextField minTextField = new JTextField ("$0.00");
		JLabel minLabel = new JLabel("Min Price");
		JTextField maxTextField = new JTextField("$0.00");
		JLabel maxLabel = new JLabel("Max Price");

		JCheckBox categoryCheckBox = new JCheckBox ("Category");
		JComboBox<String> categoryComboBox= new JComboBox<String>(controller.getCategoryNames());

		JCheckBox qohCheckBox = new JCheckBox ("Quantity-on-hand");
		JTextField qohTextField = new JTextField ("10");
		JRadioButton equalToRadioButton = new JRadioButton ("Equal to");
		JRadioButton lessThanRadioButton = new JRadioButton ("Less than");
		JRadioButton greaterThanRadioButton = new JRadioButton ("Greater than");

		ButtonGroup mygroup = new ButtonGroup();
		mygroup.add(equalToRadioButton);
		mygroup.add(lessThanRadioButton);
		mygroup.add(greaterThanRadioButton);


		JCheckBox supplierCheckBox = new JCheckBox ("Supplier");		
		JComboBox<String> supplierComboBox = new JComboBox<String>(controller.getSupplierNames());

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

		resultButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				if(filter.isQohSelected()) {
					
				}

			}
		});

		cancelButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				controller.shutdown();
				dispose();

			}
		});

		exportButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{


			}
		});

		// CHECK BOXES ---------------------------
		supplierCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (supplierCheckBox.isSelected())
				{
					filter.setSupplierSelected(true);
					supplierComboBox.setEnabled(true);
					filter.setSupplierId(supplierComboBox.getSelectedIndex() + 1);
				}
				else{
					filter.setSupplierSelected(false);
					supplierComboBox.setEnabled(false);
				}
			}
		});

		qohCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (qohCheckBox.isSelected())
				{
					filter.setQohSelected(true);
					equalToRadioButton.setEnabled(true);
					lessThanRadioButton.setEnabled(true);
					greaterThanRadioButton.setEnabled(true);
					qohTextField.setEnabled(true);
				}
				else
				{
					filter.setQohSelected(false);
					equalToRadioButton.setEnabled(false);
					lessThanRadioButton.setEnabled(false);
					greaterThanRadioButton.setEnabled(false);
					qohTextField.setEnabled(false);
				}

			}
		});

		categoryCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (categoryCheckBox.isSelected())
				{
					filter.setCategorySelected(true);
					categoryComboBox.setEnabled(true);
					filter.setCategory((String)categoryComboBox.getSelectedItem());
				}
				else{
					filter.setCategorySelected(false);
					categoryComboBox.setEnabled(false);
				}
			}
		});
		
		priceRangeCheckBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if (priceRangeCheckBox.isSelected())
				{
					filter.setPriceRangeSelected(true);
					minTextField.setEnabled(true);
					maxTextField.setEnabled(true);
					minLabel.setEnabled(true);
					maxLabel.setEnabled(true);
					toLabel.setEnabled(true);
				}
				else{
					filter.setPriceRangeSelected(false);
					minTextField.setEnabled(false);
					maxTextField.setEnabled(false);
					minLabel.setEnabled(false);
					maxLabel.setEnabled(false);
					toLabel.setEnabled(false);
				}
			}
		});

		// COMBO BOXES
		supplierComboBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if(supplierComboBox.isEnabled()) {
					filter.setSupplierId(supplierComboBox.getSelectedIndex() + 1);
				}
			}
		});
		
		categoryComboBox.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent ae)
			{
				if(categoryComboBox.isEnabled()) {
					filter.setCategory((String)categoryComboBox.getSelectedItem());
				}
			}
		});
		
		
		setVisible(true);
	}
	
}// End class
