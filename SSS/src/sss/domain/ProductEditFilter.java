/* ProductEditFilter Class
 * 
 * An object used to determine whether a user has modified a product (used in Modify Product UC)\
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.math.BigDecimal;

public class ProductEditFilter {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private Product originalProduct;
	private Product modifiedProduct = new Product();

	private boolean hasCodeChanged = false;
	private boolean hasNameChanged = false;
	private boolean hasCostPriceChanged = false;
	private boolean hasPriceChanged = false;
	private boolean hasQuantityChanged = false;
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
	
	
	
	public boolean hasQuantityChanged() {
		return hasQuantityChanged;
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
	
	
	
	public void setQuantityChanged(boolean hasQuantityChanged) {
		this.hasQuantityChanged = hasQuantityChanged;
	}
	
	
	
	public void setProductCode(String code) {
		if(!(originalProduct.getCode().equals(code))) {
			setCodeChanged(true);
		}
		else {
			setCodeChanged(false);
		}
		modifiedProduct.setCode(code);
	}
	
	
	
	public void setId(long id) {
		modifiedProduct.setId(id);
	}
	
	
	
	public void setName(String name) {
		if(!(originalProduct.getName().equals(name))) {
			setNameChanged(true);
		}
		else {
			setNameChanged(false);
		}
		modifiedProduct.setName(name);
	}
	
	
	
	public void setCostPrice(BigDecimal costPrice) {
		if(!(originalProduct.getCostPrice().equals(costPrice))) {
			setCostPriceChanged(true);
		}
		else {
			setCostPriceChanged(false);
		}
		modifiedProduct.setCostPrice(costPrice);
	}
	
	
	
	public void setPrice(BigDecimal price) {
		if(!(originalProduct.getPrice().equals(price))) {
			setPriceChanged(true);
		}
		else {
			setPriceChanged(false);
		}
		modifiedProduct.setPrice(price);
	}
	
	
	
	public void setCategory(String category) {
		if(!originalProduct.getCategory().equals(category)) {
			setCategoryChanged(true);
		}
		else {
			setCategoryChanged(false);
		}
		modifiedProduct.setCategory(category);
	}
	
	
	
	public void setSupplierId(int supplierId) {
		if(!(originalProduct.getSupplierId() == supplierId)) {
			setSupplierChanged(true);
		}
		else {
			setSupplierChanged(false);
		}
		modifiedProduct.setSupplierId(supplierId);
	}
	
	
	
	public void setActive(boolean isActive) {
		if((originalProduct.isActive() && isActive)) {
			setActiveChanged(false);
		}
		else if ((!originalProduct.isActive()) && (!isActive)){
			setActiveChanged(false);
		}
		else {
			setActiveChanged(true);
		}
		modifiedProduct.setActive(isActive);
	}
	
	
	
	public void setQuantityOnHand(int quantity) {
		if(!(originalProduct.getQuantityOnHand() == quantity)) {
			setQuantityChanged(true);
		}
		else {
			setQuantityChanged(false);
		}
		modifiedProduct.setQuantityOnHand(quantity);
	}
	
	
	
	// ==========================================================================
	// Validation Methods
	// ==========================================================================
	
	
	
	public boolean validateProduct() {
		return modifiedProduct.validateProduct();
	}
	
	
	
	// ==========================================================================
	// Other Methods
	// ==========================================================================
	
	
	
	public boolean haveChangesBeenMadeToProduct() {
		if(hasCodeChanged || hasNameChanged || hasCostPriceChanged 
				|| hasPriceChanged || hasCategoryChanged || hasSupplierChanged 
				|| hasActiveChanged || hasQuantityChanged) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	public void resetChangeFlags() {
		setCodeChanged(false);
		setNameChanged(false);
		setCostPriceChanged(false);
		setPriceChanged(false);
		setQuantityChanged(false);
		setCategoryChanged(false);
		setSupplierChanged(false);
		setActiveChanged(false);
	}
	
	
	
	// ==========================================================================
	// Printing Methods
	// ==========================================================================
	
	
	
	public String toString() {
		StringBuffer filterString = new StringBuffer();
		filterString.append("\nCode Changed: " + hasCodeChanged);
		filterString.append("\nName Changed: " + hasNameChanged);
		filterString.append("\nCost Price Changed: " + hasCostPriceChanged);
		filterString.append("\nPrice Changed: " + hasPriceChanged);
		filterString.append("\nCategory Changed: " + hasCategoryChanged);
		filterString.append("\nSupplier Changed: " + hasSupplierChanged);
		filterString.append("\nActive Changed: " + hasActiveChanged);
		filterString.append("\nQuantity Changed: " + hasQuantityChanged);
		
		filterString.append("\n\nOriginal Code: " + originalProduct.getCode());
		filterString.append("\nModified Code: " + modifiedProduct.getCode());
		
		filterString.append("\n\nOriginal Name: " + originalProduct.getName());
		filterString.append("\nModified Name: " + modifiedProduct.getName());
		
		filterString.append("\n\nOriginal Cost Price: " + originalProduct.getCostPrice());
		filterString.append("\nModified Cost Price: " + modifiedProduct.getCostPrice());
		
		filterString.append("\n\nOriginal Price: " + originalProduct.getPrice());
		filterString.append("\nModified Price: " + modifiedProduct.getPrice());
		
		filterString.append("\n\nOriginal Category: " + originalProduct.getCategory());
		filterString.append("\nModified Category: " + modifiedProduct.getCategory());
		
		filterString.append("\n\nOriginal Supplier: " + originalProduct.getSupplierId());
		filterString.append("\nModified Supplier: " + modifiedProduct.getSupplierId());
		
		filterString.append("\n\nOriginal Active: " + originalProduct.isActive());
		filterString.append("\nModified Active: " + modifiedProduct.isActive());
		
		filterString.append("\n\nOriginal Quantity: " + originalProduct.getQuantityOnHand());
		filterString.append("\nModified Quantity: " + modifiedProduct.getQuantityOnHand());
		
		filterString.append("\n");
		
		return filterString.toString();
	}
	
} 
