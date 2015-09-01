/* SaleEventItem Class
 * 
 * Event representing a particularly large sale transaction 
 * The event will fire when a sale with a total greater than the 
 * EVENT_FIRE_THRESHOLD is processed (default is $500.00)
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Date;

public class SaleEventItem extends EventItem {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	public static final Color EVENT_COLOR = new Color(46, 184, 0); // Green
	public static BigDecimal EVENT_FIRE_THRESHOLD = new BigDecimal(500.00);
	
	private BigDecimal total;
	private long saleId;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public SaleEventItem(int eventType, Date timeStamp, long saleId, BigDecimal total) {
		super(eventType, timeStamp);
		this.saleId = saleId;
		this.total = total;
		setColor(EVENT_COLOR);
	}
	
	
	
	// ==========================================================================
	// Setter Method
	// ==========================================================================
	
	
	
	public void setEventFireThreshold(BigDecimal newThreshold) {
		if(newThreshold.compareTo(BigDecimal.ZERO) > 0) {
			EVENT_FIRE_THRESHOLD = newThreshold;
		}
	}
	
	
	
	// ==========================================================================
	// Getter Method
	// ==========================================================================
	public BigDecimal getEventFireThreshold() {
		return EVENT_FIRE_THRESHOLD;
	}
	
	
	
	// ==========================================================================
	// Printing Method
	// ==========================================================================
	
	
	
	@Override
	public String toString() {
		return "[Big Sale] " + timeStamp + " | Sale ID: " + saleId + " | Sale Total: $" + total + "\n";
	}
}
