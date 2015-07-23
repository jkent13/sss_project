package sss.domain;

public class InvoiceRowComparison {

	private int rowNumber;
	
	private String productCode;
	private String costPriceChange;
	private String priceChange;
	private String quantityChange;
	
	public InvoiceRowComparison(InvoiceRow row1, InvoiceRow row2) {
		if(!row1.getProductCode().equals(row2.getProductCode())) {
			return;
		}
		else {
			rowNumber = row1.getRowNumber();
			productCode = row1.getProductCode();
			
			if (row1.getCostPrice() == null || row1.getCostPrice().equals(row2.getCostPrice())) {
				costPriceChange = "*No Change*";
			}
			else {
				costPriceChange = row1.getCostPrice().toPlainString() + " --> " + row2.getCostPrice().toPlainString();
			}
			
			if (row1.getPrice() == null || row1.getPrice().equals(row2.getPrice())) {
				priceChange = "*No Change*";
			}
			else {
				priceChange = row1.getPrice().toPlainString() + " --> " + row2.getPrice().toPlainString();
			}
			
			if (row1.getQuantity() == 0) {
				quantityChange = "*No Change*"; 
			}
			else {
				quantityChange = row2.getQuantity() + " --> " + (row1.getQuantity() + row2.getQuantity());
			}
		}
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
	
	public void print() {
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
}
