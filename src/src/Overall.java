/**
 * 
 */
package src;

import gUIModule.GUI;
import gUIModule.Player;
import imageModule.ImagePainter;

import java.util.ArrayList;

import presentation.Presentation;
import presentation.Slide;
import presentation.XMLParser;


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
		Overall ThisIsIt = new Overall("src/BasicExample.xml");
		System.out.println(ThisIsIt.slideList.getFont());
		
		
		
		// A simple test for the text module
		//textModule.Scribe.showAndDisplayGUI();
		
		
		// A simple test for the image module
		//ImagePainter.produceImage("/images/logo.jpg");

//>>>>>>> branch 'master' of https://github.com/D6Digital/SWENGFrame.git
	}
	
	

}
