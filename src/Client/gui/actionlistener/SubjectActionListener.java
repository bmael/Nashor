/**
 * 
 */
package gui.actionlistener;

import gui.SelfInternalFrame;
import gui.SubjectDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;

import remote.IMessageDisplayer;
import remote.IServerSubject;
import displayer.MessageDisplayer;

/**
 * This class provides action to do when a subject button is clicked by a client.
 * @author bmael
 *
 */
public class SubjectActionListener implements ActionListener {

	private IServerSubject subject;
	private IMessageDisplayer displayer;
	
	private boolean firstClick;
	private JComponent dialogPanel;

	
	/**
	 * Construct an instance of an action listener for a JToggleButton which represents a subject.
	 * @param subject associated to the button.
	 * @param dialogPanel the panel where discussion will be placed on the GUI.
	 */
	public SubjectActionListener(IServerSubject subject, JComponent dialogPanel){
		this.subject = subject;
		
		try{

			this.displayer = new MessageDisplayer();
		}catch(RemoteException e){
			System.err.println("Unable to create a new Message Displayer...");
		}
		
		this.firstClick = true;
		this.dialogPanel = dialogPanel;
	}
	
	/**
	 * When the client clicks on a subject button, we add him to the discussion.
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//If it the first click on the subject, the client joins the discussion
		if(this.firstClick){
			SubjectDialog dialog = new SubjectDialog(this.subject);
			try {
				JInternalFrame frameDialog = SelfInternalFrame.createLayer(this.subject.getTitle(),
																			dialog, 
																			dialog.getMenuBar());
				dialogPanel.add(frameDialog, JLayeredPane.DRAG_LAYER);
				((JLayeredPane) dialogPanel).moveToFront(frameDialog);
				dialogPanel.revalidate();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			((MessageDisplayer) this.displayer).setPrintZone(dialog.getDialog());
			
			try {
				this.subject.join(displayer);
				System.out.println("A displayer joined subject: " + subject.getTitle());
				this.subject.broadcast("New User joins the chat...");
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}else{ //else : the client have already click, he leaves the discussion
			
		}
		
		// button selection state according to the firstClick attribute
		((JToggleButton)actionEvent.getSource()).setSelected(this.firstClick);
		
		this.firstClick = !this.firstClick;
	}

}
