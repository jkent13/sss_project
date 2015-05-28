/* Register Class
 * 
 * The controller class for the POS / Retail subsystem
 * Delegates input from PosFrame to other classes when needed
 * Entry point from Presentation Layer to Domain Layer
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import sss.services.DbConnector;
import sss.services.DbWriter;
import sss.services.PrintFormatter;
import sss.services.ReceiptPrinter;
import sss.services.SaleListener;
import sss.services.SqlBuilder;

public class Register {
	
	private long nextSaleId;				// The next sale id is the next consecutive id to be used in the next Sale
	private boolean activeSale = false;		// Maintains state of Register
	
	private Sale currentSale;				// The current Sale object for the current transaction
	private Product currentProduct;			// The last Product enter as a Line	
	private NonEditableTableModel dataModel = new NonEditableTableModel();	// The data model for PosFrame's JTable (lookupTable)
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // The MySQL DateTime format
	
	private File idFile; 					// File object for manipulating a text file containing the next sale id
	private Scanner fileScanner; 			// For reading idFile
	private PrintWriter fileWriter; 		// For writing the new value of the next sale id to file if any sales are made 
	private boolean saleMade; 				// Tracks whether writing a new sale id to file is necessary 
	
	/**
	 * Constructor - calls the initialise method to set up the Register
	 */
	public Register() {
		initialise();
	}
	
	//--------------- Core Methods-------------------------------------
	
	/**
	 * A start-up method for the Register. Reads in the nextSaleId value and sets the column names for the data model
	 */
	public void initialise() {
		idFile = new File("id.txt"); // Contains nextSaleId
		dataModel.setColumnIdentifiers(new String[]{"Qty","Product ID","Name","Discount","Amount"}); // Sets the column names for the lookup table
		try {
			fileScanner = new Scanner(idFile);
			nextSaleId = fileScanner.nextLong();
			System.out.println("Next Sale ID read successfully.\nNext ID: " + nextSaleId);
			fileScanner.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: Could not find id.txt file. Next sale ID cannot be correctly established.", "File Not Found", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/**
	 * A closing method for the Register. Writes the next sale id to text for next initialization and closes 
	 * any connections to the database
	 */
	public void shutdown() {
		if(saleMade) {
			try {
				fileWriter = new PrintWriter(idFile);
				fileWriter.print(nextSaleId);
				System.out.println("Next Sale ID saved successfully.\nNext ID: " + nextSaleId);
				fileWriter.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error: Could not find id.txt file. Next sale ID cannot be correctly written to file.", "File Not Found", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

		}
		else {
			System.out.println("No sales made.\nNext ID: " + nextSaleId);
		}
		
		try {
			DbConnector.closeConnection();
			System.out.println("DB connection closed.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: The connection to the database could not be closed properly", "DB Connection Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		System.out.println("DB connection closed.");
	}
	
	/**
	 * Signals a new sale transaction should be started (if one is not already active). Changes Register state
	 */
	public void beginSale() {
		if (!activeSale) {
			currentSale = new Sale(nextSaleId);
			activeSale = true;
		}
	}
	
	/**
	 * Method for registering a SaleListener (namely PosFrame) to the currentSale Sale object 
	 * @param listener a reference to the new SaleListener to be added
	 */
	public void registerSaleListener(SaleListener listener) {
		if(activeSale) { 
			currentSale.registerListener(listener); // Passes listener argument through to currentSale registerListener()
		}
	}
	
	//-----------------------------------------------------------------
	
	//------ Getter Methods -------------------------------------------
	/**
	 * Getter method for the whether a sale is active
	 * @return true if a sale is active, false otherwise
	 */
	public boolean isActiveSale() {
		return activeSale;
	}
	
	/**
	 * Getter method for the data model (for PosFrame's lookupTable)
	 * @return a reference to the data model maintained in the Register class
	 */
	public NonEditableTableModel getDataModel() {
		return dataModel;
	}
	
	/**
	 * Getter method for the current sale total - used for exact cash payments 
	 * @return the current sale's total (amount due)
	 */
	public BigDecimal getCurrentSaleTotal() {
		if(activeSale) {
			return currentSale.getSaleTotal();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Getter method for the current sale's SQL insert statements
	 * @return a String SQL insert statement for the current sale
	 */
	public String getSaleInsertStatement() {
		String saleInsertStatement = SqlBuilder.getSaleInsertStatement(currentSale);
		return saleInsertStatement;
	}
	
	/**
	 * Getter method for the current sale's line item SQL insert statements
	 * @return a String[] containing SQL insert statements for the current sale's lines
	 */
	public String[] getLineInsertStatements() {
		String[] lineInsertStatements = SqlBuilder.getLineInsertStatements(currentSale);
		return lineInsertStatements;
	}
	
	//-----------------------------------------------------------------
	
	//--------------- Main POS methods---------------------------------

	/**
	 * Enters a new line item to the data model and to the current sale
	 * @param prod_id the product id (barcode) for the product to be displayed on the new line
	 */
	public void enterItem(long prod_id) {
		currentProduct = new Product(prod_id);
		if(currentProduct.successfulLookup() && currentProduct.getName() != null) { 
			Line newLine = new Line(currentSale.getSaleId(), currentProduct, currentSale.getNumberOfLines() +1);
			currentSale.addLineItem(newLine);
			dataModel.addRow(new Object[] {newLine.getLineUnits(), newLine.getProductId(), newLine.getProductName(), newLine.getDiscount(), newLine.getLineAmount()});
			calculateTotal();
		}
		else {
			// If the product name is null the product is not in the database
			// If the successfulLookup value is false, there was an error in retrieving the product data from the database 
			JOptionPane.showMessageDialog(null, "Product: " + prod_id + " not found", "Lookup Failed", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Removes the line at the given index from the data model and from the current sale
	 * @param lineIndex the row index for the line to be removed
	 */
	public void voidLineItem(int lineIndex) {
		if(activeSale) {
			Line lineItem = currentSale.getLineItems().get(lineIndex);
			currentSale.removeLineItem(lineItem);
			dataModel.removeRow(lineIndex);
			calculateTotal();
		}
	}
	
	/**
	 * Changes the quantity value (also known as line units) for the line at the given index in both the data model 
	 * the current sale
	 * @param lineIndex the row index for the line to be changed
	 * @param quantity the new quantity for the line
	 */
	public void changeLineQuantity(int lineIndex, int quantity) {
		String newQty;
		if(activeSale) {
			Line lineItem = currentSale.getLineItems().get(lineIndex);
			lineItem.setQuantity(quantity);
			newQty = String.valueOf(lineItem.getLineUnits());
			dataModel.setValueAt(newQty, lineIndex, 0);
			dataModel.setValueAt(lineItem.getLineAmount(), lineIndex, 4);
			calculateTotal();
		}
	}
	
	/**
	 * Applies a discount to the line at the given index, reflected in both the data model and the current sale
	 * @param lineIndex the row index for the line to be changed
	 * @param discountPercentage the percentage discount to be applied (e.g. 50 = 50% discount)
	 */
	public void applyLineDiscount(int lineIndex, double discountPercentage) {
		String newDiscount;
		if(activeSale) {
			Line lineItem = currentSale.getLineItems().get(lineIndex);
			lineItem.setDiscount(discountPercentage);
			newDiscount = lineItem.getDiscount().setScale(2).toString();
			dataModel.setValueAt(newDiscount, lineIndex, 3);
			dataModel.setValueAt(lineItem.getLineAmount(), lineIndex, 4);
			calculateTotal();
		}
	}
	
	/**
	 * Makes a payment for the current sale, which sets the amount tendered and timestamp, writes the sale 
	 * and its lines to the database and prints a receipt
	 * @param amt_tendered the amount tendered (must be greater than or equal to the sale total)
	 */
	public void makePayment(BigDecimal amt_tendered) {
		if(activeSale && amt_tendered.compareTo(currentSale.getSaleTotal()) >= 0) {
			calculateTotal();
			currentSale.setAmountTendered(amt_tendered);
			calculateBalance();
			currentSale.setTimestamp(sdf.format(new Date())); // Create and set the timestamp
			
			
			// Get the SQL insert statements
			String saleInsertStatement = getSaleInsertStatement();
			String[] lineInsertStatements = getLineInsertStatements();
			
			if(!currentSale.isValid()) {
				JOptionPane.showMessageDialog(null, "Error: Sale Invalid. Remove from database.", "Invalid Sale", JOptionPane.ERROR_MESSAGE);
			}
			
			// Write the sale and lines to database
			try {
				writeSale(saleInsertStatement, lineInsertStatements);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error: Write sale to DB failed!", "Write sale failed", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
			// Print out receipt
			printReceipt(formatSale());
			
			// Clear lookUpTable
			for(int i = dataModel.getRowCount()-1; i != -1; i--) {
				dataModel.removeRow(i);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "ERROR: Amount tendered not enough!", "Invalid Amount Tendered", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//-----------------------------------------------------------------
	
	//---------------- Calculator Methods -----------------------------
	
	/**
	 * Makes a call to the current sale to calculate its balance
	 */
	public void calculateBalance() {
		if(activeSale) {
			currentSale.calculateBalance();
		}
	}
	
	/**
	 * Makes a call to the current sale to calculate its totals
	 */
	public void calculateTotal() {
		if(activeSale) {
			currentSale.calculateTotal();
		}
	}
	
	//-----------------------------------------------------------------
	
	//----------- Printing and DB Writing Methods----------------------
	
	/**
	 * Writes a sale and its line items to the database
	 * @param saleInsertStatement the sale SQL insert statement
	 * @param lineInsertStatements the line item insert statements
	 * @throws SQLException if there is an error writing to database e.g. duplicate PK or invalid attribute data type
	 */
	public void writeSale (String saleInsertStatement, String[] lineInsertStatements) throws SQLException {
		DbWriter.executeStatement(saleInsertStatement);
		for (String lis: lineInsertStatements) {
			DbWriter.executeStatement(lis);
		}
	}
	
	/**
	 * Formats the current sale into a FormattedSale object which can be printed
	 * @return a formatted version of the current sale, suitable for a ReceiptPrinter
	 */
	public FormattedSale formatSale() {
		FormattedSale fs = PrintFormatter.formatSale(currentSale);
		return fs;
	}
	
	/**
	 * Prints out a formatted sale, increments the next sale id value, sets saleMade to true and resets Register to no active sale
	 * @param fs a formatted sale object
	 */
	public void printReceipt(FormattedSale fs) {
		ReceiptPrinter printer = new ReceiptPrinter(fs);
		printer.printReceipt();
		nextSaleId++;
		saleMade = true;
		activeSale = false;
	}
	
}// End class
