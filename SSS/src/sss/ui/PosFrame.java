/* PosFrame Class
 * 
 * Frame design to supported the 'Make Sale' function
 * 
 * Original Author: Amethyst Mayer
 */

package sss.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import sss.domain.NonEditableTableModel;
import sss.domain.Register;
import sss.services.SaleListener;

@SuppressWarnings("serial")
public class PosFrame extends JFrame implements SaleListener {

	static final BigDecimal FIVE_DOLLARS = new BigDecimal(5);
	static final BigDecimal TEN_DOLLARS = new BigDecimal(10);
	static final BigDecimal TWENTY_DOLLARS = new BigDecimal(20);
	static final BigDecimal FIFTY_DOLLARS = new BigDecimal(50);
	static final BigDecimal HUNDRED_DOLLARS = new BigDecimal(100);
	
	static final String ONE = "1";
	static final String TWO = "2";
	static final String THREE = "3";
	static final String FOUR = "4";
	static final String FIVE = "5";
	static final String SIX = "6";
	static final String SEVEN = "7";
	static final String EIGHT = "8";
	static final String NINE = "9";
	static final String ZERO = "0";
	static final String DECIMAL_POINT = ".";
	
	private JLabel saleTotalLabel = new JLabel("TOTAL: $0.00");
	private JLabel saleBalanceLabel = new JLabel("Change: $0.00");

	Register register = new Register();

	public PosFrame() {

		setTitle("Make Sale");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


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

		NonEditableTableModel dataModel = register.getDataModel();
		JTable lookUpTable = new JTable(dataModel);
		TableColumnModel columnModel =lookUpTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100); // Sets preferred width of the Product ID wide enough to display barcode without resizing
		columnModel.getColumn(2).setPreferredWidth(200); // Sets preferred width of the Product Name 
		lookUpTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row of table able to be selected at a time

		JScrollPane scrollPane = new JScrollPane(lookUpTable);
		itemsPanel.add(scrollPane);

		Font myFont = new Font("SansSerif", Font.BOLD, 29); //Label Fonts

		JPanel showAmount = new JPanel();
		showAmount.setLayout(new GridLayout(2,1,10,10));

		saleBalanceLabel.setFont(myFont);

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
		middlePanel.setLayout(new GridLayout(3,1,10,10));

		JPanel topMiddlePanel = new JPanel();
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
		midMiddlePanel.setLayout(new GridLayout(2,3,10,10));


		JPanel botMiddlePanel = new JPanel();
		botMiddlePanel.setLayout(new GridLayout(2,3,10,10));
		
		Font myFont1 = new Font("SansSerif", Font.BOLD, 35);
		

		JButton Button1 = new JButton("1");
		Button1.setFont(myFont1);
		JButton Button2 = new JButton("2");
		Button2.setFont(myFont1);
		JButton Button3 = new JButton("3");
		Button3.setFont(myFont1);
		JButton Button4 = new JButton("4");
		Button4.setFont(myFont1);
		JButton Button5 = new JButton("5");
		Button5.setFont(myFont1);
		JButton Button6 = new JButton("6");
		Button6.setFont(myFont1);
		JButton Button7 = new JButton("7");
		Button7.setFont(myFont1);
		JButton Button8 = new JButton("8");
		Button8.setFont(myFont1);
		JButton Button9 = new JButton("9");
		Button9.setFont(myFont1);
		JButton Buttondec = new JButton(".");
		Buttondec.setFont(myFont1);
		JButton Button0 = new JButton("0");
		Button0.setFont(myFont1);
		JButton Buttondel = new JButton("del");
		Buttondel.setFont(myFont1);

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
		rightPanel.setLayout(new GridLayout(1,2,10,10));

		JButton voidButton = new JButton("f1 Void");
		JButton lookUpButton = new JButton("f2 Lookup Item");
		JButton quantityButton = new JButton("f3 Quantity");
		JButton discountButton = new JButton("f4 Discount");
		JButton enterButton = new JButton("ENTER");

		JPanel firstRightPanel = new JPanel();
		firstRightPanel.setLayout(new GridLayout(2,1,10,10));

		JButton note5Button = new JButton("$5");
		JButton note10Button = new JButton("$10");
		JButton note20Button = new JButton("$20");
		JButton note50Button = new JButton("$50");
		JButton note100Button = new JButton("$100");

		JPanel exactCashPanel = new JPanel();
		JButton exactCashButton = new JButton("Exact Cash");
		exactCashPanel.setLayout(new GridLayout(2,1,10,10));

		//JPanel blankPanel = new JPanel();

		//exactCashPanel.add(blankPanel);
		exactCashPanel.add(exactCashButton);
		exactCashPanel.add(enterButton);


		JPanel notesPanel = new JPanel();		notesPanel.setLayout(new GridLayout(5,1,10,10));

		notesPanel.add(note100Button);
		notesPanel.add(note50Button);
		notesPanel.add(note20Button);
		notesPanel.add(note10Button);
		notesPanel.add(note5Button);


		JPanel secondRightPanel = new JPanel();

		secondRightPanel.setLayout(new GridLayout(4,1,10,10));

		secondRightPanel.add(voidButton);
		secondRightPanel.add(lookUpButton);
		secondRightPanel.add(quantityButton);
		secondRightPanel.add(discountButton);

		firstRightPanel.add(notesPanel);
		firstRightPanel.add(exactCashPanel);

		rightPanel.add(firstRightPanel);
		rightPanel.add(secondRightPanel);
		fullScreenPanel.add(rightPanel);

		// Lookup Product frame
		
		JFrame lookupFrame = new JFrame();
		lookupFrame.setTitle("Lookup Item");
		lookupFrame.setSize(900,500);

		lookupFrame.setLocationRelativeTo(null);

		JPanel gridPanel = new JPanel();

		gridPanel.setLayout(new GridLayout(2,1,10,10));
		lookupFrame.add(gridPanel);

		JPanel topPanel = new JPanel();

		topPanel.setLayout(new GridLayout(1,3,10,10));
		gridPanel.add(topPanel);

		JPanel topLeftPanel = new JPanel();

		topLeftPanel.setLayout(new GridLayout(4,1,10,10));
		topPanel.add(topLeftPanel);

		JLabel barcodeSearchLabel = new JLabel("Barcode:");
		JTextField barcodeSearchField = new JTextField(13);
		topLeftPanel.add(barcodeSearchLabel);
		topLeftPanel.add(barcodeSearchField);

		JLabel pCodeLabel = new JLabel("Product Code:");
		JTextField pCodeEntryField = new JTextField(13);
		topLeftPanel.add(pCodeLabel);
		topLeftPanel.add(pCodeEntryField);

		JPanel topCentrePanel = new JPanel();
		topCentrePanel.setLayout(new GridLayout(4,1,10,10));
		topPanel.add(topCentrePanel);

		JLabel searchByName = new JLabel("Search By Name:");
		topCentrePanel.add(searchByName);
		
		JTextField searchField = new JTextField();
		topCentrePanel.add(searchField);

		JLabel comboboxLabel = new JLabel("Category:");
		topCentrePanel.add(comboboxLabel);

		JComboBox<String> searchComboBox = new JComboBox<String>(register.getCategoryNames());
		searchComboBox.setSelectedIndex(0);
		topCentrePanel.add(searchComboBox);
		searchComboBox.setEnabled(true);

		JPanel topRightPanel = new JPanel();

		topRightPanel.setLayout(new GridLayout(4,1,10,10));
		topPanel.add(topRightPanel);

		JButton searchButton = new JButton("Search");
		topRightPanel.add(searchButton);


		JButton selectButton = new JButton("Select");
//		selectButton.setBackground(Color.BLUE);
//		selectButton.setOpaque(true);
		topRightPanel.add(selectButton);

		JPanel bottomPanel = new JPanel();

		bottomPanel.setLayout(new GridLayout(1,1,10,10));
		gridPanel.add(bottomPanel);

		NonEditableTableModel searchDataModel = register.getSearchDataModel();
		JTable resultsTable = new JTable(searchDataModel);
		resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrlPane = new JScrollPane(resultsTable);
		bottomPanel.add(scrlPane);

		// -- EVENT HANDLERS -------------------------------------

		// Shortcut BUTTON listeners
		voidButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(lookUpTable.getSelectedRow() != -1) {
					register.voidLineItem(lookUpTable.getSelectedRow());
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		lookUpButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				lookupFrame.setVisible(true);
				barcodeEntryField.requestFocusInWindow();
			}
		});

		quantityButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(lookUpTable.getSelectedRow() != -1) {
					try{
						String input = JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
						if(input != null) {
							int newQty = Integer.parseInt(input);
							register.changeLineQuantity(lookUpTable.getSelectedRow(), newQty);
						}
					}
					catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "Error: Invalid quantity", "Invalid Value", JOptionPane.ERROR_MESSAGE);
					}
				}

				barcodeEntryField.requestFocusInWindow();
			}
		});


		discountButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(lookUpTable.getSelectedRow() != -1) {
					try{
						String input = JOptionPane.showInputDialog(null,"Enter Discount (%):","Discount",JOptionPane.PLAIN_MESSAGE);
						if(input != null) {
							double discountPercentage = Double.parseDouble(input);
							register.applyLineDiscount(lookUpTable.getSelectedRow(), discountPercentage);
						}
					}
					catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "Error: Invalid discount", "Invalid Value", JOptionPane.ERROR_MESSAGE);
					}
				}

				barcodeEntryField.requestFocusInWindow();
			}
		});

		// Payment entry BUTTON listeners
		
		note5Button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(register.isActiveSale() && register.getCurrentSaleTotal() != new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN)) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(FIVE_DOLLARS) < 0) {
						register.makePayment(FIVE_DOLLARS);
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: Amount tendered is not enough!", "Payment Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		note10Button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(register.isActiveSale() && register.getCurrentSaleTotal() != new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN)) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(TEN_DOLLARS) < 0) {
						register.makePayment(TEN_DOLLARS);
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: Amount tendered is not enough!", "Payment Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		note20Button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(register.isActiveSale() && register.getCurrentSaleTotal() != new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN)) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(TWENTY_DOLLARS) < 0) {
						register.makePayment(TWENTY_DOLLARS);
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: Amount tendered is not enough!", "Payment Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		note50Button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(register.isActiveSale() && register.getCurrentSaleTotal() != new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN)) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(FIFTY_DOLLARS) < 0) {
						register.makePayment(FIFTY_DOLLARS);
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: Amount tendered is not enough!", "Payment Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		note100Button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(register.isActiveSale() && register.getCurrentSaleTotal() != new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN)) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(HUNDRED_DOLLARS) < 0) {
						register.makePayment(HUNDRED_DOLLARS);
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: Amount tendered is not enough!", "Payment Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		exactCashButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(register.isActiveSale() && register.getCurrentSaleTotal() != new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN)) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					register.makePayment(exactAmount);
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});

		// Keypad BUTTON listeners
		
		Button1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + ONE);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button2.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + TWO);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button3.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + THREE);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button4.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + FOUR);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button5.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + FIVE);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button6.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + SIX);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button7.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + SEVEN);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button8.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + EIGHT);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button9.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + NINE);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Button0.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + ZERO);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Buttondec.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				paymentEntryField.setText(paymentEntryField.getText() + DECIMAL_POINT);
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		Buttondel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int textLength = paymentEntryField.getText().length();
				if(textLength > 0) {
					paymentEntryField.setText(paymentEntryField.getText().substring(0, textLength-1));
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		// Shortcut KEYBOARD listeners
		// Void item Hotkey F1
		barcodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					if(lookUpTable.getSelectedRow() != -1) {
						register.voidLineItem(lookUpTable.getSelectedRow());
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});

		// Lookup Item Hotkey F2
		barcodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F2)
				{
					lookupFrame.setVisible(true);
				}
			}
		});
		
		// Change line quantity Hotkey F3
		barcodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_F3) {
					if(lookUpTable.getSelectedRow() != -1) {
						try{
							String input = JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
							if(input != null) {
								int newQty = Integer.parseInt(input);
								register.changeLineQuantity(lookUpTable.getSelectedRow(), newQty);
							}
						}
						catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Error: Invalid quantity", "Invalid Value", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});

		// Override Price Hotkey F4
		barcodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F4)
					JOptionPane.showInputDialog(null,"Enter Overriding Price:","Override",JOptionPane.PLAIN_MESSAGE);	
			}
		});

		// Discount Hotkey F5
		barcodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F5) {
					if(lookUpTable.getSelectedRow() != -1) {
						try{
							String input = JOptionPane.showInputDialog(null,"Enter Discount (%):","Discount",JOptionPane.PLAIN_MESSAGE);
							if(input != null) {
								double discountPercentage = Double.parseDouble(input);
								register.applyLineDiscount(lookUpTable.getSelectedRow(), discountPercentage);
							}
						}
						catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Error: Invalid discount", "Invalid Value", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				barcodeEntryField.requestFocusInWindow();	
			}
		});

		// Lookup Table key listeners
		// Hotkey F1
		lookUpTable.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					if(lookUpTable.getSelectedRow() != -1) {
						register.voidLineItem(lookUpTable.getSelectedRow());
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});

		// Hotkey F3
		lookUpTable.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_F3) {
					if(lookUpTable.getSelectedRow() != -1) {
						try{
							String input = JOptionPane.showInputDialog(null,"Enter Quantity:","Quanity",JOptionPane.PLAIN_MESSAGE);
							if(input != null) {
								int newQty = Integer.parseInt(input);
								register.changeLineQuantity(lookUpTable.getSelectedRow(), newQty);
							}
						}
						catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Error: Invalid quantity", "Invalid Value", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});

		// Hotkey F5
		lookUpTable.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F5) {
					if(lookUpTable.getSelectedRow() != -1) {
						try{
							String input = JOptionPane.showInputDialog(null,"Enter Discount (%):","Discount",JOptionPane.PLAIN_MESSAGE);
							if(input != null) {
								double discountPercentage = Double.parseDouble(input);
								register.applyLineDiscount(lookUpTable.getSelectedRow(), discountPercentage);
							}
						}
						catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Error: Invalid discount", "Invalid Value", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				barcodeEntryField.requestFocusInWindow();	
			}
		});

		// Enter item event handler
		barcodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e) // keyReleased necessary for Barcode Scanner
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(barcodeEntryField.getText().matches("^\\d{13}$")) { // Matches only 13 digit numbers for barcode entry, will not attempt to lookup other inputs
						try {
							register.beginSale();
							registerFrameAsListener();
							register.enterItem(Long.valueOf(barcodeEntryField.getText()));
							barcodeEntryField.setText("");
							barcodeEntryField.requestFocusInWindow();
						}
						catch (NumberFormatException nfe) { // This should never happen, due to the regex validation
							System.out.println("An NFE exception occurred");
							nfe.printStackTrace();
						}
					}
					else { 
						if(!barcodeEntryField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Error: Invalid barcode", "Invalid Barcode", JOptionPane.ERROR_MESSAGE);
						}
						barcodeEntryField.setText("");
						barcodeEntryField.requestFocusInWindow();
					}
				}



			}
		});

		// Close window handler (shows confirm dialog)
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{

				int confirm = JOptionPane.showOptionDialog(null, "Are you sure you want to close this window?", "Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});


		setVisible(true);
		barcodeEntryField.requestFocusInWindow();
	}

	// -- PosFrame Methods -------------------------------------
	/**
	 * Method for registering this frame as a SaleListener
	 */
	public void registerFrameAsListener() {
		register.registerSaleListener(this);
	}


	@Override
	/**
	 * Method called whenever a Sale object that this frame is listening to updates its balance or total
	 */
	public void update(int eventType, BigDecimal newValue) {
		switch(eventType) {
		case SaleListener.SALE_TOTAL:
			saleTotalLabel.setText("TOTAL: $" + newValue.toString());
			break;
		case SaleListener.SALE_BALANCE:
			saleBalanceLabel.setText("Change: $" + newValue.toString());
			break;
		default:
			break;

		}
	}
}
