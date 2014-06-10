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

	/**
	 * @param start the time after the slide loads when the sound starts
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * @return the time after the slide loads when the sound starts
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * @param duration the time the sound should play for
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the time the sound should play for
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param file the file and filepath
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the file and filepath
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @return true is sound should loop
	 */
	public boolean getLoop(){
		return loop;
	}

	/**
	 * @param loop true if sound should loop
	 */
	public void setLoop(boolean loop){
		this.loop = loop;
	}

	/**
	 * @return the start time within the sound file
	 */
	public Integer getObjectStartTime() {
		return objectStartTime;
	}

	/**
	 * @param objectStartTime the start time within the sound file
	 */
	public void setObjectStartTime(Integer objectStartTime) {
		this.objectStartTime = objectStartTime;
	}
}
