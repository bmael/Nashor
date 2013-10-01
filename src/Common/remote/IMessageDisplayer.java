/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the interface to displaying message to a client.
 * @author bmael
 *
 */
public interface IMessageDisplayer extends Remote {

	/**
	 * Display the given message on this message displayer.
	 * @param message, the message to display.
	 * @throws RemoteException
	 */
	public void display(String message) throws RemoteException;
}
