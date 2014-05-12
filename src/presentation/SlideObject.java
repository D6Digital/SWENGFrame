/**
 * 
 */
package presentation;

/**
 * @author Robert Mills
 * @version 1.1
 * @since 1.0
 */
public class SlideObject {
	private Integer x_coord2 = null;
	private Integer y_coord = null;
	private Integer start = null;
	private Integer duration = null;
	private Integer layer = null;
	private String file;
	private Integer branch = null;
	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param end
	 * @param layer
	 * @param file
	 * @param branch
	 */
	public SlideObject(Integer x_coord, Integer y_coord, Integer start, Integer duration, Integer layer,
			String file, Integer branch) {
	    System.err.println("TEST: " + x_coord);
		//x_coord2 = x_coord;
		this.setX_coord(x_coord);
		int testx = this.getX_coord();
		System.err.println("TEST x_coord2: " + this.getX_coord());
		System.err.println("TEST x_coord2: " + x_coord2);
		this.y_coord = y_coord;
		this.start = start;
		this.duration = duration;
		this.layer = layer;
		this.file = file;
		this.branch = branch;
	}


	public SlideObject() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Gets the time into the slide at which the object occurs
	 * @return start
	 */
	public Integer getStart() {
		return start;
	}


	/**
	 * Set the time into the slide at which the object occurs
	 * @param start 
	 */
	public void setStart(Integer start) {
		this.start = start;
	}


	/**
	 * Gets the time into the slide when the object disappears
	 * @return end
	 */
	public Integer getDuration() {
		return duration;
	}


	/**
	 * Sets the time into the slide when the object disappears 
	 * @param end the end to set
	 */
	public void setDuration(Integer end) {
		this.duration = end;
	}


	/**
	 * @return the layer
	 */
	public Integer getLayer() {
		return layer;
	}


	/**
	 * @param layer the layer to set
	 */
	public void setLayer(Integer layer) {
		this.layer = layer;
	}


	/**
	 * Sets the x coordinate of the slide object.
	 * <p>
	 * For music files this will be ignored
	 * @param x_coord the x-coordinate
	 */
	public void setX_coord(Integer x_coord) {
		x_coord2 = x_coord;
	}
	/**
	 * Sets the y coordinate of the slide object
	 * <p>
	 * For music files this will be ignored
	 * @param y_coord the y-coordinate
	 */
	public void setY_coord(Integer y_coord) {
		this.y_coord = y_coord;
	}
	/**
	 * Sets a file name for an object.
	 * The file it represents will be as follows
	 * <ul>
	 * <li> Text: the font file
	 * <li> Image: the image file
	 * <li> Audio: the sound file
	 * <li> Video: the video file
	 * <li> Graphics: do not care
	 * </ul>
	 * @param file the file name including any change from current file directory
	 */
	public void setFile(String file) {
		this.file = file;
	}
	/**
	 * Retrieves the x coordinate of the slide object
	 * @return the x_coord
	 */
	public Integer getX_coord() {
	    System.err.println("here " + x_coord2);
		return x_coord2;
	}

	/**
	 * Retrieves the y-coordinate of the slide object
	 * @return the y_coord
	 */
	public Integer getY_coord() {
		return y_coord;
	}
	/**
	 * Retrieves the associated filename for the slide object
	 * @return the file
	 */
	public String getFile() {
		return file;
	}
	
	/**
	 * 
	 * @return branch
	 */
	public Integer getBranch(){
		return branch;
	}
	
	/**
	 * 
	 * 
	 */
	public void setBranch(Integer branch){
		this.branch = branch;
	}
}
