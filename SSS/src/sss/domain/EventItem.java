/* EventItem Class
 * 
 * Abstract EventItem superclass for all types of EventItems (related to Dashboard)
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class EventItem {
	
	// ==========================================================================
	// Variables: Constants
	// ==========================================================================
	
	
	
	public static final int TYPE_BIG_SALE = 0;
	public static final int TYPE_REFUND = 1;
	public static final int TYPE_STOCK_EMPTY = 2;
	
	public static final String EVENT_SEPARATOR = "--------------------\n";
	
	
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	protected int eventType;
	protected String timeStamp;
	protected Color color;
	
	protected SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public EventItem(int eventType, Date timeStamp) {
		if(eventType >= 0 && eventType < 4) {
			this.eventType = eventType;
		}
		else {
			throw new IllegalArgumentException("Error: Invalid event type");
		}
		this.timeStamp = timeFormat.format(timeStamp);
	}
	
	
	
	// ==========================================================================
	// Setter Method
	// ==========================================================================
	
	
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public Color getColor() {
		return color;
	}
	
	
	
	public int getType() {
		return eventType;
	}
	
}
