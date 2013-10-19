/**
 * 
 */
package general;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import remote.IClient;

/**
 * @author bmael
 *
 */
public class Client extends UnicastRemoteObject implements IClient{

	/**
	 * The generated serial UID.
	 */
	private static final long serialVersionUID = -6854348577220808371L;
	
	private String name;
	private int connectedUsers;
	
	public Client() throws RemoteException {}
	
	public Client(String name) throws RemoteException {
		this.name = name;
		this.connectedUsers = 0;
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}
	
	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public void updateConnectedUsersNumber(int newValue) throws RemoteException {
		this.connectedUsers = newValue;
		System.out.println(this + " connected users is/are " + this.connectedUsers);
	}

	@Override
	public int getConnectedUsersNumber() throws RemoteException {
		return this.connectedUsers;
	}
	
	public String toString(){
		return this.name;
	}
}
