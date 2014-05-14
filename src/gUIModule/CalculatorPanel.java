package gUIModule;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class CalculatorPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Container contentPane;
	JComboBox modSelection;
	JButton includeMod;
	JButton clearMods;
	JTextArea displayIncludedMods;
	JTextArea displayTotalMod;
	//will come from XML file in completed code
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
	//will come from XML file in completed code
	int[]   modValues = {0, -20, -10, +20, +20, -10, -20, -30, -10, +10, +30, -10, -20,
			             -30, +10, -10, +20, +10, -10, -20, -30, +10, -10, -20, -30, -10,
			             -60, +10, +30, +10, -20, -30, +10, -10, -20, -30}; 
	int selectedModIndex = -1;
	int totalMod = 0;
	
	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @return 
	 */
	public CalculatorPanel() {
		super();
		setLayout(null);
		
		//adds JComboBox for selecting the modifier to be included in the total
		modSelection = new JComboBox();
		for (int j = 0; j < modNames.length; j++){
			modSelection.addItem(modNames[j]);
		};
		modSelection.setBounds(10, 10, 365, 30);
		modSelection.setActionCommand("modSelected");
		modSelection.addActionListener(this);
		add(modSelection);
		
		//adds JButton to trigger the inclution of the modifier
	    includeMod = new JButton("Include");
	    includeMod.setVerticalTextPosition(AbstractButton.CENTER);
	    includeMod.setHorizontalTextPosition(AbstractButton.CENTER);
	    includeMod.setAlignmentX(Component.CENTER_ALIGNMENT);
	    includeMod.setBounds(10, 45, 100, 30);
	    includeMod.setActionCommand("include");
	    includeMod.addActionListener(this);
	    includeMod.setToolTipText("include selected modifier to total");
		add(includeMod);
		
		//adds JButton to trigger the inclution of the modifier
	    clearMods = new JButton("Clear");
	    clearMods.setVerticalTextPosition(AbstractButton.CENTER);
	    clearMods.setHorizontalTextPosition(AbstractButton.CENTER);
	    clearMods.setAlignmentX(Component.CENTER_ALIGNMENT);
	    clearMods.setBounds(115, 45, 100, 30);
	    clearMods.setActionCommand("clear");
	    clearMods.addActionListener(this);
	    clearMods.setToolTipText("clear all modifiers");
		add(clearMods);
		
		//adds JTextArea for display of currently included modifiers
		displayIncludedMods = new JTextArea();
		displayIncludedMods.setEditable(false);
		displayIncludedMods.setBounds(10, 80, 365, 430);
		add(displayIncludedMods);
		displayIncludedMods.setEnabled(false);
		
		//adds JTextArea for display of total roll modifier
		displayTotalMod = new JTextArea();
        displayTotalMod.setEditable(false);
		displayTotalMod.setBounds(10, 520, 365, 30);
		add(displayTotalMod);
		displayTotalMod.setEnabled(false);
	}
	
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

	private void includeSelectedMod(int Index) { 
		totalMod = totalMod + modValues[Index];
	   
	    displayIncludedMods.setEnabled(true);
	    displayIncludedMods.append(modNames[Index] + "   " + modValues[Index] + "\r\n");
	   
	    displayTotalMod.setEnabled(true);
	    displayTotalMod.setText("Total Modifier = " + totalMod);
	}
}
