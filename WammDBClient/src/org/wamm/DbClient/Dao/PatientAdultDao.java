package org.wamm.DbClient.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.wamm.DbClient.BusinessObjects.Patient;
import org.wamm.DbClient.BusinessObjects.School;
import org.wamm.DbClient.BusinessObjects.Territory;
import org.wamm.DbClient.Globals.PropertyGlobals;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class PatientAdultDao extends BaseDao {

	// Database table names
	public static final String FN_TABLE = "patientadult";
	public static final String FN_ALIAS = "ptad";

	public static final String FN_PATIENT_ID = "PatientId";
	public static final String FN_FIRST_NAME = "FirstName";
	public static final String FN_MIDDLE_NAME = "MiddleName";
	public static final String FN_LAST_NAME = "LastName";
	public static final String FN_GENDER = "Gender";
	public static final String FN_BIRTHDATE = "Birthdate";
	public static final String FN_DESEASED = "Deseased";
	public static final String FN_WEIGHT = "Weight";
	public static final String FN_CUR_TERRITORY_ID = "CurTerritoryId";
	public static final String FN_CUR_STREET = "CurStreet";
	public static final String FN_PREV_TERRITORY_ID = "PrevTerritoryId";
	public static final String FN_PREV_STREET = "PrevStreet";
	public static final String FN_PHONE1 = "PhoneNumber1";
	public static final String FN_PHONE2 = "PhoneNumber2";
	public static final String FN_CHIEFDOM_ID = "ChiefdomId";
	public static final String FN_SCHOOL_ID = "SchoolId";
	public static final String FN_OCCUPATION = "Occupation";
	public static final String FN_MARITAL_STATUS = "MaritalStatus";
	public static final String FN_REGISTERED_DATE = "RegisteredDate";
	public static final String FN_LAST_UPDATED_DATE = "LastUpdatedDate";
	
	
	/**
	 * Generic access for patients queries
	 * @param where
	 * @param order
	 * @param cn
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Patient> getPatients(String where, String order, Connection cn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT ");
		sql.append(FN_PATIENT_ID + ",");
		String keyValue = UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT);
		sql.append("aes_decrypt("+FN_FIRST_NAME + ",'"+keyValue+"') AS "+FN_FIRST_NAME+",");
		sql.append("aes_decrypt("+FN_MIDDLE_NAME + ",'"+keyValue+"') AS "+FN_MIDDLE_NAME+",");
		sql.append("aes_decrypt("+FN_LAST_NAME + ",'"+keyValue+"') AS "+FN_LAST_NAME+",");
		sql.append(FN_GENDER + ",");
		sql.append(FN_BIRTHDATE + ",");
		sql.append(FN_DESEASED + ",");
		sql.append(FN_WEIGHT + ",");
		sql.append(FN_CUR_TERRITORY_ID + ",");
		sql.append(FN_CUR_STREET + ",");
		sql.append(FN_PHONE1 + ",");
		sql.append(FN_PHONE2 + ",");
		sql.append(FN_CHIEFDOM_ID + ",");
		sql.append(FN_SCHOOL_ID + ",");
		sql.append(FN_OCCUPATION + ",");
		sql.append(FN_MARITAL_STATUS + ",");
		sql.append(FN_LAST_UPDATED_DATE);
		sql.append(" FROM "+FN_TABLE+" "+FN_ALIAS);
		sql.append(((where != null && where.length() > 0) ? (" WHERE " + where) : ""));
		sql.append(((order != null && order.length() > 0) ? (" ORDER BY " + order) : ""));
		sql.append(") "+FN_ALIAS);
		return patientsList(sql.toString(), cn);
	}
		
	private static ArrayList<Patient> patientsList(String sql, Connection cn) throws SQLException {
		//System.out.println(sql);
		ArrayList<Patient> pnts = new ArrayList<Patient>();
		
		Statement statement = null;

		try {
			statement = cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Patient pnt = new Patient();
				populatePatientBO(rs, pnt);
				pnts.add(pnt);
			}
			BaseDao.closeStatement(statement);
		} catch (SQLException sqlEx) {
			throw new SQLException(sqlEx.toString());
		} catch (NullPointerException npe) {
			throw new SQLException(npe.toString());
		} finally {
			BaseDao.closeStatement(statement);
		}
		
		return pnts;
	}
	
	public static ArrayList<Patient> getPatientsByName(String firstName, String lastName, Connection cn) throws SQLException {
		return getPatients(FN_ALIAS+"."+FN_FIRST_NAME + "= aes_encrypt('"+escapeSQLCharacters(firstName.toLowerCase()) + "','"+
				UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT)+"') AND "+
				FN_ALIAS+"."+FN_LAST_NAME + " = aes_encrypt('"+escapeSQLCharacters(lastName.toLowerCase()) + "','"+
				UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT)+"')", null, cn);
	}
	
	private static void populatePatientBO(ResultSet rs, Patient pnt) throws SQLException {
		pnt.setPatientId(rs.getInt(FN_ALIAS+"."+FN_PATIENT_ID));
		pnt.setFirstName(rs.getString(FN_ALIAS+"."+FN_FIRST_NAME));
		pnt.setMiddleName(rs.getString(FN_ALIAS+"."+FN_MIDDLE_NAME));
		pnt.setLastName(rs.getString(FN_ALIAS+"."+FN_LAST_NAME));
		pnt.setGender(rs.getString(FN_ALIAS+"."+FN_GENDER));
		pnt.setBirthday(rs.getDate(FN_ALIAS+"."+FN_BIRTHDATE));
		pnt.setDeceased(rs.getDate(FN_ALIAS+"."+FN_DESEASED));
		pnt.setWeight(rs.getDouble(FN_ALIAS+"."+FN_WEIGHT));
		
		Territory t = new Territory();
		t.setTerritoryId(rs.getInt(FN_ALIAS+"."+FN_CUR_TERRITORY_ID));
		pnt.setCurTerritory(t);
		
		pnt.setCurStreet(rs.getString(FN_ALIAS+"."+FN_CUR_STREET));
		
		pnt.setPhoneNumber1(rs.getString(FN_ALIAS+"."+FN_PHONE1));
		pnt.setPhoneNumber2(rs.getString(FN_ALIAS+"."+FN_PHONE2));

		School s = new School();
		s.setSchoolId(rs.getInt(FN_ALIAS+"."+FN_SCHOOL_ID));
		pnt.setSchool(s);
		
		pnt.setOccupation(rs.getString(FN_ALIAS+"."+FN_OCCUPATION));
		pnt.setMaritalStatus(rs.getString(FN_ALIAS+"."+FN_MARITAL_STATUS));
		
		Timestamp lastUpdated = rs.getTimestamp(FN_ALIAS+"."+FN_LAST_UPDATED_DATE);
		if (lastUpdated != null) {
			pnt.setLastUpdatedDate(new Date(lastUpdated.getTime()));
		}
	}
	
	public static void insertPatients(ArrayList<Patient> pnts, Connection cn) throws SQLException {
		BaseDao.startTransaction(cn);
		try {
		for (int pi = 0; pi < pnts.size(); pi++) {
			insertPatient(pnts.get(pi), cn);
		}
		BaseDao.commitChanges(cn);
		} catch (SQLException se) {
			BaseDao.rollbackChanges(cn);
		}
	}
	
	public static void insertPatient(Patient p, Connection cn) throws SQLException {
		Statement statement = null;
		SimpleDateFormat sdfMySQLFormat = new SimpleDateFormat("yyyy-MM-dd");	//2014-05-15 13:54:05 HH:mm:ss
		StringBuffer sql = new StringBuffer();

		try {
			sql.append("INSERT INTO "+FN_TABLE+" (");
			if (p.getPatientId() > 0) {
				sql.append(FN_PATIENT_ID + ",");
			}
			sql.append(FN_FIRST_NAME + ",");
			if (p.getMiddleName() != null) {
				sql.append(FN_MIDDLE_NAME + ",");
			}
			sql.append(FN_LAST_NAME + ",");
			if (p.getGender() != null) {
				sql.append(FN_GENDER + ",");
			}
			if (p.getBirthday() != null) {
				sql.append(FN_BIRTHDATE + ",");
			}
			if (p.getDeceased() != null) {
				sql.append(FN_DESEASED + ",");
			}
			if (p.getWeight() >= 0) {
				sql.append(FN_WEIGHT + ",");
			}
			sql.append(FN_CUR_TERRITORY_ID + ",");
			sql.append(FN_CUR_STREET + ",");
			if (p.getPhoneNumber1() != null) {
				sql.append(FN_PHONE1 + ",");
			}
			if (p.getPhoneNumber2() != null) {
				sql.append(FN_PHONE2 + ",");
			}
			if (p.getSchool() != null) {
				sql.append(FN_SCHOOL_ID + ",");
			}
			if (p.getOccupation() != null) {
				sql.append(FN_OCCUPATION + ",");
			}
			if (p.getMaritalStatus() != null) {
				sql.append(FN_MARITAL_STATUS + ",");
			}
			if (p.getRegisteredDate() != null) {
				sql.append(FN_REGISTERED_DATE + ",");
			}
			sql.append(FN_LAST_UPDATED_DATE);
			sql.append(") VALUES (");
			if (p.getPatientId() > 0) {
				sql.append(p.getPatientId() + ", ");
			}
			String keyValue = UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_PATIENT);
			sql.append("aes_encrypt('"+escapeSQLCharacters(p.getFirstName().toLowerCase()) + "','" + keyValue +"'), ");
			if (p.getMiddleName() != null) {
				sql.append("aes_encrypt('"+escapeSQLCharacters(p.getMiddleName().toLowerCase()) + "','" + keyValue +"'), ");
			}
			sql.append("aes_encrypt('"+escapeSQLCharacters(p.getLastName().toLowerCase()) + "','" + keyValue +"'), ");
			if (p.getGender() != null) {
				sql.append("'"+p.getGender().toLowerCase() + "', ");
			}

			if (p.getBirthday() != null) {
				String insertDate = sdfMySQLFormat.format(p.getBirthday()).toString();
				sql.append("'"+insertDate + "', ");
			}
			if (p.getDeceased() != null) {
				String insertDate = sdfMySQLFormat.format(p.getDeceased()).toString();
				sql.append("'"+insertDate + "', ");
			}
			if (p.getWeight() >= 0) {
				sql.append(p.getWeight() + ", ");
			}
			sql.append(p.getCurTerritory().getTerritoryId() + ", ");
			sql.append("'"+escapeSQLCharacters(p.getCurStreet()) + "', ");
			if (p.getPhoneNumber1() != null) {
				sql.append("'"+p.getPhoneNumber1() + "', ");
			}
			if (p.getPhoneNumber2() != null) {
				sql.append("'"+p.getPhoneNumber2() + "', ");
			}
			if (p.getSchool() != null) {
				sql.append("'"+p.getSchool().getSchoolId() + "', ");
			}
			if (p.getOccupation() != null) {
				sql.append("'"+escapeSQLCharacters(p.getOccupation()) + "', ");
			}
			if (p.getMaritalStatus() != null) {
				sql.append("'"+p.getMaritalStatus() + "', ");
			}
			if (p.getRegisteredDate() != null) {
				String insertDate = sdfMySQLFormat.format(p.getRegisteredDate()).toString();
				sql.append("'"+insertDate + "', ");
			}
			sql.append("null)");
//			System.out.println(sql.toString());
			statement = cn.createStatement();
			statement.executeUpdate(sql.toString());
			
			ResultSet rs = null;
			try {
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					p.setPatientId(rs.getInt(1));
				}
			} catch (SQLException sqle) {
				// do nothing
			} finally {
				try {
					rs.close();
				} catch (SQLException sqle) {
					// do nothing
				} finally {
					rs = null;
				}
			}
			
			BaseDao.closeStatement(statement);
		} catch (SQLException sqlEx) {
			throw new SQLException(sqlEx.toString());
		} catch (NullPointerException npe) {
			throw new SQLException(npe.toString());
		} finally {
			BaseDao.closeStatement(statement);
		}
	}
	
	public static void deletePatient(int patientId, Connection cn) throws SQLException {
		Statement statement = null;
		StringBuffer sql = new StringBuffer();
		
		try {
			sql.append("DELETE FROM " + FN_TABLE + " WHERE " + FN_PATIENT_ID + " = " + patientId);
//			System.out.println(sql.toString());
			statement = cn.createStatement();
			statement.executeUpdate(sql.toString());

			BaseDao.closeStatement(statement);
		} catch (SQLException sqlEx) {
			throw new SQLException(sqlEx.toString());
		} catch (NullPointerException npe) {
			throw new SQLException(npe.toString());
		} finally {
			BaseDao.closeStatement(statement);
		}
	}
}
