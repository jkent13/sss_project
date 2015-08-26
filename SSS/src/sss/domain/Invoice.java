/* Invoice Class
 * 
 * A data structure used to manipulate CSV invoices after they have been imported
 * 
 * Original Author: Josh Kent
 */
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
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private int currentLineNumber = 1;
	private ArrayList<InvoiceRow> rows = new ArrayList<InvoiceRow>();
	private HashSet<String> codes = new HashSet<String>();
	
	private ResultSet products;
	private Invoice comparedInvoice;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	/**
	 * Standard no-args constructor
	 */
	public Invoice() {
		
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	/**
	 * Returns a collection of 'invoice row comparisons' for this Invoice object
	 * An invoice row comparison is a 'diff' between the values in the database and the values 
	 * in a particular invoice row
	 * @return a collection of 'invoice row comparisons' for this Invoice object
	 */
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
	
	
	
	/**
	 * Gets the number of rows in this invoice
	 * @return the number of rows in this Invoice object
	 */
	public int size() {
		return currentLineNumber - 1;
	}
	
	
	
	/**
	 * Gets the product codes in this invoice
	 * @return a set containing the product codes for the rows of this invoice
	 */
	public HashSet<String> getCodes() {
		return codes;
	}
	
	
	
	/**
	 * Gets the rows in this invoice
	 * @return a collection of 'InvoiceRow' objects that represent the rows of this invoice
	 */
	public ArrayList<InvoiceRow> getRows() {
		return rows;
	}

	
	
	/**
	 * Gets the row at a specific index
	 * @param index the index of the row to be returned
	 * @return the row at the index specified or null if the row requested does not exist
	 */
	public InvoiceRow getRow(int index) {
		if((!rows.isEmpty()) && (index < rows.size())) {
			return rows.get(index);
		}
		else {
			return null;
		}
	}
	
	
	
	// ==========================================================================
	// Other Methods
	// ==========================================================================
	
	
	
	/**
	 * Adds a new row to this Invoice
	 * @param productCode product code
	 * @param costPrice cost price
	 * @param price price
	 * @param quantity quantity on hand value
	 */
	public void addRow(String productCode, BigDecimal costPrice, BigDecimal price, int quantity) {
		InvoiceRow newRow = new InvoiceRow(currentLineNumber, productCode, costPrice, price, quantity);
		rows.add(newRow);
		codes.add(productCode);
		currentLineNumber++;
	}
	


	/**
	 * Pulls product data for products listed in this invoice from the database for the purposes of comparison
	 */
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
				JOptionPane.showMessageDialog(null, "Error: The was a problem reading product data for comparison", "SQL Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	
	// ==========================================================================
	// Printing Methods
	// ==========================================================================
	
	
	
	@Override
	public String toString() {
		StringBuffer allRows = new StringBuffer();
		allRows.append("Row Product Code \tCost Price \tPrice \tQuantity\n");
		for(InvoiceRow row: rows) {
			allRows.append(row.toString() + "\n");
		}
		return allRows.toString();
		
	}
	
	
	
	/**
	 * Prints out this invoice and its comparison in plain text (to console)
	 * Used for testing
	 */
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
			}// End for
		}// End else
	}// End method

}