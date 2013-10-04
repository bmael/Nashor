package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerSubject extends Remote {
	
	/**
	 * Sign in the client message displayer c to this subject.
	 * @param c the client message displayer.
	 * @throws RemoteException
	 */
	public void join(IMessageDisplayer c) throws RemoteException;
	
	/**
	 * Sign out the client message displayer c of this subject.
	 * @param c
	 * @throws RemoteException
	 */
	public void left(IMessageDisplayer c) throws RemoteException;
	
	/**
	 * Broadcast a message.
	 * @param message the message to broadcast
	 * @throws RemoteException
	 */
	public void broadcast(String message) throws RemoteException;
	
	/**
	 * Get the title of this subject
	 * @return title, the title of this subject
	 */
	public String getTitle();
}
