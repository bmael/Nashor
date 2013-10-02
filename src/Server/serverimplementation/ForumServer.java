/**
 * 
 */
package serverimplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import remote.IForumServer;
import remote.IServerSubject;

/**
 * This class provides an implementation of the forum server.
 * @author bmael
 *
 */
public class ForumServer extends UnicastRemoteObject 
						 implements IForumServer
 {

	/**
	 * The generated serial version UID
	 */
	private static final long serialVersionUID = 5823020808076392213L;
	
	/**
	 * The list of all subject available for clients.
	 */
	private List<IServerSubject> subjects;
	
	/**
	 * Construct a new instance of the ForumServer/.
	 */
	public ForumServer() throws RemoteException{
		this.subjects = new LinkedList<>();
	}
	
	/**
	 * Return the subject from the server according to the title given in parameter.
	 * @param title, the title of the subject to return.
	 * @return the subject with the given title, or null if no subject has the given title.
	 */
	@Override
	public IServerSubject getSubject(String title) throws RemoteException {
		for(IServerSubject subject : this.subjects){
			if(subject.getTitle().equals(title)){
				return subject;
			}
		}
		
		return null;
	}

	@Override
	public void sayHello() throws RemoteException {
		System.out.println("HELLO!!!");
	}

}
