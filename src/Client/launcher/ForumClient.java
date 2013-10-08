/**
 * 
 */
package launcher;

import gui.BackgroundPanel;
import gui.SubjectMenu;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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

	private JPanel windowPanel;
	
	private JLayeredPane desktop;
	
	private SubjectMenu subjects;
	
	/**
	 * Initialization of the Forum Client Applet.
	 */
	public void init(){
		super.init();
		
		this.initializeUI();
		
		//server lookup
		try{
			String url = "//" + InetAddress.getLocalHost().getHostAddress() + 
							"/NashorServer";
			
			System.out.println("Looking up for Nashor Server at: " + url);
			IForumServer server = (IForumServer) Naming.lookup(url);
			
			System.out.println("Server should say hello...");
			server.sayHello();
			
			//Add all available subjects on the server to the client Subject Menu.
			try{
				List<IServerSubject> availaibleSubject = server.getAllSubject();
				for(IServerSubject subject : availaibleSubject){
					System.out.println("Adding subject: " + subject.getTitle());
					this.subjects.addSubject(subject, desktop);
				}
			}catch(Exception e){
				e.printStackTrace();
				System.err.println("Unable to retrieve subject list from server...");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Unable to contact server...");
			System.exit(0);
		}
	}
	
	/**
	 * Initialize GUI component.
	 */
	private void initializeUI(){
		System.out.println(System.getProperty("user.dir")
				+ "\\Resources\\background.jpg");
		// The panel of the mainwindow
		try {
			windowPanel = new BackgroundPanel(
					ImageIO.read(new File(
							System.getProperty("user.dir")
									+ "\\Resources\\background.jpg")));

		} catch (IOException e) {
			e.printStackTrace();
			windowPanel = new JPanel();
		}
		
		windowPanel.setLayout(new BorderLayout());
		
		this.subjects = new SubjectMenu();
		windowPanel.add(subjects, BorderLayout.NORTH);
		
		desktop = new JLayeredPane();
		desktop.setOpaque(false);
		
		windowPanel.add(desktop);
		
		this.add(windowPanel);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}
