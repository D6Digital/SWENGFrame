package presentation;

/**
 * @author Robert Mills
 * @author Ruba Balanehru 
 */

import java.util.ArrayList;

public class Presentation {
	private ArrayList<Slide> slideList = new ArrayList<Slide>(0);
	private String author, comment, version, backgroundColour, fontColour, font, fontSize, lineColour, fillColour;
	int width, height;

	/**
	 * @return the slideList
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(Slide e) {
		return slideList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Slide get(int index) {
		return slideList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return slideList.size();
	}
	
	/**
	 * @param string
	 */
	public void setBackgroundColour(String string) {
		backgroundColour = string;
	}
	public String getBackgroundColour(){
		return backgroundColour;
	}
	/**
	 * @param string
	 */
	public void setFont(String string) {
		font = string;
	}
	public String getFont() {
		return font;
	}
	public void setFontSize(String string) {
		fontSize = string;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontColour(String string) {
		fontColour = string;
	}
	public String getFontColour() {
		return fontColour;
	}
	public void setLineColour(String string) {
		lineColour = string;
	}
	public String getLineColour(){
		return lineColour;
	}
	public void setFillColour(String string) {
		fillColour = string;
	}
	public String getFillColour(){
		return fillColour;
	}
	

	



	
	
}
