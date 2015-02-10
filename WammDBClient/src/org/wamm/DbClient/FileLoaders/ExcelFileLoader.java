package org.wamm.DbClient.FileLoaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.wamm.DbClient.BusinessObjects.PatientImport;

/**
 * @author Shawn Creviston
 * WAMM
 */
public interface ExcelFileLoader {

	/**
	 * Loads the data into an internal array list
	 * @param file
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void loadFileData() throws InvalidFormatException, IOException;
	/**
	 * Returns the list of data. Use loadFileData to populate
	 * @return
	 */
	public ArrayList<PatientImport> getFileData();
	public void setFile(FileInputStream file);
}
