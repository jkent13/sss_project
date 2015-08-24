package sss.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import sss.domain.IMController;
import sss.domain.NonEditableTableModel;

@SuppressWarnings("serial")
public class InvoiceComparisonFrame extends JFrame {
	
	public static final int CONFIRMED_OPTION = 1;
	public static final int CANCEL_OPTION = 0;
	
	private NonEditableTableModel comparisonDataModel;
	private IMController controller;
	
	public InvoiceComparisonFrame(NonEditableTableModel dataModel, IMController inventoryController) {
		
		this.comparisonDataModel = dataModel;
		this.controller = inventoryController;
		
		setTitle("Comparing Import with Database");
		setPreferredSize(new Dimension(850, 500));
		setMinimumSize(new Dimension(850, 500));
		setMaximumSize(new Dimension(850, 500));
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel buttonPanelSouth = new JPanel(new GridLayout(1, 3, 5, 5));
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		JButton continueButton = new JButton("Continue Import >");
		JButton cancelButton = new JButton("Cancel Import");
		JButton saveButton = new JButton("Export this report");
		
		buttonPanelSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonPanelSouth.add(continueButton);
		buttonPanelSouth.add(saveButton);
		buttonPanelSouth.add(cancelButton);
		
		
		JTable mainTable = new JTable(comparisonDataModel);
		JScrollPane scrollPane = new JScrollPane(mainTable);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		add(tablePanel, BorderLayout.CENTER);
		add(buttonPanelSouth, BorderLayout.SOUTH);
		
		
		continueButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				controller.bulkUpdate(CONFIRMED_OPTION);
				dispose();
				
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				controller.exportComparisonReport();
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				controller.bulkUpdate(CANCEL_OPTION);
				dispose();
				
			}
		});
		
		
		setVisible(true);

	}
}