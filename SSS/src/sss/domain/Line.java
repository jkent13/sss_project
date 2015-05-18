/* Line Class
*  Represents a single line from a single sale
*  Original Author: Josh Kent
*  
*  SET BIGDECIMAL ROUNDING MODE TO HALF-EVEN
*/

package sss.domain;

import java.math.BigDecimal;
import java.sql.SQLException;

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
	
	public Line(long sale_id, Product prod, int line_number) throws SQLException {
		this.sale_id = sale_id;
		this.line_number = line_number;
		product = prod;
		this.prod_id = product.getId();
		line_price = product.getPrice();
		line_cost_price = product.getCostPrice();
		line_cost_amount = line_cost_price.multiply(new BigDecimal(line_units)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		line_amount = line_price.multiply(new BigDecimal(line_units)).multiply(line_discount).setScale(2, BigDecimal.ROUND_HALF_EVEN);	
	}
	
	public void setDiscount(double discountPercentage) {
		if (discountPercentage >= 0.0) {
			line_discount = BigDecimal.ONE;
			line_discount = line_discount.subtract(new BigDecimal(discountPercentage / 100.0)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			line_amount = line_price.multiply(new BigDecimal(line_units)).multiply(line_discount).setScale(2, BigDecimal.ROUND_HALF_EVEN);	
		}
	}
	
	public long getSaleId() {
		return sale_id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public BigDecimal getLineAmount() {
		return line_amount;
	}
	
	public BigDecimal getLineCostAmount() {
		return line_cost_amount;
	}
	
	public int getLineNumber() {
		return line_number;
	}
	
	public long getProductId() {
		return prod_id;
	}
	
	public String getProductName() {
		return product.getName();
	}
	public int getLineUnits() {
		return line_units;
	}
	
	public BigDecimal getLinePrice() {
		return line_price;
	}
	
	public BigDecimal getLineCostPrice() {
		return line_cost_price;
	}
	
	public BigDecimal getDiscount() {
		return line_discount.multiply(new BigDecimal(100)).subtract(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public void setQuantity(int quantity) {
		if (quantity > 0) { // Refunds will use negative quantities?
			line_units = quantity;
			line_cost_amount = line_cost_price.multiply(new BigDecimal(line_units)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			line_amount = line_price.multiply(new BigDecimal(line_units)).multiply(line_discount).setScale(2, BigDecimal.ROUND_HALF_EVEN);		
		}
	}
	
	@Override
	public String toString() {
		return "Sale ID: " + sale_id + " Line Number: " + line_number + " Product ID: " + prod_id + " Line Price: " + line_price + " Line Units: "
				+ line_units + " Line Discount: " + getDiscount() + " Line Total: " + line_amount;
	}
}
