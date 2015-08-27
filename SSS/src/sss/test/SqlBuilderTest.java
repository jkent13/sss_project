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

import sss.domain.InventoryFilter;
import sss.domain.Line;
import sss.domain.Product;
import sss.domain.Sale;
import sss.services.SqlBuilder;

public class SqlBuilderTest {

	private static Sale testSale;	
	private static String timestamp;
	private static String saleTypePurchase = "Purchase";
	
	private final Product CAT =  new Product(9312547856932L, "CATY123", "Cat", new BigDecimal(5), new BigDecimal(10), 18, "Pet", true, 1);
	private final Product BED =  new Product(2309493056932L, "BEDY123", "Bed", new BigDecimal(4), new BigDecimal(8), 1, "Furniture", false, 2);
	private final Product PEN =  new Product(9493849856932L, "PENY123", "Pen", new BigDecimal(31), new BigDecimal(62), 8, "Office", true, 5);
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetLastSaleId() {
		assertEquals("SELECT MAX(sale_id) as 'Last Sale ID' FROM sale;", SqlBuilder.getLastSaleId());
	}
	
	@Test
	public void testGetSupplierNames() {
		assertEquals("SELECT supp_name FROM supplier ORDER BY supp_id;", SqlBuilder.getSupplierNames());
	}
	
	@Test
	public void testGetCategoryNames() {
		assertEquals("SELECT DISTINCT prod_category FROM product ORDER BY prod_category;",SqlBuilder.getCategoryNames());
	}
	
	@Test
	public void testGetAllProducts() {
		assertEquals("SELECT prod_id, prod_code, prod_name, prod_cost_price, prod_price, prod_qoh, prod_category, supp_name, prod_active "
				+ "FROM product, supplier WHERE product.supp_id = supplier.supp_id ORDER BY prod_name;", SqlBuilder.getAllProducts());
	}
	
	@Test
	public void testGetProductsFiltered() {
		InventoryFilter filter = new InventoryFilter(true, true, false, false);
		filter.setQohOperator("=");
		filter.setQohValue(56);
		filter.setSupplierId(1);
		
		assertEquals("SELECT prod_id, prod_code, prod_name, prod_cost_price, prod_price, prod_qoh, prod_category, supp_name, prod_active"
				+ " FROM product, supplier WHERE product.supp_id = supplier.supp_id AND product.supp_id = 1 AND prod_qoh = 56"
				+ " ORDER BY prod_name;", SqlBuilder.getProductsFiltered(filter));
	}
	
	@Test
	public void testGetSaleReportQuery() {
		String startDate = "2014-04-04 08:00:00";
		String endDate = "2014-04-05 08:00:00";
		
		assertEquals("SELECT sale_id, sale_date, sale_total, sale_amt_tendered, sale_balance FROM sale WHERE sale_type"
				+ " = 'Purchase' AND sale_date BETWEEN '2014-04-04 08:00:00' AND '2014-04-05 08:00:00';", SqlBuilder.getSaleReportQuery(startDate, endDate));
	}
	
	@Test
	public void testGetSaleReportByHourQuery() {
		String startDate = "2013-12-25 08:00:00";
		
		assertEquals("SELECT CONCAT(HOUR(sale_date), ':00-', HOUR(sale_date)+1, ':00') AS 'Hour', "
				+ "COUNT(*) AS `Number of Sales`, SUM(sale_total) AS 'Sale Totals' FROM sale WHERE sale_date BETWEEN "
				+ "'2013-12-25 08:00:00' AND '2013-12-25 08:00:00' GROUP BY HOUR(sale_date);", SqlBuilder.getSaleReportByHourQuery(startDate));
	}
	
	@Test
	public void testGetProductById() {
		assertEquals("SELECT * FROM product WHERE prod_id = 1234567891022;", SqlBuilder.getProductById(1234567891022L));
	}

	@Test
	public void testGetSaleInsertStatement() {

		timestamp = "2015-02-25 11:30:08";

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
				
		assertEquals("INSERT INTO sale VALUES(160163, '2015-02-25 11:30:08', 72.73, 7.27, 80.00, 100.00, 20.00, 'Purchase');", SqlBuilder.getSaleInsertStatement(testSale));
	}

	@Test
	public void testGetLineInsertStatements() {

		timestamp = "2015-02-25 11:30:08";

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