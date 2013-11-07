/**
 * 
 */
package client.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.subjectsmanager.gui.SubjectsManager;
import common.remote.IClient;

/**
 * @author bmael
 *
 */
public class ShowSubjectsManagerActionListener implements ActionListener {

	private IClient user;
	
	public ShowSubjectsManagerActionListener(IClient user){
		this.user = user;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new SubjectsManager(this.user);
	}

}
