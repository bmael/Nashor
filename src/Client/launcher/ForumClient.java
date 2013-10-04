/**
 * 
 */
package launcher;

import gui.SelfInternalFrame;
import gui.SubjectDialog;
import gui.SubjectMenu;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.InetAddress;
import java.rmi.Naming;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
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

	private JLayeredPane desktop;
	
	private SubjectMenu subjects;
	private SubjectDialog dialog;
	
	/**
	 * Initialization of the Forum Client Applet.
	 */
	public void init(){
		super.init();
		
		this.initializeUI();
		
		//server lookup
		try{
			String url = "//" + InetAddress.getLocalHost().getHostAddress() + "/NashorServer";
			System.out.println("Looking up for Nashor Server at: " + url);
			IForumServer server = (IForumServer) Naming.lookup(url);
			
			System.out.println("Server should say hello...");
			server.sayHello();
			
			//Add all available subjects on the server to the client Subject Menu.
			List<IServerSubject> availaibleSubject = server.getAllSubject();
			for(IServerSubject subject : availaibleSubject){
				System.out.println("Adding subject: " + subject.getTitle());
				this.subjects.addSubject(subject.getTitle());
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
		this.setLayout(new BorderLayout());
		
		this.subjects = new SubjectMenu();
		this.add(subjects, BorderLayout.NORTH);
		
		desktop = new JLayeredPane();
		desktop.setOpaque(false);
		
		this.add(desktop);
		
		this.dialog = new SubjectDialog();
		JInternalFrame frameDialog = SelfInternalFrame.createLayer("new Conversation", this.dialog, null);
		
		desktop.add(frameDialog, JLayeredPane.DRAG_LAYER);
		desktop.moveToFront(frameDialog);
		desktop.revalidate();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}
