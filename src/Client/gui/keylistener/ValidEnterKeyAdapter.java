/**
 * 
 */
package gui.keylistener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * @author bmael
 *
 */
public class ValidEnterKeyAdapter extends KeyAdapter {

	private JButton buttonToclick;
	
	public ValidEnterKeyAdapter(JButton buttonToclick){
		this.buttonToclick = buttonToclick;
	}
	
	public void keyTyped(KeyEvent e){
		if(e.getKeyChar() == KeyEvent.VK_ENTER){
			buttonToclick.doClick();
		}
		
	}
}
