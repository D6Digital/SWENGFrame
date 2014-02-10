/**
 * 
 */
package src;

import java.util.ArrayList;

/**
 * @author Robert
 *
 */
public class Overall {
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

}
