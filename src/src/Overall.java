/**
 * 
 */
package src;

import gUIModule.GUI;
import gUIModule.Player;

import java.util.ArrayList;

import slideModule.Slide;
import slideModule.XMLParser;

/**
 * @author Robert
 *
 */
public class Overall {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Slide> slideList;

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
		String panelType = "utilitiesSelectionPanel";
		String windowName = "Utilities";
		int width = 200;
		int height = 225;
		
		GUI utilities = new GUI(panelType, windowName, width, height);
	}

}
