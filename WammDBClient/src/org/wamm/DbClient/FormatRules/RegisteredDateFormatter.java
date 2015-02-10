package org.wamm.DbClient.FormatRules;

import java.util.GregorianCalendar;

import org.wamm.DbClient.BusinessObjects.PatientImport;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class RegisteredDateFormatter implements DataFormatRule {

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getImportedDateRegistered() == null || patient.getImportedDateRegistered().length() == 0) {
			return;
		}
		
		String regDate = patient.getImportedDateRegistered().trim().toLowerCase().replaceAll(" ", "");
		//System.out.println("Imported bday: " + bday);

		GregorianCalendar gc = null;
		if (regDate.length() == 4) {
			// Year only
			gc = new GregorianCalendar();
			gc.set(GregorianCalendar.YEAR, Integer.parseInt(regDate));
			gc.set(GregorianCalendar.MONTH, 1);
			gc.set(GregorianCalendar.DAY_OF_MONTH, 1);
		} else {
			String splitDate[] = regDate.split("/");
			if (splitDate.length == 3) {
				// Could be year/month/day or day/month/year
				if (splitDate[0].length() == 4) {
					gc = new GregorianCalendar();
					gc.set(GregorianCalendar.YEAR, Integer.parseInt(splitDate[0]));
					gc.set(GregorianCalendar.MONTH, Integer.parseInt(splitDate[1]));
					gc.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(splitDate[2]));
				} else if (splitDate[2].length() == 4) {
					gc = new GregorianCalendar();
					gc.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
					gc.set(GregorianCalendar.MONTH, Integer.parseInt(splitDate[1]));
					gc.set(GregorianCalendar.YEAR, Integer.parseInt(splitDate[2]));
				}
			}
		}
		
		if (gc != null) {
			patient.setRegisteredDate(gc.getTime());
		}
	}
}
