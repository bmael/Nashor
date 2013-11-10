/**
 * 
 */
package client.subjectsmanager.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import common.remote.IClient;

/**
 * @author bmael
 *
 */
public class DeleteSubjectActionListener implements ActionListener {

	private IClient user;
	JTable table;
	
	public DeleteSubjectActionListener(IClient user, JTable table){
		this.user = user;
		this.table = table;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
			
		for(int index = this.table.getSelectedRowCount() - 1; index >= 0; index--){
			int row = this.table.getSelectedRows()[index];
			
			String valueAt = (String) model.getValueAt(row, 0);
			System.out.println("Trying to remove row: " + row);
			
			try {
				this.user.getServer().removeSubject(valueAt);
			} catch (RemoteException e1) {
				e1.printStackTrace();
				System.err.println("Unable to remove a subject: " + valueAt);
			}
			
			model.removeRow(row);
		}			
	}

}
