package org.wamm.DbClient.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.Dao.PatientAdultDao;
import org.wamm.DbClient.GUI.objects.TerritoryComboBoxEntry;
import org.wamm.DbClient.Globals.PropertyGlobals;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class PatientSearchPanel extends JPanel {

	private static final long serialVersionUID = -3819304945172451071L;

	
	private JTextField firstName;
	private JTextField lastName;
	private JTextField bdayStart;
	private JTextField bdayEnd;
	private JComboBox<TerritoryComboBoxEntry> locatations;
	
	private boolean setup;
	
	public PatientSearchPanel() {
		setup = false;
	}
	
	public void setupPanel() {
		if (!setup) {
			setup = true;
			this.setLayout(new GridBagLayout());
//			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
			
			JPanel filterLine = new JPanel();
			filterLine.setLayout(new GridBagLayout());
			
			JLabel lbl = new JLabel("Name:");
			lbl.setFont(new Font("Calibri",Font.BOLD, 24));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(5,5,1,5);
			this.add(lbl, gbc);
			
			firstName = new JTextField();
			firstName.setColumns(20);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
//			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,1,5);
			filterLine.add(firstName, gbc);
			
			lastName = new JTextField();
			lastName.setColumns(20);
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
//			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,1,5);
			filterLine.add(lastName, gbc);
			
			lbl = new JLabel("Does not need to be full name.");
			lbl.setFont(new Font("Calibri",Font.BOLD, 12));
			gbc = new GridBagConstraints();
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
//			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,1,5);
			filterLine.add(lbl, gbc);
			
			lbl = new JLabel("First Name");
			lbl.setFont(new Font("Calibri",Font.BOLD, 12));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(1,5,5,5);
			filterLine.add(lbl, gbc);
			
			lbl = new JLabel("Last Name");
			lbl.setFont(new Font("Calibri",Font.BOLD, 12));
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(1,5,5,5);
			filterLine.add(lbl, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.insets = new Insets(5,5,5,5);
			this.add(filterLine, gbc);
			
			filterLine = new JPanel();
			filterLine.setLayout(new GridBagLayout());
			
			lbl = new JLabel("Birthdate:");
			lbl.setFont(new Font("Calibri",Font.BOLD, 24));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(5,5,5,5);
			this.add(lbl, gbc);
			
			bdayStart = new JTextField();
			bdayStart.setColumns(15);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(5,5,5,5);
			filterLine.add(bdayStart, gbc);
			
			JCheckBox checkBox = new JCheckBox();
			checkBox.setText("Use Range");
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(5,5,5,5);
			filterLine.add(checkBox, gbc);
			
			bdayEnd = new JTextField();
			bdayEnd.setColumns(15);
			bdayEnd.setVisible(false);
			gbc = new GridBagConstraints();
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(5,5,5,5);
			filterLine.add(bdayEnd, gbc);
			
			lbl = new JLabel("Format: yyyy or yyyy-mm-dd");
			lbl.setFont(new Font("Calibri",Font.BOLD, 12));
			gbc = new GridBagConstraints();
			gbc.gridx = 4;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(5,5,5,5);
			filterLine.add(lbl, gbc);
			
			final JLabel startDateLbl = new JLabel("Start Date");
			startDateLbl.setFont(new Font("Calibri",Font.BOLD, 12));
			startDateLbl.setVisible(false);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(1,5,5,5);
			filterLine.add(startDateLbl, gbc);
			
			final JLabel endDateLbl = new JLabel("End Date");
			endDateLbl.setFont(new Font("Calibri",Font.BOLD, 12));
			endDateLbl.setVisible(false);
			gbc = new GridBagConstraints();
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(1,5,5,5);
			filterLine.add(endDateLbl, gbc);
			
			checkBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JCheckBox check = (JCheckBox) e.getSource();
					bdayEnd.setVisible(check.isSelected());
					bdayEnd.setText("");
					startDateLbl.setVisible(check.isSelected());
					endDateLbl.setVisible(check.isSelected());
				}
			});
			
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.insets = new Insets(5,5,5,5);
			this.add(filterLine, gbc);
			
			gbc = new GridBagConstraints();
			gbc.gridy = 3;
			gbc.weighty = 0.9;
			this.add(Box.createVerticalGlue(), gbc);
		}
	}
	
	public void loadPatientsFromFilters() {
		StringBuilder whereClauseA = new StringBuilder();
		StringBuilder whereClauseB = new StringBuilder();
		
		// Build Name sql
		if (firstName.getText().length() > 0) {
			whereClauseA.append("'"+PatientAdultDao.escapeSQLCharacters(firstName.getText().trim().toLowerCase())+"' = aes_decrypt("+PatientAdultDao.FN_FIRST_NAME+ ",'"+
				UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT)+"')");
			
			whereClauseB.append("INSTR(aes_decrypt("+PatientAdultDao.FN_FIRST_NAME+ ",'"+
					UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT)+"'),'"+
					PatientAdultDao.escapeSQLCharacters(firstName.getText().trim().toLowerCase())+"') = 1");
		}
		
		if (lastName.getText().length() > 0) {
			if (whereClauseA.length() > 0) {
				whereClauseA.append(" AND ");
				whereClauseB.append(" AND ");
			}
			whereClauseA.append("'"+PatientAdultDao.escapeSQLCharacters(lastName.getText().trim().toLowerCase())+"' = aes_decrypt("+PatientAdultDao.FN_LAST_NAME+ ",'"+
				UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT)+"')");
			
			whereClauseB.append("INSTR(aes_decrypt("+PatientAdultDao.FN_LAST_NAME+ ",'"+
					UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT)+"'),'"+
					PatientAdultDao.escapeSQLCharacters(lastName.getText().trim().toLowerCase())+"') = 1");
		}
		
		// Load in from DB
		Connection cn = null;
		
		try {
			cn = PatientAdultDao.createMySqlConnection(true);
			
			ArrayList<Patient> dbListA = PatientAdultDao.getPatients(whereClauseA.toString(), null, cn);
			
			HashSet<Integer> pids = new HashSet<Integer>();
			for (Patient pat: dbListA) {
				pids.add(pat.getPatientId());
			}
			
			ArrayList<Patient> dbListB = PatientAdultDao.getPatients(whereClauseB.toString(), null, cn);
			for (Patient pat: dbListB) {
				if (!pids.contains(pat.getPatientId())) {
					dbListA.add(pat);
				}
			}
			
			UserSessionVO.getSession().setPatientList(dbListA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
