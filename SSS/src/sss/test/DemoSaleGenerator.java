/* DemoSaleGenerator Class
 * 
*  A class with a main method able to generate complete Sale and Line objects, 
*  and write them to the database in batches of 1000 sales
*  Used solely to populate the DB with large amounts of sale data
*  
*  Original Author: Josh Kent
*/

package sss.test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import org.joda.time.DateTime;
//
//import sss.domain.Line;
//import sss.domain.Product;
//import sss.domain.Sale;
//
//public class DemoSaleGenerator {
//
//  static ArrayList<Long> prodids = new ArrayList<>();
//  static ArrayList<Double> prices = new ArrayList<>();
//  
//  static ArrayList<Sale> generatedSales = new ArrayList<>();
//  static ArrayList<Line> generatedLineItems = new ArrayList<>();
//  static ArrayList<String> generatedTimestamps = new ArrayList<>();
//  static ArrayList<Product> allProducts = new ArrayList<>();
//  
//  static long currentSaleId = 1;
//  static Random rand = new Random();
//  
//  // Date Gen Method ------------------------------------
//  public static List<DateTime> getDateRange(DateTime start, DateTime end) {
//    
//    int hour = 15;
//    int randomMin = rand.nextInt((3 - 1) + 1) + 1;
//    int randomSec = rand.nextInt((59 - 1) + 1) + 1;
//
//    List<DateTime> ret = new ArrayList<DateTime>();
//    DateTime tmp = start;
//    while (tmp.isBefore(end) || tmp.equals(end)) {
//      ret.add(tmp);
//      if(tmp.getHourOfDay() >= 17 && tmp.getMinuteOfDay() >= 50)
//        tmp = tmp.plusHours(hour);
//      else {
//      tmp = tmp.plusMinutes(randomMin);
//      tmp = tmp.plusSeconds(randomSec);
//      randomMin = rand.nextInt((3 - 1) + 1) + 1;
//      randomSec = rand.nextInt((59 - 1) + 1) + 1;
//      }
//    }
//    return ret;
//  }
//  // -----------------------------------------------
//  
//  // Line Gen method ---------------------
//  public static ArrayList<Line> createLineItems() throws SQLException {
//    ArrayList<Line> lineItems = new ArrayList<>();
//    
//    int numOfLines = rand.nextInt((7 - 1) + 1) + 1;
//    int qtyPerLine = rand.nextInt((3 - 1) + 1) + 1;
//    int randomProduct = rand.nextInt((99 - 1) + 1) + 0;
//    
//    ArrayList<Integer> selectedProducts = new ArrayList<>();
//    int lineNo = 1;
//    Line line = new Line(currentSaleId, allProducts.get(randomProduct), lineNo);
//    line.setQuantity(qtyPerLine);        
//    while(numOfLines != 0) {
//      lineItems.add(line);
//      qtyPerLine = rand.nextInt((3 - 1) + 1) + 1; // New random quantity
//      selectedProducts.add(randomProduct); // Record product selected
//      randomProduct = rand.nextInt((99 - 1) + 1) + 0; // Get new random product
//      while(selectedProducts.contains(randomProduct)){
//        randomProduct = rand.nextInt((99 - 1) + 1) + 0; // Make sure no product doubles
//      }
//      lineNo++;
//      line = new Line(currentSaleId, allProducts.get(randomProduct), lineNo);
//      line.setQuantity(qtyPerLine);
//      numOfLines--;
//    }
//    return lineItems;
//  }
//  // -----------------------------------------------------
//  
//  // Sale Gen method -------------------------------------
//  public static void createSale(ArrayList<Line> lineItems, String timeStamp) {
//	  Sale sale = new Sale(currentSaleId, timeStamp, "Purchase");
//	  for(Line l: lineItems){
//		  sale.addLineItem(l);
//	  }
//	  
//	  sale.calculateTotal();
//	  sale.calculateGST();
//	  sale.calculateSubtotal();
//	  
//	  double amtTendered = 0;
//	  int saleAmt = ((int)sale.getTotal()) + 1;
//	  
//	  if(saleAmt <= 10)
//		  amtTendered = 10;
//	  else if(saleAmt <= 20)
//		  amtTendered = 20;
//	  else if(saleAmt <= 50)
//		  amtTendered = 50;
//	  else if(saleAmt <= 70)
//		  amtTendered = 75;
//	  else if(saleAmt <= 100)
//		  amtTendered = 100;
//	  else if(saleAmt <= 200)
//		  amtTendered = 200;
//	  else if(saleAmt <= 250) {
//		  amtTendered = sale.getTotal() + 10;
//	  }
//	  else
//		  amtTendered = sale.getTotal();
//	  
//	  sale.setAmountTendered(amtTendered);
//	  sale.calculateBalance();
//	  if(sale.isValid())
//		  generatedSales.add(sale);
//	  else
//		  throw new IllegalArgumentException();
//  }
//  //-----------------------------------------------------
//  
//  
//  public static void main(String[] args) throws ClassNotFoundException, SQLException {
//    
//    // Load JDBC Driver
//    Class.forName("com.mysql.jdbc.Driver");
//    System.out.println("Driver loaded");
//    
//    // Connect to existing DB
//    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sss_project" , "root" , "abc123");
//    System.out.println("DB Connection Established\n");
//    
//    // Create a Statement to use for submitting SQL to database
//    Statement statement = connection.createStatement();
//    
//    PreparedStatement insertSale = connection.prepareStatement("INSERT INTO sale VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
//    
//    PreparedStatement insertLine = connection.prepareStatement("INSERT INTO line VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
//    
//    ResultSet results = statement.executeQuery("SELECT prod_id, prod_price FROM product");
//    
//    while(results.next()){
//      Long code = results.getLong(1);
//      Double price = results.getDouble(2);
//      prodids.add(code);
//      prices.add(price);
//    }
//    
//    
//    for(Long code: prodids) {
//    	Product prod = new Product(code);
//    	allProducts.add(prod);
//    }
//    
//    // DATE GENERATOR --------------------
//    DateTime start = new DateTime(2013, 3, 1, 8, 1, 0);
//    System.out.println("Start: " + start);
//
//    DateTime end = new DateTime(2015, 3, 10, 17, 0, 0);
//    System.out.println("End: " + end);
//    System.out.println();
//    //
//    java.util.Date dt = new java.util.Date();
//
//    java.text.SimpleDateFormat sdf
//            = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //MySQL DateTime formatting
//
//    String currentTime = sdf.format(dt);
//    //
//    
//    List<DateTime> between = getDateRange(start, end); // Generate DateTime list
//
//    // -------------------------------------------------
//
//    for (DateTime d : between) {
//    	dt = d.toDate();
//    	currentTime = sdf.format(dt);
//    	generatedTimestamps.add(currentTime);
//    }
////    int batchSize = 1000;
////    int i = 0;
////    for(String ts: generatedTimestamps) {
////    	generatedLineItems = createLineItems();
////
////    	createSale(generatedLineItems, ts);
////    	insertSale.setLong(1, generatedSales.get(i).getSaleId());
////    	insertSale.setString(2, generatedSales.get(i).getSaleDate());
////    	insertSale.setDouble(3, generatedSales.get(i).getSaleSubtotal());
////    	insertSale.setDouble(4, generatedSales.get(i).getSaleGST());
////    	insertSale.setDouble(5, generatedSales.get(i).getSaleTotal());
////    	insertSale.setDouble(6, generatedSales.get(i).getSaleAmountTendered());
////    	insertSale.setDouble(7, generatedSales.get(i).getSaleBalance());
////    	insertSale.setString(8, generatedSales.get(i).getSaleType());
////    	insertSale.addBatch();
////    	
////    	for(Line l: generatedLineItems) {
////    		insertLine.setLong(1, l.getSaleId());
////    		insertLine.setInt(2, l.getLineNumber());
////    		insertLine.setLong(3, l.getProductId());
////    		insertLine.setInt(4, l.getLineUnits());
////    		insertLine.setDouble(5, l.getLineCostPrice());
////    		insertLine.setDouble(6, l.getLinePrice());
////    		insertLine.setDouble(7, l.getLineAmount());
////    		insertLine.setDouble(8, l.getDiscount());
////    		insertLine.addBatch();
////    	}
////    	if(++i % batchSize == 0) {
////    		insertSale.executeBatch();
////    		insertLine.executeBatch();
////    		System.out.println(i);
////    	}
////    	currentSaleId++;
////    }
////    insertSale.executeBatch();
////    insertLine.executeBatch();
//    
//    statement.close();
//    insertSale.close();
//    insertLine.close();
//    connection.close();
//
////    for(Sale s: generatedSales) {
////    	System.out.println(s);
////    }
//    
//    System.out.println(generatedTimestamps.size());
//    System.out.println(currentSaleId);
//    
//  }
//}