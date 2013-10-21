/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * This is the server forum interface.
 * @author bmael
 *
 */
public interface IForumServer extends Remote {
		
	/**
	 * Increment the number of connected user by one.
	 */
	public void join(IClient client) throws RemoteException;
	
	/**
	 * Return the subject with the associated name.
	 * @param title, the title of subject to return.
	 * @return the subject with the name given in parameter.
	 * @throws RemoteException
	 */
	public IServerSubject getSubject(String title) throws RemoteException;
	
	/**
	 * Return the list of all subjects available on the server. 
	 * @return subjects, a List of IServerSubject which is the all available subject list.
	 * @throws RemoteException
	 */
	public List<IServerSubject> getAllSubject() throws RemoteException;
	
	/**
	 * This is a test method. Called by a client, the server write "HELLO!!!".
	 * @throws RemoteException
	 */
	public void sayHello() throws RemoteException;
	
	/**
	 * Return the current server date.
	 * @return the current server date.
	 * @throws RemoteException
	 */
	public Date getDate() throws RemoteException;
}
