package org.wamm.DbClient.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ImportFileScreen extends JPanel {

	// ----------------- Main Process Flow --------------------------- //
	// Have user define the file to be imported

	// Load into file specific object

	// Convert to patient object

	// Run rules on patient objects

	// Show imported list

	// Consolidate

	// Confirmation screen for consolidation

	// First stage import into database

	// Run compare on existing patients in SQL

	// Show matched results

	// commit final list of patients

	private FileSelectScreen fileSelectScreen;
	private ConfirmationScreen confirmScreen;

	public ImportFileScreen() {

	}

	public void setupPanel() {
		this.setLayout(new GridBagLayout());

		fileSelectScreen = new FileSelectScreen();
		fileSelectScreen.setMainScreen(this);
		fileSelectScreen.setupPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(5,5,5,5);
		this.add(fileSelectScreen, gbc);

		confirmScreen = new ConfirmationScreen();
//		confirmScreen.setMainScreen(this);
		confirmScreen.setupPanel();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.9;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(5,5,5,5);
		this.add(confirmScreen, gbc);
	}

	/**
	 * @return the fileSelectScreen
	 */
	public FileSelectScreen getFileSelectScreen() {
		return fileSelectScreen;
	}

	/**
	 * @return the confirmScreen
	 */
	public ConfirmationScreen getConfirmScreen() {
		return confirmScreen;
	}
}
