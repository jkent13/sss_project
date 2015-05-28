/* Sale Class
 * 
*  Represents a sale transaction, consists of Line objects and 
*  additional sale-related fields
*  
*  Original Author: Josh Kent
*/

package sss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sss.services.SaleListener;

public class Sale {
	
	List<SaleListener> listeners = new ArrayList<SaleListener>();		// Contains reference to all listeners concerned with changes
																		// to a Sale object (such as a UI frame)
	
	private int number_of_lines = 0;									// Initially, Sale has no lines
	
	private long sale_id; 												// PK eg. 160165
	
	private String sale_date; 											// String representing a MySQL DateTime
	private BigDecimal sale_subtotal = new BigDecimal(0.00); 			// Sale subtotal before GST (10 / 11 of sale total)
	private BigDecimal sale_gst = new BigDecimal(0.00);					// Sale GST (1 / 11 of sale total)
	private BigDecimal sale_total = new BigDecimal(0.00); 				// Sale total (sum of line item totals)
	private BigDecimal sale_amount_tendered = new BigDecimal(0.00);		// Amount tendered for sale (must be >= sale total)
	private BigDecimal sale_balance = new BigDecimal(0.00); 			// Difference between amount tendered and sale total
	private String sale_type = "Purchase"; 								// Sale type: either 'Purchase' or 'Refund'

	private ArrayList<Line> lineItems = new ArrayList<>(); 				// Collection of all lines within a Sale
	
	/**
	 * Creates a Sale from an id, a timestamp and a sale type
	 * @param sale_id a unique sale id number
	 * @param sale_date a String representing a MySQL DateTime 
	 * @param sale_type either 'Purchase' or 'Refund'
	 */
	public Sale(long sale_id, String sale_date, String sale_type) {
		this.sale_id = sale_id;
		this.sale_date = sale_date;
		this.sale_type = sale_type;
	}
	
	/**
	 * Creates a Sale from an id
	 * @param sale_id a unique sale id number
	 */
	public Sale(long sale_id) {
		this.sale_id = sale_id;
	}
	
	// ------------ Observer Pattern Methods --------------------
	
	/**
	 * Add a new listener to this Sale object
	 * @param newListener reference to the new SaleListener
	 */
	public void registerListener(SaleListener newListener) {
		listeners.add(newListener);
	}
	
	/**
	 * Remove (de-register) a listener from this Sale object
	 * @param listener a reference to the listener to be removed
	 */
	public void removeListener(SaleListener listener) {
		if(listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}
	
	/**
	 * Notify all listeners that a certain change has been made to this Sale object and they must update themselves
	 * @param eventType the event type (either SaleLister.SALE_BALANCE if the sale balance has changed or 
	 * SaleLister.SALE_TOTAL if the sale total has changed)
	 * @param newValue the new value of the sale balance or the sale total (depending on the event type)
	 */
	public void notifyListeners(int eventType, BigDecimal newValue) {
		for(SaleListener sl: listeners) {
			sl.update(eventType, newValue);
		}
	}
	
	// ----------------------------------------------------------
	
	// ---------------- Setter Methods --------------------------
	
	/**
	 * Setter method for the amount tendered (must be >= sale total)
	 * @param sale_amount_tendered the amount tendered
	 */
	public void setAmountTendered(BigDecimal sale_amount_tendered) {
		if(sale_amount_tendered.compareTo(sale_total) >= 0) {
			this.sale_amount_tendered = sale_amount_tendered.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			calculateBalance();
		}
		else {
			JOptionPane.showMessageDialog(null, "ERROR: Amount tendered not enough!", "Invalid Amount Tendered", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Setter method for the sale's timestamp (also known as sale_date)
	 * @param timestamp a String representing a MySQL DateTime
	 */
	public void setTimestamp(String timestamp) {
		this.sale_date = timestamp;
	}
	
	// ----------------------------------------------------------
	
	// ----------------- Getter Methods -------------------------
	
	/**
	 * Getter method for the number of lines in this Sale
	 * @return the number of lines in this Sale
	 */
	public int getNumberOfLines() {
		return number_of_lines;
	}

	/**
	 * Getter method for the sale id
	 * @return the sale id
	 */
	public long getSaleId() {
		return sale_id;
	}

	/**
	 * Getter method for the sale date (also known as timestamp)
	 * @return the sale date (timestamp)
	 */
	public String getSaleDate() {
		return sale_date;
	}

	/**
	 * Getter method for the sale's subtotal
	 * @return the sale subtotal
	 */
	public BigDecimal getSaleSubtotal() {
		return sale_subtotal;
	}

	/**
	 * Getter method for the sale's GST amount
	 * @return the sale GST amount
	 */
	public BigDecimal getSaleGST() {
		return sale_gst;
	}
	
	/**
	 * Getter method for the sale total
	 * @return the sale total
	 */
	public BigDecimal getSaleTotal() {
		calculateTotal();
		return sale_total;
	}

	/**
	 * Getter method for the sale amount tendered
	 * @return the sale amount tendered
	 */
	public BigDecimal getSaleAmountTendered() {
		return sale_amount_tendered;
	}

	/**
	 * Getter method for the sale balance (also known as change due)
	 * @return the sale balance (change)
	 */
	public BigDecimal getSaleBalance() {
		return sale_balance;
	}

	/**
	 * Getter method for the sale type (either 'Purchase' or 'Refund')
	 * @return the sale type
	 */
	public String getSaleType() {
		return sale_type;
	}
	
	/**
	 * Getter method for the sale's line items
	 * @return an ArrayList containing the sale's Lines
	 */
	public ArrayList<Line> getLineItems() {
		return lineItems;
	}

	// ----------------------------------------------------------
	
	// ---------- Line Item Manipulation Methods ----------------
	
	/**
	 * Adds a new Line to this sale
	 * @param lineItem the new line to be added
	 */
	public void addLineItem(Line lineItem){
		lineItems.add(lineItem);
		number_of_lines++; 	// Update number of lines
		calculateTotal(); 	// Recalculate total
		
	}
	
	/**
	 * Removes a Line from this sale
	 * @param lineItem the line to be removed
	 */
	public void removeLineItem(Line lineItem) {
		lineItems.remove(lineItem);
		number_of_lines--; 	// Update number of lines
		rebuildLineItems(); // Restructures line items (so each line number is correct)
		calculateTotal();	// Recalculate total
	}
	
	/**
	 * Helper method that rebuilds a sale's line items ArrayList when a Line is removed. This prevents
	 * line numbers from being incorrect when a sale is written to the database
	 */
	private void rebuildLineItems(){
		ArrayList<Line> rebuiltLines = new ArrayList<Line>();
		int newLineNumber = 1;
		for(int i = 0; i < lineItems.size(); i++) {
			if(lineItems.get(i) != null) {
				rebuiltLines.add(lineItems.get(i));
				rebuiltLines.get(i).setLineNumber(newLineNumber);
				newLineNumber++;
			}
		}
		lineItems = rebuiltLines;
	}
	
	// ----------------------------------------------------------
	
	// ------------- Calculator Methods -------------------------
	
	/**
	 * Calculates the sale's total based on the total of each of its Lines
	 */
	public void calculateTotal() {
		sale_total = new BigDecimal(0.00).setScale(2); // Reset to default to avoid errors
		for(Line l: lineItems) {
			sale_total = sale_total.add(l.getLineAmount().setScale(2, BigDecimal.ROUND_HALF_EVEN));
		}
		calculateGST(); 		// Update GST value
		calculateSubtotal();	// Update subtotal
		notifyListeners(SaleListener.SALE_TOTAL, sale_total); // Send new total value to each SaleListener
	}
	
	/**
	 * Helper method that calculates a sale's GST
	 */
	private void calculateGST() { 
		if(sale_total.doubleValue() > 0) {
			sale_gst = new BigDecimal(0.00).setScale(2); // Reset to default to avoid errors
			
			// Sale GST is 1/11 of sale total
			sale_gst = sale_total.divide(new BigDecimal(11), 2, BigDecimal.ROUND_HALF_EVEN); 
		}
		else {
			sale_gst = new BigDecimal(0.00);
		}
	}
	
	/**
	 * Helper method that calculates a sale's subtotal
	 */
	private void calculateSubtotal() {
		sale_subtotal = new BigDecimal(0.00).setScale(2); // Reset to default to avoid errors
		
		// Sale subtotal is the sale total - the sale GST
		sale_subtotal = sale_total.subtract(sale_gst).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	 * Calculates the sale balance (also known as change due)
	 */
	public void calculateBalance() {
		sale_balance = new BigDecimal(0.00).setScale(2); // Reset to default to avoid errors
		
		// Sale balance is the amount tendered - the sale total
		sale_balance = sale_amount_tendered.subtract(sale_total).setScale(2, BigDecimal.ROUND_HALF_EVEN); 
		notifyListeners(SaleListener.SALE_BALANCE, sale_balance);
	}
	
	// ----------------------------------------------------------
	
	// ---------------- Testing Methods -------------------------
	
	/**
	 * A testing method that checks whether the conditions for a valid sale are met, including that the sale subtotal + sale GST
	 * equal the sale total, only 'Refund' or 'Purchase' are the sale type, and the amount tendered is >= than the sale total
	 * @return true if the sale is valid, false otherwise
	 */
	public boolean isValid() {
		return (sale_subtotal.add(sale_gst).equals(sale_total) && (sale_type.equals("Purchase") || sale_type.equals("Refund")) && sale_amount_tendered.compareTo(sale_total) >= 0);
	}
	
	@Override
	public String toString() {
		return "Sale ID: " + sale_id + " Timestamp: " + sale_date + " Number of Lines: " + number_of_lines + "\nSale Subtotal: " + sale_subtotal + " Sale GST: " + sale_gst + " Sale Total: " + sale_total + "\nAmount Tendered: " + sale_amount_tendered + " Sale Balance (Change Due): " + sale_balance + "\n";
	}
	
}// End class
