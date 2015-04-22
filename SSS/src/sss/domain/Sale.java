package sss.domain;
/*
 * CHANGE MONEY DOUBLES TO BIGDECIMAL!!!!!!!!!
 */
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sale {
	private DecimalFormat moneyFormatter = new DecimalFormat("#.##");

	private int number_of_lines = 0;
	
	private long sale_id; // PK
	
	private String sale_date; // String representing a MySQL DateTime
	private double sale_subtotal; // Sale subtotal before GST (10 / 11 of sale total)
	private double sale_gst; // Sale GST (1 / 11 of sale total)
	private double sale_total; // Sale total (sum of line item totals)
	private double sale_amount_tendered; // Amount tendered for sale (must be > sale total)
	private double sale_balance; // Difference between amount tendered and sale total
	private String sale_type = "Purchase"; // Sale type: either 'Purchase' or 'Refund'

	private ArrayList<Line> lineItems = new ArrayList<>(); // Collection of all lines within a Sale
	
	public Sale(long sale_id, String sale_date, String sale_type) {
		this.sale_id = sale_id;
		this.sale_date = sale_date;
		this.sale_type = sale_type;
	}
	
	public void setAmountTendered(double sale_amount_tendered) {
		this.sale_amount_tendered = sale_amount_tendered;
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

	public double getSaleSubtotal() {
		return sale_subtotal;
	}

	public double getSaleGST() {
		return sale_gst;
	}

	public double getSaleTotal() {
		return sale_total;
	}

	public double getSaleAmountTendered() {
		return sale_amount_tendered;
	}

	public double getSaleBalance() {
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
	}
	
	public double calculateTotal() {
		for(Line l: lineItems) {
			sale_total += l.getLineAmount();
		}
		return sale_total;
	}
	
	public double calculateGST() {
		moneyFormatter.setRoundingMode(RoundingMode.HALF_UP);
		sale_gst = Double.valueOf(moneyFormatter.format(sale_total / 11));
		return sale_gst;
	}
	
	public double calculateSubtotal() {
		sale_subtotal = Double.valueOf(moneyFormatter.format(sale_total - sale_gst));
		return sale_subtotal;
	}
	
	public double calculateBalance() {
		sale_balance = sale_amount_tendered - sale_total;
		return sale_balance;
	}
	
	public boolean isValid() {
		return (sale_subtotal + sale_gst == sale_total && (sale_type.equals("Purchase") || sale_type.equals("Refund")) && sale_amount_tendered >= sale_total);
	}
	
	public double getTotal() {
		return sale_total;
	}
	
	@Override
	public String toString() {
		return "Sale ID: " + sale_id + " Timestamp: " + sale_date + " Number of Items: " + number_of_lines + "\nSale Subtotal: " + sale_subtotal + " Sale GST: " + sale_gst + " Sale Total: " + sale_total + "\nAmount Tendered: " + sale_amount_tendered + " Sale Balance (Change Due): " + sale_balance + "\n";
	}
	
}
