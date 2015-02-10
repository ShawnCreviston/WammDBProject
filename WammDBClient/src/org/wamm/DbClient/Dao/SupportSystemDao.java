package org.wamm.DbClient.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wamm.DbClient.BusinessObjects.SupportSystem;
import org.wamm.DbClient.Globals.PropertyGlobals;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class SupportSystemDao extends BaseDao {

	// Database table names
	public static final String FN_TABLE = "supportsystem";
	public static final String FN_ALIAS = "spsys";

	public static final String FN_PATIENT_E_ID = "InternalID";
	public static final String FN_SUPPORT_GROUP_ID = "SupportGroupID";
	
	
	/**
	 * Generic access for support systems queries
	 * @param where
	 * @param order
	 * @param cn
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<SupportSystem> getSupportSystems(String where, String order, Connection cn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT ");
		sql.append("aes_decrypt("+FN_PATIENT_E_ID + ",'"+UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_SUPSYS)+"') AS "+FN_PATIENT_E_ID+",");
		sql.append(FN_SUPPORT_GROUP_ID);
		sql.append(" FROM "+FN_TABLE+" "+FN_ALIAS);
		sql.append(((where != null && where.length() > 0) ? (" WHERE " + where) : ""));
		sql.append(((order != null && order.length() > 0) ? (" ORDER BY " + order) : ""));
		sql.append(") "+FN_ALIAS);
		return supportSystemList(sql.toString(), cn);
	}
		
	private static ArrayList<SupportSystem> supportSystemList(String sql, Connection cn) throws SQLException {
		ArrayList<SupportSystem> ter = new ArrayList<SupportSystem>();
		
		Statement statement = null;

		try {
			statement = cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				SupportSystem sg = new SupportSystem();
				populateSupportSystemBO(rs, sg);
				ter.add(sg);
			}
			BaseDao.closeStatement(statement);
		} catch (SQLException sqlEx) {
			throw new SQLException(sqlEx.toString());
		} catch (NullPointerException npe) {
			throw new SQLException(npe.toString());
		} finally {
			BaseDao.closeStatement(statement);
		}
		
		return ter;
	}
	
	private static void populateSupportSystemBO(ResultSet rs, SupportSystem sg) throws SQLException {
		sg.setInternalId(rs.getInt(FN_ALIAS+"."+FN_PATIENT_E_ID));
		sg.setSupportGroupId(rs.getInt(FN_ALIAS+"."+FN_SUPPORT_GROUP_ID));
	}
	
	public static void insertSupportSystems(ArrayList<SupportSystem> supportSystems, Connection cn) throws SQLException {
		BaseDao.startTransaction(cn);
		try {
		for (int pi = 0; pi < supportSystems.size(); pi++) {
			insertSupportSystem(supportSystems.get(pi), cn);
		}
		BaseDao.commitChanges(cn);
		} catch (SQLException se) {
			BaseDao.rollbackChanges(cn);
		}
	}
	
	public static void insertSupportSystem(SupportSystem p, Connection cn) throws SQLException {
		Statement statement = null;
		StringBuffer sql = new StringBuffer();

		try {
			statement = cn.createStatement();

			sql.append("INSERT INTO "+FN_TABLE+" (");
			sql.append(FN_PATIENT_E_ID + ", ");
			sql.append(FN_SUPPORT_GROUP_ID);
			sql.append(") VALUES (");
			sql.append("aes_encrypt('"+p.getInternalId() + "','" + UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_SUPSYS)+"'), ");
			sql.append(p.getSupportGroupId() + ")");
//			System.out.println(sql.toString());
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
	
	public static void deletePatient(int patientId, int supportGroupId, Connection cn) throws SQLException {
		Statement statement = null;
		StringBuffer sql = new StringBuffer();
		
		try {
			sql.append("DELETE FROM " + FN_TABLE + " WHERE " + FN_PATIENT_E_ID + " = aes_encrypt('"+patientId + "','"+
					UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_SUPSYS)+"')");
			sql.append(" AND "+FN_SUPPORT_GROUP_ID + " = " + supportGroupId);
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
