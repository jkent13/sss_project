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
import javax.swing.border.TitledBorder;


public class ModifyProductFrame extends JFrame {

	public ModifyProductFrame()
	{

		
		setTitle("Modify Product");
		//			myFrame.setSize(900,500);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		//Parameters: numRows, numColumns, Hgap, Vgap
		setLocationRelativeTo(null);

		//Full Screen Panel
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
		add(fullScreenPanel);


		//SECTION PANELS

		JPanel leftPanel = new JPanel();
		TitledBorder leftPanelTitle = new TitledBorder("Product Inventory:");
		leftPanel.setBorder(leftPanelTitle);
		leftPanel.setLayout(new GridLayout(1,1,10,10));
		fullScreenPanel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
		rightPanel.setBorder(rightPanelTitle);
		rightPanel.setLayout(new GridLayout(5,1,10,10));
		fullScreenPanel.add(rightPanel);

		String[] colNames = {"Product id","Barcode","Name","Category","Sale Price"};
		Object[][] data = {
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"},
				{"DGKF353","4256985216","Cat","Pet","$40"}
		};

		JTable lookUpTable = new JTable(data, colNames);
		JScrollPane scrlPane = new JScrollPane(lookUpTable);
		leftPanel.add(scrlPane);
		
		JPanel datePanel = new JPanel();
		TitledBorder datePanelTitle = new TitledBorder("Time-Period View:");
		datePanel.setBorder(datePanelTitle);
		datePanel.setLayout(new GridLayout(1,2,10,10));
		
		
		JPanel rightDatePanel = new JPanel();
		TitledBorder rightDatePanelTitle = new TitledBorder("Select Date:");
		rightDatePanel.setBorder(rightDatePanelTitle);
		rightDatePanel.setLayout(new GridLayout(3,1,10,10));
		
		JPanel leftDatePanel = new JPanel();
		TitledBorder leftDatePanelTitle = new TitledBorder("Select Date:");
		leftDatePanel.setBorder(leftDatePanelTitle);
		leftDatePanel.setLayout(new GridLayout(3,1,10,10));
		
		JTextField viewStartDate = new JTextField();
		JLabel viewStartDateLabel = new JLabel("Start Date:");
		JLabel viewStartDateExample = new JLabel("e.g. 01/03/2014");
		rightDatePanel.add(viewStartDateLabel);
		rightDatePanel.add(viewStartDate);
		rightDatePanel.add(viewStartDateExample);
		
		JTextField viewEndDate = new JTextField();
		JLabel viewEndDateLabel = new JLabel("End Date:");
		JLabel viewEndDateExample = new JLabel("e.g. 24/03/2014");
		leftDatePanel.add(viewEndDateLabel);
		leftDatePanel.add(viewEndDate);
		leftDatePanel.add(viewEndDateExample);

		datePanel.add(rightDatePanel);
		datePanel.add(leftDatePanel);
		rightPanel.add(datePanel);
		
		JPanel codesPanel = new JPanel();
//		TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
//		codesPanel.setBorder(reportTypePanelTitle);
		codesPanel.setLayout(new GridLayout(2,2,10,10));
		rightPanel.add(codesPanel);

		JLabel productBarcodeLabel = new JLabel ("Barcode:");
		JTextField productBarcode = new JTextField();
		
		JLabel productCodeLabel = new JLabel ("Product Code:");
		JTextField productCode = new JTextField();

		codesPanel.add(productBarcodeLabel);
		codesPanel.add(productBarcode);
		codesPanel.add(productCodeLabel);
		codesPanel.add(productCode);

		JPanel productDetailsPanel = new JPanel();
		productDetailsPanel.setLayout(new GridLayout(3,2,10,10));
		rightPanel.add(productDetailsPanel);

		JLabel productNameLabel = new JLabel ("Name:");
		JTextField productName = new JTextField();
		
		JLabel productSalePriceLabel = new JLabel ("Sale Price:");
		JTextField productSalePrice = new JTextField();
		
		JLabel categoryLabel = new JLabel ("Category:");
		JComboBox categoryComboBox = new JComboBox ();
		ButtonGroup groupTypeGroup = new ButtonGroup();

		productDetailsPanel.add(productNameLabel);
		productDetailsPanel.add(productName);
		productDetailsPanel.add(productSalePriceLabel);
		productDetailsPanel.add(productSalePrice);
		productDetailsPanel.add(categoryLabel);
		productDetailsPanel.add(categoryComboBox);
		
		JPanel suppActivePanel = new JPanel();
		suppActivePanel.setLayout(new GridLayout(2,2,10,10));
		rightPanel.add(suppActivePanel);

		JLabel productSupplierLabel = new JLabel ("Supplier:");
		JTextField productSupplier = new JTextField();
		
		JLabel activeCheckBoxLabel = new JLabel ("Active:");
		JCheckBox activeCheckBox = new JCheckBox ();
		
		suppActivePanel.add(productSupplierLabel);
		suppActivePanel.add(productSupplier);
		suppActivePanel.add(activeCheckBoxLabel);
		suppActivePanel.add(activeCheckBox);
		

		JPanel resultsButtonPanel = new JPanel();
		//								TitledBorder resultsButtonPanelTitle = new TitledBorder("Shown as:");
		//								resultsButtonPanel.setBorder(resultsButtonPanelTitle);
		resultsButtonPanel.setLayout(new GridLayout(3,1,10,10));
		rightPanel.add(resultsButtonPanel);

		JLabel blank = new JLabel();
		resultsButtonPanel.add(blank);
		JButton getResultsButton = new JButton("Get Results");
		resultsButtonPanel.add(getResultsButton);

		getResultsButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
//				myFrame.dispose();
			}
		});

		JButton backButton = new JButton("Back");
		resultsButtonPanel.add(backButton);

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
