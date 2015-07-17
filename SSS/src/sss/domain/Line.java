/* Line Class
 * 
*  Represents a single line from a single sale
*  
*  Original Author: Josh Kent
*/

package sss.domain;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Line {
	
	private long sale_id; 								// Composite PK - Reference to sale to which the line belongs
	private int line_number = 1; 						// Composite PK - Reference to the line number within a sale
	
	private Product product; 							// Reference to the product related to this line
	
	private long prod_id;								// Reference to product barcode
	private int line_units = 1;							// Number of units (must be >= 1)
	private BigDecimal line_price;						// Selling price for the product listed
	private BigDecimal line_cost_price;					// Cost price for the product listed
	private BigDecimal line_amount;						// Line subtotal, (units * selling price - discount)
	private BigDecimal line_cost_amount;				// Line cost subtotal (units * cost price)
	private BigDecimal line_discount = BigDecimal.ONE; 	// Discount multiplier to be applied to this line
	
	/**
	 * Creates a new Line from a sale id, a product id and a line number
	 * @param sale_id the id for the Sale object that this Line belongs to
	 * @param prod_id the id for the Product object that will be displayed on this Line
	 * @param line_number the line number (position in Sale line items)
	 * @throws SQLException this exception will be thrown if there is issues creating the Product which accesses the database
	 */
	public Line(long sale_id, long prod_id, int line_number) throws SQLException {
		this.sale_id = sale_id;
		this.line_number = line_number;
		product = new Product(prod_id);
		this.prod_id = product.getId();
		line_price = product.getPrice();
		line_cost_price = product.getCostPrice();
		line_cost_amount = line_cost_price.multiply(new BigDecimal(line_units));
		line_amount = line_price.multiply(new BigDecimal(line_units)).multiply(line_discount);		
	}
	
	/**
	 * Creates a new Line from a sale id, a Product object and a line number
	 * @param sale_id sale_id the id for the Sale object that this Line belongs to
	 * @param prod the Product object displayed on this Line
	 * @param line_number line_number the line number (position in Sale line items)
	 */
	public Line(long sale_id, Product prod, int line_number) {
		this.sale_id = sale_id;
		this.line_number = line_number;
		product = prod;
		this.prod_id = product.getId();
		line_price = product.getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN);
		line_cost_price = product.getCostPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN);
		line_cost_amount = line_cost_price.multiply(new BigDecimal(line_units)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		line_amount = line_price.multiply(new BigDecimal(line_units)).multiply(line_discount).setScale(2, BigDecimal.ROUND_HALF_EVEN);	
	}
	
	/**
	 * Setter method for the discount value 
	 * @param discountPercentage a discount expressed as a percentage (e.g. 40 would mean a 40% discount)
	 */
	public void setDiscount(double discountPercentage) {
		if (discountPercentage >= 0.0) { // A discount percentage must be positive or 0 
			
			// Reset discount to default (necessary to avoid errors)
			line_discount = BigDecimal.ONE; 
			
			// The discount percentage is converted to a discount multiplier for easier calculations (e.g. 40% discount is represented by
			// 0.60 multiplier)
			line_discount = line_discount.subtract(new BigDecimal(discountPercentage / 100.0)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			// Changes to the discount will affect the line amount so the line amount must be updated
			line_amount = line_price.multiply(new BigDecimal(line_units)).multiply(line_discount).setScale(2, BigDecimal.ROUND_HALF_EVEN);	
		}
	}
	
	/**
	 * Getter method for sale id
	 * @return the sale id related to this Line
	 */
	public long getSaleId() {
		return sale_id;
	}
	
	/**
	 * Getter method for product
	 * @return the Product object for this Line
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * Getter method for line amount
	 * @return the line amount (line total)
	 */
	public BigDecimal getLineAmount() {
		return line_amount;
	}
	
	/**
	 * Getter method for line cost amount (line cost price x line units)
	 * @return the line cost amount
	 */
	public BigDecimal getLineCostAmount() {
		return line_cost_amount;
	}
	
	/**
	 * Getter method for the line number
	 * @return this Line's line number (position in Sale)
	 */
	public int getLineNumber() {
		return line_number;
	}
	
	/**
	 * Getter method for the product id
	 * @return the product id for the Product object associated with this Line
	 */
	public long getProductId() {
		return prod_id;
	}
	
	/**
	 * Getter method for the product name
	 * @return the name of the product associated with this Line
	 */
	public String getProductName() {
		return product.getName();
	}
	
	/**
	 * Getter method for line units
	 * @return the line units (the quantity) for this Line 
	 */
	public int getLineUnits() {
		return line_units;
	}
	
	/**
	 * Getter method for the line price 
	 * @return the line price (retail price for the product)
	 */
	public BigDecimal getLinePrice() {
		return line_price;
	}
	
	/**
	 * Getter method for the line cost price
	 * @return the line cost price (the wholesale price for the product)
	 */
	public BigDecimal getLineCostPrice() {
		return line_cost_price;
	}
	
	/**
	 * Getter method for the discount percentage of this line - NOT the discount multiplier
	 * @return the discount percentage of this expressed as a negative decimal number (e.g. -50.00 is a 50% discount)
	 */
	public BigDecimal getDiscount() {
		// The discount multiplier is converted back to a percentage
		return line_discount.multiply(new BigDecimal(100)).subtract(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	 * Setter method for line units (also referred to as quantity)
	 * @param quantity the new value for units
	 */
	public void setQuantity(int quantity) {
		if (quantity > 0) { 				// Refunds will use negative quantities, currently not supported
			line_units = quantity; 
			
			// Changing line units affects line cost amount and line amount, so their values must be updated
			line_cost_amount = line_cost_price.multiply(new BigDecimal(line_units)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			line_amount = line_price.multiply(new BigDecimal(line_units)).multiply(line_discount).setScale(2, BigDecimal.ROUND_HALF_EVEN);		
		}
	}
	
	/**
	 * Setter method for line number - this is necessary for the Sale object's rebuildLineItems() method
	 * @param line_number the new line number for this Line object, must not be negative
	 */
	public void setLineNumber(int line_number) {
		if(line_number > 0) {
			this.line_number = line_number;
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: Invalid value for line number", "Invalid argument", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Overridden toString() method to display most important information about a Line object. Used mostly for testing
	@Override
	public String toString() {
		return "Sale ID: " + sale_id + " Line Number: " + line_number + " Product ID: " + prod_id + " Line Price: " + line_price + " Line Units: "
				+ line_units + " Line Discount: " + getDiscount() + " Line Total: " + line_amount;
	}
	
} //End class
