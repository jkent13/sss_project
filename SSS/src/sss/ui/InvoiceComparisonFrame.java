package sss.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sss.domain.NonEditableTableModel;

public class InvoiceComparisonFrame extends JFrame {
	
	private NonEditableTableModel comparisonDataModel;
	
	public InvoiceComparisonFrame(NonEditableTableModel comparisonDataModel) {
		
		this.comparisonDataModel = comparisonDataModel;
		
		setTitle("Comparing Import with Database");
		setPreferredSize(new Dimension(850, 500));
		setMinimumSize(new Dimension(850, 500));
		setMaximumSize(new Dimension(850, 500));
		setLocationRelativeTo(null);
		
		JPanel buttonPanelSouth = new JPanel(new GridLayout(1, 2, 10, 10));
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		JButton continueButton = new JButton("Continue Import >");
		JButton cancelButton = new JButton("Cancel Import");
		
		buttonPanelSouth.add(continueButton);
		buttonPanelSouth.add(cancelButton);
		
		JTable mainTable = new JTable(comparisonDataModel);
		JScrollPane scrollPane = new JScrollPane(mainTable);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		add(tablePanel, BorderLayout.CENTER);
		add(buttonPanelSouth, BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
