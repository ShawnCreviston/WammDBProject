package org.wamm.DbClient.FormatRules;

import org.wamm.DbClient.BusinessObjects.PatientImport;

/**
 * @author Shawn Creviston
 * WAMM
 */
public interface DataFormatRule {

	public void refactorPatientData(PatientImport patient);
}
