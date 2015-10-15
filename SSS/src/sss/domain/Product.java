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

import javax.swing.JOptionPane;

import sss.services.DbReader;
import sss.services.SqlBuilder;

public class Product {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private ResultSet productDetails; 						// Product details pulled from DB
	private boolean successfulLookup = true; 			// This will become false if a SQL exception is encountered and a product is not successfully constructed
	
	private long prod_id; 												// PK (13 digit barcode) eg 9315248963524
	
	private String prod_code;											// Substitute PK (should also be unique) eg TBEX123
	private String prod_name; 										// Product's name eg Blue pen
	private BigDecimal prod_cost_price; 					// Price of product bought from supplier (Wholesale price) eg 3.00
	private BigDecimal prod_price; 								// Price of product bought by customer (Retail price) eg 6.00
	private int prod_qoh; 												// Product quantity on hand (must be >= 0) eg 9
	private String prod_category; 								// Product category eg Office, Pet, Homeware
	private boolean prod_active; 									// Must be converted to char 'Y' or 'N', inactive products are not return in reports
	
	private int supp_id;													// Reference to supplier eg 1, 2, 3, 4, 5
	
	
	
	// ==========================================================================
	// Constructors
	// ==========================================================================
	
	
	
	/**
	 * Creates a Product from a product id that is looked up in the database
	 * @param prod_id the product id (i.e. barcode value)
	 */
	public Product(long prod_id) {
		this.prod_id = prod_id;
		 String lookUpQuery = SqlBuilder.getProductById(prod_id);
		 productDetails = DbReader.executeQuery(lookUpQuery);
		 if(productDetails == null) {
			 successfulLookup = false;
			 return;
		 }
		try {
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
				productDetails.close(); // Close ResultSet
			}
		} catch (SQLException e) {
			successfulLookup = false;
			JOptionPane.showMessageDialog(null, "Error: Failed to construct Product", "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	/**
	 * Creates a Product entirely from input arguments (used for test cases)
	 * @param prod_id product id (barcode value)
	 * @param prod_code product code (can be mix of letters and digits, generally unique)
	 * @param prod_name name of the product
	 * @param prod_cost_price wholesale price of the product (less than retail price)
	 * @param prod_price retail price of the product (GST inclusive, the price a customer pays)
	 * @param prod_qoh product quantity on hand (must be positive)
	 * @param prod_category the sale category of a product e.g. Office, Furniture, Food
	 * @param prod_active whether or not the product is active (in use)
	 * @param supp_id the supplier id for this product
	 */
	public Product(long prod_id, String prod_code, String prod_name, BigDecimal prod_cost_price, BigDecimal prod_price, int prod_qoh, String prod_category, boolean prod_active, int supp_id) {
		this.prod_id = prod_id;
		this.prod_code = prod_code;
		this.prod_name = prod_name;
		this.prod_cost_price = prod_cost_price;
		this.prod_price = prod_price;
		if(prod_qoh > 0)
			this.prod_qoh = prod_qoh;
		else 
			this.prod_qoh = 0;
		this.prod_category = prod_category;
		this.prod_active = prod_active;
		this.supp_id = supp_id;
	}
	
	
	
	public Product() {
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	/**
	 * Getter method for the product id
	 * @return the product id value (barcode)
	 */
	public long getId() {
		return this.prod_id;
	}
	
	
	
	/**
	 * Getter method for the product name
	 * @return the product name
	 */
	public String getName() {
		return this.prod_name;
	}
	
	
	
	/**
	 * Getter method for the product code
	 * @return the product code
	 */
	public String getCode() {
		return this.prod_code;
	}
	
	
	
	/**
	 * Getter method for the product cost price (wholesale price)
	 * @return the product cost price
	 */
	public BigDecimal getCostPrice() {
		return this.prod_cost_price;
	}
	
	
	
	/**
	 * Getter method for the product price (retail price)
	 * @return the product price
	 */
	public BigDecimal getPrice() {
		return this.prod_price;
	}
	
	
	
	/**
	 * Getter method for the quantity on hand 
	 * @return the quantity on hand for this product
	 */
	public int getQuantityOnHand() {
		return this.prod_qoh;
	}
	
	
	
	/**
	 * Getter method for the product category
	 * @return the category for this product
	 */
	public String getCategory() {
		return this.prod_category;
	}
	
	
	
	/**
	 * Getter method for whether the product is active
	 * @return whether the product is active
	 */
	public boolean isActive() {
		return this.prod_active;
	}
	
	
	
	/**
	 * Getter method for whether the product was looked up successfully
	 * @return whether the product was successfully looked up (true) or encountered SQL errors (false)
	 */
	public boolean successfulLookup() {
		return this.successfulLookup;
	}
	
	
	
	/**
	 * Getter method for the supplier id
	 * @return the supplier id for this product
	 */
	public int getSupplierId() {
		return this.supp_id;
	}
	
	

	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
	public void setId(long prod_id) {
		this.prod_id = prod_id;
	}

	
	
	public void setCode(String prod_code) {
		this.prod_code = prod_code;
	}

	
	
	public void setName(String prod_name) {
		this.prod_name = prod_name;
	}
	
	

	public void setCostPrice(BigDecimal prod_cost_price) {
		this.prod_cost_price = prod_cost_price;
	}

	
	
	public void setPrice(BigDecimal prod_price) {
		this.prod_price = prod_price;
	}

	public void setQuantityOnHand(int prod_qoh) {
		this.prod_qoh = prod_qoh;
	}

	
	
	public void setCategory(String prod_category) {
		this.prod_category = prod_category;
	}

	
	
	public void setActive(boolean prod_active) {
		this.prod_active = prod_active;
	}

	
	
	public void setSupplierId(int supp_id) {
		this.supp_id = supp_id;
	}
	
	
	
	// ==========================================================================
	// Validation Methods
	// ==========================================================================
	
	
	
	public boolean validateProduct() {
		if(prod_code == null) {
			return false;
		}
		else if(prod_name == null) {
			return false;
		}
		else if(prod_cost_price == null) {
			return false;
		}
		else if(prod_price == null) {
			return false;
		}
		else if(prod_id == 0L) {
			return false;
		}
		else if(prod_category == null) {
			return false;
		}
		else if(supp_id == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
}