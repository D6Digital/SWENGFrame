package gUIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
public class UtilitiesPanel extends JPanel{ //implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	//JPanel UtilitiesPanel;
	JPanel multiPanel= new JPanel();
	CalculatorPanel calculatorPanel; //= new CalculatorPanel();
	DicePanel dicePanel;
	JPanel standAloneMusicPlayerPanel;
	StandAloneMusicPlayer standAloneMusicPlayer; //= new StandAloneMusicPlayer();
	JLabel background; 
	ArrayList<JButton> buttonList = new ArrayList<>();
	JButton backButton = new JButton("Back");
	JLabel title;



    private int utilitiesWidth=150;
    private int heightOfSlide;
	//	GUI diceRoller;
//	GUI calculatorGUI;
//	GUI audioPlayer;
	
	/**
	 * Create a simple panel and add the JButtons for the utilities
	 */
	public UtilitiesPanel(int utilitiesWidth, int slideWidth, int slideHeight) {
		//Calls JPanel to create the UtilitiesPanel object.
		//Sets the layout to BoxLayout
	    
		super();
		setLayout(null);
		
		heightOfSlide = slideHeight;
		
		calculatorPanel = new CalculatorPanel(360, slideHeight - 20);
		dicePanel = new DicePanel(300, slideHeight - 20);
		standAloneMusicPlayer = new StandAloneMusicPlayer();
		
		standAloneMusicPlayerPanel = standAloneMusicPlayer.getFullControlPanel(360, slideHeight - 20);
		
		backButton.setBounds(0, slideHeight - 20, utilitiesWidth, 20);
		backButton.setActionCommand("back");
		
		
		multiPanel.add(backButton);
		//Set up background image
		BufferedImage backgroundImage;
		try{
			backgroundImage = ImageIO.read(new File("resources/buttons/UtilitiesBackground.png"));
			Image scaledBackground = backgroundImage.getScaledInstance(utilitiesWidth,slideHeight,java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, utilitiesWidth, slideHeight);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		//setup title
		BufferedImage titleImage;
		try{
			titleImage = ImageIO.read(new File("resources/buttons/UtilitiesLabel.png"));
			Image scaledTitle = titleImage.getScaledInstance(80,30,java.awt.Image.SCALE_SMOOTH);
			title = new JLabel(new ImageIcon(scaledTitle));
			title.setBounds((utilitiesWidth/2)-40, 10, 80, 30);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		multiPanel.setLayout(null);
        multiPanel.setBounds(0,0,utilitiesWidth,slideHeight);
        multiPanel.setBackground(Color.BLACK);
		this.add(multiPanel);
		
		//Adds JButtons for diceRoller and calculator
		buttonList.add(addButtons("diceRollerLaunch", "Opens the dice roller application", (utilitiesWidth/2)-50, (slideHeight/4)-50, "DiceButton.png"));
		
		buttonList.add(addButtons("calculatorLaunch", "Opens the modifier calculator application", (utilitiesWidth/2)-50, (slideHeight/2)-50, "CalculatorButton.png"));
		
		buttonList.add(addButtons("audioPlayerLaunch", "Opens the audio player application", (utilitiesWidth/2)-50, (3*slideHeight/4)-50, "AudioButton.png"));

	    
		//calculatorPanel.setBounds(0,0,utilitiesWidth,700);
		//dicePanel.setBounds(0,0,utilitiesWidth,700);
		//standAloneMusicPlayerPanel.setBounds(0,0,utilitiesWidth,700);
		multiPanel.add(calculatorPanel);
		multiPanel.add(standAloneMusicPlayerPanel);
		multiPanel.add(dicePanel);
		setPanelVisible("none");
		multiPanel.setOpaque(false);
		this.setBounds(slideWidth-utilitiesWidth, 0, utilitiesWidth, slideHeight);

		
		
	}
	
	
	
	/**
	 * Method for creating JButtons with specified paramiters
	 * @return 
	 */
	public JButton addButtons(String actionCommand, String toolTip,
						   int x_coord, int y_coord, String image) {
		
		JButton button = new JButton();
		button.setVerticalTextPosition(AbstractButton.CENTER);
		button.setHorizontalTextPosition(AbstractButton.CENTER);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBounds(x_coord, y_coord, 100, 100);
		button.setActionCommand(actionCommand);
		//button.addActionListener(this);
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
		this.add(title);
		this.add(background);
		return button;
	}
	
	public ArrayList<JButton> getButtons() {
	    return buttonList;
	}
	
	
	
	/**
	 * When a utility button is released instantiate the utility
	 * if it doesn't already exist
	 */
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if ("diceRollerLaunch".equals(e.getActionCommand())){
//		    this.setSize(dicePanel.getWidth(), dicePanel.getHeight());
//			setPanelVisible("dicePanel");
//			this.repaint();
//		}
//		else if ("calculatorLaunch".equals(e.getActionCommand())) {
//		    this.setSize(calculatorPanel.getWidth(), calculatorPanel.getHeight());
//		    setPanelVisible("calculatorPanel"); 
//	        this.repaint();
//		}
//	    else if ("audioPlayerLaunch".equals(e.getActionCommand())) {
//	        this.setSize(standAloneMusicPlayerPanel.getWidth(), standAloneMusicPlayerPanel.getHeight());
//	        setPanelVisible("standAloneMusicPlayerPanel");
//	        this.repaint();
//        }
//	   
//	}
	
	public void setUtilityVisible(JButton button) {
	    String caseString = button.getActionCommand();
	    switch(caseString) {
	    case "diceRollerLaunch":
	        setPanelVisible("dicePanel");
	    break;
	    case "calculatorLaunch":
	        setPanelVisible("calculatorPanel");
	    break;
	    case "audioPlayerLaunch":
	        setPanelVisible("standAloneMusicPlayerPanel");
	    break;
	    case "back":
	        setPanelVisible("none");
	    default: break;   
	    }
	}
	

	private void setPanelVisible(String panel) {
	    switch(panel) {
	    case "dicePanel":
            dicePanel.setVisible(true);
            calculatorPanel.setVisible(false);
            standAloneMusicPlayerPanel.setVisible(false);
            setWidth(dicePanel.getWidth());
            multiPanel.setVisible(true);
	    break;    
	    case "calculatorPanel":
            dicePanel.setVisible(false);
            calculatorPanel.setVisible(true);
            standAloneMusicPlayerPanel.setVisible(false);
            setWidth(calculatorPanel.getWidth());
            multiPanel.setVisible(true);
	    break;
	    case "standAloneMusicPlayerPanel":
            dicePanel.setVisible(false);
            calculatorPanel.setVisible(false);
            standAloneMusicPlayerPanel.setVisible(true);  
            setWidth(standAloneMusicPlayerPanel.getWidth());
            System.out.println(standAloneMusicPlayerPanel.getWidth());
            multiPanel.setVisible(true);
	    break;
	    case "none":
            dicePanel.setVisible(false);
            calculatorPanel.setVisible(false);
            standAloneMusicPlayerPanel.setVisible(false); 
            multiPanel.setVisible(false);
            setWidth(150);
	    default: break;
	    }
	    multiPanel.repaint();
	}

    private void setWidth(int width) {
        this.utilitiesWidth = width;
        multiPanel.setBounds(0,0,utilitiesWidth,700);
        backButton.setBounds(
                0, 
                heightOfSlide-20, 
                width,
                20);
        
    }
    
    public int getWidth() {
        return utilitiesWidth;
    }



    public JButton getBackButton() {
        return backButton;
    }
	
}
