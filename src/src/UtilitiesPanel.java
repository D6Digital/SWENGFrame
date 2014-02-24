package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Sam Pick
 * @author Andrew Walter
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
		//Calls JPanel to create the UtilitiesPanel object.
		//Sets the layout to BoxLayout
		super();
		UtilitiesPanel.setLayout(new BoxLayout(UtilitiesPanel, BoxLayout.Y_AXIS));
		
		// When the panel is first created the utilities will not exist but when they are
		// instantiated, these fields should be updated so we don't get multiple utilities
		diceRollerExists = false;
		calculatorExists = false;
		
		//Adds JButtons for diceRoller and calculator
		JButton diceButton = new JButton("Launch Dice Roller");
		diceButton.setVerticalTextPosition(AbstractButton.CENTER);
		diceButton.setHorizontalTextPosition(AbstractButton.CENTER);
		diceButton.setActionCommand("diceRollerLaunch");
		diceButton.addActionListener(this);
		diceButton.setToolTipText("Opens the dice roller application");
		UtilitiesPanel.add(contrastButton);
		
		JButton calculatorButton = new JButton("Launch Modifier Calculator");
		calculatorButton.setVerticalTextPosition(AbstractButton.CENTER);
		calculatorButton.setHorizontalTextPosition(AbstractButton.CENTER);
		calculatorButton.setActionCommand("calculatorLaunch");
		calculatorButton.addActionListener(this);
		calculatorButton.setToolTipText("Opens the modifier calculator application");
		UtilitiesPanel.add(calculatorButton);
	}
	
	
	/**
	 * When a utility button is released instantiate the utility
	 * if it doesn't already exist
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO manage button presses
		
	}


	//public void setVisibility(boolean visible){
	//		this.setVisible(visible);
	//	}

	
	
}
