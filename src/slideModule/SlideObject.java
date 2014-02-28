/**
 * 
 */
package slideModule;

/**
 * @author Robert Mills
 * @version 1.1
 * @since 1.0
 */
public class SlideObject {
	private int x_coord;
	private int y_coord;
	private int start;
	private int end;
	private int layer;
	private String file;
	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param end
	 * @param layer
	 * @param file
	 */
	public SlideObject(int x_coord, int y_coord, int start, int end, int layer,
			String file) {
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.start = start;
		this.end = end;
		this.layer = layer;
		this.file = file;
	};


	public SlideObject() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Gets the time into the slide at which the object occurs
	 * @return start
	 */
	public int getStart() {
		return start;
	}


	/**
	 * Set the time into the slide at which the object occurs
	 * @param start 
	 */
	public void setStart(int start) {
		this.start = start;
	}


	/**
	 * Gets the time into the slide when the object disappears
	 * @return end
	 */
	public int getEnd() {
		return end;
	}


	/**
	 * Sets the time into the slide when the object disappears 
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}


	/**
	 * @return the layer
	 */
	public int getLayer() {
		return layer;
	}


	/**
	 * @param layer the layer to set
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}


	/**
	 * Sets the x coordinate of the slide object.
	 * <p>
	 * For music files this will be ignored
	 * @param x_coord the x-coordinate
	 */
	public void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}
	/**
	 * Sets the y coordinate of the slide object
	 * <p>
	 * For music files this will be ignored
	 * @param y_coord the y-coordinate
	 */
	public void setY_coord(int y_coord) {
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
	public int getX_coord() {
		return x_coord;
	}

	/**
	 * Retrieves the y-coordinate of the slide object
	 * @return the y_coord
	 */
	public int getY_coord() {
		return y_coord;
	}
	/**
	 * Retrieves the associated filename for the slide object
	 * @return the file
	 */
	public String getFile() {
		return file;
	}
}
