/**
 * 
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * @author bmael
 *
 */
public class SelfInternalFrame extends JInternalFrame {

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = 7872949621070817203L;

	public SelfInternalFrame(String s, JPanel p, JMenuBar menuBar) {
		if (menuBar != null)
			setJMenuBar(menuBar);
		getContentPane().add(p, BorderLayout.CENTER);
		//setMinimumSize(new Dimension(200,200));
		setBounds(50, 50, 200, 200);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle(s);
		setVisible(true);
	}
	
	public static JInternalFrame createLayer(String s, JPanel p, JMenuBar menubar) {
		return new SelfInternalFrame(s, p, menubar);
	}
	
}
