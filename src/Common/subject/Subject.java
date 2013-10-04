/**
 * 
 */
package subject;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import remote.IMessageDisplayer;
import remote.IServerSubject;

/**
 * This class is the subject representation.
 * @author bmael
 *
 */
public class Subject implements IServerSubject{

	private List<IMessageDisplayer> messageDisplayers;
	private String title;
	
	public Subject(String title){
		this.messageDisplayers = new LinkedList<>();
		this.title = title;
	}
	
	@Override
	public void join(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.add(c);
	}

	@Override
	public void leave(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.remove(c);
	}

	@Override
	public void broadcast(String message) throws RemoteException {
		for (IMessageDisplayer displayer : this.messageDisplayers) {
			displayer.display(message);
		}
	}

	@Override
	public String getTitle() throws RemoteException {
		return this.title;
	}

}
