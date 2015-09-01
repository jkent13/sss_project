package sss.domain;

import java.awt.Color;
import java.math.BigDecimal;

public class RefundEventItem extends EventItem {
	public static final Color EVENT_COLOR = new Color(184, 46, 0);
	
	private BigDecimal total;
	private long saleId;
	
	public RefundEventItem(int eventType, String timeStamp, long saleId, BigDecimal total) {
		super(eventType, timeStamp);
		this.saleId = saleId;
		this.total = total;
		setColor(EVENT_COLOR);
	}
	
	@Override
	public String toString() {
		return "[Refund] " + timeStamp + " | Sale ID: " + saleId + " | Amount: $" + total + "\n";
	}
	
}
