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
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class PatientLookupScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = -3819304945172451071L;

	private MainClient mainScreen;
	
	private JScrollPane resultsScrollPane;
	private JPanel addPatientPane;
	private PatientSearchPanel searchToolsPane;
	private JLabel headerMsg;
	private JLabel statusMsg;
	private JButton nextBtn;
	private JButton cancelBtn;
	
	private boolean setup;
	
	public PatientLookupScreen() {
		setup = false;
	}
	
	public void setupPanel() {
		if (!setup) {
			setup = true;
			//this.setBackground(Color.GREEN);
			this.setLayout(new GridBagLayout());
//			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new GridBagLayout());
			
			JButton btn = new JButton("Add New Patient");
			btn.setActionCommand("AddNew");
			btn.addActionListener(this);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			btnPanel.add(btn, gbc);
			
			JPanel statusPnl = new JPanel();
			statusPnl.setBackground(this.getBackground());
			headerMsg = new JLabel("Patient Lookup");
			statusPnl.add(headerMsg, gbc);
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			btnPanel.add(statusPnl, gbc);
			
			btn = new JButton("Search Tools");
			btn.setActionCommand("NewSearch");
			btn.addActionListener(this);
			gbc = new GridBagConstraints();
			gbc.gridx = 2;
			btnPanel.add(btn, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 1;
			gbc.weighty = 0;
			this.add(btnPanel, gbc);
			
			JPanel contentPane = new JPanel();
			contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
			contentPane.setLayout(new GridBagLayout());

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
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			contentPane.add(resultsScrollPane, gbc);
			
			addPatientPane = new JPanel();
			addPatientPane.setVisible(false);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			contentPane.add(addPatientPane, gbc);
			
			searchToolsPane = new PatientSearchPanel();
			searchToolsPane.setVisible(false);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			contentPane.add(searchToolsPane, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 1;
			this.add(contentPane, gbc);
			
			btnPanel = new JPanel();
			btnPanel.setLayout(new GridBagLayout());
			
			cancelBtn = new JButton("Menu");
			cancelBtn.setActionCommand("Cancel");
			cancelBtn.addActionListener(new LoadMainMenuAction());
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			btnPanel.add(cancelBtn, gbc);
			
			statusPnl = new JPanel();
			statusPnl.setBackground(this.getBackground());
			statusMsg = new JLabel("Add new patients or search for existing patients");
			statusPnl.add(statusMsg, gbc);
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			btnPanel.add(statusPnl, gbc);
			
			nextBtn = new JButton("    ");
			nextBtn.setActionCommand("Next");
			nextBtn.setEnabled(false);
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

	/**
	 * @param mainScreen the mainScreen to set
	 */
	public void setMainScreen(MainClient mainScreen) {
		this.mainScreen = mainScreen;
	}

	public void setNextAction(ActionListener action, String text) {
		ActionListener[] actions = nextBtn.getActionListeners();
		for (ActionListener oldAction : actions) {
			nextBtn.removeActionListener(oldAction);
		}
		if (action == null) {
			nextBtn.setEnabled(false);
			return;
		} else {
			nextBtn.addActionListener(action);
			nextBtn.setEnabled(true);
		}
		nextBtn.setText(text);
	}
	
	public void setCancelAction(ActionListener action, String text) {
		ActionListener[] actions = cancelBtn.getActionListeners();
		for (ActionListener oldAction : actions) {
			cancelBtn.removeActionListener(oldAction);
		}
		if (action == null) {
			cancelBtn.setEnabled(false);
		} else {
			cancelBtn.addActionListener(action);
			cancelBtn.setEnabled(true);
		}
		cancelBtn.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String actionCommand = arg0.getActionCommand();
		
		if (actionCommand.equals("AddNew")) {
			resultsScrollPane.setVisible(false);
			searchToolsPane.setVisible(false);
			addPatientPane.setVisible(true);
		} else if (actionCommand.equals("NewSearch")) {
			resultsScrollPane.setVisible(false);
			addPatientPane.setVisible(false);
			searchToolsPane.setupPanel();
			searchToolsPane.setVisible(true);
			displayStatusMessage("Add at least one filter to search for patients. Then click run search.");
			
			setNextAction(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					searchToolsPane.loadPatientsFromFilters();
					displayPatientList(UserSessionVO.getSession().getPatientList());
					if (UserSessionVO.getSession().getPatientList() == null || UserSessionVO.getSession().getPatientList().size() == 0) {
						displayStatusMessage("Found 0 patients. Try adjusting your search.");
					} else {
						displayStatusMessage("Found "+UserSessionVO.getSession().getPatientList().size()+" patients.");
					}
					searchToolsPane.setVisible(false);
					addPatientPane.setVisible(false);
					resultsScrollPane.setVisible(true);
				}
			}, "Run Search");
		}
	}
}
