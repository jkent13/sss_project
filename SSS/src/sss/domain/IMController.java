/* IMController Class
 * 
 * Serves as the controller class between all inventory management use cases and UIs
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import sss.services.DbReader;
import sss.services.SqlBuilder;

public class IMController {
	
	private NonEditableTableModel productData = new NonEditableTableModel(); // Data model for ViewInventoryFrame JTable
	
	// Column names for the product table
	private String[] productColNames = {"ID", "Code", "Name", "Cost Price", "Sale Price", "QOH", "Category", "Supplier",  "Active?"};
	
	private String[] suppliers;		// Holds the supplier names (read in from DB). Used to fill combobox in ViewInventoryFrame
	private String[] categories;	// Holds the distinct category names (read in from DB). Used to fill combobox in ViewInventoryFrame
	
	/**
	 * Constructor calls initialise method to start up
	 */
	public IMController() {
		initialise();
	}
	
	//--------------- Core Methods-------------------------------------
	/**
	 * Sets up the controller
	 */
	private void initialise() {
		try {
		productData.setColumnIdentifiers(productColNames);

		// Get SQL statements
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
		if(supplierNames.next()) {
			// This loop counts how many suppliers names there are, so that the array can be 
			// set to the correct size
			do {
				supplierArraySize++;
			} while(supplierNames.next());
			suppliers = new String[supplierArraySize]; // Set array to correct size
			
			supplierNames.beforeFirst(); // Reset ResultSet
			
			// This loop populates the suppliers array with supplier names from the DB
			int index = 0;
			while(supplierNames.next()) {
				suppliers[index] = supplierNames.getString("supp_name");
				index++;
			}
		}
		supplierNames.close(); // Close ResultSet
		ResultSet categoryNames = DbReader.executeQuery(getCategories);
		
		int categoryArraySize = 0;
		if(categoryNames.next()) {
			// This loop counts how many categories there are, so that the array can be
			// set to the correct size
			do {
				categoryArraySize++;
			} while(categoryNames.next());
			categories = new String[categoryArraySize]; // Set array to correct size
			
			categoryNames.beforeFirst(); // Reset ResultSet
			
			// This loop populates the categories array with category names from the DB
			int index = 0;
			while(categoryNames.next()) {
				categories[index] = categoryNames.getString("prod_category");
				index++;
			}
		}
		
		categoryNames.close(); // Close ResultSet
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem retrieving product data", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/**
	 * Uses the filter parameter to get a customised SQL query and change the product data model 
	 * to show only products allowed by the filter
	 * @param filter a custom filter that limits what products will be looked up and displayed
	 */
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
	
	//-----------------------------------------------------------------
	
	//------ Getter Methods -------------------------------------------
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

	//-----------------------------------------------------------------	
}// End class
