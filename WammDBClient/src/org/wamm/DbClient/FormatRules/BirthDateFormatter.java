package org.wamm.DbClient.FormatRules;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wamm.DbClient.BusinessObjects.PatientImport;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class BirthDateFormatter implements DataFormatRule {

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getImportedBirthday() == null || patient.getImportedBirthday().length() == 0) {
			if (patient.getBirthday() == null) {
				patient.setBirthday(new GregorianCalendar(1897, 1, 1).getTime());
			}
			return;
		}
		
		String bday = patient.getImportedBirthday().trim().toLowerCase();
		//System.out.println("Imported bday: " + bday);

		GregorianCalendar gc = null;

		if (bday.equals("adult")) {
			// Exact age not known, default to 1900
			gc =  new GregorianCalendar(1898, 1, 1);
			//System.out.println("Found Adult");
		} else if (bday.equals("child") || bday.equals("minor")) {
			// TODO Not sure what default child age to use
			gc =  new GregorianCalendar(1899, 1, 1);
			//System.out.println("Found Child");
		} else {

			// Check for years
			Pattern p = Pattern.compile("^\\d+[\\.{1}\\d+]?$");
			Matcher m = p.matcher(bday);

			int ageInYears = -1;
			
			if (m.matches()) {
				// bday specified in years, calculate date
				// Parse with Double object as excel may bring an age in as a decimal
				ageInYears = (int)Double.parseDouble(bday);
				//System.out.println("Found age in years:" + ageInYears);
			} else {
				// No age found yet, try with years label
				String yearRegExp = "\\s*ye?a?r?s?";
				p = Pattern.compile("\\d+"+yearRegExp);
				
				m = p.matcher(bday);
				
				if (m.matches()) {
					// bday specified in years with year label
					
					// Strip label
					p = Pattern.compile(yearRegExp);
					m = p.matcher(bday);
					bday = m.replaceAll("");
					
					// Parse with Double object as excel may bring an age in as a decimal
					ageInYears = (int)Double.parseDouble(bday);
					//System.out.println("Found age in years:" + ageInYears);
				}
			}
			
			if (ageInYears > -1) {
				// Found an age based on years
				gc = new GregorianCalendar();
				gc.add(GregorianCalendar.YEAR, -ageInYears);
			} else {
				String monthRegExp = "\\s*mo?n?t?h?s?";
				p = Pattern.compile("\\d+"+monthRegExp);
				
				// No age found yet, try with months label
				m = p.matcher(bday);
				
				if (m.matches()) {
					// bday specified in months with label
					
					// Strip label
					p = Pattern.compile(monthRegExp);
					m = p.matcher(bday);
					bday = m.replaceAll("");
					
					// Parse with Double object as excel may bring an age in as a decimal
					int ageInMonths = (int)Double.parseDouble(bday);
					//System.out.println("Found age in months:" + ageInMonths);
					
					gc = new GregorianCalendar();
					gc.add(GregorianCalendar.MONTH, -ageInMonths);
				}
			}
		}
		
		if (gc != null) {
			patient.setBirthday(gc.getTime());
			//System.out.println("Calculated birthdate: " + patient.getBirthday().toString());
		}
	}
}
