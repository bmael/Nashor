/**
 * 
 */
package serverimplementation;

import java.io.Serializable;
import java.rmi.RemoteException;

import remote.IMessageDisplayer;
import remote.IServerSubject;

/**
 * @author bmael
 *
 */
public class ServerSubject implements IServerSubject, Serializable {
	
	/**
	 * The generated vesion UID.
	 */
	private static final long serialVersionUID = -7627909707389954486L;
	
	private String title;
	
	public ServerSubject(String title){
		this.title = title;
	}
	
	/* (non-Javadoc)
	 * @see remote.IServerSubject#join(remote.IMessageDisplayer)
	 */
	@Override
	public void join(IMessageDisplayer c) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see remote.IServerSubject#left(remote.IMessageDisplayer)
	 */
	@Override
	public void leave(IMessageDisplayer c) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see remote.IServerSubject#broadcast(java.lang.String)
	 */
	@Override
	public void broadcast(String message) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see remote.IServerSubject#getTitle()
	 */
	@Override
	public String getTitle() throws RemoteException {
		return this.title;
	}

}
