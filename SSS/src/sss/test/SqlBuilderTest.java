/*
 * SqlBuilderTest Class
 * Junit Testing for the SqlBuilder class
 * Original Author: Jasmina Pasalic
 */

package sss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import sss.domain.Line;
import sss.domain.Product;
import sss.domain.Sale;
import sss.services.SqlBuilder;

public class SqlBuilderTest {

	private static Sale testSale;	
	private static String timestamp;
	private static String saleTypePurchase = "Purchase";
	private static String saleTypeRefund = "Refund";
	
	private final int NUMBER_OF_LINES_DEFAULT = 0;
	private final BigDecimal SALE_SUBTOTAL_DEFAULT = new BigDecimal("0");
	private final BigDecimal SALE_GST_DEFAULT = new BigDecimal("0");
	private final BigDecimal SALE_TOTAL = new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_DOWN);
	private final BigDecimal SALE_AMOUNT_TENDERED = new BigDecimal("0");
	private final BigDecimal SALE_BALANCE = new BigDecimal("0");
	
	private final Product CAT =  new Product(9312547856932L, "CATY123", "Cat", new BigDecimal(5), new BigDecimal(10), 18, "Pet", true, 1);
	private final Product BED =  new Product(2309493056932L, "BEDY123", "Bed", new BigDecimal(4), new BigDecimal(8), 1, "Furniture", false, 2);
	private final Product PEN =  new Product(9493849856932L, "PENY123", "Pen", new BigDecimal(31), new BigDecimal(62), 8, "Office", true, 5);
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetLookupQueryById() {
		assertEquals("SELECT * FROM product WHERE prod_id = 1234567891022;", SqlBuilder.getProductById(1234567891022L));
	}

	@Test
	public void testGetLookupQueryByCode() {
		assertEquals("SELECT * FROM product WHERE prod_code = 'TTTT666';", SqlBuilder.getProductsByCode("TTTT666"));
	}

	@Test
	public void testGetLookupQueryByNameAndCategory() {
		assertEquals("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%CAT%' AND prod_category = 'Pet';", SqlBuilder.getProductsByNameAndCategory("Cat", "Pet"));
	}

	@Test
	public void testGetLookupQueryByName() {
		assertEquals("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%CAT%';", SqlBuilder.getProductsByName("Cat"));
	}

	@Test
	public void testGetSaleInsertStatement() {
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
		
		testSale.calculateTotal();
		testSale.setAmountTendered(new BigDecimal(100));
		testSale.calculateBalance();
				
		assertEquals("INSERT INTO sale VALUES(160163, '" + timestamp + "', 72.73, 7.27, 80.00, 100.00, 20.00, 'Purchase');", SqlBuilder.getSaleInsertStatement(testSale));
	}

	@Test
	public void testGetLineInsertStatements() {
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
		
		testSale.calculateTotal();
		testSale.setAmountTendered(new BigDecimal(100));
		testSale.calculateBalance();
		
		String[] lineStatements = SqlBuilder.getLineInsertStatements(testSale);
		
		assertEquals("INSERT INTO line VALUES(160163, 1, 9312547856932, 1, 5.00, 10.00, 10.00, 0.00);", lineStatements[0]);
		assertEquals("INSERT INTO line VALUES(160163, 2, 2309493056932, 1, 4.00, 8.00, 8.00, 0.00);", lineStatements[1]);
		assertEquals("INSERT INTO line VALUES(160163, 3, 9493849856932, 1, 31.00, 62.00, 62.00, 0.00);", lineStatements[2]);
	}

}
