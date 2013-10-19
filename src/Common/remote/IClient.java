package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote{
	
	/**
	 * Update the number of connected user stored in this client.
	 * @throws RemoteException
	 */
	public void updateConnectedUsersNumber(int newValue) throws RemoteException;
	
	/**
	 * Get the number of connected users for the server where this client is connected.
	 * @return the number of user client connected.
	 * @throws RemoteException
	 */
	public int getConnectedUsersNumber() throws RemoteException;
	
	/**
	 * Set the name of this client.
	 * @param name, the name of this client.
	 * @throws RemoteException
	 */
	public void setName(String name) throws RemoteException;
	
	/**
	 * Get the name of this client.
	 * @return the name of this client.
	 * @throws RemoteException
	 */
	public String getName() throws RemoteException;
}
