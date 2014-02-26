package slideModule;

import java.util.ArrayList;

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
 * @param start
 * @param end
 * @param layer
 * @param file
 * @param text
 * @param colour
 * @param size
 */
public Text(int x_coord, int y_coord, int start, int end, int layer,
		String file, ArrayList<TextContent> text, String colour, int size) {
	super(x_coord, y_coord, start, end, layer, file);
	this.text = text;
	this.colour = colour;
	this.size = size;
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


/**
 * @param index
 * @return the TextContent object at a given index
 * @see java.util.ArrayList#get(int)
 */
public TextContent get(int index) {
	return text.get(index);
}

/**
 * @return the size of the text ArrayList
 * @see java.util.ArrayList#size()
 */
public int size() {
	return text.size();
}

/**
 * @return the colour of the text
 */
public String getColour() {
	return colour;
}

/**
 * @param colour sets the colour of the text
 */
public void setColour(String colour) {
	this.colour = colour;
}

/**
 * @return the size of the text
 */
public int getSize() {
	return size;
}

/**
 * @param size the size of the text
 */
public void setSize(int size) {
	this.size = size;
}

}
