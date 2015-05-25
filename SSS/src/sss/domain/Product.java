/* Product Class
 * 
*  Represents a real world product / item and its details
*  
*  Original Author: Josh Kent
*/

package sss.domain;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.ResultSet;

import sss.services.DbReader;
import sss.services.SqlBuilder;

public class Product {
	private ResultSet productDetails; 		// Product details pulled from DB
	
	private long prod_id; 					// PK (13 digit barcode) eg 9315248963524
	
	private String prod_code;				// Substitute PK (should also be unique) eg TBEX123
	private String prod_name; 				// Product's name eg Blue pen
	private BigDecimal prod_cost_price; 	// Price of product bought from supplier (Wholesale price) eg 3.00
	private BigDecimal prod_price; 			// Price of product bought by customer (Retail price) eg 6.00
	private int prod_qoh; 					// Product quantity on hand (must be >= 0) eg 9
	private String prod_category; 			// Product category eg Office, Pet, Homeware
	private boolean prod_active; 			// Must be converted to char 'Y' or 'N', inactive products are not return in reports
	
	private int supp_id;					// Reference to supplier eg 1, 2, 3, 4, 5
	
	public Product(long prod_id) throws SQLException {
		this.prod_id = prod_id;
		 String lookUpQuery = SqlBuilder.getLookupQueryById(prod_id);
		 productDetails = DbReader.executeQuery(lookUpQuery);
		if (productDetails.next()){
			prod_code = productDetails.getString("prod_code");
			prod_name = productDetails.getString("prod_name");
			prod_cost_price = new BigDecimal(productDetails.getDouble("prod_cost_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			prod_price = new BigDecimal(productDetails.getDouble("prod_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			prod_qoh = productDetails.getInt("prod_qoh");
			prod_category = productDetails.getString("prod_category");
			if(productDetails.getString("prod_active").toUpperCase().equals("Y")) {
				prod_active = true;
			}
			else {
				prod_active = false;
			}
			supp_id = productDetails.getInt("supp_id");
		}
	}
	
	public Product(long prod_id, String prod_code, String prod_name, BigDecimal prod_cost_price, BigDecimal prod_price, int prod_qoh, String prod_category, boolean prod_active) {
		this.prod_id = prod_id;
		this.prod_code = prod_code;
		this.prod_name = prod_name;
		this.prod_cost_price = prod_cost_price;
		this.prod_price = prod_price;
		this.prod_qoh = prod_qoh;
		this.prod_category = prod_category;
		this.prod_active = prod_active;
	}
	
	public long getId() {
		return this.prod_id;
	}
	
	public String getName() {
		return this.prod_name;
	}
	
	public String getCode() {
		return this.prod_code;
	}
	
	public BigDecimal getCostPrice() {
		return this.prod_cost_price;
	}
	
	public BigDecimal getPrice() {
		return this.prod_price;
	}
	
	public int getQuantity() {
		return this.prod_qoh;
	}
	
	public String getCategory() {
		return this.prod_category;
	}
	
	public boolean isActive() {
		return this.prod_active;
	}
	
	public int getSupplierId() {
		return this.supp_id;
	}
	
	
	
	
	
}
