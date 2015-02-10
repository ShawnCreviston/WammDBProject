package org.wamm.DbClient.FileLoaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.wamm.DbClient.BusinessObjects.PatientImport;
import org.wamm.DbClient.BusinessObjects.SupportGroup;

/**
 * @author Eric Stephenson, Shawn Creviston
 * WAMM
 */
public class NethipsExcel implements ExcelFileLoader {

	// File specific column names and order
	public static final String COL_NO = "NO";
	public static final String COL_NAME = "Name";
	public static final String COL_ADDRESS = "Address";
	public static final String COL_AGE = "Age";
	public static final String COL_SEX = "Sex";
	public static final String COL_TREATMENT = "Treatment";
	public static final String COL_ARV = "ARV";
	public static final String COL_TYPE = "Type"; 
	
	private ArrayList<PatientImport> pList;
	private FileInputStream file;

	public NethipsExcel() {
		pList = new ArrayList<PatientImport>();
	}

	public void loadFileData() throws InvalidFormatException, IOException {
		Workbook wb = WorkbookFactory.create(file);

		//final Sheet sheet = wb.getSheetAt(0);
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			for (final Row row : sheet) {
				
				if (row.getRowNum() == 0){
					continue;
				}
				
				final Cell pName = row.getCell(1);
				final Cell pAddress = row.getCell(2);
				final Cell pAge = row.getCell(3);
				final Cell pSex = row.getCell(4);
				final Cell pTreatment = row.getCell(5);
				final Cell pARV = row.getCell(6);
				final Cell pType = row.getCell(7);
				final String pSupportGroup = sheet.getSheetName();

				//treat all cells as Strings
				for (Cell c : row) {
					c.setCellType(Cell.CELL_TYPE_STRING);
				}
				
				if (pName == null || pName.getStringCellValue().trim().length() < 1) {
					continue;
				}
				
				PatientImport p = new PatientImport();
				
				p.setFirstName(pName.getStringCellValue().trim());
				
				// Set known patient properties
				if (pAddress != null) {
					p.setImportedAddress(pAddress.getStringCellValue().trim());
				}
				if (pAge != null) {
					p.setImportedBirthday(pAge.getStringCellValue().trim());
					
					// Use line below to test crash during reading file
					//p.setImportedBirthday(pAge.getNumericCellValue()+"");
				}
				if (pSex != null) {
					p.setGender(pSex.getStringCellValue().trim());
				}
//				if (pTreatment != null) {
//					p.setImportedTreatment(pTreatment.getStringCellValue().trim());
//				}
//				if (pARV != null) {
//					p.setImportedARV(pARV.getStringCellValue());
//				}
//				if (pType != null) {
//					p.setImportedTreatmentType(pType.getStringCellValue().trim());
//				}

				SupportGroup sg = new SupportGroup();
				sg.setSupportGroupName(pSupportGroup);
				p.setSupportGroup(sg);

				pList.add(p);
			}
		}
		
		wb = null;
		file = null;
	}

	@Override
	public ArrayList<PatientImport> getFileData() {
		return pList;
	}

	@Override
	public void setFile(FileInputStream file) {
		this.file = file;
	}
}
