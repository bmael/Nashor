/**
 * 
 */
package server.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import common.remote.IClient;
import common.remote.IMessageDisplayer;
import common.remote.IServerSubject;

/**
 * @author bmael
 *
 */
public class ServerSubject extends UnicastRemoteObject implements IServerSubject {
	
	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -5696775193014234806L;
	
	private List<IMessageDisplayer> messageDisplayers;
	private String title;
	private IClient owner;
	
	/**
	 * Create a new subject.
	 * @param title of the subject
	 * @param owner of the subject (null if it a default server subject)
	 * @throws RemoteException
	 */
	public ServerSubject(String title, IClient owner) throws RemoteException{
		this.messageDisplayers = new LinkedList<>();
		this.title = title;
		this.owner = owner;
	}
	
	@Override
	public synchronized void join(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.add(c);
	}

	@Override
	public synchronized void leave(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.remove(c);
	}

	@Override
	public void broadcast(String message) throws RemoteException {
		for (IMessageDisplayer displayer : this.messageDisplayers) {
			System.out.println("Call displayer: "+ displayer.toString());
			displayer.display(message);
		}
	}

	@Override
	public String getTitle() throws RemoteException {
		return this.title;
	}

	@Override
	public IClient getOwner() throws RemoteException {
		return this.owner;
	}
	
	@Override
	public String toString(){
		return this.title;
	}
	
}
