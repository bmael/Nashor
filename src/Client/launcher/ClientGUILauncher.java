package launcher;

import gui.SubjectMenu;

import javax.swing.JFrame;

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
		
		JFrame mainWindow = ClientGUILauncher.initMainWindow();
		
		SubjectMenu subjectMenu = new SubjectMenu();
		subjectMenu.addSubject("Sport");
		
		mainWindow.add(subjectMenu);
		
		mainWindow.setVisible(true);
	}

}
