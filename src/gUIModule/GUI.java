package gUIModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import presentation.Presentation;
import presentation.Slide;
import presentation.XMLParser;
import src.Overall;
import gUIModule.UtilitiesPanel;
import gUIModule.DicePanel;
import gUIModule.CalculatorPanel;

/**
 * 
 * @author Andrew Walter
 *
 */
public class GUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Container selectionPane;
	Container menuPane;
	Container bookPane;
	Container videoPane;
	Container audioPane;
	Container utilitiesPane;
	Container dicePane;
	Container calculatorPane;
	static Presentation slideList;
	private static Integer currentVisibleSlideID;
	private SlidePanel slidePanel = new SlidePanel();
	JPanel leftBorder = new JPanel();
	JPanel rightBorder = new JPanel();
	JPanel topBorder = new JPanel();
	JButton nextSlideButton = new JButton();
	JButton previousSlideButton = new JButton();
	int borderSize = 20;
	int utilitiesWidth = 200;
	UtilitiesPanel utilities = new UtilitiesPanel();
	
	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @return 
	 */
	private void setCurrentSlideID(Integer newSlideID) {
		this.currentVisibleSlideID = newSlideID;
	}
	
	private Integer getCurrentSlideID() {
		return currentVisibleSlideID;
	} 
	
	public Slide showNextSlide() {
		if(slideList.get(currentVisibleSlideID).getLastSlide()==false){
		int nextSlideID = currentVisibleSlideID + 1;
		Slide nextSlide = slideList.get(nextSlideID);
		slidePanel.refreshSlide(nextSlide);
		setCurrentSlideID(nextSlideID);
		previousSlideButton.setBorderPainted(true);
		System.out.println("Next slide = " + nextSlide.getLastSlide());
		if(nextSlide.getLastSlide()==true){
			nextSlideButton.setBorderPainted(false);

		}
		}
		return null;
	}
	
	public Slide showPreviousSlide() {
		if(currentVisibleSlideID==1){
		nextSlideButton.setBorderPainted(true);
		if (currentVisibleSlideID ==1){
			previousSlideButton.setBorderPainted(false);
		}
		
		int previousSlideID = currentVisibleSlideID - 1;
		Slide previousSlide = slideList.get(previousSlideID);
		slidePanel.refreshSlide(previousSlide);
		setCurrentSlideID(previousSlideID);
		}	
		return null;
	}

	public GUI(String panelType) {
		switch (panelType) {
			case "bookSelectionPanel":
				setTitle("Grimoire");
				setSize(1000, 500);
				setVisible(true);
				
				selectionPane = getContentPane();
				selectionPane.setLayout(new BorderLayout());
				//BookPanel();
				break;
			case "mainMenuPanel":
				setTitle("Grimoire");
				setSize(1000, 500);
				setVisible(true);
				
				menuPane = getContentPane();
				menuPane.setLayout(new BorderLayout());
				//MenuPanel();
				break;
			case "bookMainPanel":
				//get slides
				XMLParser parser = new XMLParser("src/BasicExample.xml");	
				slideList = parser.getSlides();
				setLayout(null);
				
				//set up jframe
				setTitle("Grimoire");
				setSize(slideList.getWidth()+borderSize+borderSize+borderSize, 720);
				setVisible(true);			
				bookPane = getContentPane();
				JLayeredPane layers = new JLayeredPane();
				layers.setLayout(null);
				
				layers.setBounds(0,0,slideList.getWidth()+borderSize+borderSize+borderSize, 720);
				
				System.out.println("size="+(slideList.getWidth()+borderSize+borderSize)+","+(slideList.getHeight()+borderSize+borderSize));
				//set up slide
				bookPane.setBounds(borderSize, borderSize, slideList.getWidth()+100, slideList.getHeight()+borderSize+borderSize);
					
				slidePanel.loadPresentation(slideList);
				slidePanel.setupSlide(slideList.get(0));
				currentVisibleSlideID = 0;
				slidePanel.setBounds(borderSize, borderSize, slideList.getWidth(), slideList.getHeight());
				layers.add(slidePanel,1);	

				//set up utilities
				utilities.setBounds(slideList.getWidth()+borderSize-utilitiesWidth, borderSize, utilitiesWidth, slideList.getHeight());
				utilities.setBackground(Color.BLACK);
				utilities.setVisible(false);
				layers.add(utilities,0);
				
				//set up buttons
				previousSlideButton.setBounds(0, slideList.getHeight()+borderSize, (slideList.getWidth()+borderSize+borderSize)/2, borderSize);
				layers.add(previousSlideButton,1);
				nextSlideButton.setBounds((slideList.getWidth()+borderSize+borderSize)/2, slideList.getHeight()+borderSize,(slideList.getWidth()+utilitiesWidth+borderSize+borderSize)/2, borderSize);
				layers.add(nextSlideButton,1);
				previousSlideButton.setBorderPainted(false);				
				//previousSlideButton.setEnabled(false);
				
				//borders
				leftBorder.setBounds(0,borderSize,borderSize,slideList.getHeight());
				layers.add(leftBorder,0);
				leftBorder.setBackground(Color.GREEN);
				rightBorder.setBounds(slideList.getWidth()+borderSize,borderSize,borderSize,slideList.getHeight());
				layers.add(rightBorder,0);
				rightBorder.setBackground(Color.GREEN);
				topBorder.setBounds(0,0,slideList.getWidth()+borderSize+borderSize,borderSize);
				layers.add(topBorder,1);
				topBorder.setBackground(Color.GREEN);
				
				bookPane.add(layers);
				bookPane.setVisible(true);
						
				break;
			case "videoDisplayPanel":
				setTitle("Video Guide");
				setSize(1000, 500);
				setVisible(true);
				
				videoPane = getContentPane();
				videoPane.setLayout(new BorderLayout());
				//VideoPanel();
				break;
			case "audioMenuPanel":
				setTitle("Music");
				setSize(200, 300);
				setVisible(true);
				
				audioPane = getContentPane();
				audioPane.setLayout(new BorderLayout());
				//AudioPanel();
				break;
			case "utilitiesSelectionPanel":
				setTitle("Utilities");
				setSize(200, 225);
				setVisible(true);
				
				utilitiesPane = getContentPane();
				utilitiesPane.setLayout(new BorderLayout());
				
				UtilitiesPanel utilitiesSelectionPanel = new UtilitiesPanel();
				utilitiesPane.add(utilitiesSelectionPanel, BorderLayout.CENTER);
				break;
			case "diceRollerPanel":
				setTitle("Dice Roller");
				setSize(405, 640);
				setVisible(true);
				
				dicePane = getContentPane();
				dicePane.setLayout(new BorderLayout());
				
				DicePanel diceRollerPanel = new DicePanel();
				dicePane.add(diceRollerPanel, BorderLayout.CENTER);
				break;
			case "calculatorPanel":
				setTitle("Combat Modifier Calculator");
				setSize(400, 600);
				setVisible(true);
				
				calculatorPane = getContentPane();
				calculatorPane.setLayout(new BorderLayout());
				
				CalculatorPanel calculatorPanel = new CalculatorPanel();
				calculatorPane.add(calculatorPanel, BorderLayout.CENTER);
				break;
			default:                     
				//???DefaultPanel()???
				break;
		};
		nextSlideButton.addActionListener(
				 new ActionListener() {
		                
		                @Override
		                public void actionPerformed(ActionEvent arg0) {
		                   
		                   showNextSlide();
		                }
		            });
		
		 previousSlideButton.addActionListener(
				 new ActionListener() {
		                
		                @Override
		                public void actionPerformed(ActionEvent arg0) {
		                   
		                   showPreviousSlide();
		                }
		            });
	
	 rightBorder.addMouseListener(new java.awt.event.MouseAdapter(){
		 @Override
		 public void mouseEntered(MouseEvent e){
			 utilities.setVisible(true);
			 System.out.println("Mouse detected in right border");
		 }
		 
	 
	 });
	 utilities.addMouseListener(new java.awt.event.MouseAdapter(){
		 @Override
		 public void mouseExited(MouseEvent e){
			 utilities.setVisible(false);
			 System.out.println("Mouse detected in right border");
		 }
		 
	 
	 });


	}
}