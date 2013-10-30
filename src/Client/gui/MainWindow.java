/**
 * 
 */
package gui;

import gui.actionlistener.MainWindowListener;
import interfaces.IMainWindow;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import remote.IClient;
import remote.IServerSubject;

/**
 * This class provide the main frame for a client.
 * @author bmael
 *
 */
public class MainWindow extends JFrame implements IMainWindow{

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = 2740437090361841747L;

	private IClient user;
	
	private JPanel windowPanel;
	private SubjectAdministratorToolBar toolBar;
	private JLayeredPane desktop;
	
	private JPanel rightMenuPanel;
	private UserPanel userPanel;
	private JScrollPane subjectsScroller;
	private SubjectMenu subjects;
	private JLabel connectedUsers;
	
	/**
	 * Construct a new mainWindow for the client given in parameter.
	 * Precondition : user has to be initialize.
	 * @param user
	 */
	public MainWindow(IClient user){
		this.user = user;
		
		this.setTitle("Nashor's Chat");
		this.setLayout(new BorderLayout());
		this.setSize(800, 400);
		this.setVisible(true);
		this.addWindowListener(new MainWindowListener(this.user));
		
		/*
		 * Setting the system look & feel(look like any other application of your current OS).
		 */
		// Set cross-platform Java L&F (also called "Metal")
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		// The panel of the main window
		try {
			this.windowPanel = new BackgroundPanel(
					ImageIO.read(new File(
							System.getProperty("user.dir")
									+ "\\Resources\\background.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Searching jpg at: " + System.getProperty("user.dir")
									+ "\\Resources\\background.jpg");
			this.windowPanel = new JPanel();
		}
		
		this.windowPanel.setLayout(new BorderLayout());

		this.toolBar = new SubjectAdministratorToolBar(this.user);
		this.windowPanel.add(this.toolBar, BorderLayout.NORTH);
		
		this.desktop = new JLayeredPane();
		this.desktop.setOpaque(false);
		
		this.windowPanel.add(this.desktop);
		
		this.userPanel = new UserPanel(this.user);
		this.userPanel.setSize(150, 50);
		
		this.rightMenuPanel = new JPanel();
		this.rightMenuPanel.setLayout(new BoxLayout(this.rightMenuPanel, BoxLayout.Y_AXIS));
		
		this.rightMenuPanel.add(userPanel);
		
		this.subjects = new SubjectMenu(user);
		this.subjectsScroller = new JScrollPane(this.subjects);
		
		this.rightMenuPanel.add(this.subjectsScroller);
		
		this.connectedUsers = new JLabel("Connected users: ");
		this.rightMenuPanel.add(this.connectedUsers);
		
		this.add(windowPanel, BorderLayout.CENTER);
		this.add(rightMenuPanel, BorderLayout.EAST);
		
		this.refresh();		
	}

	@Override
	public void addSubject(IServerSubject subject) {
		this.subjects.addSubject(subject, this.desktop);
	}

	@Override
	public void addSubjects(List<IServerSubject> subjects) {
		for(IServerSubject subject : subjects){
			this.addSubject(subject);
		}		
	}

	@Override
	public void refresh() {
		this.revalidate();
	}

	@Override
	public void updateConnectedUsersNumber(int newValue) {
		this.connectedUsers.setText("Connected Users: " + newValue);
	}

	@Override
	public void removeSubjects(List<IServerSubject> subjects) {
		for(IServerSubject subject : subjects){
			try {
				this.subjects.removeSubject(subject.getTitle());
			} catch (RemoteException e) {
				System.err.println("Unable to remove subject on the GUI...");
				e.printStackTrace();
			}
		}
	}
	
}
