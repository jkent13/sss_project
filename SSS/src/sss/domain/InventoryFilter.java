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

	private boolean qohSelected = false;			// QOH Filter
	private boolean supplierSelected = false;		// Supplier Filter
	private boolean categorySelected = false;		// Category Filter
	private boolean priceRangeSelected = false;		// Price Range Filter
	
	private String qohOperator;						// The quantity on hand operator (either =, > or <)
	private int qohValue;							// The quantity on hand value (e.g. 10)
	
	private int supplierId;							// The supplier id (e.g. 1)
	
	private String category;						// The category name (e.g. Office)
	
	private BigDecimal minPrice;					// The minimum price for a price range filter (e.g. 2.00)
	private BigDecimal maxPrice;					// The maximum price for a price range filter (e.g. 100.00)
	
	/**
	 * Standard no-args constructor
	 */
	public InventoryFilter() {
		
	}
	
	/**
	 * Constructs a filter with predefined values for four main filter categories
	 * @param qohSelected sets whether QOH filter is active
	 * @param supplierSelected sets whether supplier filter is active
	 * @param categorySelected sets whether category filter is active
	 * @param priceRangeSelected sets whether price range filter is active
	 */
	public InventoryFilter(boolean qohSelected, boolean supplierSelected, boolean categorySelected, boolean priceRangeSelected) {
		this.qohSelected = qohSelected;
		this.supplierSelected = supplierSelected;
		this.categorySelected = categorySelected;
		this.priceRangeSelected = priceRangeSelected;
	}
	
	// ---------- Getter Methods ----------------------------
	/**
	 * Getter method for whether the QOH filter is active
	 * @return true if the QOH filter is active, false otherwise
	 */
	public boolean isQohSelected() {
		return qohSelected;
	}

	/**
	 * Getter method for whether the supplier filter is active
	 * @return true if the supplier filter is active, false otherwise
	 */
	public boolean isSupplierSelected() {
		return supplierSelected;
	}
	
	/**
	 * Getter method for whether the category filter is active
	 * @return true if the category filter is active, false otherwise
	 */
	public boolean isCategorySelected() {
		return categorySelected;
	}

	/**
	 * Getter method for whether the price range filter is active
	 * @return true if the price range filter is active, false otherwise
	 */
	public boolean isPriceRangeSelected() {
		return priceRangeSelected;
	}

	/**
	 * Getter method for the QOH operator
	 * @return either '=', '>' or '<'
	 */
	public String getQohOperator() {
		return qohOperator;
	}

	/**
	 * Getter method for the QOH value
	 * @return the QOH value 
	 */
	public int getQohValue() {
		return qohValue;
	}

	/**
	 * Getter method for the supplier id
	 * @return the supplier id
	 */
	public int getSupplierId() {
		return supplierId;
	}

	/**
	 * Getter method for the category
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Getter method for the min price value
	 * @return the min price value
	 */
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	/**
	 * Getter method for the max price value
	 * @return the max price value
	 */
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	
	// ------------------------------------------------------
	
	// ---------- Setter Methods ----------------------------
	/**
	 * Setter method for the supplier filter
	 * @param supplierSelected true/false whether the filter is active
	 */
	public void setSupplierSelected(boolean supplierSelected) {
		this.supplierSelected = supplierSelected;
	}

	/**
	 * Setter method for the category filter
	 * @param categorySelected true/false whether the filter is active
	 */
	public void setCategorySelected(boolean categorySelected) {
		this.categorySelected = categorySelected;
	}

	/**
	 * Setter method for the price range filter
	 * @param priceRangeSelected true/false whether the filter is active
	 */
	public void setPriceRangeSelected(boolean priceRangeSelected) {
		this.priceRangeSelected = priceRangeSelected;
	}
	
	/**
	 * Setter method for the QOH filter
	 * @param qohSelected true/false whether the filter is active
	 */
	public void setQohSelected(boolean qohSelected) {
		this.qohSelected = qohSelected;
	}
	
	/**
	 * Setter method for the QOH operator
	 * @param qohOperator the QOH operator (either =, > or <)
	 */
	public void setQohOperator(String qohOperator) {
		this.qohOperator = qohOperator;
	}

	/**
	 * Setter method for the QOH value
	 * @param qohValue the QOH value
	 */
	public void setQohValue(int qohValue) {
		this.qohValue = qohValue;
	}

	/**
	 * Setter method for the supplier id
	 * @param supplierId the supplier id
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * Setter method for the category
	 * @param category the category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Setter method for the min price value
	 * @param minPrice the min price value
	 */
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	/**
	 * Setter method for the max price value
	 * @param maxPrice the max price value
	 */
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	// ------------------------------------------------------
}// End class
