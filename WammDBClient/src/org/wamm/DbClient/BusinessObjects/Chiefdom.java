package org.wamm.DbClient.BusinessObjects;

/**
 * @author Eric Stephenson
 * WAMM
 */
public class Chiefdom {

	// Chiefdom ID info
	private int chiefdomId;
	private int territoryId;
	
	// Chiefdom name info
	private String chiefdomName;
	
	public Chiefdom() {
		chiefdomId = -1;
		territoryId = -1;
		
		chiefdomName = null;	
	}

	/**
	 * @return the chiefdomId
	 */
	public int getChiefdomId() {
		return chiefdomId;
	}

	/**
	 * @param chiefdomId the chiefdomId to set
	 */
	public void setChiefdomId(int chiefdomId) {
		this.chiefdomId = chiefdomId;
	}

	/**
	 * @return the territoryId
	 */
	public int getTerritoryId() {
		return territoryId;
	}

	/**
	 * @param territoryId the territoryId to set
	 */
	public void setTerritoryId(int territoryId) {
		this.territoryId = territoryId;
	}

	/**
	 * @return the chiefdomName
	 */
	public String getChiefdomName() {
		return chiefdomName;
	}

	/**
	 * @param chiefdomName the chiefdomName to set
	 */
	public void setChiefdomName(String chiefdomName) {
		this.chiefdomName = chiefdomName;
	}
	
	
}
