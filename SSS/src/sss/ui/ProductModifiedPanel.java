package sss.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import sss.domain.NonEditableTableModel;
import sss.domain.Product;
import sss.domain.ProductEditFilter;

public class ProductModifiedPanel extends JPanel {
	private JTable productDisplayTable;
	private String[] suppliers;
	private NonEditableTableModel tableModel = new NonEditableTableModel();
	private ArrayList<String> tableColumns = new ArrayList<>();
	private JLabel label = new JLabel("The following changes will be applied to this product:");
	
	public ProductModifiedPanel(ProductEditFilter filter, String[] suppliers) {
		this.suppliers = suppliers;
		initialiseTableModel(filter);
		populateTableModel(filter);
		productDisplayTable = new JTable(tableModel);
		productDisplayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(productDisplayTable);
		setLayout(new BorderLayout());
		add(label, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		setPreferredSize(new Dimension(1000, 100));
		
	}
	
	private void initialiseTableModel(ProductEditFilter filter) {
		if(filter.hasNameChanged()) {
			tableColumns.add("Name");
		}
		if(filter.hasCostPriceChanged()) {
			tableColumns.add("Cost Price");
		}
		if(filter.hasPriceChanged()) {
			tableColumns.add("Sale Price");
		}
		if(filter.hasQuantityChanged()) {
			tableColumns.add("QOH");
		}
		if(filter.hasCategoryChanged()) {
			tableColumns.add("Category");
		}
		if(filter.hasSupplierChanged()) {
			tableColumns.add("Supplier");
		}
		if(filter.hasActiveChanged()) {
			tableColumns.add("Active?");
		}
		
		tableModel.setColumnIdentifiers(tableColumns.toArray());
	}
	
	private void populateTableModel(ProductEditFilter filter) {
		Product originalProduct = filter.getOriginalProduct();
		Product modifiedProduct = filter.getModifiedProduct();
		
		ArrayList<Object> cellValues = new ArrayList<>();
		
		if(filter.hasNameChanged()) {
			cellValues.add(originalProduct.getName() + " --> " +
					modifiedProduct.getName());
		}
		if(filter.hasCostPriceChanged()) {
			cellValues.add(originalProduct.getCostPrice().toPlainString() + " --> "
					+ modifiedProduct.getCostPrice().toPlainString());
		}
		if(filter.hasPriceChanged()) {
			cellValues.add(originalProduct.getPrice().toPlainString() + " --> " 
					+ modifiedProduct.getPrice().toPlainString());
		}
		if(filter.hasQuantityChanged()) {
			cellValues.add(String.valueOf(originalProduct.getQuantityOnHand()) + " --> " 
					+ String.valueOf(modifiedProduct.getQuantityOnHand()));
		}
		if(filter.hasCategoryChanged()) {
			cellValues.add(originalProduct.getCategory() + " --> " +
					modifiedProduct.getCategory());
		}
		if(filter.hasSupplierChanged()) {
			String originalSupplier = matchSupplierIdToName(originalProduct.getSupplierId());
			String modifiedSupplier = matchSupplierIdToName(modifiedProduct.getSupplierId());
			
			cellValues.add(originalSupplier + " --> " + modifiedSupplier);
		}
		if(filter.hasActiveChanged()) {
			String originalActive;
			String modifiedActive;
			if(filter.getOriginalProduct().isActive()) {
				originalActive = "Y";
			}
			else {
				originalActive = "N";
			}
			if(filter.getModifiedProduct().isActive()) {
				modifiedActive = "Y";
			}
			else {
				modifiedActive = "N";
			}
			cellValues.add(originalActive + " --> " + modifiedActive);
		}
		tableModel.addRow(cellValues.toArray());
	}
	
	private String matchSupplierIdToName(int supplierId) {
		return suppliers[supplierId-1];
	}
}
