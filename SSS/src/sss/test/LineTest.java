package sss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import sss.domain.Line;
import sss.domain.Product;

public class LineTest {
	private final Product testProduct = new Product(1111111111111L,"GGGG444", "Cat" , new BigDecimal(3), new BigDecimal(5), 6, "Pet", true);
	private Line testLine = null;

	@Before
	public void setUp() throws Exception {
		testLine = new Line(666666L, testProduct, 1);
	}

	@Test
	public void testLineLongLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testLineLongProductInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDiscount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaleId() {
		assertEquals(666666L, testLine.getSaleId());
	}

	@Test
	public void testGetProduct() {
		assertEquals(testProduct, testLine.getProduct());
	}

	@Test
	public void testGetLineAmount() {
		assertEquals(new BigDecimal(5).multiply(new BigDecimal(1)).multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_EVEN), testLine.getLineAmount());
	}

	@Test
	public void testGetLineCostAmount() {
		assertEquals(new BigDecimal(3).multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_EVEN), testLine.getLineCostAmount());
	}

	@Test
	public void testGetLineNumber() {
		assertEquals(1, testLine.getLineNumber());
	}

	@Test
	public void testGetProductId() {
		assertEquals(1111111111111L, testLine.getProductId());
	}

	@Test
	public void testGetProductName() {
		assertEquals("Cat", testLine.getProductName());
	}

	@Test
	public void testGetLineUnits() {
		assertEquals(1, testLine.getLineUnits());
	}

	@Test
	public void testGetLinePrice() {
		assertEquals(new BigDecimal (5).setScale(2, BigDecimal.ROUND_HALF_EVEN), testLine.getLinePrice());
	}

	@Test
	public void testGetLineCostPrice() {
		assertEquals(new BigDecimal (3).setScale(2, BigDecimal.ROUND_HALF_EVEN), testLine.getLineCostPrice());
	}

	@Test
	public void testGetDiscount() {
		assertEquals(new BigDecimal (0).setScale(2, BigDecimal.ROUND_HALF_EVEN), testLine.getDiscount());
	}

	@Test
	public void testSetQuantity() {
		testLine.setQuantity(9);
		assertEquals(9, testLine.getLineUnits());
	}

	@Test
	public void testSetLineNumber() {
		testLine.setLineNumber(7);
		assertEquals(7, testLine.getLineNumber());
	
	}

}
