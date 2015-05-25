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
	public static String getLookupQueryById(long prod_id) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE prod_id = ");
		query.append(prod_id);
		query.append(";");
		
		return query.toString();
	}
	
	public static String getLookupQueryByCode(String prod_code) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE prod_code = '");
		query.append(prod_code);
		query.append("';");
		
		return query.toString();
	}
	
	public static String getLookupQueryByNameAndCategory(String prod_name, String prod_category) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%");
		query.append(prod_name.toUpperCase());
		query.append("%' AND prod_category = '");
		query.append(prod_category);
		query.append("';");
		
		return query.toString();
	}
	
	public static String getLookupQueryByName(String prod_name) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%");
		query.append(prod_name.toUpperCase());
		query.append("%';");
		
		return query.toString();
	}
	
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
}
