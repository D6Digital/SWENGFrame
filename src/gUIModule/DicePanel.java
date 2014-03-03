package gUIModule;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Andrew Walter
 *
 */
public class DicePanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Container contentPane;
	JComboBox diceType;
	JComboBox diceQuantity;
	JButton rollButton;
	JTextArea resultOutput;
	String[] diceTypes = {"d2", "d4", "d6", "d8", "d10", "d12", "d20", "d100"};
	String selectedDiceType = "d0";
	int selectedDiceQuantity = 0;
	
	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @return 
	 */
	public DicePanel() {
		super();
		setLayout(null);
		
		//adds JComboBox for selecting the type of dice to be rolled
		diceType = new JComboBox();
		for (int i = 0; i < diceTypes.length; i++){
			diceType.addItem(diceTypes[i]);
		};
		diceType.setBounds(10, 15, 100, 30);
		diceType.setActionCommand("diceTypeSelected");
		diceType.addActionListener(this);
		add(diceType);
		
		//adds JComboBox for selecting the type of dice to be rolled
		diceQuantity = new JComboBox();
		for (int i = 0; i < 100; i++){
			diceQuantity.addItem(i+1);
		};
		diceQuantity.setBounds(10, 55, 100, 30);
		diceQuantity.setActionCommand("diceQuantitySelected");
		diceQuantity.addActionListener(this);
		add(diceQuantity);
		
		//adds JButton to trigger algorithum based on selected inputs
	    rollButton = new JButton("Roll");
		rollButton.setVerticalTextPosition(AbstractButton.CENTER);
		rollButton.setHorizontalTextPosition(AbstractButton.CENTER);
		rollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rollButton.setBounds(120, 25, 100, 50);
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		rollButton.setToolTipText("rolls dice based on the parameters given");
		add(rollButton);
		
		//adds JTextArea for display of results of algorithum
		resultOutput = new JTextArea();
		resultOutput.setBounds(10, 100, 365, 150);
		add(resultOutput);
		resultOutput.setEnabled(false);
	}
	
	/**
	 * actionListener, stores the user selected values for dice type and quantity
	 * calls rollDice() when the calculation is needed
	 * @return 
	 */
	public void actionPerformed(ActionEvent e) {
		//If the dice type JComboBox is used, record the user selected dice type
		if ("diceTypeSelected".equals(e.getActionCommand())){
			selectedDiceType = (String) diceType.getSelectedItem();
		}
		//If the dice quantity JComboBox is used, record the user selected dice quantity
		else if ("diceQuantitySelected".equals(e.getActionCommand())){
			selectedDiceQuantity = (int) diceQuantity.getSelectedItem();
		}
		//If the roll button is pushed, call the rollDice() method
		else if ("roll".equals(e.getActionCommand())){
			rollDice();
		}
	}
	
	/**
	 * Runs the dice roller algorithum and displays the results
	 * @return 
	 */
	public void rollDice() {
		int upperRange = 0;
		int roll = 0;
		int totalResult = 0;
		int lineOverflow = 0;
		
		//Switch case statement to set the bounds of the algorithum based on the user
		//selected dice type
		switch (selectedDiceType) {
			case "d2":
				upperRange = 2;
				break;
			case "d4":
				upperRange = 4;
				break;
			case "d6":
				upperRange = 6;
				break;
			case "d8":
				upperRange = 8;
				break;
			case "d10":
				upperRange = 10;
				break;
			case "d12":
				upperRange = 12;
				break;
			case "d20":
				upperRange = 20;
				break;
			case "d100":
				upperRange = 100;
				break;
			default:
				upperRange = 1;
				break;
		}
		
		//Enable and clear the output display area
		resultOutput.setEnabled(true);
		resultOutput.setText("");
		Random generator = new Random();
		
		//for loop to "roll" the user selected type of dice the selected number of times
		for (int j = 0; j < (selectedDiceQuantity - 1); j++){ 
			roll = generator.nextInt(upperRange) + 1;
			totalResult = totalResult + roll;
			//If statement effect a carage return on the display when the number of
			//individual values being displayed exceeds the width of the JTextArea
			if (lineOverflow > 15) {
				resultOutput.append(roll + " + \r\n");
				lineOverflow = 0;
			}
			else {
				resultOutput.append(roll + " + ");
				lineOverflow = lineOverflow + 1;
			}
		}
		
		//Final dice "rolled" and total result displayed
		roll = generator.nextInt(upperRange) + 1;
		totalResult = totalResult + roll;
		resultOutput.append(roll + "\r\n");
		resultOutput.append(" = " + totalResult + ".");
	}
}