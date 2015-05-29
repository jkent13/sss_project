/* InventoryFilter Class
 * 
 * Represents a series of user selections that combine to create a specific
 * subset of Products that should be displayed
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.math.BigDecimal;

public class InventoryFilter {

	private boolean qohSelected = false;
	private boolean supplierSelected = false;
	private boolean categorySelected = false;
	private boolean priceRangeSelected = false;
	
	private String qohOperator;
	private int qohValue;
	
	private int supplierId;
	
	private String category;
	
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	
	public InventoryFilter() {
		
	}
	
	public InventoryFilter(boolean qohSelected, boolean supplierSelected, boolean categorySelected, boolean priceRangeSelected) {
		this.qohSelected = qohSelected;
		this.supplierSelected = supplierSelected;
		this.categorySelected = categorySelected;
		this.priceRangeSelected = priceRangeSelected;
	}
	
	public boolean isQohSelected() {
		return qohSelected;
	}

	public boolean isSupplierSelected() {
		return supplierSelected;
	}

	public boolean isCategorySelected() {
		return categorySelected;
	}

	public boolean isPriceRangeSelected() {
		return priceRangeSelected;
	}

	public String getQohOperator() {
		return qohOperator;
	}

	public int getQohValue() {
		return qohValue;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public String getCategory() {
		return category;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setSupplierSelected(boolean supplierSelected) {
		this.supplierSelected = supplierSelected;
	}

	public void setCategorySelected(boolean categorySelected) {
		this.categorySelected = categorySelected;
	}

	public void setPriceRangeSelected(boolean priceRangeSelected) {
		this.priceRangeSelected = priceRangeSelected;
	}
	
	public void setQohSelected(boolean qohSelected) {
		this.qohSelected = qohSelected;
	}
	
	public void setQohOperator(String qohOperator) {
		this.qohOperator = qohOperator;
	}

	public void setQohValue(int qohValue) {
		this.qohValue = qohValue;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
}
