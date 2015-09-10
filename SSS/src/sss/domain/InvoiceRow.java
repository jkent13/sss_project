/* InvoiceRow Class
 * 
 * Used to represent a single row of an Invoice object
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

public class InvoiceRow implements Comparable<InvoiceRow> {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private int rowNumber;
	
	private String productCode;
	private BigDecimal costPrice;
	private BigDecimal price;
	private int quantity;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public InvoiceRow(int rowNumber, String productCode, BigDecimal costPrice, BigDecimal price, int quantity) {
		this.rowNumber = rowNumber;
		this.productCode = productCode;
		
		this.costPrice = costPrice;
		this.price = price;
		
		if(quantity >= 0) {
			this.quantity = quantity;
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: A negative quantity was found at row: " + rowNumber + ". The quantity value for this row"
					+ " will not be changed.", "Negative Quantity", JOptionPane.ERROR_MESSAGE);
			this.quantity = 0;
		}
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public int getRowNumber() {
		return rowNumber;
	}
	
	

	public String getProductCode() {
		return productCode;
	}

	
	
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	
	

	public BigDecimal getPrice() {
		return price;
	}

	
	
	public int getQuantity() {
		return quantity;
	}

	
	
	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	
	
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	// ==========================================================================
	// Other Methods
	// ==========================================================================
	
	
	
	@Override
	public int compareTo(InvoiceRow row) {
		if(rowNumber > row.getRowNumber()) {
			return 1;
		}
		else if(rowNumber < row.getRowNumber()) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	

	public boolean isDifference(InvoiceRow row) {
		if(quantity != 0) {
			return true;
		} 
		else if (!(costPrice.equals(row.getCostPrice())) && !(price.equals(row.getPrice()))) {
				return true;
			}
		else {
			return false;
		}
	}
	
	
	
	// ==========================================================================
	// Printing Methods
	// ==========================================================================
	
	
	
	@Override
	public String toString() {
		StringBuffer string = new StringBuffer();
		
		string.append("[" + rowNumber + "] " + productCode + " \t|");
		if(costPrice != null) {
			string.append(costPrice.toPlainString() + " \t|");
		}
		else {
			string.append( "NULL \t|");
		}
		if(price != null) {
			string.append(price.toPlainString() + " \t|");
		}
		else {
			string.append( "NULL \t|");
		}
		string.append(quantity);
		
		
		return string.toString();
	}
	
	
	
	public void printRow() {
		System.out.printf("%-8s", "[" + rowNumber + "]");
		System.out.print("| ");
		System.out.printf("%-12s", productCode);
		System.out.print(" |");
		if(costPrice != null) {
			System.out.print(" ");
			System.out.printf("%-12s", costPrice.toPlainString());
			System.out.print(" |");
		}
		else {
			System.out.print(" ");
			System.out.printf("%-12s", "NULL");
			System.out.print(" |");
		}
		if(price != null) {
			System.out.print(" ");
			System.out.printf("%-12s", price.toPlainString());
			System.out.print(" |");
		}
		else {
			System.out.print(" ");
			System.out.printf("%-12s", "NULL");
			System.out.print(" |");
		}
		System.out.print(" ");
		System.out.printf("%-8s", quantity);
		System.out.print(" ||");
		
	}
	
}