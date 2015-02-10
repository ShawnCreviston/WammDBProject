package org.wamm.DbClient.GUI.objects;

import org.wamm.DbClient.BusinessObjects.SupportGroup;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class SupportGroupComboBoxEntry {
	
	private SupportGroup supportGroup;
	
	public SupportGroupComboBoxEntry(SupportGroup supportGroup) {
		this.supportGroup = supportGroup;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return supportGroup.getSupportGroupName();
	}

	/**
	 * @return the sg
	 */
	public SupportGroup getSupportGroup() {
		return supportGroup;
	}
}
