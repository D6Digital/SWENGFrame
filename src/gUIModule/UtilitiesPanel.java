package gUIModule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
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

import musicPlayerModule.StandAloneMusicPlayer;

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
	JLabel background, title; 
	ArrayList<JButton> buttonList = new ArrayList<>();
	JButton backButton = new JButton();
	JButton diceButton = new JButton();
	JButton calculatorButton = new JButton();
	JButton audioButton = new JButton();
	BufferedImage backgroundImage;
	Image scaledBackground;

    private int utilitiesWidth=150;
    private int heightOfSlide;
    private int xOffset = 0;
    private int backButtonHeight = 30;
    
    private MouseAdapter genericMouseMotionListener;
    
	private BufferedImage backButtonImage;

	private Image scaledBackButtonImage;
	int theBookLayout = 1;

	/**
	 * Create a simple panel and add the JButtons for the utilities
	 * @param genericListener 
	 * @param bookLayout 
	 */
	public UtilitiesPanel(int utilitiesWidth, int slideWidth, int slideHeight, MouseAdapter genericListener, int bookLayout) {
		//Calls JPanel to create the UtilitiesPanel object.
		//Sets the layout to BoxLayout
	    
		super();
		setLayout(null);
		this.setOpaque(true);
		this.genericMouseMotionListener = genericListener;
		this.theBookLayout = bookLayout;
		
		heightOfSlide = slideHeight;
		xOffset = slideWidth-utilitiesWidth;
		
		calculatorPanel = new CalculatorPanel(360, slideHeight - backButtonHeight,genericListener);
		dicePanel = new DicePanel(300, slideHeight - backButtonHeight,genericListener);
		standAloneMusicPlayer = new StandAloneMusicPlayer(genericListener);
		
		standAloneMusicPlayerPanel = standAloneMusicPlayer.getFullControlPanel(360, 540 - backButtonHeight);

		//back button
		setupBackbutton(utilitiesWidth, slideHeight);
		
		//Set up background image
		setupBackground(utilitiesWidth, slideHeight, bookLayout);
		//setup title
		setupTitleImage(utilitiesWidth);
		
		handleMultiPanel(utilitiesWidth, slideHeight);
		
		//Adds JButtons for diceRoller and calculator
		addToButtonList(utilitiesWidth, slideHeight);

		popAndShowMultiPanel();
		this.setBounds(slideWidth-utilitiesWidth, 0, utilitiesWidth, slideHeight);

		diceButtonListeners();
		calculatorButtonListeners();
		audioButtonListeners();
	}



	/**
	 * adds the listeners to the audio buttons
	 */
	private void audioButtonListeners() {
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
					if(theBookLayout == 2){
						buttonImage = ImageIO.read(new File("resources/buttons2/AudioButton.png"));
					}
					else
					{
						buttonImage = ImageIO.read(new File("resources/buttons/AudioButton.png"));
					}
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
					if(theBookLayout == 2){
						buttonImage = ImageIO.read(new File("resources/buttons2/AudioButtonHover.png"));
					}
					else
					{
						buttonImage = ImageIO.read(new File("resources/buttons/AudioButtonHover.png"));
					}
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
	 * adds the listeners to the calculator button
	 */
	private void calculatorButtonListeners() {
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
					if(theBookLayout == 2){
						buttonImage = ImageIO.read(new File("resources/buttons2/CalculatorButton.png"));
					}
					else
					{
						buttonImage = ImageIO.read(new File("resources/buttons/CalculatorButton.png"));
					}
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
					if(theBookLayout == 2){
						buttonImage = ImageIO.read(new File("resources/buttons2/CalculatorButtonHover.png"));
					}
					else
					{
						buttonImage = ImageIO.read(new File("resources/buttons/CalculatorButtonHover.png"));
					}
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
	}



	/**
	 * adds the listeners to the dice button
	 */
	private void diceButtonListeners() {
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
					if(theBookLayout == 2){
						buttonImage = ImageIO.read(new File("resources/buttons2/DiceButton.png"));
					}
					else
					{
						buttonImage = ImageIO.read(new File("resources/buttons/DiceButton.png"));
					}
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
					if(theBookLayout == 2){
						buttonImage = ImageIO.read(new File("resources/buttons2/DiceButtonHover.png"));
					}
					else
					{
						buttonImage = ImageIO.read(new File("resources/buttons/DiceButtonHover.png"));
					}
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
	}



	/**
	 * Add objects to multipanel and shows it
	 */
	private void popAndShowMultiPanel() {
		multiPanel.add(backButton);
		multiPanel.add(calculatorPanel);
		multiPanel.add(standAloneMusicPlayerPanel);
		multiPanel.add(dicePanel);
		setPanelVisible("none");
		multiPanel.setOpaque(false);
	}

	/**
	 * adds buttons to button list
	 * @param utilitiesWidth the width of the utilities panel
	 * @param slideHeight the height of the slide
	 */
	private void addToButtonList(int utilitiesWidth, int slideHeight) {
		buttonList.add(addButtons(diceButton,"diceRollerLaunch", "Opens the dice roller application", (utilitiesWidth/2)-50, (slideHeight/4)-50, "DiceButton.png"));
		
		buttonList.add(addButtons(calculatorButton,"calculatorLaunch", "Opens the modifier calculator application", (utilitiesWidth/2)-50, (slideHeight/2)-50, "CalculatorButton.png"));
		
		buttonList.add(addButtons(audioButton,"audioPlayerLaunch", "Opens the audio player application", (utilitiesWidth/2)-50, (3*slideHeight/4)-50, "AudioButton.png"));
	}



	/**
	 * sets up multipanel
	 * @param utilitiesWidth the width of the utilities panel
	 * @param slideHeight the height of the slide
	 */
	private void handleMultiPanel(int utilitiesWidth, int slideHeight) {
		multiPanel.setLayout(null);
        multiPanel.setBounds(0,0,utilitiesWidth,slideHeight);
        multiPanel.setBackground(Color.BLACK);
		this.add(multiPanel);
	}



	/**
	 * sets up the title image
	 * @param utilitiesWidth the width of teh utilities panel
	 */
	private void setupTitleImage(int utilitiesWidth) {
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
	}



	/**
	 * sets up the background
	 * @param utilitiesWidth the width of the utilities panel
	 * @param slideHeight the height of the slide
	 * @param bookLayout the layout of the book chosen
	 */
	private void setupBackground(int utilitiesWidth, int slideHeight,
			int bookLayout) {
		background = new JLabel();
		try{
			if(bookLayout == 2){
				backgroundImage = ImageIO.read(new File("resources/buttons2/UtilitiesBackground.png"));
			}
			else
			{
				backgroundImage = ImageIO.read(new File("resources/buttons/UtilitiesBackground.png"));
			}
			backgroundImage = ImageIO.read(new File("resources/buttons/UtilitiesBackground.png"));
			scaledBackground = backgroundImage.getScaledInstance(utilitiesWidth,slideHeight,java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, utilitiesWidth, slideHeight);
		}catch(IOException e2){
			e2.printStackTrace();
		}
	}



	/**
	 * sets up the back button
	 * @param utilitiesWidth the width of the utilities panel
	 * @param slideHeight the height of the slides
	 */
	private void setupBackbutton(int utilitiesWidth, int slideHeight) {
		backButton.setBounds(10, (int) (slideHeight*0.965), (int) (utilitiesWidth*0.925),  (int) (slideHeight*0.035));
		backButton.setActionCommand("back");
		try{
			backButtonImage = ImageIO.read(new File("resources/buttons/Back Button.png"));
			scaledBackButtonImage = backButtonImage.getScaledInstance((int) (utilitiesWidth*0.925),  (int) (slideHeight*0.035),java.awt.Image.SCALE_SMOOTH);
			backButton.setIcon(new ImageIcon(scaledBackButtonImage));
		}catch(IOException e2){
			e2.printStackTrace();
		}
	}
	
	
	
	/**
	 * Method for creating JButtons with specified parameters
	 * @return the created JButton
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
			if(theBookLayout == 2){
				buttonImage = ImageIO.read(new File("resources/buttons2/"+image));
			}
			else{
				buttonImage = ImageIO.read(new File("resources/buttons/"+image));
			}
			Image scaledButton = buttonImage.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){
			
		}
		add(button);
		add(title);
		add(background);
		return button;
	}
	
	/**
	 * @return the list of buttons
	 */
	public ArrayList<JButton> getButtons() {
	    return buttonList;
	}
	
	/**
	 * When a utility button is released instantiate the utility
	 * if it doesn't already exist
	 * @param button is the button that has triggered the method
	 */
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
	

	/**
	 * sets the given panel visible
	 * @param panel
	 */
	private void setPanelVisible(String panel) {
	    switch(panel) {
	    case "dicePanel":
	    	isDicePanel();
	    break;    
	    case "calculatorPanel":
	    	isCalculatorPanel();
	    break;
	    case "standAloneMusicPlayerPanel":
	    	isMusicPlayer();
	    break;
	    case "none":
            isNone();
	    default: break;
	    }
	    multiPanel.repaint();
	}



	/**
	 * when no utility is selected
	 */
	private void isNone() {
		dicePanel.setVisible(false);
		calculatorPanel.setVisible(false);
		standAloneMusicPlayerPanel.setVisible(false); 
		multiPanel.setVisible(false);
		setWidth(150);
	}



	/**
	 * when standalone music player is selected
	 */
	private void isMusicPlayer() {
		standAloneMusicPlayerPanel.setSize(standAloneMusicPlayerPanel.getWidth() , this.heightOfSlide);
		dicePanel.setVisible(false);
		calculatorPanel.setVisible(false);
		standAloneMusicPlayerPanel.setVisible(true);  
		this.xOffset = this.xOffset  + (this.getWidth() - standAloneMusicPlayerPanel.getWidth());
		setWidth(standAloneMusicPlayerPanel.getWidth());
		this.setBounds(this.xOffset - (360 - utilitiesWidth), 0, 360, this.heightOfSlide);
		System.out.println(standAloneMusicPlayerPanel.getWidth());
		multiPanel.setVisible(true);
	}

	/**
	 * when calculator panel is selected
	 */
	private void isCalculatorPanel() {
		calculatorPanel.setSize(calculatorPanel.getWidth() , this.heightOfSlide);
		dicePanel.setVisible(false);
		calculatorPanel.setVisible(true);
		standAloneMusicPlayerPanel.setVisible(false);
		this.xOffset = this.xOffset  + (this.getWidth() - calculatorPanel.getWidth());
		setWidth(calculatorPanel.getWidth());
		this.setBounds(this.xOffset - (350 - utilitiesWidth), 0, 350, this.heightOfSlide);
		multiPanel.setVisible(true);
	}



	/**
	 * when dice panel is selected
	 */
	private void isDicePanel() {
		dicePanel.setSize(dicePanel.getWidth() , this.heightOfSlide);
		dicePanel.setVisible(true);
		calculatorPanel.setVisible(false);
		standAloneMusicPlayerPanel.setVisible(false);
		this.xOffset = this.xOffset  + (this.getWidth() - dicePanel.getWidth());
		setWidth(dicePanel.getWidth());
		this.setBounds(this.xOffset - (300 - utilitiesWidth), 0, 300, this.heightOfSlide);
		multiPanel.setVisible(true);
	}

    /**
     * sets the width of the panels
     * @param width the width desired
     */
    private void setWidth(int width) {
        this.utilitiesWidth = width;
        this.setBounds(this.xOffset, 0, utilitiesWidth, this.heightOfSlide);
        multiPanel.setBounds(0,0,utilitiesWidth,this.heightOfSlide);
        backButton.setBounds(10, (int) (heightOfSlide*0.965), (int) (utilitiesWidth*0.925),  (int) (heightOfSlide*0.035));
        scaledBackButtonImage = backButtonImage.getScaledInstance((int) (utilitiesWidth*0.925),  (int) (heightOfSlide*0.035),java.awt.Image.SCALE_SMOOTH);
		backButton.setIcon(new ImageIcon(scaledBackButtonImage));
    }
    
    /**
     * Gets the with of the panel
     * @see javax.swing.JComponent#getWidth()
     */
    public int getWidth() {
        return utilitiesWidth;
    }

 

    /**
     * @return the back button
     */
    public JButton getBackButton() {
        return backButton;
    }


 
	/**
	 * sets the dimensions for the utilities
	 * @param utilitiesOffset the offset of the panel compared to the slide
	 * @param slideHeight the height of the slide
	 */
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
		scaledBackButtonImage = backButtonImage.getScaledInstance((int) (utilitiesWidth*0.925),  (int) (slideHeight*0.025),java.awt.Image.SCALE_SMOOTH);
		backButton.setIcon(new ImageIcon(scaledBackButtonImage));
		title.setBounds((utilitiesWidth/2)-40, 10, 80, 30);
		
	}



	/**
	 * stop playing music from the standalone music player
	 */
	public void stopPlaying() {
		standAloneMusicPlayer.killThread();
		
	}
	
}
