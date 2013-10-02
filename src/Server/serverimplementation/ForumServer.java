/**
 * 
 */
package serverimplementation;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import remote.IServerForum;
import remote.IServerSubject;

/**
 * This class provides an implementation of the forum server.
 * @author bmael
 *
 */
public class ForumServer implements IServerForum{

	private List<IServerSubject> subjects;
	
	public ForumServer(){
		this.subjects = new LinkedList<>();
	}
	
	@Override
	public IServerSubject getSubject(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
