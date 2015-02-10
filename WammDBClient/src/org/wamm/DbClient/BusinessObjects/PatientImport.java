package org.wamm.DbClient.BusinessObjects;

public class PatientImport extends Patient {

	private String importedAddress;
	private String importedBirthday;
	private String importedDeseased;
	private String importedWeight;
	private String importedDateRegistered;
	
	// Treatment info
//	private String importedTreatment;
//	private String importedARV;
//	private String importedTreatmentType;
	
	public PatientImport () {
		super();
		
		importedBirthday = null;
		importedDeseased = null;
		importedDateRegistered = null;
		importedWeight = null;
		
		importedAddress = null;
//		importedTreatment = null;
//		importedARV = null;
//		importedTreatmentType = null;
	}

	/**
	 * @return the importedAddress
	 */
	public String getImportedAddress() {
		return importedAddress;
	}

	/**
	 * @param importedAddress the importedAddress to set
	 */
	public void setImportedAddress(String importedAddress) {
		this.importedAddress = importedAddress;
	}

	/**
	 * @return the importedBirthday
	 */
	public String getImportedBirthday() {
		return importedBirthday;
	}

	/**
	 * @param importedBirthday the importedBirthday to set
	 */
	public void setImportedBirthday(String importedBirthday) {
		this.importedBirthday = importedBirthday;
	}

	/**
	 * @return the importedDeseased
	 */
	public String getImportedDeseased() {
		return importedDeseased;
	}

	/**
	 * @param importedDeseased the importedDeseased to set
	 */
	public void setImportedDeseased(String importedDeseased) {
		this.importedDeseased = importedDeseased;
	}

	/**
	 * @return the importedWeight
	 */
	public String getImportedWeight() {
		return importedWeight;
	}

	/**
	 * @param importedWeight the importedWeight to set
	 */
	public void setImportedWeight(String importedWeight) {
		this.importedWeight = importedWeight;
	}

	/**
	 * @return the importedDateRegistered
	 */
	public String getImportedDateRegistered() {
		return importedDateRegistered;
	}

	/**
	 * @param importedDateRegistered the importedDateRegistered to set
	 */
	public void setImportedDateRegistered(String importedDateRegistered) {
		this.importedDateRegistered = importedDateRegistered;
	}
}
