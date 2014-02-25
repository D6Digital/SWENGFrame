/**
 * 
 */
package src;

import java.util.ArrayList;

/**
 * @author Robert Mills
 *
 */
public class Slide {
	private ArrayList<SlideObject> objectList = new ArrayList<SlideObject>(0);
	private ArrayList<Image> imageList = new ArrayList<Image>(0);
	private ArrayList<Text> textList = new ArrayList<Text>(0);
	/**
	 * @return the objectList
	 */
	public ArrayList<SlideObject> getObjectList() {
		return objectList;
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean imageAdd(Image e) {
		return imageList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Image imageGet(int index) {
		return imageList.get(index);
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean objAdd(SlideObject e) {
		return objectList.add(e);
	}
}
