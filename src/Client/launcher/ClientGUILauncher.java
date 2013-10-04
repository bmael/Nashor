package launcher;

import java.net.InetAddress;
import java.rmi.Naming;

import javax.swing.JFrame;

import remote.IForumServer;

/**
 * This class is the launcher for Nashor client
 * @author bmael
 *
 */
public class ClientGUILauncher {

	/**
	 * Initialize the MainWindow of the client application.
	 * @return JFrame the mainWindow 
	 */
	@SuppressWarnings("unused")
	private static JFrame initMainWindow(){
		JFrame mainWindow = new JFrame("Nashor Chat");
		
		mainWindow.setSize(400, 400);
		
		return mainWindow;
	}
	
	/**
	 * Launch the client application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		//server lookup
		try{
			String url = "//" + InetAddress.getLocalHost().getHostAddress() + "/NashorServer";
			System.out.println("Looking up for Nashor Server at: " + url);
			IForumServer server = (IForumServer) Naming.lookup(url);
			
			System.out.println("Server should say hello...");
			server.sayHello();
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Unable to contact server...");
		}
		
		//initialization of mainwondow
//		JFrame mainWindow = ClientGUILauncher.initMainWindow();
//		
//		SubjectMenu subjectMenu = new SubjectMenu();
//		subjectMenu.addSubject("Sport");
//		
//		mainWindow.add(subjectMenu);
//		
//		mainWindow.setVisible(true);
		
	}

}
