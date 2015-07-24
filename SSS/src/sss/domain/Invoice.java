package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

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
	
	public int size() {
		return currentLineNumber - 1;
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
	
	public void printComparison() {
		if(comparedInvoice == null) {
			pull();
		}
		if(comparedInvoice.size() != size()) {
			JOptionPane.showMessageDialog(null, "Error: The imported invoice contains a product not in the database", "Product Not Found", JOptionPane.ERROR_MESSAGE);
		}
		else {

			ArrayList<InvoiceRow> comparedRows = comparedInvoice.getRows();

			System.out.printf("%-9s", "Row #");
			System.out.printf("%-15s", " Code");
			System.out.printf("%-15s", " CP ($)");
			System.out.printf("%-15s", " P ($)");
			System.out.printf("%-10s", " Quant.");
			System.out.print("\t");
			System.out.printf("%-9s", "Row #");
			System.out.printf("%-15s", " Code");
			System.out.printf("%-15s", " CP ($)");
			System.out.printf("%-15s", " P ($)");
			System.out.printf("%-10s", " Quant.");
			System.out.print("\n");
			
			for(int i = 0; i < rows.size(); i++) {
				rows.get(i).printRow();
				System.out.print("\t");
				comparedRows.get(i).printRow();
				System.out.print("\n");
			}
		}
	}

	public ArrayList<InvoiceRowComparison> getComparisonSet() {
		if(comparedInvoice == null) {
			pull();
		}
		if(comparedInvoice.size() != size()) {
			JOptionPane.showMessageDialog(null, "Error: The imported invoice contains a product not in the database", "Product Not Found", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else {
			ArrayList<InvoiceRow> comparedRows = comparedInvoice.getRows();
			ArrayList<InvoiceRowComparison> comparisonSet = new ArrayList<InvoiceRowComparison>();
			InvoiceRowComparison currentCompare;
			
			for(int i = 0; i < rows.size(); i++) {
				currentCompare = new InvoiceRowComparison(rows.get(i), comparedRows.get(i));
				if(currentCompare != null) {
					comparisonSet.add(currentCompare);
					currentCompare = null;
				}
			}
			
			return comparisonSet;
		}
	}
}
