package org.wamm.DbClient.GUI.models;

import java.util.Vector;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import org.wamm.DbClient.GUI.renderers.ConfirmTableCellRenderer;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ConfirmTableColModel extends DefaultTableColumnModel {

	private static final long serialVersionUID = 3489122481631916852L;

	public ConfirmTableColModel() {
		super();
	}
	
	public void buildColumnsList() {
		ConfirmTableCellRenderer ctcr = new ConfirmTableCellRenderer();
		TableColumn colHeader = new TableColumn();
		colHeader.setHeaderValue("First Name");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(80);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Middle");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(30);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Last Name");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(80);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Gender");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(30);
		colHeader.setMaxWidth(30);
		colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Birthday");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(80);
		colHeader.setMaxWidth(80);
		colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Registered");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(80);
		colHeader.setMaxWidth(80);
		colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Weight");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(50);
		colHeader.setMaxWidth(50);
		colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Address");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(80);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Street");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(80);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Phone #");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(60);
		colHeader.setMaxWidth(120);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Treatment");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(20);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("PL Code");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(20);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Marital Status");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(30);
		colHeader.setMaxWidth(90);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
		
		colHeader = new TableColumn();
		colHeader.setHeaderValue("Support Group");
		colHeader.setModelIndex(0);
		colHeader.setMinWidth(80);
		colHeader.setMaxWidth(200);
		//colHeader.setResizable(false);
		colHeader.setCellRenderer(ctcr);
		this.addColumn(colHeader);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableColumnModel#addColumn(javax.swing.table.TableColumn)
	 */
	@Override
	public void addColumn(TableColumn column) {
		if (column.getModelIndex() == 0) {
			column.setModelIndex(this.tableColumns.size());
		}
		super.addColumn(column);
	}
	
	public Vector<TableColumn> getColumnsList() {
		return this.tableColumns;
	}
}
