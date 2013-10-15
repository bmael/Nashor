package gui;

import general.User;
import gui.actionlistener.ValidUserNameActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPanel extends JPanel{

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -449738241414461419L;

	private User user;
	
	private JTextField name;
	private JButton validButton;
	
	public UserPanel(User user){
		this.user = user;
		
		this.init();
	}
	
	private void init(){
		this.name = new JTextField(10);
		this.validButton = new JButton("Ok");
		this.validButton.addActionListener(new ValidUserNameActionListener(user, name));
		
		this.add(name);
		this.add(validButton);
	}
	
}
