package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sss.services.DbReader;
import sss.services.DbWriter;
import sss.services.SqlBuilder;

public class ModifyProductController {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private String[] suppliers;		// Holds the supplier names (read in from DB). Used to fill combobox 
	private String[] categories;	// Holds the distinct category names (read in from DB). Used to fill combobox
	private ProductEditFilter filter = new ProductEditFilter();
	private NonEditableTableModel productData = new NonEditableTableModel();
	private InventoryFilter blankFilter = new InventoryFilter(false, false, false, false);
	private String[] productColNames = {"Product ID", "Code", "Name", "Cost Price", "Sale Price", "QOH", "Category", "Supplier",  "Active?"};
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public ModifyProductController() {
		initialise();
	}
	
	
	
  // ==========================================================================
	// Core Methods
	// ==========================================================================
	
	
	
	private void initialise() {
		try {
			productData.setColumnIdentifiers(productColNames);
			
			String selectAllProducts = SqlBuilder.getAllProducts();
			String getSuppliers = SqlBuilder.getSupplierNames();
			String getCategories = SqlBuilder.getCategoryNames();
			
			ResultSet allProducts = DbReader.executeQuery(selectAllProducts);
			
			// Populate productData
			if(allProducts.next()) {
				do {
					productData.addRow(new Object[] {
							allProducts.getLong("prod_id"),
							allProducts.getString("prod_code"),
							allProducts.getString("prod_name"),
							new BigDecimal(allProducts.getDouble("prod_cost_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
							new BigDecimal(allProducts.getDouble("prod_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
							allProducts.getInt("prod_qoh"),
							allProducts.getString("prod_category"),
							allProducts.getString("supp_name"),
							allProducts.getString("prod_active")					
					});
				} while(allProducts.next());
			}
			
			allProducts.close(); // Close ResultSet
			ResultSet supplierNames = DbReader.executeQuery(getSuppliers);

			int supplierArraySize = 0;
			if (supplierNames.next()) {
				// This loop counts how many suppliers names there are, so that the
				// array can be set to the correct size
				do {
					supplierArraySize++;
				} while (supplierNames.next());
				suppliers = new String[supplierArraySize]; // Set array to correct size

				supplierNames.beforeFirst(); // Reset ResultSet

				// This loop populates the suppliers array with supplier names from the DB
				int index = 0;
				while (supplierNames.next()) {
					suppliers[index] = supplierNames.getString("supp_name");
					index++;
				}
			}
			supplierNames.close(); // Close ResultSet
			ResultSet categoryNames = DbReader.executeQuery(getCategories);

			int categoryArraySize = 0;
			if (categoryNames.next()) {
				// This loop counts how many categories there are, so that the array can
				// be set to the correct size
				do {
					categoryArraySize++;
				} while (categoryNames.next());
				categories = new String[categoryArraySize]; // Set array to correct size

				categoryNames.beforeFirst(); // Reset ResultSet

				// This loop populates the categories array with category names from the DB
				int index = 0;
				while (categoryNames.next()) {
					categories[index] = categoryNames.getString("prod_category");
					index++;
				}
			}

			categoryNames.close(); // Close ResultSet

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error: There was a problem retrieving product data", "SQL Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	
	public void getResults(InventoryFilter filter) {
		try {
			String filteredQuery = SqlBuilder.getProductsFiltered(filter);
			ResultSet filterProducts = DbReader.executeQuery(filteredQuery);
			
			// Clear product data
			for(int i = productData.getRowCount()-1; i != -1; i--) {
				productData.removeRow(i);
			}
			
			if(filterProducts.next()) {
				// Populate productData with new values
				do {
					productData.addRow(new Object[] {
							filterProducts.getLong("prod_id"),
							filterProducts.getString("prod_code"),
							filterProducts.getString("prod_name"),
							new BigDecimal(filterProducts.getDouble("prod_cost_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
							new BigDecimal(filterProducts.getDouble("prod_price")).setScale(2, BigDecimal.ROUND_HALF_EVEN),
							filterProducts.getInt("prod_qoh"),
							filterProducts.getString("prod_category"),
							filterProducts.getString("supp_name"),
							filterProducts.getString("prod_active")					
					});
				} while(filterProducts.next());
			}

			filterProducts.close(); // Close ResultSet
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem retrieving product data", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	
	public void saveModifiedProduct(Product modifiedProduct) {
		if(isAllValid(modifiedProduct)) {
				JOptionPane.showMessageDialog(null, "Changes applied to database", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	
	// ==========================================================================
	// Validation Methods
	// ==========================================================================
	
	
	
	public boolean isAllValid(Product product) {
		return (isProductUnique(product) && isCostPricePositive(product) && isPricePositive(product));
	}
	
	
	
	private boolean isProductUnique(Product product) {
		try {
			String barcodeMatchQuery = SqlBuilder.getBarcodeMatchQuery(product.getId());
			String productCodeMatchQuery = SqlBuilder.getProductCodeMatchQuery(product.getCode());
			
			ResultSet queryResults = DbReader.executeQuery(barcodeMatchQuery);
			if(queryResults.next()){
				JOptionPane.showMessageDialog(null, "Error: There is already a product in the database with that barcode. Barcodes must be unique", "Duplicate Barcode", JOptionPane.ERROR_MESSAGE);
				queryResults.close();
				return false;
			}
			queryResults.close();
			queryResults = DbReader.executeQuery(productCodeMatchQuery);
			if(queryResults.next()){
				JOptionPane.showMessageDialog(null, "Error: There is already a product in the database with that product code. Product codes must be unique", "Duplicate Barcode", JOptionPane.ERROR_MESSAGE);
				queryResults.close();
				return false;
			}
			return true;			
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem retrieving "
					+ "product data", "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	
	
	private boolean isCostPricePositive(Product product) {
		if(product.getCostPrice().compareTo(BigDecimal.ZERO) <= 0) {
			JOptionPane.showMessageDialog(null, "Error: The cost price value must be > 0", "Invalid Cost Price", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			return true;
		}
	}
	
	
	
	private boolean isPricePositive(Product product) {
		if(product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
			JOptionPane.showMessageDialog(null, "Error: The price value must be > 0", "Invalid Price", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			return true;
		}
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	/**
	 * Getter method for the product data model
	 * @return the product data model
	 */
	public NonEditableTableModel getDataModel() {
		return productData;
	}
	
	
	/**
	 * Getter method for the array containing all supplier names 
	 * @return the supplier name array
	 */
	public String[] getSupplierNames() {
		return suppliers;
	}
	
	/**
	 * Getter method for the array containing all category names
	 * @return the category name array
	 */
	public String[] getCategoryNames() {
		return categories;
	}
}
