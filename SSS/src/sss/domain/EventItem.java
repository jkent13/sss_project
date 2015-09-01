package sss.domain;

import java.awt.Color;

public abstract class EventItem {
	public static final int TYPE_BIG_SALE = 0;
	public static final int TYPE_REFUND = 1;
	public static final int TYPE_STOCK_EMPTY = 2;
	
	public static final String EVENT_SEPARATOR = "--------------------\n";
	
	protected int eventType;
	protected String timeStamp;
	protected Color color;
	
	public EventItem(int eventType, String timeStamp) {
		if(eventType >= 0 && eventType < 4) {
			this.eventType = eventType;
		}
		else {
			throw new IllegalArgumentException("Error: Invalid event type");
		}
		this.timeStamp = timeStamp;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getType() {
		return eventType;
	}
	
	protected void printSeparator() {
		System.out.println(EventItem.EVENT_SEPARATOR);
	}
	
}
