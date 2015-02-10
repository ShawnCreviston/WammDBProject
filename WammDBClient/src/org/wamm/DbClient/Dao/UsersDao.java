package org.wamm.DbClient.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wamm.DbClient.BusinessObjects.ClientUserBO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class UsersDao extends BaseDao {

	// Database table names
	public static final String FN_TABLE = "nethipsusers";
	public static final String FN_ALIAS = "nhus";

	public static final String FN_USERNAME = "Username";
	public static final String FN_RIGHTS_EDIT_USERS = "CanEditUsers";
	public static final String FN_RIGHTS_EDIT_SUPPORT_TABLES = "CanEditSupportTables";
	public static final String FN_RIGHT_SYNC = "CanSyncDB";
	
	
	/**
	 * Generic access for patients queries
	 * @param where
	 * @param order
	 * @param cn
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<ClientUserBO> getUsers(String where, String order, Connection cn) throws SQLException {
		return usersList("SELECT * FROM "+FN_TABLE
				+ ((where != null && where.length() > 0) ? (" WHERE " + where) : "")
				+ ((order != null && order.length() > 0) ? (" ORDER BY " + order) : ""), cn);
	}
	
	public static ClientUserBO getUserByUsername(String username, Connection cn) throws SQLException {
		ArrayList<ClientUserBO> users = getUsers(FN_USERNAME + " = '" + username + "'", "", cn);
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
		
	private static ArrayList<ClientUserBO> usersList(String sql, Connection cn) throws SQLException {
		ArrayList<ClientUserBO> users = new ArrayList<ClientUserBO>();
		
		Statement statement = null;

		try {
			statement = cn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ClientUserBO cubo = populateClientUserBO(rs);
				users.add(cubo);
			}
			BaseDao.closeStatement(statement);
		} catch (SQLException sqlEx) {
			throw new SQLException(sqlEx.toString());
		} catch (NullPointerException npe) {
			throw new SQLException(npe.toString());
		} finally {
			BaseDao.closeStatement(statement);
		}
		
		return users;
	}
	
	private static ClientUserBO populateClientUserBO(ResultSet rs) throws SQLException {
		ClientUserBO cubo = new ClientUserBO(
				rs.getString(FN_USERNAME),
				rs.getBoolean(FN_RIGHTS_EDIT_USERS), 
				rs.getBoolean(FN_RIGHT_SYNC), 
				rs.getBoolean(FN_RIGHTS_EDIT_SUPPORT_TABLES));
		return cubo;
	}
}
