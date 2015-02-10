package org.wamm.DbClient.Dao;

import java.util.HashMap;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class DBKeys {

	private HashMap<String, String> dbKeys;
	
	public DBKeys() {
		dbKeys = new HashMap<String, String>();
	}
	
	public void setTableKey(String tableId, String tableKey) {
		dbKeys.put(tableId, tableKey);
	}
	
	public String getTableKey(String tableId) {
		return dbKeys.get(tableId);
	}
}
