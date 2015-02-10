package org.wamm.DbClient.FormatRules;

import org.wamm.DbClient.BusinessObjects.PatientImport;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class GenderFormatter implements DataFormatRule {

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getGender() == null) {
			return;
		}
		
		String gender = patient.getGender().toLowerCase().trim();
		
//		if (gender.equals("male")) {
//			gender = "m";
//		} else if (gender.equals("female")) {
//			gender = "f";
//		}
		
		if (gender.length() > 1) {
			gender = gender.substring(0,1);
		}
		
		if (!gender.equals("f") && !gender.equals("m")) {
			gender = "";
		}
		
		patient.setGender(gender.toUpperCase());	
	}
}
