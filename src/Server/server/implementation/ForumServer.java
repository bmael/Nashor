/**
 * 
 */
package server.implementation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import common.remote.IClient;
import common.remote.IForumServer;
import common.remote.IServerSubject;

/**
 * This class provides an implementation of the forum server.
 * 
 * @author bmael
 * 
 */
public class ForumServer extends UnicastRemoteObject implements IForumServer {

	/**
	 * The generated serial version UID
	 */
	private static final long serialVersionUID = 5823020808076392213L;

	/**
	 * The list of all subject available for clients.
	 */
	private List<IServerSubject> subjects;

	/**
	 * The list of the Clients connected to this server.
	 */
	private List<IClient> clients;

	/**
	 * Construct a new instance of the ForumServer/.
	 */
	public ForumServer() throws RemoteException {
		this.subjects = new LinkedList<>();
		this.clients = new LinkedList<>();
		// this.CreateDefaultSubjects();
	}

	/**
	 * Return the subject from the server according to the title given in
	 * parameter.
	 * 
	 * @param title
	 *            , the title of the subject to return.
	 * @return the subject with the given title, or null if no subject has the
	 *         given title.
	 */
	@Override
	public synchronized IServerSubject getSubject(String title)
			throws RemoteException {
		for (IServerSubject subject : this.subjects) {
			if (subject.getTitle().equals(title)) {
				return subject;
			}
		}
		return null;
	}

	@Override
	public synchronized void sayHello() throws RemoteException {
		System.out.println("HELLO!!!");
	}

	@Override
	public synchronized List<IServerSubject> getAllSubject()
			throws RemoteException {
		return this.subjects;
	}

	@Override
	public synchronized List<IServerSubject> getSubjectsOfClient(IClient client)
			throws RemoteException {
		List<IServerSubject> res = new LinkedList<>();
		System.err.println("CLIENNNTTTTTTTT is : " + client);
		for (IServerSubject subject : this.subjects) {
			if (subject.getOwner() != null && subject.getOwner().equals(client)) {
				res.add(subject);
			}
		}

		return res;
	}

	/**
	 * Create default subjects for the Nashor Chat.
	 */
	// private synchronized void CreateDefaultSubjects() throws RemoteException{
	// this.subjects.add(new ServerSubject("Sports", null));
	// this.subjects.add(new ServerSubject("Games", null));
	// this.subjects.add(new ServerSubject("Movies", null));
	// this.subjects.add(new ServerSubject("Cats", null));
	// }

	@Override
	public synchronized void join(IClient client) throws RemoteException {

		try {
			this.getNewClientId(client);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		this.clients.add(client);

		int connectedClientNumber = this.clients.size();

		for (IClient c : this.clients) {
			c.getNewConnectedUsersNumber(connectedClientNumber);
		}
	}

	@Override
	public synchronized Date getDate() throws RemoteException {
		Date now = new Date();
		return now;
	}

	private synchronized void getNewClientId(IClient client)
			throws RemoteException {
		if (this.clients.isEmpty()) {
			client.setId(1);
		} else {
			client.setId(this.clients.get(this.clients.size() - 1).getId() + 1);
		}
	}

	@Override
	public synchronized void createSubject(String url, String title,
			IClient owner) throws RemoteException {

		IServerSubject subject = new ServerSubject(title, owner);
		System.out.println(" ** Storing the " + title + " subject with url: "
				+ url);
		try {
			Naming.rebind(url, subject);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		System.out.println(" ** " + title + " subject is online");

		this.subjects.add(subject);

		for (IClient c : this.clients) {
			c.getNewSubject(subject);
		}
	}

	@Override
	public void createSubject(IServerSubject subject) throws RemoteException {
		this.subjects.add(subject);
	}

	@Override
	public synchronized void removeSubject(String url, String title)
			throws RemoteException {
		List<IServerSubject> old = new LinkedList<>(this.subjects);

		this.subjects.remove(this.getSubject(title));

		for (IClient c : this.clients) {
			c.updateSubjectsList(old, subjects);
		}
		
		try {
			System.out.println(" ** Unbinding " + title + " subject");
			Naming.unbind(url);
			System.out.println(" ** " + title + " subject exiting.");
		} catch (MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void left(IClient client) throws RemoteException {

		// remove all subject administrated by this client
		List<IServerSubject> toRemove = new LinkedList<>();

		for (IServerSubject subject : this.subjects) {
			if (subject.getOwner() != null && subject.getOwner().equals(client)) {
				toRemove.add(subject);
			}
		}

		clients.remove(client);

		int connectedUsers = this.clients.size();
		for (IClient c : this.clients) {
			c.getNewConnectedUsersNumber(connectedUsers);
			c.removeSubject(toRemove);
		}

		this.subjects.removeAll(toRemove);
	}
}
