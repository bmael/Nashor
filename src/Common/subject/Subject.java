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
	
	public Subject(){
		this.messageDisplayers = new LinkedList<>();
	}
	
	@Override
	public void join(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.add(c);
	}

	@Override
	public void left(IMessageDisplayer c) throws RemoteException {
		this.messageDisplayers.remove(c);
	}

	@Override
	public void broadcast(String message) throws RemoteException {
		for (IMessageDisplayer displayer : this.messageDisplayers) {
			displayer.display(message);
		}
	}

}
