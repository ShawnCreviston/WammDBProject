package org.wamm.DbClient.GUI.actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import org.wamm.DbClient.GUI.models.ConfirmTableModel;
import org.wamm.DbClient.GUI.objects.ConfirmTableEntry;
import org.wamm.DbClient.GUI.objects.PatientEditDialog;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class EditPatientAction extends MouseAdapter {
	
	private PatientEditDialog editDialog;
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable table = (JTable)e.getSource();
			int viewRowIndex = table.getSelectedRow();
			int modelRowIndex = table.convertRowIndexToModel(viewRowIndex);
			//System.out.println("Clicked: "+modelRowIndex);
			
			ConfirmTableModel ctm = (ConfirmTableModel) table.getModel();
			ConfirmTableEntry cte = ctm.getRowAt(modelRowIndex);
			
			if (editDialog == null) {
				editDialog = new PatientEditDialog();
			}
			
			editDialog.setLocation((int)(e.getLocationOnScreen().getX()-editDialog.getSize().getWidth()/3),(int)(e.getLocationOnScreen().getY()-editDialog.getSize().getHeight()/4));
			editDialog.setForPatient(cte.getPatient());
			editDialog.setVisible(true);
			
			ctm.fireTableRowsUpdated(viewRowIndex, viewRowIndex);
		}
	}
}
