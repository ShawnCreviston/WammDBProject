package org.wamm.DbClient.GUI.cards;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wamm.DbClient.BusinessObjects.Patient;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class ComparePatientCard extends JPanel {

	private static final long serialVersionUID = -7800937382805686047L;
	private Patient patientA;
	private Patient patientB;
	
	private boolean setup;
	
	public ComparePatientCard() {
		setup = false;
	}
	
	public void setupCard() {
		if (setup) return;
		setup = true;
		
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JLabel header = new JLabel("A duplicate entry was found, select which person to keep. Or keep both if you are sure these are separate people.");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(header, gbc);
		
		PatientEditCard patientACard = new PatientEditCard();
		patientACard.setPatient(patientA);
		patientACard.setupCard();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(patientACard, gbc);
		
		PatientEditCard patientBCard = new PatientEditCard();
		patientBCard.setPatient(patientB);
		patientBCard.setupCard();
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(patientBCard, gbc);
	}

	/**
	 * @return the patientA
	 */
	public Patient getPatientA() {
		return patientA;
	}

	/**
	 * @param patientA the patientA to set
	 */
	public void setPatientA(Patient patientA) {
		this.patientA = patientA;
	}

	/**
	 * @return the patientB
	 */
	public Patient getPatientB() {
		return patientB;
	}

	/**
	 * @param patientB the patientB to set
	 */
	public void setPatientB(Patient patientB) {
		this.patientB = patientB;
	}
}
