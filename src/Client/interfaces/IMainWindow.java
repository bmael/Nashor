/**
 * 
 */
package interfaces;

import java.util.List;

import remote.IServerSubject;

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
	 * Refresh the GUI.
	 */
	public void refresh();
}
