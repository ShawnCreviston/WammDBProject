package org.wamm.DbClient.GUI.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.BusinessObjects.SupportSystem;
import org.wamm.DbClient.BusinessObjects.Treatment;
import org.wamm.DbClient.Dao.PatientAdultDao;
import org.wamm.DbClient.Dao.SupportGroupDao;
import org.wamm.DbClient.Dao.SupportSystemDao;
import org.wamm.DbClient.Dao.TreatmentDao;
import org.wamm.DbClient.GUI.ImportFileScreen;
import org.wamm.DbClient.GUI.MainClient;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class SaveToDbAction implements ActionListener {

	private ImportFileScreen mainScreen;

	@Override
	public void actionPerformed(ActionEvent ae) {
		mainScreen.getConfirmScreen().displayStatusMessage("Saving to database...");
		// Push loading onto separate thread so it doesn't lock up GUI updates
		Thread actionThread = new Thread(createRunnableAction());
		actionThread.start();

	}

	private Runnable createRunnableAction() {
		return new Runnable() {
			public void run() {
				ArrayList<Patient> pList = UserSessionVO.getSession().getPatientList();
				ArrayList<Patient> failList = new ArrayList<Patient>();

				// Load in from DB
				Connection cn = null;

				try {
					cn = PatientAdultDao.createMySqlConnection(true);
					boolean patientDeleted = false;
					

					// iterate through list
					for (int pi = 0; pi < pList.size(); pi++) {
						Patient patient = pList.get(pi);
						patientDeleted = false;
						PatientAdultDao.startTransaction(cn);
						try {
							if (patient.getPatientId() > 0) {
								// Consolidated with existing patient, delete and over write
								PatientAdultDao.deletePatient(patient.getPatientId(), cn);
								patientDeleted = true;
							}
							PatientAdultDao.insertPatient(patient, cn);
							
							if (!patientDeleted && patient.getPatientId() > 0) {
								if (patient.getSupportGroup() != null) {
									if (patient.getSupportGroup().getSupportGroupID() < 0) {
										// New group
										SupportGroupDao.insertSupportGroup(patient.getSupportGroup(), cn);
									}
									if (patient.getSupportGroup().getSupportGroupID() > 0) {
										try {
											SupportSystemDao.insertSupportSystem(new SupportSystem(patient.getPatientId(), patient.getSupportGroup().getSupportGroupID()), cn);
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
								}
							
								if (patient.getTreatments() != null) {
									for (Treatment t: patient.getTreatments()) {
										t.setInternalId(patient.getPatientId());
										TreatmentDao.insertTreatment(t, cn);
									}
								}
							}
							PatientAdultDao.commitChanges(cn);
						} catch (SQLException e) {
							e.printStackTrace();
							failList.add(patient);
							try {
								PatientAdultDao.rollbackChanges(cn);
							} catch (SQLException e1) {}
						}
						
						mainScreen.getConfirmScreen().displayStatusMessage("Saving data...("+pi+"/"+pList.size()+")");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}

				PatientAdultDao.closeConnection(cn);

				// update patients list
				if (failList.size() > 0) {
					System.out.println("The above insert statement errors should be fixable by the user within the application.");
					pList.clear();
					pList.addAll(failList);
					mainScreen.getConfirmScreen().displayStatusMessage(failList.size() + " failed to saved.    Please fix them above and click next to re-save...");

					mainScreen.getConfirmScreen().displayPatientList(pList);

					JOptionPane.showMessageDialog(MainClient.getBasePanel().getWindow(), "Error saving the displayed people.\nThese people were not saved.\nFix and click next to try again.");
				} else {
					mainScreen.getConfirmScreen().displayStatusMessage("All people saved.    Please Select a New File Above...");
					pList.clear();
					mainScreen.getConfirmScreen().displayPatientList(pList);
					
					// Setup the Next step button action
					mainScreen.getConfirmScreen().setNextAction(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(MainClient.getBasePanel().getWindow(), "Please select a file first");
						}
					});
					mainScreen.getConfirmScreen().setCancelAction(new LoadMainMenuAction(), "Menu");
				}
			}
		};
	}

	/**
	 * @param mainScreen the mainScreen to set
	 */
	public void setMainScreen(ImportFileScreen mainScreen) {
		this.mainScreen = mainScreen;
	}
}
