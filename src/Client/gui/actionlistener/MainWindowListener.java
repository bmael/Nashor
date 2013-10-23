/**
 * 
 */
package gui.actionlistener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import remote.IClient;

/**
 * @author bmael
 *
 */
public class MainWindowListener implements WindowListener {

	private IClient user;
	
	public MainWindowListener(IClient user){
		this.user = user;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		System.out.println("Exited...");
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("Exiting....");
		try {
			this.user.exit();
		} catch (RemoteException e) {
			System.err.println("Unable to disconnect the client...");
			e.printStackTrace();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
