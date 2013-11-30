/**
 * 
 */
package client.subjectsearcher.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import client.mainwindow.gui.SubjectMenu;

/**
 * @author Maël
 *
 */
public class SearchActionListener implements ActionListener {

	private SubjectMenu subjects;
	private JTextField searchField;
	
	public SearchActionListener(SubjectMenu subjects, JTextField searchField) {
		this.subjects = subjects;
		this.searchField = searchField;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton subject = this.subjects.getSubjectsButton(this.searchField.getText());
		if(subject == null){
			JOptionPane.showMessageDialog(null, "Unable to find a subject with the given name...");
		}else{
			subject.doClick();
		}
		
		this.searchField.setText("");
	}

}
