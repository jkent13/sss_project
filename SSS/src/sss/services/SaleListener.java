package sss.services;

import java.math.BigDecimal;

public interface SaleListener {
	final static int SALE_TOTAL = 1;
	final static int SALE_BALANCE = 2;
	
	public void update(int eventType, BigDecimal newValue);
}
