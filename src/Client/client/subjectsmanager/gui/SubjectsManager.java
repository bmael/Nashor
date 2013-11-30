/**
 * 
 */
package client.subjectsmanager.gui;

import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import client.subjectsmanager.actionlistener.CreateNewSubjectActionListener;
import client.subjectsmanager.actionlistener.DeleteSubjectActionListener;
import client.subjectsmanager.table.SubjectsTable;
import common.remote.IClient;
import common.remote.IServerSubject;

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

	List<IServerSubject> subjects;
	DefaultTableModel model;
	private Object[] ColumnNames = {"Subjects"};
	Object[][] datas;

	private JScrollPane scroller;
	private JTable subjectsTable;

	private JPanel bottomPanel;
	private JButton addButton;
	private JButton deleteButton;

	public SubjectsManager(IClient user){
		this.user = user;

		this.setTitle("Subjects Manager");
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.setSize(250, 400);
		this.setResizable(false);

		this.createSubjectsTableComponent();

		this.bottomPanel = new JPanel();

		this.addButton = new JButton("Create");
		this.addButton.setToolTipText("Create a new subject available for all users on this server.");
		this.addButton.addActionListener(new CreateNewSubjectActionListener(this.user, this.subjectsTable));
		this.bottomPanel.add(this.addButton);

		this.deleteButton = new JButton("Delete");
		this.deleteButton.setToolTipText("Delete selected subjects.");
		this.deleteButton.addActionListener(new DeleteSubjectActionListener(this.user, this.subjectsTable));
		this.bottomPanel.add(this.deleteButton);

		this.add(this.bottomPanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	/**
	 * Create the Subjects Table component according to the user's subjects list and add it to this subjects manager panel
	 */
	private void createSubjectsTableComponent() {
		this.parseSubjectsData();

		model = new DefaultTableModel(datas, ColumnNames);

		subjectsTable = new SubjectsTable(this.user, model);
		subjectsTable.setPreferredScrollableViewportSize(subjectsTable.getPreferredSize());

		if(subjects.isEmpty()){
			this.subjectsTable.setVisible(false);
		}

		this.scroller = new JScrollPane(subjectsTable);
		this.add(scroller, BorderLayout.CENTER);
	}

	/**
	 * Initialize the data for the subjects table.
	 * @throws RemoteException
	 */
	private void parseSubjectsData() {
		try {
			this.subjects = this.user.getServer().getSubjectsOfClient(this.user);
			System.out.println(this.subjects);
		} catch (RemoteException e) {
			e.printStackTrace();
			System.err.println("Unable to retrieve " + this.user + " subjects list from the server...");
		}

		datas = new Object[subjects.size()][2];

		for(int i = 0; i < subjects.size(); i++){
			try {
				datas[i][0] = subjects.get(i).getTitle();
				System.out.println(datas[i][0]+" | ");
			} catch (RemoteException e) {
				e.printStackTrace();
				System.err.println("Unable to have access to a IServerSubject from the list...");
			}
		}
	}
}
