/**
 * 
 */
package client.mainwindow.gui;

import java.util.List;

import common.remote.IServerSubject;

/**
 * This interface provides methods needed for the client GUI. 
 * @author bmael
 *
 */
public interface IMainWindow {
	
	/**
	 * Add the subject given in parameter to the GUI.
	 * @param subject to add to the GUI.
	 */
	public void addSubject(IServerSubject subject);
	
	/**
	 * Add all subjects from the given list in parameter to the GUI.
	 * @param subjects to add to the GUI.
	 */
	public void addSubjects(List<IServerSubject> subjects);
	
	/**
	 * Remove the given subjects on the GUI.
	 * @param subjects to remove.
	 */
	public void removeSubjects(List<IServerSubject> subjects);
	
	/**
	 * Update the connected users number.
	 * @param newValue for the connected users number.
	 */
	public void updateConnectedUsersNumber(int newValue);
	
	/**
	 * Refresh the GUI.
	 */
	public void refresh();
}
