/* FormattedSale Class
 * 
 * A representation of a Sale object as a series of String values that can easily be printed
 * using the ReceiptPrinter class
 * Also contains receipt header and footer information as constants
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

public class FormattedSale {
	
	// The top of the receipt - Contains information about the store that is not dynamic 
	private final String[] receiptHeader = {"Simple Sale Services", 				//  Index 0
											"123 Fake St, Somewhere",				//  Index 1
											"Phone: (02) 9999 9999",				//  Index 2
											" ",									//  Index 3 (deliberately blank line)
											"TAX INVOICE - ABN 11 111 111 111"};	//  Index 4
	
	// This holds the header information specific to a Sale, which is the
	// Receipt No. and the Timestamp
	private String[] saleHeader;

	// This holds the actual line items of the Sale including the product name, the line
	// units, the product cost price and the line  amount
	private String[] saleDetails;

	// This holds the Total information, the Cash Tendered information, the Change information 
	// and the GST included in the total
	private String[] saleFooter;

	// This is the footer of the receipt - Contains generic information about refunds that is not dynamic
	private final String[] receiptFooter = {	"Please keep your receipt as proof-of-purchases.", 	// Index 0
												"No refunds can be given without a receipt."};		// Index 1
	
	/**
	 * Constructor
	 * @param saleHeader the array containing the receipt number and timestamp
	 * @param saleDetails the array containing the line item details
	 * @param saleFooter the array containing the totals, cash tendered, change and GST details
	 */
	public FormattedSale(String[] saleHeader, String[] saleDetails, String[] saleFooter) {
		this.saleHeader = saleHeader;
		this.saleDetails = saleDetails;
		this.saleFooter = saleFooter;
	}

	/**
	 * Getter method for the receiptHeader array
	 * @return the receipt header information
	 */
	public String[] getReceiptHeader() {
		return receiptHeader;
	}

	/**
	 * Getter method for the saleHeader array
	 * @return the sale header information
	 */
	public String[] getSaleHeader() {
		return saleHeader;
	}

	/**
	 * Getter method for the saleDetails array
	 * @return the sale details information (line items)
	 */
	public String[] getSaleDetails() {
		return saleDetails;
	}

	/**
	 * Getter method for the saleFooter array
	 * @return the sale footer information (totals)
	 */
	public String[] getSaleFooter() {
		return saleFooter;
	}

	/**
	 * Getter method for the receiptFooter array
	 * @return the receipt footer information
	 */
	public String[] getReceiptFooter() {
		return receiptFooter;
	}
	
} // End class
