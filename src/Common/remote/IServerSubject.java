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
	public void leave(IMessageDisplayer c) throws RemoteException;
	
	/**
	 * Broadcast a message.
	 * @param message the message to broadcast
	 * @throws RemoteException
	 */
	public void broadcast(String message) throws RemoteException;
	
	/**
	 * Return the title of this subject.
	 * @return title, the title of this subject.
	 * @throws RemoteException
	 */
	public String getTitle() throws RemoteException;
	
	/**
	 * Return this subject owner.
	 * @return the owner of this subject or null if the subject is a default server subject.
	 * @throws RemoteException
	 */
	public IClient getOwner() throws RemoteException;
}
