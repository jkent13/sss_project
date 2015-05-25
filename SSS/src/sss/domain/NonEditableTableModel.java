/* LineItemTableModel Class
 * 
 * A custom version of DefaultTableModel (used to create JTables) where
 * the cells are not editable. Objects of this class are used in the look-up JTable
 * of PosFrame
 * 
 * Original Author: Josh Kent
 */

package sss.domain;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class NonEditableTableModel extends DefaultTableModel {
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
