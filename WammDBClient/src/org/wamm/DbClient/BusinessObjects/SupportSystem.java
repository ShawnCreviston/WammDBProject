package org.wamm.DbClient.BusinessObjects;

/**
 * @author Eric Stephenson
 * WAMM
 */
public class SupportSystem {

	// SupportSystem ID info
	private int internalId;
	private int supportGroupId;
	
	public SupportSystem() {
		internalId = -1;
		supportGroupId = -1;
	}
	
	public SupportSystem(int internalId, int supportGroupId) {
		this.internalId = internalId;
		this.supportGroupId = supportGroupId;
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
	 * @return the supportGroupId
	 */
	public int getSupportGroupId() {
		return supportGroupId;
	}

	/**
	 * @param supportGroupId the supportGroupId to set
	 */
	public void setSupportGroupId(int supportGroupId) {
		this.supportGroupId = supportGroupId;
	}
}
