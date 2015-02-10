package org.wamm.DbClient.BusinessObjects;

/**
 * @author Eric Stephenson
 * WAMM
 */
public class Plhiv {
	
	// Patient ID info
	private int internalId;
	
	// Patient codes
	private String patientCode;
	
	// Patient medical
	private String plhivName;
	private String transfer;
	private String coInfected;
	private String source;
	
	public Plhiv() {
		internalId = -1;
		
		patientCode = null;
		
		plhivName = null;
		transfer = null;
		coInfected = null;
		source = null;
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
	 * @return the patientCode
	 */
	public String getPatientCode() {
		return patientCode;
	}

	/**
	 * @param patientCode the patientCode to set
	 */
	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	/**
	 * @return the plhivName
	 */
	public String getPlhivName() {
		return plhivName;
	}

	/**
	 * @param plhivName the plhivName to set
	 */
	public void setPlhivName(String plhivName) {
		this.plhivName = plhivName;
	}

	/**
	 * @return the transfer
	 */
	public String getTransfer() {
		return transfer;
	}

	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	/**
	 * @return the coInfected
	 */
	public String getCoInfected() {
		return coInfected;
	}

	/**
	 * @param coInfected the coInfected to set
	 */
	public void setCoInfected(String coInfected) {
		this.coInfected = coInfected;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
}
