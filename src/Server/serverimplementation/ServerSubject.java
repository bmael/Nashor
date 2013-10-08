/**
 * 
 */
package serverimplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import remote.IMessageDisplayer;
import remote.IServerSubject;

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
	
	public ServerSubject(String title) throws RemoteException{
		this.messageDisplayers = new LinkedList<>();
		this.title = title;
	}
	
	/* (non-Javadoc)
	 * @see remote.IServerSubject#join(remote.IMessageDisplayer)
	 */
	@Override
	public void join(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.add(c);
	}

	/* (non-Javadoc)
	 * @see remote.IServerSubject#left(remote.IMessageDisplayer)
	 */
	@Override
	public void leave(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.remove(c);
	}

	/* (non-Javadoc)
	 * @see remote.IServerSubject#broadcast(java.lang.String)
	 */
	@Override
	public void broadcast(String message) throws RemoteException {
		for (IMessageDisplayer displayer : this.messageDisplayers) {
			System.out.println("Call displayer: "+ displayer.toString());
			displayer.display(message);
		}
	}

	/* (non-Javadoc)
	 * @see remote.IServerSubject#getTitle()
	 */
	@Override
	public String getTitle() throws RemoteException {
		return this.title;
	}

}
