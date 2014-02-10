/**
 * 
 */
package src;

import java.util.ArrayList;

/**
 * @author Robert
 *
 */
public class XMLParser {
private String fileName;
private ArrayList<Slide> slides = new ArrayList<Slide>(0);
	/**
	 * 
	 */
	public XMLParser(String fileName) {
		super();
		this.fileName = fileName;
	}
	public ArrayList<Slide> getSlides() {
		//Insert Parser here
		return slides;
	}
	

}
