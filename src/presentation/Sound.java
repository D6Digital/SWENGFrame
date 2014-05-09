package presentation;

/**
 * 
 * @author Josh Drake
 * @author Andrew Walter
 *
 */
public class Sound {

	private Boolean loop;
    private int start;
    private int duration;
    private String file;

	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param duration
	 * @param layer
	 * @param file
	 * @param loop
	 */
	public Sound(int start, int duration, String file, boolean loop) {
		this.start = start;
		this.duration = duration;
		this.file = file;
		this.loop = loop;
	}
	
	public Sound() {
	}
	
	public void setStart(int start) {
	    this.start = start;
	}
	
	public int getStart() {
	    return start;
	}
	
   public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public int getDuration() {
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
}
