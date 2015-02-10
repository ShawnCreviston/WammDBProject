package org.wamm.DbClient.FormatRules;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.wamm.DbClient.BusinessObjects.PatientImport;
import org.wamm.DbClient.BusinessObjects.SupportGroup;
import org.wamm.DbClient.BusinessObjects.Territory;
import org.wamm.DbClient.Dao.SupportGroupDao;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class SupportGroupFormatter implements DataFormatRule {
	
	private ArrayList<SupportGroup> supportGroups;
	
	private ArrayList<Territory> ter;
	private HashMap<Integer, Territory> terMap;

	@Override
	public void refactorPatientData(PatientImport patient) {
		if (patient.getSupportGroup() == null ) { return; }
		if (patient.getSupportGroup().getSupportGroupName() == null || 
				patient.getSupportGroup().getSupportGroupName().length() == 0) { 
			return; 
			}
		
		SupportGroup matchGroup = null;
		
		for (int ti = 0; ti < supportGroups.size(); ti++) {
			SupportGroup testGroup = supportGroups.get(ti);
			
			double testSim = StringUtility.getSimilarity(patient.getSupportGroup().getSupportGroupName().toLowerCase(), testGroup.getSupportGroupName().toLowerCase().trim());
			
			if (testSim > .95) {
				matchGroup = testGroup;
				break;
			}
		}
		
		if (matchGroup == null) {
			// Add it to DB
			matchGroup = patient.getSupportGroup();
			Territory curTer = terMap.get(1);
			
			if (patient.getSupportGroup().getTerritory() != null) {
				String terName = patient.getSupportGroup().getTerritory().getTerritoryName().trim().toLowerCase();
				double curSim = 0;
				
				for (int ti = 0; ti < ter.size(); ti++) {
					Territory testTer = ter.get(ti);
					if (testTer == curTer) { continue; }

					double testSim = StringUtility.getSimilarity(terName, testTer.getTerritoryName());

					if (testSim > .95 && testSim > curSim) {
						// Better match
						//System.out.println(curSim+"% "+t.getTerritoryName()+">>>"+testSim+"% "+testTer.getTerritoryName());
						curTer = testTer;
						curSim = testSim;
					} else if (testSim > .75 && testSim > curSim) {
						testSim = StringUtility.getSimilarity(terName, testTer.getTerritoryName().replaceAll(" ", ""));

						if (testSim > .95 && testSim > curSim) {
							// Better match
							//System.out.println(curSim+"% "+t.getTerritoryName()+">>>"+testSim+"% "+testTer.getTerritoryName());
							curTer = testTer;
							curSim = testSim;
						}
					}
				}
			}
			
			matchGroup.setSupportGroupID(-1);
			matchGroup.setTerritory(curTer);
			matchGroup.setSupportGroupName(matchGroup.getSupportGroupName().trim());
			
//			Connection cn = null;
//			try {
//				cn = SupportGroupDao.createMySqlConnection(false);
//				
//				SupportGroupDao.insertSupportGroup(matchGroup, cn);
				
				supportGroups.add(matchGroup);
				
//				SupportGroupDao.closeConnection(cn);
//			} catch (SQLException e) {
//				e.printStackTrace();
//				SupportGroupDao.closeConnection(cn);
//			}
			
		} else {
			patient.setSupportGroup(matchGroup);
		}
	}
	
	/**
	 * Loads all the current territories from the database for
	 * matching during the refactoring process
	 */
	public void loadSupportGroupData() {
		supportGroups = null;
		
		// Load in from DB
		Connection cn = null;
		
		try {
			cn = SupportGroupDao.createMySqlConnection(false);
			
			supportGroups = SupportGroupDao.getSupportGroups("", "", cn);
			
			SupportGroupDao.closeConnection(cn);
		} catch (SQLException e) {
			e.printStackTrace();
			SupportGroupDao.closeConnection(cn);
		}
		
		for (int ti = 0; ti < supportGroups.size(); ti++) {
			SupportGroup sg = supportGroups.get(ti);
			if (sg.getTerritory() != null) {
				Territory ter = terMap.get(sg.getTerritory().getTerritoryId());
				if (ter != null) {
					sg.setTerritory(ter);
				}
			}
			//supportGroups.get(ti).setSupportGroupName(StringUtility.formatStringCaps(supportGroups.get(ti).getSupportGroupName().toLowerCase().trim()));
		}
	}

	/**
	 * @param ter the ter to set
	 */
	public void setTerritoryList(ArrayList<Territory> ter) {
		this.ter = ter;
	}

	/**
	 * @param terMap the terMap to set
	 */
	public void setTerritoryMap(HashMap<Integer, Territory> terMap) {
		this.terMap = terMap;
	}
}
