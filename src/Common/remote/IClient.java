package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IClient extends Remote {
	
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
	
	/**
	 * Get the server where this client is connected.
	 * @return
	 * @throws RemoteException
	 */
	public IForumServer getServer() throws RemoteException;
	
	/**
	 * Get the list of available subjects on the server.
	 * @return the list of available subjects on the server.
	 * @throws RemoteException
	 */
	public List<IServerSubject> getAvailableSubjects() throws RemoteException;
	
	/**
	 * Return the new subject created on the server.
	 * @param the new subject created on the server.
	 * @return The new server subject created on the server.
	 * @throws RemoteException
	 */
	public IServerSubject getNewSubject(IServerSubject subject) throws RemoteException;
	
	/**
	 * Return the new connected users value.
	 * @param newValue for connected users.
	 * @return The new connected users value.
	 * @throws RemoteException
	 */
	public int getNewConnectedUsersNumber(int newValue) throws RemoteException;
	
	/**
	 * Return the Id of this client.
	 * @return this client id.
	 */
	public int getId() throws RemoteException;
	
	/**
	 * Set the id of the this client.
	 * @param id the new value of this client id.
	 * @throws RemoteException
	 */
	public void setId(int id) throws RemoteException;
}
