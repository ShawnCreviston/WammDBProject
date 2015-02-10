package org.wamm.DbClient.BusinessObjects;

/**
 * @author Eric Stephenson
 * WAMM
 */
public class School {
	
	// School ID info
	private int schoolId;
	private int territoryId;

	// Name info
	private String schoolName;
	
	// Address info
	private String curStreet;
	
	public School() {
		schoolId = -1;
		territoryId = -1;
		
		schoolName = null;
		curStreet = null;
		
		curStreet = null;
	}

	/**
	 * @return the schoolId
	 */
	public int getSchoolId() {
		return schoolId;
	}

	/**
	 * @param schoolId the schoolId to set
	 */
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
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
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * @return the curStreet
	 */
	public String getCurStreet() {
		return curStreet;
	}

	/**
	 * @param curStreet the curStreet to set
	 */
	public void setCurStreet(String curStreet) {
		this.curStreet = curStreet;
	}
	
}
