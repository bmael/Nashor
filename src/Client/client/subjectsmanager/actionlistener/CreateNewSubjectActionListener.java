/**
 * 
 */
package client.subjectsmanager.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import common.remote.IClient;

/**
 * @author bmael
 *
 */
public class CreateNewSubjectActionListener implements ActionListener {

	
	private IClient user;
	JTable subjectsTable;
	
	public CreateNewSubjectActionListener(IClient user, JTable subjectsTable){
		this.user = user;
		this.subjectsTable = subjectsTable;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String title = JOptionPane.showInputDialog("What is the title of your subject?", null);
		
		if(title!= null && title!= "" && title.length() > 0){
			try {
				String address = null;
				try {
					address = "//" + InetAddress.getLocalHost().getHostAddress() + "/" + title;
					this.user.getServer().createSubject(address, title, this.user);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				
				DefaultTableModel model = (DefaultTableModel) this.subjectsTable.getModel();
				Object[] newValue = new Object[1];
				newValue[0] = title;
				model.addRow(newValue);
				
				if(model.getRowCount() > 0){
					subjectsTable.setVisible(true);
				}
				
			} catch (RemoteException e) {
				System.err.println("Unable to create the new subject");
				e.printStackTrace();
			}
		}
	}

}
