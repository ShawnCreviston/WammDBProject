package org.wamm.DbClient.ValueObjects;

import java.util.ArrayList;

import org.wamm.DbClient.BusinessObjects.ClientUserBO;
import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.Dao.DBKeys;

public class UserSessionVO {

	private static UserSessionVO session;
	public static UserSessionVO getSession() {
		if (session == null) {
			session = new UserSessionVO();
		}
		return session;
	}
	
	private UserSessionVO() {
		clientUser = null;
		dbKeys = new DBKeys();
	}
	
	private ClientUserBO clientUser;
	private DBKeys dbKeys;
	/**
	 * The currently active patient list
	 */
	private ArrayList<Patient> patientList;
	
	/**
	 * @return the clientUser
	 */
	public ClientUserBO getClientUser() {
		return clientUser;
	}

	/**
	 * @param clientUser the clientUser to set
	 */
	public void setClientUser(ClientUserBO clientUser) {
		this.clientUser = clientUser;
	}

	/**
	 * @return the dbKeys
	 */
	public DBKeys getDbKeys() {
		return dbKeys;
	}

	/**
	 * @return the patientList
	 */
	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	/**
	 * @param patientList the patientList to set
	 */
	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}
}
