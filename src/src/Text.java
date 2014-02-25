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

/**
 * @return the list of text contents
 */
public ArrayList<TextContent> getText() {
	return text;
}

/**
 * @param text adds an array of textContent
 */
public void setText(ArrayList<TextContent> text) {
	this.text = text;
}

/**
 * @param textCont adds an individual instance of TxtContent to the array
 */
public void add(TextContent textCont) {
	text.add(textCont);
}
/**
 * resets the text Array
 */
public void resetText(){
	text = null;
	text = new ArrayList<TextContent>(0);
}

public String getColour() {
	return colour;
}

public void setColour(String colour) {
	this.colour = colour;
}

public int getSize() {
	return size;
}

public void setSize(int size) {
	this.size = size;
}

}
