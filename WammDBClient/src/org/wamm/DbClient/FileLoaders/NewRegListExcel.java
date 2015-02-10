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
import org.wamm.DbClient.BusinessObjects.Territory;
import org.wamm.DbClient.BusinessObjects.Treatment;

/**
 * @author Eric Stephenson, Shawn Creviston
 * WAMM
 */
public class NewRegListExcel implements ExcelFileLoader {

	private ArrayList<PatientImport> pList;
	private FileInputStream file;
	
	public NewRegListExcel() {
		pList = new ArrayList<PatientImport>();
	}
	
	public void loadFileData() throws InvalidFormatException, IOException {
		Workbook wb = WorkbookFactory.create(file);

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			for (final Row row : sheet) {
				//skip first row containing header info
				if (row.getRowNum() == 0) {
					continue;
				}
				PatientImport p = null;
				
				//determine which sheet data to load
				switch (i) {

					case 0:
						p = loadFirstWBData(row);
						break;
					case 1:
						p = loadWBData(row);
						break;
					case 2:
						p = loadWBData(row);
						break;
					case 3:
						p = loadWBData(row);
						break;
					default:
						p = loadWBData(row);
						break;		
				}
				
				if (p != null && p.getFirstName() !=null) {
					
					SupportGroup sg = new SupportGroup();
					sg.setSupportGroupName(sheet.getSheetName());
					p.setSupportGroup(sg);
					
					pList.add(p);
				}
			}
		}
		
		file = null;
	}
	
	private PatientImport loadFirstWBData(Row row) {
		final Cell pName = row.getCell(3);
		final Cell pAddress = row.getCell(8);
		final Cell pOccupation = row.getCell(9);
		final Cell pAge = row.getCell(7);
		final Cell pSex = row.getCell(6);
		final Cell pTreatment = row.getCell(5);
		final Cell pPhoneNumber = row.getCell(10);
		final Cell pLocation = row.getCell(1);
		
		//treat all cells as Strings
		for (Cell c : row) {
			c.setCellType(Cell.CELL_TYPE_STRING);
		}
		
		PatientImport p = new PatientImport();

		// IGNORE NAMELESS PATIENTS
		if (pName == null) {
			return null;
		}
		
		String pCellName = pName.getStringCellValue().trim();
		
		if(pCellName != null && !pCellName.isEmpty()){
			p.setFirstName(pCellName);
		} else {
			return null;
		}
		
		//SET KNOWN PATIENT PROPERTIES
		if (pAddress != null) {
			p.setImportedAddress(pAddress.getStringCellValue().trim());
		}
		if (pAge != null) {
			p.setImportedBirthday(pAge.getStringCellValue().trim());
		}
		if (pSex != null) {
			//male
			if (pSex.getStringCellValue().trim().equalsIgnoreCase("0")) {
				p.setGender("M");
			} //female
			else if (pSex.getStringCellValue().trim().equalsIgnoreCase("1")) {
				p.setGender("F");
			} else {
				p.setGender("");
			}
		}
		if (pTreatment != null) {
			if (pTreatment.getStringCellValue().trim().length() > 0) {
				Treatment t = new Treatment();
				t.setTreatment(pTreatment.getStringCellValue().trim());
				p.addTreatment(t);
			}
		}
		if (pPhoneNumber != null) {
			p.setPhoneNumber1(pPhoneNumber.getStringCellValue().trim());;
		}
		
		if (pOccupation != null) {
			p.setOccupation(pOccupation.getStringCellValue().trim());;
		}
		if (pLocation != null) {
			Territory t = new Territory();
			t.setTerritoryName(pLocation.getStringCellValue().trim());
		}
		
		//if (pARV != null) {
		//	p.setImportedARV(pARV.getStringCellValue());
		//}
		//if (pType != null) {
		//	p.setImportedTreatmentType(pType.getCellType()==Cell.CELL_TYPE_NUMERIC ? pType.getNumericCellValue() + "" : pType.getStringCellValue().trim());
		//}
		
		return p;
	}
	
	private PatientImport loadWBData(Row row) {
		final Cell pName = row.getCell(5);
		final Cell pAddress = row.getCell(6);
		final Cell pAge = row.getCell(7);
		final Cell pSex = row.getCell(8);
		final Cell pTreatment = row.getCell(3);
		final Cell pLocation = row.getCell(1);
		//final Cell pSchool = row.getCell(9);
		//final Cell pClass = row.getCell(10);
		
		//final Cell pPhoneNumber = row.getCell(10);
		//final Cell pARV = row.getCell(6);
		//final Cell pType = row.getCell(7);
		//final String pSupportGroup = sheet.getSheetName();
		
		//treat all cells as Strings
		for (Cell c : row) {
			c.setCellType(Cell.CELL_TYPE_STRING);
		}
		
		// IGNORE NAMELESS PATIENTS
		if (pName == null) {
			return null;
		}
		
		PatientImport p = new PatientImport();
		
		String pCellName = pName.getStringCellValue().trim();
		
		if(pCellName != null && !pCellName.isEmpty()){
			p.setFirstName(pCellName);
		} else {
			return null;
		}
		
		//SET KNOWN PATIENT PROPERTIES
		if (pAddress != null) {
			p.setImportedAddress(pAddress.getStringCellValue().trim());
		}
		if (pAge != null) {
			p.setImportedBirthday(pAge.getStringCellValue().trim());
		}
		if (pSex != null) {
			//male
			if (pSex.getStringCellValue().trim().equalsIgnoreCase("0")) {
				p.setGender("M");
			} //female
			else if (pSex.getStringCellValue().trim().equalsIgnoreCase("1")) {
				p.setGender("F");
			} else {
				p.setGender("");
			}
		}
		if (pTreatment != null) {
			if (pTreatment.getStringCellValue().trim().length() > 0) {
				Treatment t = new Treatment();
				t.setTreatment(pTreatment.getStringCellValue().trim());
				p.addTreatment(t);
			}
		}
		if (pLocation != null) {
			Territory t = new Territory();
			t.setTerritoryName(pLocation.getStringCellValue().trim());
		}
		
//		Disabled school import because the data isn't consistent enough to parse and I don't
//		have a list of school information in the database to use.
//		if (pSchool != null) {			
//			//FOR DISPLAY PURPOSES TO SHOW SCHOOL IS BEING IMPORTED
//			School school = new School();
//			school.setSchoolName(pSchool.getStringCellValue().trim());
//			p.setSchool(school);
//		}
		
//		if (pClass != null) {
//			p.setImportedClass(pClass.getStringCellValue().trim());
//		}
		
		//if (pPhoneNumber != null) {
		//	p.setPhoneNumber(pPhoneNumber.getStringCellValue().trim());;
		//}		
		//if (pARV != null) {
		//	p.setImportedARV(pARV.getStringCellValue());
		//}
		//if (pType != null) {
		//	p.setImportedTreatmentType(pType.getCellType()==Cell.CELL_TYPE_NUMERIC ? pType.getNumericCellValue() + "" : pType.getStringCellValue().trim());
		//}
		
		//p.setImportedSupportGroup(pSupportGroup);
		
		
		return p;
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
