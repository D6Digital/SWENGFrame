package gUIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	JLabel background; 
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
		
		//Set up background image
		BufferedImage backgroundImage;
		try{
			backgroundImage = ImageIO.read(new File("resources/buttons/Background.png"));
			Image scaledBackground = backgroundImage.getScaledInstance(GUI.utilitiesWidth,250,java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, GUI.utilitiesWidth, 250);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		//Adds JButtons for diceRoller and calculator
		addButtons("diceRollerLaunch", "Opens the dice roller application", 17, 25, "DiceButton.png");
		
		addButtons("calculatorLaunch", "Opens the modifier calculator application", 140, 80, "CalculatorButton.png");
		
	    addButtons("audioPlayerLaunch", "Opens the audio player application", 17, 140, "AudioButton.png");
		
	    multiPanel.setLayout(null);
		multiPanel.setBounds(0,250,GUI.utilitiesWidth,700);
		multiPanel.setBackground(Color.BLACK);
		calculatorPanel.setBounds(0,0,GUI.utilitiesWidth,700);
		dicePanel.setBounds(0,0,GUI.utilitiesWidth,700);
		standAloneMusicPlayerPanel.setBounds(0,0,GUI.utilitiesWidth,700);
		multiPanel.add(calculatorPanel);
		multiPanel.add(standAloneMusicPlayerPanel);
		multiPanel.add(dicePanel);
		multiPanel.setOpaque(false);
		this.add(multiPanel);
		
	}
	
	/**
	 * Method for creating JButtons with specified paramiters
	 */
	public void addButtons(String actionCommand, String toolTip,
						   int x_coord, int y_coord, String image) {
		
		JButton button = new JButton();
		button.setVerticalTextPosition(AbstractButton.CENTER);
		button.setHorizontalTextPosition(AbstractButton.CENTER);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBounds(x_coord, y_coord, 100, 100);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
		button.setToolTipText(toolTip);
		button.setBorderPainted(false); 
		button.setContentAreaFilled(false); 
		button.setFocusPainted(false); 
		button.setOpaque(false);
		BufferedImage buttonImage;
		try{
			buttonImage = ImageIO.read(new File("resources/buttons/"+image));
			Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){
			
		}
		add(button);
		this.add(background);
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
