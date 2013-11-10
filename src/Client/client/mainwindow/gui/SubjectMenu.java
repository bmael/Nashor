package client.mainwindow.gui;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import client.actionlistener.SubjectActionListener;
import common.remote.IClient;
import common.remote.IServerSubject;

/**
 * This class provide a GUI representation for all subjects available for clients
 * @author bmael
 *
 */
public class SubjectMenu extends JPanel{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -1872063443614599300L;
	private IClient user;
	
	/**
	 * Construct a new instance of a SubjectMenu
	 */
	public SubjectMenu(IClient user){
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.user = user;
	}
	
	/**
	 * Add a new subject to the current subject menu.
	 * @param subjectName
	 */
	public void addSubject(IServerSubject subject, JComponent dialogPanel){
		JToggleButton newButton = new JToggleButton();
		try {
			newButton.setText(subject.getTitle());
			newButton.addActionListener(new SubjectActionListener(subject, dialogPanel, this.user));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		this.add(newButton);
	}
	
	public void removeSubject(String title){
		for(Component component : this.getComponents()){
			JToggleButton button = (JToggleButton) component;
			if(button.getText().equals(title)){
				if(button.isSelected()){ // If the user is in the discussion we force him to quit it
					button.doClick();
				}
				this.remove(button);
			}
		}
	}
	
	/**
	 * Return the subject button component associated to the title given in parameter.
	 * @param title the title of the button to return.
	 * @return a button component or null if no button could be return.
	 */
	public JToggleButton getSubjectsButton(String title){
		for(Component component : this.getComponents()){
			JToggleButton button = (JToggleButton) component;
			if(button.getText().equalsIgnoreCase(title)){
				return button;
			}
		}
		
		return null;
	}
	
	public ArrayList<String> getAllSubject(){
		ArrayList<String> res = new ArrayList<>();
		for(Component component : this.getComponents()){
			JToggleButton button = (JToggleButton) component;
			res.add(button.getText());
		}
		return res;
	}
}
	