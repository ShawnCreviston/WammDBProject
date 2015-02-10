package org.wamm.DbClient.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.wamm.DbClient.Dao.DBKeys;
import org.wamm.DbClient.Globals.PropertyGlobals;
import org.wamm.DbClient.ValueObjects.UserSessionVO;
import org.wamm.DbClient.utilities.CipherUtility;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class MainClient {
	
	private static MainClient bp = null;
	public static MainClient getBasePanel() {
		return bp;
	}

	private JFrame window;

	public MainClient() {
		if (bp == null) bp = this;
	}

	public void setupPanel() {
		window = new JFrame("Nethips DB Patient System");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1024, 768);

		JPanel contentPane = new JPanel();
		window.setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		LoginScreen loginScreen = new LoginScreen();
		loginScreen.setupPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5,5,5,5);
		contentPane.add(loginScreen, gbc);

		window.validate();
		window.setVisible(true);
	}

	public void init() {
		// Use invoke to push the method call onto the Swing thread instead
		// of running it off this main thread.
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					setupPanel();
				}
			});
		} catch (Exception e) {
			System.err.println("Error starting GUI");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Nethips DB Client");
		//tempRun();

		if (loadAppConfigurations(args)) {
			MainClient mi = new MainClient();
			// Setup and start gui
			mi.init();
		}
	}

	private static boolean loadAppConfigurations(String[] args) {
		// Set default values where needed
		System.setProperty(PropertyGlobals.PROPERTY_DB_MYSQL_IP, "localhost:3306");
		
		String dbid = null;

		for (int a = 0; a < args.length; a++) {
			String key = args[a];
			String value;
			if (key.contains("=")) {
				String values[] = key.split("=");
				key = values[0].trim();
				value = values[1].trim();
			} else {
				value = "";
			}

			if (key.toLowerCase().equals("-dbid")) {
				dbid = value;
				System.out.println("DB ID Set");
			} else if (key.toLowerCase().equals("-dbkeyfile")) {
				System.setProperty(PropertyGlobals.PROPERTY_DB_KEY_FILE, value);
				System.out.println("DB Key File: "+System.getProperty(PropertyGlobals.PROPERTY_DB_KEY_FILE));
			}
		}
		
		if (dbid == null || System.getProperty(PropertyGlobals.PROPERTY_DB_KEY_FILE) == null) {
			System.out.println("DB ID or DB Key File path missing");
			return false;
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(System.getProperty(PropertyGlobals.PROPERTY_DB_KEY_FILE)));
			String line = null;
			boolean encrypted = false;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				
				if (line.equals("encrypted")) {
					encrypted = true;
					continue;
				}
				
				if (encrypted) {
					String keys = new String(CipherUtility.decrypt(line, dbid.getBytes()));
					String values[] = keys.split("=");
					String key = values[0].trim();
					String value = values[1].trim();
					
					DBKeys dbKeys = UserSessionVO.getSession().getDbKeys();

					if (key.toLowerCase().equals("treatment")) {
						dbKeys.setTableKey(PropertyGlobals.PROPERTY_DBT_KEY_TREATMENT, value);
					} else if (key.toLowerCase().equals("plhiv")) {
						dbKeys.setTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PLHIV, value);
					} else if (key.toLowerCase().equals("supportsystem")) {
						dbKeys.setTableKey(PropertyGlobals.PROPERTY_DBT_KEY_SUPSYS, value);
					} else if (key.toLowerCase().equals("supporttransaction")) {
						dbKeys.setTableKey(PropertyGlobals.PROPERTY_DBT_KEY_SUPTRAN, value);
					}  else if (key.toLowerCase().equals("patient")) {
						dbKeys.setTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT, value);
					}  else if (key.toLowerCase().equals("users")) {
						dbKeys.setTableKey(PropertyGlobals.PROPERTY_DBT_KEY_USERS, value);
					}
				} else if (line.contains("=")) {
					String values[] = line.split("=");
					String key = values[0].trim();
					String value = values[1].trim();
					
					if (key.toLowerCase().equals("mysqlip")) {
						System.setProperty(PropertyGlobals.PROPERTY_DB_MYSQL_IP, value);
						System.out.println("MySql Ip: "+System.getProperty(PropertyGlobals.PROPERTY_DB_MYSQL_IP));
					}
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/*
	public static void tempRun() {
		// Example
		// System.out.println(CipherUtility.encrypt("stringToEncrypt", "keyString".getBytes()));
	}*/

	/**
	 * @return the window
	 */
	public JFrame getWindow() {
		return window;
	}
}
