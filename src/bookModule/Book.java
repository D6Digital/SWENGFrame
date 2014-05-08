/**
 * 
 */
package bookModule;

import presentation.Image;

/**
 * 
 * @author Sam Lambert
 *
 */
public class Book {
	private String title,fileName;
	private Image buttonIcon;
	private String id;
	
public Book(String id) {
		this.setId(id);
	}

/**
 * @return the Title
 */
public String getTitle(){
	return title;
}

/**
 * @param set the Title
 */
public void setTitle(String title){
	this.title = title;
}

/**
 * @return the Filename
 */
public String getFileName() {
	return fileName;
}

/**
 * @param set the Filename
 */
public void setFileName(String fileName){
	this.fileName = fileName;
}

/**
 * @return the button icon
 */
public Image getButtonIcon() {
	return buttonIcon;
}

/**
 * @param set the button icon
 */
public void setButtonIcon(Image buttonIcon) {
	this.buttonIcon = buttonIcon;
}

/**
 * @return the id
 */
public String getId() {
	return id;
}

/**
 * @param set the id
 */
public void setId(String id) {
	this.id = id;
}

}


