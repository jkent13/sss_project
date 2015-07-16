package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import sss.services.DbReader;
import sss.services.SqlBuilder;

public class Invoice {
	private int currentLineNumber = 1;
	private ArrayList<InvoiceRow> rows = new ArrayList<InvoiceRow>();
	private HashSet<String> codes = new HashSet<String>();
	
	private ResultSet products;
	private Invoice comparedInvoice;
	
	public Invoice() {
		
	}
	
	public void addRow(String productCode, BigDecimal costPrice, BigDecimal price, int quantity) {
		InvoiceRow newRow = new InvoiceRow(currentLineNumber, productCode, costPrice, price, quantity);
		rows.add(newRow);
		codes.add(productCode);
		currentLineNumber++;
	}
	
	public HashSet<String> getCodes() {
		return codes;
	}
	
	public ArrayList<InvoiceRow> getRows() {
		return rows;
	}

	public InvoiceRow getRow(int index) {
		return rows.get(index);
	}

	public void pull() {
		if(currentLineNumber > 1) {
			String query = SqlBuilder.lookupProductsFromInvoice(this);
			products = DbReader.executeQuery(query);
			comparedInvoice = new Invoice();
			try {

				while(products.next()) {
						comparedInvoice.addRow(
								products.getString("prod_code"),
								new BigDecimal(products.getDouble("prod_cost_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
								new BigDecimal(products.getDouble("prod_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
								products.getInt("prod_qoh"));
				}

				products.close(); // Close ResultSet
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.print(comparedInvoice);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer allRows = new StringBuffer();
		allRows.append("Row Product Code \tCost Price \tPrice \tQuantity\n");
		for(InvoiceRow row: rows) {
			allRows.append(row.toString() + "\n");
		}
		return allRows.toString();
		
	}
}
