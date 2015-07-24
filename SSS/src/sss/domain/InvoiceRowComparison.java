package sss.domain;

import java.math.BigDecimal;

public class InvoiceRowComparison {

	private int rowNumber;
	
	private String productCode;
	private String costPriceChange;
	private String priceChange;
	private String quantityChange;
	
	private BigDecimal newCostPrice;
	private BigDecimal newPrice;
	private int newQuantity;
	
	public InvoiceRowComparison(InvoiceRow row1, InvoiceRow row2) {
		if(!row1.getProductCode().equals(row2.getProductCode())) {
			return;
		}
		else {
			rowNumber = row1.getRowNumber();
			productCode = row1.getProductCode();
			
			if (row1.getCostPrice() == null || row1.getCostPrice().equals(row2.getCostPrice())) {
				costPriceChange = "*No Change*";
				newCostPrice = row2.getCostPrice();
			}
			else {
				costPriceChange = row2.getCostPrice().toPlainString() + " --> " + row1.getCostPrice().toPlainString();
				newCostPrice = row1.getCostPrice();
			}
			
			if (row1.getPrice() == null || row1.getPrice().equals(row2.getPrice())) {
				priceChange = "*No Change*";
				newPrice = row2.getPrice();
			}
			else {
				priceChange = row2.getPrice().toPlainString() + " --> " + row1.getPrice().toPlainString();
				newPrice = row1.getPrice();
			}
			
			if (row1.getQuantity() == 0) {
				quantityChange = "*No Change*";
				newQuantity = row2.getQuantity();
			}
			else {
				quantityChange = row2.getQuantity() + " --> " + (row1.getQuantity() + row2.getQuantity());
				newQuantity = row1.getQuantity() + row2.getQuantity();
			}
		}
	}

	public BigDecimal getNewCostPrice() {
		return newCostPrice;
	}

	public BigDecimal getNewPrice() {
		return newPrice;
	}

	public int getNewQuantity() {
		return newQuantity;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getCostPriceChange() {
		return costPriceChange;
	}

	public String getPriceChange() {
		return priceChange;
	}

	public String getQuantityChange() {
		return quantityChange;
	}
	
	public void printDetails() {
		System.out.printf("%-8s", "[" + rowNumber + "]");
		System.out.print("| ");
		System.out.printf("%-10s", productCode);
		System.out.print(" |");
		System.out.printf("%-20s", costPriceChange);
		System.out.print(" |");
		System.out.printf("%-20s", priceChange);
		System.out.print(" |");
		System.out.printf("%-12s", quantityChange);
		System.out.print(" |\n");
	}
	
	public static void printHeader() {
		System.out.printf("%-8s", "Row #");
		System.out.print("| ");
		System.out.printf("%-10s", "Code");
		System.out.print(" |");
		System.out.printf("%-20s", "Cost Price");
		System.out.print(" |");
		System.out.printf("%-20s", "Price");
		System.out.print(" |");
		System.out.printf("%-12s", "Quantity");
		System.out.print(" |\n");
		
		System.out.println("--------------------------------------------------------------------------------");
	}
}
