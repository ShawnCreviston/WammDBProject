package org.wamm.DbClient.GUI.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.GUI.ImportFileScreen;
import org.wamm.DbClient.ValueObjects.UserSessionVO;
import org.wamm.DbClient.comparators.PatientComparator;
import org.wamm.DbClient.utilities.StringUtility;

/**
 * @author Shawn Creviston, Eric Stephenson
 * WAMM
 */
public class ConsolidateLocalAction implements ActionListener {

	private ImportFileScreen mainScreen;

	@Override
	public void actionPerformed(ActionEvent ae) {
		mainScreen.getConfirmScreen().displayStatusMessage("Checking for duplicates...");
		// Push loading onto separate thread so it doesn't lock up GUI updates
		Thread actionThread = new Thread(createRunnableAction());
		actionThread.start();

	}

	private Runnable createRunnableAction() {
		return new Runnable() {
			public void run() {
				ArrayList<Patient> pList = UserSessionVO.getSession().getPatientList();
				ArrayList<Patient> nonDuplicateList = new ArrayList<Patient>();
				PatientComparator comparator = new PatientComparator();

				fulllist: for(Patient p1 : pList) {
					for (Patient p2 : nonDuplicateList) {
						int result = comparator.compare(p1, p2);
						if (result == 0) {
//							System.out.println("Patients match perfectly:");
//							System.out.println("Patient: " + p1.getFirstName() + " "+ p1.getLastName() + " from "+ p1.getCurStreet() + " born on "+ 
//									(p1.getBirthday() !=null?p1.getBirthday().getYear():"")+" SG: "+p1.getSupportGroup().getSupportGroupName());
//							System.out.println("Patient: " + p2.getFirstName() + " "+ p2.getLastName() + " from "+ p2.getCurStreet() + " born on "+ 
//									(p2.getBirthday() !=null?p2.getBirthday().getYear():"")+" SG: "+p2.getSupportGroup().getSupportGroupName());
//							System.out.println("------------------");

							continue fulllist;
						} else if (result == 1) {
							// Matched, one miss matched piece of data though, try to determine which to keep.
//							System.out.println("Patients semi match:");
//							System.out.println("Patient: " + p1.getFirstName() + " "+ p1.getLastName() + " from "+ p1.getCurStreet() + " born on "+ 
//									getBdayYear(p1.getBirthday())+" SG: "+p1.getSupportGroup().getSupportGroupName());
//							System.out.println("Patient: " + p2.getFirstName() + " "+ p2.getLastName() + " from "+ p2.getCurStreet() + " born on "+ 
//									getBdayYear(p2.getBirthday())+" SG: "+p2.getSupportGroup().getSupportGroupName());
//							System.out.println("------------------");


							// See how close age is
							int p1b = getBdayYear(p1.getBirthday());
							int p2b = getBdayYear(p2.getBirthday());
							if (p1b < 1900 && p2b > 1900) {
								// Keep p2
								continue fulllist;
							} else if (p1b > 1900 && p2b < 1900) {
								// Keep p1
								nonDuplicateList.remove(p2);
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
									nonDuplicateList.remove(p2);
									break;
								} else if (p1s.length() < 1 && p2s.length() > 1) {
									continue fulllist;
								} else if (StringUtility.getSimilarity(p1s.toLowerCase(),p2s.toLowerCase()) > 0.90) {
									continue fulllist;
								}

							} else if (Math.abs(p1b-p2b) < 6) {
								if (p1b > p2b) {
									// Keep p1
									nonDuplicateList.remove(p2);
									break;
								}
								continue fulllist;
							}
						}
					}

					nonDuplicateList.add(p1);

					mainScreen.getConfirmScreen().displayStatusMessage("Checking for duplicates...("+(pList.size()-nonDuplicateList.size())+" found)");
				}


				mainScreen.getConfirmScreen().displayStatusMessage("Merged " + (pList.size()-nonDuplicateList.size()) + " people.    Click Next to check database for duplicate people");

				// update patients list
				pList.clear();
				pList.addAll(nonDuplicateList);

				mainScreen.getConfirmScreen().displayPatientList(pList);

				// Setup the Next step button action
				ConsolidateToDbAction consolidateDbAction = new ConsolidateToDbAction();
				consolidateDbAction.setMainScreen(mainScreen);
				mainScreen.getConfirmScreen().setNextAction(consolidateDbAction);
			}
		};
	}

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
