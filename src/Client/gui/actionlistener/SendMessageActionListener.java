/**
 * 
 */
package gui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;

import remote.IClient;
import remote.IServerSubject;

/**
 * This class provides action to do when a client wants to send a message.
 * @author bmael
 *
 */
public class SendMessageActionListener implements ActionListener{

	private IServerSubject subject;
	private JTextField textField;
	private IClient user;
	
	/**
	 * Construct a new instance of an action listener for a send button.
	 * @param subject
	 * @param textField
	 */
	public SendMessageActionListener(IServerSubject subject, JTextField textField, IClient user){
		this.subject = subject;
		this.textField = textField;
		this.user = user;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!textField.getText().equals("")){
			try {
				SimpleDateFormat ft = new SimpleDateFormat("[H:mm]");
				subject.broadcast(ft.format(this.user.getServer().getDate()) + " " + this.user + ": " + textField.getText());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			textField.setText("");
		}
	}

}
