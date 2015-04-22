package sss.domain;

import java.sql.SQLException;
import java.sql.ResultSet;
import sss.services.DbReader;

public class Product {
	private DbReader databaseReader = new DbReader();
	private ResultSet productDetails;
	
	private long prod_id; // PK
	
	private String prod_code; // Substitute PK (should also be unique)
	private String prod_name; // Product's name
	private double prod_cost_price; // Price of product bought from supplier (Wholesale price)
	private double prod_price; // Price of product bought by customer (Retail price)
	private int prod_qoh; // Product quantity on hand (must be >= 0)
	private String prod_category; // Product category
	private boolean prod_active; // Must be converted to char 'Y' or 'N', inactive products are not return in reports
	
	private int supp_id; // Reference to supplier
	
	public Product(long prod_id) throws SQLException {
		this.prod_id = prod_id;
		productDetails = databaseReader.lookUpProduct(prod_id);
		
		if (productDetails.next()){
			prod_code = productDetails.getString("prod_code");
			prod_name = productDetails.getString("prod_name");
			prod_cost_price = productDetails.getDouble("prod_cost_price");
			prod_price = productDetails.getDouble("prod_price");
			prod_qoh = productDetails.getInt("prod_qoh");
			prod_category = productDetails.getString("prod_category");
			if(productDetails.getString("prod_active") == "Y") {
				prod_active = true;
			}
			else {
				prod_active = false;
			}
			supp_id = productDetails.getInt("supp_id");
		}
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
	
	public double getCostPrice() {
		return this.prod_cost_price;
	}
	
	public double getPrice() {
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
