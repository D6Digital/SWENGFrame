/**
 * 
 */
package presentation;

import java.util.ArrayList;

/**
 * @author Robert Mills
 * @author Chris Sainsbury
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
    
    public Slide() {
        
    }
    
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
	 * @see java.util.ArrayList#get(Integer)
	 */
	public Image getImage(Integer index) {
		return imageList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public Integer sizeImage() {
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
	 * @see java.util.ArrayList#get(Integer)
	 */
	public SlideObject getObject(Integer index) {
		return objectList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public Integer sizeObject() {
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
	 * @see java.util.ArrayList#get(Integer)
	 */
	public Text getText(Integer index) {
		return textList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public Integer sizeText() {
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
	 * @see java.util.ArrayList#get(Integer)
	 */
	public Video getVideo(Integer index) {
		return videoList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public Integer sizeVideo() {
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
	 * @see java.util.ArrayList#get(Integer)
	 */
	public Sound getSound(Integer index) {
		return soundList.get(index);
	}
	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public Integer sizeSound() {
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
	 * @see java.util.ArrayList#get(Integer)
	 */
	public Shapes getShape(Integer index) {
		return shapeList.get(index);
	}
	/**
	 * @return
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
	
    public Integer getSlideID() {
        return slideID;
    }
	
    public void setSlideID(Integer slideID) {
        this.slideID = slideID;
    }
    
    public String getSlideName() {
       return slideName;  
    }
	
    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setLastSlide(Boolean isItLastSlide) {
        this.isItLastSlide = isItLastSlide;
    }
    
    public Boolean getLastSlide() {
        return isItLastSlide;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor =  descriptor;
    }
}
