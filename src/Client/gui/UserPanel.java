package gui;

import general.Client;
import gui.actionlistener.ValidUserNameActionListener;
import gui.keylistener.ValidEnterKeyAdapter;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;

public class UserPanel extends JPanel{

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -449738241414461419L;

	private Client user;
	
	private JTextField name;
	private JButton validButton;
	
	public UserPanel(Client user){
		this.user = user;

		this.init();
	}
	
	private void init(){
		
		this.name = new JTextField(10);
		PromptSupport.setPrompt("Pseudo", this.name);
		
		this.validButton = new JButton("Ok");
		this.validButton.addActionListener(new ValidUserNameActionListener(user, name));
		
		this.name.addKeyListener(new ValidEnterKeyAdapter(this.validButton));
		
		this.add(name);
		this.add(validButton);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));		
	}
	
}
