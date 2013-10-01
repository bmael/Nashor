/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the server forum interface.
 * @author bmael
 *
 */
public interface IServerForum extends Remote {
	
	/**
	 * Return the subject with the associated name.
	 * @param title, the title of subject to return.
	 * @return the subject with the name given in parameter.
	 * @throws RemoteException
	 */
	public IServerSubject getSubject(String title) throws RemoteException;
}
