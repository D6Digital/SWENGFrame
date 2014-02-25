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
String colour;
int size;


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

public ArrayList<TextContent> getText() {
	return text;
}

public void setText(ArrayList<TextContent> text) {
	this.text = text;
}

public void add(TextContent textCont) {
	text.add(textCont);
}
public void resetText(){
	text = null;
	text = new ArrayList<TextContent>(0);
}

}
