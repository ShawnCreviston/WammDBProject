package org.wamm.DbClient.BusinessObjects;

/**
 * @author Shawn Creviston, Eric Stephenson
 * WAMM
 */
public class Territory {

	// Territory ID info
	private int territoryId;
	private int parentTerritoryId;
	private Territory parent;
	
	// Territory info
	private String territoryType;
	private String territoryName;
	
	public Territory() {
		territoryId = -1;
		parentTerritoryId = -1;
		parent = null;
		
		territoryType = null;
		territoryName = null;
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
	 * @return the parentTerritoryId
	 */
	public int getParentTerritoryId() {
		return parentTerritoryId;
	}

	/**
	 * @param parentTerritoryId the parentTerritoryId to set
	 */
	public void setParentTerritoryId(int parentTerritoryId) {
		this.parentTerritoryId = parentTerritoryId;
	}

	/**
	 * @return the territoryType
	 */
	public String getTerritoryType() {
		return territoryType;
	}

	/**
	 * @param territoryType the territoryType to set
	 */
	public void setTerritoryType(String territoryType) {
		this.territoryType = territoryType;
	}

	/**
	 * @return the territoryName
	 */
	public String getTerritoryName() {
		return territoryName;
	}

	/**
	 * @param territoryName the territoryName to set
	 */
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	/**
	 * @return the parent
	 */
	public Territory getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Territory parent) {
		this.parent = parent;
	}
}
