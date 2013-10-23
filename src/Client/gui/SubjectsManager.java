/**
 * 
 */
package gui;

import gui.table.SubjectsTable;

import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import remote.IClient;
import remote.IServerSubject;

/**
 * @author bmael
 *
 */
public class SubjectsManager extends JDialog {

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -1870719967054726040L;
	private IClient user;
	
	private JScrollPane scroller;
	private JTable subjectsTable;
	
	public SubjectsManager(IClient user){
		this.user = user;
		
		this.setTitle("Subjects Manager");
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.setSize(200, 100);
		
		
		
		try {
			List<IServerSubject> subjects = this.user.getServer().getSubjectsOfClient(this.user);
			
			Object[] ColumnNames = {"Subjects"};
			Object[][] datas = new Object[subjects.size()][1];
			
			for(int i = 0; i < subjects.size(); i++){
				datas[i][0] = subjects.get(i).getTitle();
			}
			
			DefaultTableModel model = new DefaultTableModel(datas, ColumnNames);
			subjectsTable = new SubjectsTable(model);
			subjectsTable.setPreferredScrollableViewportSize(subjectsTable.getPreferredSize());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		this.scroller = new JScrollPane(subjectsTable);
		this.add(scroller, BorderLayout.CENTER);
		
		this.setVisible(true);
		System.err.println("YALLLLLLLLLLLLLLLLLLLAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!");
	}
}
