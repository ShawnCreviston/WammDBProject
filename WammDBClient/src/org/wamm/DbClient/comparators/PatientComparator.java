package org.wamm.DbClient.comparators;

import java.util.Comparator;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston, Eric Stephenson
 * WAMM
 */
public class PatientComparator implements Comparator<Patient> {
	
	public static PatientComparator SHARED_INSTANCE;
	
	public static PatientComparator getSharedInstance() {
		if (SHARED_INSTANCE == null) {
			SHARED_INSTANCE = new PatientComparator();
		}
		return SHARED_INSTANCE;
	}

	/**
	 * Return 0 if patients equal, returns positive for number of differences
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int compare(Patient p1, Patient p2) {
		// check null
		if (p1 == null || p2 == null) {
			return 10;
		}
		
		// objects are the same
		if (p1 == p2) {
			return 0;
		}
		
		if (p1.getLastName() == null || p2.getLastName() == null) {
			return 10;
		} else if (p1.getLastName().length() == 0 || p2.getLastName().length() == 0) {
			return 10;
		} else if (!p1.getLastName().equalsIgnoreCase(p2.getLastName())) {
			return 10;
		}
		
		if (p1.getFirstName() == null || p2.getFirstName() == null) {
			return 10;
		} else if (p1.getFirstName().length() == 0 || p2.getFirstName().length() == 0) {
			return 10;
		} else if (!p1.getFirstName().equalsIgnoreCase(p2.getFirstName())) {
			return 10;
		}
		
		if (p1.getGender() != null && p2.getGender() != null &&
				p1.getGender().length() > 0 && p2.getGender().length() > 0) {
			if (!p1.getGender().equalsIgnoreCase(p2.getGender())) {
				return 10;
			}
		}
		
		int differences = 0;
		
		// territory
		if (p1.getCurTerritory().getTerritoryId() == 1 || p1.getCurTerritory().getTerritoryId() == 1) {
			// Territory of the country, don't compare.
		} else if (p1.getCurTerritory().getTerritoryId() != p2.getCurTerritory().getTerritoryId()) {
			differences++;
		}
		
		// street
		String p1s = "";
		String p2s = "";
		if (p1.getCurStreet() != null) {
			p1s = p1.getCurStreet();
		}
		if (p2.getCurStreet() != null) {
			p2s = p2.getCurStreet();
		}
		if (StringUtility.getSimilarity(p1s.toLowerCase(),p2s.toLowerCase()) < 0.92) {
			differences++;
		}
		
		// birthday
		int p1b = 0;
		int p2b = 0;
		if (p1.getBirthday() != null) {
			p1b = p1.getBirthday().getYear();
		}
		if (p2.getBirthday() != null) {
			p2b = p2.getBirthday().getYear();
		}
		if (p1b != p2b) {
			differences++;
		}

		return differences;
	}

}
