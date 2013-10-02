/**
 * 
 */
package subject;

import java.rmi.RemoteException;

import remote.IMessageDisplayer;

/**
 * This class provides a Message Displayer representation
 * @author bmael
 *
 */
public class MessageDisplayer implements IMessageDisplayer{

	@Override
	public void display(String message) throws RemoteException {
		System.out.println(message);
	}

}
