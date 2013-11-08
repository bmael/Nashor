/**
 * 
 */
package tools.internalframe;

import javax.swing.JToggleButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * This class redefines behavior of InternalFrame.
 * @author bmael
 *
 */
public class SelfInternalFrameAdapter extends InternalFrameAdapter {
	
	private JToggleButton button;
	
	/**
	 * Construct a new instance of a self internalFrameAdapter.
	 * @param firstClick
	 */
	public SelfInternalFrameAdapter(JToggleButton button){
		super();
		this.button = button;
	}
	
	/**
	 * When the internal frame is closed, we simulate a click on the associated 
	 * button.
	 */
	public void internalFrameClosed(InternalFrameEvent e){
		this.button.doClick();
	}
}
