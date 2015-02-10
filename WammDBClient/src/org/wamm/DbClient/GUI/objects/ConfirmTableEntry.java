package org.wamm.DbClient.GUI.objects;

import java.text.SimpleDateFormat;

import org.wamm.DbClient.BusinessObjects.Patient;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ConfirmTableEntry {
	
	// Backing data model
	private Patient patient;
	
	public ConfirmTableEntry() {
		patient = null;
	}
	
	public Object getValueAt(int columnIndex) {
		if (patient == null) { return null; }
		switch (columnIndex) {
		case 0:
			return patient.getFirstName();
		case 1:
			return patient.getMiddleName();
		case 2:
			return patient.getLastName();
		case 3:
			return patient.getGender();
		case 4:
			if (patient.getBirthday()==null) {
				return null;
			} else {
				return (new SimpleDateFormat("dd-MM-yyyy")).format(patient.getBirthday());
			}
		case 5:
			if (patient.getRegisteredDate()==null) {
				return null;
			} else {
				return (new SimpleDateFormat("dd-MM-yyyy")).format(patient.getRegisteredDate());
			}
		case 6:
			return patient.getWeight();
		case 7:
			if (patient.getCurTerritory()==null) {
				return null;
			} else {
				return patient.getCurTerritory().getTerritoryName();
			}
		case 8:
			return patient.getCurStreet();
		case 9:
			if (patient.getPhoneNumber2() != null && patient.getPhoneNumber2().length() > 0) {
				return patient.getPhoneNumber1() + " / " + patient.getPhoneNumber2();
			}
			return patient.getPhoneNumber1();
		case 10:
			if (patient.getTreatments()==null || patient.getTreatments().size() == 0) {
				return null;
			} else {
				return patient.getTreatments().get(0).getTreatment();
			}
		case 11:
			if (patient.getTreatments()==null || patient.getTreatments().size() == 0) {
				return null;
			} else {
				return patient.getTreatments().get(0).getPlCode();
			}
		case 12:
			return patient.getMaritalStatus();
		case 13:
			if (patient.getSupportGroup()==null) {
				return null;
			} else {
				return patient.getSupportGroup().getSupportGroupName();
			}
		}
		
		// Invalid column
		return null;
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
