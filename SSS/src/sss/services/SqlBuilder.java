/* SqlBuilder Class
 * 
*  Responsible for creating SQL strings in the correct syntax for passing to the DbReader and DbWriter classes 
*  Creates INSERT, UPDATE and SELECT statements
*  
*  Original Author: Josh Kent
*/

package sss.services;

import sss.domain.Sale;
import sss.domain.Line;

public class SqlBuilder {
	
	private SqlBuilder() {
		
	}
	
	//---------- SELECT Methods --------------------------------
	
	/**
	 * Gets a SQL SELECT statement to retrieve a specific product from the database based on id (barcode) 
	 * @param prod_id the product id (barcode) for the product to be looked up
	 * @return a SQL SELECT statement String
	 */
	public static String getLookupQueryById(long prod_id) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE prod_id = ");
		query.append(prod_id);
		query.append(";");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement to retrieve a specific product by product code
	 * @param prod_code the product code for the product to be looked up
	 * @return a SQL SELECT statement String
	 */
	public static String getLookupQueryByCode(String prod_code) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE prod_code = '");
		query.append(prod_code);
		query.append("';");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement to retrieve all products within a given category and with a similar name to
	 * the given name
	 * @param prod_name the name of the product/s to be looked up
	 * @param prod_category the category of the product/s to be looked up
	 * @return a SQL SELECT statement String
	 */
	public static String getLookupQueryByNameAndCategory(String prod_name, String prod_category) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%");
		query.append(prod_name.toUpperCase());
		query.append("%' AND prod_category = '");
		query.append(prod_category);
		query.append("';");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement to retrieve all products with a name similar to the given name
	 * @param prod_name the name of the product/s to be looked up
	 * @return a SQL SELECT statement String
	 */
	public static String getLookupQueryByName(String prod_name) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%");
		query.append(prod_name.toUpperCase());
		query.append("%';");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement for selecting all sales between two given dates from the database
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return a SQL SELECT statement String
	 */
	public static String getSaleReportQuery(String startDate, String endDate) {
		StringBuffer currentStatement = new StringBuffer();
		
		currentStatement.append("SELECT * FROM sale WHERE sale_date BETWEEN ");
		currentStatement.append("'" + startDate + "' ");
		currentStatement.append("AND ");
		currentStatement.append("'" + endDate + "';");
		
		return currentStatement.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement for selecting sales grouped by hour between two given dates from the database
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return a SQL SELECT statement String
	 */
	public static String getSaleReportByHourQuery(String startDate, String endDate) {
		StringBuffer currentStatement = new StringBuffer();
		
		currentStatement.append("SELECT CONCAT(HOUR(sale_date), ':00-', HOUR(sale_date)+1, ':00') AS 'Hour', "
				+ "COUNT(*) AS `Number of Sales`, SUM(sale_total) AS 'Sale Totals' FROM sale WHERE sale_date BETWEEN ");
		currentStatement.append("'" + startDate + "' ");
		currentStatement.append("AND ");
		currentStatement.append("'" + endDate +"' ");
		currentStatement.append("GROUP BY HOUR(sale_date);");

		return currentStatement.toString();
	}
	
	//---------------------------------------------------------
	
	//---------- INSERT Methods -------------------------------
	
	/**
	 * Gets a SQL INSERT statement for writing a Sale object to the database
	 * @param sale a Sale object 
	 * @return a SQL INSERT statement String
	 */
	public static String getSaleInsertStatement(Sale sale) {
		
		StringBuffer currentStatement = new StringBuffer();
		
		currentStatement.append("INSERT INTO sale VALUES(");
		currentStatement.append(sale.getSaleId() + ", '");
		currentStatement.append(sale.getSaleDate() + "', ");
		currentStatement.append(sale.getSaleSubtotal() + ", ");
		currentStatement.append(sale.getSaleGST() + ", ");
		currentStatement.append(sale.getSaleTotal() + ", ");
		currentStatement.append(sale.getSaleAmountTendered() + ", ");
		currentStatement.append(sale.getSaleBalance() + ", '");
		currentStatement.append(sale.getSaleType() + "');");
		
		return currentStatement.toString();
		
	}
	
	/**
	 * Gets multiple SQL INSERT statements for writing all a Sale object's Lines to the database
	 * @param sale a Sale object
	 * @return a String[] containing SQL INSERT statement Strings
	 */
	public static String[] getLineInsertStatements(Sale sale) {
		String[] statements = new String[sale.getNumberOfLines()];
		StringBuffer currentStatement = new StringBuffer();
		int i = 0;
		
		for(Line l: sale.getLineItems()) {
			
			currentStatement.append("INSERT INTO line VALUES(");
			currentStatement.append(l.getSaleId() + ", ");
			currentStatement.append(l.getLineNumber() + ", ");
			currentStatement.append(l.getProductId() + ", ");
			currentStatement.append(l.getLineUnits() + ", ");
			currentStatement.append(l.getLineCostPrice() + ", ");
			currentStatement.append(l.getLinePrice() + ", ");
			currentStatement.append(l.getLineAmount() + ", ");
			currentStatement.append(l.getDiscount() + ");");
			
			statements[i] = currentStatement.toString();
			i++;
			currentStatement.delete(0, currentStatement.length());
		}
		return statements;
	}
	
	//---------------------------------------------------------
	
}// End class
