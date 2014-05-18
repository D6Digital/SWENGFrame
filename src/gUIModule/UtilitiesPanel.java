package gUIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.Overall;

import gUIModule.GUI;

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
	JPanel multiPanel= new JPanel();
	CalculatorPanel calculator = new CalculatorPanel();
	
	
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
		addButtons("Dice Roller", "diceRollerLaunch",
				   "Opens the dice roller application", 17, 25);
		
		addButtons("Modifier Calculator", "calculatorLaunch",
				   "Opens the modifier calculator application", 17, 100);
		multiPanel.setBounds(0,200,GUI.utilitiesWidth,400);
		multiPanel.setBackground(Color.BLACK);
		calculator.setBounds(0,0,GUI.utilitiesWidth,400);
		multiPanel.add(calculator);
		add(multiPanel);
		
	}
	
	/**
	 * Method for creating JButtons with specified paramiters
	 */
	public void addButtons(String buttonText, String actionCommand, String toolTip,
						   int x_coord, int y_coord) {
		
		JButton button = new JButton(buttonText);
		button.setVerticalTextPosition(AbstractButton.CENTER);
		button.setHorizontalTextPosition(AbstractButton.CENTER);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBounds(x_coord, y_coord, 150, 50);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
		button.setToolTipText(toolTip);
		add(button);
	}
	
	/**
	 * When a utility button is released instantiate the utility
	 * if it doesn't already exist
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("diceRollerLaunch".equals(e.getActionCommand())){
			if (diceRollerExists == false){
				GUI diceRoller = new GUI("diceRollerPanel");
				diceRollerExists = true;
			}
			else if (diceRollerExists == true){
				//TODO bring diceRoller to the front of the screen
			}
		}
		else if ("calculatorLaunch".equals(e.getActionCommand())) {
			if (calculatorExists == false){
				GUI calculator = new GUI("calculatorPanel");
				calculatorExists = true;
			}
			else if (calculatorExists == true){
				//TODO bring calculator to front of the screen
			}
		}
	}
	
	//public void setVisibility(boolean visible){
	//		this.setVisible(visible);
	//	}
	
}
