/* PrintFormatter Class
 * 
 * Responsible for take a Sale object and returning a FormattedSale object
 * A FormattedSale object can be passed to the ReceiptPrinter and easily printed
 * 
 * Original Author: Josh Kent
 */
package sss.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import sss.domain.FormattedSale;
import sss.domain.Line;
import sss.domain.Sale;

public class PrintFormatter {

	private static DecimalFormat moneyFormatter = new DecimalFormat("#.##");	// Formats numbers to two decimal places
	
	private PrintFormatter () {};
	
	/**
	 * Static method for transforming a Sale object into a FormattedSale object which can be 
	 * printed
	 * @param sale the Sale object to be transformed
	 * @return a FormattedSale version of the input Sale
	 */
	public static FormattedSale formatSale(Sale sale) {
		
		moneyFormatter.setMinimumFractionDigits(2);	// Means that even numbers with no decimal places will still display '.00'
		
		String[] saleHeader = new String[2]; // Fixed size
		String[] saleDetails = new String[sale.getNumberOfLines() * 4]; // Each line item needs four elements in this array (variable size)
		String[] saleFooter = new String[8]; // Fixed size
		
		// Count the number of items (for display in sale footer)
		int numberOfItems = 0;
		for(Line l: sale.getLineItems()) {
			numberOfItems += l.getLineUnits();
		}
		
		// Create sale header
		saleHeader[0] = "Receipt No: " + sale.getSaleId();
		saleHeader[1] = "Timestamp: " + sale.getSaleDate();
		
		/* saleHeader example:
		 * 
		 * saleHeader[0] = Receipt No: 165062
		 * saleHeader[1] = Timestamp: 2015-05-28 10:57:49
		 */
		
		// Loop through every line item and build the sale details array
		int j = 0;
		for(int i = 0; i < sale.getNumberOfLines(); i++, j++) {
			saleDetails[j] = sale.getLineItems().get(i).getProductName();
			j++;
			saleDetails[j] = sale.getLineItems().get(i).getLineUnits() + " x $" + moneyFormatter.format(sale.getLineItems().get(i).getLinePrice());
			j++;
			saleDetails[j] = "$" + moneyFormatter.format(sale.getLineItems().get(i).getLineAmount());
			j++;
			
			String discount = sale.getLineItems().get(i).getDiscount().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
			if (discount.equals("0.00")) // Don't add discount for printing if = 0.00
				saleDetails[j] = " ";
			else
				saleDetails[j] = discount + "%";
		}
		
		/* saleDetails example:
		 * 
		 * saleDetails[0] = blue pen			<< Product name
		 * saleDetails[1] = 2 x $12.00			<< Line units x Line price
		 * saleDetails[2] = $12.00				<< Line amount
		 * saleDetails[3] = -50.00%				<< Discount (if applicable). No discount = " "
		 * 
		 * ^ These four elements represent ONE line item from a Sale object. Every line item needs four spaces in the array
		 */
		
		saleFooter[0] = "Total for " + numberOfItems + " items";
		saleFooter[1] = "$" + moneyFormatter.format(sale.getSaleTotal());
		saleFooter[2] = "Cash Tendered";
		saleFooter[3] = "$" + moneyFormatter.format(sale.getSaleAmountTendered());
		saleFooter[4] = "Change";
		saleFooter[5] = "$" + moneyFormatter.format(sale.getSaleBalance());
		saleFooter[6] = "GST included in total";
		saleFooter[7] = "$" + moneyFormatter.format(sale.getSaleGST());
		
		/* saleFooter example:
		 * 
		 * saleFooter[0] = Total for 8 items
		 * saleFooter[1] = $64.00
		 * saleFooter[2] = Cash Tendered
		 * saleFooter[3] = $65.00
		 * saleFooter[4] = Change
		 * saleFooter[5] = $1.00
		 * saleFooter[6] = GST included in total
		 * saleFooter[7] = $5.82
		 */
		
		FormattedSale fs = new FormattedSale(saleHeader, saleDetails, saleFooter);
		return fs;
	}
}
