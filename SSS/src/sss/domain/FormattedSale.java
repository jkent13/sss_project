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
	
	private final String[] receiptHeader = {"Simple Sale Services", 				//  Index 0
											"123 Fake St, Somewhere",				//  Index 1
											"Phone: (02) 9999 9999",				//  Index 2
											" ",									//  Index 3 (deliberately blank line)
											"TAX INVOICE - ABN 11 111 111 111"};	//  Index 4

	private String[] saleHeader;

	private String[] saleDetails;

	private String[] saleFooter;

	private final String[] receiptFooter = {	"Please keep your receipt as proof-of-purchases.", 	// Index 0
												"No refunds can be given without a receipt."};		// Index 1
	
	public FormattedSale(String[] saleHeader, String[] saleDetails, String[] saleFooter) {
		this.saleHeader = saleHeader;
		this.saleDetails = saleDetails;
		this.saleFooter = saleFooter;
	}

	public String[] getReceiptHeader() {
		return receiptHeader;
	}

	public String[] getSaleHeader() {
		return saleHeader;
	}

	public String[] getSaleDetails() {
		return saleDetails;
	}

	public String[] getSaleFooter() {
		return saleFooter;
	}

	public String[] getReceiptFooter() {
		return receiptFooter;
	}
	
	
}
