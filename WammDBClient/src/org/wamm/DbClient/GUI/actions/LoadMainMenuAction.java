package org.wamm.DbClient.GUI.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.wamm.DbClient.GUI.MainClient;
import org.wamm.DbClient.GUI.MenuMainScreen;

public class LoadMainMenuAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {
		final MenuMainScreen pls = new MenuMainScreen();
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
			e.printStackTrace();
		}
	}
}
