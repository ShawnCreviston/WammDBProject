package org.wamm.DbClient.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.wamm.DbClient.Globals.PropertyGlobals;
import org.wamm.DbClient.ValueObjects.UserSessionVO;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class BaseDao {

	public static Connection createSQLLiteConnection() throws SQLException {
		Connection cn = null;
		
		try {
			// Driver to use for SQL Lite sever
			Class.forName("org.sqlite.JDBC");
			cn = DriverManager.getConnection("jdbc:sqlite:database/WAMM DB");
//			Class.forName("org.gjt.mm.mysql.Driver");
//			cn = DriverManager.getConnection("URL",
//					"User",
//					"Pass");
		} catch (Exception e) {
			System.out.println("SQL Server Connection Failed: "+ e);
			throw new SQLException(e.toString());
		}
		return cn;
	}
	
	/**
	 * Create a connection to the database, use SSL for any connections looking up patient data.
	 * Currently useSSL is forced to TRUE.
	 * @param useSSL
	 * @return
	 * @throws SQLException
	 */
	public static Connection createMySqlConnection(boolean useSSL) throws SQLException {
		Connection cn = null;
		useSSL =  true;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://"+System.getProperty(PropertyGlobals.PROPERTY_DB_MYSQL_IP)+"/sl_nethips" +
					(useSSL?"?verifyServerCertificate=false&useSSL=true&requireSSL=true": ""),
					UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DB_MYSQL_USERNAME),
					UserSessionVO.getSession().getDbKeys().getTableKey(PropertyGlobals.PROPERTY_DB_MYSQL_USERPASS));
		} catch (Exception e) {
			System.out.println("Create Connection Failed: "+ e);
			throw new SQLException(e.toString());
		}
		return cn;
	}
	
	public static void startTransaction(Connection cn) throws SQLException {
		cn.setAutoCommit(false);
	}
	
	public static void commitChanges(Connection cn) throws SQLException {
		cn.commit();
	}

	public static void rollbackChanges(Connection cn) throws SQLException {
		cn.rollback();
	}

	/** Closes database resources to free them for garbage collection
	 * @param conn
	 * @param stmt
	 */
	public static void closeDBResources(Connection cn, Statement stmt) throws SQLException {
		try {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				// Do nothing
			}
			if (cn != null && !cn.isClosed()) {
				cn.close();
			}
		} catch (NullPointerException npe) {
			// Do nothing
		} finally {
			stmt = null;
			cn = null;
		}
	}
	
	/**
	 * Closes a connection.
	 * <br>
	 * NOTE: Any exceptions thrown are trapped by this method. If you want to handle
	 * an exception, call <code>closeDBResources()</code> and pass in null for the
	 * <code>Statement</code> argument instead.
	 * @param cn ( Connection ) The connection to close
	 */
	public static void closeConnection(Connection cn) {
		try {
			closeDBResources(cn, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * Closes a statement.
	 * <br>
	 * NOTE: Any exceptions thrown are trapped by this method. If you want to handle
	 * an exception, call <code>closeDBResources()</code> and pass in null for the
	 * <code>Connection</code> argument instead.
	 * @param cn ( Statement ) The statement to close
	 */
	public static void closeStatement(Statement statement) {
		try {
			closeDBResources(null, statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String escapeSQLCharacters(String originalString) {
		if (originalString.indexOf("'") > -1) {
			originalString = originalString.replace("'", "''");
		}
		return originalString;
	}
}
