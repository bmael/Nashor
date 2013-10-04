/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author bmael
 *
 */
public class SubjectDialog extends JPanel{

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -3867762301783478911L;
	
	private JTextArea dialog;
	private JTextField input;
	private JButton send;
	
	/**
	 * Construct a new instance of a subject dialog.
	 */
	public SubjectDialog(){
		super();
		this.initializeSubjectDialog();
	}
	
	private void initializeSubjectDialog(){
		
		this.setLayout(new BorderLayout());
		
		dialog = new JTextArea();
		dialog.setEditable(false);
		
		input = new JTextField("", 10);
		send = new JButton("Send");
		
		this.add(dialog, BorderLayout.CENTER);
		
		JPanel sendBar = new JPanel();
		
		sendBar.add(input);
		sendBar.add(send);
		
		this.add(sendBar, BorderLayout.SOUTH);
	}
}
