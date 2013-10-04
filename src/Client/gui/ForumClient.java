/**
 * 
 */
package gui;

import java.applet.Applet;
import java.awt.Graphics;

/**
 * @author bmael
 *
 */
public class ForumClient extends Applet {

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -2175181013298400908L;

	private SubjectMenu subjects;
	
	/**
	 * Initialization of the Forum Client Applet.
	 */
	public void init(){
		super.init();
		
		this.subjects = new SubjectMenu();
		
		//Try to do it clearly...
		this.subjects.addSubject("Sport");
		
		this.add(this.subjects);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}
