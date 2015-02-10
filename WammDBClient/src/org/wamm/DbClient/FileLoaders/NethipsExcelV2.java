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
 * @author Shawn Creviston
 * WAMM
 */
public class NethipsExcelV2 implements ExcelFileLoader {
	
	private ArrayList<PatientImport> pList;
	private FileInputStream file;

	public NethipsExcelV2() {
		pList = new ArrayList<PatientImport>();
	}

	public void loadFileData() throws InvalidFormatException, IOException {
		Workbook wb = WorkbookFactory.create(file);

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			int numBlankLines = 0;
			
			for (Row row : sheet) {
				if (row.getRowNum() < 2) {
					// First two rows are header information
					continue;
				}
				
				Cell cellNethipsId = row.getCell(0); // Nethips sequential id per spread sheet, no meaning for db system
				Cell cellPlCode = row.getCell(1);    // Seams to be an unused field
				Cell cellName = row.getCell(2);
				Cell cellContactAddress = row.getCell(3);  // persons street address
				Cell cellSupportGroupName = row.getCell(4);
				Cell cellSupportGroupLocation = row.getCell(5);
				Cell cellSex = row.getCell(6);
				Cell cellAge = row.getCell(7);
				Cell cellWeight = row.getCell(8);
				Cell cellTreatmentCode = row.getCell(9);
				Cell cellPhone = row.getCell(10);
				Cell cellDateRegistered = row.getCell(11);
				Cell cellOccupation = row.getCell(12);
				Cell cellMaritalStatus = row.getCell(13);
//				Cell cellNumOfChildren = row.getCell(14);
				Cell cellPlhivStatus = row.getCell(15);

				// override, treat all cells as Strings for import processing
				// will get converted to correct data type later.
				for (Cell c : row) {
					c.setCellType(Cell.CELL_TYPE_STRING);
				}
				
				if (cellName == null || cellName.getStringCellValue().trim().length() < 1) {
					numBlankLines++;
					if (numBlankLines == 3) {
						// end of entries in file but worksheet still contains more rows, 
						// in some cases 100+ extra rows if user applied cell formats.
						break;
					}
					continue;
				}
				
				numBlankLines = 0;
				
				PatientImport p = new PatientImport();
				
				p.setFirstName(cellName.getStringCellValue().trim());
				
				// Set known patient properties
				if (cellContactAddress != null) {
					p.setImportedAddress(cellContactAddress.getStringCellValue().trim());
				}
				if (cellSex != null) {
					p.setGender(cellSex.getStringCellValue().trim());
				}
				if (cellAge != null) {
					p.setImportedBirthday(cellAge.getStringCellValue().trim());
				}
				if (cellWeight != null) {
					try {
						p.setWeight(Double.parseDouble(cellWeight.getStringCellValue().trim()));
					} catch (NumberFormatException nfe) {}
				}
				if (cellPhone != null) {
					p.setPhoneNumber1(cellPhone.getStringCellValue().trim());
				}
				if (cellOccupation != null) {
					p.setOccupation(cellOccupation.getStringCellValue().trim());
				}
				if (cellMaritalStatus != null) {
					p.setMaritalStatus(cellMaritalStatus.getStringCellValue().trim());
				}
				if (cellDateRegistered != null) {
					p.setImportedDateRegistered(cellDateRegistered.getStringCellValue().trim());
				}
				
				Treatment t = null;
				if (cellTreatmentCode != null) {
					String importedTreatment = cellTreatmentCode.getStringCellValue().trim();
					if (importedTreatment.length() > 0) {
						t = new Treatment();
						t.setTreatment(importedTreatment);
					}
				}
				
				if (cellPlCode != null) {
					String importedPlCode = cellPlCode.getStringCellValue().trim();
					if (importedPlCode.length() > 0) {
						if (t == null) t = new Treatment();
						t.setPlCode(importedPlCode);
					}
				}
				
				if (cellPlhivStatus != null) {
					String importedPlhiv = cellPlhivStatus.getStringCellValue().trim();
					if (importedPlhiv.length() > 0) {
						if (t == null) t = new Treatment();
						t.setCurrentStatus(importedPlhiv);
					}
				}
				
				if (t != null) {
					p.addTreatment(t);
				}

				SupportGroup sg = new SupportGroup();
				sg.setSupportGroupName(cellSupportGroupName.getStringCellValue().trim());
				sg.setTerritory(new Territory());
				sg.getTerritory().setTerritoryName(cellSupportGroupLocation.getStringCellValue().trim());
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
