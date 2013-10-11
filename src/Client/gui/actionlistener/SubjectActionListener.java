/**
 * 
 */
package gui.actionlistener;

import gui.SelfInternalFrame;
import gui.SelfInternalFrameAdapter;
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
	
	private JComponent dialogPanel;
	private JInternalFrame frame;

	
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
		
		this.dialogPanel = dialogPanel;
	}
	
	/**
	 * When the client clicks on a subject button, we add him to the discussion.
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//If it the first click on the subject, the client joins the discussion
		if(((JToggleButton)actionEvent.getSource()).isSelected()){
			SubjectDialog dialog = new SubjectDialog(this.subject);
			try {
				frame = SelfInternalFrame.createLayer(this.subject.getTitle(),
																			dialog, 
																			dialog.getMenuBar());
				frame.addInternalFrameListener(new SelfInternalFrameAdapter((JToggleButton)actionEvent.getSource()));
				
				dialogPanel.add(frame, JLayeredPane.DRAG_LAYER);
				((JLayeredPane) dialogPanel).moveToFront(frame);
				dialogPanel.revalidate();
				
				((JToggleButton)actionEvent.getSource()).setSelected(true);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			((MessageDisplayer) this.displayer).setPrintZone(dialog.getDialog());
			
			try {
				this.subject.join(displayer);
				System.out.println("A displayer joined subject: " + subject.getTitle());
				this.subject.broadcast("New User joins the discussion...");
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}else{ //else : the client have already click, he leaves the discussion
			try {
				System.out.println("A displayer leave subject: " + subject.getTitle());
				this.subject.leave(displayer);
				dialogPanel.remove(frame);
				dialogPanel.revalidate();
				dialogPanel.repaint();
			} catch (RemoteException e) {
				e.printStackTrace();
				System.err.println("Unable to leave discussion...");
			}
		}		
	}

}
