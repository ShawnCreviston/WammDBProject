package org.wamm.DbClient.BusinessEnums;

/**
 * @author Shawn Creviston
 * WAMM
 */
public enum FileTypes {

	NETHIPS_EXCEL_FILE ("NETHIPS PLHIVs DATA BASE"),
	NEW_REG_EXCEL_FILE ("New Registration List");
	
	private String fileDesription;
	
	private FileTypes (String fileDescription) {
		this.fileDesription = fileDescription;
	}
	
	public String getFileDescription() {
		return fileDesription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return getFileDescription();
	}
}
