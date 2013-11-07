package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IClient extends Remote {
	
	/**
	 * This client exiting the application.
	 * @throws RemoteException
	 */
	public void exit() throws RemoteException;
	
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
	 * Get all subject of the client given in parameter.
	 * @param the client that we have to retrieve its subjects.
	 * @return the subjects of the client.
	 */
	public List<IServerSubject> getMySubject(IClient client) throws RemoteException;
	
	/**
	 * Return the new subject created on the server.
	 * @param the new subject created on the server.
	 * @return The new server subject created on the server.
	 * @throws RemoteException
	 */
	public IServerSubject getNewSubject(IServerSubject subject) throws RemoteException;
	
	/**
	 * Replace the old subjects list with the new one given in parameter.
	 * @param oldValue the old subjects list
	 * @param newValue the new available subjects list
	 * @throws RemoteException
	 */
	public void updateSubjectsList(List<IServerSubject> oldValue, List<IServerSubject> newValue) throws RemoteException;
		/**
	 * Remove from the GUI subjects that have been removed from server.
	 * @param subjects to remove from the GUI.
	 * @throws RemoteException
	 */
	public void removeSubject(List<IServerSubject> subjects) throws RemoteException;
	
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
