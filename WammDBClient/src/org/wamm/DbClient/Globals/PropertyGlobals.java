package org.wamm.DbClient.Globals;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class PropertyGlobals {
	
	// System Properties ---------------------------------------------------------------
	public static final String PROPERTY_TEST_MODE = "program.mode";
	public static final String PROPERTY_TEST_FLAG = "TESTING";
	public static final String PROPERTY_LIVE_FLAG = "LIVE";
	
	public static final String PROPERTY_DB_MYSQL_IP =       "program.mysql.ip";
	public static final String PROPERTY_DB_MYSQL_USERNAME = "program.mysql.username";
	public static final String PROPERTY_DB_MYSQL_USERPASS = "program.mysql.userpass";
	
	public static final String PROPERTY_DB_ID =             "program.dbid";
	public static final String PROPERTY_DB_KEY_FILE =       "program.dbKeyFile";
	
	public static final String PROPERTY_DBT_KEY_PATIENT =   "program.dbTKey.Patient";
	public static final String PROPERTY_DBT_KEY_TREATMENT = "program.dbTKey.Treatment";
	public static final String PROPERTY_DBT_KEY_PLHIV =     "program.dbTKey.PLHIV";
	public static final String PROPERTY_DBT_KEY_SUPSYS =    "program.dbTKey.SupSys";
	public static final String PROPERTY_DBT_KEY_SUPTRAN =   "program.dbTKey.SupTran";
	public static final String PROPERTY_DBT_KEY_USERS =     "program.dbTKey.Users";
}
