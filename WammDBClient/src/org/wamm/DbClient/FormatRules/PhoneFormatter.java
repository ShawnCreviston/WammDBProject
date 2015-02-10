package org.wamm.DbClient.FormatRules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wamm.DbClient.BusinessObjects.PatientImport;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class PhoneFormatter implements DataFormatRule {

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getPhoneNumber1() == null || patient.getPhoneNumber1().length() == 0) {
			return;
		}
		
		String phone1 = patient.getPhoneNumber1().toLowerCase().trim();
		String phone2 = null;
		
		if (phone1.equals("nil") || phone1.equals("n/a") || phone1.equals("na")) {
			phone1 = "";
		}
		
		if (phone1.indexOf("/") > -1) {
			// Tried to save two phone numbers
			String phoneNums[] = phone1.split("/");
			phone1 = phoneNums[0].trim();
			if (phoneNums.length > 1) {
				phone2 = phoneNums[1].trim();
			}
		}
		
		patient.setPhoneNumber1(formatPhone(phone1));
		if (phone2 != null) {
			patient.setPhoneNumber2(formatPhone(phone2));
		}
	}

	private String formatPhone(String phone) {
		StringBuilder newPhone = new StringBuilder();
		
		Pattern p = Pattern.compile("\\d");
		
		for (int ci = 0; ci < phone.length() && newPhone.length() < 12; ci++) {
			String digit = ""+phone.charAt(ci);
			Matcher m = p.matcher(digit);
			if (m.matches()) {
				newPhone.append(digit);
			}
		}
		
		return newPhone.toString();
	}

}
