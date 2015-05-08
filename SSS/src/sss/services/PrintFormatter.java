package sss.services;

import java.text.DecimalFormat;

import sss.domain.FormattedSale;
import sss.domain.Line;
import sss.domain.Sale;

public class PrintFormatter {
	
	
//	private String[] saleHeader = {"Receipt No: 169001",
//									"Date: 08/05/2015    Time: 12:06"};
//	
//	private String[] saleDetails = {"Door",
//										"2 x $20.00",
//										"$40.00",
//										"Cat",
//										"7 x $40.00",
//										"$280.00"};
//	
//	
//	private String[] saleFooter = { "Total for 9 items",
//									"$320.00",
//									"Cash Tendered",
//									"$350.00",
//									"Change",
//									"$30.00",
//									"GST INCLUDED IN TOTAL",
//									"$31.82"};

	private static DecimalFormat moneyFormatter = new DecimalFormat("#.##");
	
	public PrintFormatter () {};
	
	public static FormattedSale formatSale(Sale sale) {
		
		moneyFormatter.setMinimumFractionDigits(2);
		
		String[] saleHeader = new String[2];
		String[] saleDetails = new String[sale.getNumberOfLines() * 3];
		String[] saleFooter = new String[8];
		
		int numberOfItems = 0;
		for(Line l: sale.getLineItems()) {
			numberOfItems += l.getLineUnits();
		}
		
		saleHeader[0] = "Receipt No: " + sale.getSaleId();
		saleHeader[1] = "Timestamp: " + sale.getSaleDate();
		
		int j = 0;
		for(int i = 0; i < sale.getNumberOfLines(); i++, j++) {
			saleDetails[j] = sale.getLineItems().get(i).getProductName();
			j++;
			saleDetails[j] = sale.getLineItems().get(i).getLineUnits() + " x $" + moneyFormatter.format(sale.getLineItems().get(i).getLinePrice());
			j++;
			saleDetails[j] = "$" + moneyFormatter.format(sale.getLineItems().get(i).getLineAmount());
		}
		
		saleFooter[0] = "Total for " + numberOfItems + " items";
		saleFooter[1] = "$" + moneyFormatter.format(sale.getSaleTotal());
		saleFooter[2] = "Cash Tendered";
		saleFooter[3] = "$" + moneyFormatter.format(sale.getSaleAmountTendered());
		saleFooter[4] = "Change";
		saleFooter[5] = "$" + moneyFormatter.format(sale.getSaleBalance());
		saleFooter[6] = "GST included in total";
		saleFooter[7] = "$" + moneyFormatter.format(sale.getSaleGST());
		
		FormattedSale fs = new FormattedSale(saleHeader, saleDetails, saleFooter);
		return fs;
	}
}
