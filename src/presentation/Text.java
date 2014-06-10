package presentation;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Robert Mills
 * @author Andrew Walter
 *
 */
public class Text extends SlideObject {
	ArrayList<TextContent> text = new ArrayList<TextContent>();
	String colour;
	int size;
	int xEnd;
	int yEnd;

	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param duration
	 * @param layer
	 * @param file
	 * @param text
	 * @param colour
	 * @param size
	 * @param branch
	 */
	public Text(int x_coord, int y_coord, int start, int duration, int layer,
			String file, ArrayList<TextContent> text, String colour, int size, int branch) {
		super(x_coord, y_coord, start, duration, layer, file, branch);
		this.text = text;
		this.colour = colour;
		this.size = size;
	}


	public Text() {
		// TODO Auto-generated constructor stub
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
	 * @param textCont adds an individual instance of TextContent to the array
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
	 * @param converts hex colours to RGB and returns a type Color
	 */
	public Color getColourObject() {
		int[] RGB = {0, 0, 0};
		Color colourReturn;
		if (colour.charAt(0) == '#'){
			String colourHex = colour.substring(1,7);
			RGB[0] = Integer.parseInt(colourHex.substring(0,2), 16);
			RGB[1] = Integer.parseInt(colourHex.substring(2,4), 16);
			RGB[2] = Integer.parseInt(colourHex.substring(4,6), 16);
		}
		colourReturn = new Color(RGB[0], RGB[1], RGB[2]);
		return colourReturn;
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



	/**
	 * @return the point where the text should stop and wrap
	 */
	public int getXend() {
		return xEnd;
	}


	/**
	 * @param xend the point where the text should stop and wrap
	 */
	public void setXend(int xend) {
		this.xEnd = xend;
	}


	/**
	 * @return the point when the text should cut off
	 */
	public int getYend() {
		return yEnd;
	}


	/**
	 * @param yend the point when the text should cut off
	 */
	public void setYend(int yend) {
		this.yEnd = yend;
	}

}
