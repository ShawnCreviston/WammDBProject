package org.wamm.DbClient.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wamm.DbClient.BusinessEnums.FileTypes;
import org.wamm.DbClient.GUI.actions.SpecifyFileAction;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class FileSelectScreen extends JPanel {

	private static final long serialVersionUID = 315343875635557789L;

	private ImportFileScreen mainScreen;
	
	private boolean setup;
	
	public FileSelectScreen() {
		setup = false;
	}
	
	public void setupPanel() {
		if (!setup) {
			setup = true;
			//this.setBackground(Color.BLUE);
			this.setLayout(new GridBagLayout());
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			JLabel lbl = new JLabel("Select the File to Import:");
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 0.1;
			gbc.weighty = 0.1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			this.add(lbl, gbc);
			
			JComboBox<FileTypes> fts = new JComboBox<>();
			fts.addItem(FileTypes.NETHIPS_EXCEL_FILE);
			fts.addItem(FileTypes.NEW_REG_EXCEL_FILE);
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 0.1;
			gbc.weighty = 0.1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			this.add(fts, gbc);
			
			JButton b = new JButton("Browse For File...");
			SpecifyFileAction sfa = new SpecifyFileAction();
			sfa.setMainScreen(mainScreen);
			sfa.setComboBox(fts);
			b.addActionListener(sfa);
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.weightx = 0.1;
			gbc.weighty = 0.1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			this.add(b, gbc);
		}
	}

	/**
	 * @param mainScreen the mainScreen to set
	 */
	public void setMainScreen(ImportFileScreen mainScreen) {
		this.mainScreen = mainScreen;
	}
}
