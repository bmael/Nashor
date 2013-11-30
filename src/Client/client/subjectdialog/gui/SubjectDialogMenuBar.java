/**
 * 
 */
package client.subjectdialog.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class provides GUI representation for SubjectDialog option.
 * @author bmael
 *
 */
public class SubjectDialogMenuBar extends JMenuBar {
	
	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -382903318840261888L;

	/**
	 * Construct a new instance of a SubjectDialogMenuBar.
	 */
	public SubjectDialogMenuBar(){
		super();
		
		JMenu optionMenu = new JMenu("Options");
		
		JMenuItem printItem = new JMenuItem("Print...");
		JMenuItem leaveItem = new JMenuItem("Leave");
		
		optionMenu.add(printItem);
		optionMenu.addSeparator();
		optionMenu.add(leaveItem);
		
		this.add(optionMenu);
	}
}
