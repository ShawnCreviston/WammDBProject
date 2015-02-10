package org.wamm.DbClient.GUI.models;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.wamm.DbClient.GUI.objects.ConfirmTableEntry;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ConfirmTableModel extends DefaultTableModel{
	
	private static final long serialVersionUID = -4577292027067162080L;
	private Vector<TableColumn> columns;
	private ArrayList<ConfirmTableEntry> rowData;

	public ConfirmTableModel() {
		rowData = new ArrayList<ConfirmTableEntry>();
		columns = new Vector<TableColumn>();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columns.size();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return (String) columns.get(column).getHeaderValue();
	}
	
	public ConfirmTableEntry getRowAt(int rowIndex) {
		if (rowIndex > -1 && rowIndex < rowData.size()) {
			return rowData.get(rowIndex);
		}
		return null;
	}
	
	public ConfirmTableEntry removeRowAt(int rowIndex) {
		if (rowIndex > -1 && rowIndex < rowData.size()) {
			this.fireTableRowsDeleted(rowIndex, rowIndex);
			return rowData.remove(rowIndex);
		}
		return null;
	}

	public int getRowCount() {
		if (rowData == null) {
			return 0;
		} else {
			return rowData.size();
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex > -1 && rowIndex < rowData.size()) {
			return rowData.get(rowIndex).getValueAt(columnIndex);
		}
		return null;
	}
	
	public void addRow(ConfirmTableEntry cte) {
		rowData.add(cte);
		this.fireTableRowsInserted(rowData.size()-1, rowData.size()-1);
	}
	
	public void clearRows() {
		rowData.clear();
		this.fireTableDataChanged();
	}

	/**
	 * @param columns ( Vector<TableColumn> ) the columns to set
	 */
	public void setColumns(Vector<TableColumn> columns) {
		this.columns = columns;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		//return super.isCellEditable(row, column);
		return false;
	}
}
