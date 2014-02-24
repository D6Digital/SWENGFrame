package src;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
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
	JPanel UtilitiesPanel;
	
	
	/**
	 * Create a simple panel and add the JButtons for the utilities
	 */
	public UtilitiesPanel() {
		//Calls JPanel to create the UtilitiesPanel object.
		//Sets the layout to BoxLayout
		super();
		setLayout(null);
		
		// When the panel is first created the utilities will not exist but when they are
		// instantiated, these fields should be updated so we don't get multiple utilities
		diceRollerExists = false;
		calculatorExists = false;
		
		//Adds JButtons for diceRoller and calculator
		JButton diceButton = new JButton("Launch Dice Roller");
		diceButton.setVerticalTextPosition(AbstractButton.CENTER);
		diceButton.setHorizontalTextPosition(AbstractButton.CENTER);
		diceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		diceButton.setBounds(100, 75, 150, 50);
		diceButton.setActionCommand("diceRollerLaunch");
		diceButton.addActionListener(this);
		diceButton.setToolTipText("Opens the dice roller application");
		add(diceButton);
		
		JButton calculatorButton = new JButton("Launch Modifier Calculator");
		calculatorButton.setVerticalTextPosition(AbstractButton.CENTER);
		calculatorButton.setHorizontalTextPosition(AbstractButton.CENTER);
		calculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		calculatorButton.setBounds(100, 175, 150, 50);
		calculatorButton.setActionCommand("calculatorLaunch");
		calculatorButton.addActionListener(this);
		calculatorButton.setToolTipText("Opens the modifier calculator application");
		add(calculatorButton);
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
