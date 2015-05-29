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

import sss.services.DbConnector;
import sss.services.DbReader;
import sss.services.SqlBuilder;

public class IMController {
	
	private NonEditableTableModel productData = new NonEditableTableModel();
	
	private String[] productColNames = {"ID", "Code", "Name", "Cost Price", "Sale Price", "QOH", "Category", "Supplier",  "Active?"};
	
	private String[] suppliers;
	private String[] categories;
	
	public IMController() {
		initialise();
	}
	
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
		
		allProducts.close();
		ResultSet supplierNames = DbReader.executeQuery(getSuppliers);
		
		int supplierArraySize = 0;
		if(supplierNames.next()) {
			do {
				supplierArraySize++;
			} while(supplierNames.next());
			suppliers = new String[supplierArraySize];
			
			supplierNames.beforeFirst();
			int index = 0;
			while(supplierNames.next()) {
				suppliers[index] = supplierNames.getString("supp_name");
				index++;
			}
		}
		supplierNames.close();
		ResultSet categoryNames = DbReader.executeQuery(getCategories);
		
		int categoryArraySize = 0;
		if(categoryNames.next()) {
			do {
				categoryArraySize++;
			} while(categoryNames.next());
			categories = new String[categoryArraySize];
			
			categoryNames.beforeFirst();
			int index = 0;
			while(categoryNames.next()) {
				categories[index] = categoryNames.getString("prod_category");
				index++;
			}
		}
		
		categoryNames.close();
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: There was a problem retrieving product data", "SQL Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		try {
			DbConnector.closeConnection();
			System.out.println("DB connection closed.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: The connection to the database could not be closed properly", "DB Connection Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public NonEditableTableModel getDataModel() {
		return productData;
	}
	
	public String[] getSupplierNames() {
		return suppliers;
	}
	
	public String[] getCategoryNames() {
		return categories;
	}
	
	public void getResults(boolean qoh, boolean supplier, boolean category, boolean priceRange) {
		
	}
}
