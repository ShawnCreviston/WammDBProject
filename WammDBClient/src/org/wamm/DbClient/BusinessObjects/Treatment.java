package org.wamm.DbClient.BusinessObjects;

/**
 * @author Eric Stephenson, Shawn Creviston
 * WAMM
 */
public class Treatment {

	// Treatment ID info
	private int internalId;
	
	// Treatment info
	private String treatment;
	private String plCode;
	private String currentStatus;
	
	public Treatment() {
		internalId = -1;
		
		treatment = null;
		plCode = null;
		currentStatus = null;
	}

	/**
	 * @return the internalId
	 */
	public int getInternalId() {
		return internalId;
	}

	/**
	 * @param internalId the internalId to set
	 */
	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	/**
	 * @return the treatment
	 */
	public String getTreatment() {
		return treatment;
	}

	/**
	 * @param treatment the treatment to set
	 */
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	/**
	 * @return the code
	 */
	public String getPlCode() {
		return plCode;
	}

	/**
	 * @param code the code to set
	 */
	public void setPlCode(String code) {
		this.plCode = code;
	}

	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus the currentStatus to set
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
}
