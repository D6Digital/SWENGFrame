/**
 * 
 */
package src;

import java.util.ArrayList;

/**
 * @author Robert
 *
 */
public class Slide {
private ArrayList<SlideObject> objectList = new ArrayList<SlideObject>(0);
private ArrayList<SlideObject> imageList = new ArrayList<SlideObject>(0);
/**
 * @return the objectList
 */
public ArrayList<SlideObject> getObjectList() {
	return objectList;
}
/**
 * @return the imageList
 */
public ArrayList<SlideObject> getImageList() {
	return imageList;
}

}
