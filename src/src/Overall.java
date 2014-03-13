/**
 * 
 */
package src;

import gUIModule.GUI;
import gUIModule.Player;
import imageModule.ImagePainter;

import java.util.ArrayList;

import slideModule.Presentation;
import slideModule.Slide;
import slideModule.XMLParser;

/**
 * @author Robert
 *
 */
public class Overall {
	
	private static final long serialVersionUID = 1L;
	
	private Presentation slideList;

	/**
	 * 
	 */
	public Overall(String fileName) {
		XMLParser parser = new XMLParser(fileName);
		slideList = parser.getSlides();
		Player presPlayer = new Player(slideList);
		presPlayer.play();
	}
	
	public static void main(String[] args) {
		//Currently setup to test UtilitiesPanel
		
		//GUI utilities = new GUI("utilitiesSelectionPanel");  // can open all utilities from this instance
        //GUI diceRoller = new GUI("diceRollerPanel");
	    //GUI calculator = new GUI("calculatorPanel");
		
		
		// A simple test for the text module
		textModule.Scribe.showAndDisplayGUI();
		
		
		// A simple test for the image module
		//ImagePainter.ProduceImage("/images/logo.jpg", "company logo");

//>>>>>>> branch 'master' of https://github.com/D6Digital/SWENGFrame.git
	}
	
	

}
