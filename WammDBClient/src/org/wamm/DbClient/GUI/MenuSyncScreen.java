package org.wamm.DbClient.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wamm.DbClient.GUI.actions.LoadMainMenuAction;

public class MenuSyncScreen extends JPanel implements ActionListener {

	private boolean setup;
	
	public MenuSyncScreen() {
		setup = false;
	}
	
	public void setupPanel() {
		if (!setup) {
			setup = true;
			
			this.setLayout(new GridBagLayout());
			
			JLabel lbl = new JLabel("Data Sync Tools");
			lbl.setFont(new Font("Calibri",Font.BOLD, 24));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,25,5);
			this.add(lbl, gbc);
			
			JButton menuBtn = new JButton("Database Syncronization");
			menuBtn.setActionCommand("dbSync");
			menuBtn.addActionListener(this);
			menuBtn.setFont(new Font("Calibri",Font.BOLD, 18));
			menuBtn.setEnabled(false);
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(5,5,1,5);
			this.add(menuBtn, gbc);
			
			lbl = new JLabel("Database Syncronization allows you to import a database file from the other office locations and merge them into the database your connected to.");
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(1,5,5,5);
			this.add(lbl, gbc);
			
			menuBtn = new JButton("Excel File Import");
			menuBtn.setActionCommand("excel");
			menuBtn.addActionListener(this);
			menuBtn.setFont(new Font("Calibri",Font.BOLD, 18));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(40,5,1,5);
			this.add(menuBtn, gbc);
			
			lbl = new JLabel("Import data from an excel file.");
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(1,5,5,5);
			this.add(lbl, gbc);
			
			menuBtn = new JButton("Main Menu");
			menuBtn.addActionListener(new LoadMainMenuAction());
			menuBtn.setFont(new Font("Calibri",Font.BOLD, 18));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(5,5,5,5);
			this.add(menuBtn, gbc);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String actionCommand = arg0.getActionCommand();
		
		if (actionCommand.equals("dbSync")) {
			
		} else if (actionCommand.equals("excel")) {
			final ImportFileScreen pls = new ImportFileScreen();
			pls.setupPanel();
			try {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						MainClient.getBasePanel().getWindow().getContentPane().setVisible(false);
						MainClient.getBasePanel().getWindow().setContentPane(pls);
						MainClient.getBasePanel().getWindow().invalidate();
					}
				});
			} catch (Exception e) {
				System.err.println("Error loading patient lookup screen");
				e.printStackTrace();
			}
		}
	}
}
