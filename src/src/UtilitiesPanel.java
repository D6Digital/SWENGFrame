package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * 
 * @author Sam Pick
 *
 */
public class UtilitiesPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean diceRollerExists;
	public boolean calculatorExists;
	
	
	/**
	 * Create a simple panel and add the JButtons for the utilities
	 */
	public UtilitiesPanel() {
		super();
		
		// When the panel is first created the utilities will not exist but when they are
		// instantiated, these fields should be updated so we don't get multiple utilities
		diceRollerExists = false;
		calculatorExists = false;
		
		// TODO add the necessary JButtons
	}
	
	
	/**
	 * When a utility button is released instantiate the utility
	 * if it doesn't already exist
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO manage button presses
		
	}


	public void setVisibility(boolean visible){
			this.setVisible(visible);
		}

	
	
}
