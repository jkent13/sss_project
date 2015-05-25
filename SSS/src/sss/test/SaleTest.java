package sss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sss.domain.Sale;

public class SaleTest {
	
	private static Sale testSale;	
	private static String timestamp;
	private static String saleTypePurchase = "Purchase";
	private static String saleTypeRefund = "Refund";
	
	private final int NUMBER_OF_LINES_DEFAULT = 0;
	private final BigDecimal SALE_SUBTOTAL_DEFAULT = new BigDecimal("0");
	private final BigDecimal SALE_GST_DEFAULT = new BigDecimal("0");
	
	
	@Before
	public void setUp() throws Exception {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf
		= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		timestamp = sdf.format(dt);

		Long saleId = 160163L;
		
		testSale = new Sale(saleId, timestamp, saleTypePurchase);
		
	}

	@Test
	public void testSaleLongStringString() {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf
		= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		String newTime = sdf.format(dt);
		Sale constructor1 = new Sale(100100, newTime, "Refund");
		
		assertEquals(100100, constructor1.getSaleId());
		assertEquals(newTime, constructor1.getSaleDate());
		assertEquals("Refund", constructor1.getSaleType());
		
		assertEquals(NUMBER_OF_LINES_DEFAULT, constructor1.getNumberOfLines());
		assertEquals(SALE_SUBTOTAL_DEFAULT, constructor1.getSaleSubtotal());
	}

	@Test
	public void testSaleLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAmountTendered() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTimestamp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfLines() {
		assertEquals(0, testSale.getNumberOfLines());
	}

	@Test
	public void testGetSaleId() {
		assertEquals(160163L, testSale.getSaleId());
	}

	@Test
	public void testGetSaleDate() {
		assertEquals(timestamp, testSale.getSaleDate());
	}

	@Test
	public void testGetSaleSubtotal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaleGST() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaleTotal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaleAmountTendered() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaleBalance() {
		testSale.setAmountTendered(new BigDecimal(100));
		testSale.calculateBalance();
		assertEquals(new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleBalance());
	}

	@Test
	public void testGetSaleType() {
		assertEquals(saleTypePurchase, testSale.getSaleType());
	}

	@Test
	public void testGetLineItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLineItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLineItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testRebuildLineItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTotal() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateGST() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateSubtotal() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValid() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
