package sss.domain;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class LineItemTableModel extends DefaultTableModel {
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
