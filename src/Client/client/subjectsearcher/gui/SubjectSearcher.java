/**
 * 
 */
package client.subjectsearcher.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;

import tools.keyadapter.ValidEnterKeyAdapter;
import client.mainwindow.gui.SubjectMenu;
import client.subjectsearcher.actionlistener.SearchActionListener;

/**
 * @author Maël
 *
 */
public class SubjectSearcher extends JPanel {

	/**
	 * The generated serial version UID
	 */
	private static final long serialVersionUID = -4048662118300490897L;
	
	private JTextField searchField;
	private JButton validation;
	
	private SubjectMenu subjects;
	
	public SubjectSearcher(SubjectMenu subjects){
		this.subjects = subjects;
		
		this.searchField = new JTextField(10);
		PromptSupport.setPrompt("Search a subject", this.searchField);
		this.add(this.searchField);
		
		this.validation = new JButton("Search");
		this.validation.addActionListener(new SearchActionListener(this.subjects, this.searchField));
		this.add(this.validation);
		
		this.searchField.addKeyListener(new ValidEnterKeyAdapter(this.validation));
	}
}
