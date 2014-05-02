/**
 * 
 */
package bookModule;

import presentation.Image;

/**
 * 
 * @author swjl500
 *
 */
public class Book {
	private String title,fileName;
	private Image buttonIcon;
	
public String getTitle(){
	return title;
}

public void setTitle(String title){
	this.title = title;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName){
	this.fileName = fileName;
}

public Image getButtonIcon() {
	return buttonIcon;
}

public void setButtonIcon(Image buttonIcon) {
	this.buttonIcon = buttonIcon;
}

}


