package org.wamm.DbClient.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wamm.DbClient.BusinessObjects.SupportGroup;
import org.wamm.DbClient.BusinessObjects.Territory;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class SupportGroupDao extends BaseDao {

	// Database table names
	public static final String FN_TABLE = "supportgroup";
	public static final String FN_ALIAS = "sptgp";

	public static final String FN_SUPPORT_GROUP_ID = "SupportGroupID";
	public static final String FN_NAME = "Name";
	public static final String FN_TERRITORY_ID = "TerritoryID";
	
	/**
	 * Generic access for support group queries
	 * @param where
	 * @param order
	 * @param cn
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<SupportGroup> getSupportGroups(String where, String order, Connection cn) throws SQLException {
		return supportGroupList("SELECT * FROM "+FN_TABLE+" AS "+FN_ALIAS
				+ ((where != null && where.length() > 0) ? (" WHERE " + where) : "")
				+ ((order != null && order.length() > 0) ? (" ORDER BY " + order) : ""), cn);
	}
		
	private static ArrayList<SupportGroup> supportGroupList(String sql, Connection cn) throws SQLException {
		ArrayList<SupportGroup> ter = new ArrayList<SupportGroup>();
		
		Statement statement = null;

		try {
			statement = cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				SupportGroup sg = new SupportGroup();
				populateSupportGroupBO(rs, sg);
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
	
	private static void populateSupportGroupBO(ResultSet rs, SupportGroup sg) throws SQLException {
		sg.setSupportGroupID(rs.getInt(FN_ALIAS+"."+FN_TERRITORY_ID));
		sg.setSupportGroupName(rs.getString(FN_ALIAS+"."+FN_NAME));
		
		int territoryId = rs.getInt(FN_ALIAS+"."+FN_TERRITORY_ID);
		if (territoryId > 0) {
			Territory ter = new Territory();
			ter.setTerritoryId(territoryId);
			sg.setTerritory(ter);
		}
	}
	
	public static void insertSupportGroup(SupportGroup sg, Connection cn) throws SQLException {
		Statement statement = null;
		
		StringBuffer sql = new StringBuffer();

		try {
			sql.append("INSERT INTO "+FN_TABLE+" (");
			if (sg.getSupportGroupID() > 0) {
				sql.append(FN_SUPPORT_GROUP_ID + ",");
			}
			sql.append(FN_NAME);
			if (sg.getTerritory() != null) {
				sql.append(","+FN_TERRITORY_ID);
			}
			sql.append(") VALUES (");
			if (sg.getSupportGroupID() > 0) {
				sql.append(sg.getSupportGroupID()+",");
			}
			sql.append("'"+sg.getSupportGroupName()+"'");
			if (sg.getTerritory() != null) {
				sql.append(","+sg.getTerritory().getTerritoryId());
			}
			sql.append(")");
			
			statement = cn.createStatement();
			statement.executeUpdate(sql.toString());

			if (sg.getSupportGroupID() < 1) {
				ResultSet rs = null;
				try {
					rs = statement.getGeneratedKeys();
					if (rs.next()) {
						sg.setSupportGroupID(rs.getInt(1));
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
}
