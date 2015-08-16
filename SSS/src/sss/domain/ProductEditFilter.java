package sss.domain;

public class ProductEditFilter {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private Product originalProduct;
	private Product modifiedProduct;

	private boolean hasCodeChanged = false;
	private boolean hasNameChanged = false;
	private boolean hasCostPriceChanged = false;
	private boolean hasPriceChanged = false;
	private boolean hasCategoryChanged = false;
	private boolean hasSupplierChanged = false;
	private boolean hasActiveChanged = false;



	// ==========================================================================
	// Constructors
	// ==========================================================================
	
	
	
	public ProductEditFilter() {
		
	}
	
	
	
	public ProductEditFilter(Product product) {
		originalProduct = product;
	}



	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public Product getOriginalProduct() {
		return originalProduct;
	}



	public Product getModifiedProduct() {
		return modifiedProduct;
	}



	public boolean hasCodeChanged() {
		return hasCodeChanged;
	}



	public boolean hasNameChanged() {
		return hasNameChanged;
	}



	public boolean hasCostPriceChanged() {
		return hasCostPriceChanged;
	}



	public boolean hasPriceChanged() {
		return hasPriceChanged;
	}



	public boolean hasCategoryChanged() {
		return hasCategoryChanged;
	}



	public boolean hasSupplierChanged() {
		return hasSupplierChanged;
	}



	public boolean hasActiveChanged() {
		return hasActiveChanged;
	}



	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
	public void setOriginalProduct(Product originalProduct) {
		this.originalProduct = originalProduct;
	}



	public void setModifiedProduct(Product modifiedProduct) {
		this.modifiedProduct = modifiedProduct;
	}



	public void setCodeChanged(boolean hasCodeChanged) {
		this.hasCodeChanged = hasCodeChanged;
	}



	public void setNameChanged(boolean hasNameChanged) {
		this.hasNameChanged = hasNameChanged;
	}



	public void setCostPriceChanged(boolean hasCostPriceChanged) {
		this.hasCostPriceChanged = hasCostPriceChanged;
	}



	public void setPriceChanged(boolean hasPriceChanged) {
		this.hasPriceChanged = hasPriceChanged;
	}



	public void setCategoryChanged(boolean hasCategoryChanged) {
		this.hasCategoryChanged = hasCategoryChanged;
	}



	public void setSupplierChanged(boolean hasSupplierChanged) {
		this.hasSupplierChanged = hasSupplierChanged;
	}



	public void setActiveChanged(boolean hasActiveChanged) {
		this.hasActiveChanged = hasActiveChanged;
	}
}
