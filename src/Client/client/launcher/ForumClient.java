/**
 * 
 */
package client.launcher;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import client.implementation.Client;
import common.remote.IForumServer;

/**
 * This class contains the client launcher for "Nashor Chat".
 * @author bmael
 *
 */
public class ForumClient {

	private static Client user;
	private static String serverAddress;
	
	/**
	 * Launcher of the Forum Client.
	 */
	public static void main(String args[]){
		
		try {				
			try {
				serverAddress = JOptionPane.showInputDialog("Server address:", "//" + InetAddress.getLocalHost().getHostAddress() + 
						"/NashorServer");
			} catch (UnknownHostException e1) {
				System.err.println("Unable to find host...");
				e1.printStackTrace();
			}
							
			//server lookup
			try{				
				System.out.println("Looking up for Nashor Server at: " + serverAddress);
				IForumServer server = (IForumServer) Naming.lookup(serverAddress);
				
				System.out.println("Server should say hello...");
				server.sayHello();
				
				// Launch the client with the initialized server.
				user = new Client(server);
				try{
					server.join(user);	
				}catch(RemoteException e){
					e.printStackTrace();
					System.err.println("Unable to join the server...");
				}
					
				//Retrieve the list of available subjects on the server.
				try{
					user.getAvailableSubjects();
				}catch(RemoteException e){
					e.printStackTrace();System.err.println("Unable to retrieve the subjects list");
				}
				
			} catch (RemoteException e) {
				e.printStackTrace();
				System.err.println("Unable to contact server...");
				System.exit(0);
			}
		} catch(Exception e2){
			e2.printStackTrace();
			System.err.println("Unable to create the client...");
			System.exit(0);
		}
	}
}
