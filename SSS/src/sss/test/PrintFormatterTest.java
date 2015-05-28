/*
 * PrintformatterTest Class
 * Junit Testing for the PrintFormatter class
 * Original Author: Amethyst Mayer
 */

package sss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import sss.domain.Line;
import sss.domain.Product;
import sss.domain.Sale;

public class PrintFormatterTest {
	
	private static Sale testSale;	
	private static String timestamp;
	private static String saleTypePurchase = "Purchase";
	
	private final static Product CAT =  new Product(9312547856932L, "CATY123", "Cat", new BigDecimal(5), new BigDecimal(10), 18, "Pet", true);
	private final static Product BED =  new Product(2309493056932L, "BEDY123", "Bed", new BigDecimal(4), new BigDecimal(8), 1, "Furniture", false);
	private final static Product PEN =  new Product(9493849856932L, "PENY123", "Pen", new BigDecimal(31), new BigDecimal(62), 8, "Office", true);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf
		= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		timestamp = sdf.format(dt);

		Long saleId = 160163L;
		
		testSale = new Sale(saleId, timestamp, saleTypePurchase);
		
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
	}

	@Test
	public void testFormatSale() {		
		assertEquals(160163L, testSale.getSaleId());
		assertEquals(timestamp, testSale.getSaleDate());
		assertEquals(3, testSale.getNumberOfLines());
		assertEquals(new BigDecimal(80).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleTotal());
		BigDecimal amt = new BigDecimal(100);
		testSale.setAmountTendered(amt);
		assertEquals(amt.setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleAmountTendered());
		testSale.calculateBalance();
		assertEquals(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleBalance());
		testSale.calculateGST();
		assertEquals(new BigDecimal(7.27).setScale(2, BigDecimal.ROUND_HALF_EVEN), testSale.getSaleGST());
		
		
//		fail("Not yet implemented");
	}

}
