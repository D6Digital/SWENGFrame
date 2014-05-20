package gUIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import musicPlayerModule.StandAloneMusicPlayer;

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
	

	//JPanel UtilitiesPanel;
	JPanel multiPanel= new JPanel();
	CalculatorPanel calculatorPanel = new CalculatorPanel(); //= new CalculatorPanel();
	DicePanel dicePanel = new DicePanel();
	JPanel standAloneMusicPlayerPanel = new JPanel();
	StandAloneMusicPlayer standAloneMusicPlayer = new StandAloneMusicPlayer(); //= new StandAloneMusicPlayer();
//	GUI diceRoller;
//	GUI calculatorGUI;
//	GUI audioPlayer;
	
	/**
	 * Create a simple panel and add the JButtons for the utilities
	 */
	public UtilitiesPanel() {
		//Calls JPanel to create the UtilitiesPanel object.
		//Sets the layout to BoxLayout
	    
		super();
		setLayout(null);
		
		standAloneMusicPlayerPanel = standAloneMusicPlayer.getFullControlPanel();
		
		//Adds JButtons for diceRoller and calculator
		addButtons("Dice Roller", "diceRollerLaunch",
				   "Opens the dice roller application", 17, 25);
		
		addButtons("Modifier Calculator", "calculatorLaunch",
				   "Opens the modifier calculator application", 17, 100);
		
	    addButtons("Standalone Audio Player", "audioPlayerLaunch",
                  "Opens the audio player application", 17, 175);
		
	    multiPanel.setLayout(null);
		multiPanel.setBounds(0,200,GUI.utilitiesWidth,400);
		multiPanel.setBackground(Color.BLACK);
		calculatorPanel.setBounds(0,0,GUI.utilitiesWidth,400);
		dicePanel.setBounds(0,0,GUI.utilitiesWidth,400);
		standAloneMusicPlayerPanel.setBounds(0,0,GUI.utilitiesWidth,400);
		multiPanel.add(calculatorPanel);
		multiPanel.add(standAloneMusicPlayerPanel);
		multiPanel.add(dicePanel);
		multiPanel.setOpaque(false);
		this.add(multiPanel);
		
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
			setPanelVisible("dicePanel");
		}
		else if ("calculatorLaunch".equals(e.getActionCommand())) {
		    setPanelVisible("calculatorPanel");
		}
	    else if ("audioPlayerLaunch".equals(e.getActionCommand())) {
	        setPanelVisible("standAloneMusicPlayerPanel");
        }
	   
	}

	private void setPanelVisible(String panel) {
	    switch(panel) {
	    case "dicePanel":
            dicePanel.setVisible(true);
            calculatorPanel.setVisible(false);
            standAloneMusicPlayerPanel.setVisible(false);
	    break;    
	    case "calculatorPanel":
            dicePanel.setVisible(false);
            calculatorPanel.setVisible(true);
            standAloneMusicPlayerPanel.setVisible(false);
	    break;
	    case "standAloneMusicPlayerPanel":
            dicePanel.setVisible(false);
            calculatorPanel.setVisible(false);
            standAloneMusicPlayerPanel.setVisible(true);  
	    break;
	    default: break;
	    }
	    multiPanel.repaint();
	}
	
}
