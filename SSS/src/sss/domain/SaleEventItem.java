package sss.domain;

import java.awt.Color;
import java.math.BigDecimal;

public class SaleEventItem extends EventItem {
	public static final Color EVENT_COLOR = new Color(46, 184, 0);
	
	private BigDecimal total;
	private long saleId;
	
	public SaleEventItem(int eventType, String timeStamp, long saleId, BigDecimal total) {
		super(eventType, timeStamp);
		this.saleId = saleId;
		this.total = total;
		setColor(EVENT_COLOR);
	}
	
	@Override
	public String toString() {
		return "[Big Sale] " + timeStamp + " | Sale ID: " + saleId + " | Sale Total: $" + total + "\n";
	}
}
