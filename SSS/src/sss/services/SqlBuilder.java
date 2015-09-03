/* SqlBuilder Class
 * 
*  Responsible for creating SQL strings in the correct syntax for passing to the DbReader and DbWriter classes 
*  Creates INSERT, UPDATE and SELECT statements
*  
*  Original Author: Josh Kent
*/

package sss.services;

import java.util.ArrayList;

import sss.domain.InventoryFilter;
import sss.domain.Invoice;
import sss.domain.InvoiceRow;
import sss.domain.InvoiceRowComparison;
import sss.domain.LookupFilter;
import sss.domain.Product;
import sss.domain.ProductEditFilter;
import sss.domain.Sale;
import sss.domain.Line;
import sss.domain.TopSellerFilter;

public class SqlBuilder {
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	private SqlBuilder() {
	}
	
	
	
	// ==========================================================================
	// Sale Report SELECT Methods
	// ==========================================================================
	
	
	
	/**
	 * Gets a SQL SELECT statement for selecting all sales between two given dates from the database
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return a SQL SELECT statement String
	 */
	public static String getSaleReportQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT sale_id, sale_date, sale_total, sale_amt_tendered, sale_balance "
				+ "FROM sale WHERE sale_type = 'Purchase' "
				+ "AND sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "';");
		
		return query.toString();
	}
	
	
	
	/**
	 * Gets a SQL SELECT statement for selecting sales grouped by hour for a given date from the database
	 * @param date the date
	 * @return a SQL SELECT statement String
	 */
	public static String getSaleReportByHourQuery(String startDate) {
		StringBuffer query = new StringBuffer();

		query.append("SELECT CONCAT(hrs.theHour, ':00-', hrs.theHour+1, ':00') as 'Hours', "
				+ "COUNT(sale_date) AS `Number of Sales`,"
				+ " SUM(sale_total) AS 'Sale Totals' "
				+ "FROM ( SELECT 8 AS theHour " 
				+ "UNION ALL SELECT 9 "
				+ "UNION ALL SELECT 10 "
				+ "UNION ALL SELECT 11 "
				+ "UNION ALL SELECT 12 "
				+ "UNION ALL SELECT 13 "
				+ "UNION ALL SELECT 14 "
				+ "UNION ALL SELECT 15 "
				+ "UNION ALL SELECT 16 "
				+ "UNION ALL SELECT 17) AS hrs "
				+ "LEFT OUTER JOIN sale "
				+ "ON EXTRACT(HOUR FROM sale.sale_date) = hrs.theHour "
				+ "AND DATE(sale.sale_date) = ");
		query.append("'" + startDate + "' ");
		query.append("AND sale.sale_type = 'Purchase' ");
		query.append("GROUP BY hrs.theHour;");

		return query.toString();
	}

	
	
	public static String getSingleDayGrossProfitQuery(String startDate) {
		StringBuffer query = new StringBuffer();

		query.append("SELECT CONCAT(hrs.theHour, ':00-', hrs.theHour + 1, ':00') AS 'Hours', "
				+ "COUNT(sale_date) AS `Number of Sales`, "
				+ "SUM(line_amount - (line_cost_price * line_units)) AS 'Gross Profit' "
				+ "FROM (SELECT 8 AS theHour "
				+ "UNION ALL SELECT 9 "
				+ "UNION ALL SELECT 10 "
				+ "UNION ALL SELECT 11 "
				+ "UNION ALL SELECT 12 "
				+ "UNION ALL SELECT 13 "
				+ "UNION ALL SELECT 14 "
				+ "UNION ALL SELECT 15 "
				+ "UNION ALL SELECT 16 "
				+ "UNION ALL SELECT 17) AS hrs "
				+ "LEFT OUTER JOIN sale "
				+ "ON EXTRACT(HOUR FROM sale.sale_date) = hrs.theHour "
				+ "AND DATE(sale.sale_date) = ");
		query.append("'" + startDate + "' ");
		query.append("AND sale.sale_type = 'Purchase' ");
		query.append("LEFT OUTER JOIN line "
				+ "ON sale.sale_id = line.sale_id "
				+ "GROUP BY hrs.theHour;");

		return query.toString();
	}
	
	
	
	public static String getSaleDollarByDayQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT DATE_FORMAT(sale_date, '%b %d %y') AS 'Day', "
				+ "COUNT(sale_id) AS `Number of Sales`, "
				+ "SUM(sale_total) AS 'Sale Totals' "
				+ "FROM sale "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale_type = 'Purchase' "
				+ "GROUP BY DAY(sale_date) , MONTH(sale_date) , YEAR(sale_date) "
				+ "ORDER BY sale_date;");
		
		return query.toString();
	}
	
	
	
	public static String getGrossProfitByDayQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT DATE_FORMAT(sale_date, '%b %d %y') AS 'Day', "
				+ "COUNT(sale.sale_id) AS `Number of Products Sold`, "
				+ "SUM(line_amount - (line_cost_price * line_units)) AS 'Gross Profit' "
				+ "FROM sale, line "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale_type = 'Purchase' "
				+ "AND sale.sale_id = line.sale_id "
				+ "GROUP BY DAY(sale_date) , MONTH(sale_date) , YEAR(sale_date) "
				+ "ORDER BY sale_date;");

		return query.toString();
	}
	
	
	
	public static String getSaleDollarByWeekQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT CONCAT(WEEK(sale_date), '/', YEAR(sale_date)) AS 'Week No.', "
				+ "DATE_FORMAT(sale_date, '%a %d/%m/%y') AS 'Starting Date', "
				+ "COUNT(sale_id) AS `Number of Sales`, "
				+ "SUM(sale_total) AS 'Sale Totals' "
				+ "FROM sale "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale_type = 'Purchase' "
				+ "GROUP BY CONCAT(WEEK(sale_date), '/', YEAR(sale_date)) "
				+ "ORDER BY sale_date;");
		
		return query.toString();
	}
	
	
	
	public static String getGrossProfitByWeekQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT CONCAT(WEEK(sale_date), '/', YEAR(sale_date)) AS 'Week No.', "
				+ "DATE_FORMAT(sale_date, '%a %d/%m/%y') AS 'Starting Date', "
				+ "COUNT(sale.sale_id) AS `Number of Products Sold`, "
				+ "SUM(line_amount - (line_cost_price * line_units)) AS 'Gross Profit' "
				+ "FROM sale, line "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale_type = 'Purchase' "
				+ "AND sale.sale_id = line.sale_id "
				+ "GROUP BY CONCAT(WEEK(sale_date), '/', YEAR(sale_date)) "
				+ "ORDER BY sale_date;");
		
		return query.toString();
	}
	
	
	
	public static String getSaleDollarByMonthQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT DATE_FORMAT(sale_date, '%b/%Y') AS 'Month', "
				+ "DATE_FORMAT(sale_date, '%a %d/%m/%y') AS 'Starting Date', "
				+ "COUNT(sale_id) AS `Number of Sales`, "
				+ "SUM(sale_total) AS 'Sale Totals' "
				+ "FROM sale "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale_type = 'Purchase' "
				+ "GROUP BY DATE_FORMAT(sale_date, '%b/%Y') "
				+ "ORDER BY sale_date;");
		
		return query.toString();
	}
	
	
	
	public static String getGrossProfitByMonthQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT DATE_FORMAT(sale_date, '%b/%Y') AS 'Month', "
				+ "DATE_FORMAT(sale_date, '%a %d/%m/%y') AS 'Starting Date', "
				+ "COUNT(sale.sale_id) AS `Number of Products Sold`, "
				+ "SUM(line_amount - (line_cost_price * line_units)) AS 'Gross Profit' "
				+ "FROM sale, line "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale_type = 'Purchase' "
				+ "AND sale.sale_id = line.sale_id "
				+ "GROUP BY DATE_FORMAT(sale_date, '%b/%Y') "
				+ "ORDER BY sale_date;");
		
		return query.toString();
	}
	
	
	
	// ==========================================================================
	// Refund Report SELECT Methods
	// ==========================================================================
	
	
	
	public static String getSingleDayRefundQuery(String startDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT CONCAT(hrs.theHour, ':00-', hrs.theHour+1, ':00') as 'Hours', "
				+ "COUNT(sale_date) AS `Number of Refunds`, "
				+ "SUM(sale_total) AS 'Refund Totals' "
				+ "FROM ( SELECT 8 AS theHour "
				+ "UNION ALL SELECT 9 "
				+ "UNION ALL SELECT 10 "
				+ "UNION ALL SELECT 11 "
				+ "UNION ALL SELECT 12 "
				+ "UNION ALL SELECT 13 "
				+ "UNION ALL SELECT 14 "
				+ "UNION ALL SELECT 15 "
				+ "UNION ALL SELECT 16 "
				+ "UNION ALL SELECT 17) AS hrs "
				+ "LEFT OUTER JOIN sale "
				+ "ON EXTRACT(HOUR FROM sale.sale_date) = hrs.theHour "
				+ "AND DATE(sale.sale_date) = ");
		query.append("'" + startDate + "' ");
		query.append("AND sale.sale_type = 'Refund' "
				+ "AND sale.sale_total < 0 "
				+ "GROUP BY hrs.theHour;");
		
		return query.toString();
	}
	

	
	public static String getRefundByDayQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT DATE_FORMAT(sale_date, '%b %d %y') AS 'Day', "
				+ "COUNT(sale_id) AS `Number of Refunds`, "
				+ "SUM(sale_total) AS 'Refund Totals' "
				+ "FROM sale "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale.sale_type = 'Refund' "
				+ "AND sale.sale_total < 0 "
				+ "GROUP BY DAY(sale_date) , MONTH(sale_date) , YEAR(sale_date) "
				+ "ORDER BY sale_date;");

		return query.toString();
	}
	
	
	
	public static String getRefundByWeekQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT CONCAT(WEEK(sale_date), '/', YEAR(sale_date)) AS 'Week No.', "
				+ "DATE_FORMAT(sale_date, '%a %d/%m/%y') AS 'Starting Date', "
				+ "COUNT(sale_id) AS `Number of Refunds`, "
				+ "SUM(sale_total) AS 'Refund Totals' "
				+ "FROM sale "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale.sale_type = 'Refund' "
				+ "AND sale.sale_total < 0 "
				+ "GROUP BY CONCAT(WEEK(sale_date), '/', YEAR(sale_date)) "
				+ "ORDER BY sale_date;");
		
		return query.toString();
	}
	
	
	
	public static String getRefundByMonthQuery(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT DATE_FORMAT(sale_date, '%b/%Y') AS 'Month', "
				+ "DATE_FORMAT(sale_date, '%a %d/%m/%y') AS 'Starting Date', "
				+ "COUNT(sale_id) AS `Number of Refunds`, "
				+ "SUM(sale_total) AS 'Refund Totals' "
				+ "FROM sale "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND sale.sale_type = 'Refund' "
				+ "AND sale.sale_total < 0 "
				+ "GROUP BY DATE_FORMAT(sale_date, '%b/%Y') "
				+ "ORDER BY sale_date;");
		
		return query.toString();
	}
	
	
	
	// ==========================================================================
	// Product Report SELECT Methods
	// ==========================================================================
	
	
	
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
	 * Gets a SQL SELECT statement that will retrieve all products that match the provided filter values, ordered alphabetically
	 * by name
	 * @param filter an InventoryFilter that filters product results
	 * @return a SQL SELECT statement String
	 */
	public static String getProductsFiltered(InventoryFilter filter) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT prod_id, prod_code, prod_name, prod_cost_price, prod_price, prod_qoh, prod_category, supp_name, prod_active"
				+ " FROM product, supplier "
				+ "WHERE product.supp_id = supplier.supp_id");
		
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
		
		if(filter.isNameSupplied()) {
			query.append(" AND ");
			query.append("product.prod_name LIKE '%");
			query.append(filter.getProductName() + "%'");
		}
		
		query.append(" ORDER BY prod_name;");
		return query.toString();
	}
	
	
	
	public static String lookupProduct(LookupFilter filter) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT prod_id, prod_code, prod_name, prod_cost_price, prod_price, prod_qoh, prod_category, supp_name, prod_active"
				+ " FROM product, supplier "
				+ "WHERE product.supp_id = supplier.supp_id");
		
		if(filter.isUseCategory()) {
			query.append(" AND ");
			query.append("product.prod_category = '");
			query.append(filter.getCategoryValue() + "'");
		}
		
		if(filter.isUseBarcode()) {
			query.append(" AND ");
			query.append("CAST(product.prod_id as CHAR) LIKE '%");
			query.append(filter.getBarcodeValue() + "%'");
		}
		
		if(filter.isUseProductName()) {
			query.append(" AND ");
			query.append("product.prod_name LIKE '%");
			query.append(filter.getProductNameValue() + "%'");
		}
		
		if(filter.isUseProductCode()) {
			query.append(" AND ");
			query.append("product.prod_code LIKE '%");
			query.append(filter.getProductCodeValue() + "%'");
		}
		
		query.append(" ORDER BY product.prod_name;");
		return query.toString();
	}
	
	
	
	public static String lookupProductsFromInvoice(Invoice invoice) {
		StringBuffer query = new StringBuffer();
		ArrayList<InvoiceRow> rows = invoice.getRows();
		
		query.append("SELECT prod_code, prod_cost_price, prod_price, prod_qoh "
				+ "FROM product WHERE ");
		
		for(InvoiceRow row : rows){
			query.append("prod_code = '" + row.getProductCode() + "'");
			query.append(" OR ");
		}
		query.delete(query.length()-4, query.length());
		query.append(";");
		
		return query.toString();		
	}
	
	
	
	/**
	 * Gets a SQL SELECT statement that will retrieve all rows and columns for all products, ordered alphabetically by name
	 * @return a SQL SELECT statement String
	 */
	public static String getAllProducts() {
		return "SELECT prod_id, prod_code, prod_name, prod_cost_price, prod_price, prod_qoh, prod_category, supp_name, prod_active"
				+ " FROM product, supplier "
				+ "WHERE product.supp_id = supplier.supp_id "
				+ "ORDER BY prod_name;";
	}
	
	
	
	public static String getZeroSaleProducts(String startDate, String endDate) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT 0 as 'Units Sold', "
				+ "prod_name as 'Name', "
				+ "product.prod_id as 'ID' "
				+ "FROM product "
				+ "WHERE product.prod_id NOT IN "
				+ "(select line.prod_id as 'ID' "
				+ "FROM line, sale, product "
				+ "WHERE sale_date "
				+ "BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND line.sale_id = sale.sale_id "
				+ "AND line.prod_id = product.prod_id) "
				+ "ORDER BY prod_name;");
		return query.toString();
	}
	
	
	
	// ==========================================================================
	// Top/Slow Seller Report SELECT Methods
	// ==========================================================================
	
	
	
	public static String getTopSellerQuery(TopSellerFilter filter) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT SUM(line.line_units) as 'Units Sold', "
				+ "prod_name as 'Name', line.prod_id as 'ID' "
				+ "FROM line, sale, product "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + filter.getStartDate() + "' ");
		query.append("AND ");
		query.append("'" + filter.getEndDate() + "' ");
		query.append("AND line.sale_id = sale.sale_id "
				+ "AND line.prod_id = product.prod_id "
				+ "GROUP BY line.prod_id "
				+ "ORDER BY SUM(line.line_units) DESC "
				+ "LIMIT ");
		query.append(filter.getLimit() + ";");
		return query.toString();
	}
	
	
	
	public static String getSlowSellerQuery(String startDate, String endDate, int unitsSold) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT SUM(line.line_units) as 'Units Sold', "
				+ "prod_name as 'Name', "
				+ "line.prod_id as 'ID' "
				+ "FROM line, sale, product "
				+ "WHERE sale_date BETWEEN ");
		query.append("'" + startDate + "' ");
		query.append("AND ");
		query.append("'" + endDate + "' ");
		query.append("AND line.sale_id = sale.sale_id "
				+ "AND line.prod_id = product.prod_id "
				+ "GROUP BY line.prod_id "
				+ "HAVING SUM(line.line_units) <= ");
		query.append(unitsSold + " ");
		query.append("ORDER BY SUM(line.line_units) DESC;");
		return query.toString();
	}
	
	
	
	// ==========================================================================
	// Other SELECT Methods
	// ==========================================================================

	
	
	public static String getBarcodeMatchQuery(long barcode) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT prod_id FROM product WHERE prod_id = ");
		query.append(barcode + ";");
		
		return query.toString();
	}
	
	
	
	public static String getProductCodeMatchQuery(String productCode) {
		 StringBuffer query = new StringBuffer();
		 query.append("SELECT prod_id FROM product WHERE prod_code = '");
		 query.append(productCode + "';");
		 
		 return query.toString();
	}
	
	
	
	/**
	 * Gets a SQL SELECT statement that will get the most recent sale id from the sale table
	 * @return a SQL SELECT statement String
	 */
	public static String getLastSaleIdQuery() {
		return "SELECT MAX(sale_id) as 'Last Sale ID' FROM sale;";
	}
	
	
	
	/**
	 * Gets a SQL SELECT statement that will return all the names of product suppliers from the supplier table
	 * @return a SQL SELECT statement String
	 */
	public static String getSupplierNamesQuery() {
		return "SELECT supp_name FROM supplier ORDER BY supp_id;";
	}
	
	
	
	/**
	 * Gets a SQL SELECT statement that will return all the distinct category names from the product table
	 * @return a SQL SELECT statement
	 */
	public static String getCategoryNamesQuery() {
		return "SELECT DISTINCT prod_category FROM product ORDER BY prod_category;";
	}
	

	
	public static String getProductQuantityQuery(String productCode) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT prod_qoh "
				+ "FROM product "
				+ "WHERE prod_code = ");
		query.append("'" + productCode + "';");
		
		return query.toString();
	}
	
	
	
	public static String[] getProductsEmptyQueries(Sale sale) {
		StringBuffer currentStatement = new StringBuffer();
		String[] statements = new String[sale.getNumberOfLines()];
		ArrayList<Line> lineItems = sale.getLineItems();
		int i = 0;
		
		for(Line l: lineItems) {
			currentStatement.append("SELECT prod_code, prod_name "
					+ "FROM product "
					+ "WHERE ");
			currentStatement.append("prod_code = '" + l.getProduct().getCode() + "'");
			currentStatement.append(" AND prod_qoh = 0;");
			statements[i] = currentStatement.toString();
			i++;
			currentStatement.delete(0, currentStatement.length());
		}
		
		return statements;
	}
	
	
	
	// ==========================================================================
	// Sale/Line INSERT Methods
	// ==========================================================================
	
	
	
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
	
	
	
	// ==========================================================================
	// Product INSERT Method
	// ==========================================================================
	
	
	
	public static String getProductInsertStatement(Product product) {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO product VALUES(");
		query.append(product.getId() +", ");
		query.append("'" + product.getCode() + "', ");
		query.append("'" + product.getName() + "', ");
		query.append(product.getCostPrice().doubleValue() + ", ");
		query.append(product.getPrice().doubleValue() + ", ");
		query.append(product.getQuantityOnHand() + ", ");
		query.append("'" + product.getCategory() + "', ");
		query.append(product.getSupplierId() + ", ");
		if(product.isActive()) {
			query.append("'Y');");
		}
		else {
			query.append("'N');");
		}
		
		return query.toString();
	}
	
	
	
	// ==========================================================================
	// UPDATE Methods
	// ==========================================================================
	
	
	
	public static String[] getInvoiceUpdateStatements(ArrayList<InvoiceRowComparison> comparisonSet) {
		String[] statements = new String[comparisonSet.size()];
		StringBuffer currentStatement = new StringBuffer();
		
		for(int i = 0; i < comparisonSet.size(); i++) {
			currentStatement.append("UPDATE product SET ");
			currentStatement.append("prod_cost_price = ");
			currentStatement.append(comparisonSet.get(i).getNewCostPrice() + ", ");
			currentStatement.append("prod_price = ");
			currentStatement.append(comparisonSet.get(i).getNewPrice() + ", ");
			currentStatement.append("prod_qoh = ");
			currentStatement.append(comparisonSet.get(i).getNewQuantity() + " ");
			currentStatement.append("WHERE prod_code = '");
			currentStatement.append(comparisonSet.get(i).getProductCode() + "';");
			
			statements[i] = currentStatement.toString();
			currentStatement.delete(0, currentStatement.length());
		}
		return statements;
	}
	
	
	
	public static String getProductUpdateStatement(ProductEditFilter filter) {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE product SET ");
		if(filter.hasNameChanged()) {
			query.append(" prod_name = ");
			query.append("'" + filter.getModifiedProduct().getName() + "',");
		}
		if(filter.hasCostPriceChanged()) {
			query.append(" prod_cost_price = ");
			query.append(filter.getModifiedProduct().getCostPrice() + ",");
		}
		if(filter.hasPriceChanged()) {
			query.append(" prod_price = ");
			query.append(filter.getModifiedProduct().getPrice() + ",");
		}
		if(filter.hasQuantityChanged()) {
			query.append(" prod_qoh = ");
			query.append(filter.getModifiedProduct().getQuantityOnHand() + ",");
		}
		if(filter.hasCategoryChanged()) {
			query.append(" prod_category = ");
			query.append("'" + filter.getModifiedProduct().getCategory() + "',");
		}
		if(filter.hasSupplierChanged()) {
			query.append(" supp_id = ");
			query.append(filter.getModifiedProduct().getSupplierId() + ",");
		}
		if(filter.hasActiveChanged()) {
			query.append(" prod_active = ");
			if(filter.getModifiedProduct().isActive()) {
				query.append("'Y',");
			}
			else {
				query.append("'N',");
			}
		}
		query.delete(query.length()-1, query.length()); // Removes extra ','
		query.append(" WHERE prod_id = ");
		query.append(filter.getModifiedProduct().getId() + ";");
		
		return query.toString();
	}
	
	
	
	public static String[] getStockAdjustmentsUpdateStatements(Sale sale) {
		String[] statements = new String[sale.getNumberOfLines()];
		ArrayList<Line> lineItems = sale.getLineItems();
		StringBuffer currentStatement = new StringBuffer();
		int i = 0;
		for(Line line: lineItems) {
			if(line.getLineUnits() > 0) {
				currentStatement.append("UPDATE product "
						+ "SET prod_qoh = GREATEST(0, prod_qoh-");
				currentStatement.append(line.getLineUnits() + ") ");
				currentStatement.append("WHERE prod_id = ");
				currentStatement.append(line.getProductId() + ";");
				
				statements[i] = currentStatement.toString();
			}
			else if (!line.getDoNotAdjustFlag()){
				currentStatement.append("UPDATE product "
						+ "SET prod_qoh = prod_qoh + ");
				currentStatement.append(Math.abs(line.getLineUnits()) + " ");
				currentStatement.append("WHERE prod_id = ");
				currentStatement.append(line.getProductId() + ";");
				
				statements[i] = currentStatement.toString();
			}
			i++;
			currentStatement.delete(0, currentStatement.length());
		}
		return statements;
	}
	
}// End class