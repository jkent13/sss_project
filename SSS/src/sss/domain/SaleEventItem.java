package sss.domain;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Date;

public class SaleEventItem extends EventItem {
	public static final Color EVENT_COLOR = new Color(46, 184, 0);
	public static BigDecimal EVENT_FIRE_THRESHOLD = new BigDecimal(500.00);
	
	private BigDecimal total;
	private long saleId;
	
	public SaleEventItem(int eventType, Date timeStamp, long saleId, BigDecimal total) {
		super(eventType, timeStamp);
		this.saleId = saleId;
		this.total = total;
		setColor(EVENT_COLOR);
	}
	
	public void setEventFireThreshold(BigDecimal newThreshold) {
		if(newThreshold.compareTo(BigDecimal.ZERO) > 0) {
			EVENT_FIRE_THRESHOLD = newThreshold;
		}
	}
	
	public BigDecimal getEventFireThreshold() {
		return EVENT_FIRE_THRESHOLD;
	}
	
	@Override
	public String toString() {
		return "[Big Sale] " + timeStamp + " | Sale ID: " + saleId + " | Sale Total: $" + total + "\n";
	}
}
