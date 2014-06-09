package presentation;

/**
 * @author Robert Mills
 * @author Ruba Balanehru 
 */

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author rjm529
 *
 */
public class Presentation {
	private ArrayList<Slide> slideList = new ArrayList<Slide>(0);
	private String author, comment, version, backgroundColour, fontColour, font, lineColour, fillColour, title;
	int width, height, fontSize;

	/**
	 * @return the slideList the list of slides
	 */
	public ArrayList<Slide> getSlideList() {
		return slideList;
	}
	/**
	 * @param slideList the slideList to set
	 */
	public void setSlideList(ArrayList<Slide> slideList) {
		this.slideList = slideList;
	}
	/**
	 * @return the author get the author of the presentation
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author set the author of the presentation
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return Any comments on the presentation
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment set any comments
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the version of the presentation
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version set the version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the width the width of the presentation in pixels
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width of the presentation in pixels
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height of the presentation in pixels
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height of the presentation in pixels
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @param e the slide to add
	 * @return true if successful 
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(Slide e) {
		return slideList.add(e);
	}
	/**
	 * @param index the index of the slide to get
	 * @return the slide requested
	 * @see java.util.ArrayList#get(int)
	 */
	public Slide get(int index) {
		return slideList.get(index);
	}
	/**
	 * @return the number of slides in the presentation
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return slideList.size();
	}
	
	/**
	 * @param string the string corresponding to the default background colour
	 */
	public void setBackgroundColour(String string) {
		backgroundColour = string;
	}
	public String getBackgroundColour(){
		return backgroundColour;
	}
	/**
	 * @param string the default font for the presentations
	 */
	public void setFont(String string) {
		font = string;
	}
	/**
	 * @return deault font
	 */
	public String getFont() {
		return font;
	}
	/**
	 * @param integer default font size in pt
	 */
	public void setFontSize(int integer) {
		fontSize = integer;
	}
	/**
	 * @return default font size in pt
	 */
	public int getFontSize() {
		return fontSize;
	}
	/**
	 * @param string default font colour
	 */
	public void setFontColour(String string) {
		fontColour = string;
	}
	/**
	 * @return default font colour
	 */
	public String getFontColour() {
		return fontColour;
	}
	
	/**
	 * @param string default line colour
	 */
	public void setLineColour(String string) {
		lineColour = string;
	}
	/**
	 * @return default line colour
	 */
	public String getLineColour(){
		return lineColour;
	}
	/**
	 * @param string default fill colour
	 */
	public void setFillColour(String string) {
		fillColour = string;
	}
	/**
	 * @return default fill colour
	 */
	public String getFillColour(){
		return fillColour;
	}
	
	/**
	 * @param converts hex colours to RGB and returns a type Color
	 */
	public Color getBackgroundColourObject() {
		int[] RGB = {0, 0, 0};
		Color colourReturn;
		if (backgroundColour.charAt(0) == '#'){
			String colourHex = backgroundColour.substring(1,7);
			RGB[0] = Integer.parseInt(colourHex.substring(0,2), 16);
			RGB[1] = Integer.parseInt(colourHex.substring(2,4), 16);
			RGB[2] = Integer.parseInt(colourHex.substring(4,6), 16);
		}
		colourReturn = new Color(RGB[0], RGB[1], RGB[2]);
		return colourReturn;
	}
	public void setTitle(String string) {
		title = string;
		
	}
	
	/**
	 * @return presentation title
	 */
	public String getTitle() {
		return title;
	}
	

	



	
	
}
