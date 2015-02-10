package org.wamm.DbClient.FormatRules;

import org.wamm.DbClient.BusinessObjects.PatientImport;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class OccupationFormatter implements DataFormatRule {

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getOccupation() == null ) { return; }
		
		String occupation = patient.getOccupation().trim().toLowerCase();
		
		if (occupation.equals("nil") || occupation.equals("n/a") || occupation.equals("na") || occupation.length() < 3) {
			patient.setOccupation(null);
			return;
		}
		
		patient.setOccupation(StringUtility.formatStringCaps(occupation.toLowerCase().trim()));
	}

}
