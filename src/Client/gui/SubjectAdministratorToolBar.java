/**
 * 
 */
package gui;

import gui.actionlistener.CreateNewSubjectActionListener;
import gui.actionlistener.ShowSubjectsManagerActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import remote.IClient;

/**
 * This class provides an implementation of available actions for a subject administrator.
 * @author bmael
 *
 */
public class SubjectAdministratorToolBar extends JToolBar {

		
	
	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -5537662131655098163L;

	private JButton addSubjectButton;
	private JButton removeSubjectButton;
	private JButton subjectsListButton;
	
	private IClient user;
	
	public SubjectAdministratorToolBar(IClient user){
		this.user = user;
		
		this.setFloatable(true);
		this.setToolTipText("Action to administrate subjects.");
		
		this.addSubjectButton = new JButton("Create");
		this.addSubjectButton.setToolTipText("Create a new subject available for all users on this server.");
		this.addSubjectButton.addActionListener(new CreateNewSubjectActionListener(this.user));
		this.add(this.addSubjectButton);
		
		this.removeSubjectButton = new JButton("Delete");
		this.removeSubjectButton.setToolTipText("Delete a subject created by you.");
		this.add(this.removeSubjectButton);
		
		this.subjectsListButton = new JButton("Subjects List");
		this.subjectsListButton.setToolTipText("Display the list of all subjects created by you.");
		this.subjectsListButton.addActionListener(new ShowSubjectsManagerActionListener(this.user));
		this.add(this.subjectsListButton);
	}
}
