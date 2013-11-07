/**
 * 
 */
package client.mainwindow.gui;

import javax.swing.JButton;
import javax.swing.JToolBar;

import client.actionlistener.ShowSubjectsManagerActionListener;
import common.remote.IClient;

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

	private JButton subjectsListButton;
	
	private IClient user;
	
	public SubjectAdministratorToolBar(IClient user){
		this.user = user;
		
		this.setFloatable(true);
		this.setToolTipText("Action to administrate subjects.");
		
		this.subjectsListButton = new JButton("Subjects List");
		this.subjectsListButton.setToolTipText("Display the list of all subjects created by you.");
		this.subjectsListButton.addActionListener(new ShowSubjectsManagerActionListener(this.user));
		this.add(this.subjectsListButton);
	}
}
