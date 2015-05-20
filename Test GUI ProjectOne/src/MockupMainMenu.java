import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class MockupMainMenu {

	public static void main(String[] args) {

		JFrame myFrame = new JFrame();
		myFrame.setTitle("Main Menu");
		myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);


		//Full Screen Panel
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
		myFrame.add(fullScreenPanel);


		//SECTION PANELS

		JPanel topPanel = new JPanel();
//		TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
//		topPanel.setBorder(topPanelTitle);
		topPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(topPanel);

		JLabel inventoryLabel = new JLabel("Welcome to the SSS Project");
		Font myFont = new Font("SansSerif", Font.BOLD, 42);
		inventoryLabel.setFont(myFont);
		topPanel.add(inventoryLabel);

		JPanel bottomPanel = new JPanel();
//		TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
//		bottomPanel.setBorder(bottomPanelTitle);
		bottomPanel.setLayout(new GridLayout(2,2,10,10));
		fullScreenPanel.add(bottomPanel);


		JButton salesTransButton = new JButton("Sales Transactions");
		bottomPanel.add(salesTransButton);

		JButton dashboardButton = new JButton("Dashboard");
		bottomPanel.add(dashboardButton);

		JButton inventoryButton = new JButton("Inventory");
		bottomPanel.add(inventoryButton);

		JButton reportsButton = new JButton("Reports");
		bottomPanel.add(reportsButton);

		myFrame.setVisible(true);


		salesTransButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				JFrame myFrame = new JFrame();
				myFrame.setTitle("Make Sale");
				myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
				myFrame.setLocationRelativeTo(null);



				//Full Screen Panel
				JPanel fullScreenPanel = new JPanel();
				TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
				fullScreenPanel.setBorder(fullScreenTitle);
				fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
				myFrame.add(fullScreenPanel);


				//SECTION PANELS

				JPanel leftPanel = new JPanel();
				TitledBorder leftPanelTitle = new TitledBorder("Left Panel:");
				leftPanel.setBorder(leftPanelTitle);
				leftPanel.setLayout(new GridLayout(2,1,10,10));
				fullScreenPanel.add(leftPanel);

				JPanel itemsPanel = new JPanel();
				TitledBorder itemsPanelTitle = new TitledBorder("Product Inventory:");
				itemsPanel.setBorder(itemsPanelTitle);
				itemsPanel.setLayout(new GridLayout(1,1,10,10));
				//				leftPanel.add(itemsPanel);


				String[] colNames = {"Qty","Product id","Name","Discount","Sale Price"};
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
				itemsPanel.add(scrlPane);



				JPanel showAmount = new JPanel();
				showAmount.setLayout(new GridLayout(2,1,10,10));
				JLabel saleBalanceLabel = new JLabel("Balance:$" + "");
				JLabel saleTotalLabel = new JLabel("TOTAL:$" + "");

				JPanel showBalance = new JPanel();
				JPanel showTotal = new JPanel();

				showBalance.add(saleBalanceLabel);
				showTotal.add(saleTotalLabel);

				showAmount.add(showBalance);
				showAmount.add(showTotal);

				leftPanel.add(itemsPanel);
				leftPanel.add(showAmount);
				//				fullScreenPanel.add(itemsPanel);




				JPanel middlePanel = new JPanel();
				//						TitledBorder middlePanelTitle = new TitledBorder("Middle Panel:");
				//						middlePanel.setBorder(middlePanelTitle);
				middlePanel.setLayout(new GridLayout(3,1,10,10));

				JPanel topMiddlePanel = new JPanel();
				//						TitledBorder topMiddlePanelTitle = new TitledBorder("Top Middle Panel:");
				//						topMiddlePanel.setBorder(topMiddlePanelTitle);
				topMiddlePanel.setLayout(new GridLayout(4,2,10,10));

				JLabel barcodeLabel = new JLabel("Barcode:");
				JTextField barcodeEntryField = new JTextField(13);

				JLabel paymentLabel = new JLabel("Payment:");
				JTextField paymentEntryField = new JTextField(5);

				topMiddlePanel.add(barcodeLabel);
				topMiddlePanel.add(barcodeEntryField);
				topMiddlePanel.add(paymentLabel);
				topMiddlePanel.add(paymentEntryField);

				JPanel midMiddlePanel = new JPanel();
				//						TitledBorder midMiddlePanelTitle = new TitledBorder("Middle Middle Panel:");
				//						midMiddlePanel.setBorder(midMiddlePanelTitle);
				midMiddlePanel.setLayout(new GridLayout(2,3,10,10));


				JPanel botMiddlePanel = new JPanel();
				//						TitledBorder botMiddlePanelTitle = new TitledBorder("Bottom Middle Panel:");
				//						botMiddlePanel.setBorder(botMiddlePanelTitle);
				botMiddlePanel.setLayout(new GridLayout(2,3,10,10));

				JButton Button1 = new JButton("1");
				JButton Button2 = new JButton("2");
				JButton Button3 = new JButton("3");
				JButton Button4 = new JButton("4");
				JButton Button5 = new JButton("5");
				JButton Button6 = new JButton("6");
				JButton Button7 = new JButton("7");
				JButton Button8 = new JButton("8");
				JButton Button9 = new JButton("9");
				JButton Buttondec = new JButton(".");
				JButton Button0 = new JButton("0");
				JButton Buttondel = new JButton("del");

				midMiddlePanel.add(Button1);
				midMiddlePanel.add(Button2);
				midMiddlePanel.add(Button3);
				midMiddlePanel.add(Button4);
				midMiddlePanel.add(Button5);
				midMiddlePanel.add(Button6);
				botMiddlePanel.add(Button7);
				botMiddlePanel.add(Button8);
				botMiddlePanel.add(Button9);
				botMiddlePanel.add(Buttondec);
				botMiddlePanel.add(Button0);
				botMiddlePanel.add(Buttondel);

				middlePanel.add(topMiddlePanel);
				middlePanel.add(midMiddlePanel);
				middlePanel.add(botMiddlePanel);
				fullScreenPanel.add(middlePanel);



				JPanel rightPanel = new JPanel();
				//						TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
				//						rightPanel.setBorder(rightPanelTitle);
				rightPanel.setLayout(new GridLayout(1,2,10,10));
				JButton voidButton = new JButton("f1 Void");
				JButton lookUpButton = new JButton("f2 Lookup Item");
				JButton quantityButton = new JButton("f3 Quantity");
				JButton overridePriceButton = new JButton("f4 Override Price");
				JButton discountButton = new JButton("f5 Discount");
				JButton optionsButton = new JButton("f6 Options");
				JButton enterButton = new JButton("ENTER");

				JPanel firstRightPanel = new JPanel();
				//						TitledBorder firstRightPanelTitle = new TitledBorder("1st Right Panel:");
				//						firstRightPanel.setBorder(firstRightPanelTitle);
				firstRightPanel.setLayout(new GridLayout(2,1,10,10));
				JButton note5Button = new JButton("$5");
				JButton note10Button = new JButton("$10");
				JButton note20Button = new JButton("$20");
				JButton note50Button = new JButton("$50");
				JButton note100Button = new JButton("$100");

				JPanel exactCashPanel = new JPanel();
				JButton exactCashButton = new JButton("Exact Cash");
				exactCashPanel.setLayout(new GridLayout(3,1,10,10));
				//						JButton enterButton2 = new JButton("ENTER");
				JPanel blankPanel = new JPanel();
				//						exactCashPanel.add(enterButton2);
				exactCashPanel.add(blankPanel);
				exactCashPanel.add(exactCashButton);


				JPanel notesPanel = new JPanel();
				notesPanel.setLayout(new GridLayout(5,1,10,10));

				notesPanel.add(note100Button);
				notesPanel.add(note50Button);
				notesPanel.add(note20Button);
				notesPanel.add(note10Button);
				notesPanel.add(note5Button);


				JPanel secondRightPanel = new JPanel();
				//						TitledBorder secondRightPanelTitle = new TitledBorder("2nd Right Panel:");
				//						secondRightPanel.setBorder(secondRightPanelTitle);
				secondRightPanel.setLayout(new GridLayout(7,1,10,10));

				secondRightPanel.add(voidButton);
				secondRightPanel.add(lookUpButton);
				secondRightPanel.add(quantityButton);
				secondRightPanel.add(overridePriceButton);
				secondRightPanel.add(discountButton);
				secondRightPanel.add(optionsButton);
				secondRightPanel.add(enterButton);

				firstRightPanel.add(notesPanel);
				firstRightPanel.add(exactCashPanel);

				rightPanel.add(firstRightPanel);
				rightPanel.add(secondRightPanel);
				fullScreenPanel.add(rightPanel);


				myFrame.setVisible(true);
				barcodeEntryField.requestFocusInWindow();

				lookUpButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JFrame myFrame = new JFrame();
						myFrame.setTitle("Lookup Item");
						myFrame.setSize(900,500);
						//						Parameters: numRows, numColumns, Hgap, Vgap
						myFrame.setLocationRelativeTo(null);
						myFrame.setVisible(true);

						JPanel fullScreenPanel = new JPanel();
						TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
						fullScreenPanel.setBorder(fullScreenTitle);
						fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
						myFrame.add(fullScreenPanel);


						JPanel topPanel = new JPanel();
						TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
						topPanel.setBorder(topPanelTitle);
						topPanel.setLayout(new GridLayout(1,2,10,10));
						fullScreenPanel.add(topPanel);

						JPanel bottomPanel = new JPanel();
						TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
						bottomPanel.setBorder(bottomPanelTitle);
						bottomPanel.setLayout(new GridLayout(1,2,10,10));
						bottomPanel.setSize(700,240);
						fullScreenPanel.add(bottomPanel);

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
						bottomPanel.add(scrlPane);

						barcodeEntryField.requestFocusInWindow();
					}

				});

				voidButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						barcodeEntryField.requestFocusInWindow();
					}
				});

				barcodeEntryField.addKeyListener(new KeyAdapter()
				{
					public void keyPressed(KeyEvent e)
					{
						if (e.getKeyCode() == KeyEvent.VK_F2)
						{
							JFrame myFrame = new JFrame();
							myFrame.setTitle("Lookup Item");
							myFrame.setSize(900,500);
							//						Parameters: numRows, numColumns, Hgap, Vgap
							myFrame.setLocationRelativeTo(null);
							myFrame.setVisible(true);

							JPanel fullScreenPanel = new JPanel();
							//						TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
							//						fullScreenPanel.setBorder(fullScreenTitle);
							fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
							myFrame.add(fullScreenPanel);

							JPanel topPanel = new JPanel();
							//						TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
							//						topPanel.setBorder(topPanelTitle);
							topPanel.setLayout(new GridLayout(1,3,10,10));
							fullScreenPanel.add(topPanel);

							JPanel topLeftPanel = new JPanel();
							//						TitledBorder topLeftPanelTitle = new TitledBorder("Left Panel:");
							//						topLeftPanel.setBorder(topLeftPanelTitle);
							topLeftPanel.setLayout(new GridLayout(4,1,10,10));
							topPanel.add(topLeftPanel);

							JLabel barcodeLabel = new JLabel("Barcode:");
							JTextField barcodeEntryField = new JTextField(13);
							topLeftPanel.add(barcodeLabel);
							topLeftPanel.add(barcodeEntryField);

							JLabel pCodeLabel = new JLabel("Product Code:");
							JTextField pCodeEntryField = new JTextField(13);
							topLeftPanel.add(pCodeLabel);
							topLeftPanel.add(pCodeEntryField);

							JPanel topMiddlePanel = new JPanel();
							TitledBorder topMiddlePanelTitle = new TitledBorder("Search by:");
							topMiddlePanel.setBorder(topMiddlePanelTitle);
							topMiddlePanel.setLayout(new GridLayout(3,1,10,10));
							topPanel.add(topMiddlePanel);

							JTextField searchField = new JTextField();
							topMiddlePanel.add(searchField);

							JLabel comboboxLabel = new JLabel("Category:");
							topMiddlePanel.add(comboboxLabel);
							String[] selectSearch = { "Pet", "Homeware" };
							//Create the combo box, select item at index 1.
							@SuppressWarnings({ "unchecked", "rawtypes" })
							JComboBox searchComboBox = new JComboBox(selectSearch);
							searchComboBox.setSelectedIndex(1);
							topMiddlePanel.add(searchComboBox);
							searchComboBox.setEnabled(true);

							JPanel topRightPanel = new JPanel();
							//						TitledBorder topRightPanelTitle = new TitledBorder("Right Panel:");
							//						topRightPanel.setBorder(topRightPanelTitle);
							topRightPanel.setLayout(new GridLayout(3,1,10,10));
							topPanel.add(topRightPanel);

							JButton searchButton = new JButton("Search!");
							topRightPanel.add(searchButton);

							JLabel spaceLabel = new JLabel();
							topRightPanel.add(spaceLabel);

							JButton selectButton = new JButton("Select");
							topRightPanel.add(selectButton);

							JPanel bottomPanel = new JPanel();
							//						TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
							//						bottomPanel.setBorder(bottomPanelTitle);
							bottomPanel.setLayout(new GridLayout(1,1,10,10));
							fullScreenPanel.add(bottomPanel);

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
							bottomPanel.add(scrlPane);
						}
					}
				});

				quantityButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
						barcodeEntryField.requestFocusInWindow();
					}
				});

				barcodeEntryField.addKeyListener(new KeyAdapter()
				{
					public void keyPressed(KeyEvent e)
					{
						if (e.getKeyCode() == KeyEvent.VK_F3)
							JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);

					}
				});

				overridePriceButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JOptionPane.showInputDialog(null,"Enter Overriding Price:","Override",JOptionPane.PLAIN_MESSAGE);
						barcodeEntryField.requestFocusInWindow();
					}
				});

				barcodeEntryField.addKeyListener(new KeyAdapter()
				{
					public void keyPressed(KeyEvent e)
					{
						if (e.getKeyCode() == KeyEvent.VK_F4)
							JOptionPane.showInputDialog(null,"Enter Overriding Price:","Override",JOptionPane.PLAIN_MESSAGE);	
					}
				});

				discountButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JOptionPane.showInputDialog(null,"Enter Discount (%):","Discount",JOptionPane.PLAIN_MESSAGE);
						barcodeEntryField.requestFocusInWindow();
					}
				});

				barcodeEntryField.addKeyListener(new KeyAdapter()
				{
					public void keyPressed(KeyEvent e)
					{
						if (e.getKeyCode() == KeyEvent.VK_F5)
							JOptionPane.showInputDialog(null,"Enter Discount (%):","Discount",JOptionPane.PLAIN_MESSAGE);	
					}
				});

			}
		});

		dashboardButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame myFrame = new JFrame();
				myFrame.setTitle("Make Sale");
				myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
				myFrame.setLocationRelativeTo(null);
				myFrame.setVisible(true);
			}
		});

		inventoryButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame myFrame = new JFrame();
				myFrame.setTitle("Product Inventory");
				myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
				myFrame.setLocationRelativeTo(null);

				//Full Screen Panel over Frame
				JPanel fullScreenPanel = new JPanel();
				TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
				fullScreenPanel.setBorder(fullScreenTitle);
				fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
				myFrame.add(fullScreenPanel);

				//SECTION PANELS within the Full Screen Panel

				JPanel topPanel = new JPanel();		//top panel in the FULL SCREEN Panel
				TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
				topPanel.setBorder(topPanelTitle);
				topPanel.setLayout(new GridLayout(3,2,10,10));
				fullScreenPanel.add(topPanel);

				JLabel inventoryLabel = new JLabel("Product Inventory");
				Font myFont = new Font("SansSerif", Font.BOLD, 42);
				inventoryLabel.setFont(myFont);
				topPanel.add(inventoryLabel);

				JPanel bottomPanel = new JPanel();		//bottom panel in the FULL SCREEN Panel
				TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
				bottomPanel.setBorder(bottomPanelTitle);
				bottomPanel.setLayout(new GridLayout(1,3,10,10));
				fullScreenPanel.add(bottomPanel);

				JPanel bottomLeftPanel = new JPanel();	//left panel in the BOTTOM Panel
				bottomLeftPanel.setLayout(new GridLayout(2,1,10,10));
				fullScreenPanel.add(bottomLeftPanel);
				bottomPanel.add(bottomLeftPanel);

				JPanel bottomMiddlePanel = new JPanel();	//middle panel in the BOTTOM Panel
				bottomMiddlePanel.setLayout(new GridLayout(1,1,10,10));
				bottomPanel.add(bottomMiddlePanel);

				JPanel bottomRightPanel = new JPanel();
				bottomRightPanel.setLayout(new GridLayout(2,1,10,10));
				bottomPanel.add(bottomRightPanel);

				JButton viewStockButton = new JButton("View Inventory Stock");
				bottomMiddlePanel.add(viewStockButton);

				JButton addProductButton = new JButton("Add Product");
				bottomLeftPanel.add(addProductButton);

				JButton modifyProductButton = new JButton("Modify Product");
				bottomLeftPanel.add(modifyProductButton);

				JButton importCSVButton = new JButton("Import CSV");
				bottomRightPanel.add(importCSVButton);

				JButton addSupplierButton = new JButton("Add Supplier");
				bottomRightPanel.add(addSupplierButton);


				viewStockButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JFrame viewStockFrame = new JFrame ();//Creating frame
						
						JPanel MAINPANEL = new JPanel (new GridLayout(1,2,2,2));//Creating the main panel 
						TitledBorder MAINPANELtitle = new TitledBorder("MAIN");
						MAINPANEL.setBorder(MAINPANELtitle);
						viewStockFrame.add(MAINPANEL);
						
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
						TitledBorder Resultcanceltitle = new TitledBorder("Result/Cancel/Export");
						buttonPANEL.setBorder(Resultcanceltitle);
						
						JPanel resultCancelPANEL = new JPanel(new GridLayout(1, 2, 10, 1));//Panel for result and cancel
						
						JPanel exportPanel = new JPanel(new GridLayout(1, 1, 1, 1));//Panel for export 
						
						JButton resultButton = new JButton ("Result");
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
//								viewStockFrame.dispose();
								
							}
						});
						
						cancelButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								viewStockFrame.dispose();
								
							}
						});
						
						exportButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
//								viewStockFrame.dispose();
								
							}
						});
						
						p2.add(buttonPANEL);//Adding Button panel to the main function panel (P2)
						/** End of Button panel adding **/
						
						MAINPANEL.add(p1);
						MAINPANEL.add(p2);		
						
						viewStockFrame.setExtendedState(viewStockFrame.MAXIMIZED_BOTH);
						viewStockFrame.setLocationRelativeTo(null);
						viewStockFrame.setVisible(true);

					}
				});
				
				addProductButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JFrame addProductFRAME = new JFrame ();//Creating frame
						
						
						JPanel addProductpanel = new JPanel(new GridLayout (3, 1, 1, 1));
						TitledBorder mainPanelT = new TitledBorder("Add Product");
						addProductpanel.setBorder(mainPanelT);
						addProductFRAME.add(addProductpanel);
						
						JPanel outtitlePanel = new JPanel(new BorderLayout ());
						JPanel intitlePanel = new JPanel(new GridLayout(1,1,1,1));
						JLabel frameTitle = new JLabel ("Add Product");
						Font font = new Font("SansSerif", Font.BOLD, 42);
						frameTitle.setFont(font);
						
						intitlePanel.add(frameTitle);
						outtitlePanel.add(intitlePanel, BorderLayout.NORTH);
						addProductpanel.add(outtitlePanel);
						
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
						
						
						addProductFRAME.setExtendedState(addProductFRAME.MAXIMIZED_BOTH);
						addProductFRAME.setLocationRelativeTo(null);
						addProductFRAME.setVisible(true);
						barcodeText.requestFocusInWindow();

						saveButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
//								addProductFRAME.dispose();
								
							}
						});
						
						cancelButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								addProductFRAME.dispose();
								
							}
						});
					}
				});
				
				modifyProductButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
						
					}
				});
				
				importCSVButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
						
					}
				});
				
				addSupplierButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
						
					}
				});
				
				myFrame.setVisible(true);
			}
		});

		reportsButton.addActionListener(new ActionListener()	//creates the report main menu from Main Menu
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame reportsMenuFrame = new JFrame();		//creates the Reports frame 
				reportsMenuFrame.setTitle("Reports Menu");
				//			myFrame.setSize(900,500);
				reportsMenuFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
				//Parameters: numRows, numColumns, Hgap, Vgap
				reportsMenuFrame.setLocationRelativeTo(null);

				//Full Screen Panel
				JPanel fullScreenPanel = new JPanel();
				TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
				fullScreenPanel.setBorder(fullScreenTitle);
				fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
				reportsMenuFrame.add(fullScreenPanel);


				//SECTION PANELS

				JPanel topPanel = new JPanel();
				TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
				topPanel.setBorder(topPanelTitle);
				topPanel.setLayout(new GridLayout(3,2,10,10));
				fullScreenPanel.add(topPanel);

				JLabel inventoryLabel = new JLabel("Reports");
				Font myFont = new Font("SansSerif", Font.BOLD, 42);
				inventoryLabel.setFont(myFont);
				topPanel.add(inventoryLabel);

				JPanel bottomPanel = new JPanel();
				TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
				bottomPanel.setBorder(bottomPanelTitle);
				bottomPanel.setLayout(new GridLayout(2,2,10,10));
				fullScreenPanel.add(bottomPanel);


				JButton salesReportButton = new JButton("Sales Report");
				bottomPanel.add(salesReportButton);

				JButton refundReportButton = new JButton("Refund Report");
				bottomPanel.add(refundReportButton);

				JButton slowSellersButton = new JButton("Slow Sellers Report");
				bottomPanel.add(slowSellersButton);

				JButton topSellersButton = new JButton("Top Seller Report");
				bottomPanel.add(topSellersButton);

				reportsMenuFrame.setVisible(true);

				salesReportButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JFrame myFrame = new JFrame();
						myFrame.setTitle("Sales Reports");
						//			myFrame.setSize(900,500);
						myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
						//Parameters: numRows, numColumns, Hgap, Vgap
						myFrame.setLocationRelativeTo(null);

						//Full Screen Panel
						JPanel fullScreenPanel = new JPanel();
						TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
						fullScreenPanel.setBorder(fullScreenTitle);
						fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
						myFrame.add(fullScreenPanel);


						//SECTION PANELS

						JPanel topPanel = new JPanel();
						TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
						topPanel.setBorder(topPanelTitle);
						topPanel.setLayout(new GridLayout(3,2,10,10));
						fullScreenPanel.add(topPanel);

						JLabel salesReportsLabel = new JLabel("Sales Reports");
						Font myFont = new Font("SansSerif", Font.BOLD, 42);
						salesReportsLabel.setFont(myFont);
						topPanel.add(salesReportsLabel);

						JPanel bottomPanel = new JPanel();
						TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
						bottomPanel.setBorder(bottomPanelTitle);
						bottomPanel.setLayout(new GridLayout(2,2,10,10));
						fullScreenPanel.add(bottomPanel);


						JButton singleDaySaleButton = new JButton("Single Day Report");
						bottomPanel.add(singleDaySaleButton);

						JButton timePeriodSaleButton = new JButton("Time-Period Report");
						bottomPanel.add(timePeriodSaleButton);

						myFrame.setVisible(true);

						singleDaySaleButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								JFrame myFrame = new JFrame();
								myFrame.setTitle("Single Day Report");
								myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
								myFrame.setLocationRelativeTo(null);

								//Full Screen Panel
								JPanel fullScreenPanel = new JPanel();
								TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
								fullScreenPanel.setBorder(fullScreenTitle);
								fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
								myFrame.add(fullScreenPanel);


								//SECTION PANELS

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
								TitledBorder datePanelTitle = new TitledBorder("View Date:");
								datePanel.setBorder(datePanelTitle);
								datePanel.setLayout(new GridLayout(3,2,10,10));
								
								JTextField viewDate = new JTextField();
								JLabel viewDateLabel = new JLabel("View Date:");
								JLabel viewDateExample = new JLabel("e.g. 24/03/2014");
								datePanel.add(viewDateLabel);
								datePanel.add(viewDate);
								datePanel.add(viewDateExample);

								
								rightPanel.add(datePanel);

								
								rightPanel.add(datePanel);
								
								JPanel reportTypePanel = new JPanel();
								TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
								reportTypePanel.setBorder(reportTypePanelTitle);
								reportTypePanel.setLayout(new GridLayout(3,1,10,10));
								rightPanel.add(reportTypePanel);

								JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
								JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
								JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
								ButtonGroup myGroup = new ButtonGroup();

								myGroup.add(salesByDollar);
								myGroup.add(salesByVolume);
								myGroup.add(profitByDollar);

								reportTypePanel.add(salesByDollar);
								reportTypePanel.add(salesByVolume);
								reportTypePanel.add(profitByDollar);

								JPanel shownAsPanel = new JPanel();
								TitledBorder shownAsPanelTitle = new TitledBorder("Shown as:");
								shownAsPanel.setBorder(shownAsPanelTitle);
								shownAsPanel.setLayout(new GridLayout(1,2,10,10));
								rightPanel.add(shownAsPanel);

								JButton barGraph = new JButton("Bar Graph");
								shownAsPanel.add(barGraph);
								JButton lineGraph = new JButton("Line Graph");
								shownAsPanel.add(lineGraph);

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
//										myFrame.dispose();
									}
								});

								JButton backButton = new JButton("Back");
								resultsButtonPanel.add(backButton);

								backButton.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent arg0) 
									{
										myFrame.dispose();
									}
								});


								myFrame.setVisible(true);
							}
						});
						
						timePeriodSaleButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								JFrame myFrame = new JFrame();
								myFrame.setTitle("Time-Period Report");
								//			myFrame.setSize(900,500);
								myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
								//Parameters: numRows, numColumns, Hgap, Vgap
								myFrame.setLocationRelativeTo(null);

								//Full Screen Panel
								JPanel fullScreenPanel = new JPanel();
								TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
								fullScreenPanel.setBorder(fullScreenTitle);
								fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
								myFrame.add(fullScreenPanel);


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
								
								JPanel reportTypePanel = new JPanel();
								TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
								reportTypePanel.setBorder(reportTypePanelTitle);
								reportTypePanel.setLayout(new GridLayout(3,1,10,10));
								rightPanel.add(reportTypePanel);

								JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
								JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
								JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
								ButtonGroup reportTypeGroup = new ButtonGroup();

								reportTypeGroup.add(salesByDollar);
								reportTypeGroup.add(salesByVolume);
								reportTypeGroup.add(profitByDollar);

								reportTypePanel.add(salesByDollar);
								reportTypePanel.add(salesByVolume);
								reportTypePanel.add(profitByDollar);

								JPanel groupTypePanel = new JPanel();
								TitledBorder groupTypePanelTitle = new TitledBorder("Group by:");
								groupTypePanel.setBorder(groupTypePanelTitle);
								groupTypePanel.setLayout(new GridLayout(3,1,10,10));
								rightPanel.add(groupTypePanel);

								JRadioButton dayRadio = new JRadioButton("Day", true);
								JRadioButton weekRadio = new JRadioButton("Week", false);
								JRadioButton monthRadio = new JRadioButton("Month", false);
								ButtonGroup groupTypeGroup = new ButtonGroup();

								groupTypeGroup.add(dayRadio);
								groupTypeGroup.add(weekRadio);
								groupTypeGroup.add(monthRadio);

								groupTypePanel.add(dayRadio);
								groupTypePanel.add(weekRadio);
								groupTypePanel.add(monthRadio);
								
								JPanel shownAsPanel = new JPanel();
								TitledBorder shownAsPanelTitle = new TitledBorder("Shown as:");
								shownAsPanel.setBorder(shownAsPanelTitle);
								shownAsPanel.setLayout(new GridLayout(1,2,10,10));
								rightPanel.add(shownAsPanel);

								JButton barGraph = new JButton("Bar Graph");
								shownAsPanel.add(barGraph);
								JButton lineGraph = new JButton("Line Graph");
								shownAsPanel.add(lineGraph);

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
//										myFrame.dispose();
									}
								});

								JButton backButton = new JButton("Back");
								resultsButtonPanel.add(backButton);

								backButton.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent arg0) 
									{
										myFrame.dispose();
									}
								});


								myFrame.setVisible(true);
							}
						});
					}
				});


				refundReportButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JFrame myFrame = new JFrame();
						myFrame.setTitle("Refund Reports");
						//			myFrame.setSize(900,500);
						myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
						//Parameters: numRows, numColumns, Hgap, Vgap
						myFrame.setLocationRelativeTo(null);

						//Full Screen Panel
						JPanel fullScreenPanel = new JPanel();
						TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
						fullScreenPanel.setBorder(fullScreenTitle);
						fullScreenPanel.setLayout(new GridLayout(2,1,10,10));
						myFrame.add(fullScreenPanel);


						//SECTION PANELS

						JPanel topPanel = new JPanel();
						TitledBorder topPanelTitle = new TitledBorder("Top Panel:");
						topPanel.setBorder(topPanelTitle);
						topPanel.setLayout(new GridLayout(3,2,10,10));
						fullScreenPanel.add(topPanel);

						JLabel inventoryLabel = new JLabel("Refund Reports");
						Font myFont = new Font("SansSerif", Font.BOLD, 42);
						inventoryLabel.setFont(myFont);
						topPanel.add(inventoryLabel);

						JPanel bottomPanel = new JPanel();
						TitledBorder bottomPanelTitle = new TitledBorder("Bottom Panel:");
						bottomPanel.setBorder(bottomPanelTitle);
						bottomPanel.setLayout(new GridLayout(2,2,10,10));
						fullScreenPanel.add(bottomPanel);


						JButton singleDayRefundButton = new JButton("Single Day Report");
						bottomPanel.add(singleDayRefundButton);

						JButton timePeriodRefundButton = new JButton("Time-Period Report");
						bottomPanel.add(timePeriodRefundButton);

						myFrame.setVisible(true);
						
						singleDayRefundButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								JFrame myFrame = new JFrame();
								myFrame.setTitle("Single Day Report");
								//			myFrame.setSize(900,500);
								myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
								//Parameters: numRows, numColumns, Hgap, Vgap
								myFrame.setLocationRelativeTo(null);

								//Full Screen Panel
								JPanel fullScreenPanel = new JPanel();
								TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
								fullScreenPanel.setBorder(fullScreenTitle);
								fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
								myFrame.add(fullScreenPanel);


								//SECTION PANELS

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
										{"DGKF353","4256985216","Cat","Pet","$40"}
								};

								JTable lookUpTable = new JTable(data, colNames);
								JScrollPane scrlPane = new JScrollPane(lookUpTable);
								leftPanel.add(scrlPane);
								
								JPanel datePanel = new JPanel();
								TitledBorder datePanelTitle = new TitledBorder("Report Type:");
								datePanel.setBorder(datePanelTitle);
								datePanel.setLayout(new GridLayout(3,2,10,10));
								
								JTextField viewDate = new JTextField();
								JLabel viewDateLabel = new JLabel("View Date:");
								JLabel viewDateExample = new JLabel("e.g. 24/03/2014");
								datePanel.add(viewDateLabel);
								datePanel.add(viewDate);
								datePanel.add(viewDateExample);

								
								rightPanel.add(datePanel);
								
								JPanel reportTypePanel = new JPanel();
								TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
								reportTypePanel.setBorder(reportTypePanelTitle);
								reportTypePanel.setLayout(new GridLayout(3,1,10,10));
								rightPanel.add(reportTypePanel);

								JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
								JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
								JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
								ButtonGroup myGroup = new ButtonGroup();

								myGroup.add(salesByDollar);
								myGroup.add(salesByVolume);
								myGroup.add(profitByDollar);

								reportTypePanel.add(salesByDollar);
								reportTypePanel.add(salesByVolume);
								reportTypePanel.add(profitByDollar);

								JPanel shownAsPanel = new JPanel();
								TitledBorder shownAsPanelTitle = new TitledBorder("Shown as:");
								shownAsPanel.setBorder(shownAsPanelTitle);
								shownAsPanel.setLayout(new GridLayout(1,1,10,10));
								rightPanel.add(shownAsPanel);

								JButton barGraph = new JButton("Bar Graph");
								shownAsPanel.add(barGraph);

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
//										myFrame.dispose();
									}
								});

								JButton backButton = new JButton("Back");
								resultsButtonPanel.add(backButton);

								backButton.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent arg0) 
									{
										myFrame.dispose();
									}
								});


								myFrame.setVisible(true);
							}
						});
						
						timePeriodRefundButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								JFrame myFrame = new JFrame();
								myFrame.setTitle("Time-Period Report");
								//			myFrame.setSize(900,500);
								myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
								//Parameters: numRows, numColumns, Hgap, Vgap
								myFrame.setLocationRelativeTo(null);

								//Full Screen Panel
								JPanel fullScreenPanel = new JPanel();
								TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
								fullScreenPanel.setBorder(fullScreenTitle);
								fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
								myFrame.add(fullScreenPanel);


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
								
								JPanel reportTypePanel = new JPanel();
								TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
								reportTypePanel.setBorder(reportTypePanelTitle);
								reportTypePanel.setLayout(new GridLayout(3,1,10,10));
								rightPanel.add(reportTypePanel);

								JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
								JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
								JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
								ButtonGroup reportTypeGroup = new ButtonGroup();

								reportTypeGroup.add(salesByDollar);
								reportTypeGroup.add(salesByVolume);
								reportTypeGroup.add(profitByDollar);

								reportTypePanel.add(salesByDollar);
								reportTypePanel.add(salesByVolume);
								reportTypePanel.add(profitByDollar);

								JPanel groupTypePanel = new JPanel();
								TitledBorder groupTypePanelTitle = new TitledBorder("Group by:");
								groupTypePanel.setBorder(groupTypePanelTitle);
								groupTypePanel.setLayout(new GridLayout(3,1,10,10));
								rightPanel.add(groupTypePanel);

								JRadioButton dayRadio = new JRadioButton("Day", true);
								JRadioButton weekRadio = new JRadioButton("Week", false);
								JRadioButton monthRadio = new JRadioButton("Month", false);
								ButtonGroup groupTypeGroup = new ButtonGroup();

								groupTypeGroup.add(dayRadio);
								groupTypeGroup.add(weekRadio);
								groupTypeGroup.add(monthRadio);

								groupTypePanel.add(dayRadio);
								groupTypePanel.add(weekRadio);
								groupTypePanel.add(monthRadio);
								
								JPanel shownAsPanel = new JPanel();
								TitledBorder shownAsPanelTitle = new TitledBorder("Shown as:");
								shownAsPanel.setBorder(shownAsPanelTitle);
								shownAsPanel.setLayout(new GridLayout(1,2,10,10));
								rightPanel.add(shownAsPanel);

								JButton barGraph = new JButton("Bar Graph");
								shownAsPanel.add(barGraph);

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
//										myFrame.dispose();
									}
								});

								JButton backButton = new JButton("Back");
								resultsButtonPanel.add(backButton);

								backButton.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent arg0) 
									{
										myFrame.dispose();
									}
								});


								myFrame.setVisible(true);
							}
						});
						
					}
				});
				
				
				slowSellersButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
							JFrame myFrame = new JFrame();
							myFrame.setTitle("Slow Sellers Report");
							//			myFrame.setSize(900,500);
							myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
							//Parameters: numRows, numColumns, Hgap, Vgap
							myFrame.setLocationRelativeTo(null);

							//Full Screen Panel
							JPanel fullScreenPanel = new JPanel();
							TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
							fullScreenPanel.setBorder(fullScreenTitle);
							fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
							myFrame.add(fullScreenPanel);


							//SECTION PANELS

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
									{"DGKF353","4256985216","Cat","Pet","$40"}
							};

							JTable lookUpTable = new JTable(data, colNames);
							JScrollPane scrlPane = new JScrollPane(lookUpTable);
							leftPanel.add(scrlPane);
							
							JPanel datePanel = new JPanel();
							TitledBorder datePanelTitle = new TitledBorder("Select Date:");
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

							
							JPanel labelPanel = new JPanel();
							TitledBorder labelPanelTitle = new TitledBorder("Report Type:");
							labelPanel.setBorder(labelPanelTitle);
							labelPanel.setLayout(new GridLayout(3,1,10,10));
							rightPanel.add(labelPanel);

							JLabel soldUnitsLabel = new JLabel("Product units sold,");
							JLabel soldUnitsLabel2 = new JLabel("less than or equal to:");
							Font myFont = new Font("SansSerif", Font.BOLD, 42);
							soldUnitsLabel.setFont(myFont);
							soldUnitsLabel2.setFont(myFont);
							labelPanel.add(soldUnitsLabel);
							labelPanel.add(soldUnitsLabel2);

							JPanel inputUnitsPanel = new JPanel();
							TitledBorder inputUnitsPanelTitle = new TitledBorder("Input:");
							inputUnitsPanel.setBorder(inputUnitsPanelTitle);
							inputUnitsPanel.setLayout(new GridLayout(3,1,10,10));
							rightPanel.add(inputUnitsPanel);

							JLabel unitsInputLabel = new JLabel("Input:");
							JTextField units = new JTextField("");
							JLabel unitsInputExample = new JLabel("e.g. 10");
							inputUnitsPanel.add(unitsInputLabel);
							inputUnitsPanel.add(units);
							inputUnitsPanel.add(unitsInputExample);

							JPanel resultsButtonPanel = new JPanel();
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
//									myFrame.dispose();
								}
							});

							JButton backButton = new JButton("Back");
							resultsButtonPanel.add(backButton);

							backButton.addActionListener(new ActionListener()
							{

								@Override
								public void actionPerformed(ActionEvent arg0) 
								{
									myFrame.dispose();
								}
							});


							myFrame.setVisible(true);
						}
					});
					
				
				topSellersButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
							JFrame myFrame = new JFrame();
							myFrame.setTitle("Top Sellers Report");
							//			myFrame.setSize(900,500);
							myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
							//Parameters: numRows, numColumns, Hgap, Vgap
							myFrame.setLocationRelativeTo(null);

							//Full Screen Panel
							JPanel fullScreenPanel = new JPanel();
							TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
							fullScreenPanel.setBorder(fullScreenTitle);
							fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
							myFrame.add(fullScreenPanel);


							//SECTION PANELS

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
									{"DGKF353","4256985216","Cat","Pet","$40"}
							};

							JTable lookUpTable = new JTable(data, colNames);
							JScrollPane scrlPane = new JScrollPane(lookUpTable);
							leftPanel.add(scrlPane);
							
							JPanel datePanel = new JPanel();
							TitledBorder datePanelTitle = new TitledBorder("Select Date:");
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
							
							JPanel labelPanel = new JPanel();
							TitledBorder labelPanelTitle = new TitledBorder("Report Type:");
							labelPanel.setBorder(labelPanelTitle);
							labelPanel.setLayout(new GridLayout(3,1,10,10));
							rightPanel.add(labelPanel);

							JLabel soldUnitsLabel = new JLabel("Number of Top");
							JLabel soldUnitsLabel2 = new JLabel("Products:");
							Font myFont = new Font("SansSerif", Font.BOLD, 42);
							soldUnitsLabel.setFont(myFont);
							soldUnitsLabel2.setFont(myFont);
							labelPanel.add(soldUnitsLabel);
							labelPanel.add(soldUnitsLabel2);

							JPanel inputUnitsPanel = new JPanel();
							TitledBorder inputUnitsPanelTitle = new TitledBorder("Input:");
							inputUnitsPanel.setBorder(inputUnitsPanelTitle);
							inputUnitsPanel.setLayout(new GridLayout(3,1,10,10));
							rightPanel.add(inputUnitsPanel);

							JLabel unitsInputLabel = new JLabel("Input:");
							JTextField units = new JTextField("");
							JLabel unitsInputExample = new JLabel("e.g. 10");
							inputUnitsPanel.add(unitsInputLabel);
							inputUnitsPanel.add(units);
							inputUnitsPanel.add(unitsInputExample);

							JPanel resultsButtonPanel = new JPanel();
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
//									myFrame.dispose();
								}
							});

							JButton backButton = new JButton("Back");
							resultsButtonPanel.add(backButton);

							backButton.addActionListener(new ActionListener()
							{

								@Override
								public void actionPerformed(ActionEvent arg0) 
								{
									myFrame.dispose();
								}
							});


							myFrame.setVisible(true);
						}
					});
			}
		});




	}
}
