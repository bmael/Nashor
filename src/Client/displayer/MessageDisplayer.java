/**
 * 
 */
package displayer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

import remote.IMessageDisplayer;

/**
 * @author bmael
 *
 */
public class MessageDisplayer extends UnicastRemoteObject implements IMessageDisplayer {
	
	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -6468219716789966797L;
	
	private JEditorPane printZone;
	
	/**
	 * Construct a new instance of a message displayer and 
	 * initialize the JTextArea where this displayer have to display the message.
	 * @param printZone
	 */
	public MessageDisplayer(JEditorPane printZone) throws RemoteException{
		this.printZone = printZone;
	}
	
	/**
	 * Construct a new instance of a message displayer.
	 */
	public MessageDisplayer()  throws RemoteException {}
	
	/**
	 * Set the JTextArea where this displayer have to display the message.
	 * @param printZone the JEditorPane where this displayer have to display the message.
	 */
	public void setPrintZone(JEditorPane printZone){
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
