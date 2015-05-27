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
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JOptionPane;

import sss.services.DbConnector;
import sss.services.DbWriter;
import sss.services.PrintFormatter;
import sss.services.ReceiptPrinter;
import sss.services.SqlBuilder;

public class Register {
	
	private long nextSaleId;				// The next sale id is the next consecutive id to be used in the next Sale
	private boolean activeSale = false;		// Maintains state of Register
		
	private BigDecimal lastSaleBalance;		// 
	
	private Sale currentSale;
	private Product currentProduct;
	private NonEditableTableModel dataModel = new NonEditableTableModel();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private File idFile;
	private Scanner fileScanner;
	private PrintWriter fileWriter;
	private boolean saleMade;
	
	public Register() {
		initialise();
	}
	
	public void registerSaleObserver(Observer o) {
		if(activeSale) {
			currentSale.addObserver(o);
		}
	}
	
	public NonEditableTableModel getDataModel() {
		return dataModel;
	}
	
	public String getCurrentSaleBalance() {
		if (activeSale) {
			return String.valueOf(currentSale.getSaleBalance());
		}
			
		else if ((!activeSale) && (lastSaleBalance != null)) {
			return String.valueOf(lastSaleBalance);
		}
		else 
			return "0.00";
	}
	
	public String getCurrentSaleTotal() {
		if (activeSale)
			return String.valueOf(currentSale.getSaleTotal());
		else
			return "0.00";
	}
	
	public Object[] getNewLineData() {
		if(activeSale) {
			ArrayList<Line> currentLines = currentSale.getLineItems();
			Object[] lineData = new Object[5];
			
			lineData[0] = currentLines.get(currentLines.size()-1).getLineUnits();
			lineData[1] = currentLines.get(currentLines.size()-1).getProductId();
			lineData[2] = currentLines.get(currentLines.size()-1).getProductName();
			lineData[3] = currentLines.get(currentLines.size()-1).getDiscount();
			lineData[4] = currentLines.get(currentLines.size()-1).getLineAmount();
			
			return lineData;
		}
		else
			return null;
			
	}
	
	public void voidLineItem(int lineIndex) {
		if(activeSale) {
			Line lineItem = currentSale.getLineItems().get(lineIndex);
			currentSale.removeLineItem(lineItem);
			dataModel.removeRow(lineIndex);
		}
	}
	
	public void changeLineQuantity(int lineIndex, int quantity) {
		String newQty;
		if(activeSale) {
			Line lineItem = currentSale.getLineItems().get(lineIndex);
			lineItem.setQuantity(quantity);
			newQty = String.valueOf(lineItem.getLineUnits());
			dataModel.setValueAt(newQty, lineIndex, 0);
			dataModel.setValueAt(lineItem.getLineAmount(), lineIndex, 4);
		}
	}
	
	public void applyLineDiscount(int lineIndex, double discountPercentage) {
		String newDiscount;
		if(activeSale) {
			Line lineItem = currentSale.getLineItems().get(lineIndex);
			lineItem.setDiscount(discountPercentage);
			newDiscount = lineItem.getDiscount().setScale(2).toString();
			dataModel.setValueAt(newDiscount, lineIndex, 3);
			dataModel.setValueAt(lineItem.getLineAmount(), lineIndex, 4);
		}
	}
	
	public void beginSale() {
		if (!activeSale) {
			currentSale = new Sale(nextSaleId);
			activeSale = true;
		}
	}
	
	public void enterItem(long prod_id) throws SQLException {
		currentProduct = new Product(prod_id);
		Line newLine = new Line(currentSale.getSaleId(), currentProduct, currentSale.getNumberOfLines() +1);
		currentSale.addLineItem(newLine);
		dataModel.addRow(new Object[] {newLine.getLineUnits(), newLine.getProductId(), newLine.getProductName(), newLine.getDiscount(), newLine.getLineAmount()});
	}
	
	public void calculateBalance() {
		currentSale.calculateBalance();
	}
	
	public void calculateTotal() {
		currentSale.calculateTotal();
		currentSale.calculateGST();
		currentSale.calculateSubtotal();
	}
	
	public void makePayment(BigDecimal amt_tendered) {
		if(amt_tendered.compareTo(currentSale.getSaleTotal()) >= 0) {
			
			currentSale.setAmountTendered(amt_tendered);
			currentSale.calculateBalance();
			currentSale.setTimestamp(sdf.format(new Date()));
			currentSale.calculateGST();
			currentSale.calculateSubtotal();

			String saleInsertStatement = getSaleInsertStatement();
			String[] lineInsertStatements = getLineInsertStatements();
			
			if(currentSale.isValid()) {
				System.out.println("VALID SALE");
			}
			else {
				JOptionPane.showMessageDialog(null, "Error: Sale Invalid. Remove from database.", "Invalid Sale", JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				writeSale(saleInsertStatement, lineInsertStatements);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error: Write sale to DB failed!", "Write sale failed", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			printReceipt(formatSale());
			
			// Clear lookUpTable
			for(int i = dataModel.getRowCount()-1; i != -1; i--) {
				dataModel.removeRow(i);
			}
		}
		else {
			System.out.print("ERROR: Amount tendered not enough!");
		}
	}
	
	public String getSaleInsertStatement() {
		String saleInsertStatement = SqlBuilder.getSaleInsertStatement(currentSale);
		return saleInsertStatement;
	}
	
	public String[] getLineInsertStatements() {
		String[] lineInsertStatements = SqlBuilder.getLineInsertStatements(currentSale);
		return lineInsertStatements;
	}
	
	public void writeSale (String saleInsertStatement, String[] lineInsertStatements) throws SQLException {
		DbWriter.executeStatement(saleInsertStatement);
		for (String lis: lineInsertStatements) {
			DbWriter.executeStatement(lis);
		}
	}
	
	public FormattedSale formatSale() {
		FormattedSale fs = PrintFormatter.formatSale(currentSale);
		return fs;
	}
	
	public void printReceipt(FormattedSale fs) {
		ReceiptPrinter printer = new ReceiptPrinter(fs);
		printer.printReceipt();
		nextSaleId++;
		saleMade = true;
		lastSaleBalance = currentSale.getSaleBalance();
		activeSale = false;
	}
	
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
	
	public void shutdown() {
		if(saleMade) {
			try {
				fileWriter = new PrintWriter(idFile);
				fileWriter.print(nextSaleId);
				System.out.println("Next Sale ID saved successfully.\nNext ID: " + nextSaleId);
				fileWriter.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else {
			System.out.println("No sales made.\nNext ID: " + nextSaleId);
		}
		
		try {
			DbConnector.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DB connection closed.");
	}
}
