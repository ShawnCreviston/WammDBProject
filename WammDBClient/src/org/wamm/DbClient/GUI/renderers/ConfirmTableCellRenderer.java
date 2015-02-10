package org.wamm.DbClient.GUI.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ConfirmTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 8255241976546991296L;

	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		//ConfirmTableEntry cte = ((ConfirmTableModel)table.getModel()).getRowAt(row);		
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		// Can modify cell visuals as needed here, I don't have anything to change yet.
		return cell;
	}
}
