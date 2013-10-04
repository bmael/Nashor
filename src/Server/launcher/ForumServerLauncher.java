/**
 * 
 */
package launcher;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import serverimplementation.ForumServer;

/**
 * This class provide the static void main method to launch the server.
 * @author bmael
 *
 */
public class ForumServerLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			try {
				System.out.println("Trying to registry port 1099");
				LocateRegistry.createRegistry(1099);
			} catch (Exception e) {
				System.out.println("-- Port already in use....");
			}
//			
//			if (reg == null) {
//				try {
//					System.out.println("Trying to get registry in use...");
//					reg = java.rmi.registry.LocateRegistry.getRegistry();	
//				} catch (Exception e) {
//						System.out.println("-- Can't get the registry in use");
//						System.exit(0);
//				}
//			}
//			
//			try {
			ForumServer nashor = new ForumServer();
			
			String url = "//" + InetAddress.getLocalHost().getHostAddress() + "/NashorServer";
			System.out.println("Storing the NashorServer with url: " + url);
			Naming.rebind(url, nashor);
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
			
//			System.out.println("Instanciation of a new Forum Server...");
//			
//			System.out.println("Trying to registry port 1099...");
//			LocateRegistry.createRegistry(1099);
//			
//			System.out.println("Trying to bind...");
//			Naming.bind("//127.0.0.1:1099/NashorServer", nashor);
		}catch(Exception e){
			System.err.println("Unable to launch server...");
			e.printStackTrace();
		}
		
		System.out.println("Server is online");
		
	}

}
