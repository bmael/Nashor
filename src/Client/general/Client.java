/**
 * 
 */
package general;

import gui.MainWindow;
import interfaces.IGUILaunchable;
import interfaces.IMainWindow;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import remote.IClient;
import remote.IForumServer;
import remote.IServerSubject;

/**
 * @author bmael
 *
 */
public class Client extends UnicastRemoteObject implements IClient, IGUILaunchable{

	/**
	 * The generated serial UID.
	 */
	private static final long serialVersionUID = -6854348577220808371L;
	
	private int id = 0;
	
	private String name;
	private IForumServer server;
	private IMainWindow gui;
	
	public Client(IForumServer server) throws RemoteException {
		id = id ++;
		this.server = server;
		//this.connectedUsersNumber = new JLabel("Connected users:" + 0);
		this.name = "";
		this.gui = new MainWindow(this);
	}
	
	public Client(IForumServer server, String name) throws RemoteException {
		id = id ++;
		this.server = server;
		this.name = name;
		this.gui = new MainWindow(this);
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}
	
	public void setName(String name) throws RemoteException {
		this.name = name;
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
		List<IServerSubject> subjects = this.server.getAllSubject(); 
		this.gui.addSubjects(subjects);
		this.refreshGUI();
		return subjects;
	}

	@Override
	public void refreshGUI() {
		this.gui.refresh();
	}

	@Override
	public IServerSubject getNewSubject(IServerSubject subject) throws RemoteException {
		this.gui.addSubject(subject);
		this.refreshGUI();
		return subject;
	}

	@Override
	public int getNewConnectedUsersNumber(int newValue) throws RemoteException {
		this.gui.updateConnectedUsersNumber(newValue);
		return newValue;
	}
}
