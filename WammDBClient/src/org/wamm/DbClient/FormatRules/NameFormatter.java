package org.wamm.DbClient.FormatRules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wamm.DbClient.BusinessObjects.PatientImport;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class NameFormatter implements DataFormatRule {

	@Override
	public void refactorPatientData(PatientImport patient) {
		String first = patient.getFirstName()==null?"":patient.getFirstName();
		String middle = patient.getMiddleName()==null?"":patient.getMiddleName();
		String last = patient.getLastName()==null?"":patient.getLastName();
		
		// Clean up
		first = first.toLowerCase().trim();
		middle = middle.toLowerCase().trim();
		last = last.toLowerCase().trim();
		
		// Rebuild into one string for rules
		String name = first+(middle.length()>0?" "+middle:"")+(last.length()>0?" "+last:"");
		
		first = null;
		middle = null;
		last = null;
		
		if (name.length() == 0) {return;}
		//System.out.println("Name: >>>" + name + "<<<");
		
		// Check for trailing person numbers
		Pattern p = Pattern.compile("\\s+\\d+$");
		Matcher m = p.matcher(name);
		
		if (m.find()) {
			//System.out.println("Found number at end");
			name = m.replaceAll("");
			//System.out.println("Name: >>>" + name + "<<<");
		}
		
		// Split into each name
		String names[] = name.split(" ");
		for (int ni = 0; ni < names.length; ni++) {
			if (ni == 0) {
				first = names[ni];
			} else if (ni == names.length-1) {
				last = names[ni];
			} else {
				if (names[ni].length() == 0) {
					continue;
				} else if (middle == null) {
					middle = names[ni]; 
				} else {
					middle += " " + names[ni];
				}
			}
		}
		
		if (first != null) {
			patient.setFirstName(StringUtility.formatStringCaps(first));
		}
		
		if (middle != null) {
			// For consistency in abbreviated middle names, add a . if missing
			// It appears most single initial middle names are followed by a .
			// in the files with few missing.
			if (middle.length() == 1) { middle += "."; }
			patient.setMiddleName(StringUtility.formatStringCaps(middle));
		}
		
		if (last != null) {
			patient.setLastName(StringUtility.formatStringCaps(last));
		}
	}
}
