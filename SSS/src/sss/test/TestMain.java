/* TestMain Class
 * 
*  Contains a main method for quick testing of objects and methods, used to 
*  quickly find errors or logic flaws in code in a sandbox test environment without 
*  use of a GUI
*  
*  Original Author: Josh Kent
*/

package sss.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import sss.domain.FormattedSale;
import sss.domain.Invoice;
import sss.domain.InvoiceRowComparison;
import sss.domain.Line;
import sss.domain.Product;
import sss.domain.Sale;
import sss.services.DbConnector;
import sss.services.DbWriter;
import sss.services.PrintFormatter;
import sss.services.ReceiptPrinter;
import sss.services.SqlBuilder;

public class TestMain {

  static ArrayList<Long> prodids = new ArrayList<>();
  static ArrayList<Double> prices = new ArrayList<>();
  
  static ArrayList<Sale> generatedSales = new ArrayList<>();
  static ArrayList<Line> generatedLineItems = new ArrayList<>();
  static ArrayList<String> generatedTimestamps = new ArrayList<>();
  static ArrayList<Product> allProducts = new ArrayList<>();
  
  static long currentSaleId = 4521554;
  static Random rand = new Random();
  
  // Line Gen method ---------------------
  public static ArrayList<Line> createLineItems() throws SQLException {
    ArrayList<Line> lineItems = new ArrayList<>();
    
    int numOfLines = rand.nextInt((7 - 1) + 1) + 1;
    int qtyPerLine = rand.nextInt((3 - 1) + 1) + 1;
    int randomProduct = rand.nextInt((99 - 1) + 1) + 0;
    
    ArrayList<Integer> selectedProducts = new ArrayList<>();
    int lineNo = 1;
    Line line = new Line(currentSaleId, allProducts.get(randomProduct), lineNo);
    line.setQuantity(qtyPerLine);        
    while(numOfLines != 0) {
      lineItems.add(line);
      qtyPerLine = rand.nextInt((3 - 1) + 1) + 1; // New random quantity
      selectedProducts.add(randomProduct); // Record product selected
      randomProduct = rand.nextInt((99 - 1) + 1) + 0; // Get new random product
      while(selectedProducts.contains(randomProduct)){
        randomProduct = rand.nextInt((99 - 1) + 1) + 0; // Make sure no product doubles
      }
      lineNo++;
      line = new Line(currentSaleId, allProducts.get(randomProduct), lineNo);
      line.setQuantity(qtyPerLine);
      numOfLines--;
    }
    return lineItems;
  }
  
// Sale Gen method -------------------------------------
public static void createSale(ArrayList<Line> lineItems, String timeStamp) {
	  Sale sale = new Sale(currentSaleId, timeStamp, "Purchase");
	  for(Line l: lineItems){
		  sale.addLineItem(l);
	  }
	  
	  sale.calculateTotal();
	  
	  double amtTendered = 0;
	  long saleAmt = sale.getSaleTotal().longValue() + 1;
	  
	  if(saleAmt <= 10)
		  amtTendered = 10;
	  else if(saleAmt <= 20)
		  amtTendered = 20;
	  else if(saleAmt <= 50)
		  amtTendered = 50;
	  else if(saleAmt <= 70)
		  amtTendered = 75;
	  else if(saleAmt <= 100)
		  amtTendered = 100;
	  else if(saleAmt <= 200)
		  amtTendered = 200;
	  else if(saleAmt <= 250) {
		  amtTendered = sale.getSaleTotal().longValue() + 10;
	  }
	  else
		  amtTendered = sale.getSaleTotal().longValue();
	  
	  BigDecimal amtT = new BigDecimal(amtTendered);
	  amtT = amtT.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	  sale.setAmountTendered(amtT);
	  sale.calculateBalance();
	  if(sale.isValid())
		  generatedSales.add(sale);
	  else
		  throw new IllegalArgumentException();
}
  
	public static void main(String[] args) throws SQLException {
		
		
//		long barcode = 9300026131160L;
//		Line testLine = new Line(1L, barcode, 1);
//		Line testLine2 = new Line(1L, 9300062687246L, 2);
//		
//		testLine.setDiscount(10);
//		testLine2.setQuantity(4);
		
//		Product prod = new Product(120392183218L);
//		if(prod.successfulLookup()) {
//			System.out.println("SUCCESS");
//			System.out.println(prod.getName());
//		}
//		else {
//			System.out.println("NOT FOUND");
//		}
		
//		System.out.println(testLine);
//		System.out.println(testLine2);
		
//		String idQuery = SqlBuilder.getLookupQueryById(barcode);
//		String nameQuery = SqlBuilder.getLookupQueryByName("cat");
//		
//		System.out.println(idQuery);
//		System.out.println(nameQuery);
//		String number = "20.5754";
//		BigDecimal bd = new BigDecimal(number);
//		bd = bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
//		System.out.println(bd);
//
//		java.util.Date dt = new java.util.Date();
//
//		java.text.SimpleDateFormat sdf
//		= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //MySQL DateTime formatting
//
//		String currentTime = sdf.format(dt);
//		
//	    // Load JDBC Driver
//	    try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    System.out.println("Driver loaded");
//	    
//	    // Connect to existing DB
//	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sss_project" , "root" , "abc123");
//	    System.out.println("DB Connection Established\n");
//	    
//	    // Create a Statement to use for submitting SQL to database
//	    Statement statement = connection.createStatement();
//	    
//	    ResultSet results = statement.executeQuery("SELECT prod_id, prod_price FROM product");
//	    
//	    while(results.next()){
//	      Long code = results.getLong(1);
//	      Double price = results.getDouble(2);
//	      prodids.add(code);
//	      prices.add(price);
//	    }
//	    
//	    
//	    for(Long code: prodids) {
//	    	Product prod = new Product(code);
//	    	allProducts.add(prod);
//	    }
//	    
//	    generatedLineItems = createLineItems();
//	    createSale(generatedLineItems, currentTime);
//	    
//		System.out.println(generatedSales.get(0));
//		FormattedSale fs = PrintFormatter.formatSale(generatedSales.get(0));
//		ReceiptPrinter rp = new ReceiptPrinter(fs);
//		rp.printReceipt();
//		
//		statement.close();
//		connection.close();
//		DbConnector.closeConnection();

		readCsv();
		
	}
	
	public static void readCsv() {

		try {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
			chooser.setFileFilter(filter);
			int selection = chooser.showOpenDialog(null);
			if(selection == JFileChooser.APPROVE_OPTION) {

				File file = chooser.getSelectedFile();
				Scanner fileReader = new Scanner(file);
				ArrayList<String[]> rows = new ArrayList<String[]>();

				String currentLine;
				String[] tokens;

				while (fileReader.hasNext()) {
					currentLine = fileReader.nextLine();
					tokens = currentLine.split(",");
					rows.add(tokens);
				}
				fileReader.close();
				
				if(validateCsvRows(rows)) {
					Invoice invoice = new Invoice();
					BigDecimal costPrice = null;
					BigDecimal price = null;
					int quantity = 0;

					for(String[] row: rows) {
						if(!row[1].equals("-")) {
							costPrice = new BigDecimal(row[1]).setScale(2, BigDecimal.ROUND_HALF_EVEN);;
						}
						else {
							costPrice = null;
						}
						if(!row[2].equals("-")) {
							price = new BigDecimal(row[2]).setScale(2, BigDecimal.ROUND_HALF_EVEN);;
						}
						else {
							price = null;
						}
						if(!row[3].equals("-")) {
							quantity = Integer.parseInt(row[3]);
						}
						else {
							quantity = 0;
						}

						invoice.addRow(row[0], costPrice, price, quantity);
					}
					
					ArrayList<InvoiceRowComparison> compSet = invoice.getComparisonSet();
					InvoiceRowComparison.printHeader();
					for(InvoiceRowComparison irc: compSet) {
						irc.printDetails();
					}
					
					String[] updateStatements = SqlBuilder.getInvoiceUpdateStatements(compSet);
					
					for(String updateStatement: updateStatements) {
						DbWriter.executeStatement(updateStatement);
					}
					
					
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static boolean validateCsvRows(ArrayList<String[]> rows) {
		int rowCounter = 1;
		try {
			for(String[] row: rows) {
				if(row.length != 4) {
					return false;
				}
				if(!row[1].equals("-")) {
					BigDecimal costPrice = new BigDecimal(row[1]);
				}
				if(!row[2].equals("-")) {
					BigDecimal price = new BigDecimal(row[2]);
				}
				if(!row[3].equals("-")) {
					Integer quantity = Integer.parseInt(row[3]);
				}
				rowCounter++;
			}
		} 
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Error: An invalid price value was found at row: " + rowCounter + 
					". Please ensure this value is correct and retry the import.", "Invalid Price", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
}
