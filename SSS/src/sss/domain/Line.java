package sss.domain;

import java.sql.SQLException;

/* Line Class
*  Represents a single line from a single sale
*
* CHANGE MONEY DOUBLES TO BIGDECIMAL!!!!!!!!!
* 
*/

public class Line {
	
	private long sale_id; 		// Composite PK - Reference to sale to which the line belongs
	private int line_number = 1; 	// Composite PK - Reference to the line number within a sale
	private Product product;
	private long prod_id;				// Reference to product barcode
	private int line_units = 1;				// Number of units (must be >= 1)
	private double line_price;			// Selling price for the product listed
	private double line_cost_price;		// Cost price for the product listed
	private double line_amount;			// Line subtotal, (units * selling price - discount)
	private double line_cost_amount;	// Line cost subtotal (units * cost price)
	private double line_discount = 1; // Discount multiplier to be applied to this line
	
	public Line(long sale_id, long prod_id, int line_number) throws SQLException {
		this.sale_id = sale_id;
		this.line_number = line_number;
		product = new Product(prod_id);
		this.prod_id = product.getId();
		line_price = product.getPrice();
		line_cost_price = product.getCostPrice();
		line_cost_amount = line_cost_price * line_units;
		line_amount = line_price * line_units * line_discount;		
	}
	
	public Line(long sale_id, Product prod, int line_number) throws SQLException {
		this.sale_id = sale_id;
		this.line_number = line_number;
		product = prod;
		this.prod_id = product.getId();
		line_price = product.getPrice();
		line_cost_price = product.getCostPrice();
		line_cost_amount = line_cost_price * line_units;
		line_amount = line_price * line_units * line_discount;		
	}
	
	public void setDiscount(double discountPercentage) {
		if (discountPercentage > 0.0) {
			line_discount = 1 - (discountPercentage / 100.0);
			line_amount = line_price * line_units * line_discount;	
		}
	}
	
	public long getSaleId() {
		return sale_id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public double getLineAmount() {
		return line_amount;
	}
	
	public double getLineCostAmount() {
		return line_cost_amount;
	}
	
	public int getLineNumber() {
		return line_number;
	}
	
	public long getProductId() {
		return prod_id;
	}
	
	public int getLineUnits() {
		return line_units;
	}
	
	public double getLinePrice() {
		return line_price;
	}
	
	public double getLineCostPrice() {
		return line_cost_price;
	}
	
	public double getDiscount() {
		return line_discount;
	}
	
	public void setQuantity(int quantity) {
		if (quantity > 0) { // Refunds will use negative quantities?
			line_units = quantity;
			line_cost_amount = line_cost_price * line_units;
			line_amount = line_price * line_units * line_discount;	
		}
	}
	
	@Override
	public String toString() {
		return "Sale ID: " + sale_id + " Line Number: " + line_number + " Product ID: " + prod_id + " Line Price: " + line_price + " Line Units: "
				+ line_units + " Line Discount Multiplier: " + line_discount + " Line Total: " + line_amount;
	}
}
