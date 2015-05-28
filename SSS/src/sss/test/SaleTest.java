/*
 * SaleTest Class
 * Junit Testing for the Sale class
 * Original Author: Amethyst Mayer
 */

package sss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sss.domain.Line;
import sss.domain.Product;
import sss.domain.Sale;

public class SaleTest {
	
	private static Sale testSale;	
	private static String timestamp;
	private static String saleTypePurchase = "Purchase";
	private static String saleTypeRefund = "Refund";
	
	private final int NUMBER_OF_LINES_DEFAULT = 0;
	private final BigDecimal SALE_SUBTOTAL_DEFAULT = new BigDecimal("0");
	private final BigDecimal SALE_GST_DEFAULT = new BigDecimal("0");
	private final BigDecimal SALE_TOTAL = new BigDecimal("0");
	private final BigDecimal SALE_AMOUNT_TENDERED = new BigDecimal("0");
	private final BigDecimal SALE_BALANCE = new BigDecimal("0");
	
	private final Product CAT =  new Product(9312547856932L, "CATY123", "Cat", new BigDecimal(5), new BigDecimal(10), 18, "Pet", true);
	private final Product BED =  new Product(2309493056932L, "BEDY123", "Bed", new BigDecimal(4), new BigDecimal(8), 1, "Furniture", false);
	private final Product PEN =  new Product(9493849856932L, "PENY123", "Pen", new BigDecimal(31), new BigDecimal(62), 8, "Office", true);
	
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
		assertEquals(SALE_GST_DEFAULT, constructor1.getSaleGST());
		assertEquals(SALE_TOTAL, constructor1.getSaleTotal());
		assertEquals(SALE_AMOUNT_TENDERED, constructor1.getSaleAmountTendered());
		assertEquals(SALE_BALANCE, constructor1.getSaleBalance());
		assertEquals(saleTypeRefund, constructor1.getSaleType());
	}

	@Test
	public void testSaleLong() {
		Sale constructor2 = new Sale(232323);
		
		assertEquals(232323, constructor2.getSaleId());
		
		assertEquals(NUMBER_OF_LINES_DEFAULT, constructor2.getNumberOfLines());
		assertEquals(SALE_SUBTOTAL_DEFAULT, constructor2.getSaleSubtotal());
		assertEquals(SALE_GST_DEFAULT, constructor2.getSaleGST());
		assertEquals(SALE_TOTAL, constructor2.getSaleTotal());
		assertEquals(SALE_AMOUNT_TENDERED, constructor2.getSaleAmountTendered());
		assertEquals(SALE_BALANCE, constructor2.getSaleBalance());
		assertEquals(saleTypePurchase, constructor2.getSaleType());
	}

	@Test
	public void testGetSetAmountTendered() {
		BigDecimal amt = new BigDecimal(100);
		testSale.setAmountTendered(amt);
		assertEquals(amt.setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleAmountTendered());
	}

	@Test
	public void testSetTimestamp() {
		java.util.Date date = new java.util.Date();

		java.text.SimpleDateFormat sdf
		= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		String newTime = sdf.format(date);
		
		assertEquals(newTime, testSale.getSaleDate());
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
	public void testGetSaleBalance() {
		BigDecimal amt = new BigDecimal(100);
		testSale.setAmountTendered(amt);
		testSale.calculateBalance();
		assertEquals(amt.setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleBalance());
	}

	@Test
	public void testGetSaleType() {
		assertEquals(saleTypePurchase, testSale.getSaleType());
	}

	@Test
	public void testGetAddLineItems() throws SQLException {
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
	
		ArrayList<Line> lines = testSale.getLineItems();
		
		assertEquals(line1, lines.get(0));
		assertEquals(line2, lines.get(1));
		assertEquals(line3, lines.get(2));
		assertEquals(3, lines.size());
	}

	@Test
	public void testRemoveLineItem() throws SQLException {
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
		
		ArrayList<Line> lines = testSale.getLineItems();
		
		assertEquals(3, lines.size());
	
		testSale.removeLineItem(line2);
		
		lines = testSale.getLineItems();
		
		assertEquals(2, lines.size());
		
		assertEquals(line1, lines.get(0));
		assertEquals(line3, lines.get(1));
	}

	@Test
	public void testCalculateTotal() throws SQLException {
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
		
		testSale.calculateTotal();
		assertEquals(new BigDecimal(80).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleTotal());
	}

	@Test
	public void testCalculateGST() throws SQLException {
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
		
		testSale.calculateTotal();
		testSale.calculateGST();
		
		assertEquals(new BigDecimal(7.27).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleGST());
	}

	@Test
	public void testCalculateSubtotal() throws SQLException {
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
		
		testSale.calculateTotal();
		testSale.calculateGST();
		testSale.calculateSubtotal();
		
		assertEquals(new BigDecimal(72.73).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleSubtotal());
	}

	@Test
	public void testCalculateBalance() throws SQLException {
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
		
		testSale.calculateTotal();
		BigDecimal amt = new BigDecimal(100);
		testSale.setAmountTendered(amt);
		testSale.calculateBalance();
		
		assertEquals(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleBalance());
	}
}
