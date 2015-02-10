package org.wamm.DbClient.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wamm.DbClient.BusinessObjects.Territory;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class TerritoryDao extends BaseDao {

	// Database table names
	public static final String FN_TABLE = "territory";
	public static final String FN_ALIAS = "trty";

	public static final String FN_TERRITORY_ID = "TerritoryID";
	public static final String FN_NAME = "Name";
	public static final String FN_TERRITORY_TYPE = "TerritoryType";
	public static final String FN_PARENT_ID = "ParentTerritoryID";
	
	
	/**
	 * Generic access for patients queries
	 * @param where
	 * @param order
	 * @param cn
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Territory> getTerritories(String where, String order, Connection cn) throws SQLException {
		return territoryList("SELECT * FROM "+FN_TABLE+" AS "+FN_ALIAS
				+ ((where != null && where.length() > 0) ? (" WHERE " + where) : "")
				+ ((order != null && order.length() > 0) ? (" ORDER BY " + order) : ""), cn);
	}
		
	private static ArrayList<Territory> territoryList(String sql, Connection cn) throws SQLException {
		ArrayList<Territory> ter = new ArrayList<Territory>();
		
		Statement statement = null;

		try {
			statement = cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Territory t = new Territory();
				populateTerritoryBO(rs, t);
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
	
	private static void populateTerritoryBO(ResultSet rs, Territory t) throws SQLException {
		t.setTerritoryId(rs.getInt(FN_TERRITORY_ID)); //FN_ALIAS+"."+
		t.setTerritoryName(rs.getString(FN_NAME));
		t.setTerritoryType(rs.getString(FN_TERRITORY_TYPE));
		t.setParentTerritoryId(rs.getInt(FN_PARENT_ID));
	}
	
	public static void insertTerritory(Territory ter, Connection cn) throws SQLException {
		Statement statement = null;
		
		StringBuffer sql = new StringBuffer();

		try {
			sql.append("INSERT INTO "+FN_TABLE+" (");
			if (ter.getTerritoryId() > 0) {
				sql.append(FN_TERRITORY_ID + ",");
			}
			sql.append("'"+FN_NAME + "','");
			sql.append(FN_TERRITORY_TYPE + "',");
			sql.append(FN_PARENT_ID);
			sql.append(") VALUES (");
			if (ter.getTerritoryId() > 0) {
				sql.append(ter.getTerritoryId()+",");
			}
			sql.append("'"+ter.getTerritoryName()+"',");
			sql.append("'"+ter.getTerritoryType()+"',");
			sql.append(ter.getParentTerritoryId()+")");
			
			statement = cn.createStatement();
			statement.executeUpdate(sql.toString());

			if (ter.getTerritoryId() == 0) {
				ResultSet rs = null;
				try {
					rs = statement.getGeneratedKeys();
					if (rs.next()) {
						ter.setTerritoryId(rs.getInt(1));
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
