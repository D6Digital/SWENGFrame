/**
 * 
 */
package presentation;

import java.util.ArrayList;

/**
 * @author Robert Mills
 * @author Chris Sainsbury
 * This class has several constructors based on the different requirements for its use
 */
public class Slide {
	private ArrayList<SlideObject> objectList = new ArrayList<SlideObject>(0);
	private ArrayList<Image> imageList = new ArrayList<Image>(0);
	private ArrayList<Text> textList = new ArrayList<Text>(0);
	private ArrayList<Video> videoList = new ArrayList<Video>(0);
	private ArrayList<Sound> soundList = new ArrayList<Sound>(0);
	private ArrayList<Shapes> shapeList = new ArrayList<Shapes>(0);
	private Integer slideID;
	private String slideName;
	private Integer duration = null;
	private Boolean isItLastSlide = false;
	private String descriptor = "";

	/**
	 * 
	 * @param objectList
	 * @param imageList
	 * @param textList
	 * @param videoList
	 * @param soundList
	 * @param shapeList
	 * @param slideID
	 * @param slideName
	 */
	public Slide(ArrayList<SlideObject> objectList, ArrayList<Image> imageList, ArrayList<Text> textList,
			ArrayList<Video> videoList, ArrayList<Sound> soundList, ArrayList<Shapes> shapeList,
			Integer slideID, String slideName) {
		this.objectList = objectList;
		this.imageList = imageList;
		this.textList = textList;
		this.videoList = videoList;
		this.soundList = soundList;
		this.shapeList = shapeList;
		this.slideID = slideID;
		this.slideName = slideName;
	}

	/**
	 * 
	 * @param objectList
	 * @param imageList
	 * @param textList
	 * @param videoList
	 * @param soundList
	 * @param shapeList
	 * @param slideID
	 * @param slideName
	 * @param duration
	 * @param isItLastSlide
	 */
	public Slide(ArrayList<SlideObject> objectList,
			ArrayList<Image> imageList, ArrayList<Text> textList,
			ArrayList<Video> videoList, ArrayList<Sound> soundList,
			ArrayList<Shapes> shapeList, Integer slideID, String slideName,
			Integer duration, Boolean isItLastSlide) {
		this.objectList = objectList;
		this.imageList = imageList;
		this.textList = textList;
		this.videoList = videoList;
		this.soundList = soundList;
		this.shapeList = shapeList;
		this.slideID = slideID;
		this.slideName = slideName;
		this.duration = duration;
		this.isItLastSlide = isItLastSlide;
	}
	
	/**
	 * 
	 */
	public Slide() {

	}

	/**
	 * @param e the image to be added to the slide
	 * @return true if successful
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	 public boolean addImage(Image e) {
		return imageList.add(e);
	}
	/**
	 * @param index of the image to retrieved
	 * @return the image retrieved
	 * @see java.util.ArrayList#get(Integer)
	 */
	 public Image getImage(Integer index) {
		 return imageList.get(index);
	 }
	 /**
	  * @return the number of images on the slide
	  * @see java.util.ArrayList#size()
	  */
	 public Integer sizeImage() {
		 return imageList.size();
	 }
	 /**
	  * @param e the slide object to be added
	  * @return true if successful
	  * @see java.util.ArrayList#add(java.lang.Object)
	  * This method is not used as all objects found on the
	  * slide have their own defined data types
	  */
	 public boolean addObject(SlideObject e) {
		 return objectList.add(e);
	 }
	 /**
	  * @param index the index of the object to be retrieved
	  * @return the object being retrieved
	  * @see java.util.ArrayList#get(Integer)
	  */
	 public SlideObject getObject(Integer index) {
		 return objectList.get(index);
	 }
	 /**
	  * @return the number of object on the slide
	  * @see java.util.ArrayList#size()
	  */
	 public Integer sizeObject() {
		 return objectList.size();
	 }
	 /**
	  * @param e the test to be added
	  * @return true if successful
	  * @see java.util.ArrayList#add(java.lang.Object)
	  */
	 public boolean addText(Text e) {
		 return textList.add(e);
	 }
	 /**
	  * @param index the index of the text to be retrieved
	  * @return the text being retrieved
	  * @see java.util.ArrayList#get(Integer)
	  */
	 public Text getText(Integer index) {
		 return textList.get(index);
	 }
	 /**
	  * @return the number of text objects on the slide
	  * @see java.util.ArrayList#size()
	  */
	 public Integer sizeText() {
		 return textList.size();
	 }
	 /**
	  * @param e the video to be added
	  * @return true if successful
	  * @see java.util.ArrayList#add(java.lang.Object)
	  */
	 public boolean addVideo(Video e) {
		 return videoList.add(e);
	 }
	 /**
	  * @param index the index of the video to be retrieved
	  * @return
	  * @see java.util.ArrayList#get(Integer)
	  */
	 public Video getVideo(Integer index) {
		 return videoList.get(index);
	 }
	 /**
	  * @return the number of videos on the slide
	  * @see java.util.ArrayList#size()
	  */
	 public Integer sizeVideo() {
		 return videoList.size();
	 }	
	 /**
	  * @param e the sound to be added to the slide
	  * @return true if successful
	  * @see java.util.ArrayList#add(java.lang.Object)
	  */
	 public boolean addSound(Sound e) {
		 return soundList.add(e);
	 }
	 /**
	  * @param index the index of the sound object to be retrieved
	  * @return the desired sounds object
	  * @see java.util.ArrayList#get(Integer)
	  */
	 public Sound getSound(Integer index) {
		 return soundList.get(index);
	 }
	 /**
	  * @return the number of sound objects on the slide
	  * @see java.util.ArrayList#size()
	  */
	 public Integer sizeSound() {
		 return soundList.size();
	 }
	 /**
	  * @param e the shape to be added
	  * @return true if successful
	  * @see java.util.ArrayList#add(java.lang.Object)
	  */
	 public boolean addShape(Shapes e) {
		 return shapeList.add(e);
	 }
	 /**
	  * @param index the index of the desired shape
	  * @return the shape
	  * @see java.util.ArrayList#get(Integer)
	  */
	 public Shapes getShape(Integer index) {
		 return shapeList.get(index);
	 }
	 /**
	  * @return the number of shapes on the slide
	  * @see java.util.ArrayList#size()
	  */
	 public Integer sizeShape() {
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
	 /**
	 * @return the list of sound objects
	 */
	public ArrayList<Sound> getSoundList() {
		 return soundList;
	 }
	
	 /**
	 * @param soundList the list of sound object to set
	 */
	public void setSoundList(ArrayList<Sound> soundList) {
		 this.soundList = soundList;
	 }
	 /**
	 * @return the list of shapes
	 */
	public ArrayList<Shapes> getShapeList() {
		 return shapeList;
	 }
	 
	 /**
	 * @param shapeList a preset list of shapes
	 */
	public void setShapeList(ArrayList<Shapes> shapeList) {
		 this.shapeList = shapeList;
	 }

	 /**
	 * @return the ID number of the slide
	 */
	public Integer getSlideID() {
		 return slideID;
	 }

	 /**
	 * @param slideID the ID of the slide
	 */
	public void setSlideID(Integer slideID) {
		 this.slideID = slideID;
	 }

	 /**
	 * @return the name of the slide
	 */
	public String getSlideName() {
		 return slideName;  
	 }

	 /**
	 * @param slideName the name of the slide
	 */
	public void setSlideName(String slideName) {
		 this.slideName = slideName;
	 }

	 /**
	 * @param duration the time before the next slide
	 */
	public void setDuration(Integer duration) {
		 this.duration = duration;
	 }

	 /**
	 * @return the time before the next slide
	 */
	public Integer getDuration() {
		 return duration;
	 }

	 /**
	 * @param isItLastSlide true when slide is last in presentation
	 */
	public void setLastSlide(Boolean isItLastSlide) {
		 this.isItLastSlide = isItLastSlide;
	 }

	 /**
	 * @return true if last slide
	 */
	public Boolean getLastSlide() {
		 return isItLastSlide;
	 }

	 /**
	 * @return the description of the slide
	 */
	public String getDescriptor() {
		 return descriptor;
	 }

	 /**
	 * @param descriptor the description of the slide
	 */
	public void setDescriptor(String descriptor) {
		 this.descriptor =  descriptor;
	 }
}
