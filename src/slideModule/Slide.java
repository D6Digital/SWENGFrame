/**
 * 
 */
package slideModule;

import java.util.ArrayList;

/**
 * @author Robert Mills
 *
 */
public class Slide {
	private ArrayList<SlideObject> objectList = new ArrayList<SlideObject>(0);
	private ArrayList<Image> imageList = new ArrayList<Image>(0);
	private ArrayList<Text> textList = new ArrayList<Text>(0);
	private ArrayList<Video> videoList = new ArrayList<Video>(0);
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addImage(Image e) {
		return imageList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Image getImage(int index) {
		return imageList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int sizeImage() {
		return imageList.size();
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addObject(SlideObject e) {
		return objectList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public SlideObject getObject(int index) {
		return objectList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int sizeObject() {
		return objectList.size();
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addText(Text e) {
		return textList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Text getText(int index) {
		return textList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int sizeText() {
		return textList.size();
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addVideo(Video e) {
		return videoList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Video getVideo(int index) {
		return videoList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int sizeVideo() {
		return videoList.size();
	}
	
	
}
