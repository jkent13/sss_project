/* LookupFilter Class
 * 
 * Represents a set of selections made by a user that form the basis 
 * of a product lookup query
 * 
 */

package sss.domain;

public class LookupFilter {
	
	private boolean useBarcode = false;
	private boolean useProductCode = false;
	private boolean useProductName = false;
	private boolean useCategory = false;
	
	private String barcodeValue;
	private String productCodeValue;
	private String productNameValue;
	private String categoryValue;
	
	public LookupFilter() {
		
	}
	
	public LookupFilter(boolean useBarcode, boolean useProductCode, boolean useProductName, boolean useCategory) {
		this.useBarcode = useBarcode;
		this.useProductCode = useProductCode;
		this.useProductName = useProductName;
		this.useCategory = useCategory;
	}

	// ---------- Getter Methods ----------------------------
	public boolean isUseBarcode() {
		return useBarcode;
	}

	public boolean isUseProductCode() {
		return useProductCode;
	}

	public boolean isUseProductName() {
		return useProductName;
	}

	public boolean isUseCategory() {
		return useCategory;
	}

	public String getBarcodeValue() {
		return barcodeValue;
	}

	public String getProductCodeValue() {
		return productCodeValue;
	}

	public String getProductNameValue() {
		return productNameValue;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	// ------------------------------------------------------
	
	// ---------- Setter Methods ----------------------------
	public void setUseBarcode(boolean useBarcode) {
		this.useBarcode = useBarcode;
	}

	public void setUseProductCode(boolean useProductCode) {
		this.useProductCode = useProductCode;
	}

	public void setUseProductName(boolean useProductName) {
		this.useProductName = useProductName;
	}

	public void setUseCategory(boolean useCategory) {
		this.useCategory = useCategory;
	}

	public void setBarcodeValue(String barcodeValue) {
		this.barcodeValue = barcodeValue;
	}

	public void setProductCodeValue(String productCodeValue) {
		this.productCodeValue = productCodeValue;
	}

	public void setProductNameValue(String productNameValue) {
		this.productNameValue = productNameValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	
	// ------------------------------------------------------
}// End class
