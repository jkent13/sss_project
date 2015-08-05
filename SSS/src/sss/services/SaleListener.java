/* SaleListener Interface
 * 
 * The SaleListener interface must be implemented by any class that wants to listen to or 
 * 'observe' a Sale object. SaleListeners that are registered as listeners for a particular sale object
 * have their update method called whenever a that sale object's total or balance changes
 * 
 * Original Author: Josh Kent
 */

package sss.services;

import java.math.BigDecimal;

public interface SaleListener {
	final static int SALE_TOTAL = 1;
	final static int SALE_BALANCE = 2;
	
	public void update(int eventType, BigDecimal newValue);
}