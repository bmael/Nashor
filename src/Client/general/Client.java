/**
 * 
 */
package general;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.JLabel;

import remote.IClient;
import remote.IForumServer;
import remote.IServerSubject;

/**
 * @author bmael
 *
 */
public class Client extends UnicastRemoteObject implements IClient{

	/**
	 * The generated serial UID.
	 */
	private static final long serialVersionUID = -6854348577220808371L;
	
	private int id = 0;
	
	private String name;
	private IForumServer server;
	
	private JLabel connectedUsersNumber;
	
	public Client(IForumServer server) throws RemoteException {
		id = id ++;
		this.server = server;
		this.connectedUsersNumber = new JLabel("Connected users:" + 0);
		this.name = "";
	}
	
	public Client(IForumServer server, String name) throws RemoteException {
		id = id ++;
		this.server = server;
		this.connectedUsersNumber = new JLabel("Connected users:" + 0);
		this.name = name;
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}
	
	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public void updateConnectedUsersNumber(int newValue) throws RemoteException {
		this.connectedUsersNumber = new JLabel("Connected users:" + newValue);
	}

	@Override
	public JLabel getConnectedUsersNumberLabel() throws RemoteException {
		return this.connectedUsersNumber;		
	}

	@Override
	public IForumServer getServer() throws RemoteException {
		return this.server;
	}
	
	@Override
	public String toString(){
		return this.name.equals("") ? Integer.toString(id) : this.name;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) throws RemoteException {
		this.id = id;
	}

	@Override
	public List<IServerSubject> getAvailableSubjects() throws RemoteException {
		return this.server.getAllSubject();
	}
}
