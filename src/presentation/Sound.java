package presentation;

/**
 * 
 * @author Josh Drake
 * @author Andrew Walter
 *
 */
public class Sound {

	private Boolean loop;
    private Integer start;
    private Integer duration = -1000;
    private String file;
	private Integer objectStartTime = 0;

	

	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param duration
	 * @param layer
	 * @param file
	 * @param loop
	 */
	public Sound(Integer start, Integer duration, String file, boolean loop, Integer objectStartTime) {
		this.start = start;
		this.duration = duration;
		this.file = file;
		this.loop = loop;
		this.objectStartTime = objectStartTime;
	}
	
	public Sound() {
	}
	
	public void setStart(Integer start) {
	    this.start = start;
	}
	
	public Integer getStart() {
	    return start;
	}
	
   public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
    
    public String getFile() {
        return file;
    }

	public boolean getLoop(){
		return loop;
	}
	
	public void setLoop(boolean loop){
		this.loop = loop;
	}
	
	public Integer getObjectStartTime() {
		return objectStartTime;
	}

	public void setObjectStartTime(Integer objectStartTime) {
		this.objectStartTime = objectStartTime;
	}
}
