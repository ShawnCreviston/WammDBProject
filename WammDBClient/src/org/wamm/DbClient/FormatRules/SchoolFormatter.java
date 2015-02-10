package org.wamm.DbClient.FormatRules;

import org.wamm.DbClient.BusinessObjects.PatientImport;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class SchoolFormatter implements DataFormatRule {

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getSchool() == null ) { return; }
		if (patient.getSchool().getSchoolName() == null || 
				patient.getSchool().getSchoolName().length() == 0) { 
			return; 
			}
		
		patient.getSchool().setSchoolName(StringUtility.formatStringCaps(patient.getSchool().getSchoolName().toLowerCase().trim()));	
	}
}
