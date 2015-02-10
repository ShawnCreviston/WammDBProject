package org.wamm.DbClient.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wamm.DbClient.BusinessObjects.Treatment;
import org.wamm.DbClient.Globals.PropertyGlobals;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class TreatmentDao extends BaseDao {

	// Database table names
	public static final String FN_TABLE = "treatment";
	public static final String FN_ALIAS = "tmnt";

	public static final String FN_INTERNAL_ID = "InternalID";
	public static final String FN_TREATMENT = "Treatment";
	public static final String FN_PL_CODE = "PLCode";
	public static final String FN_CURRENT_STATUS = "CurrentStatus";
	
	/**
	 * Generic access for support group queries
	 * @param where
	 * @param order
	 * @param cn
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Treatment> getTreatments(String where, String order, Connection cn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT ");
		sql.append("aes_decrypt("+FN_INTERNAL_ID + ",'"+UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_TREATMENT)+"') AS "+FN_INTERNAL_ID+",");
		sql.append(FN_TREATMENT+",");
		sql.append(FN_PL_CODE+",");
		sql.append(FN_CURRENT_STATUS);
		sql.append(" FROM "+FN_TABLE+" "+FN_ALIAS);
		sql.append(((where != null && where.length() > 0) ? (" WHERE " + where) : ""));
		sql.append(((order != null && order.length() > 0) ? (" ORDER BY " + order) : ""));
		sql.append(") "+FN_ALIAS);
		return treatmentsList(sql.toString(), cn);
	}
		
	private static ArrayList<Treatment> treatmentsList(String sql, Connection cn) throws SQLException {
		ArrayList<Treatment> ter = new ArrayList<Treatment>();
		
		Statement statement = null;

		try {
			statement = cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Treatment t = new Treatment();
				populateTreatmentBO(rs, t);
				ter.add(t);
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
	
	private static void populateTreatmentBO(ResultSet rs, Treatment t) throws SQLException {
		t.setInternalId(rs.getInt(FN_ALIAS+"."+FN_INTERNAL_ID));
		t.setTreatment(rs.getString(FN_ALIAS+"."+FN_TREATMENT));
		t.setPlCode(rs.getString(FN_ALIAS+"."+FN_PL_CODE));
		t.setCurrentStatus(rs.getString(FN_ALIAS+"."+FN_CURRENT_STATUS));
	}
	
	public static void insertTreatment(Treatment t, Connection cn) throws SQLException {
		Statement statement = null;
		
		StringBuffer sql = new StringBuffer();

		try {
			sql.append("INSERT INTO "+FN_TABLE+" (");
			sql.append(FN_INTERNAL_ID);
			if (t.getTreatment() != null && t.getTreatment().length() > 0) {
				sql.append(","+FN_TREATMENT);
			}
			if (t.getPlCode() != null && t.getPlCode().length() > 0) {
				sql.append(","+FN_PL_CODE);
			}
			if (t.getCurrentStatus() != null && t.getCurrentStatus().length() > 0) {
				sql.append(","+FN_CURRENT_STATUS);
			}
			sql.append(") VALUES (");
			sql.append("aes_encrypt('"+t.getInternalId() + "','" + UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DBT_KEY_TREATMENT)+"')");
			if (t.getTreatment() != null && t.getTreatment().length() > 0) {
				sql.append(",'"+t.getTreatment()+"'");
			}
			if (t.getPlCode() != null && t.getPlCode().length() > 0) {
				sql.append(",'"+t.getPlCode()+"'");
			}
			if (t.getCurrentStatus() != null && t.getCurrentStatus().length() > 0) {
				sql.append(",'"+t.getCurrentStatus()+"'");
			}
			sql.append(")");
			
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
