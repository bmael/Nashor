/**
 * 
 */
package gui;

import general.Client;
import gui.actionlistener.SendMessageActionListener;
import gui.keylistener.ValidEnterKeyAdapter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

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
	
	private JScrollPane scroll;
	private JEditorPane dialog;
	private JTextField input;
	private JButton send;
	
	private Client user;
	
	/**
	 * Construct a new instance of a subject dialog.
	 */
	public SubjectDialog(IServerSubject subject, Client user){
		super();
		this.subject = subject;
		this.user = user;
		this.initializeSubjectDialog();
	}
	
	/**
	 * Initialize SubjectDialog GUI component.
	 */
	private void initializeSubjectDialog(){
		
		this.setLayout(new BorderLayout());
		
		menuBar = new SubjectDialogMenuBar();
		
		dialog = new JEditorPane();
		dialog.setEditable(false);
		
		DefaultCaret caret = (DefaultCaret)dialog.getCaret();  
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 
		
		this.scroll = new JScrollPane ( dialog );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
		
		input = new JTextField("", 10);
		
		send = new JButton("Send");
		send.addActionListener(new SendMessageActionListener(this.subject, this.input, this.user));
		
		input.addKeyListener(new ValidEnterKeyAdapter(this.send));
		
		this.add(scroll, BorderLayout.CENTER);
		
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
	 * @return the JEditorPane where messages will be displayed.
	 */
	public JEditorPane getDialog() {
		return dialog;
	}
	
	
	
}
