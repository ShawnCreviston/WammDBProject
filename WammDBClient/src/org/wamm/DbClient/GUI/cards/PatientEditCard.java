package org.wamm.DbClient.GUI.cards;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.BusinessObjects.SupportGroup;
import org.wamm.DbClient.BusinessObjects.Territory;
import org.wamm.DbClient.Dao.SupportGroupDao;
import org.wamm.DbClient.Dao.TerritoryDao;
import org.wamm.DbClient.GUI.objects.SupportGroupComboBoxEntry;
import org.wamm.DbClient.GUI.objects.TerritoryComboBoxEntry;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class PatientEditCard extends JPanel {
	
	private static final long serialVersionUID = -1130783002278287206L;
	private Patient patient;
	private HashMap<Integer, TerritoryComboBoxEntry> territoryMap;
	private HashMap<Integer, SupportGroupComboBoxEntry> supportGroupMap;

	private JTextField firstName;
	private JTextField middleName;
	private JTextField lastName;
	private JComboBox<String> gender;
	private JTextField birthday;
	private JTextField deceased;
	private JTextField weight;
	private JComboBox<TerritoryComboBoxEntry> address;
	private JTextField street;
	private JTextField phone;
	private JTextField school;
	private JTextField occupation;
	private JTextField maritalStatus;
	private JComboBox<SupportGroupComboBoxEntry> supportGroup;
	
	
	private boolean setup;
	
	public PatientEditCard() {
		setup = false;
	}
	
	public void setupCard() {
		if (setup) return;
		setup = true;
		
		this.setLayout(new GridBagLayout());
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		int gridy = 0;
		
		JLabel lbl = new JLabel("First:");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		firstName = new JTextField("JJ");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(firstName, gbc);
		
		
		lbl = new JLabel("Middle:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		middleName = new JTextField("JJ");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(middleName, gbc);
		
		
		lbl = new JLabel("Last:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		lastName = new JTextField("JJ");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(lastName, gbc);
		
		
		lbl = new JLabel("Gender:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		gender = new JComboBox<String>();
		gender.addItem("");
		gender.addItem("M");
		gender.addItem("F");
//		gender.setEditable(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(gender, gbc);
		
		
		lbl = new JLabel("Birthday:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		birthday = new JTextField("JJ");
		birthday.setEditable(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(birthday, gbc);
		
		
		lbl = new JLabel("Deceased day:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		deceased = new JTextField("JJ");
		deceased.setEditable(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(deceased, gbc);
		
		
		lbl = new JLabel("Weight:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		weight = new JTextField("JJ");
		weight.setEditable(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(weight, gbc);
		
		
		lbl = new JLabel("Address:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		address = new JComboBox<TerritoryComboBoxEntry>();
		ArrayList<Territory> territoryList = loadTerritoryData();
		if (territoryList != null) {
			territoryMap = new HashMap<Integer, TerritoryComboBoxEntry>();
			for (Territory ter:territoryList) {
				TerritoryComboBoxEntry tcbe = new TerritoryComboBoxEntry(ter);
				address.addItem(tcbe);
				territoryMap.put(ter.getTerritoryId(), tcbe);
			}
		}
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(address, gbc);
		
		
		lbl = new JLabel("Street:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		street = new JTextField("JJ");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(street, gbc);
		
		
		lbl = new JLabel("Phone:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		phone = new JTextField("JJ");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(phone, gbc);
		
		
		lbl = new JLabel("School:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		school = new JTextField("JJ");
		school.setEditable(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(school, gbc);
		
		
		lbl = new JLabel("Occupation:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		occupation = new JTextField("JJ");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(occupation, gbc);
		
		lbl = new JLabel("Matital Status:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		maritalStatus = new JTextField("JJ");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(maritalStatus, gbc);
		
		
		lbl = new JLabel("Support Group:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = gridy;
		this.add(lbl, gbc);
		
		supportGroup = new JComboBox<SupportGroupComboBoxEntry>();
		ArrayList<SupportGroup> supportGroupList = loadSupportGroupData();
		if (supportGroupList != null) {
			supportGroupMap = new HashMap<Integer, SupportGroupComboBoxEntry>();
			for (SupportGroup sg:supportGroupList) {
				SupportGroupComboBoxEntry sgcbe = new SupportGroupComboBoxEntry(sg);
				supportGroup.addItem(sgcbe);
				supportGroupMap.put(sg.getSupportGroupID(), sgcbe);
			}
		}
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		this.add(supportGroup, gbc);
	}
	
	public void setForPatient(Patient patient) {
		setPatient(patient);
		
		firstName.setText(patient.getFirstName());
		middleName.setText(patient.getMiddleName());
		lastName.setText(patient.getLastName());
		if (patient.getGender() != null) {
			if (patient.getGender().equalsIgnoreCase("M")) {
				gender.setSelectedIndex(1);
			} else if (patient.getGender().equalsIgnoreCase("F")) {
				gender.setSelectedIndex(2);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		birthday.setText(patient.getBirthday()==null?"":sdf.format(patient.getBirthday()));
		deceased.setText(patient.getDeceased() == null?"":sdf.format(patient.getDeceased()));
		weight.setText(""+patient.getWeight());
		address.setSelectedItem(territoryMap.get(patient.getCurTerritory()== null?"":patient.getCurTerritory().getTerritoryId()));
		street.setText(patient.getCurStreet());
		phone.setText(patient.getPhoneNumber1());
		school.setText(patient.getSchool()== null?"":patient.getSchool().getSchoolName());
		occupation.setText(patient.getOccupation());
		maritalStatus.setText(patient.getMaritalStatus());
		supportGroup.setSelectedItem(patient.getSupportGroup()==null?"":patient.getSupportGroup().getSupportGroupID());
	}
	
	public void saveChangesToPatient() {
		patient.setFirstName(firstName.getText());
		patient.setMiddleName(middleName.getText());
		patient.setLastName(lastName.getText());
		patient.setGender((String)gender.getSelectedItem());
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		birthday.setText(patient.setBirthday()==null?"":sdf.format(patient.setBirthday()));
//		deceased.setText(patient.setDeceased() == null?"":sdf.format(patient.setDeceased()));
//		patient.setWeight(weight.setText());
		patient.setCurTerritory(((TerritoryComboBoxEntry)address.getSelectedItem()).getTerritory());
		patient.setCurStreet(street.getText());
		patient.setPhoneNumber1(phone.getText());
//		school.setText(patient.setSchool()== null?"":patient.setSchool().setSchoolName());
		patient.setOccupation(occupation.getText());
		patient.setMaritalStatus(maritalStatus.getText());
		patient.setSupportGroup(((SupportGroupComboBoxEntry)supportGroup.getSelectedItem()).getSupportGroup());
	}
	
	/**
	 * Loads all the current territories from the database for
	 * matching during the refactoring process
	 * @return 
	 */
	private ArrayList<Territory> loadTerritoryData() {
		ArrayList<Territory> territoryList = null;
		HashMap<Integer, Territory> territoryMap = new HashMap<Integer, Territory>();
		
		// Load in from DB
		Connection cn = null;
		
		try {
			cn = TerritoryDao.createMySqlConnection(false);
			
			territoryList = TerritoryDao.getTerritories("", TerritoryDao.FN_TERRITORY_TYPE + "," + TerritoryDao.FN_NAME, cn);
			
			TerritoryDao.closeConnection(cn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (territoryList == null) { return null; }
		
		// Build Hash Map
		for (Territory t:territoryList) {
			territoryMap.put(t.getTerritoryId(), t);
		}
		
		// Build Parent tree
		for (Territory t:territoryList) {
			territoryMap.put(t.getTerritoryId(), t);
			if (t.getParentTerritoryId() > 0) {
				t.setParent(territoryMap.get(t.getParentTerritoryId()));
			}
			
			// Lower case everything to make comparisons easier
			// Done here so we only have to do it once
//			t.setTerritoryName(t.getTerritoryName().toLowerCase().trim());
//			t.setTerritoryType(t.getTerritoryType().toLowerCase().trim());
		}
		
		return territoryList;
	}
	
	/**
	 * Loads all the current territories from the database for
	 * matching during the refactoring process
	 */
	public ArrayList<SupportGroup> loadSupportGroupData() {
		ArrayList<SupportGroup> supportGroups = null;
		
		// Load in from DB
		Connection cn = null;
		
		try {
			cn = SupportGroupDao.createMySqlConnection(false);
			
			supportGroups = SupportGroupDao.getSupportGroups("", "", cn);
			
			SupportGroupDao.closeConnection(cn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int ti = 0; ti < supportGroups.size(); ti++) {
			supportGroups.get(ti).setSupportGroupName(StringUtility.formatStringCaps(supportGroups.get(ti).getSupportGroupName().toLowerCase().trim()));
		}
		
		return supportGroups;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
