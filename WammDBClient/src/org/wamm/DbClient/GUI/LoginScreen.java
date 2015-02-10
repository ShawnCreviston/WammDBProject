package org.wamm.DbClient.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.wamm.DbClient.BusinessObjects.ClientUserBO;
import org.wamm.DbClient.Dao.UsersDao;
import org.wamm.DbClient.Globals.PropertyGlobals;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

public class LoginScreen extends JPanel implements ActionListener {

	private JTextField username;
	private JPasswordField userpass;
	
	private boolean setup;
	
	public LoginScreen() {
		setup = false;
	}
	
	public void setupPanel() {
		if (!setup) {
			setup = true;
			
			this.setLayout(new GridBagLayout());
			
			JLabel lbl = new JLabel("Nethips Client System");
			lbl.setFont(new Font("Calibri",Font.BOLD, 36));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			gbc.weighty = 0.1;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(5,5,5,5);
			this.add(lbl, gbc);
			
			lbl = new JLabel("Please Login");
			lbl.setFont(new Font("Calibri",Font.BOLD, 20));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.insets = new Insets(5,5,5,5);
			this.add(lbl, gbc);
			
			lbl = new JLabel("Username: ");
			lbl.setFont(new Font("Calibri",Font.BOLD, 18));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.5;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTHEAST;
			this.add(lbl, gbc);
			
			username = new JTextField();
			username.setColumns(20);
			username.setFont(new Font("Calibri",Font.BOLD, 18));
			username.setText("clientTest");
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.5;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			this.add(username, gbc);
			
			lbl = new JLabel("Password: ");
			lbl.setFont(new Font("Calibri",Font.BOLD, 18));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.5;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTHEAST;
			this.add(lbl, gbc);
			
			userpass = new JPasswordField();
			userpass.setColumns(20);
			userpass.setFont(new Font("Calibri",Font.BOLD, 18));
			userpass.setText("testtest");
			gbc = new GridBagConstraints();
			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0.5;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			this.add(userpass, gbc);
			
			JButton loginBtn = new JButton("Login");
			loginBtn.setActionCommand("Login");
			loginBtn.addActionListener(this);
			loginBtn.setFont(new Font("Calibri",Font.BOLD, 18));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 2;
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 0;
			gbc.weighty = 0.1;
			gbc.anchor = GridBagConstraints.NORTH;
			this.add(loginBtn, gbc);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String dbUserName = username.getText();
		// TODO Use char array instead of string for security.
		String dbuserpass = new String(userpass.getPassword());
		
		UserSessionVO.getSession().getDbKeys().setTableKey(PropertyGlobals.PROPERTY_DB_MYSQL_USERNAME, dbUserName);
		UserSessionVO.getSession().getDbKeys().setTableKey(PropertyGlobals.PROPERTY_DB_MYSQL_USERPASS, dbuserpass);
		
		Connection cn = null;

		try {
			cn = UsersDao.createMySqlConnection(true);
			
			ClientUserBO cubo = UsersDao.getUserByUsername(dbUserName, cn);
			if (cubo == null) {
				JOptionPane.showMessageDialog(MainClient.getBasePanel().getWindow(), "Invalid username/password/rights.");
			} else {
				// Load main menu screen
				UserSessionVO.getSession().setClientUser(cubo);
				
				final MenuMainScreen menuMainScreen = new MenuMainScreen();
				menuMainScreen.setupPanel();
				try {
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							MainClient.getBasePanel().getWindow().getContentPane().setVisible(false);
							MainClient.getBasePanel().getWindow().setContentPane(menuMainScreen);
							MainClient.getBasePanel().getWindow().invalidate();
						}
					});
				} catch (Exception e) {
					System.err.println("Error starting GUI");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
