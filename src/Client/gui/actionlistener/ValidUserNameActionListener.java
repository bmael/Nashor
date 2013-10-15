package gui.actionlistener;

import general.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ValidUserNameActionListener implements ActionListener {

	private User user;
	private JTextField name;
	
	public ValidUserNameActionListener(User user, JTextField name){
		this.user = user;
		this.name = name;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.user.setName(this.name.getText());
	}

}
