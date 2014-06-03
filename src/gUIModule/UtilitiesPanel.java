package gUIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import main.Overall;
import musicPlayerModule.StandAloneMusicPlayer;


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
	JButton diceButton = new JButton();
	JButton calculatorButton = new JButton();
	JButton audioButton = new JButton();
	BufferedImage backgroundImage;
	Image scaledBackground;

    private int utilitiesWidth=150;
    private int heightOfSlide;
    private int xOffset = 0;
    
    
    private MouseAdapter genericMouseMotionListener;

	//	GUI diceRoller;
//	GUI calculatorGUI;
//	GUI audioPlayer;
	
	/**
	 * Create a simple panel and add the JButtons for the utilities
	 * @param genericListener 
	 */
	public UtilitiesPanel(int utilitiesWidth, int slideWidth, int slideHeight, MouseAdapter genericListener) {
		//Calls JPanel to create the UtilitiesPanel object.
		//Sets the layout to BoxLayout
	    
		super();
		setLayout(null);
		this.setOpaque(true);
		this.genericMouseMotionListener = genericListener;
		
		heightOfSlide = slideHeight;
		xOffset = slideWidth-utilitiesWidth;
		
		calculatorPanel = new CalculatorPanel(360, slideHeight - 20,genericListener);
		dicePanel = new DicePanel(300, slideHeight - 20,genericListener);
		standAloneMusicPlayer = new StandAloneMusicPlayer(genericListener);
		
		standAloneMusicPlayerPanel = standAloneMusicPlayer.getFullControlPanel(360, 540 - 20);
		
		backButton.setBounds(10, (int) (slideHeight*0.975), (int) (utilitiesWidth*0.925),  (int) (slideHeight*0.025));
		backButton.setActionCommand("back");
		
		
		multiPanel.add(backButton);
		//Set up background image
		background = new JLabel();
		try{
			backgroundImage = ImageIO.read(new File("resources/buttons/UtilitiesBackground.png"));
			scaledBackground = backgroundImage.getScaledInstance(utilitiesWidth,slideHeight,java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, utilitiesWidth, slideHeight);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		//setup title
		BufferedImage titleImage;
		title = new JLabel();
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
		buttonList.add(addButtons(diceButton,"diceRollerLaunch", "Opens the dice roller application", (utilitiesWidth/2)-50, (slideHeight/4)-50, "DiceButton.png"));
		
		buttonList.add(addButtons(calculatorButton,"calculatorLaunch", "Opens the modifier calculator application", (utilitiesWidth/2)-50, (slideHeight/2)-50, "CalculatorButton.png"));
		
		buttonList.add(addButtons(audioButton,"audioPlayerLaunch", "Opens the audio player application", (utilitiesWidth/2)-50, (3*slideHeight/4)-50, "AudioButton.png"));

	    
		//calculatorPanel.setBounds(0,0,utilitiesWidth,700);
		//dicePanel.setBounds(0,0,utilitiesWidth,700);
		//standAloneMusicPlayerPanel.setBounds(0,0,utilitiesWidth,700);
		multiPanel.add(calculatorPanel);
		multiPanel.add(standAloneMusicPlayerPanel);
		multiPanel.add(dicePanel);
		setPanelVisible("none");
		multiPanel.setOpaque(false);
		this.setBounds(slideWidth-utilitiesWidth, 0, utilitiesWidth, slideHeight);

		diceButton.addMouseMotionListener(genericMouseMotionListener);
		diceButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				BufferedImage buttonImage;
				try{
					buttonImage = ImageIO.read(new File("resources/buttons/DiceButton.png"));
					Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
					diceButton.setIcon(new ImageIcon(scaledButton));
				}catch (IOException ex){
					
				}
				diceButton.repaint();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				BufferedImage buttonImage;
				try{
					buttonImage = ImageIO.read(new File("resources/buttons/DiceButtonHover.png"));
					Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
					diceButton.setIcon(new ImageIcon(scaledButton));
				}catch (IOException ex){
					
				}
				diceButton.repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		calculatorButton.addMouseMotionListener(genericMouseMotionListener);
		calculatorButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				BufferedImage buttonImage;
				try{
					buttonImage = ImageIO.read(new File("resources/buttons/CalculatorButton.png"));
					Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
					calculatorButton.setIcon(new ImageIcon(scaledButton));
				}catch (IOException ex){
					
				}
				calculatorButton.repaint();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				BufferedImage buttonImage;
				try{
					buttonImage = ImageIO.read(new File("resources/buttons/CalculatorButtonHover.png"));
					Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
					calculatorButton.setIcon(new ImageIcon(scaledButton));
				}catch (IOException ex){
					
				}
				calculatorButton.repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		audioButton.addMouseMotionListener(genericMouseMotionListener);
		audioButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				BufferedImage buttonImage;
				try{
					buttonImage = ImageIO.read(new File("resources/buttons/AudioButton.png"));
					Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
					audioButton.setIcon(new ImageIcon(scaledButton));
				}catch (IOException ex){
					
				}
				audioButton.repaint();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				BufferedImage buttonImage;
				try{
					buttonImage = ImageIO.read(new File("resources/buttons/AudioButtonHover.png"));
					Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
					audioButton.setIcon(new ImageIcon(scaledButton));
				}catch (IOException ex){
					
				}
				audioButton.repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	/**
	 * Method for creating JButtons with specified paramiters
	 * @return 
	 */
	public JButton addButtons(JButton button, String actionCommand, String toolTip,
						   int x_coord, int y_coord, String image) {
		
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
		add(title);
		add(background);
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
	    	dicePanel.setSize(dicePanel.getWidth() , this.heightOfSlide);
            dicePanel.setVisible(true);
            calculatorPanel.setVisible(false);
            standAloneMusicPlayerPanel.setVisible(false);
            this.xOffset = this.xOffset  + (this.getWidth() - dicePanel.getWidth());
            setWidth(dicePanel.getWidth());
            this.setBounds(this.xOffset - (300 - utilitiesWidth), 0, 300, this.heightOfSlide);
            multiPanel.setVisible(true);
	    break;    
	    case "calculatorPanel":
	    	calculatorPanel.setSize(calculatorPanel.getWidth() , this.heightOfSlide);
            dicePanel.setVisible(false);
            calculatorPanel.setVisible(true);
            standAloneMusicPlayerPanel.setVisible(false);
            this.xOffset = this.xOffset  + (this.getWidth() - calculatorPanel.getWidth());
            setWidth(calculatorPanel.getWidth());
            this.setBounds(this.xOffset - (350 - utilitiesWidth), 0, 350, this.heightOfSlide);
            multiPanel.setVisible(true);
	    break;
	    case "standAloneMusicPlayerPanel":
	    	standAloneMusicPlayerPanel.setSize(standAloneMusicPlayerPanel.getWidth() , this.heightOfSlide);
            dicePanel.setVisible(false);
            calculatorPanel.setVisible(false);
            standAloneMusicPlayerPanel.setVisible(true);  
            this.xOffset = this.xOffset  + (this.getWidth() - standAloneMusicPlayerPanel.getWidth());
            setWidth(standAloneMusicPlayerPanel.getWidth());
            this.setBounds(this.xOffset - (360 - utilitiesWidth), 0, 360, this.heightOfSlide);
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
        this.setBounds(this.xOffset, 0, utilitiesWidth, this.heightOfSlide);
        multiPanel.setBounds(0,0,utilitiesWidth,this.heightOfSlide);
        backButton.setBounds(10, (int) (heightOfSlide*0.975), (int) (utilitiesWidth*0.925),  (int) (heightOfSlide*0.025));
        
    }
    
    public int getWidth() {
        return utilitiesWidth;
    }

 

    public JButton getBackButton() {
        return backButton;
    }



	public void setDimensions(int utilitiesOffset, int slideHeight) {
		this.heightOfSlide = slideHeight;
		this.xOffset = utilitiesOffset;
		if(scaledBackground != null){
			scaledBackground = backgroundImage.getScaledInstance(utilitiesWidth,slideHeight,java.awt.Image.SCALE_SMOOTH);
			background.setIcon(new ImageIcon(scaledBackground));
		}
		background.setBounds(0, 0, utilitiesWidth, slideHeight);
		diceButton.setBounds((utilitiesWidth/2)-50, (slideHeight/4)-50, 100, 100);
		calculatorButton.setBounds((utilitiesWidth/2)-50, (slideHeight/2)-50, 100, 100);
		audioButton.setBounds((utilitiesWidth/2)-50, (3*slideHeight/4)-50, 100, 100);
		multiPanel.setBounds(0,0,utilitiesWidth,slideHeight);
		backButton.setBounds(10, (int) (slideHeight*0.975), (int) (utilitiesWidth*0.925),  (int) (slideHeight*0.025));
		title.setBounds((utilitiesWidth/2)-40, 10, 80, 30);
		
	}
	
}
