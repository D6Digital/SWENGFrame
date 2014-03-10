/**
 * 
 */
package slideModule;

import java.util.ArrayList;

/**
 * @author Robert Mills
 * @author Chris Sainsbury
 *
 */
public class Slide {
	private ArrayList<SlideObject> objectList = new ArrayList<SlideObject>(0);
	private ArrayList<Image> imageList = new ArrayList<Image>(0);
	private ArrayList<Text> textList = new ArrayList<Text>(0);
	private ArrayList<Video> videoList = new ArrayList<Video>(0);
	private ArrayList<Sound> soundList = new ArrayList<Sound>(0);
	private ArrayList<Shapes> shapeList = new ArrayList<Shapes>(0);
    private int slideID;
    private Object slideName;
    
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
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addSound(Sound e) {
		return soundList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Sound getSound(int index) {
		return soundList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int sizeSound() {
		return soundList.size();
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addShape(Shapes e) {
		return shapeList.add(e);
	}
	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public Shapes getShape(int index) {
		return shapeList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int sizeShape() {
		return shapeList.size();
	}	
	/**
	 * @return the objectList
	 */
	public ArrayList<SlideObject> getObjectList() {
		return objectList;
	}
	/**
	 * @param objectList the objectList to set
	 */
	public void setObjectList(ArrayList<SlideObject> objectList) {
		this.objectList = objectList;
	}
	/**
	 * @return the imageList
	 */
	public ArrayList<Image> getImageList() {
		return imageList;
	}
	/**
	 * @param imageList the imageList to set
	 */
	public void setImageList(ArrayList<Image> imageList) {
		this.imageList = imageList;
	}
	/**
	 * @return the textList
	 */
	public ArrayList<Text> getTextList() {
		return textList;
	}
	/**
	 * @param textList the textList to set
	 */
	public void setTextList(ArrayList<Text> textList) {
		this.textList = textList;
	}
	/**
	 * @return the videoList
	 */
	public ArrayList<Video> getVideoList() {
		return videoList;
	}
	/**
	 * @param videoList the videoList to set
	 */
	public void setVideoList(ArrayList<Video> videoList) {
		this.videoList = videoList;
	}
	public ArrayList<Sound> getSoundList() {
		return soundList;
	}
	/**
	 * @param videoList the videoList to set
	 */
	public void setSoundList(ArrayList<Sound> soundList) {
		this.soundList = soundList;
	}
	public ArrayList<Shapes> getShapeList() {
		return shapeList;
	}
	/**
	 * @param videoList the videoList to set
	 */
	public void setShapeList(ArrayList<Shapes> shapeList) {
		this.shapeList = shapeList;
	}
	
    public int getSlideID() {
        return slideID;
    }
	
    public void setSlideID(int slideID) {
        this.slideID = slideID;
    }
    
    public Object getSlideName() {
       return slideName;  
    }
	
    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }
	public void setDuration(Object object) {
		// TODO Auto-generated method stub
		
	}
	public void setLastSlide(Object object) {
		// TODO Auto-generated method stub
		
	}
    
}
