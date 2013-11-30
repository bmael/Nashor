/**
 * 
 */
package server.launcher;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import server.implementation.ForumServer;
import server.implementation.ServerSubject;
import common.remote.IServerSubject;

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
			System.out.println(" ***** Initializing communication port *****");
			try {
				System.out.println(" ** Trying to registry port 1099");
				LocateRegistry.createRegistry(1099);
			} catch (Exception e) {
				System.out.println(" **** Port already in use....");
			}

			System.out.println(" ***** Initializing default subjects *****");
			String sportsAddress = initDefaultSubject("Sports");	
			String gamesAddress = initDefaultSubject("Games");
			String catsAddress = initDefaultSubject("Cats");
			String moviesAddress = initDefaultSubject("Movies");

			System.out.println(" ****** Initializing the server ***** ");
			
			ForumServer nashor = new ForumServer();
			
			String url = "//" + InetAddress.getLocalHost().getHostAddress() + "/NashorServer";
			System.out.println(" ** Storing the NashorServer with url: " + url);
			Naming.rebind(url, nashor);
			System.out.println(" ** NashorServer is online");
			
			System.out.println(" ** Retrieving default subjects");
			
			lookupDefaultSubject(sportsAddress, nashor);
			lookupDefaultSubject(gamesAddress, nashor);
			lookupDefaultSubject(catsAddress, nashor);
			lookupDefaultSubject(moviesAddress, nashor);
		}catch(Exception e){
			System.err.println("Unable to launch server...");
			e.printStackTrace();
			System.exit(0);
		}	
	}

	/**
	 * @param address
	 * @param server
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	private static void lookupDefaultSubject(String address, ForumServer server)
			throws NotBoundException, MalformedURLException, RemoteException {
		System.out.println(" **** Looking up for Sports subject at: " + address);
		IServerSubject sports = (IServerSubject) Naming.lookup(address);
		System.out.println(" **** Adding Sports subject to NashorServer");
		server.createSubject(sports);
	}

	/**
	 * @return
	 * @throws RemoteException
	 * @throws UnknownHostException
	 * @throws MalformedURLException
	 */
	private static String initDefaultSubject(String subjectName) throws RemoteException,
			UnknownHostException, MalformedURLException {
		ServerSubject subject = new ServerSubject(subjectName, null);
		String address = "//" + InetAddress.getLocalHost().getHostAddress() + "/" + subjectName;
		System.out.println(" ** Storing the " + subjectName + " server with url: " + address);
		Naming.rebind(address, subject);
		System.out.println(" ** " + subjectName + " subject is online");
		return address;
	}

}
