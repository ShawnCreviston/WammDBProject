package org.wamm.DbClient.BusinessObjects;

public class ClientUserBO {

	private String username;
	
	private boolean canEditUsers;
	private boolean canSyncDb;
	private boolean canEditSupportTables;
	
	@SuppressWarnings("unused")
	private ClientUserBO() {
		
	}
	
	public ClientUserBO(String username, boolean canEditUsers, boolean canSyncDb, boolean canEditSupportTables) {
		this.username = username;
		this.canEditUsers = canEditUsers;
		this.canSyncDb = canSyncDb;
		this.canEditSupportTables = canEditSupportTables;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the canEditUsers
	 */
	public boolean canEditUsers() {
		return canEditUsers;
	}
//	/**
//	 * @param canEditUsers the canEditUsers to set
//	 */
//	public void setCanEditUsers(boolean canEditUsers) {
//		this.canEditUsers = canEditUsers;
//	}
	/**
	 * @return the canSyncDb
	 */
	public boolean canSyncDb() {
		return canSyncDb;
	}
//	/**
//	 * @param canSyncDb the canSyncDb to set
//	 */
//	public void setCanSyncDb(boolean canSyncDb) {
//		this.canSyncDb = canSyncDb;
//	}
	/**
	 * @return the canEditSupportTables
	 */
	public boolean canEditSupportTables() {
		return canEditSupportTables;
	}
//	/**
//	 * @param canEditSupportTables the canEditSupportTables to set
//	 */
//	public void setCanEditSupportTables(boolean canEditSupportTables) {
//		this.canEditSupportTables = canEditSupportTables;
//	}

}
