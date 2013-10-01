package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class provide a GUI representation for all subjects available for clients
 * @author bmael
 *
 */
public class SubjectMenu extends JPanel{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -1872063443614599300L;

	/**
	 * Construct a new instance of a SubjectMenu
	 */
	public SubjectMenu(){
		super();
	}
	
	/**
	 * Add a new subject to the current subject menu.
	 * @param subjectName
	 */
	public void addSubject(String subjectName){
		JButton newButton = new JButton(subjectName);
		
		this.add(newButton);
	}
}
	