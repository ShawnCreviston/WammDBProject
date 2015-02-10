package org.wamm.DbClient.BusinessObjects;

import java.util.Currency;
import java.util.Date;

/**
 * @author Eric Stephenson
 * WAMM
 */
public class SupportTransaction {
	
	// SupportTransaction ID info
	private int internalId;
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

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the amount
	 */
	public Currency getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Currency amount) {
		this.amount = amount;
	}

	private int schoolId;
	private int supportGroupId;
	
	// Date info
	private Date transactionDate;
	
	// Amount info
	private Currency amount;
	
	public SupportTransaction() {
		internalId = -1;
		schoolId = -1;
		supportGroupId = -1;
		
		transactionDate = null;
		
		amount = null;
	}
	
}
