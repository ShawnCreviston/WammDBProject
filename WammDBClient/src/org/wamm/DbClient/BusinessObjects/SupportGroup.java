package org.wamm.DbClient.BusinessObjects;

/**
 * @author Eric Stephenson, Shawn Creviston
 * WAMM
 */
public class SupportGroup {

	// SupportGroup ID info
	private int supportGroupID;
		
	// SupportGroup name info
	private String groupName;
	
	private Territory territory;
	
	public SupportGroup() {
		supportGroupID = -1;
		groupName = null;
		territory = null;
	}

	/**
	 * @return the supportGroupID
	 */
	public int getSupportGroupID() {
		return supportGroupID;
	}

	/**
	 * @param supportGroupID the supportGroupID to set
	 */
	public void setSupportGroupID(int supportGroupID) {
		this.supportGroupID = supportGroupID;
	}

	/**
	 * @return the supportGroupName
	 */
	public String getSupportGroupName() {
		return groupName;
	}

	/**
	 * @param supportGroupName the supportGroupName to set
	 */
	public void setSupportGroupName(String supportGroupName) {
		this.groupName = supportGroupName;
	}

	/**
	 * @return the territory
	 */
	public Territory getTerritory() {
		return territory;
	}

	/**
	 * @param territory the territory to set
	 */
	public void setTerritory(Territory territory) {
		this.territory = territory;
	}
}
