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
		assertEquals("SELECT * FROM product WHERE prod_id = 1234567891022;", SqlBuilder.getLookupQueryById(1234567891022L));
	}

	@Test
	public void testGetLookupQueryByCode() {
		assertEquals("SELECT * FROM product WHERE prod_code = 'TTTT666';", SqlBuilder.getLookupQueryByCode("TTTT666"));
	}

	@Test
	public void testGetLookupQueryByNameAndCategory() {
		assertEquals("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%CAT%' AND prod_category = 'Pet';", SqlBuilder.getLookupQueryByNameAndCategory("Cat", "Pet"));
	}

	@Test
	public void testGetLookupQueryByName() {
		assertEquals("SELECT * FROM product WHERE UPPER(prod_name) LIKE '%CAT%';", SqlBuilder.getLookupQueryByName("Cat"));
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
