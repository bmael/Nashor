/**
 * 
 */
package displayer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JTextArea;

import remote.IMessageDisplayer;

/**
 * @author bmael
 *
 */
public class MessageDisplayer extends UnicastRemoteObject implements IMessageDisplayer {
	
	private JTextArea printZone;
	
	/**
	 * Construct a new instance of a message displayer and 
	 * initialize the JTextArea where this displayer have to display the message.
	 * @param printZone
	 */
	public MessageDisplayer(JTextArea printZone) throws RemoteException{
		this.printZone = printZone;
	}
	
	/**
	 * Construct a new instance of a message displayer.
	 */
	public MessageDisplayer()  throws RemoteException {}
	
	/**
	 * Set the JTextArea where this displayer have to display the message.
	 * @param printZone the JTextArea where this displayer have to display the message.
	 */
	public void setPrintZone(JTextArea printZone){
		this.printZone = printZone;
	}
	
	/* (non-Javadoc)
	 * @see remote.IMessageDisplayer#display(java.lang.String)
	 */
	@Override
	public void display(String message) throws RemoteException {
		this.printZone.setText(this.printZone.getText() + message + "\n");
	}

}
