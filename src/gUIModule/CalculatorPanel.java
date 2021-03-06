package gUIModule;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Andrew Walter
 *
 */
public class CalculatorPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Container contentPane;
	JComboBox<String> modSelection;
	JButton includeMod,clearMods;
	JTextArea displayIncludedMods, displayTotalMod;
	//this will be system specific so will require future
	//modification to the parser and schema
	String[] modNames = {"", "Character using off-hand",
			"Character Wounded/Traumatized (per wound/trauma)",
			"Character has superior position", "Touch-only attack",
			"Called shot", "Character wielding a two-handed weapon one-handed",
			"Very small target (insect-sized)", "Small target (child-sized)",
			"Large target (car-sized)", "Very large target (side of barn)",
			"Visibility impaired (Minor: dim light)",
			"Visibility impared (Major: dark)", "Blind attack",
			"Character has reach advantage", "Character charging",
			"Character receiving a charge",
			"Attacker using Smartlink or laser-sight", 
			"Attacker behind cover", "Attacker running", "Attacker in melee",
			"Attacker has reach advantage", "Defender has minor cover",
			"Defender has moderate cover", "Defender has major cover",
			"Defender prone and far (>10 meters)", "Defender hidden",
			"Aimed shot (quick action)", "Aimed shot (compled action)",
			"Sweeping fire with beam weapon (second shot only)",
			"Multiple targets in same Action Phase (per additional target)",
			"Indirect fire", "Point-blank range (<2 meters)",
			"Medium range", "Long range", "Extreme range"};
	
	int[]   modValues = {0, -20, -10, +20, +20, -10, -20, -30, -10, +10, +30, -10, -20,
			-30, +10, -10, +20, +10, -10, -20, -30, +10, -10, -20, -30, -10,
			-60, +10, +30, +10, -20, -30, +10, -10, -20, -30}; 
	int selectedModIndex = -1;
	int totalMod = 0;

	int panelWidth,panelHeight,modHeight,modPlusButtonPlusDisplay,modPlusButtonHeight;

	private int fontSize = 12;

	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @param genericListener 
	 * @return 
	 */
	public CalculatorPanel(int widthOfPanel, int heightOfPanel, MouseAdapter genericListener) {
		super();

		this.setBackground(new Color(15526830));

		panelWidth = widthOfPanel;
		panelHeight = heightOfPanel;
		setLayout(null);
		setBounds(5,0,panelWidth-10,panelHeight);
		
		//adds JComboBox for selecting the modifier to be included in the total
		modSelector(genericListener);

		//adds JButton to trigger the inclusion of the modifier
		includeMods(genericListener);


		//adds JButton to trigger the inclusion of the modifier
		clearMods(genericListener);

		modPlusButtonHeight = modHeight + (int) (panelHeight*0.025) + (int)(panelHeight*0.05);
		
		//add JTextArea for display of currently included modifiers
		displayChosenMods(genericListener);

		modPlusButtonPlusDisplay = modPlusButtonHeight +  (int)(panelHeight*0.7);

		//adds JTextArea for display of total roll modifier
		showTotalMod(genericListener);

	}


	/**
	 * adds JComboBox for selecting the modifier to be included in the total
	 * @param genericListener
	 */
	private void modSelector(MouseAdapter genericListener) {
		modSelection = new JComboBox<String>();
		modSelection.setFont(new Font("Papyrus", Font.BOLD, fontSize ));
		for (int j = 0; j < modNames.length; j++){
			modSelection.addItem(modNames[j]);
		};
		modSelection.setLayout(null);
		modSelection.setBounds(5, 5, (int) (panelWidth*0.9), (int) (panelHeight*0.05)-5);
		modHeight = (int) (panelHeight*0.05);
		modSelection.setActionCommand("modSelected");
		modSelection.addActionListener(this);
		modSelection.addMouseMotionListener(genericListener);
		add(modSelection);
	}


	/**
	 * adds JButton to trigger the inclusion of the modifier
	 * @param genericListener
	 */
	private void includeMods(MouseAdapter genericListener) {
		includeMod = new JButton("Include");
		includeMod.setFont(new Font("Papyrus", Font.BOLD, fontSize ));
		includeMod.setLayout(null);
		includeMod.setBounds(5, modHeight + (int) (panelHeight*0.025), (((int) (panelWidth*0.9))/2)-10, (int)(panelHeight*0.05));
		includeMod.setActionCommand("include");
		includeMod.addActionListener(this);
		includeMod.addMouseMotionListener(genericListener);
		includeMod.setToolTipText("include selected modifier to total");
		add(includeMod);
	}


	/**
	 * adds JButton to trigger the inclusion of the modifier
	 * @param genericListener
	 */
	private void clearMods(MouseAdapter genericListener) {
		clearMods = new JButton("Clear");
		clearMods.setFont(new Font("Papyrus", Font.BOLD, fontSize ));
		clearMods.setLayout(null);
		clearMods.setBounds(((int) (panelWidth*0.9)/2)+5, modHeight + (int) (panelHeight*0.025), (((int) (panelWidth*0.9))/2), (int)(panelHeight*0.05));
		clearMods.setActionCommand("clear");
		clearMods.addActionListener(this);
		clearMods.addMouseMotionListener(genericListener);
		clearMods.setToolTipText("clear all modifiers");
		add(clearMods);
	}


	/**
	 * adds JTextArea to display calculator output
	 * @param genericListener
	 */
	private void showTotalMod(MouseAdapter genericListener) {
		displayTotalMod = new JTextArea();
		displayTotalMod.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		displayTotalMod.setFont(new Font("Papyrus", Font.BOLD, fontSize ));
		displayTotalMod.setLayout(null);
		displayTotalMod.setEditable(false);
		displayTotalMod.setBounds((int) (panelWidth*0.025), modPlusButtonPlusDisplay + (int) (panelHeight*0.05), (int) (panelWidth*0.9), (int)(panelHeight*0.1));
		add(displayTotalMod);
		displayTotalMod.setEnabled(false);
		displayTotalMod.addMouseMotionListener(genericListener);
	}


	/**
	 * adds JTextArea to display list of selected mods.
	 * @param genericListener
	 */
	private void displayChosenMods(MouseAdapter genericListener) {
		//adds JTextArea for display of currently included modifiers
		displayIncludedMods = new JTextArea();
		displayIncludedMods.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		displayIncludedMods.setFont(new Font("Papyrus", Font.BOLD, fontSize ));
		displayIncludedMods.setLayout(null);
		displayIncludedMods.setEditable(false);
		displayIncludedMods.setBounds(5, modPlusButtonHeight + (int) (panelHeight*0.025), (int) (panelWidth*0.9), (int)(panelHeight*0.7));
		add(displayIncludedMods);
		displayIncludedMods.setEnabled(false);
		displayIncludedMods.addMouseMotionListener(genericListener);
	}


	/**
	 * action listener
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//If the modSelection JComboBox is used, record the user selected modifier
		if ("modSelected".equals(e.getActionCommand())){
			selectedModIndex = modSelection.getSelectedIndex();
		}
		//If the clear button is pressed, empty the JTextAreas and set totalMod to zero
		else if ("clear".equals(e.getActionCommand())){
			totalMod = 0;

			displayIncludedMods.setEnabled(true);
			displayIncludedMods.setText("");
			displayIncludedMods.setEnabled(false);
			displayTotalMod.setEnabled(true);
			displayTotalMod.setText("");
			displayIncludedMods.setEnabled(false);
		}
		//If the include button is pressed, call includeSelectedMod(), passing it the
		//index of the mod to be included.
		else if ("include".equals(e.getActionCommand())){
			includeSelectedMod(selectedModIndex);
		}
	}

	/**
	 * adds the value of the selected Mod. to the total modifier value
	 * @param Index
	 */
	private void includeSelectedMod(int Index) { 
		totalMod = totalMod + modValues[Index];

		displayIncludedMods.setEnabled(true);
		displayIncludedMods.append(modNames[Index] + "   " + modValues[Index] + "\r\n");

		displayTotalMod.setEnabled(true);
		displayTotalMod.setText("Total Modifier = " + totalMod);
	}

	/**
	 * adjusts dimensions of the JPanel
	 * @param panelHeight
	 */
	public void setDimensions(int panelHeight){
		modPlusButtonHeight = modHeight + (int) (panelHeight*0.025) + (int)(panelHeight*0.05);
		modPlusButtonPlusDisplay = modPlusButtonHeight +  (int)(panelHeight*0.7);
		setBounds(5,0,panelWidth-10,panelHeight);
		displayIncludedMods.setBounds(5, modPlusButtonHeight + (int) (panelHeight*0.025), (int) (panelWidth*0.9), (int)(panelHeight*0.7));
		displayTotalMod.setBounds(5, modPlusButtonPlusDisplay + (int) (panelHeight*0.05), (int) (panelWidth*0.9), (int)(panelHeight*0.1));
	}
}
