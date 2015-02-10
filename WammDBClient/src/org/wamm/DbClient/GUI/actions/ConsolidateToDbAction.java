package org.wamm.DbClient.GUI.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.Dao.PatientAdultDao;
import org.wamm.DbClient.GUI.ImportFileScreen;
import org.wamm.DbClient.ValueObjects.UserSessionVO;
import org.wamm.DbClient.comparators.PatientComparator;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ConsolidateToDbAction implements ActionListener {

	private ImportFileScreen mainScreen;

	@Override
	public void actionPerformed(ActionEvent ae) {
		mainScreen.getConfirmScreen().displayStatusMessage("Checking for db duplicates...");
		// Push loading onto separate thread so it doesn't lock up GUI updates
		Thread actionThread = new Thread(createRunnableAction());
		actionThread.start();
	}

	private Runnable createRunnableAction() {
		return new Runnable() {
			public void run() {
				ArrayList<Patient> pList = UserSessionVO.getSession().getPatientList();
				ArrayList<Patient> nonDuplicateList = new ArrayList<>();
				ArrayList<Patient> dbList = null;
				PatientComparator comparator = new PatientComparator();

				// Load in from DB
				Connection cn = null;

				try {
					//HashMap<Integer, Territory> territoryMap = loadTerritoryData();
					//HashMap<Integer, SupportGroup> supportGroupMap = loadSupportGroupData();
					
					cn = PatientAdultDao.createMySqlConnection(true);

					// iterate through list
					fulllist: for(Patient p1 : pList) {
						try {
							dbList = PatientAdultDao.getPatientsByName(p1.getFirstName(), p1.getLastName(), cn);
							//dbList.addAll(PatientChildDao.getPatientsByName(p1.getFirstName(), p1.getLastName(), cn));
						} catch (SQLException se) {
							se.printStackTrace();
							continue;
						}
						
						for (Patient p2 : dbList) {
							// Populate data from static lists
							//p2.setCurTerritory(p2.getCurTerritory().getTerritoryId());
							
							// Compare
							int result = comparator.compare(p1, p2);
							if (result == 0) {
//								System.out.println("Patients match perfectly:");
//								System.out.println("Patient: " + p1.getFirstName() + " "+ p1.getLastName() + " from "+ p1.getCurStreet() + " born on "+ 
//										(p1.getBirthday() !=null?p1.getBirthday().getYear():""));
//								System.out.println("Patient: " + p2.getFirstName() + " "+ p2.getLastName() + " from "+ p2.getCurStreet() + " born on "+ 
//										(p2.getBirthday() !=null?p2.getBirthday().getYear():""));
//								System.out.println("------------------");

								continue fulllist;
							} else if (result == 1) {
								// Matched, one miss matched piece of data though, try to determine which to keep.
//								System.out.println("Patients semi match:");
//								System.out.println("Patient: " + p1.getFirstName() + " "+ p1.getLastName() + " from "+ p1.getCurStreet() + " born on "+ 
//										getBdayYear(p1.getBirthday()));
//								System.out.println("Patient: " + p2.getFirstName() + " "+ p2.getLastName() + " from "+ p2.getCurStreet() + " born on "+ 
//										getBdayYear(p2.getBirthday()));
//								System.out.println("------------------");


								// See how close age is
								int p1b = getBdayYear(p1.getBirthday());
								int p2b = getBdayYear(p2.getBirthday());
								if (p1b < 1900 && p2b > 1900) {
									// Keep p2
									continue fulllist;
								} else if (p1b > 1900 && p2b < 1900) {
									// Keep p1
									p1.setPatientId(p2.getPatientId());
									//dbList.remove(p2);
									break;
								}

								if (Math.abs(p1b-p2b) == 0) {
									// Check street
									String p1s = "";
									String p2s = "";
									if (p1.getCurStreet() != null) {
										p1s = p1.getCurStreet();
									}
									if (p2.getCurStreet() != null) {
										p2s = p2.getCurStreet();
									}

									if (p1s.length() > 1 && p2s.length() < 1) {
										// Keep p1
										p1.setPatientId(p2.getPatientId());
										dbList.remove(p2);
										break;
									} else if (p1s.length() < 1 && p2s.length() > 1) {
										continue fulllist;
									} else if (StringUtility.getSimilarity(p1s.toLowerCase(),p2s.toLowerCase()) > 0.90) {
										continue fulllist;
									}

								} else if (Math.abs(p1b-p2b) < 6) {
									if (p1b > p2b) {
										// Keep p1
										p1.setPatientId(p2.getPatientId());
										//dbList.remove(p2);
										break;
									}
									continue fulllist;
								}
							}
						}

						nonDuplicateList.add(p1);

						mainScreen.getConfirmScreen().displayStatusMessage("Checking for duplicates...("+(pList.size()-nonDuplicateList.size())+" found)");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				PatientAdultDao.closeConnection(cn);

				mainScreen.getConfirmScreen().displayStatusMessage("Merged " + (pList.size()-nonDuplicateList.size()) + " people.    Click Next to save new people to database");

				// update patients list
				pList.clear();
				pList.addAll(nonDuplicateList);

				mainScreen.getConfirmScreen().displayPatientList(pList);
				
				// Setup the Next step button action
				SaveToDbAction saveToDbAction = new SaveToDbAction();
				saveToDbAction.setMainScreen(mainScreen);
				mainScreen.getConfirmScreen().setNextAction(saveToDbAction);
			}
		};
	}
	
	/**
	 * Loads all the current territories from the database for
	 * matching during the refactoring process
	 * /
	private HashMap<Integer, Territory> loadTerritoryData() {
		ArrayList<Territory> ter = null;
		HashMap<Integer, Territory> terMap = new HashMap<Integer, Territory>();
		
		// Load in from DB
		Connection cn = null;
		
		try {
			cn = TerritoryDao.createMySqlConnection(false);
			
			ter = TerritoryDao.getTerritories("", "", cn);
			
			TerritoryDao.closeConnection(cn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (ter == null) { return null; }
		
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
		
		return terMap;
	}*/
	
	/**
	 * Loads all the current support groups from the database for
	 * matching during the refactoring process
	 * /
	private HashMap<Integer, SupportGroup> loadSupportGroupData() {
		ArrayList<SupportGroup> supportList = null;
		HashMap<Integer, SupportGroup> supportMap = new HashMap<Integer, SupportGroup>();
		
		// Load in from DB
		Connection cn = null;
		
		try {
			cn = SupportGroupDao.createMySqlConnection(false);
			
			supportList = SupportGroupDao.getSupportGroups("", "", cn);
			
			TerritoryDao.closeConnection(cn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (supportList == null) { return null; }
		
		// Build Hash Map
		for (SupportGroup sg:supportList) {
			supportMap.put(sg.getSupportGroupID(), sg);
		}
		
		return supportMap;
	}*/

	private int getBdayYear(Date date) {
		if (date != null) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			return gc.get(GregorianCalendar.YEAR);
		}
		return 0;
	}

	/**
	 * @param mainScreen the mainScreen to set
	 */
	public void setMainScreen(ImportFileScreen mainScreen) {
		this.mainScreen = mainScreen;
	}
}
