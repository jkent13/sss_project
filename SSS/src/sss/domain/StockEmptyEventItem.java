/* StockEmptyEventItem Class
 * 
 * Event representing a product's quantity on hand value has just dropped to 0 
 * which should generally represent an item running out of stock. Fired after a 
 * sale has been processed.
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.awt.Color;
import java.util.Date;

import sss.domain.EventItem;

@SuppressWarnings("serial")
public class StockEmptyEventItem extends EventItem {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	public static final transient Color EVENT_COLOR = new Color(0, 138, 184); // Blue
	
	private String productCode;
	private String productName;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public StockEmptyEventItem(int eventType, Date timeStamp, String productCode, String productName) {
		super(eventType, timeStamp);
		this.productCode = productCode;
		this.productName = productName;
		setColor(EVENT_COLOR);
	}
	
	
	
	// ==========================================================================
	// Printing Method
	// ==========================================================================
	
	
	
	@Override
	public String toString() {
		return "[Stock Empty] " + timeStamp + " | " + productCode + " | " + productName + "\n";
	}
	
}
