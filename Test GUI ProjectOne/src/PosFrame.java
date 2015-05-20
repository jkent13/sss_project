import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class PosFrame extends JFrame {

	public PosFrame() {
		setTitle("Make Sale");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);



		//Full Screen Panel
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
		add(fullScreenPanel);


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

		Font myFont = new Font("SansSerif", Font.BOLD, 29); //Label Fonts

		JPanel showAmount = new JPanel();
		showAmount.setLayout(new GridLayout(2,1,10,10));
		JLabel saleBalanceLabel = new JLabel("Balance:$" + "");
		saleBalanceLabel.setFont(myFont);
		JLabel saleTotalLabel = new JLabel("TOTAL:$" + "");
		saleTotalLabel.setFont(myFont);

		JPanel showBalance = new JPanel();
		JPanel showTotal = new JPanel();

		showBalance.add(saleBalanceLabel);
		showTotal.add(saleTotalLabel);

		showAmount.add(showBalance);
		showAmount.add(showTotal);

		leftPanel.add(itemsPanel);
		leftPanel.add(showAmount);


		JPanel middlePanel = new JPanel();
		//	TitledBorder middlePanelTitle = new TitledBorder("Middle Panel:");
		//	middlePanel.setBorder(middlePanelTitle);
		middlePanel.setLayout(new GridLayout(3,1,10,10));

		JPanel topMiddlePanel = new JPanel();
		//						TitledBorder topMiddlePanelTitle = new TitledBorder("Top Middle Panel:");
		//						topMiddlePanel.setBorder(topMiddlePanelTitle);
		topMiddlePanel.setLayout(new GridLayout(4,2,10,10));

		JLabel barcodeLabel = new JLabel("Barcode:");
		JTextField barcodeEntryField = new JTextField(13);
		barcodeLabel.setFont(myFont);

		JLabel paymentLabel = new JLabel("Payment:");
		JTextField paymentEntryField = new JTextField(5);
		paymentLabel.setFont(myFont);

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
		
		
		setVisible(true);
		barcodeEntryField.requestFocusInWindow();

		
	}
}
