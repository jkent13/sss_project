package sss.services;

public class PrintFormatter {
	
	private final static String[] receiptHeader = {"Simple Sale Services", 
												"123 Fake St, Somewhere",
												"Phone: (02) 9999 9999",
												" ",
												"TAX INVOICE - ABN 11 111 111 111"};
	
	private String[] saleHeader = {"Receipt No: 169001",
									"Date: 08/05/2015    Time: 12:06"};
	
	private String[] saleDetails = {"Door",
										"2 x $20.00",
										"$40.00",
										"Cat",
										"7 x $40.00",
										"$280.00"};
	
	
	private String[] saleFooter = { "Total for 9 items",
									"$320.00",
									"Cash Tendered",
									"$350.00",
									"Change",
									"$30.00",
									"GST INCLUDED IN TOTAL",
									"$31.82"};
	
	private final static String[] receiptFooter = {"Please keep your receipt as proof-of-purchases.",
											"No refunds can be given without a receipt."};
	
	
	public PrintFormatter () {}; 
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
