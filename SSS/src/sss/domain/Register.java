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
import sss.services.SqlBuilder;

public class Register {
	
	private long nextSaleId;
	private boolean activeSale = false;
	
	private BigDecimal lastSaleBalance;
	
	private Sale currentSale;
	private Product currentProduct;
	private LineItemTableModel dataModel = new LineItemTableModel();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private File idFile;
	private Scanner fileScanner;
	private PrintWriter fileWriter;
	private boolean saleMade;
	
	public Register() {
		initialise();
	}
	
	public LineItemTableModel getDataModel() {
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
			String saleInsertStatement = getSaleInsertStatement();
			String[] lineInsertStatements = getLineInsertStatements();
			try {
				writeSale(saleInsertStatement, lineInsertStatements);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error: Write sale to DB failed!", "Write sale failed", JOptionPane.ERROR_MESSAGE);
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
		idFile = new File("id.txt");
		dataModel.setColumnIdentifiers(new String[]{"Qty","Product ID","Name","Discount","Amount"}); // Sets the column names for the table
		try {
			fileScanner = new Scanner(idFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nextSaleId = fileScanner.nextLong();
		System.out.println("Next Sale ID read successfully.\nNext ID: " + nextSaleId);
		fileScanner.close();
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
