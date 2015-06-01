/*
 * SqlBuilderTest Class
 * Junit Testing for the SqlBuilder class
 * Original Author: Jasmina Pasalic
 */

package sss.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sss.services.SqlBuilder;

public class SqlBuilderTest {

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
		fail("Not yet implemented");
	}

	@Test
	public void testGetLineInsertStatements() {
		fail("Not yet implemented");
	}

}
