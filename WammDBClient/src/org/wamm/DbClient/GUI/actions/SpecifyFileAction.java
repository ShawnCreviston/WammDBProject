package org.wamm.DbClient.GUI.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.wamm.DbClient.BusinessEnums.FileTypes;
import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.BusinessObjects.PatientImport;
import org.wamm.DbClient.FileLoaders.ExcelFileLoader;
import org.wamm.DbClient.FileLoaders.NethipsExcelV2;
import org.wamm.DbClient.FileLoaders.NewRegListExcel;
import org.wamm.DbClient.FormatRules.BirthDateFormatter;
import org.wamm.DbClient.FormatRules.DataFormatRule;
import org.wamm.DbClient.FormatRules.GenderFormatter;
import org.wamm.DbClient.FormatRules.LocationFormatter;
import org.wamm.DbClient.FormatRules.MaritalStatusFormatter;
import org.wamm.DbClient.FormatRules.NameFormatter;
import org.wamm.DbClient.FormatRules.OccupationFormatter;
import org.wamm.DbClient.FormatRules.PhoneFormatter;
import org.wamm.DbClient.FormatRules.RegisteredDateFormatter;
import org.wamm.DbClient.FormatRules.SchoolFormatter;
import org.wamm.DbClient.FormatRules.SupportGroupFormatter;
import org.wamm.DbClient.FormatRules.WeightFormatter;
import org.wamm.DbClient.GUI.ImportFileScreen;
import org.wamm.DbClient.GUI.MainClient;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class SpecifyFileAction implements ActionListener {

	private ImportFileScreen mainScreen;
	private JComboBox<FileTypes> fts;

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		final JFileChooser jfc = new JFileChooser();

		if (jfc.showOpenDialog(MainClient.getBasePanel().getWindow()) == JFileChooser.APPROVE_OPTION) {
			UserSessionVO.getSession().setPatientList(null);
			mainScreen.getConfirmScreen().displayStatusMessage("Loading File...");
			// Push loading onto separate thread so it doesn't lock up GUI updates
			Thread actionThread = new Thread(createRunnableAction(jfc));
			actionThread.start();
		}
	}
	
	private Runnable createRunnableAction(final JFileChooser jfc) {
		return new Runnable() {
			public void run() {
				FileInputStream f = null;
				try {
					f = new FileInputStream(jfc.getSelectedFile());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				// Load into file specific object
				final ExcelFileLoader efl;
				if ((FileTypes)fts.getSelectedItem() ==  FileTypes.NETHIPS_EXCEL_FILE) {
					efl = new NethipsExcelV2();	
				} else if ((FileTypes)fts.getSelectedItem() ==  FileTypes.NEW_REG_EXCEL_FILE) {
					efl = new NewRegListExcel();			
				} else {
					// Invalid file type, show error message
					return;
				}

				// Convert to patient object
				try {
					mainScreen.getConfirmScreen().displayStatusMessage("Reading in data...");
					efl.setFile(f);
					
					// Running this on a new thread allows us to monitor the status below and update the GUI
					Thread fileReadThread = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								efl.loadFileData();
							} catch (Exception e1) {
								mainScreen.getConfirmScreen().displayStatusMessage("Failed to load File: "+e1.getMessage());
								System.err.println("Failed to load file");
								e1.printStackTrace();
							} finally {
							}
						}
					});
					ArrayList<PatientImport> pList = efl.getFileData();
					fileReadThread.start();
					while (fileReadThread.isAlive()) {
						mainScreen.getConfirmScreen().displayStatusMessage("Reading in data...("+pList.size()+")");
						Thread.sleep(100);
					}
					
					f = null;

					mainScreen.getConfirmScreen().displayStatusMessage("Cleaning up data...");

					// Create formatting rules to run
					ArrayList<DataFormatRule> rules = new ArrayList<DataFormatRule>();
					
					LocationFormatter lf = new LocationFormatter();
					// Load in current list of territories for matching and use in other rules
					lf.loadTerritoryData();
					
					rules.add(new NameFormatter());
					rules.add(new BirthDateFormatter());
					rules.add(new RegisteredDateFormatter());
					rules.add(new GenderFormatter());
					rules.add(new WeightFormatter());
					rules.add(new OccupationFormatter());
					rules.add(new MaritalStatusFormatter());
					rules.add(new PhoneFormatter());
					
					SupportGroupFormatter sgf = new SupportGroupFormatter();
					sgf.setTerritoryList(lf.getTerritoryList());
					sgf.setTerritoryMap(lf.getTerritoryMap());
					sgf.loadSupportGroupData();
					rules.add(sgf);
					
					rules.add(new SchoolFormatter());
					
					// I added this one last so that maybe we can use the other info to help determine location information.
					rules.add(lf);

					// Run rules on patient objects
					for (int pi = 0; pi < pList.size(); pi++) {
						for (int ri = 0; ri < rules.size(); ri++) {
							mainScreen.getConfirmScreen().displayStatusMessage("Cleaning up data...("+pi+"/"+pList.size()+")");
							try {
								((DataFormatRule) rules.get(ri)).refactorPatientData(pList.get(pi));
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
					
					for (int pi = 0; pi < pList.size(); pi++) {
						// Strip out missing name people
						PatientImport patient = pList.get(pi);
						if (patient.getFirstName() == null || patient.getFirstName().length() < 2) {
							pList.remove(pi);
							pi--;
							continue;
						} else if (patient.getLastName() == null || patient.getLastName().length() < 2) {
							pList.remove(pi);
							pi--;
							continue;
						}
					}

					// Show imported list
					UserSessionVO.getSession().setPatientList(new ArrayList<Patient>());
					UserSessionVO.getSession().getPatientList().addAll(pList);
					mainScreen.getConfirmScreen().displayPatientList(UserSessionVO.getSession().getPatientList());
					
					// Setup the Next step button action
					ConsolidateLocalAction consolidateAction = new ConsolidateLocalAction();
					consolidateAction.setMainScreen(mainScreen);
					mainScreen.getConfirmScreen().setNextAction(consolidateAction);
					mainScreen.getConfirmScreen().setCancelAction(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							UserSessionVO.getSession().getPatientList().clear();
							mainScreen.getConfirmScreen().displayPatientList(UserSessionVO.getSession().getPatientList());
							mainScreen.getConfirmScreen().displayStatusMessage("Please Select a File Type Above...");
							mainScreen.getConfirmScreen().setNextAction(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									JOptionPane.showMessageDialog(MainClient.getBasePanel().getWindow(), "Please select a file first");
								}
							});
							
							mainScreen.getConfirmScreen().setCancelAction(new LoadMainMenuAction(), "Menu");
						}
					}, "Cancel");
					mainScreen.getConfirmScreen().displayStatusMessage(pList.size() + " people loaded.    Click Next to check file for duplicate people");
				} catch (Exception e1) {
					mainScreen.getConfirmScreen().displayStatusMessage("Failed to load File: "+e1.getMessage());
					System.err.println("Failed to load file");
					e1.printStackTrace();
				} finally {
					f = null;
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

	public void setComboBox(JComboBox<FileTypes> fts) {
		this.fts = fts;
	}
}
