package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JLabel;

public interface IClient extends Remote{
	
	/**
	 * Return the Connected Users Number Label of this client.
	 * @return the JLabel of this client for connected users number.
	 * @throws RemoteException
	 */
	public JLabel getConnectedUsersNumberLabel() throws RemoteException;
	
	/**
	 * Update the number of connected user stored in this client.
	 * @throws RemoteException
	 */
	public void updateConnectedUsersNumber(int newValue) throws RemoteException;
	
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
