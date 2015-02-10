package org.wamm.DbClient.FormatRules;

import org.wamm.DbClient.BusinessObjects.PatientImport;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class MaritalStatusFormatter implements DataFormatRule {
	
	private final String married = "Married";
	private final String single = "Single";
	private final String widow = "Widow";
	private final String divorced = "Divorced";

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getMaritalStatus() == null || patient.getMaritalStatus().length() == 0) {
			return;
		}
		
		String ms = patient.getMaritalStatus().toLowerCase().trim();
		
//		String yearRegExp = "\\s*ye?a?r?s?";
//		Pattern p = Pattern.compile("divorce");
		
		if (ms.equals("m") || ms.equals("married")) {
			ms = married;
		} else if (ms.equals("s") || ms.indexOf("single") >-1 || ms.equals("separate")) {
			ms = single;
		} else if (ms.equals("w") || ms.indexOf("widow") >-1) {
			ms = widow;
		} else if (ms.equals("d") || ms.indexOf("divorce") > -1) {
			ms = divorced;
		} else {
			// unknown
			ms = "";
		}
		
		patient.setMaritalStatus(ms);
	}

}
