package textModule;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * 
 * @author samPick
 *
 */
public class Scribe extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Scribe() {
		
		setLayout(new BorderLayout());
		
		// Create the JTextPane
		JTextPane textPane = createTextPane();
		
		add(textPane);
		
	}
	
	/**
	 * 
	 * @return The text pane
	 */
	private JTextPane createTextPane() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
