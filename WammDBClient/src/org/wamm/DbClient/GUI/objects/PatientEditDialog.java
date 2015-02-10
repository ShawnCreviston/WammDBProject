package org.wamm.DbClient.GUI.objects;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.GUI.cards.PatientEditCard;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class PatientEditDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 7094204838555554034L;
	private PatientEditCard editCard;
	
	public PatientEditDialog() {
		int width = 300;
		int height = 400;
		
		setModal(true);
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(getBackground());
		contentPane.setLayout(new GridBagLayout());
		
		JLabel lbl = new JLabel("Edit Information");
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(lbl, gbc);
		
		editCard = new PatientEditCard();
		editCard.setupCard();
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		contentPane.add(editCard, gbc);
		
		JButton saveBtn = new JButton(" Save ");
		saveBtn.setMinimumSize(new Dimension(150,50));
		saveBtn.setPreferredSize(saveBtn.getMinimumSize());
		saveBtn.setSize(saveBtn.getMinimumSize());
		saveBtn.addActionListener(this);
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		contentPane.add(saveBtn, gbc);
		
		setMinimumSize(new Dimension(width,height));
		setPreferredSize(getMinimumSize());
		setSize(getLayout().minimumLayoutSize(editCard));
		//setLocation(location);
	}

	public void setForPatient(Patient patient) {
		editCard.setForPatient(patient);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		editCard.saveChangesToPatient();
		setVisible(false);
	}
	
}
