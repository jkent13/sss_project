package sss.domain;

import java.awt.Color;
import java.util.Date;

import sss.domain.EventItem;

public class StockEmptyEventItem extends EventItem {
	public static final Color EVENT_COLOR = new Color(0, 138, 184);
	
	private String productCode;
	private String productName;
	
	public StockEmptyEventItem(int eventType, Date timeStamp, String productCode, String productName) {
		super(eventType, timeStamp);
		this.productCode = productCode;
		this.productName = productName;
		setColor(EVENT_COLOR);
	}
	
	@Override
	public String toString() {
		return "[Stock Empty] " + timeStamp + " | " + productCode + " | " + productName + "\n";
	}
}
