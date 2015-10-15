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

import sss.domain.FormattedSale;
import sss.domain.Line;
import sss.domain.Product;
import sss.domain.Sale;
import sss.services.PrintFormatter;

public class PrintFormatterTest {
	
	private static Sale testSale;	
	private static String timestamp;
	private static String saleTypePurchase = "Purchase";
	
	private final static Product CAT =  new Product(9312547856932L, "CATY123", "Cat", new BigDecimal(5), new BigDecimal(10), 18, "Pet", true, 2);
	private final static Product BED =  new Product(2309493056932L, "BEDY123", "Bed", new BigDecimal(4), new BigDecimal(8), 1, "Furniture", false, 5);
	private final static Product PEN =  new Product(9493849856932L, "PENY123", "Pen", new BigDecimal(31), new BigDecimal(62), 8, "Office", true, 4);
	
	private static FormattedSale fs;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		timestamp = "2015-03-31 13:56:12";

		Long saleId = 160163L;
		
		testSale = new Sale(saleId, timestamp, saleTypePurchase);
		
		Line line1 = new Line(testSale.getSaleId(), CAT, 1);
		Line line2 = new Line(testSale.getSaleId(), BED, 2);
		Line line3 = new Line(testSale.getSaleId(), PEN, 3);
		
		line3.setDiscount(50);
		line1.setQuantity(4);
		
		testSale.addLineItem(line1);
		testSale.addLineItem(line2);
		testSale.addLineItem(line3);
		
		testSale.calculateTotal();
		testSale.setAmountTendered(new BigDecimal(80));

		testSale.calculateBalance();
		
		fs = PrintFormatter.formatSale(testSale);
	}

	@Test
	public void testFormatSale() {	
		String[] receiptHeader = fs.getReceiptHeader();
		String[] saleHeader = fs.getSaleHeader();
		String[] saleDetails = fs.getSaleDetails();
		String[] saleFooter = fs.getSaleFooter();
		String[] receiptFooter = fs.getReceiptFooter();
		
		assertEquals(5, receiptHeader.length);
		assertEquals(2, saleHeader.length);
		assertEquals(12, saleDetails.length);
		assertEquals(8, saleFooter.length);
		assertEquals(2, receiptFooter.length);
		
		assertEquals("Simple Sale Services", receiptHeader[0]);
		assertEquals("123 Fake St, Somewhere", receiptHeader[1]);
		assertEquals("Phone: (02) 9999 9999", receiptHeader[2]);
		assertEquals(" ", receiptHeader[3]);
		assertEquals("TAX INVOICE - ABN 11 111 111 111", receiptHeader[4]);
		
		assertEquals("Receipt No: 160163", saleHeader[0]);
		assertEquals("Timestamp: 2015-03-31 13:56:12", saleHeader[1]);
		
		assertEquals("Cat", saleDetails[0]);
		assertEquals("4 x $10.00", saleDetails[1]);
		assertEquals("$40.00", saleDetails[2]);
		assertEquals(" ", saleDetails[3]);

		assertEquals("Bed", saleDetails[4]);
		assertEquals("1 x $8.00", saleDetails[5]);
		assertEquals("$8.00", saleDetails[6]);
		assertEquals(" ", saleDetails[7]);
		
		assertEquals("Pen", saleDetails[8]);
		assertEquals("1 x $62.00", saleDetails[9]);
		assertEquals("$31.00", saleDetails[10]);
		assertEquals("-50.00%", saleDetails[11]);
		
		assertEquals("Total for 6 items", saleFooter[0]);
		assertEquals("$79.00", saleFooter[1]);
		assertEquals("Cash Tendered", saleFooter[2]);
		assertEquals("$80.00", saleFooter[3]);
		assertEquals("Change", saleFooter[4]);
		assertEquals("$1.00", saleFooter[5]);
		assertEquals("GST included in total", saleFooter[6]);
		assertEquals("$7.18", saleFooter[7]);
		
		assertEquals("Please keep your receipt as proof-of-purchases.", receiptFooter[0]);
		assertEquals("No refunds can be given without a receipt.", receiptFooter[1]);
	}

}