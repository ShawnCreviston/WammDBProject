package org.wamm.DbClient.GUI.objects;

import org.wamm.DbClient.BusinessObjects.Territory;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class TerritoryComboBoxEntry {
	
	private Territory ter;
	
	public TerritoryComboBoxEntry(Territory ter) {
		this.ter = ter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + ter.getTerritoryType() + ") " + ter.getTerritoryName();
	}

	/**
	 * @return the ter
	 */
	public Territory getTerritory() {
		return ter;
	}
	
	
}
