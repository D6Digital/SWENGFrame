package gUIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
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

	JComboBox<String> diceType,secondDiceType;
	JComboBox<Integer> diceQuantity,secondDiceQuantity;

	JRadioButton multiDice;
	Boolean multiDiceEnabled = false;

	JButton rollButton;
	JTextArea firstResultOutput,secondResultOutput,displayTotalResult;

	String[] diceTypes = {"d2", "d3", "d4", "d6", "d8", "d10", "d12", "d20", "d100"};
	String selectedDiceType = "d0";
	String secondSelectedDiceType = "d0";
	
	int selectedDiceQuantity = 0;
	int secondSelectedDiceQuantity = 0;
	int panelWidth,panelHeight;

	private int fontSize = 12;

	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @param genericListener 
	 * @return 
	 */
	public DicePanel(int widthOfPanel, int heightOfPanel, MouseAdapter genericListener) {
		super();

		this.setBackground(new Color(15526830));
		panelWidth = widthOfPanel;
		panelHeight = heightOfPanel;

		setLayout(null);
		setBounds(3,0,panelWidth, panelHeight);

		//adds a JComboBox for selecting the type of dice to be rolled
		setupDiceType(genericListener);

		//adds a JComboBox for selecting the type of dice to be rolled
		setupDiceQuantity(genericListener);

		//adds a radioButton for enabling dual dice type rolling
		setupMultiDice(genericListener);

		//adds a second JComboBox for selecting the type of dice to be rolled
		//only enabled if the addSecondDice radioButton is checked
		setupSecDiceType(genericListener);

		//adds a second JComboBox for selecting the type of dice to be rolled
		//only enabled if the addSecondDice radioButton is checked
		setupSecDiceQuantity(genericListener);

		//adds a JButton to trigger algorithum based on selected inputs
		setupRollButton(genericListener);

		//adds a JTextArea for display of results of a single type of dice rolling
		displayFirstResult(genericListener);

		//adds a JTextArea for display of results of rolling an addition dice type
		//if multiDice is enabled
		displaySecResult(genericListener);

		//adds JTextArea for display of total of all dice types rolled
		displayTotalResult(genericListener);
	}

	/**
	 * adds JTextArea for display of total of all dice types rolled
	 * @param genericListener sets the mouse listener
	 */
	private void displayTotalResult(MouseAdapter genericListener) {
		displayTotalResult = new JTextArea();
		displayTotalResult.setWrapStyleWord(true);
		displayTotalResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		displayTotalResult.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		displayTotalResult.setBounds(
				(int) (panelWidth*0.025), 
				(int) (panelHeight*0.92),
				(int) (panelWidth*0.925),
				(int) (panelHeight*0.035));
		add(displayTotalResult);
		displayTotalResult.addMouseMotionListener(genericListener);
		displayTotalResult.setEnabled(false);
	}

	/**
	 * adds a JTextArea for display of results of rolling an addition dice type
		if multiDice is enabled
	 * @param genericListener sets the mouse listener
	 */
	private void displaySecResult(MouseAdapter genericListener) {
		secondResultOutput = new JTextArea();
		secondResultOutput.setWrapStyleWord(true);
		secondResultOutput.setLineWrap(true);
		secondResultOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		secondResultOutput.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		secondResultOutput.setBounds(
				(int) (panelWidth*0.025) + (int) (panelWidth*0.45) +  (int) (panelWidth*0.025), 
				(int) (panelWidth*0.025) + (int) (panelHeight*0.1) + (int) (panelHeight*0.03) +  (int) (panelHeight*0.03),
				(int) (panelWidth*0.45),
				(int) (panelHeight*0.75));
		add(secondResultOutput);
		secondResultOutput.addMouseMotionListener(genericListener);
		secondResultOutput.setEnabled(false);
	}

	/**
	 * adds a JTextArea for display of results of a single type of dice rolling
	 * @param genericListener sets the mouse listener
	 */
	private void displayFirstResult(MouseAdapter genericListener) {
		firstResultOutput = new JTextArea();
		firstResultOutput.setWrapStyleWord(true);
		firstResultOutput.setLineWrap(true);
		firstResultOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		firstResultOutput.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		firstResultOutput.setBounds(
				(int) (panelWidth*0.025), 
				(int) (panelWidth*0.025) + (int) (panelHeight*0.1) + (int) (panelHeight*0.03) +  (int) (panelHeight*0.03),
				(int) (panelWidth*0.45),
				(int) (panelHeight*0.75));
		add(firstResultOutput);
		firstResultOutput.addMouseMotionListener(genericListener);
		firstResultOutput.setEnabled(false);
	}

	/**
	 * adds a JButton to trigger algorithum based on selected inputs
	 * @param genericListener sets the mouse listener
	 */
	private void setupRollButton(MouseAdapter genericListener) {
		rollButton = new JButton("Roll");
		rollButton.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		rollButton.setVerticalTextPosition(AbstractButton.CENTER);
		rollButton.setHorizontalTextPosition(AbstractButton.CENTER);
		rollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rollButton.setBounds(
				(int) (panelWidth*0.025) + (int) (2*panelWidth*0.18) + (int) (panelWidth*0.3) + (int) (2*panelWidth*0.025), 
				(int) (panelWidth*0.025) + (int) (panelHeight*0.04),
				(int) (panelWidth*0.25),
				(int) (panelHeight*0.05));
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		rollButton.addMouseMotionListener(genericListener);
		rollButton.setToolTipText("rolls dice based on the parameters given");
		add(rollButton);
	}

	/**
	 * adds a second JComboBox for selecting the type of dice to be rolled
		only enabled if the addSecondDice radioButton is checked
	 * @param genericListener sets the mouse listener
	 */
	private void setupSecDiceQuantity(MouseAdapter genericListener) {
		secondDiceQuantity = new JComboBox<Integer>();
		secondDiceQuantity.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		for (int i = 0; i < 100; i++){
			secondDiceQuantity.addItem(i+1);
		};
		secondDiceQuantity.setBounds(
				(int) (panelWidth*0.025) + (int) (panelWidth*0.13) + (int) (panelWidth*0.3) + (int) (panelWidth*0.025), 
				(int) (panelWidth*0.025) + (int) (panelHeight*0.05) + (int) (panelWidth*0.03),
				(int) (panelWidth*0.2),
				(int) (panelHeight*0.05));
		secondDiceQuantity.setActionCommand("secondDiceQuantitySelected");
		secondDiceQuantity.addActionListener(this);
		secondDiceQuantity.addMouseMotionListener(genericListener);
		add(secondDiceQuantity);
		secondDiceQuantity.setEnabled(false);
	}

	/**
	 * adds a second JComboBox for selecting the type of dice to be rolled
	 *only enabled if the addSecondDice radioButton is checked
	 * @param genericListener sets the mouse listener
	 */
	private void setupSecDiceType(MouseAdapter genericListener) {
		secondDiceType = new JComboBox<String>();
		secondDiceType.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		for (int i = 0; i < diceTypes.length; i++){
			secondDiceType.addItem(diceTypes[i]);
		};
		secondDiceType.setBounds(
				(int) (panelWidth*0.025) + (int) (panelWidth*0.13) + (int) (panelWidth*0.3) + (int) (panelWidth*0.025), 
				(int) (panelWidth*0.025),
				(int) (panelWidth*0.2),
				(int) (panelHeight*0.05));
		secondDiceType.setActionCommand("secondDiceTypeSelected");
		secondDiceType.addActionListener(this);
		secondDiceType.addMouseMotionListener(genericListener);
		add(secondDiceType);
		secondDiceType.setEnabled(false);
	}

	/**
	 * adds a radioButton for enabling dual dice type rolling
	 * @param genericListener sets the mouse listener
	 */
	private void setupMultiDice(MouseAdapter genericListener) {
		multiDice = new JRadioButton("<html>Enable MultiDice</html>");
		multiDice.setFont(new Font("Papyrus", Font.PLAIN, (int) (fontSize*0.9)));
		multiDice.setOpaque(false);
		multiDice.setBounds(              
				(int) (panelWidth*0.025) + (int) (panelWidth*0.18) + (int) (panelWidth*0.02), 
				(int) (panelWidth*0.025) + (int) (panelWidth*0.02), 
				(int) (panelWidth*0.25),
				(int) (panelHeight*0.1));
		multiDice.setActionCommand("multiDiceClicked");
		multiDice.addActionListener(this);
		multiDice.addMouseMotionListener(genericListener);
		multiDice.setSelected(false);
		add(multiDice);
	}

	/**
	 * adds a JComboBox for selecting the type of dice to be rolled
	 * @param genericListener sets the mouse listener
	 */
	private void setupDiceQuantity(MouseAdapter genericListener) {
		diceQuantity = new JComboBox<Integer>();
		diceQuantity.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		for (int i = 0; i < 100; i++){
			diceQuantity.addItem(i+1);
		};
		diceQuantity.setBounds(
				(int) (panelWidth*0.025), 
				(int) (panelWidth*0.025) + (int) (panelHeight*0.05) + (int) (panelWidth*0.03),
				(int) (panelWidth*0.2),
				(int) (panelHeight*0.05));
		diceQuantity.setActionCommand("diceQuantitySelected");
		diceQuantity.addActionListener(this);
		diceQuantity.addMouseMotionListener(genericListener);
		add(diceQuantity);
	}

	/**
	 * adds a JComboBox for selecting the type of dice to be rolled
	 * @param genericListener sets the mouse listener
	 */
	private void setupDiceType(MouseAdapter genericListener) {
		diceType = new JComboBox<String>();
		diceType.setFont(new Font("Papyrus", Font.BOLD, fontSize));
		for (int i = 0; i < diceTypes.length; i++){
			diceType.addItem(diceTypes[i]);
		};
		diceType.setBounds(
				(int) (panelWidth*0.025),
				(int) (panelWidth*0.025),
				(int) (panelWidth*0.2),
				(int) (panelHeight*0.05));
		diceType.setActionCommand("diceTypeSelected");
		diceType.addActionListener(this);
		diceType.addMouseMotionListener(genericListener);
		add(diceType);
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
	 * Runs the dice roller algorithm and displays the results
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

			firstResultOutput.append(roll + " + ");
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
		firstResultOutput.append(" 1st Dice \n Total = " + firstDiceTypeTotalResult + ".\r\n");
		firstResultOutput.append(" 1s rolled = " + firstDiceTypeTotalCriticalSuccesses + ".\r\n");
		firstResultOutput.append(" " + upperRange + "s rolled = " + firstDiceTypeTotalCriticalFails + ".\r\n");

		if (multiDiceEnabled){
			//Switch case statement to set the bounds of the algorithm based on the user
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
				secondResultOutput.append(roll + " + ");
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
			secondResultOutput.append(" 2nd Dice \n Total = " + secondDiceTypeTotalResult + ".\r\n");
			secondResultOutput.append(" 1s rolled = " + secondDiceTypeTotalCriticalSuccesses + ".\r\n");
			secondResultOutput.append(" " + upperRange + "s rolled = " + secondDiceTypeTotalCriticalFails + ".\r\n");

		}

		sumOfRolls = firstDiceTypeTotalResult + secondDiceTypeTotalResult;

		displayTotalResult.setEnabled(true);
		displayTotalResult.setText("Total Dice Rolls = " + sumOfRolls);
	}

	/**
	 * sets the size of the panel based on the dimensions of the presentation
	 * @param height he height of the presentation
	 */
	public void setDimensions(int height){
		setBounds(3,0,panelWidth, height);
		displayTotalResult.setBounds(
				(int) (panelWidth*0.025), 
				(int) (height*0.92),
				(int) (panelWidth*0.925),
				(int) (height*0.05)-(int)(height*0.025));
		firstResultOutput.setBounds(
				(int) (panelWidth*0.025), 
				(int) (panelWidth*0.025) + (int) (height*0.1) + (int) (height*0.03) +  (int) (height*0.03),
				(int) (panelWidth*0.45),
				(int) (height*0.75));
		secondResultOutput.setBounds(
				(int) (panelWidth*0.025) + (int) (panelWidth*0.45) +  (int) (panelWidth*0.025), 
				(int) (panelWidth*0.025) + (int) (height*0.1) + (int) (height*0.03) +  (int) (height*0.03),
				(int) (panelWidth*0.45),
				(int) (height*0.75));
	}

}