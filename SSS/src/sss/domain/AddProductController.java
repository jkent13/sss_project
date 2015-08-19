package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sss.services.DbReader;
import sss.services.DbWriter;
import sss.services.SqlBuilder;

public class AddProductController {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private String[] suppliers;		// Holds the supplier names (read in from DB). Used to fill combobox 
	private String[] categories;	// Holds the distinct category names (read in from DB). Used to fill combobox
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public AddProductController() {
		initialise();
	}
	
	
	
  // ==========================================================================
	// Core Methods
	// ==========================================================================
	
	
	
	private void initialise() {
		try {
		String getSuppliers = SqlBuilder.getSupplierNames();
			String getCategories = SqlBuilder.getCategoryNames();

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
	
	
	
	public void saveNewProduct(Product newProduct) {
		if(isAllValid(newProduct)) {
				String productInsertStatement = SqlBuilder.getProductInsertStatement(newProduct);
				DbWriter.executeStatement(productInsertStatement);
				JOptionPane.showMessageDialog(null, "Product added to database", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	
	// ==========================================================================
	// Validation Methods
	// ==========================================================================
	
	
	
	public boolean isAllValid(Product product) {
		return (isProductUnique(product) && isProductCodeValid(product) && 
				isProductNameValid(product)&& isCostPricePositive(product) 
				&& isPricePositive(product));
	}
	
	
	
	private boolean isProductCodeValid(Product product) {
		String productCode = product.getCode();
		if(productCode.length() > 20) {
			JOptionPane.showMessageDialog(null, "Error: A product code must be less than 20 characters in length", "Product Code Too Long", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!(productCode.matches("^[a-zA-Z0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Error: A product code can only consist of alphanumeric characters", "Invalid Character(s) in Product Code", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			return true;
		}
	}
	
	
	
	private boolean isProductNameValid(Product product) {
		String productName = product.getName();
		if(productName.length() > 45) {
			JOptionPane.showMessageDialog(null, "Error: A product name must be less than 45 characters in length", "Product Name Too Long", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!(productName.matches("^[a-zA-Z0-9-\\s]+$"))) {
			JOptionPane.showMessageDialog(null, "Error: A product name can only consist of alphanumeric characters or '-' characters", "Invalid Character(s) in Product Name", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			return true;
		}
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
