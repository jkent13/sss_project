/*
 * MockupMainMenu Class
 * Frame design to support 'View Inventory' function
 * Original Author: Jasmina Pasalic
 */

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


@SuppressWarnings("serial")
public class ViewInventoryFrame extends JFrame {

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
		

		String[] colNames = {"Product id","Barcode","Name","Category","Sale Price","In-Stock"};
		Object[][] data = {
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"},
				{"DGKF353","4256985216","Cat","Pet","$40","7"}
		};

		JTable lookUpTable = new JTable(data, colNames);
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		p1.add(scrlPane);
		
		
		/** Creating a panel for the search functions**/
		
		JPanel searchPanel = new JPanel(new GridLayout(3,2,2,9));//Search panel inside p2
		TitledBorder SearchPaneltitle = new TitledBorder("SEARCH");
		searchPanel.setBorder(SearchPaneltitle);
		
		JTextField searchTBox= new JTextField("Text"); 
		JButton SearchB = new JButton("Search");
		JLabel spaceForPanel = new JLabel("ENTER TEXT TO SEARCH");//Creating space/so panel looks better
		
		
		searchPanel.add(spaceForPanel);//Adding the space
		searchPanel.add(searchTBox);
		searchPanel.add(SearchB);
		
		
		
		
		p2.add(searchPanel);//Adding search panel 1st
		
		/** Creating all the buttons/labels etc for searching options**/
		
		JCheckBox pRange = new JCheckBox ("Price Range"); 
		JLabel to = new JLabel ("to");
		JTextField minT = new JTextField ("$0.00");
		JLabel minL = new JLabel("Min Price");
		JTextField maxT = new JTextField("$0.00");
		JLabel maxL = new JLabel("Max Price");
		
		JCheckBox catCheckB = new JCheckBox ("Category");
		JComboBox catDr= new JComboBox ();
		
		JCheckBox Quant = new JCheckBox ("Quantity-on-hand");
		JTextField amoun = new JTextField ("10");
		JRadioButton R1 = new JRadioButton ("Equal to");
		JRadioButton R2 = new JRadioButton ("Less than");
		JRadioButton R3 = new JRadioButton ("Greater than");
		
		ButtonGroup mygroup = new ButtonGroup();
		mygroup.add(R1);
		mygroup.add(R2);
		mygroup.add(R3);
		
		
		JCheckBox supC = new JCheckBox ("Supplier");		
		JComboBox supDrop= new JComboBox ();
		
		/** End **/
		
		JPanel checkPANEL1 = new JPanel (new GridLayout(1,2,1,1));//Panel for check boxes - holds 2 Panels (Category + priceRange)
		
		/** Panels for PriceRange Panel **/
		
		JPanel priceRangeP = new JPanel (new GridLayout(3,1,4,4));//Panel for Price range options
		TitledBorder priceRangePtitle = new TitledBorder("Price Range");
		priceRangeP.setBorder(priceRangePtitle);
		
		JPanel priceCheckP = new JPanel(new GridLayout(1,1,1,1));//Panel for the checkbox (Inside price range panel)
		
		priceCheckP.add(pRange);
		
		JPanel priceLabelP = new JPanel(new GridLayout(1,2,125,30));//Panel for Labels (Inside price range panel)
		
		priceLabelP.add(minL);
		priceLabelP.add(maxL);
		
		JPanel priceTextP = new JPanel(new GridLayout(1,3,10,10));//Panel for Textbox (Inside price range panel)

		priceTextP.add(minT);
		priceTextP.add(to);
		priceTextP.add(maxT);
		
		//Adding (3) above panels to price range panel
		
		priceRangeP.add(priceCheckP);
		priceRangeP.add(priceLabelP);
		priceRangeP.add(priceTextP);
		
		minT.setEnabled(false);
		maxT.setEnabled(false);
		minL.setEnabled(false);
		maxL.setEnabled(false);
		to.setEnabled(false);
		
		pRange.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent argo0)
			{
				if (pRange.isSelected())
					{
					minT.setEnabled(true);
					maxT.setEnabled(true);
					minL.setEnabled(true);
					maxL.setEnabled(true);
					to.setEnabled(true);
					}
				else{
					minT.setEnabled(false);
					maxT.setEnabled(false);
					minL.setEnabled(false);
					maxL.setEnabled(false);
					to.setEnabled(false);
				}
			}
		});
		
		
		
		//price range panel to the panel holding (category + priceRange)
		checkPANEL1.add(priceRangeP);
		
		/** End OF Price range panel**/ 
		
		
		/** Creating category panel + adding features**/
		
		JPanel categoryPanel = new JPanel (new GridLayout(2,2,4,30));
		TitledBorder ButtonStuffP4title = new TitledBorder("Category");
		categoryPanel.setBorder(ButtonStuffP4title);
		
		categoryPanel.add(catCheckB);
		categoryPanel.add(catDr);
		catDr.setEnabled(false);
		
		catCheckB.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent argo0)
			{
				if (catCheckB.isSelected())
					{
					catDr.setEnabled(true);
					}
				else{
					catDr.setEnabled(false);
				}
			}
		});
		
		checkPANEL1.add(categoryPanel);
		
		/**End of Category panel**/
		
		JPanel checkPANEL2 = new JPanel (new GridLayout(1,2,1,1));//Panel for check boxes - holds 2 Panels (Quantity + Supplier)
		
		/** Creating the Quantity panel + adding features**/ 
		
		JPanel quantityPanel = new JPanel (new GridLayout(5,1,1,1));
		TitledBorder ButtonStuffP2title = new TitledBorder("Quantity");
		quantityPanel.setBorder(ButtonStuffP2title);
		
		quantityPanel.add(Quant);
		quantityPanel.add(R1);
		quantityPanel.add(R2);
		quantityPanel.add(R3);
		quantityPanel.add(amoun);
		
		
		R1.setEnabled(false);
		R2.setEnabled(false);
		R3.setEnabled(false);
		amoun.setEnabled(false);
	
		
		
		
		Quant.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent argo0)
			{
				if (Quant.isSelected())
					{
					R1.setEnabled(true);
					R2.setEnabled(true);
					R3.setEnabled(true);
					amoun.setEnabled(true);
					}
				else
				{
					R1.setEnabled(false);
					R2.setEnabled(false);
					R3.setEnabled(false);
					amoun.setEnabled(false);
				}
				
			}
		});
		
		checkPANEL2.add(quantityPanel);
		
		/**End OF Quantity Panel**/
		
		/** Creating supplier panel **/
		
		JPanel supplierPanel = new JPanel (new GridLayout(2,2,4,30));//Creating Supplier panel 
		TitledBorder ButtonStuffP3title = new TitledBorder("Supplier");
		supplierPanel.setBorder(ButtonStuffP3title);
		
		supplierPanel.add(supC);
		supplierPanel.add(supDrop);
		supDrop.setEnabled(false);
		
		supC.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent argo0)
			{
				if (supC.isSelected())
					{
					supDrop.setEnabled(true);
					}
				else{
					supDrop.setEnabled(false);
				}
			}
		});
		checkPANEL2.add(supplierPanel);//Adding supplier to checkbox panel 2
		
		/** End of Supplier panel **/
		
		//Adding fist and second panel for the check boxes to the main panel2
		p2.add(checkPANEL2);
		p2.add(checkPANEL1);
		
		
		/**Creating button panel for RESUTL-CANCEL-EXPORT**/
		JPanel buttonPANEL = new JPanel(new GridLayout(2, 1, 1, 10));//Main button panel		
		TitledBorder Resultcanceltitle = new TitledBorder("Get Results/Cancel/Export");
		buttonPANEL.setBorder(Resultcanceltitle);
		
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
		
		resultButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
//				viewStockFrame.dispose();
				
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
		
		exportButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
//				viewStockFrame.dispose();
				
			}
		});
		
		p2.add(buttonPANEL);//Adding Button panel to the main function panel (P2)
		/** End of Button panel adding **/
		
		MAINPANEL.add(p1);
		MAINPANEL.add(p2);		
		
		
		setVisible(true);

	
	}
}
