package gUIModule;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	JComboBox secondDiceType;
	JComboBox diceQuantity;
	JComboBox secondDiceQuantity;
	
	JRadioButton multiDice;
	Boolean multiDiceEnabled = false;
	
	JButton rollButton;
	JTextArea firstResultOutput;
	JTextArea secondResultOutput;
	JTextArea displayTotalResult;
	
	String[] diceTypes = {"d2", "d3", "d4", "d6", "d8", "d10", "d12", "d20", "d100"};
	String selectedDiceType = "d0";
	int selectedDiceQuantity = 0;
	String secondSelectedDiceType = "d0";
	int secondSelectedDiceQuantity = 0;
	
	int panelWidth;
	int panelHeight;
	
	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @return 
	 */
	public DicePanel() {
		super();
		setLayout(null);
		setBounds(0,0,400, 600);
		
		//adds a JComboBox for selecting the type of dice to be rolled
		diceType = new JComboBox();
		for (int i = 0; i < diceTypes.length; i++){
			diceType.addItem(diceTypes[i]);
		};
		diceType.setBounds(10, 15, 55, 30);
		diceType.setActionCommand("diceTypeSelected");
		diceType.addActionListener(this);
		add(diceType);
		
		//adds a JComboBox for selecting the type of dice to be rolled
		diceQuantity = new JComboBox();
		for (int i = 0; i < 100; i++){
			diceQuantity.addItem(i+1);
		};
		diceQuantity.setBounds(10, 55, 55, 30);
		diceQuantity.setActionCommand("diceQuantitySelected");
		diceQuantity.addActionListener(this);
		add(diceQuantity);
		
		//adds a radioButton for enabling dual dice type rolling
		multiDice = new JRadioButton("Enable MultiDice");
		multiDice.setBounds(65, 45, 120, 15);
		multiDice.setActionCommand("multiDiceClicked");
		multiDice.addActionListener(this);
		multiDice.setSelected(false);
		add(multiDice);
		
		//adds a second JComboBox for selecting the type of dice to be rolled
		//only enabled if the addSecondDice radioButton is checked
		secondDiceType = new JComboBox();
		for (int i = 0; i < diceTypes.length; i++){
			secondDiceType.addItem(diceTypes[i]);
		};
		secondDiceType.setBounds(195, 15, 55, 30);
		secondDiceType.setActionCommand("secondDiceTypeSelected");
		secondDiceType.addActionListener(this);
		add(secondDiceType);
		secondDiceType.setEnabled(false);
		
		//adds a second JComboBox for selecting the type of dice to be rolled
		//only enabled if the addSecondDice radioButton is checked
		secondDiceQuantity = new JComboBox();
		for (int i = 0; i < 100; i++){
			secondDiceQuantity.addItem(i+1);
		};
		secondDiceQuantity.setBounds(195, 55, 55, 30);
		secondDiceQuantity.setActionCommand("secondDiceQuantitySelected");
		secondDiceQuantity.addActionListener(this);
		add(secondDiceQuantity);
		secondDiceQuantity.setEnabled(false);
		
		//adds a JButton to trigger algorithum based on selected inputs
	    rollButton = new JButton("Roll");
		rollButton.setVerticalTextPosition(AbstractButton.CENTER);
		rollButton.setHorizontalTextPosition(AbstractButton.CENTER);
		rollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rollButton.setBounds(255, 25, 100, 50);
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		rollButton.setToolTipText("rolls dice based on the parameters given");
		add(rollButton);
		
		//adds a JTextArea for display of results of a single type of dice rolling
		firstResultOutput = new JTextArea();
		firstResultOutput.setBounds(10, 100, 180, 450);
		add(firstResultOutput);
		firstResultOutput.setEnabled(false);
		
		//adds a JTextArea for display of results of rolling an addition dice type
		//if multiDice is enabled
		secondResultOutput = new JTextArea();
		secondResultOutput.setBounds(200, 100, 180, 450);
		add(secondResultOutput);
		secondResultOutput.setEnabled(false);
		
		//adds JTextArea for display of total of all dice types rolled
		displayTotalResult = new JTextArea();
		displayTotalResult.setBounds(10, 560, 370, 30);
		add(displayTotalResult);
		displayTotalResult.setEnabled(false);
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
		//If the multiDice JRadioButton is used,
		//toggle the additional JComboBoxes as appropreate
		else if ("multiDiceClicked".equals(e.getActionCommand())){
			if (!multiDiceEnabled){
				secondDiceType.setEnabled(true);
				secondDiceQuantity.setEnabled(true);
				multiDiceEnabled = true;
			}
			else if (multiDiceEnabled){
				secondDiceType.setEnabled(false);
				secondDiceQuantity.setEnabled(false);
				
				secondResultOutput.setText("");
				secondResultOutput.setEnabled(false);
				
				multiDiceEnabled = false;
			}
		}
		//If the dice type JComboBox is used, record the user selected dice type
		if ("secondDiceTypeSelected".equals(e.getActionCommand())){
			secondSelectedDiceType = (String) secondDiceType.getSelectedItem();
		}
		//If the dice quantity JComboBox is used, record the user selected dice quantity
		else if ("secondDiceQuantitySelected".equals(e.getActionCommand())){
			secondSelectedDiceQuantity = (int) secondDiceQuantity.getSelectedItem();
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
		int firstDiceTypeTotalResult = 0;
		int firstDiceTypeTotalCriticalSuccesses = 0;
		int firstDiceTypeTotalCriticalFails = 0;
		int secondDiceTypeTotalResult = 0;
		int secondDiceTypeTotalCriticalSuccesses = 0;
		int secondDiceTypeTotalCriticalFails = 0;
		int sumOfRolls = 0;
		int lineOverflow = 0;
		
		//Switch case statement to set the bounds of the algorithum based on the user
		//selected dice type
		switch (selectedDiceType) {
			case "d2":
				upperRange = 2;
				break;
			case "d3":
				upperRange = 3;
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
				upperRange = 2;
				break;
		}
		
		//Enable and clear the output display area
		firstResultOutput.setEnabled(true);
		firstResultOutput.setText("");
		Random generator = new Random();
		
		//for loop to "roll" the user selected type of dice the selected number of times
		for (int j = 0; j < (selectedDiceQuantity - 1); j++){ 
			roll = generator.nextInt(upperRange) + 1;
			firstDiceTypeTotalResult = firstDiceTypeTotalResult + roll;
			if (roll == 1){
				firstDiceTypeTotalCriticalSuccesses = firstDiceTypeTotalCriticalSuccesses + 1; 
			}
			else if (roll == upperRange){
				firstDiceTypeTotalCriticalFails = firstDiceTypeTotalCriticalFails + 1;
			}
			
			//If statement effects a carage return on the display when the number of
			//individual values being displayed exceeds the width of the JTextArea
			if (lineOverflow > 4) {
				firstResultOutput.append(roll + " + \r\n");
				lineOverflow = 0;
			}
			else {
				firstResultOutput.append(roll + " + ");
				lineOverflow = lineOverflow + 1;
			}
		}
		
		//Final dice "rolled" and total result displayed
		roll = generator.nextInt(upperRange) + 1;
		firstDiceTypeTotalResult = firstDiceTypeTotalResult + roll;
		if (roll == 1){
			firstDiceTypeTotalCriticalSuccesses = firstDiceTypeTotalCriticalSuccesses + 1; 
		}
		else if (roll == upperRange){
			firstDiceTypeTotalCriticalFails = firstDiceTypeTotalCriticalFails + 1;
		}
		
		firstResultOutput.append(roll + "\r\n");
		firstResultOutput.append(" 1st Dice Type Total = " + firstDiceTypeTotalResult + ".\r\n");
		firstResultOutput.append(" 1s rolled = " + firstDiceTypeTotalCriticalSuccesses + ".\r\n");
		firstResultOutput.append(" " + upperRange + "s rolled = " + firstDiceTypeTotalCriticalFails + ".\r\n");
		
		if (multiDiceEnabled){
			lineOverflow = 0;
			
			//Switch case statement to set the bounds of the algorithum based on the user
			//selected dice type
			switch (secondSelectedDiceType) {
				case "d2":
					upperRange = 2;
					break;
				case "d3":
					upperRange = 3;
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
					upperRange = 2;
					break;
			}
			
			//Enable and clear the output display area
			secondResultOutput.setEnabled(true);
			secondResultOutput.setText("");
			
			//for loop to "roll" the user selected type of dice the selected number
			//of times
			for (int j = 0; j < (secondSelectedDiceQuantity - 1); j++){ 
				roll = generator.nextInt(upperRange) + 1;
				secondDiceTypeTotalResult = secondDiceTypeTotalResult + roll;
				if (roll == 1){
					secondDiceTypeTotalCriticalSuccesses = secondDiceTypeTotalCriticalSuccesses + 1; 
				}
				else if (roll == upperRange){
					secondDiceTypeTotalCriticalFails = secondDiceTypeTotalCriticalFails + 1;
				}
				
				//If statement effects a carage return on the display when the number of
				//individual values being displayed exceeds the width of the JTextArea
				if (lineOverflow > 4) {
					secondResultOutput.append(roll + " + \r\n");
					lineOverflow = 0;
				}
				else {
					secondResultOutput.append(roll + " + ");
					lineOverflow = lineOverflow + 1;
				}
			}
			
			//Final dice "rolled" and total result displayed
			roll = generator.nextInt(upperRange) + 1;
			secondDiceTypeTotalResult = secondDiceTypeTotalResult + roll;
			if (roll == 1){
				secondDiceTypeTotalCriticalSuccesses = secondDiceTypeTotalCriticalSuccesses + 1; 
			}
			else if (roll == upperRange){
				secondDiceTypeTotalCriticalFails = secondDiceTypeTotalCriticalFails + 1;
			}
			
			secondResultOutput.append(roll + "\r\n");
			secondResultOutput.append(" 2nd Dice Type Total = " + secondDiceTypeTotalResult + ".\r\n");
			secondResultOutput.append(" 1s rolled = " + secondDiceTypeTotalCriticalSuccesses + ".\r\n");
			secondResultOutput.append(" " + upperRange + "s rolled = " + secondDiceTypeTotalCriticalFails + ".\r\n");
			
		}
		
		sumOfRolls = firstDiceTypeTotalResult + secondDiceTypeTotalResult;
		
		displayTotalResult.setEnabled(true);
		displayTotalResult.setText("Total Dice Rolls = " + sumOfRolls);
	}
}