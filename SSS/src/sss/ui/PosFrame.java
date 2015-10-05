/* PosFrame Class
 * 
 * Frame design to supported the 'Make Sale' function
 * 
 * Original Author: Amethyst Mayer
 */

package sss.ui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.net.URL;

import javax.swing.ImageIcon;
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

import sss.domain.LookupFilter;
import sss.domain.NonEditableTableModel;
import sss.domain.Register;
import sss.domain.SaleListener;

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
	
	private JTextField barcodeSearchField;
	private JTextField pCodeEntryField;
	private JTextField searchField;
	private JComboBox<String> searchComboBox;

	private LookupFilter filter = new LookupFilter();
	
	Register register = Register.getInstance();

	public PosFrame() {

		setTitle("Make Sale");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


		//Full Screen Panel
		JPanel fullScreenPanel = new JPanel();
		TitledBorder fullScreenTitle = new TitledBorder("Point-of-Sale");
		fullScreenPanel.setBorder(fullScreenTitle);
		fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
		add(fullScreenPanel);
		
		//Load Image Resources
		
		URL aud5noteUrl = Main.class.getResource("/aud5front.jpg");
		URL aud10noteUrl = Main.class.getResource("/aud10front.jpg");
		URL aud20noteUrl = Main.class.getResource("/aud20front.jpg");
		URL aud50noteUrl = Main.class.getResource("/aud50front.jpg");
		URL aud100noteUrl = Main.class.getResource("/aud100front.jpg");


		//SECTION PANELS

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(2,1,10,10));
		fullScreenPanel.add(leftPanel);

		JPanel itemsPanel = new JPanel();
		itemsPanel.setLayout(new GridLayout(1,1,10,10));

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

		JButton voidButton = new JButton("F1 Void");
		JButton lookUpButton = new JButton("F2 Lookup Product");
		JButton quantityButton = new JButton("F3 Quantity");
		JButton discountButton = new JButton("F4 Discount");
		
		JButton enterButton = new JButton("ENTER");
		Font enterFont = new Font("SansSerif", Font.BOLD, 30);
		enterButton.setFont(enterFont);

		JPanel firstRightPanel = new JPanel();
		firstRightPanel.setLayout(new GridLayout(1,1,10,10));

		ImageIcon fiveNote = new ImageIcon(aud5noteUrl);
		JButton note5Button = new JButton(fiveNote);
		ImageIcon tenNote = new ImageIcon(aud10noteUrl);;
		JButton note10Button = new JButton(tenNote);
		ImageIcon twentyNote = new ImageIcon(aud20noteUrl);
		JButton note20Button = new JButton(twentyNote);
		ImageIcon fiftyNote = new ImageIcon(aud50noteUrl);
		JButton note50Button = new JButton(fiftyNote);
		ImageIcon hundredNote = new ImageIcon(aud100noteUrl);
		JButton note100Button = new JButton(hundredNote);

		JPanel exactCashPanel = new JPanel();
		JButton exactCashButton = new JButton("Exact Cash");
		exactCashButton.setFont(enterFont);
		exactCashPanel.setLayout(new GridLayout(2,1,10,10));

		JPanel notesPanel = new JPanel();		
		notesPanel.setLayout(new GridLayout(7,1,10,10));

		notesPanel.add(note100Button);
		notesPanel.add(note50Button);
		notesPanel.add(note20Button);
		notesPanel.add(note10Button);
		notesPanel.add(note5Button);
		notesPanel.add(exactCashButton);
		notesPanel.add(enterButton);


		JPanel secondRightPanel = new JPanel();

		secondRightPanel.setLayout(new GridLayout(4,1,10,10));

		secondRightPanel.add(voidButton);
		secondRightPanel.add(lookUpButton);
		secondRightPanel.add(quantityButton);
		secondRightPanel.add(discountButton);

		firstRightPanel.add(notesPanel);
//		firstRightPanel.add(exactCashPanel);

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
		barcodeSearchField = new JTextField(13);
		topLeftPanel.add(barcodeSearchLabel);
		topLeftPanel.add(barcodeSearchField);

		JLabel pCodeLabel = new JLabel("Product Code:");
		pCodeEntryField = new JTextField(13);
		topLeftPanel.add(pCodeLabel);
		topLeftPanel.add(pCodeEntryField);

		JPanel topCentrePanel = new JPanel();
		topCentrePanel.setLayout(new GridLayout(4,1,10,10));
		topPanel.add(topCentrePanel);

		JLabel searchByName = new JLabel("Search By Name:");
		topCentrePanel.add(searchByName);
		
		searchField = new JTextField();
		topCentrePanel.add(searchField);

		JLabel comboboxLabel = new JLabel("Category:");
		topCentrePanel.add(comboboxLabel);

		searchComboBox = new JComboBox<String>(register.getCategoryNames());
		searchComboBox.setSelectedIndex(0);
		topCentrePanel.add(searchComboBox);
		searchComboBox.setEnabled(true);

		JPanel topRightPanel = new JPanel();

		topRightPanel.setLayout(new GridLayout(4,1,10,10));
		topPanel.add(topRightPanel);

		JPanel blankSearch = new JPanel();
		topRightPanel.add(blankSearch);
		
		JButton searchButton = new JButton("Search");
		topRightPanel.add(searchButton);

		JPanel blankSelect = new JPanel();
		topRightPanel.add(blankSelect);
		
		JButton selectButton = new JButton("Select");
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
				if(register.saleHasLines()) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(FIVE_DOLLARS) <= 0) {
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
				if(register.saleHasLines()) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(TEN_DOLLARS) <= 0) {
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
				if(register.saleHasLines()) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(TWENTY_DOLLARS) <= 0) {
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
				if(register.saleHasLines()) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(FIFTY_DOLLARS) <= 0) {
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
				if(register.saleHasLines()) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					if(exactAmount.compareTo(HUNDRED_DOLLARS) <= 0) {
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
				if(register.saleHasLines()) { // Change for circumstances where an refund + purchase could total 0.00 - must work from no. of lines or alternative
					BigDecimal exactAmount = register.getCurrentSaleTotal();
					register.makePayment(exactAmount);
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		enterButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(register.saleHasLines()) { 
					try{ 
						BigDecimal exactAmount = register.getCurrentSaleTotal();
						BigDecimal payment = new BigDecimal(paymentEntryField.getText());
						if(exactAmount.compareTo(payment) <= 0) {
							register.makePayment(payment);
						}
						else {
							JOptionPane.showMessageDialog(null, "Error: Amount tendered is not enough!", "Payment Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch (NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Error: An invalid payment amount was entered", "Invalid Amount Tendered", JOptionPane.ERROR_MESSAGE);
						paymentEntryField.setText("");
					}
					
				}
				paymentEntryField.setText("");
				barcodeEntryField.requestFocusInWindow();
			}
		});
		
		paymentEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(register.saleHasLines()) { 
						try{ 
							BigDecimal exactAmount = register.getCurrentSaleTotal();
							BigDecimal payment = new BigDecimal(paymentEntryField.getText());
							if(exactAmount.compareTo(payment) <= 0) {
								register.makePayment(payment);
							}
							else {
								JOptionPane.showMessageDialog(null, "Error: Amount tendered is not enough!", "Payment Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						catch (NumberFormatException nfe){
							JOptionPane.showMessageDialog(null, "Error: An invalid payment amount was entered", "Invalid Amount Tendered", JOptionPane.ERROR_MESSAGE);
							paymentEntryField.setText("");
						}
						
					}
					paymentEntryField.setText("");
					barcodeEntryField.requestFocusInWindow();
				}
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

		// Discount Hotkey F4
		barcodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F4) {
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
		
		// Hotkey F2
		lookUpTable.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F2) {
					lookupFrame.setVisible(true);
				}
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

		// Hotkey F4
		lookUpTable.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_F4) {
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
							JOptionPane.showMessageDialog(null, "Error: Invalid barcode", "Invalid Value", JOptionPane.ERROR_MESSAGE);
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
		
		// lookupFrame button handlers
		
		searchButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				buildLookupFilter();
				register.lookUpProducts(filter);
			}
		});
		
		selectButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(resultsTable.getSelectedRow() != -1) {
					try {
						Long code = (Long)resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 0);
						if(code.toString().matches("^\\d{13}$")) { // Matches only 13 digit numbers for barcode entry, will not attempt to lookup other inputs
							register.beginSale();
							registerFrameAsListener();
							register.enterItem(code);
							lookupFrame.setVisible(false);
							barcodeEntryField.requestFocusInWindow();
						}
					}
					catch (NumberFormatException nfe) { // This should never happen, due to the regex validation
						JOptionPane.showMessageDialog(null, "Error: Invalid barcode", "Invalid Value", JOptionPane.ERROR_MESSAGE);
					}
					catch (ClassCastException cce) { 
						JOptionPane.showMessageDialog(null, "Error: The selected product's barcode is invalid", "Invalid Barcode", JOptionPane.ERROR_MESSAGE);
					}
				}
				barcodeEntryField.requestFocusInWindow();
			}
		});

		resultsTable.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(resultsTable.getSelectedRow() != -1) {
						try {
							Long code = (Long)resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 0);
							if(code.toString().matches("^\\d{13}$")) { // Matches only 13 digit numbers for barcode entry, will not attempt to lookup other inputs

								register.beginSale();
								registerFrameAsListener();
								register.enterItem(code);
								lookupFrame.setVisible(false);
								barcodeEntryField.requestFocusInWindow();
							}
						}
						catch (NumberFormatException nfe) { // This should never happen, due to the regex validation
							JOptionPane.showMessageDialog(null, "Error: Invalid barcode", "Invalid Value", JOptionPane.ERROR_MESSAGE);
						}
						catch (ClassCastException cce) { 
							JOptionPane.showMessageDialog(null, "Error: The selected product's barcode is invalid", "Invalid Barcode", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				barcodeEntryField.requestFocusInWindow();	
			}
		});

		resultsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				Point p = event.getPoint();
				int row = resultsTable.rowAtPoint(p);
				if(row != -1) {
					if (event.getClickCount() == 2) {
						try {
							Long code = (Long)resultsTable.getModel().getValueAt(row, 0);
							if(code.toString().matches("^\\d{13}$")) { // Matches only 13 digit numbers for barcode entry, will not attempt to lookup other inputs
								register.beginSale();
								registerFrameAsListener();
								register.enterItem(code);
								lookupFrame.setVisible(false);
								barcodeEntryField.requestFocusInWindow();
							}
						}
						catch (NumberFormatException nfe) { // This should never happen, due to the regex validation
							JOptionPane.showMessageDialog(null, "Error: Invalid barcode", "Invalid Value", JOptionPane.ERROR_MESSAGE);
						}
						catch (ClassCastException cce) { 
							JOptionPane.showMessageDialog(null, "Error: The selected product's barcode is invalid", "Invalid Barcode", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				barcodeEntryField.requestFocusInWindow();	
			}
		});
		
		barcodeSearchField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildLookupFilter();
					register.lookUpProducts(filter);
				}	
			}
		});
		
		pCodeEntryField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildLookupFilter();
					register.lookUpProducts(filter);
				}	
			}
		});
		
		searchField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildLookupFilter();
					register.lookUpProducts(filter);
				}	
			}
		});
		
		searchComboBox.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buildLookupFilter();
					register.lookUpProducts(filter);
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
					register.cancelSale();
					dispose();
				}
			}
		});


		setVisible(true);
		barcodeEntryField.requestFocusInWindow();
	}

	// -- PosFrame Methods -------------------------------------
	
	private void buildLookupFilter() {
		if(barcodeSearchField.getText().equals(null)) {
			filter.setUseBarcode(false);
		}
		else {
			filter.setUseBarcode(true);
			filter.setBarcodeValue(barcodeSearchField.getText());
		}
		
		if(pCodeEntryField.getText().equals(null)) {
			filter.setUseProductCode(false);
		}
		else {
			filter.setUseProductCode(true);
			filter.setProductCodeValue(pCodeEntryField.getText());
		}
		
		if(searchField.getText().equals(null)) {
			filter.setUseProductName(false);
		}
		else {
			filter.setUseProductName(true);
			filter.setProductNameValue(searchField.getText());
		}
		
		if(((String)searchComboBox.getSelectedItem()).equals("All")) {
			filter.setUseCategory(false);
		}
		else {
			filter.setUseCategory(true);
			filter.setCategoryValue((String)searchComboBox.getSelectedItem());
		}
	}
	
	
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