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

public class Sale {
	
	private int number_of_lines = 0;
	
	private long sale_id; 												// PK
	
	private String sale_date; 											// String representing a MySQL DateTime
	private BigDecimal sale_subtotal = new BigDecimal("0"); 			// Sale subtotal before GST (10 / 11 of sale total)
	private BigDecimal sale_gst = new BigDecimal("0");					// Sale GST (1 / 11 of sale total)
	private BigDecimal sale_total = new BigDecimal("0"); 				// Sale total (sum of line item totals)
	private BigDecimal sale_amount_tendered = new BigDecimal("0");		// Amount tendered for sale (must be > sale total)
	private BigDecimal sale_balance = new BigDecimal("0"); 				// Difference between amount tendered and sale total
	private String sale_type = "Purchase"; 								// Sale type: either 'Purchase' or 'Refund'

	private ArrayList<Line> lineItems = new ArrayList<>(); 				// Collection of all lines within a Sale
	
	public Sale(long sale_id, String sale_date, String sale_type) {
		this.sale_id = sale_id;
		this.sale_date = sale_date;
		this.sale_type = sale_type;
	}
	
	public Sale(long sale_id) {
		this.sale_id = sale_id;
	}
	
	public void setAmountTendered(BigDecimal sale_amount_tendered) {
		this.sale_amount_tendered = sale_amount_tendered.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public void setTimestamp(String timestamp) {
		this.sale_date = timestamp;
	}
	
	public int getNumberOfLines() {
		return number_of_lines;
	}

	public long getSaleId() {
		return sale_id;
	}

	public String getSaleDate() {
		return sale_date;
	}

	public BigDecimal getSaleSubtotal() {
		return sale_subtotal;
	}

	public BigDecimal getSaleGST() {
		return sale_gst;
	}

	public BigDecimal getSaleTotal() {
		calculateTotal();
		return sale_total;
	}

	public BigDecimal getSaleAmountTendered() {
		return sale_amount_tendered;
	}

	public BigDecimal getSaleBalance() {
		return sale_balance;
	}

	public String getSaleType() {
		return sale_type;
	}
	
	public ArrayList<Line> getLineItems() {
		return lineItems;
	}

	public void addLineItem(Line lineItem){
		lineItems.add(lineItem);
		number_of_lines++;
	}
	
	public void removeLineItem(Line lineItem) {
		lineItems.remove(lineItem);
		number_of_lines--;
		rebuildLineItems();
	}
	
	public void rebuildLineItems(){
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
	
	public BigDecimal calculateTotal() {
		sale_total = new BigDecimal(0);
		for(Line l: lineItems) {
			sale_total = sale_total.add(l.getLineAmount().setScale(2, BigDecimal.ROUND_HALF_EVEN));
		}
		return sale_total;
	}
	
	public BigDecimal calculateGST() { 
		sale_gst = new BigDecimal(0);
		sale_gst = sale_total.divide(new BigDecimal(11), 2, BigDecimal.ROUND_HALF_EVEN);
		return sale_gst;
	}
	
	public BigDecimal calculateSubtotal() {
		sale_subtotal = new BigDecimal(0);
		sale_subtotal = sale_total.subtract(sale_gst).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return sale_subtotal;
	}
	
	public BigDecimal calculateBalance() {
		sale_balance = new BigDecimal(0);
		sale_balance = sale_amount_tendered.subtract(sale_total).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return sale_balance;
	}
	
	public boolean isValid() {
		return (sale_subtotal.add(sale_gst).equals(sale_total) && (sale_type.equals("Purchase") || sale_type.equals("Refund")) && sale_amount_tendered.compareTo(sale_total) >= 0);
	}
	
	@Override
	public String toString() {
		return "Sale ID: " + sale_id + " Timestamp: " + sale_date + " Number of Lines: " + number_of_lines + "\nSale Subtotal: " + sale_subtotal + " Sale GST: " + sale_gst + " Sale Total: " + sale_total + "\nAmount Tendered: " + sale_amount_tendered + " Sale Balance (Change Due): " + sale_balance + "\n";
	}
	
}
