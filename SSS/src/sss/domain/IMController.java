/* IMController Class
 * 
 * Serves as the controller class between all inventory management use cases and UIs
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import sss.services.DbReader;
import sss.services.DbWriter;
import sss.services.SqlBuilder;
import sss.ui.InvoiceComparisonFrame;

public class IMController {
	
	private NonEditableTableModel productData = new NonEditableTableModel(); // Data model for ViewInventoryFrame JTable
	private NonEditableTableModel invoiceComparisonData = new NonEditableTableModel();
	
	private InventoryFilter blankFilter = new InventoryFilter(false, false, false, false);
	private ArrayList<InvoiceRowComparison> comparisonSet;
	
	// Column names for the product table
	private String[] productColNames = {"Product ID", "Code", "Name", "Cost Price", "Sale Price", "QOH", "Category", "Supplier",  "Active?"};
	
	private String[] comparisonTableColNames = {"Row #", "Product Code", "Cost Price", "Price", "Quantity"};
	
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
		invoiceComparisonData.setColumnIdentifiers(comparisonTableColNames);

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


	public void readCsv() {

		try {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
			chooser.setFileFilter(filter);
			int selection = chooser.showOpenDialog(null);
			if(selection == JFileChooser.APPROVE_OPTION) {

				File file = chooser.getSelectedFile();
				Scanner fileReader = new Scanner(file);
				ArrayList<String[]> rows = new ArrayList<String[]>();

				String currentLine;
				String[] tokens;

				while (fileReader.hasNext()) {
					currentLine = fileReader.nextLine();
					tokens = currentLine.split(",");	
					rows.add(tokens);
				}
				fileReader.close();
				
				if(validateCsvRows(rows)) {
					Invoice invoice = new Invoice();
					BigDecimal costPrice = null;
					BigDecimal price = null;
					int quantity = 0;

					for(String[] row: rows) {
						if(!row[1].equals("-")) {
							costPrice = new BigDecimal(row[1]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						}
						else {
							costPrice = null;
						}
						if(!row[2].equals("-")) {
							price = new BigDecimal(row[2]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						}
						else {
							price = null;
						}
						if(!row[3].equals("-")) {
							quantity = Integer.parseInt(row[3]);
						}
						else {
							quantity = 0;
						}

						invoice.addRow(row[0], costPrice, price, quantity);
					}

					comparisonSet = invoice.getComparisonSet();

					for(InvoiceRowComparison irc: comparisonSet) {
						invoiceComparisonData.addRow(new Object[] {
								irc.getRowNumber(),
								irc.getProductCode(),
								irc.getCostPriceChange(),
								irc.getPriceChange(),
								irc.getQuantityChange()
						});
					}
					
					new InvoiceComparisonFrame(invoiceComparisonData, this);
				}
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: The file you tried to open could not be found.", "File Not Found", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void bulkUpdate(int confirmed) {
		if(confirmed == 1) {
			
			if(comparisonSet != null) {
				String[] updateStatements = SqlBuilder.getInvoiceUpdateStatements(comparisonSet);
				
				for(String updateStatement: updateStatements) {
					DbWriter.executeStatement(updateStatement);
				}
				
				JOptionPane.showMessageDialog(null, "Changes applied successfully!", "Complete", JOptionPane.INFORMATION_MESSAGE);
				
				refresh();
				comparisonSet = null;
				for(int i = invoiceComparisonData.getRowCount()-1; i != -1; i--) {
					invoiceComparisonData.removeRow(i);
				}
			}
			
		}
		else {
			comparisonSet = null;
			for(int i = invoiceComparisonData.getRowCount()-1; i != -1; i--) {
				invoiceComparisonData.removeRow(i);
			}
		}
	}
	
	public void exportComparisonReport() {
		if(comparisonSet != null) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV Files", "csv");
			int optionSelected = fileChooser.showDialog(null, "Save");
			if(optionSelected == JFileChooser.APPROVE_OPTION) {
				try{
					File selectedFile = fileChooser.getSelectedFile();
					String fileName = selectedFile.getName();
					if(!fileName.toUpperCase().contains(".CSV")) {
						fileName = fileChooser.getSelectedFile() + ".csv";
					}
					else {
						fileName = fileChooser.getSelectedFile().toString();
					}
					
					FileWriter writer = new FileWriter(fileName);
					writer.append(InvoiceRowComparison.getCsvHeader());
					for(int i = 0; i < comparisonSet.size(); i++) {
						writer.append(comparisonSet.get(i).getCsvRow());
					}
					writer.close();
					
					JOptionPane.showMessageDialog(null, "Report exported successfully!", "Complete", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	public void exportProductReport() {
		if(productData != null) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV Files", "csv");
			int optionSelected = fileChooser.showDialog(null, "Save");
			if(optionSelected == JFileChooser.APPROVE_OPTION) {
				try{
					File selectedFile = fileChooser.getSelectedFile();
					String fileName = selectedFile.getName();
					if(!fileName.toUpperCase().contains(".CSV")) {
						fileName = fileChooser.getSelectedFile() + ".csv";
					}
					else {
						fileName = fileChooser.getSelectedFile().toString();
					}
					
					FileWriter writer = new FileWriter(fileName);
					
					//WRITE HEADER
					for(int i = 0; i < productColNames.length; i++) {
						if((i+1) != productColNames.length){
							writer.append(productColNames[i] + ",");
						}
						else {
							writer.append(productColNames[i]);
						}
					}
					writer.append("\n");
					
					int rowCount = productData.getRowCount();
					int colCount = productData.getColumnCount();
					
					//WRITE CONTENT
					for(int i = 0; i < rowCount; i++) {
						for(int j = 0; j < colCount; j++) {
							if((j+1) != colCount) {
								writer.append(productData.getValueAt(i, j) + ",");
							}
							else {
								writer.append(productData.getValueAt(i, j).toString());
							}
						}
						writer.append("\n");
						
					}
					writer.close();
					
					JOptionPane.showMessageDialog(null, "Report exported successfully!", "Complete", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	private boolean validateCsvRows(ArrayList<String[]> rows) {
		int rowCounter = 1;
		try {
			for(String[] row: rows) {
				if(row.length != 4) {
					return false;
				}
				if(!row[1].equals("-")) {
					BigDecimal costPrice = new BigDecimal(row[1]);
				}
				if(!row[2].equals("-")) {
					BigDecimal price = new BigDecimal(row[2]);
				}
				if(!row[3].equals("-")) {
					Integer quantity = Integer.parseInt(row[3]);
				}
				rowCounter++;
			}
		} 
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Error: An invalid price value was found at row: " + rowCounter + 
					". Please ensure this value is correct and retry the import.", "Invalid Price", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private void refresh() {
		getResults(blankFilter);
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