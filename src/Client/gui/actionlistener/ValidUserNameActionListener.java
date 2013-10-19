package gui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;

import remote.IClient;

public class ValidUserNameActionListener implements ActionListener {

	private IClient user;
	private JTextField name;
	
	public ValidUserNameActionListener(IClient user, JTextField name){
		this.user = user;
		this.name = name;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.user.setName(this.name.getText());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
