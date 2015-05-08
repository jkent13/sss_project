package sss.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import sss.services.DbConnector;
import sss.services.DbReader;
import sss.services.DbWriter;
import sss.services.PrintFormatter;
import sss.services.ReceiptPrinter;
import sss.services.SqlBuilder;

public class Register {
	
	private long nextSaleId;
	private boolean activeSale = false;
	
	private Sale currentSale;
	private Product currentProduct;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Date timestamp;
	
	private File idFile;
	private Scanner fileScanner;
	private PrintWriter fileWriter;
	private boolean saleMade;
	
	public Register() {
		initialise();
	}
	
	public void beginSale() {
		currentSale = new Sale(nextSaleId);
		activeSale = true;
	}
	
	public void enterItem(long prod_id) throws SQLException {
		currentProduct = new Product(prod_id);
		currentSale.addLineItem(new Line(currentSale.getSaleId(), currentProduct, currentSale.getNumberOfLines() +1));
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
			getInsertStatements();
			printReceipt(formatSale());
		}
		else {
			System.out.print("ERROR: Amount tenderend not enough!");
		}
	}
	
	public void getInsertStatements () {
		String saleInsertStatement = SqlBuilder.getSaleInsertStatement(currentSale);
		String[] lineInsertStatements = SqlBuilder.getLineInsertStatements(currentSale);
		try {
			writeSale(saleInsertStatement, lineInsertStatements);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}
	
	public void initialise() {
		idFile = new File("id.txt");
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
