/**
 * 
 */
package gui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;

import remote.IServerSubject;

/**
 * This class provides action to do when a client wants to send a message.
 * @author bmael
 *
 */
public class SendMessageActionListener implements ActionListener{

	private IServerSubject subject;
	private JTextField textField;
	
	/**
	 * Construct a new instance of an action listener for a send button.
	 * @param subject
	 * @param textField
	 */
	public SendMessageActionListener(IServerSubject subject, JTextField textField){
		this.subject = subject;
		this.textField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!textField.getText().equals("")){
			try {
				System.out.println("Broadcasting message: " + textField.getText());
				subject.broadcast(textField.getText());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			textField.setText("");
		}
	}

}
