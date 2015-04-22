package test;

import java.sql.SQLException;

import sss.domain.Line;
import sss.services.DbConnector;
import sss.services.SqlBuilder;

public class TestMain {

	public static void main(String[] args) throws SQLException {
		
		long barcode = 9300026131160L;
		Line testLine = new Line(1L, barcode, 1);
		Line testLine2 = new Line(1L, 9300062687246L, 2);
		
		testLine.setDiscount(50.0);
		testLine2.setQuantity(4);
		
		System.out.println(testLine);
		System.out.println(testLine2);
		
		String idQuery = SqlBuilder.getLookupQueryById(barcode);
		String nameQuery = SqlBuilder.getLookupQueryByName("cat");
		
		System.out.println(idQuery);
		System.out.println(nameQuery);
		
		DbConnector.closeConnection();

	}

}
