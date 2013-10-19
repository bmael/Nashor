/**
 * 
 */
package launcher;

import general.Client;
import gui.BackgroundPanel;
import gui.SubjectMenu;
import gui.UserPanel;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import remote.IForumServer;
import remote.IServerSubject;

/**
 * @author bmael
 *
 */
public class ForumClient extends Applet {

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -2175181013298400908L;

	/* GUI part */
	private static JFrame mainWindow;
	private static JPanel windowPanel;
	private static JLayeredPane desktop;
	private static JPanel rightMenuPanel;
	private static UserPanel userPanel;
	private static SubjectMenu subjects;
	private static JLabel connectedUsers;

	
	/* Client part */
	private static Client user;
	private static String serverAddress;
	
	private static void initMainWindow(){
		mainWindow = new JFrame("Nashor Chat");
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setSize(800, 400);
		mainWindow.setVisible(true);
		
		/*
		 * Setting the system look & feel(The applet will look like any other application of your current OS).
		 * DON'T KILL ME
		 */
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}

		// The panel of the mainwindow
		try {
			windowPanel = new BackgroundPanel(
					ImageIO.read(new File(
							System.getProperty("user.dir")
									+ "\\Resources\\background.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Searching jpg at: " + System.getProperty("user.dir")
									+ "\\Resources\\background.jpg");
			windowPanel = new JPanel();
		}
		
		windowPanel.setLayout(new BorderLayout());

		desktop = new JLayeredPane();
		desktop.setOpaque(false);
		
		windowPanel.add(desktop);
		
		userPanel = new UserPanel(user);
		userPanel.setSize(150, 50);
		
		rightMenuPanel = new JPanel();
		rightMenuPanel.setLayout(new BoxLayout(rightMenuPanel, BoxLayout.Y_AXIS));
		
		rightMenuPanel.add(userPanel);
		
		subjects = new SubjectMenu(user);
		rightMenuPanel.add(subjects);
		
		connectedUsers = new JLabel();
		rightMenuPanel.add(connectedUsers);
		
		mainWindow.add(windowPanel, BorderLayout.CENTER);
		mainWindow.add(rightMenuPanel, BorderLayout.EAST);
		
		mainWindow.revalidate();
	}
	
	/**
	 * Initialization of the Forum Client.
	 */
	public static void main(String args[]){
		
		try {
			user = new Client();
			
			try {
				serverAddress = JOptionPane.showInputDialog(mainWindow, "Server address:", "//" + InetAddress.getLocalHost().getHostAddress() + 
						"/NashorServer");
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			
			// Initialize the client main window.
			initMainWindow();
			
			//server lookup
			try{
				String url = "//" + InetAddress.getLocalHost().getHostAddress() + 
								"/NashorServer";
				
				System.out.println("Looking up for Nashor Server at: " + url);
				IForumServer server = (IForumServer) Naming.lookup(serverAddress);
				
				
				System.out.println("Server should say hello...");
				server.sayHello();
				
				server.join(user);
				
				//Add all available subjects on the server to the client Subject Menu.
				try{
					List<IServerSubject> availaibleSubject = server.getAllSubject();
					for(IServerSubject subject : availaibleSubject){
						System.out.println("Adding subject: " + subject.getTitle());
						subjects.addSubject(subject, desktop);
					}
				}catch(Exception e){
					e.printStackTrace();
					System.err.println("Unable to retrieve subject list from server...");
				}
				
				connectedUsers.setText("Connected users: " + user.getConnectedUsersNumber());
				mainWindow.revalidate();
				
			
			} catch (RemoteException e) {
				e.printStackTrace();
				System.err.println("Unable to contact server...");
				System.exit(0);
				
			}
		}
		catch(Exception e2){
			e2.printStackTrace();
			System.err.println("Unable to create the client...");
			System.exit(0);
		}
	}
}
