/*
 * ProductTest Class
 * Junit Testing for the Product class
 * Original Author: Jasmina Pasalic
 */

package sss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import sss.domain.Product;



public class ProductTest {
	private final Product testProduct = new Product(1111111111111L,"GGGG444", "Cat" , new BigDecimal(3), new BigDecimal(5), 6, "Pet", true, 1);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProductLong() {
		Product idConstructor = new Product(9300518897101L);
		assertEquals(9300518897101L, idConstructor.getId());
		assertEquals("GHDH302", idConstructor.getCode()); 
		assertEquals("milk", idConstructor.getName()); 
		assertEquals(new BigDecimal(17.50).setScale(2, BigDecimal.ROUND_HALF_EVEN), idConstructor.getCostPrice()); 
		assertEquals(new BigDecimal(35.00).setScale(2, BigDecimal.ROUND_HALF_EVEN), idConstructor.getPrice()); 
		assertEquals(5, idConstructor.getQuantityOnHand()); 
		assertEquals("Food", idConstructor.getCategory()); 
		assertEquals(true, idConstructor.isActive());	
	}

	@Test
	public void testProductLongStringStringBigDecimalBigDecimalIntStringBoolean() {
		final Product testProduct2 = new Product(2222222222222L,"RRRR555", "Foundation" , new BigDecimal(2), new BigDecimal(4), 5, "Cosmetics", false, 2);
		assertEquals(2222222222222L, testProduct2.getId());
		assertEquals("RRRR555", testProduct2.getCode()); 
		assertEquals("Foundation", testProduct2.getName()); 
		assertEquals(new BigDecimal(2), testProduct2.getCostPrice()); 
		assertEquals(new BigDecimal(4), testProduct2.getPrice()); 
		assertEquals(5, testProduct2.getQuantityOnHand()); 
		assertEquals("Cosmetics", testProduct2.getCategory()); 
		assertEquals(false, testProduct2.isActive());	
		
	}

	@Test
	public void testGetId() {
		assertEquals(1111111111111L, testProduct.getId());
	}

	@Test
	public void testGetName() {
		assertEquals("Cat", testProduct.getName());
	}

	@Test
	public void testGetCode() {
		assertEquals("GGGG444", testProduct.getCode());
	}

	@Test
	public void testGetCostPrice() {
		assertEquals(new BigDecimal(3), testProduct.getCostPrice());
	}

	@Test
	public void testGetPrice() {
		assertEquals(new BigDecimal(5), testProduct.getPrice());
	}

	@Test
	public void testGetQuantity() {
		assertEquals(6, testProduct.getQuantityOnHand());
	}

	@Test
	public void testGetCategory() {
		assertEquals("Pet", testProduct.getCategory());
	}

	@Test
	public void testIsActive() {
		assertEquals(true, testProduct.isActive());
	}

	@Test
	public void testGetSupplierId() {
		assertEquals(1, testProduct.getSupplierId());
	}

}
