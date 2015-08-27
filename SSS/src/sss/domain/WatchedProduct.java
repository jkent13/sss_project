/* WatchedProduct Class
 * 
 * Data structure used to represent a product being watched from the dashboard
 * 
 * Original Author: Josh Kent
 */
package sss.domain;

public class WatchedProduct {

	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private String productCode;
	private int originalQuantity;
	private int currentQuantity;
	private int proportionFilled;
	
	
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	public WatchedProduct(String productCode, int originalQuantity) {
		this.productCode = productCode;
		this.originalQuantity = originalQuantity;
	}
	
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public String getProductCode() {
		return productCode;
	}
	
	
	
	public int getOriginalQuantity() {
		return originalQuantity;
	}
	
	
	
	public int getCurrentQuantity() {
		return currentQuantity;
	}
	
	
	
	public int getProportionFilled() {
		calculateProportionFilled();
		return proportionFilled;
	}
	
	
	
	// ==========================================================================
	// Other Methods
	// ==========================================================================
	
	
	
	public void findCurrentQuantity() {
		// TODO read from file/db and listen to sales?
		// poll db?
		// set currentQuantity and recalculate proportionFilled
		// repaint
	}
	
	
	
	public void calculateProportionFilled() {
		proportionFilled = Integer.valueOf(currentQuantity / originalQuantity);
		if(proportionFilled > originalQuantity) {
			proportionFilled = originalQuantity;
		}
	}
	
}
