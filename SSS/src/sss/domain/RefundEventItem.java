/* RefundEventItem Class
 * 
 * Event representing a refund being processed. Event will fire on any sale transaction
 * that counts as a refund (typically those with a negative sale total).
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class RefundEventItem extends EventItem {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	public static final transient Color EVENT_COLOR = new Color(184, 46, 0); // Red
	
	private BigDecimal total;
	private long saleId;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public RefundEventItem(int eventType, Date timeStamp, long saleId, BigDecimal total) {
		super(eventType, timeStamp);
		this.saleId = saleId;
		this.total = total;
		setColor(EVENT_COLOR);
	}
	
	
	
	// ==========================================================================
	// Printing Method
	// ==========================================================================
	
	
	
	@Override
	public String toString() {
		return "[Refund] " + timeStamp + " | Sale ID: " + saleId + " | Amount: $" + total + "\n";
	}
	
}
