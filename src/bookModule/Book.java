/**
 * 
 */
package bookModule;

/**
 * 
 * @author Sam Lambert
 * @author Robert Mills
 *
 */
public class Book {
	private String title,fileName;
	private String iconFileName;
	private String id;
	
/**
 * Create a book and set the id
 * @param id
 */
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
public String getButtonIcon() {
	return iconFileName;
}

/**
 * @param set the button icon
 */
public void setButtonIcon(String file) {
	iconFileName = file;
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


