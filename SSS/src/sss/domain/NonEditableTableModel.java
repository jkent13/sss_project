<<<<<<< HEAD:SSS/src/sss/domain/LineItemTableModel.java
/* LineItemTableModel Class
=======
/* NonEditableTableModel Class
>>>>>>> origin/DevelopmentCodebase:SSS/src/sss/domain/NonEditableTableModel.java
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
	
	// This overridden method prevents users from editing any cells in a JTable using this data model
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// For any cell in any row and any column, always return false
		return false;
	}

} // End class
