/**
 * 
 */
package gui;

import gui.actionlistener.SendMessageActionListener;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import remote.IServerSubject;

/**
 * @author bmael
 *
 */
public class SubjectDialog extends JPanel{

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -3867762301783478911L;
	
	private IServerSubject subject;
	
	private SubjectDialogMenuBar menuBar;
	
	private JTextArea dialog;
	private JTextField input;
	private JButton send;
	
	/**
	 * Construct a new instance of a subject dialog.
	 */
	public SubjectDialog(IServerSubject subject){
		super();
		this.subject = subject;
		this.initializeSubjectDialog();
	}
	
	/**
	 * Initialize SubjectDialog GUI component.
	 */
	private void initializeSubjectDialog(){
		
		this.setLayout(new BorderLayout());
		
		menuBar = new SubjectDialogMenuBar();
		
		dialog = new JTextArea();
		dialog.setEditable(false);
		
		input = new JTextField("", 10);
		send = new JButton("Send");
		send.addActionListener(new SendMessageActionListener(this.subject, this.input));
		
		this.add(dialog, BorderLayout.CENTER);
		
		JPanel sendBar = new JPanel();
		
		sendBar.add(input);
		sendBar.add(send);
		
		this.add(sendBar, BorderLayout.SOUTH);
	}

	/**
	 * Get the subject dialog menu bar.
	 * @return the subject dialog menu bar of this subject dialog.
	 */
	public SubjectDialogMenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * Get the JTextArea where messages will be displayed.
	 * @return the JTextArea where messages will be displayed.
	 */
	public JTextArea getDialog() {
		return dialog;
	}
	
	
	
}
