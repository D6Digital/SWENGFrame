package src;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Robert Mills
 *
 */
public class Text extends SlideObject {
ArrayList<TextContent> text;

/**
 * @param x_coord
 * @param y_coord
 * @param file the file containing the ttf file
 * @param text
 */
public Text(int x_coord, int y_coord, String file) {
	super(x_coord, y_coord, file);
	this.text = new ArrayList<TextContent>(0);
}


}
