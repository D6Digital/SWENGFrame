package gUIModule;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		int nextSlideID = currentVisibleSlideID + 1;
		Slide nextSlide = slideList.get(nextSlideID);
		slidePanel.refreshSlide(nextSlide);
		setCurrentSlideID(nextSlideID);
		previousSlideButton.setBorderPainted(true);
		System.out.println("Next slide = " + nextSlide.getLastSlide());
		if(nextSlide.getLastSlide()==true){
			nextSlideButton.setBorderPainted(false);
		}
		return null;
	}
	
	public Slide showPreviousSlide() {
		nextSlideButton.setBorderPainted(true);
		if (currentVisibleSlideID ==1){
			previousSlideButton.setBorderPainted(false);
		}
		
		int previousSlideID = currentVisibleSlideID - 1;
		Slide previousSlide = slideList.get(previousSlideID);
		slidePanel.refreshSlide(previousSlide);
		setCurrentSlideID(previousSlideID);
			
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
				setSize(slideList.getWidth()+borderSize+borderSize+utilitiesWidth, 720);
				setVisible(true);			
				bookPane = getContentPane();
				
				System.out.println("size="+(slideList.getWidth()+borderSize+borderSize)+","+(slideList.getHeight()+borderSize+borderSize));
				//set up slide
				bookPane.setBounds(borderSize, borderSize, slideList.getWidth()+100, slideList.getHeight()+borderSize+borderSize);
					
				slidePanel.loadPresentation(slideList);
				slidePanel.setupSlide(slideList.get(0));
				currentVisibleSlideID = 0;
				previousSlideButton.setBorderPainted(false);
				slidePanel.setBounds(borderSize, borderSize, slideList.getWidth(), slideList.getHeight());
				bookPane.add(slidePanel);	

				//set up utilities
				UtilitiesPanel utilities = new UtilitiesPanel();
				utilities.setBounds(slideList.getWidth()+borderSize, borderSize, utilitiesWidth, slideList.getHeight());
				bookPane.add(utilities);
				
				//set up buttons
				previousSlideButton.setBounds(0, slideList.getHeight()+borderSize, (slideList.getWidth()+utilitiesWidth+borderSize+borderSize)/2, borderSize);
				bookPane.add(previousSlideButton);
				nextSlideButton.setBounds((slideList.getWidth()+utilitiesWidth+borderSize+borderSize)/2, slideList.getHeight()+borderSize,(slideList.getWidth()+utilitiesWidth+borderSize+borderSize)/2, borderSize);
				bookPane.add(nextSlideButton);
				
				
				//borders
				leftBorder.setBounds(0,borderSize,borderSize,slideList.getHeight());
				bookPane.add(leftBorder);
				rightBorder.setBounds(slideList.getWidth()+utilitiesWidth+borderSize,borderSize,borderSize,slideList.getHeight());
				bookPane.add(rightBorder);
				topBorder.setBounds(0,0,slideList.getWidth()+utilitiesWidth+borderSize+borderSize,borderSize);
				bookPane.add(topBorder);
				
				
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
	}


}