/**
 * 
 */
package gui.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import remote.IClient;

/**
 * @author bmael
 *
 */
public class CreateNewSubjectActionListener implements ActionListener {

	
	private IClient user;
	
	public CreateNewSubjectActionListener(IClient user){
		this.user = user;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String title = JOptionPane.showInputDialog("What is the title of your subject?", null);
		
		if(title!= "" && title.length() > 0){
			try {
				this.user.getServer().createSubject(title, this.user);
			} catch (RemoteException e) {
				System.err.println("Unable to create the new subject");
				e.printStackTrace();
			}
		}
	}

}
