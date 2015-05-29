/* SqlBuilder Class
 * 
*  Responsible for creating SQL strings in the correct syntax for passing to the DbReader and DbWriter classes 
*  Creates INSERT, UPDATE and SELECT statements
*  
*  Original Author: Josh Kent
*/

package sss.services;

import java.math.BigDecimal;

import sss.domain.InventoryFilter;
import sss.domain.Sale;
import sss.domain.Line;

public class SqlBuilder {
	
	private SqlBuilder() {
		
	}
	
	//---------- SELECT Methods --------------------------------
	
	/**
	 * Gets a SQL SELECT statement that will return all the names of product suppliers from the supplier table
	 * @return a SQL SELECT statement String
	 */
	public static String getSupplierNames() {
		return "SELECT supp_name FROM supplier ORDER BY supp_id;";
	}
	
	/**
	 * Gets a SQL SELECT statement that will return all the distinct category names from the product table
	 * @return a SQL SELECT statement
	 */
	public static String getCategoryNames() {
		return "SELECT DISTINCT prod_category FROM product;";
	}
	
	/**
	 * Gets a SQL SELECT statement that will retrieve all rows and columns for all products
	 * @return a SQL SELECT statement String
	 */
	public static String getAllProducts() {
		return "SELECT prod_id, prod_code, prod_name, prod_cost_price, prod_price, prod_qoh, prod_category, supp_name, prod_active"
				+ " FROM product, supplier WHERE product.supp_id = supplier.supp_id;";
	}
	
	/**
	 * Gets a SQL SELECT statement that will retrieve all products either =, >, or < a quantity on hand value
	 * @param qoh a value for quantity on hand
	 * @param operator the operator to be used for this query (either =, > or <)
	 * @return a SQL SELECT statement String
	 */
	public static String getProductsByQuantity(int qoh, String operator) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE prod_qoh ");
		query.append(operator + " ");
		query.append(qoh + " ");
		query.append("ORDER BY prod_qoh;");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement that will retrieve all products with price between minPrice and maxPrice (inclusive)
	 * @param minPrice the minimum price value
	 * @param maxPrice the maximum price value
	 * @return a SQL SELECT statement String
	 */
	public static String getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE prod_price >= ");
		query.append(minPrice.toString() + " ");
		query.append("AND prod_price <= ");
		query.append(maxPrice.toString() + " ");
		query.append("ORDER BY prod_price;");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement that will retrieve all products with the given supplier id
	 * @param supp_id a supplier id number (e.g. 1)
	 * @return a SQL SELECT statementString
	 */
	public static String getProductsBySupplierId(int supp_id) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE supp_id = ");
		query.append(supp_id + ";");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement that will retrieve all products from the category supplied
	 * @param category the category value (e.g. Office)
	 * @return a SQL SELECT statement String
	 */
	public static String getProductsByCategory(String category) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE prod_category = '");
		query.append(category + "';");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement to retrieve a specific product from the database based on id (barcode) 
	 * @param prod_id the product id (barcode) for the product to be looked up
	 * @return a SQL SELECT statement String
	 */
	public static String getProductById(long prod_id) {
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
	public static String getProductsByCode(String prod_code) {
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
	public static String getProductsByNameAndCategory(String prod_name, String prod_category) {
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
	public static String getProductsByName(String prod_name) {
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
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT * FROM sale WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "';");
		
		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement for selecting sales grouped by hour between two given dates from the database
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return a SQL SELECT statement String
	 */
	public static String getSaleReportByHourQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT CONCAT(HOUR(sale_date), ':00-', HOUR(sale_date)+1, ':00') AS 'Hour', "
				+ "COUNT(*) AS `Number of Sales`, SUM(sale_total) AS 'Sale Totals' FROM sale WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate +"' ");
		query.append("GROUP BY HOUR(sale_date);");

		return query.toString();
	}
	
	/**
	 * Gets a SQL SELECT statement that will retrieve all products that match the provided filter values
	 * @param filter an InventoryFilter that filters product results
	 * @return a SQL SELECT statement String
	 */
	public static String getProductsFiltered(InventoryFilter filter) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT prod_id, prod_code, prod_name, prod_cost_price, prod_price, prod_qoh, prod_category, supp_name, prod_active"
				+ " FROM product, supplier WHERE product.supp_id = supplier.supp_id");
		
		if(filter.isCategorySelected()) {
			query.append(" AND ");
			query.append("prod_category = '");
			query.append(filter.getCategory() + "'");
		}
		
		if(filter.isSupplierSelected()) {
			query.append(" AND ");
			query.append("product.supp_id = ");
			query.append(filter.getSupplierId());
		}
		
		if(filter.isQohSelected()) {
			query.append(" AND ");
			query.append("prod_qoh ");
			query.append(filter.getQohOperator() + " ");
			query.append(filter.getQohValue());
		}
		
		if(filter.isPriceRangeSelected()) {
			query.append(" AND ");
			query.append("prod_price >= ");
			query.append(filter.getMinPrice() + " ");
			query.append("AND ");
			query.append("prod_price <= ");
			query.append(filter.getMaxPrice());
		}
		
		query.append(";");
		return query.toString();
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
