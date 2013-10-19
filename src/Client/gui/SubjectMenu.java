package gui;

import general.Client;
import gui.actionlistener.SubjectActionListener;

import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import remote.IServerSubject;

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
	private Client user;
	
	/**
	 * Construct a new instance of a SubjectMenu
	 */
	public SubjectMenu(Client user){
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
}
	