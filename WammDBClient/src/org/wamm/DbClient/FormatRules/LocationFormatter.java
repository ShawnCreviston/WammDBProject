package org.wamm.DbClient.FormatRules;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.wamm.DbClient.BusinessObjects.PatientImport;
import org.wamm.DbClient.BusinessObjects.Territory;
import org.wamm.DbClient.Dao.TerritoryDao;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class LocationFormatter implements DataFormatRule {
	
	private ArrayList<Territory> ter;
	private HashMap<Integer, Territory> terMap;

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getImportedAddress() == null) {
			if (patient.getCurStreet() != null) {
				Territory ct = patient.getCurTerritory();
				patient.setImportedAddress(patient.getCurStreet() + 
						(ct!=null?(ct.getTerritoryName()!= null && ct.getTerritoryName().length() > 0? "," +ct.getTerritoryName():""):""));
			} else {
				patient.setImportedAddress("");
			}
		}
		
		// Try to identify and build a location for the patient
		// Use School, Support Group, and Address info to narrow
		// down to most accurate location.
		String ia = patient.getImportedAddress().toLowerCase().trim();
		//System.out.println("Imported Address: " + ia);
		
		Territory curTer = terMap.get(1);
		patient.setCurTerritory(curTer);
		if (ia.length() == 0) {
			// Nothing brought in
			patient.setCurStreet("");
			return;
		}
		double curSim = 0;
		
		String street = ia;
		
		// Break into address sets if possible
		String splitChar = ",";
		String addSets[] = ia.split(splitChar);
		if (addSets.length == 1) {
			splitChar = "//";
			addSets = ia.split(splitChar);
		}
		if (addSets.length == 1) {
			splitChar = "-";
			addSets = ia.split(splitChar);
		}
		
		if (addSets.length > 1) {
			// Multiple sets, hopefully a town or village on second address line
			// Start at end of sets
			for (int asi = addSets.length-1; asi > 0; asi--) {
				String testAdd = addSets[asi].trim();
				
				// Default to 20%
				curSim = 0.2;
				
				for (int ti = 0; ti < ter.size(); ti++) {
					Territory testTer = ter.get(ti);
					if (testTer == curTer) { continue; }
					
					double testSim = StringUtility.getSimilarity(testAdd, testTer.getTerritoryName());
					
					if (testSim > .75 && testSim > curSim) {
						// Better match
						//System.out.println(curSim+"% "+t.getTerritoryName()+">>>"+testSim+"% "+testTer.getTerritoryName());
						curTer = testTer;
						curSim = testSim;
					}
				}
				
				if (asi == addSets.length-1 && curTer != patient.getCurTerritory()) {
					street = street.replace(splitChar+addSets[asi], "");
				}
			}
		} else {
			// Single address string
			curSim = 0.2;
			for (int ti = 0; ti < ter.size(); ti++) {
				Territory testTer = ter.get(ti);
				if (testTer == curTer) { continue; }

				double testSim = StringUtility.getSimilarity(ia, testTer.getTerritoryName());

				if (testSim > .9 && testSim > curSim) {
					// Better match
					//System.out.println(curSim+"% "+t.getTerritoryName()+">>>"+testSim+"% "+testTer.getTerritoryName());
					curTer = testTer;
					curSim = testSim;
				}
			}
			
			if (patient.getCurTerritory() == curTer) {
				splitChar = " ";
				addSets = ia.split(splitChar);
				
				for (int asi = addSets.length-1; asi >= 0; asi--) {
					String testAdd = addSets[asi].trim();
					
					// Default to 20%
					curSim = 0.2;
					
					for (int ti = 0; ti < ter.size(); ti++) {
						Territory testTer = ter.get(ti);
						if (testTer == curTer) { continue; }
						
						double testSim = StringUtility.getSimilarity(testAdd, testTer.getTerritoryName());
						
						if (testSim > .75 && testSim > curSim) {
							// Better match
							//System.out.println(curSim+"% "+t.getTerritoryName()+">>>"+testSim+"% "+testTer.getTerritoryName());
							curTer = testTer;
							curSim = testSim;
						}
					}
					
					if (addSets.length > 1 && asi == addSets.length-1 && curTer != patient.getCurTerritory()) {
						street = street.replace(splitChar+addSets[asi], "");
					}
				}
			}
			
			if (patient.getCurTerritory() == curTer) {
				ia = " "+ia+" ";
				for (int ti = 0; ti < ter.size(); ti++) {
					Territory testTer = ter.get(ti);
					if (testTer == curTer) { continue; }

					if (ia.contains(" "+testTer.getTerritoryName()+" ")) {
						curTer = testTer;
						break;
					}
				}
				ia = ia.trim();
			}
			
			if (patient.getCurTerritory() == curTer) {
				if (patient.getSupportGroup() != null && patient.getSupportGroup().getTerritory() != null) {
					if (patient.getSupportGroup().getTerritory().getTerritoryId() > 0) {
						curTer = terMap.get(patient.getSupportGroup().getTerritory().getTerritoryId());
					}
				}
			}
		}
		
		patient.setCurTerritory(curTer);
		patient.setCurStreet(street);
		patient.setImportedAddress(null);
		//System.out.println("Final Address: >>" + street +"<<, >>"+ t.getTerritoryName()+"<<");
	}
	
	/**
	 * Loads all the current territories from the database for
	 * matching during the refactoring process
	 */
	public void loadTerritoryData() {
		ter = null;
		terMap = new HashMap<Integer, Territory>();
		
		// Load in from DB
		Connection cn = null;
		
		try {
			cn = TerritoryDao.createMySqlConnection(false);
			
			ter = TerritoryDao.getTerritories("", "", cn);
			
			TerritoryDao.closeConnection(cn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (ter == null) { return; }
		
		// Build Hash Map
		for (Territory t:ter) {
			terMap.put(t.getTerritoryId(), t);
		}
		
		// Build Parent tree
		for (Territory t:ter) {
			terMap.put(t.getTerritoryId(), t);
			if (t.getParentTerritoryId() > 0) {
				t.setParent(terMap.get(t.getParentTerritoryId()));
			}
			
			// Lower case everything to make comparisons easier
			// Done here so we only have to do it once
			t.setTerritoryName(t.getTerritoryName().toLowerCase().trim());
			t.setTerritoryType(t.getTerritoryType().toLowerCase().trim());
		}
		
	}

	/**
	 * @return the ter
	 */
	public ArrayList<Territory> getTerritoryList() {
		return ter;
	}

//	/**
//	 * @param ter the ter to set
//	 */
//	public void setTerritories(ArrayList<Territory> ter) {
//		this.ter = ter;
//	}

	/**
	 * @return the terMap
	 */
	public HashMap<Integer, Territory> getTerritoryMap() {
		return terMap;
	}

}
