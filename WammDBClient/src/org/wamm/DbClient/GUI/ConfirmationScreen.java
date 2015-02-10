package org.wamm.DbClient.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.GUI.actions.EditPatientAction;
import org.wamm.DbClient.GUI.actions.LoadMainMenuAction;
import org.wamm.DbClient.GUI.models.ConfirmTableColModel;
import org.wamm.DbClient.GUI.models.ConfirmTableModel;
import org.wamm.DbClient.GUI.objects.ConfirmTableEntry;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ConfirmationScreen extends JPanel {

	private static final long serialVersionUID = -3819304945172451071L;

//	private MainClient mainScreen;
	
	private JScrollPane resultsScrollPane;
	private JPanel statusPnl;
	private JLabel statusMsg;
	private JButton nextBtn;
	private JButton cancelBtn;
	
	private boolean setup;
	
	public ConfirmationScreen() {
		setup = false;
	}
	
	public void setupPanel() {
		if (!setup) {
			setup = true;
			//this.setBackground(Color.GREEN);
			this.setLayout(new GridBagLayout());
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));

			JLabel lbl = new JLabel("Results:");
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			this.add(lbl, gbc);
			
			resultsScrollPane = new JScrollPane();
			resultsScrollPane.setBorder(new EmptyBorder(0,0,0,0));
			resultsScrollPane.setBackground(this.getBackground());
			resultsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			resultsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//resultsScrollPane.getVerticalScrollBar().setBackground(resultsScrollPane.getBackground());
			//resultsScrollPane.getVerticalScrollBar().setUnitIncrement(25);
			JViewport v = new JViewport();
			resultsScrollPane.setViewport(v);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 0.9;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			this.add(resultsScrollPane, gbc);
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new GridBagLayout());
			
			cancelBtn = new JButton("Menu");
			cancelBtn.setActionCommand("Cancel");
			cancelBtn.addActionListener(new LoadMainMenuAction());
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			btnPanel.add(cancelBtn, gbc);
			
			statusPnl = new JPanel();
			statusPnl.setBackground(this.getBackground());
			statusMsg = new JLabel("Please Select a File Type Above...");
			statusPnl.add(statusMsg, gbc);
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			btnPanel.add(statusPnl, gbc);
			
			nextBtn = new JButton("Next");
			nextBtn.setActionCommand("Next");
			nextBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(MainClient.getBasePanel().getWindow(), "Please select a file first");
				}
			});
			gbc = new GridBagConstraints();
			gbc.gridx = 2;
			btnPanel.add(nextBtn, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1;
			gbc.weighty = 0;
			this.add(btnPanel, gbc);
		}
	}
	
	public void displayPatientList(ArrayList<Patient> pList) {
		ConfirmTableModel ctm = new ConfirmTableModel();
		ConfirmTableColModel ctcm = new ConfirmTableColModel();
		ctcm.buildColumnsList();
		ctm.setColumns(ctcm.getColumnsList());
		
		JTable table = new JTable(ctm);
		table.setAutoCreateRowSorter(true);
		table.setColumnModel(ctcm);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new EditPatientAction());

		for (Patient pat: pList) {
			ConfirmTableEntry e = new ConfirmTableEntry();
			e.setPatient(pat);
			ctm.addRow(e);
		}
		
		resultsScrollPane.setViewportView(table);
	}
	
	public void displayStatusMessage(String statusMessage) {
		statusMsg.setText(statusMessage);
	}
	

//	/**
//	 * @param mainScreen the mainScreen to set
//	 */
//	public void setMainScreen(MainClient mainScreen) {
//		this.mainScreen = mainScreen;
//	}

	public void setNextAction(ActionListener action) {
		ActionListener[] actions = nextBtn.getActionListeners();
		for (ActionListener oldAction : actions) {
			nextBtn.removeActionListener(oldAction);
		}
		nextBtn.addActionListener(action);
	}
	
	public void setCancelAction(ActionListener action, String text) {
		ActionListener[] actions = cancelBtn.getActionListeners();
		for (ActionListener oldAction : actions) {
			cancelBtn.removeActionListener(oldAction);
		}
		cancelBtn.setText(text);
		if (action == null) {
			cancelBtn.setEnabled(false);
		} else {
			cancelBtn.addActionListener(action);
			cancelBtn.setEnabled(true);
		}
	}
}
